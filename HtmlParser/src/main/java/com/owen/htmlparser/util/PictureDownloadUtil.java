package com.owen.htmlparser.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class PictureDownloadUtil {
	private static Logger log = Logger.getLogger(PictureDownloadUtil.class);
	public static long ignoreFileSize=15*1024;
	
	private static Set<String> ignoreUrlSet= new HashSet<String>();
	static{
		ignoreUrlSet.add("http://i02.c.aliimg.com/img/ibank/2013/180/734/928437081_2118088041.jpg");
		ignoreUrlSet.add("http://i02.c.aliimg.com/img/ibank/2014/340/667/1166766043_1837161286.jpg");
		ignoreUrlSet.add("http://i00.c.aliimg.com/img/ibank/2014/966/766/1298667669_1837161286.jpg");
		ignoreUrlSet.add("http://i03.c.aliimg.com/img/ibank/2014/743/171/1401171347_1837161286.jpg");
		ignoreUrlSet.add("http://i05.c.aliimg.com/img/ibank/2015/970/274/2250472079_50040663.jpg");
		ignoreUrlSet.add("http://i02.c.aliimg.com/img/ibank/2015/919/551/2254155919_50040663.jpg");
		ignoreUrlSet.add("http://i00.c.aliimg.com/img/ibank/2015/930/216/2254612039_50040663.jpg");
		ignoreUrlSet.add("http://ecx.images-amazon.com/images/I/711s08Zj65L._UL1500_.jpg");
		ignoreUrlSet.add("http://ecx.images-amazon.com/images/I/61xDVRrdelL._UL1500_.jpg");
		ignoreUrlSet.add("http://ecx.images-amazon.com/images/I/51GdSzNHrKL._UL1500_.jpg");
		ignoreUrlSet.add("http://ecx.images-amazon.com/images/I/51tcXji2W4L._UL1500_.jpg");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
	}

	public static File downloadPicture(String url,File downloadFolder) {
		File storeFile = null;
		if(url == null || url.trim().length()<1){
			return storeFile;
		}
		if(ignoreUrlSet.contains(url)){
			log.warn("ignore image url as it exist in the ignore list");
			return storeFile;
		}else{
			log.info(url+">>>>>>>>>>>>>>>>>"+downloadFolder);
		}
		FileOutputStream output =null;
    	InputStream instream =null;
    	long fileSize=0;
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
        		log.info("  File exists :"+storeFile.getAbsolutePath());
        		return storeFile;
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
	        if(fileSize>=0 && fileSize <= ignoreFileSize){
	        	if(storeFile.delete()){
	        		log.info(storeFile.getAbsolutePath()+" is deleted as file size ("+fileSize+") is smaller than (k) "+ignoreFileSize/1024);
	        	}else{
	        		log.info(storeFile.getAbsolutePath()+" is faile to be deleted !!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        	}
	        }
        }catch(Exception e){
        	log.error(e.getMessage(),e);
        }
        return storeFile;
	}
}
