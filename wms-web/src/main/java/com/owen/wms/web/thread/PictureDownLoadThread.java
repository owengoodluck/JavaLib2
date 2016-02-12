package com.owen.wms.web.thread;

import java.io.File;

import com.owen.htmlparser.util.FileUtil;
import com.owen.htmlparser.util.PictureDownloadUtil;
import com.owen.wms.web.constants.AppConstant;

public class PictureDownLoadThread extends Thread{
	private String url;
	private File downloadFolder; 
	private String copyFolder=AppConstant.picCopyFolder;
	private int picFilterSize = 10;
	public PictureDownLoadThread(String url, File downloadFolder) {
		super();
		this.url = url;
		this.downloadFolder = downloadFolder;
	}

	public void run() {
		File file = PictureDownloadUtil.downloadPicture(url, downloadFolder,picFilterSize);
		if(file!=null && file.exists()){
			File newFile = new File(copyFolder+file.getName());
			if(!newFile.exists()){
				FileUtil.copy(file, newFile );
			}
		}
	}

}
