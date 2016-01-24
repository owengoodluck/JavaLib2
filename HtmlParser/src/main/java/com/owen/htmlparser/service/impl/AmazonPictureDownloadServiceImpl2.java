package com.owen.htmlparser.service.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.owen.htmlparser.service.PictureDownloadService;
import com.owen.htmlparser.util.FileUtil;
import com.owen.htmlparser.util.HtmlParserUtil;
import com.owen.htmlparser.util.PictureDownloadUtil;
import com.owen.htmlparser.util.StringUtil;

public class AmazonPictureDownloadServiceImpl2 implements PictureDownloadService {

	private Logger log = Logger.getLogger(this.getClass());
	
	public void downloadPictue(String url, String targetRootFolder,Integer picFilterSize) {
		this.log.info("-----Parse Amazon URL: "+url);
		//1.  get html content and tiltle
		String htmlContent = null;
		String title= null;
		try {
			htmlContent = HtmlParserUtil.getHtmlContent(url);
			title = HtmlParserUtil.getHtmlTitleTag(htmlContent);
			title.replace("Amazon.com", "");
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
		String originalLabel = "hiRes\":\"";
		String endLabel = "\"";
		List<String> imgUrlList = StringUtil.iteratorGetSubStringList(htmlContent, originalLabel, endLabel);
		if(imgUrlList!=null && !imgUrlList.isEmpty()){
			for(String imgUrl:imgUrlList){
				PictureDownloadUtil.downloadPicture(imgUrl, subFolder,picFilterSize);
			}
		}

	}

}
