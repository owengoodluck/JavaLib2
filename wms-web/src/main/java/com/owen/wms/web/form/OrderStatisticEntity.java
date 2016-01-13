package com.owen.wms.web.form;

import java.util.Date;

public class OrderStatisticEntity {
	private String itemSku;
	private int sellCount;
	private Date latestDate;
	private int stockQuantity;
	private String mainImageUrl;
	
	public String getLocalImagePath(){
		String url = this.mainImageUrl;
		if(url==null || url.trim().length()<1){
			return null;
		}
		String picFileName=url.substring(url.lastIndexOf("/"));
    	if(picFileName!=null){
    		if(picFileName.toLowerCase().endsWith(".jpg")){
    			//ok
    		}else if(picFileName.toLowerCase().indexOf("?")!=-1){
    			picFileName=picFileName.substring(0, picFileName.toLowerCase().indexOf("?"));
    		}
    	}
    	return picFileName;
	}
	
	public String getItemSku() {
		return itemSku;
	}
	public void setItemSku(String itemSku) {
		this.itemSku = itemSku;
	}
	public int getSellCount() {
		return sellCount;
	}
	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getMainImageUrl() {
		return mainImageUrl;
	}
	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}
	public Date getLatestDate() {
		return latestDate;
	}
	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}
	
	
}
