package com.owen.wms.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROD_JEWELRY")
public class ProductJewelryEntity implements java.io.Serializable{
	private static final long serialVersionUID = 7590672770281845493L;
	
	@Id
	private String itemSku;
	private String updateDelete;//Delete , PartialUpdate, Update ?
	private String parentChild; // child, parent in Relationship
	private String parentSku; //in Relationship
	private String relationshipType; //Variation, Accessoryin Relationship
	private String variationTheme; //?
	
	private String itemName;//title
	private String manufacturer="Generic";
	private String model; //?
	private String feedProductType;//FashionEarring,FashionNecklaceBraceletAnklet,FashionOther,FashionRing -- productType
	private String itemType;// ?
	private String departmentName;//mens,  womens ?
	private String brandName="Generic"; 
	
	private String externalProductId;//upc code
	private String externalProductIdType="UPC";
	private String productDescription="<b>Why Stainless Steel Jewelry is good choice for you ?</b><p>"+
			"&nbsp&nbsp&nbsp&nbsp<b>Amazing and Versatile:</b> Stainless steel jewelry is very versatile. Regardless you want to make yourself look more attractive, or look more refined ,or want to own something pretty or any other circumstances,there is always a piece of stainless steel jewelry available that can help you fulfill those needs.<br/>"+
			"&nbsp&nbsp&nbsp&nbsp<b>Durable and Fashion:</b> Stainless steel is not only a naturally hardy alloy , but it is also an alloy that can handle a lot of wear and tear. Stainless steel jewelry will last quite a bit longer than other material. This is a bigger return on investment.<br/>"+
			"&nbsp&nbsp&nbsp&nbsp<b>Good Material:</b> It is stainless ,it's not easy to be tarnished and oxidized. It's a top fashion accessory to complement your wardrobe with the sleek, contemporary styling offered."+
			"<p><b>CandyVillage Stainless Steel Fanshi Elegant Vintage Jewelry</b>";

	private double standardPrice; // in Price
	private double listPrice; //in Price
	private int quantity;//in Inventory
	private String currency="USD";//in Price
	
	private String displayDimensionsUnitOfMeasure="MM";
	
	private String bulletPoint1;
	private String bulletPoint2;
	private String bulletPoint3;
	private String bulletPoint4;
	private String bulletPoint5;

	private String genericKeywords1;
	private String genericKeywords2;
	private String genericKeywords3;
	private String genericKeywords4;
	private String genericKeywords5;
	
	private String mainImageUrl;  //in ProductImage
	private String otherImageUrl1;//in ProductImage
	private String otherImageUrl2;//in ProductImage
	private String otherImageUrl3;//in ProductImage
	private String otherImageUrl4;//in ProductImage
	private String otherImageUrl5;//in ProductImage
	private String otherImageUrl6;//in ProductImage
	private String otherImageUrl7;//in ProductImage
	private String otherImageUrl8;//in ProductImage
	
	private String countryOfOrigin = "China";
	private String metalType="stainless-steel"; //in variationData
	private String metalStamp="NA";//NA
	private String gemType1="NA"; //NA in ?
	private String gemType2="NA"; //NA in ?
	private String gemType3="NA"; //NA in ?
	
	private String colorName;

	public String getItemSku() {
		return itemSku;
	}

	public void setItemSku(String itemSku) {
		this.itemSku = itemSku;
	}

	public String getUpdateDelete() {
		return updateDelete;
	}

	public void setUpdateDelete(String updateDelete) {
		this.updateDelete = updateDelete;
	}

	public String getParentChild() {
		return parentChild;
	}

	public void setParentChild(String parentChild) {
		this.parentChild = parentChild;
	}

	public String getParentSku() {
		return parentSku;
	}

