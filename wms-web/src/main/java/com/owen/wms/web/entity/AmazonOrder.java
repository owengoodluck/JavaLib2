package com.owen.wms.web.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="AMAZON_ORDER")
public class AmazonOrder implements java.io.Serializable{
	private static final long serialVersionUID = -2331957677783899791L;
	
	private Boolean isPrinted;
	
	@Id
	private String amazonOrderId;
	
	@OneToMany(mappedBy="order",fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN}) 
	private Set<AmazonOrderItem> orderItemList;
	
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
	
	private String shippingAddressName;
	private String shippingAddressAddressLine1;
	private String shippingAddressAddressLine2;
	private String shippingAddressAddressLine3;
	private String shippingAddressCity;
	private String shippingAddressCounty;
	private String shippingAddressDistrict;
	private String shippingAddressStateOrRegion;
	private String shippingAddressPostalCode;
	private String shippingAddressCountryCode;
	private String shippingAddressPhone;
	
	public Boolean getIsPrinted() {
		return isPrinted;
	}
	public void setIsPrinted(Boolean isPrinted) {
		this.isPrinted = isPrinted;
	}
	public String getShippingAddressName() {
		return shippingAddressName;
	}
	public void setShippingAddressName(String shippingAddressName) {
		this.shippingAddressName = shippingAddressName;
	}
	public String getShippingAddressAddressLine1() {
		return shippingAddressAddressLine1;
	}
	public void setShippingAddressAddressLine1(String shippingAddressAddressLine1) {
		this.shippingAddressAddressLine1 = shippingAddressAddressLine1;
	}
	public String getShippingAddressAddressLine2() {
		return shippingAddressAddressLine2;
	}
	public void setShippingAddressAddressLine2(String shippingAddressAddressLine2) {
		this.shippingAddressAddressLine2 = shippingAddressAddressLine2;
	}
	public String getShippingAddressAddressLine3() {
		return shippingAddressAddressLine3;
	}
	public void setShippingAddressAddressLine3(String shippingAddressAddressLine3) {
		this.shippingAddressAddressLine3 = shippingAddressAddressLine3;
	}
	public String getShippingAddressCity() {
		return shippingAddressCity;
	}
	public void setShippingAddressCity(String shippingAddressCity) {
		this.shippingAddressCity = shippingAddressCity;
	}
	public String getShippingAddressCounty() {
		return shippingAddressCounty;
	}
	public void setShippingAddressCounty(String shippingAddressCounty) {
		this.shippingAddressCounty = shippingAddressCounty;
	}
	public String getShippingAddressDistrict() {
		return shippingAddressDistrict;
	}
	public void setShippingAddressDistrict(String shippingAddressDistrict) {
		this.shippingAddressDistrict = shippingAddressDistrict;
	}
	public String getShippingAddressStateOrRegion() {
		return shippingAddressStateOrRegion;
	}
	public void setShippingAddressStateOrRegion(String shippingAddressStateOrRegion) {
		this.shippingAddressStateOrRegion = shippingAddressStateOrRegion;
	}
	public String getShippingAddressPostalCode() {
		return shippingAddressPostalCode;
	}
	public void setShippingAddressPostalCode(String shippingAddressPostalCode) {
		this.shippingAddressPostalCode = shippingAddressPostalCode;
	}
	public String getShippingAddressCountryCode() {
		return shippingAddressCountryCode;
	}
	public void setShippingAddressCountryCode(String shippingAddressCountryCode) {
		this.shippingAddressCountryCode = shippingAddressCountryCode;
	}
	public String getShippingAddressPhone() {
		return shippingAddressPhone;
	}
	public void setShippingAddressPhone(String shippingAddressPhone) {
		this.shippingAddressPhone = shippingAddressPhone;
	}
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
	@Override
	public String toString() {
		return "AmazonOrder [amazonOrderId=" + amazonOrderId + ", orderItemList=" + orderItemList + ", purchaseDate="
				+ purchaseDate + ", lastUpdateDate=" + lastUpdateDate + ", orderStatus=" + orderStatus
				+ ", fulfillmentChannel=" + fulfillmentChannel + ", salesChannel=" + salesChannel
				+ ", shipServiceLevel=" + shipServiceLevel + ", numberOfItemsShipped=" + numberOfItemsShipped
				+ ", numberOfItemsUnshipped=" + numberOfItemsUnshipped + ", marketplaceId=" + marketplaceId
				+ ", shipmentServiceLevelCategory=" + shipmentServiceLevelCategory + ", orderType=" + orderType
				+ ", earliestShipDate=" + earliestShipDate + ", latestShipDate=" + latestShipDate + ", isBusinessOrder="
				+ isBusinessOrder + ", isPrime=" + isPrime + ", isPremiumOrder=" + isPremiumOrder + "]";
	}
	
	
}
