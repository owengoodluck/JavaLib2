package com.owen.htmlparser.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.owen.htmlparser.service.PictureDownloadService;
import com.owen.htmlparser.util.FileUtil;
import com.owen.htmlparser.util.HtmlParserUtil;
import com.owen.htmlparser.util.PictureDownloadUtil;
import com.owen.htmlparser.util.StringUtil;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class AmazonPictureDownloadServiceImpl implements PictureDownloadService {

	private Logger log = Logger.getLogger(this.getClass());
	
	public void downloadPictue(String url, String targetRootFolder,Integer picFilterSize) {
		this.log.info("-----Parse Amazon URL: "+url);
		//1.  get html content
		String htmlContent = null;
		try {
			htmlContent = HtmlParserUtil.getHtmlContent(url);
//			title = HtmlParserUtil.getHtmlTitleTag(htmlContent);
//			title.replace("Amazon.com", "");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return;
		}

		//2. create sub folder
//		title = StringUtil.removeIlleaglePathCharacter(title);
		String asin = StringUtil.getAmazonASINFromUrl(url);
		File subFolder = new File(targetRootFolder+"/"+asin);
		if(!subFolder.exists()){
			if(subFolder.mkdirs()){
				this.log.info(subFolder.getAbsolutePath()+" folder is created ");
			}else{
				this.log.info(subFolder.getAbsolutePath()+" folder is fail to be created ");
			}
		}

		//3. create short cut url
		FileUtil.createInternetShortcut(subFolder.getAbsolutePath()+"/Amazon_"+asin+".url", url);

		//4. download zoom picture
		List<String> imgUrlList = null;
		//4.1 check if sub item data exists
		String originalLabel = "data[\"colorImages\"] =";
		String endLabel = "data[\"heroImage\"]";
		int indexStart = htmlContent.indexOf(originalLabel);
		int indexEnd = htmlContent.indexOf(endLabel);
		String jsonDataOfPics = null;
		if(indexStart >-1){
			//there are sub items
			jsonDataOfPics = htmlContent.substring(indexStart+originalLabel.length(), indexEnd-1).trim();
			if(jsonDataOfPics.endsWith(";")){
				jsonDataOfPics = jsonDataOfPics.substring(0, jsonDataOfPics.length()-1);
			}
		}
		if(jsonDataOfPics.trim().length()<=2){
			//no sub items
			originalLabel = " 'colorImages':";
			endLabel = "'colorToAsin':";
			indexStart = htmlContent.indexOf(originalLabel);
			indexEnd = htmlContent.indexOf(endLabel);
			jsonDataOfPics = htmlContent.substring(indexStart+originalLabel.length(), indexEnd-1).trim();
			if(jsonDataOfPics.endsWith(",")){
				jsonDataOfPics = jsonDataOfPics.substring(0, jsonDataOfPics.length()-1);
			}
			System.out.println(jsonDataOfPics);
		}
		this.parseSubitemsPic(jsonDataOfPics,subFolder,picFilterSize);
	}

	/**
	 * Parse amazon json data to get all sub item's pic url
	 * @param jsonString
	 */
	private void parseSubitemsPic(String jsonString,File folder,Integer picFilterSize) {
		List<String> picUrlList = new ArrayList<String> ();
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonString);
		Map<String, ArrayList> map = (Map) JSONObject.toBean(jsonObject, Map.class);
		for(String key:map.keySet()){
			ArrayList<MorphDynaBean> subItemPicList = map.get(key);
			File subFolder = new File(folder.getAbsolutePath()+"/"+key.replaceAll(" ", ""));
			if(!subFolder.exists()){
				subFolder.mkdirs();
			}
			//for each sub item , there are pictures
			for (MorphDynaBean pic : subItemPicList) {
				String picUrl=null;
				try {
					picUrl = pic.get("hiRes").toString();
				} catch (Exception e) {
					picUrl = pic.get("large").toString();
				}
				PictureDownloadUtil.downloadPicture(picUrl, subFolder,picFilterSize);
			}
		}
	}
}
