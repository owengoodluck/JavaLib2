package com.owen.wms.web.thread;

import java.io.File;

import com.owen.htmlparser.util.PictureDownloadUtil;

public class PictureDownLoadThread extends Thread{
	private String url;
	private File downloadFolder; 

	
	public PictureDownLoadThread(String url, File downloadFolder) {
		super();
		this.url = url;
		this.downloadFolder = downloadFolder;
	}

	public void run() {
		PictureDownloadUtil.downloadPicture(url, downloadFolder);
	}

}
