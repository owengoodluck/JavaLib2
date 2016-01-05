package com.owen.wms.web.form;

public class YanwenExpress {
	private String amazonOrderID;
	private String sequenceNo;
	private String methodToGetOrder;//localDB 从本地同步数据库读取, remoteAmz 从亚马逊网站读取
	private String channel;
	private String sendDate;
	private String nameChinese;
	private String nameEnglish;
	private int quantity;
	private int weight;
	private double declaredValue;
	private String declaredCurrency;
	private String downloadPath;
	private String country;
	
	public String getMethodToGetOrder() {
		return methodToGetOrder;
	}
	public void setMethodToGetOrder(String methodToGetOrder) {
		this.methodToGetOrder = methodToGetOrder;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	public String getAmazonOrderID() {
		return amazonOrderID;
	}
	public void setAmazonOrderID(String amazonOrderID) {
		this.amazonOrderID = amazonOrderID;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getNameChinese() {
		return nameChinese;
	}
	public void setNameChinese(String nameChinese) {
		this.nameChinese = nameChinese;
	}
	public String getNameEnglish() {
		return nameEnglish;
	}
	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public double getDeclaredValue() {
		return declaredValue;
	}
	public void setDeclaredValue(double declaredValue) {
		this.declaredValue = declaredValue;
	}
	public String getDeclaredCurrency() {
		return declaredCurrency;
	}
	public void setDeclaredCurrency(String declaredCurrency) {
		this.declaredCurrency = declaredCurrency;
	}
	public String getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	
	
}