	public void setParentSku(String parentSku) {
		this.parentSku = parentSku;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getVariationTheme() {
		return variationTheme;
	}

	public void setVariationTheme(String variationTheme) {
		this.variationTheme = variationTheme;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFeedProductType() {
		return feedProductType;
	}

	public void setFeedProductType(String feedProductType) {
		this.feedProductType = feedProductType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getExternalProductId() {
		return externalProductId;
	}

	public void setExternalProductId(String externalProductId) {
		this.externalProductId = externalProductId;
	}

	public String getExternalProductIdType() {
		return externalProductIdType;
	}

	public void setExternalProductIdType(String externalProductIdType) {
		this.externalProductIdType = externalProductIdType;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDisplayDimensionsUnitOfMeasure() {
		return displayDimensionsUnitOfMeasure;
	}

	public void setDisplayDimensionsUnitOfMeasure(String displayDimensionsUnitOfMeasure) {
		this.displayDimensionsUnitOfMeasure = displayDimensionsUnitOfMeasure;
	}

	public String getBulletPoint1() {
		return bulletPoint1;
	}

	public void setBulletPoint1(String bulletPoint1) {
		this.bulletPoint1 = bulletPoint1;
	}

	public String getBulletPoint2() {
		return bulletPoint2;
	}

	public void setBulletPoint2(String bulletPoint2) {
		this.bulletPoint2 = bulletPoint2;
	}

	public String getBulletPoint3() {
		return bulletPoint3;
	}

	public void setBulletPoint3(String bulletPoint3) {
		this.bulletPoint3 = bulletPoint3;
	}

	public String getBulletPoint4() {
		return bulletPoint4;
	}

	public void setBulletPoint4(String bulletPoint4) {
		this.bulletPoint4 = bulletPoint4;
	}

	public String getBulletPoint5() {
		return bulletPoint5;
	}

	public void setBulletPoint5(String bulletPoint5) {
		this.bulletPoint5 = bulletPoint5;
	}

	public String getGenericKeywords1() {
		return genericKeywords1;
	}

	public void setGenericKeywords1(String genericKeywords1) {
		this.genericKeywords1 = genericKeywords1;
	}

	public String getGenericKeywords2() {
		return genericKeywords2;
	}

	public void setGenericKeywords2(String genericKeywords2) {
		this.genericKeywords2 = genericKeywords2;
	}

	public String getGenericKeywords3() {
		return genericKeywords3;
	}

	public void setGenericKeywords3(String genericKeywords3) {
		this.genericKeywords3 = genericKeywords3;
	}

	public String getGenericKeywords4() {
		return genericKeywords4;
	}

	public void setGenericKeywords4(String genericKeywords4) {
		this.genericKeywords4 = genericKeywords4;
	}

	public String getGenericKeywords5() {
		return genericKeywords5;
	}

	public void setGenericKeywords5(String genericKeywords5) {
		this.genericKeywords5 = genericKeywords5;
	}

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public String getOtherImageUrl1() {
		return otherImageUrl1;
	}

	public void setOtherImageUrl1(String otherImageUrl1) {
		this.otherImageUrl1 = otherImageUrl1;
	}

	public String getOtherImageUrl2() {
		return otherImageUrl2;
	}

	public void setOtherImageUrl2(String otherImageUrl2) {
		this.otherImageUrl2 = otherImageUrl2;
	}

	public String getOtherImageUrl3() {
		return otherImageUrl3;
	}

	public void setOtherImageUrl3(String otherImageUrl3) {
		this.otherImageUrl3 = otherImageUrl3;
	}

	public String getOtherImageUrl4() {
		return otherImageUrl4;
	}

	public void setOtherImageUrl4(String otherImageUrl4) {
		this.otherImageUrl4 = otherImageUrl4;
	}

	public String getOtherImageUrl5() {
		return otherImageUrl5;
	}

	public void setOtherImageUrl5(String otherImageUrl5) {
		this.otherImageUrl5 = otherImageUrl5;
	}

	public String getOtherImageUrl6() {
		return otherImageUrl6;
	}

	public void setOtherImageUrl6(String otherImageUrl6) {
		this.otherImageUrl6 = otherImageUrl6;
	}

	public String getOtherImageUrl7() {
		return otherImageUrl7;
	}

	public void setOtherImageUrl7(String otherImageUrl7) {
		this.otherImageUrl7 = otherImageUrl7;
	}

	public String getOtherImageUrl8() {
		return otherImageUrl8;
	}

	public void setOtherImageUrl8(String otherImageUrl8) {
		this.otherImageUrl8 = otherImageUrl8;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getMetalType() {
		return metalType;
	}

	public void setMetalType(String metalType) {
		this.metalType = metalType;
	}

	public String getMetalStamp() {
		return metalStamp;
	}

	public void setMetalStamp(String metalStamp) {
		this.metalStamp = metalStamp;
	}

	public String getGemType1() {
		return gemType1;
	}

	public void setGemType1(String gemType1) {
		this.gemType1 = gemType1;
	}

	public String getGemType2() {
		return gemType2;
	}

	public void setGemType2(String gemType2) {
		this.gemType2 = gemType2;
	}

	public String getGemType3() {
		return gemType3;
	}

	public void setGemType3(String gemType3) {
		this.gemType3 = gemType3;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	
}
