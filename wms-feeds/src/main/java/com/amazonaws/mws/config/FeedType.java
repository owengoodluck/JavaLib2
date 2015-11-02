package com.amazonaws.mws.config;

public enum FeedType {
	//xml
	XmlProduct("_POST_PRODUCT_DATA_"),
	XmlInventory("_POST_INVENTORY_AVAILABILITY_DATA_"),
	XmlOverrides("_POST_PRODUCT_OVERRIDES_DATA_"),
	XmlPricing("_POST_PRODUCT_PRICING_DATA_"),
	XmlProductImages("_POST_PRODUCT_IMAGE_DATA_"),
	XmlRelationships("_POST_PRODUCT_RELATIONSHIP_DATA_"),
	//flat file
	FlatFileProduct("_POST_INVENTORY_AVAILABILITY_DATA_"),
	FlatFileInventory("_POST_INVENTORY_AVAILABILITY_DATA_"),
	FlatFileOverrides("_POST_PRODUCT_OVERRIDES_DATA_"),
	FlatFilePricing("_POST_PRODUCT_PRICING_DATA_"),
	FlatFileProductImages("_POST_PRODUCT_IMAGE_DATA_"),
	FlatFileRelationships("_POST_PRODUCT_RELATIONSHIP_DATA_");
	
	private String value;
	private FeedType(String value){
		this.value = value	;
	}
	
	public String toString(){
		return this.value;
	}
	
}
