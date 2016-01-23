package com.owen.htmlparser.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParserUtil {
	
	private static Logger log = Logger.getLogger(HtmlParserUtil.class);

	public static String getHtmlContent(String url) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String content = EntityUtils.toString(entity);
			return content;
		}else{
			log.error("fail to get content for URL = "+url);
			return null;
		}
	}
	
	public static List<String> getImageTagUrlList(String htmlContentString){
		List<String> imgUrlList=new ArrayList<String>();
		Parser parser =  new  Parser();
		NodeList nodeList = null;
		try {
			parser.setInputHTML(htmlContentString);
			NodeClassFilter imgClassFilter = new NodeClassFilter(ImageTag.class);
			nodeList = parser.extractAllNodesThatMatch(imgClassFilter);
		} catch (ParserException e) {
			log.warn(e.getMessage(),e);
		}
		if(nodeList!=null && nodeList.size()>0){
			for(int i=0;i<nodeList.size();i++){
				ImageTag imgTag = (ImageTag)nodeList.elementAt(i);
				String imgUrl=imgTag.getImageURL();
				if(imgUrl.trim().length()<1){
					continue;
				}
				if(imgUrl.startsWith("\\\"http:")){
					// url =\"http://cbu01.alicdn.com/img/ibank/2014/291/571/1717175192_514610680.jpg\"
					imgUrl=imgUrl.substring(2, imgUrl.length()-1);
				}
				if(imgUrl.endsWith("\\")){
					imgUrl=imgUrl.substring(0, imgUrl.length()-1);
				}
				if(!imgUrl.startsWith("http:")&&!imgUrl.startsWith("https:")){
					imgUrl="http:"+imgUrl;
				}
				imgUrlList.add(imgUrl);
			}
		}
		return imgUrlList;
	}
	
	public static String getHtmlTitleTag(String htmlContent){
		String labelStart="<title>";
		String labelEnd="</title>";
		String title = StringUtil.getSubString(htmlContent, labelStart, labelEnd);
		return title;
	}
	
	public static void accessUrl(String url) throws Exception{
		HttpClient client =  new DefaultHttpClient();  
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = client.execute(httpget);
		log.info(url+",response status = "+response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if( entity != null ) {
	         EntityUtils.consume(entity);
	    }
	}
}
