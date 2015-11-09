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
	public void testDownload1688() {
		String[] urls = new String[] {"http://detail.1688.com/offer/520066973714.html?spm=a2615.7691456.0.0.aIMURX"};
		for (String url : urls) {
			this.pictureDownloadService.downloadPictue(url, targetRootFolder);
		}
	}

	@Test
	public void testDownloadTaobao() {
		String[] urls = new String[] { 
				"http://detail.1688.com/offer/39313310587.html?spm=0.0.0.0.jQojEd",
				"http://detail.1688.com/offer/35537393292.html?spm=0.0.0.0.jQojEd",
				"http://detail.1688.com/offer/38910265060.html?spm=0.0.0.0.xMKF8k" };
		for (String url : urls) {
			this.pictureDownloadService.downloadPictue(url, targetRootFolder);
		}
	}

	@Test
	public void testDownloadAmazon() {
		String[] urls = new String[] {"http://www.amazon.com/CVO-Jewelry-Stainless-Vintage-Necklace/dp/B017859DDE/ref=sr_1_8?m=A75HRC7E5LZBX&s=merchant-items&ie=UTF8&qid=1446653981&sr=1-8"};
		for (String url : urls) {
			this.amazonPictureDownloadService.downloadPictue(url, targetRootFolder);
		}
	}
}
