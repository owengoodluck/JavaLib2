package com.owen.wms.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROD_JEWELRY")
public class ProductJewelryEntity implements java.io.Serializable{
	private static final long serialVersionUID = 7590672770281845493L;
	
	@Id
	private String item_sku;
	private String update_delete;//Delete , PartialUpdate, Update
	private String parent_child; // child, parent
	private String parent_sku;
	private String relationship_type; //Variation, Accessory
	private String variation_theme;
	
	private String item_name;//title
	private String manufacturer="CVO";
	private String model;
	private String feed_product_type;//FashionEarring,FashionNecklaceBraceletAnklet,FashionOther,FashionRing
	private String item_type;
	private String department_name;//mens,  womens
	private String brand_name="CVO";
	
	private String external_product_id;
	private String external_product_id_type="UPC";
	private String product_description;

	private double standard_price;
	private double list_price;
	private int quantity;
	private String currency="USD";
	
	private String display_dimensions_unit_of_measure="MM";
	
	private String bullet_point1;
	private String bullet_point2;
	private String bullet_point3;
	private String bullet_point4;
	private String bullet_point5;

	private String generic_keywords1;
	private String generic_keywords2;
	private String generic_keywords3;
	private String generic_keywords4;
	private String generic_keywords5;
	
	private String main_image_url;
	private String other_image_url1;
	private String other_image_url2;
	private String other_image_url3;
	private String other_image_url4;
	private String other_image_url5;
	private String other_image_url6;
	private String other_image_url7;
	private String other_image_url8;
	
	private String country_of_origin = "China";
	private String metal_type;
	private String metal_stamp;//NA
	private String gem_type1; //NA
	private String gem_type2; //NA
	private String gem_type3; //NA
	
	private String color_name;

	public String getItem_sku() {
		return item_sku;
	}

	public void setItem_sku(String item_sku) {
		this.item_sku = item_sku;
	}

	public String getUpdate_delete() {
		return update_delete;
	}

	public void setUpdate_delete(String update_delete) {
		this.update_delete = update_delete;
	}

	public String getParent_child() {
		return parent_child;
	}

	public void setParent_child(String parent_child) {
		this.parent_child = parent_child;
	}

	public String getParent_sku() {
		return parent_sku;
	}

	public void setParent_sku(String parent_sku) {
		this.parent_sku = parent_sku;
	}

	public String getRelationship_type() {
		return relationship_type;
	}

	public void setRelationship_type(String relationship_type) {
		this.relationship_type = relationship_type;
	}

	public String getVariation_theme() {
		return variation_theme;
	}

