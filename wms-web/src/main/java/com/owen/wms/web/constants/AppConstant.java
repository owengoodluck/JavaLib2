package com.owen.wms.web.constants;

public class AppConstant {
	public final static Double ShippingFeeEarnPerShip = 2.9;  //USD
	public final static Double ShippingFeeEarnPerItem = 1.95;  //USD
	public final static Double USDRate = 6.4;  
	public final static Double ShippingFeePay = 14.5;  //RMB
	public final static String AdobeCommand = "C:/Program Files (x86)/Adobe/Reader 11.0/Reader/AcroRd32.exe /s /o /N /T ";
	public final static String pictureDownloadTmpPath="C:/Users/Fang/Desktop/tmp_pic/";
	public final static String defaultPdfDownloadPath = "C:/Users/Fang/Desktop/Amazon_HFan/运单打印";
	
	//
	public static String picCopyFolder;

	public String getPicCopyFolder() {
		return picCopyFolder;
	}

	public void setPicCopyFolder(String picCopyFolder) {
		AppConstant.picCopyFolder = picCopyFolder;
	}
	
}
