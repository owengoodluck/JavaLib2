package com.owen.wms.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AMAZON_ORDER_ITEM")
public class AmazonOrderItem implements java.io.Serializable{
	private static final long serialVersionUID = 1259177860243630955L;
	
	@Id
	private String orderItemId;
	
	@ManyToOne
	@JoinColumn(name="orderID")
	private AmazonOrder order;//foreign key
	
	private String ASIN;
	private String sellerSKU;
	private String title;
	private Integer quantityOrdered;
	private Integer quantityShipped;
	
	private String itemPriceCurrencyCode;
	private Double itemPriceAmount;
	
	private String shippingPriceCurrencyCode;
	private Double shippingPriceAmount;
	
	private String giftWrapPriceCurrencyCode;
	private Double giftWrapPriceAmount;
	
	private String itemTaxCurrencyCode;
	private Double itemTaxAmount;
	
	private String shippingTaxCurrencyCode;
	private Double shippingTaxAmount;
	
	private String giftWrapTaxCurrencyCode;
	private Double giftWrapTaxAmount;
	
	private String shippingDiscountCurrencyCode;
	private Double shippingDiscountAmount;
	
	private String promotionDiscountCurrencyCode;
	private Double promotionDiscountAmount;
	
	private String conditionId;
	private String conditionSubtypeId;
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	public AmazonOrder getOrder() {
		return order;
	}
	public void setOrder(AmazonOrder order) {
		this.order = order;
	}
	public String getASIN() {
		return ASIN;
	}
	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}
	public String getSellerSKU() {
		return sellerSKU;
	}
	public void setSellerSKU(String sellerSKU) {
		this.sellerSKU = sellerSKU;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}
	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	public Integer getQuantityShipped() {
		return quantityShipped;
	}
	public void setQuantityShipped(Integer quantityShipped) {
		this.quantityShipped = quantityShipped;
	}
	public String getItemPriceCurrencyCode() {
		return itemPriceCurrencyCode;
	}
	public void setItemPriceCurrencyCode(String itemPriceCurrencyCode) {
		this.itemPriceCurrencyCode = itemPriceCurrencyCode;
	}
	public Double getItemPriceAmount() {
		return itemPriceAmount;
	}
	public void setItemPriceAmount(Double itemPriceAmount) {
		this.itemPriceAmount = itemPriceAmount;
	}
	public String getShippingPriceCurrencyCode() {
		return shippingPriceCurrencyCode;
	}
	public void setShippingPriceCurrencyCode(String shippingPriceCurrencyCode) {
		this.shippingPriceCurrencyCode = shippingPriceCurrencyCode;
	}
	public Double getShippingPriceAmount() {
		return shippingPriceAmount;
	}
	public void setShippingPriceAmount(Double shippingPriceAmount) {
		this.shippingPriceAmount = shippingPriceAmount;
	}
	public String getGiftWrapPriceCurrencyCode() {
		return giftWrapPriceCurrencyCode;
	}
	public void setGiftWrapPriceCurrencyCode(String giftWrapPriceCurrencyCode) {
		this.giftWrapPriceCurrencyCode = giftWrapPriceCurrencyCode;
	}
	public Double getGiftWrapPriceAmount() {
		return giftWrapPriceAmount;
	}
	public void setGiftWrapPriceAmount(Double giftWrapPriceAmount) {
		this.giftWrapPriceAmount = giftWrapPriceAmount;
	}
	public String getItemTaxCurrencyCode() {
		return itemTaxCurrencyCode;
	}
	public void setItemTaxCurrencyCode(String itemTaxCurrencyCode) {
		this.itemTaxCurrencyCode = itemTaxCurrencyCode;
	}
	public Double getItemTaxAmount() {
		return itemTaxAmount;
	}
	public void setItemTaxAmount(Double itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
	}
	public String getShippingTaxCurrencyCode() {
		return shippingTaxCurrencyCode;
	}
	public void setShippingTaxCurrencyCode(String shippingTaxCurrencyCode) {
		this.shippingTaxCurrencyCode = shippingTaxCurrencyCode;
	}
	public Double getShippingTaxAmount() {
		return shippingTaxAmount;
	}
	public void setShippingTaxAmount(Double shippingTaxAmount) {
		this.shippingTaxAmount = shippingTaxAmount;
	}
	public String getGiftWrapTaxCurrencyCode() {
		return giftWrapTaxCurrencyCode;
	}
	public void setGiftWrapTaxCurrencyCode(String giftWrapTaxCurrencyCode) {
		this.giftWrapTaxCurrencyCode = giftWrapTaxCurrencyCode;
	}
	public Double getGiftWrapTaxAmount() {
		return giftWrapTaxAmount;
	}
	public void setGiftWrapTaxAmount(Double giftWrapTaxAmount) {
		this.giftWrapTaxAmount = giftWrapTaxAmount;
	}
	public String getShippingDiscountCurrencyCode() {
		return shippingDiscountCurrencyCode;
	}
	public void setShippingDiscountCurrencyCode(String shippingDiscountCurrencyCode) {
		this.shippingDiscountCurrencyCode = shippingDiscountCurrencyCode;
	}
	public Double getShippingDiscountAmount() {
		return shippingDiscountAmount;
	}
	public void setShippingDiscountAmount(Double shippingDiscountAmount) {
		this.shippingDiscountAmount = shippingDiscountAmount;
	}
	public String getPromotionDiscountCurrencyCode() {
		return promotionDiscountCurrencyCode;
	}
	public void setPromotionDiscountCurrencyCode(String promotionDiscountCurrencyCode) {
		this.promotionDiscountCurrencyCode = promotionDiscountCurrencyCode;
	}
	public Double getPromotionDiscountAmount() {
		return promotionDiscountAmount;
	}
	public void setPromotionDiscountAmount(Double promotionDiscountAmount) {
		this.promotionDiscountAmount = promotionDiscountAmount;
	}
	public String getConditionId() {
		return conditionId;
	}
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	public String getConditionSubtypeId() {
		return conditionSubtypeId;
	}
	public void setConditionSubtypeId(String conditionSubtypeId) {
		this.conditionSubtypeId = conditionSubtypeId;
	}
	
	
	
}
