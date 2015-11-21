package com.owen.wms.web.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="AMAZON_ORDER")
public class AmazonOrder implements java.io.Serializable{
	private static final long serialVersionUID = -2331957677783899791L;
	
	@OneToMany(mappedBy="order")
	@Cascade({CascadeType.PERSIST,CascadeType.DELETE_ORPHAN}) 
	private Set<AmazonOrderItem> orderItemList;
	
	@Id
	private String amazonOrderId;
	private Date purchaseDate;
	private Date lastUpdateDate;
	private String orderStatus;
	private String fulfillmentChannel;
	private String salesChannel;
	private String shipServiceLevel;
	private Integer numberOfItemsShipped;
	private Integer numberOfItemsUnshipped;
	private String marketplaceId;
	private String shipmentServiceLevelCategory;
	private String orderType;
	private Date earliestShipDate;
	private Date latestShipDate;
	private Boolean isBusinessOrder;
	private Boolean isPrime;
	private Boolean isPremiumOrder;
	
	public Set<AmazonOrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(Set<AmazonOrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getAmazonOrderId() {
		return amazonOrderId;
	}
	public void setAmazonOrderId(String amazonOrderId) {
		this.amazonOrderId = amazonOrderId;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getFulfillmentChannel() {
		return fulfillmentChannel;
	}
	public void setFulfillmentChannel(String fulfillmentChannel) {
		this.fulfillmentChannel = fulfillmentChannel;
	}
	public String getSalesChannel() {
		return salesChannel;
	}
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	public String getShipServiceLevel() {
		return shipServiceLevel;
	}
	public void setShipServiceLevel(String shipServiceLevel) {
		this.shipServiceLevel = shipServiceLevel;
	}
	public Integer getNumberOfItemsShipped() {
		return numberOfItemsShipped;
	}
	public void setNumberOfItemsShipped(Integer numberOfItemsShipped) {
		this.numberOfItemsShipped = numberOfItemsShipped;
	}
	public Integer getNumberOfItemsUnshipped() {
		return numberOfItemsUnshipped;
	}
	public void setNumberOfItemsUnshipped(Integer numberOfItemsUnshipped) {
		this.numberOfItemsUnshipped = numberOfItemsUnshipped;
	}
	public String getMarketplaceId() {
		return marketplaceId;
	}
	public void setMarketplaceId(String marketplaceId) {
		this.marketplaceId = marketplaceId;
	}
	public String getShipmentServiceLevelCategory() {
		return shipmentServiceLevelCategory;
	}
	public void setShipmentServiceLevelCategory(String shipmentServiceLevelCategory) {
		this.shipmentServiceLevelCategory = shipmentServiceLevelCategory;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Date getEarliestShipDate() {
		return earliestShipDate;
	}
	public void setEarliestShipDate(Date earliestShipDate) {
		this.earliestShipDate = earliestShipDate;
	}
	public Date getLatestShipDate() {
		return latestShipDate;
	}
	public void setLatestShipDate(Date latestShipDate) {
		this.latestShipDate = latestShipDate;
	}
	public Boolean getIsBusinessOrder() {
		return isBusinessOrder;
	}
	public void setIsBusinessOrder(Boolean isBusinessOrder) {
		this.isBusinessOrder = isBusinessOrder;
	}
	public Boolean getIsPrime() {
		return isPrime;
	}
	public void setIsPrime(Boolean isPrime) {
		this.isPrime = isPrime;
	}
	public Boolean getIsPremiumOrder() {
		return isPremiumOrder;
	}
	public void setIsPremiumOrder(Boolean isPremiumOrder) {
		this.isPremiumOrder = isPremiumOrder;
	}
	
	
}
