package com.owen.wms.web.form;

public class ProdQueryForm {
	
	private String itemSKU;
	private String itemType;
	private String itemName;
	private String parentChild;
	
	private int currentPage;
	private int pageSize;
	public String getItemSKU() {
		return itemSKU;
	}
	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getParentChild() {
		return parentChild;
	}
	public void setParentChild(String parentChild) {
		this.parentChild = parentChild;
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
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
