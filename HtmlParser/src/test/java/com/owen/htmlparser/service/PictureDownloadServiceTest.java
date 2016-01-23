package com.owen.htmlparser.service;

import org.junit.Test;

import com.owen.htmlparser.service.PictureDownloadService;
import com.owen.htmlparser.service.impl.AlibabaGroupPictureDownloadServiceImpl;
import com.owen.htmlparser.service.impl.AmazonPictureDownloadServiceImpl;

public class PictureDownloadServiceTest {

	private PictureDownloadService pictureDownloadService = new AlibabaGroupPictureDownloadServiceImpl();
	private PictureDownloadService amazonPictureDownloadService = new AmazonPictureDownloadServiceImpl();
	private String targetRootFolder = "C:/Users/owen/Desktop/tmp";

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
//		String[] urls = new String[] {"http://www.amazon.com/Dolphins-Swarovski-Zirconia-Crystal-Necklace/dp/B00NQVXCCS"};
		String[] urls = new String[] {"http://www.amazon.com/Gifts-Decor-Dolphin-Sculpture-Figurine/dp/B008YQ4XNW",
				"http://www.amazon.com/Dolphins-Swarovski-Zirconia-Crystal-Necklace/dp/B00NQVXCCS",
				"http://www.amazon.com/KATGI-Fashion-Beautiful-Crystal-Necklace/dp/B00KDUQ894"};
		for (String url : urls) {
			this.amazonPictureDownloadService.downloadPictue(url, targetRootFolder);
		}
	}
}
