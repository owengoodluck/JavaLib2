package com.owen.htmlparser.service;

import org.junit.Test;

import com.owen.htmlparser.service.PictureDownloadService;
import com.owen.htmlparser.service.impl.AlibabaGroupPictureDownloadServiceImpl;
import com.owen.htmlparser.service.impl.AmazonPictureDownloadServiceImpl;

public class PictureDownloadServiceTest {

	private PictureDownloadService pictureDownloadService = new AlibabaGroupPictureDownloadServiceImpl();
	private PictureDownloadService amazonPictureDownloadService = new AmazonPictureDownloadServiceImpl();
	private String targetRootFolder = "C:/Users/owen/Desktop/Amazon/pictures/temp";
	
	@Test
	public void testDownload1688(){
		String url=  "http://detail.1688.com/offer/520576225172.html?spm=a2615.7691456.0.0.TghlUy";
		this.pictureDownloadService.downloadPictue(url, targetRootFolder);
	}
	@Test
	public void testDownloadTaobao(){
		String url = "https://item.taobao.com/item.htm?spm=a230r.1.14.14.tbAjYB&id=521729457523&ns=1&abbucket=8#detail";
		url = "https://item.taobao.com/item.htm?spm=a230r.1.14.82.tbAjYB&id=521513859421&ns=1&abbucket=8#detail";
		this.pictureDownloadService.downloadPictue(url, targetRootFolder);
	}
	
	@Test
	public void testDownloadAmazon(){
		String url = "http://www.amazon.com/CVO-Jewelry-Leather-Bracelet-Relationship/dp/B016GLB6VS/ref=sr_1_17?m=A75HRC7E5LZBX&s=merchant-items&ie=UTF8&qid=1446469727&sr=1-17";
		this.amazonPictureDownloadService.downloadPictue(url, targetRootFolder);
	}
}
