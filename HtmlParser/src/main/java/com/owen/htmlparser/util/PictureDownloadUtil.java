package com.owen.htmlparser.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class PictureDownloadUtil {
	public static long ignoreFileSize=10*1024;

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
        		}else if(picFileName.toLowerCase().indexOf("?")!=-1){
        			picFileName=picFileName.substring(0, picFileName.toLowerCase().indexOf("?"));
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
}
