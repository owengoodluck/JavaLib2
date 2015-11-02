package com.owen.test;

import org.junit.Test;

import com.owen.htmlparser.PictureDownloadService;

public class PictureDownloadServiceTest {

	private PictureDownloadService pictureDownloadService = new PictureDownloadService();
	private String targetRootFolder = "C:/Users/owen/Desktop/Amazon/pictures/temp";
	
	@Test
	public void testDownload1688(){
		String url = "https://item.taobao.com/item.htm?spm=a230r.1.14.14.tbAjYB&id=521729457523&ns=1&abbucket=8#detail";
		url = "https://item.taobao.com/item.htm?spm=a230r.1.14.82.tbAjYB&id=521513859421&ns=1&abbucket=8#detail";
//		url=  "http://detail.1688.com/offer/521627405218.html?spm=0.0.0.0.VDc3M4";
		this.pictureDownloadService.downloadPictueFromAlibabaGroup(url, targetRootFolder);
	}
	
}
