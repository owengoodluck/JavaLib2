package com.owen.htmlparser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class GetSample {
	
	private String url ="http://detail.1688.com/offer/40923537011.html?spm=a261y.7663282.1998411376.2.CSbdHm";   
	
	public static void main(String[] args) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();  

		HttpGet httpget = new HttpGet("http://www.baidu.com/");
		HttpResponse response = httpclient.execute(httpget);
		
		try {
			// 获取响应实体  
			HttpEntity entity = response.getEntity();
			System.out.println("--------------------------------------");
			// 打印响应状态  
			System.out.println(response.getStatusLine());
			if (entity != null) {
				// 打印响应内容长度  
				System.out.println("Response content length: " + entity.getContentLength());
				// 打印响应内容  
				System.out.println("Response content: " + EntityUtils.toString(entity));
			}
			System.out.println("------------------------------------");
		} finally {
		}
	}
}