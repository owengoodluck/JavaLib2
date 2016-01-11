package com.owen.wms.web.form;

public class ExpressQueryForm {

	private String expressID;
	private String orderID;
	private String channel;
	private String receiver;
	private String sendDateFrom;
	private String sendDateTo;
	private int currentPage;
	private int pageSize;
	
	public String getExpressID() {
		return expressID;
	}
	public void setExpressID(String expressID) {
		this.expressID = expressID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSendDateFrom() {
		return sendDateFrom;
	}
	public void setSendDateFrom(String sendDateFrom) {
		this.sendDateFrom = sendDateFrom;
	}
	public String getSendDateTo() {
		return sendDateTo;
	}
	public void setSendDateTo(String sendDateTo) {
		this.sendDateTo = sendDateTo;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