	public void setVariation_theme(String variation_theme) {
		this.variation_theme = variation_theme;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
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

	public String getFeed_product_type() {
		return feed_product_type;
	}

	public void setFeed_product_type(String feed_product_type) {
		this.feed_product_type = feed_product_type;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getExternal_product_id() {
		return external_product_id;
	}

	public void setExternal_product_id(String external_product_id) {
		this.external_product_id = external_product_id;
	}

	public String getExternal_product_id_type() {
		return external_product_id_type;
	}

	public void setExternal_product_id_type(String external_product_id_type) {
		this.external_product_id_type = external_product_id_type;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public double getStandard_price() {
		return standard_price;
	}

	public void setStandard_price(double standard_price) {
		this.standard_price = standard_price;
	}

	public double getList_price() {
		return list_price;
	}

	public void setList_price(double list_price) {
		this.list_price = list_price;
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

	public String getDisplay_dimensions_unit_of_measure() {
		return display_dimensions_unit_of_measure;
	}

	public void setDisplay_dimensions_unit_of_measure(String display_dimensions_unit_of_measure) {
		this.display_dimensions_unit_of_measure = display_dimensions_unit_of_measure;
	}

	public String getBullet_point1() {
		return bullet_point1;
	}

	public void setBullet_point1(String bullet_point1) {
		this.bullet_point1 = bullet_point1;
	}

	public String getBullet_point2() {
		return bullet_point2;
	}

	public void setBullet_point2(String bullet_point2) {
		this.bullet_point2 = bullet_point2;
	}

	public String getBullet_point3() {
		return bullet_point3;
	}

	public void setBullet_point3(String bullet_point3) {
		this.bullet_point3 = bullet_point3;
	}

	public String getBullet_point4() {
		return bullet_point4;
	}

	public void setBullet_point4(String bullet_point4) {
		this.bullet_point4 = bullet_point4;
	}

	public String getBullet_point5() {
		return bullet_point5;
	}

	public void setBullet_point5(String bullet_point5) {
		this.bullet_point5 = bullet_point5;
	}

	public String getGeneric_keywords1() {
		return generic_keywords1;
	}

	public void setGeneric_keywords1(String generic_keywords1) {
		this.generic_keywords1 = generic_keywords1;
	}

	public String getGeneric_keywords2() {
		return generic_keywords2;
	}

	public void setGeneric_keywords2(String generic_keywords2) {
		this.generic_keywords2 = generic_keywords2;
	}

	public String getGeneric_keywords3() {
		return generic_keywords3;
	}

	public void setGeneric_keywords3(String generic_keywords3) {
		this.generic_keywords3 = generic_keywords3;
	}

	public String getGeneric_keywords4() {
		return generic_keywords4;
	}

	public void setGeneric_keywords4(String generic_keywords4) {
		this.generic_keywords4 = generic_keywords4;
	}

	public String getGeneric_keywords5() {
		return generic_keywords5;
	}

	public void setGeneric_keywords5(String generic_keywords5) {
		this.generic_keywords5 = generic_keywords5;
	}

	public String getMain_image_url() {
		return main_image_url;
	}

	public void setMain_image_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}

	public String getOther_image_url1() {
		return other_image_url1;
	}

	public void setOther_image_url1(String other_image_url1) {
		this.other_image_url1 = other_image_url1;
	}

	public String getOther_image_url2() {
		return other_image_url2;
	}

	public void setOther_image_url2(String other_image_url2) {
		this.other_image_url2 = other_image_url2;
	}

	public String getOther_image_url3() {
		return other_image_url3;
	}

	public void setOther_image_url3(String other_image_url3) {
		this.other_image_url3 = other_image_url3;
	}

	public String getOther_image_url4() {
		return other_image_url4;
	}

	public void setOther_image_url4(String other_image_url4) {
		this.other_image_url4 = other_image_url4;
	}

	public String getOther_image_url5() {
		return other_image_url5;
	}

	public void setOther_image_url5(String other_image_url5) {
		this.other_image_url5 = other_image_url5;
	}

	public String getOther_image_url6() {
		return other_image_url6;
	}

	public void setOther_image_url6(String other_image_url6) {
		this.other_image_url6 = other_image_url6;
	}

	public String getOther_image_url7() {
		return other_image_url7;
	}

	public void setOther_image_url7(String other_image_url7) {
		this.other_image_url7 = other_image_url7;
	}

	public String getOther_image_url8() {
		return other_image_url8;
	}

	public void setOther_image_url8(String other_image_url8) {
		this.other_image_url8 = other_image_url8;
	}

	public String getCountry_of_origin() {
		return country_of_origin;
	}

	public void setCountry_of_origin(String country_of_origin) {
		this.country_of_origin = country_of_origin;
	}

	public String getMetal_type() {
		return metal_type;
	}

	public void setMetal_type(String metal_type) {
		this.metal_type = metal_type;
	}

	public String getMetal_stamp() {
		return metal_stamp;
	}

	public void setMetal_stamp(String metal_stamp) {
		this.metal_stamp = metal_stamp;
	}

	public String getGem_type1() {
		return gem_type1;
	}

	public void setGem_type1(String gem_type1) {
		this.gem_type1 = gem_type1;
	}

	public String getGem_type2() {
		return gem_type2;
	}

	public void setGem_type2(String gem_type2) {
		this.gem_type2 = gem_type2;
	}

	public String getGem_type3() {
		return gem_type3;
	}

	public void setGem_type3(String gem_type3) {
		this.gem_type3 = gem_type3;
	}

	public String getColor_name() {
		return color_name;
	}

	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
}
