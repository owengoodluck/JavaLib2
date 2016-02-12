package com.owen.wms.web.form;

import java.util.List;

import com.owen.wms.web.constants.AppConstant;

public class PictureDownloadPackage {
	private int picSize=15;//k
	private String downloadPath=AppConstant.pictureDownloadTmpPath;
	private String picSource="Alibaba";
	private List<URLString> urlList;
	
	public String getPicSource() {
		return picSource;
	}
	public void setPicSource(String picSource) {
		this.picSource = picSource;
	}
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	public List<URLString> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<URLString> urlList) {
		this.urlList = urlList;
	}
	public int getPicSize() {
		return picSize;
	}
	public void setPicSize(int picSize) {
		this.picSize = picSize;
	}
	
	
}
