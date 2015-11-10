package com.owen.wms.web.model;

import java.util.List;

public class PictureDownloadPackage {

	private String downloadPath="C:/Users/owen/Desktop/Amazon/pictures/temp";
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
	
	
}
