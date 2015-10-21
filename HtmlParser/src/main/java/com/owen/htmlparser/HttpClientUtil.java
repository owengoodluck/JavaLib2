package com.owen.htmlparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static long ignoreFileSize=20*1024;

	public static String getHtmlSource(String url) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		// 获取响应实体
		HttpEntity entity = response.getEntity();
		// 打印响应状态
		// System.out.println(response.getStatusLine());
		if (entity != null) {
			// 打印响应内容长度
			// System.out.println("Response content length: " +
			// entity.getContentLength());
			// 打印响应内容
			String content = EntityUtils.toString(entity);
			// System.out.println("Response content: " + content);
			return content;
		}else{
			System.out.println("fail to get content");
			return null;
		}
	}

	public static void parseHtmlPictureFor1688Zoom(String html,String labelStart,String labelEnd, File downloadFolder)throws Exception{
		System.out.println("Get 1688 Zoom picture");
		String imgUrl=null;
		int lengthOfOriginalLabel= labelStart.length();
		int indexBegin=html.indexOf(labelStart);
		int indexEnd=-1;
		while(indexBegin!=-1){
			indexBegin+=lengthOfOriginalLabel;
			indexEnd = html.indexOf( labelEnd ,indexBegin);
			if(indexEnd!=-1){
				imgUrl = html.substring(indexBegin, indexEnd);
				downloadPicture(imgUrl,downloadFolder);
			}else{
				System.out.println("can't find original lable ");
				break;
			}
			indexBegin=html.indexOf(labelStart,indexEnd);
		}
	}
	
	public static void parseHtmlPicture4TaoBao(String html,File downloadFolder)throws Exception{
		
		//1. get the zooming picture
		System.out.println("Get TaoBao Zooming picture");
		String lableBegin="auctionImages:[";
		String lableEnd="]";
		int lengthOfLableBegin= lableBegin.length();
		int indexBegin=html.indexOf(lableBegin);
		int indexEnd=-1;
		if(indexBegin!=-1){
			indexBegin+=lengthOfLableBegin;
			indexEnd = html.indexOf( lableEnd ,indexBegin);
			if(indexEnd!=-1){
				String imgUrls= html.substring(indexBegin,indexEnd);
				String[] urls = imgUrls.split(",");
				for(String url:urls){
					downloadPicture("http:"+url.trim().replace("\"", ""),downloadFolder);
				}
			}else{
				System.out.println("can't find the end lable");
			}
		}else{
			System.out.println("Can't find lable of [auctionImages:]");
		}
		
		//2. get detail picture
		System.out.println("Get TaoBao content detail picture");
		String detailPageUrl = StringUtil.getSubString(html, "g_config.dynamicScript(\"https:\" === location.protocol ? \"", "\"");
		String detailPageHtml = getHtmlSource("http:"+detailPageUrl);
		detailPageHtml=detailPageHtml.substring(10, detailPageHtml.length()-3); //var desc='what we need the detail'
		HtmlParserUtil.parseHtmlString(detailPageHtml, downloadFolder);
	}
	
	public static void downloadPicture(String url,File downloadFolder) {
		System.out.println(url+">>>>>>>>>>>>>>>>>"+downloadFolder);
		FileOutputStream output =null;
    	InputStream instream =null;
    	long fileSize=0;
    	File storeFile = null;
        try {
        	String picFileName=url.substring(url.lastIndexOf("/"));
        	if(picFileName!=null){
        		if(picFileName.toLowerCase().endsWith(".jpg")){
        			//ok
        		}else if(picFileName.toLowerCase().indexOf(".jpg?")!=-1){
        			picFileName=picFileName.substring(0, picFileName.toLowerCase().indexOf(".jpg?")+4);
        		}
        	}
        	storeFile = new File(downloadFolder.getAbsolutePath()+"/"+picFileName);  
        	if(storeFile.exists()){
        		System.out.println("  File exists :"+storeFile.getAbsolutePath());
        		return;
        	}
        	HttpClient client =  new DefaultHttpClient();  
        	HttpGet httpget = new HttpGet(url);
        	HttpResponse response = client.execute(httpget);
        	instream = response.getEntity().getContent();
        	
        	output = new FileOutputStream(storeFile);  
	        byte b[] = new byte[1024];
	        int j = 0;
	        while( (j = instream.read(b))!=-1){
	        	fileSize+=j;
	        	output.write(b,0,j);
	        }
	        output.flush();
	        output.close();
	        instream.close();
//	        System.out.println(storeFile.getAbsolutePath()+" s file size ("+fileSize+")");
	        if(fileSize>=0 && fileSize <= ignoreFileSize){
	        	if(storeFile.delete()){
	        		System.out.println(storeFile.getAbsolutePath()+" is deleted as file size ("+fileSize+") is smaller than (k) "+ignoreFileSize/1024);
	        	}else{
	        		System.out.println(storeFile.getAbsolutePath()+" is faile to be deleted !!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        	}
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	public static void accessUrl(String url) throws Exception{
		HttpClient client =  new DefaultHttpClient();  
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = client.execute(httpget);
		System.out.println(url+",response status = "+response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if( entity != null ) {
	         EntityUtils.consume(entity);
	    }
	}
}
