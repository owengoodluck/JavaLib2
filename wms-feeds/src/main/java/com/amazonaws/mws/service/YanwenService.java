package com.amazonaws.mws.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.amazonaws.mws.config.Owen;
import com.amazonaws.mws.entity.yanwen.ExpressType;
import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.amazonaws.mws.util.JaxbUtil;

public class YanwenService {
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd-HHmm");
	private String serviceEndpoint = "HTTP://ONLINE.YW56.COM.CN/SERVICE/";
	
	/**
	 * {SERVICEENDPOINT}/USERS/{USERID}/EXPRESSES
	 * @param userID
	 * @param userToken
	 * @param expressType
	 */
	public CreateExpressResponseType createExpress(ExpressType expressType){
		String url=serviceEndpoint+"Users/"+Owen.yanwenUserId+"/EXPRESSES";
		HttpClient client =  new DefaultHttpClient();  
		HttpPost request = new HttpPost(url.toString());
		setHeaderProperties(request,Owen.yanwenUserToken);
		
		HttpEntity requestEntity  = null;
		String charset="UTF8";
		try {
			requestEntity  = new ByteArrayEntity(JaxbUtil.toXml(expressType).getBytes(charset));
		} catch (UnsupportedEncodingException e1) {
			this.log.error("",e1);
		}
		request.setEntity(requestEntity);
		
		try {
			HttpResponse response = client.execute(request);
			HttpEntity responseEntity = response.getEntity();
			String result = EntityUtils.toString(responseEntity);
			this.log.info(result);
			CreateExpressResponseType obj = (CreateExpressResponseType) JaxbUtil.toObj(new StringReader(result), CreateExpressResponseType.class);
			return obj;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException("Faile to create yanwen express with error msg :"+e.getMessage());
		}
	}
	
	/**
	 * {SERVICEENDPOINT}/USERS/{USERID}/EXPRESSES/{EPCODE}/{LABELSIZE}LABEL
	 * @param userID
	 * @param userToken
	 * @param epCode
	 */
	public String downloadLabel(String epCode,String downloadFolder){
		String targetFilePath =null;
		String url=serviceEndpoint+"Users/"+Owen.yanwenUserId+"/EXPRESSES/"+epCode+"/A10x10LCLabel";
		this.log.info(url);
		HttpClient client =  new DefaultHttpClient();  
		HttpGet request = new HttpGet(url.toString());
		setHeaderProperties(request,Owen.yanwenUserToken);
		
		InputStream is = null;
		OutputStream os = null;
		try {
			HttpResponse response = client.execute(request);
			HttpEntity responseEntity = response.getEntity();
			is = responseEntity.getContent();
			File folder = new File(downloadFolder+"/"+this.sdf.format(new Date()));
			if(!folder.exists()){
				folder.mkdir();
			}
			targetFilePath = folder.getAbsolutePath()+"/"+epCode+".pdf";
			os = new FileOutputStream(targetFilePath);
			byte[] buf = new byte[2048];
			int length = -1;
			while ((length = is.read(buf)) != -1 ){
				os.write(buf, 0, length);
			}
			os.flush();
			this.log.info(" Label is download into "+ folder.getAbsolutePath());
			return targetFilePath;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException("Faile to download PDF file :"+e.getMessage());
		}finally{
			if(os !=null){
				try {
					os.close();
				} catch (IOException e) { }
			}
			if(is !=null){
				try {
					is.close();
				} catch (IOException e) { }
			}
		}
	}
	
	
	/**
	 * {SERVICEENDPOINT}/USERS/{USERID}/EXPRESSES?PAGE={PAGEINDEX}&CODE={EPCODEFILTER}&RECEIVER={RECEIVERFILTER}&CHANNEL={CHANNEL}&START={START}&END={END}&ISSTATUS={ISSTATUS}
	 * 
	 * String receiver,String channel,String startDate,String endDate,String isSatus
	 */
	public String queryStatus(Integer pageIndex,String epCode){
		String result = null;
		StringBuffer url=new StringBuffer(serviceEndpoint+"Users/"+Owen.yanwenUserId+"/EXPRESSES?");
		
		if(pageIndex!=null){
			url.append("PAGE="+pageIndex);
		}else{
			url.append("PAGE=1");
		}
		if(epCode!=null && epCode.trim().length()>0){
			url.append("&CODE="+epCode);
		}//TODO other conditions
		
		HttpClient client =  new DefaultHttpClient();  
		HttpGet request = new HttpGet(url.toString());
		setHeaderProperties(request,Owen.yanwenUserToken);
		
		try {
			HttpResponse response = client.execute(request);
			HttpEntity responseEntity = response.getEntity();
			result = EntityUtils.toString(responseEntity);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		return result;
	}
	
	/**
	 * {ServiceEndPoint}/Users/{USERID}/GetChannels
	 * @param userID
	 * @param userToken
	 */
	public String getChannels(){
		String result = null;
		String url=serviceEndpoint+"Users/"+Owen.yanwenUserId+"/GetChannels";
		HttpClient client =  new DefaultHttpClient();  
		HttpGet request = new HttpGet(url);
		setHeaderProperties(request,Owen.yanwenUserToken);
		try {
			HttpResponse response = client.execute(request);
			HttpEntity responseEntity = response.getEntity();
			result = EntityUtils.toString(responseEntity);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}
	
	private void setHeaderProperties(HttpRequestBase request,String userToken){
		request.setHeader("Authorization", "Basic "+userToken);;
	}
}
