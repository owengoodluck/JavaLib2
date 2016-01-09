package com.owen.wms.web.form;

public class OrderQueryForm {
	private String queryOrderID;
	private String queryOrderDateFrom;
	private String queryOrderDateTo;
	private String queryOrderStatus;
	private int currentPage;
	private int pageSize;
	public String getQueryOrderID() {
		return queryOrderID;
	}
	public void setQueryOrderID(String queryOrderID) {
		this.queryOrderID = queryOrderID;
	}
	public String getQueryOrderDateFrom() {
		return queryOrderDateFrom;
	}
	public void setQueryOrderDateFrom(String queryOrderDateFrom) {
		this.queryOrderDateFrom = queryOrderDateFrom;
	}
	public String getQueryOrderDateTo() {
		return queryOrderDateTo;
	}
	public void setQueryOrderDateTo(String queryOrderDateTo) {
		this.queryOrderDateTo = queryOrderDateTo;
	}
	public String getQueryOrderStatus() {
		return queryOrderStatus;
	}
	public void setQueryOrderStatus(String queryOrderStatus) {
		this.queryOrderStatus = queryOrderStatus;
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
