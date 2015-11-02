package com.owen.htmlparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.owen.htmlparser.util.PictureDownloadUtil;

public class PictureDownloadService {
	private String defaultFolder;
/*
 * 1688 and  Taobao URL
 * 
 * 1. Get the target url HTML page content
 * 2. Get the hidden url for dynamic page display
 * 3. get img tag lab and download it to target folder
 * 4. get zooming zone picture 
 * 
*/
	public void downloadPictueFromAlibabaGroup(String url,String targetRootFolder){
		//1.get html content
		String htmlContent = null;
		try {
			htmlContent = HttpClientUtil.getHtmlSource(url);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		//2. get title and create sub folder under the root folder 
		String title = this.getTitle4AlibabaGroup(htmlContent);
		title=title.replace("/", "");
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
			lazyLoadDetailDescHtmlContent = HttpClientUtil.getHtmlSource(lazyLoadDetailDescURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//5. Get img tag label list
		List<String> imgUrlList = this.getImageTagLabelList(lazyLoadDetailDescHtmlContent);
		if(imgUrlList!=null){
			for(String imgUrl:imgUrlList){
				PictureDownloadUtil.downloadPicture(imgUrl, subFolder);
			}
		}
	}
	
	private List<String> getImageTagLabelList(String htmlString){
		List<String> imgUrlList=new ArrayList<String>();
		Parser parser =  new  Parser();
		NodeList nodeList = null;
		try {
			parser.setInputHTML(htmlString);
			NodeClassFilter imgClassFilter = new NodeClassFilter(ImageTag.class);
			nodeList = parser.extractAllNodesThatMatch(imgClassFilter);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		if(nodeList!=null && nodeList.size()>0){
			for(int i=0;i<nodeList.size();i++){
				ImageTag imgTag = (ImageTag)nodeList.elementAt(i);
				String imgUrl=imgTag.getImageURL();
				if(imgUrl.trim().length()<1){
					continue;
				}
				if(!imgUrl.startsWith("http:")&&!imgUrl.startsWith("https:")){
					imgUrl="http:"+imgUrl;
				}
				imgUrlList.add(imgUrl);
			}
		}
		return imgUrlList;
	}
	
	private String getTitle4AlibabaGroup(String htmlContent){
		String title=null;
		String labelStart="<title>";
		String labelEnd="</title>";
		int lengthOfOriginalLabel= labelStart.length();
		int indexBegin=htmlContent.indexOf(labelStart);
		int indexEnd=-1;
		if(indexBegin!=-1){
			indexBegin+=lengthOfOriginalLabel;
			indexEnd = htmlContent.indexOf( labelEnd ,indexBegin);
			if(indexEnd!=-1){
				title = htmlContent.substring(indexBegin, indexEnd);
			}else{
				title = System.currentTimeMillis()+"";
				System.out.println("can't find title lable end:"+labelEnd);
			}
		}else{
			System.out.println("can't find title lable start:" + labelStart);
			title = System.currentTimeMillis()+"";
		}
//		title.replace("~", "");
		return title;
	}
	
	private String getLazyLoadDetailDescURL4AlibabaGroup(String url,String htmlContent){
		String labelStart = "data-tfs-url=\"";
		String labelEnd ="\"";
		if(url.indexOf("taobao.com")>0){
			labelStart = "location.protocol==='http:' ? '";
			labelEnd="'";
		}
		String lazyLoadDetailDescURL= this.getStringBetweenStartAndEndLable(htmlContent,labelStart,labelEnd);
		if(!lazyLoadDetailDescURL.startsWith("http:")&&!lazyLoadDetailDescURL.startsWith("https:")){
			lazyLoadDetailDescURL="http:"+lazyLoadDetailDescURL;
		}
		return lazyLoadDetailDescURL;
	}
	
	private String getStringBetweenStartAndEndLable(String str,String labelStart,String labelEnd){
		String middleString=null;
		int lengthOfOriginalLabel= labelStart.length();
		int indexBegin=str.indexOf(labelStart);
		int indexEnd=-1;
		if(indexBegin!=-1){
			indexBegin+=lengthOfOriginalLabel;
			indexEnd = str.indexOf( labelEnd ,indexBegin);
			if(indexEnd!=-1){
				middleString = str.substring(indexBegin, indexEnd);
			}else{
				System.out.println("can't find title lable end:"+labelEnd);
			}
		}else{
			System.out.println("can't find title lable start:" + labelStart);
		}
		return middleString;
	}
}
