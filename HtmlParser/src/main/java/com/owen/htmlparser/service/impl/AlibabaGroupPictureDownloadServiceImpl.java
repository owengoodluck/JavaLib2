package com.owen.htmlparser.service.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.owen.htmlparser.service.PictureDownloadService;
import com.owen.htmlparser.util.FileUtil;
import com.owen.htmlparser.util.HtmlParserUtil;
import com.owen.htmlparser.util.PictureDownloadUtil;
import com.owen.htmlparser.util.StringUtil;

/*
 * 1688 and  Taobao URL
 * 
 * 1. Get the target url HTML page content
 * 2. Get the hidden url for dynamic page display
 * 3. get img tag lab and download it to target folder
 * 4. get zooming zone picture 
 * 
*/
public class AlibabaGroupPictureDownloadServiceImpl implements PictureDownloadService{
	
	private Logger log = Logger.getLogger(this.getClass());
	private String defaultFolder="C:/Users/owen/Desktop/Amazon/pictures/temp";

	public void downloadPictue(String url,String targetRootFolder){
		if(targetRootFolder == null){
			targetRootFolder = this.defaultFolder;
		}
		
		//1.get html content
		String htmlContent = null;
		try {
			htmlContent = HtmlParserUtil.getHtmlContent(url);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		//2. get title and create sub folder under the root folder 
		String title = this.getTitle4AlibabaGroup(url,htmlContent);
		title=title.replace("/", "");
		title=title.replace(":", "");
		title=title.replace(" ", "");
		File subFolder = new File(targetRootFolder+"/"+title);
		if(!subFolder.exists()){
			subFolder.mkdir();
		}
		
		//3. Create shortcut url
		FileUtil.createInternetShortcut(subFolder.getAbsolutePath()+"/"+title+".url", url);
		
		//4.Get the hidden url for dynamic page display and the content
		String lazyLoadDetailDescURL = getLazyLoadDetailDescURL4AlibabaGroup(url,htmlContent);
		String lazyLoadDetailDescHtmlContent = null;
		try {
			lazyLoadDetailDescHtmlContent = HtmlParserUtil.getHtmlContent(lazyLoadDetailDescURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//5. Get img tag label list
		List<String> imgUrlList = HtmlParserUtil.getImageTagUrlList(lazyLoadDetailDescHtmlContent);
		if(imgUrlList!=null){
			for(String imgUrl:imgUrlList){
				PictureDownloadUtil.downloadPicture(imgUrl, subFolder);
			}
		}
		
		//6.download zoom picture for 1688 only
		if(url.indexOf("detail.1688.com")!=-1){
			this.downLoadZoomPicFor1688(htmlContent, subFolder);
		}
	}
	
	private void downLoadZoomPicFor1688(String htmlContent,File subFolder){
		String originalLabel = "\",\"original\":\"";
		String endLabel = "\"}'>";
		List<String> imgUrlList = StringUtil.iteratorGetSubStringList(htmlContent, originalLabel, endLabel);
		if(imgUrlList!=null && !imgUrlList.isEmpty()){
			for(String imgUrl:imgUrlList){
				PictureDownloadUtil.downloadPicture(imgUrl, subFolder);
			}
		}
	}
	
	private String getTitle4AlibabaGroup(String url, String htmlContent){
		String title= HtmlParserUtil.getHtmlTitleTag(htmlContent);
		String pageNumber = url.indexOf("1688.com")!=-1 ? 
				url.substring(url.indexOf("offer")+6,url.indexOf(".html")) : 
				url.substring(url.indexOf("&id=")+5, url.indexOf("&ns=")) ;
		if(title == null || title.trim().length()<1){
			log.warn("Fail to get tile");
			title = pageNumber;
		}else{
			title=pageNumber+"-"+title;
		}
		return title;
	}
	
	private String getLazyLoadDetailDescURL4AlibabaGroup(String url,String htmlContent){
		String labelStart = "data-tfs-url=\"";
		String labelEnd ="\"";
		if(url.indexOf("taobao.com")>0){
			labelStart = "location.protocol==='http:' ? '";
			labelEnd="'";
		}
		String lazyLoadDetailDescURL= StringUtil.getSubString(htmlContent,labelStart,labelEnd);
		if(!lazyLoadDetailDescURL.startsWith("http:")&&!lazyLoadDetailDescURL.startsWith("https:")){
			lazyLoadDetailDescURL="http:"+lazyLoadDetailDescURL;
		}
		return lazyLoadDetailDescURL;
	}
	
}
