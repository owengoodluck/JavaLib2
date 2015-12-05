package com.owen.wms.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="amz_jewelry")
public class JewelryEntity implements java.io.Serializable{
	private static final long serialVersionUID = -1519524380820857135L;
	
	@Id
	@Column(name="item_sku")
	private String itemSku;

	@Column(name="item_name")
	private String itemName;

	@Column(name="manufacturer")
	private String manufacturer;

	@Column(name="model")
	private String model;

	@Column(name="feed_product_type")
	private String feedProductType;

	@Column(name="item_type")
	private String itemType;

	@Column(name="brand_name")
	private String brandName;

	@Column(name="external_product_id")
	private String externalProductId;

	@Column(name="external_product_id_type")
	private String externalProductIdType="UPC";

	@Column(name="product_description",length=2000)
	private String productDescription;

	@Column(name="update_delete")
	private String updateDelete;

	@Column(name="standard_price")
	private Double standardPrice;

	@Column(name="quantity")
	private Integer quantity=80;

	@Column(name="currency")
	private String currency="USD";

	@Column(name="product_site_launch_date")
	private String productSiteLaunchDate;

	@Column(name="product_tax_code")
	private String productTaxCode;

	@Column(name="list_price")
	private Double listPrice=26.9;

	@Column(name="sale_price")
	private String salePrice;

	@Column(name="sale_from_date")
	private String saleFromDate;

	@Column(name="sale_end_date")
	private String saleEndDate;

	@Column(name="merchant_release_date")
	private String merchantReleaseDate;

	@Column(name="item_package_quantity")
	private String itemPackageQuantity;

	@Column(name="fulfillment_latency")
	private String fulfillmentLatency;

	@Column(name="restock_date")
	private String restockDate;

	@Column(name="max_aggregate_ship_quantity")
	private String maxAggregateShipQuantity;

	@Column(name="offering_can_be_gift_messaged")
	private String offeringCanBeGiftMessaged;

	@Column(name="offering_can_be_giftwrapped")
	private String offeringCanBeGiftwrapped;

	@Column(name="is_discontinued_by_manufacturer")
	private String isDiscontinuedByManufacturer;

	@Column(name="missing_keyset_reason")
	private String missingKeysetReason;

	@Column(name="delivery_schedule_group_id")
	private String deliveryScheduleGroupId;

	@Column(name="website_shipping_weight")
	private String websiteShippingWeight;

	@Column(name="website_shipping_weight_unit_of_measure")
	private String websiteShippingWeightUnitOfMeasure;

	@Column(name="display_dimensions_unit_of_measure")
	private String displayDimensionsUnitOfMeasure;

	@Column(name="item_display_diameter")
	private String itemDisplayDiameter;

	@Column(name="item_display_height")
	private String itemDisplayHeight;

	@Column(name="item_display_width")
	private String itemDisplayWidth;

	@Column(name="item_display_length")
	private String itemDisplayLength;

	@Column(name="item_length")
	private String itemLength;

	@Column(name="item_width")
	private String itemWidth;

	@Column(name="item_height")
	private String itemHeight;

	@Column(name="item_dimensions_unit_of_measure")
	private String itemDimensionsUnitOfMeasure;

	@Column(name="bullet_point1")
	private String bulletPoint1;

	@Column(name="bullet_point2")
	private String bulletPoint2;

	@Column(name="bullet_point3")
	private String bulletPoint3;

	@Column(name="bullet_point4")
	private String bulletPoint4;

	@Column(name="bullet_point5")
	private String bulletPoint5;

	@Column(name="target_audience_keywords1")
	private String targetAudienceKeywords1;

	@Column(name="target_audience_keywords2")
	private String targetAudienceKeywords2;

	@Column(name="target_audience_keywords3")
	private String targetAudienceKeywords3;

	@Column(name="catalog_number")
	private String catalogNumber;

	@Column(name="specific_uses_keywords1")
	private String specificUsesKeywords1;

	@Column(name="specific_uses_keywords2")
	private String specificUsesKeywords2;

	@Column(name="specific_uses_keywords3")
	private String specificUsesKeywords3;

	@Column(name="specific_uses_keywords4")
	private String specificUsesKeywords4;

	@Column(name="specific_uses_keywords5")
	private String specificUsesKeywords5;

	@Column(name="thesaurus_attribute_keywords1")
	private String thesaurusAttributeKeywords1;

	@Column(name="thesaurus_attribute_keywords2")
	private String thesaurusAttributeKeywords2;

	@Column(name="thesaurus_attribute_keywords3")
	private String thesaurusAttributeKeywords3;

	@Column(name="thesaurus_attribute_keywords4")
	private String thesaurusAttributeKeywords4;

	@Column(name="thesaurus_attribute_keywords5")
	private String thesaurusAttributeKeywords5;

	@Column(name="thesaurus_subject_keywords1")
	private String thesaurusSubjectKeywords1;

	@Column(name="thesaurus_subject_keywords2")
	private String thesaurusSubjectKeywords2;

	@Column(name="thesaurus_subject_keywords3")
	private String thesaurusSubjectKeywords3;

	@Column(name="thesaurus_subject_keywords4")
	private String thesaurusSubjectKeywords4;

	@Column(name="thesaurus_subject_keywords5")
	private String thesaurusSubjectKeywords5;

	@Column(name="generic_keywords1")
	private String genericKeywords1;

	@Column(name="generic_keywords2")
	private String genericKeywords2;

	@Column(name="generic_keywords3")
	private String genericKeywords3;

	@Column(name="generic_keywords4")
	private String genericKeywords4;

	@Column(name="generic_keywords5")
	private String genericKeywords5;

	@Column(name="platinum_keywords1")
	private String platinumKeywords1;

	@Column(name="platinum_keywords2")
	private String platinumKeywords2;

	@Column(name="platinum_keywords3")
	private String platinumKeywords3;

	@Column(name="platinum_keywords4")
	private String platinumKeywords4;

	@Column(name="platinum_keywords5")
	private String platinumKeywords5;

	@Column(name="main_image_url")
	private String mainImageUrl;

	@Column(name="swatch_image_url")
	private String swatchImageUrl;

	@Column(name="other_image_url1")
	private String otherImageUrl1;

	@Column(name="other_image_url2")
	private String otherImageUrl2;

	@Column(name="other_image_url3")
	private String otherImageUrl3;

	@Column(name="other_image_url4")
	private String otherImageUrl4;

	@Column(name="other_image_url5")
	private String otherImageUrl5;

	@Column(name="other_image_url6")
	private String otherImageUrl6;

	@Column(name="other_image_url7")
	private String otherImageUrl7;

	@Column(name="other_image_url8")
	private String otherImageUrl8;

	@Column(name="fulfillment_center_id")
	private String fulfillmentCenterId;

	@Column(name="package_length")
	private String packageLength;

	@Column(name="package_height")
	private String packageHeight;

	@Column(name="package_width")
	private String packageWidth;

	@Column(name="package_dimensions_unit_of_measure")
	private String packageDimensionsUnitOfMeasure;

	@Column(name="package_weight")
	private String packageWeight;

	@Column(name="package_weight_unit_of_measure")
	private String packageWeightUnitOfMeasure;

	@Column(name="parent_child")
	private String parentChild;

	@Column(name="parent_sku")
	private String parentSku;

	@Column(name="relationship_type")
	private String relationshipType;

	@Column(name="variation_theme")
	private String variationTheme;

	@Column(name="country_of_origin")
	private String countryOfOrigin="China";

	@Column(name="prop_65")
	private String prop65;

	@Column(name="cpsia_cautionary_statement1")
	private String cpsiaCautionaryStatement1;

	@Column(name="cpsia_cautionary_statement2")
	private String cpsiaCautionaryStatement2;

	@Column(name="cpsia_cautionary_statement3")
	private String cpsiaCautionaryStatement3;

	@Column(name="cpsia_cautionary_statement4")
	private String cpsiaCautionaryStatement4;

	@Column(name="cpsia_cautionary_description")
	private String cpsiaCautionaryDescription;

	@Column(name="department_name")
	private String departmentName;

	@Column(name="designer")
	private String designer;

	@Column(name="total_metal_weight")
	private String totalMetalWeight;

	@Column(name="total_metal_weight_unit_of_measure")
	private String totalMetalWeightUnitOfMeasure;

	@Column(name="total_diamond_weight")
	private String totalDiamondWeight;

	@Column(name="total_diamond_weight_unit_of_measure")
	private String totalDiamondWeightUnitOfMeasure;

	@Column(name="total_gem_weight")
	private String totalGemWeight;

	@Column(name="total_gem_weight_unit_of_measure")
	private String totalGemWeightUnitOfMeasure;

	@Column(name="material_type1")
	private String materialType1;

	@Column(name="material_type2")
	private String materialType2;

	@Column(name="material_type3")
	private String materialType3;

	@Column(name="material_type4")
	private String materialType4;

	@Column(name="metal_type")
	private String metalType="stainless-steel";

	@Column(name="metal_stamp")
	private String metalStamp="NA";

	@Column(name="setting_type")
	private String settingType;

	@Column(name="number_of_stones")
	private String numberOfStones;

	@Column(name="clasp_type")
	private String claspType;

	@Column(name="chain_type")
	private String chainType;

	@Column(name="ring_size")
	private String ringSize;

	@Column(name="is_resizable")
	private String isResizable;

	@Column(name="ring_sizing_lower_range")
	private String ringSizingLowerRange;

	@Column(name="ring_sizing_upper_range")
	private String ringSizingUpperRange;

	@Column(name="estate_period")
	private String estatePeriod;

	@Column(name="certificate_number1")
	private String certificateNumber1;

	@Column(name="certificate_number2")
	private String certificateNumber2;

	@Column(name="certificate_number3")
	private String certificateNumber3;

	@Column(name="certificate_number4")
	private String certificateNumber4;

	@Column(name="certificate_number5")
	private String certificateNumber5;

	@Column(name="certificate_number6")
	private String certificateNumber6;

	@Column(name="certificate_number7")
	private String certificateNumber7;

	@Column(name="certificate_number8")
	private String certificateNumber8;

	@Column(name="certificate_number9")
	private String certificateNumber9;

	@Column(name="gem_type1")
	private String gemType1="NA";

	@Column(name="gem_type2")
	private String gemType2="NA";

	@Column(name="gem_type3")
	private String gemType3="NA";

	@Column(name="stone_cut1")
	private String stoneCut1;

	@Column(name="stone_cut2")
	private String stoneCut2;

	@Column(name="stone_cut3")
	private String stoneCut3;

	@Column(name="stone_color1")
	private String stoneColor1;

	@Column(name="stone_color2")
	private String stoneColor2;

	@Column(name="stone_color3")
	private String stoneColor3;

	@Column(name="stone_clarity1")
	private String stoneClarity1;

	@Column(name="stone_clarity2")
	private String stoneClarity2;

	@Column(name="stone_clarity3")
	private String stoneClarity3;

	@Column(name="stone_shape1")
	private String stoneShape1;

	@Column(name="stone_shape2")
	private String stoneShape2;

	@Column(name="stone_shape3")
	private String stoneShape3;

	@Column(name="stone_creation_method1")
	private String stoneCreationMethod1;

	@Column(name="stone_creation_method2")
	private String stoneCreationMethod2;

	@Column(name="stone_creation_method3")
	private String stoneCreationMethod3;

	@Column(name="stone_treatment_method1")
	private String stoneTreatmentMethod1;

	@Column(name="stone_treatment_method2")
	private String stoneTreatmentMethod2;

	@Column(name="stone_treatment_method3")
	private String stoneTreatmentMethod3;

	@Column(name="stone_dimensions_unit_of_measure1")
	private String stoneDimensionsUnitOfMeasure1;

	@Column(name="stone_dimensions_unit_of_measure2")
	private String stoneDimensionsUnitOfMeasure2;

	@Column(name="stone_dimensions_unit_of_measure3")
	private String stoneDimensionsUnitOfMeasure3;

	@Column(name="stone_height1")
	private String stoneHeight1;

	@Column(name="stone_height2")
	private String stoneHeight2;

	@Column(name="stone_height3")
	private String stoneHeight3;

	@Column(name="stone_length1")
	private String stoneLength1;

	@Column(name="stone_length2")
	private String stoneLength2;

	@Column(name="stone_length3")
	private String stoneLength3;

	@Column(name="stone_width1")
	private String stoneWidth1;

	@Column(name="stone_width2")
	private String stoneWidth2;

	@Column(name="stone_width3")
	private String stoneWidth3;

	@Column(name="stone_weight1")
	private String stoneWeight1;

	@Column(name="stone_weight2")
	private String stoneWeight2;

	@Column(name="stone_weight3")
	private String stoneWeight3;

	@Column(name="certificate_type1")
	private String certificateType1;

	@Column(name="certificate_type2")
	private String certificateType2;

	@Column(name="certificate_type3")
	private String certificateType3;

	@Column(name="certificate_type4")
	private String certificateType4;

	@Column(name="certificate_type5")
	private String certificateType5;

	@Column(name="certificate_type6")
	private String certificateType6;

	@Column(name="certificate_type7")
	private String certificateType7;

	@Column(name="certificate_type8")
	private String certificateType8;

	@Column(name="certificate_type9")
	private String certificateType9;

	@Column(name="is_lab_created1")
	private String isLabCreated1;

	@Column(name="is_lab_created2")
	private String isLabCreated2;

	@Column(name="is_lab_created3")
	private String isLabCreated3;

	@Column(name="inscription1")
	private String inscription1;

	@Column(name="inscription2")
	private String inscription2;

	@Column(name="inscription3")
	private String inscription3;

	@Column(name="stone_depth_percentage1")
	private String stoneDepthPercentage1;

	@Column(name="stone_depth_percentage2")
	private String stoneDepthPercentage2;

	@Column(name="stone_depth_percentage3")
	private String stoneDepthPercentage3;

	@Column(name="stone_table_percentage1")
	private String stoneTablePercentage1;

	@Column(name="stone_table_percentage2")
	private String stoneTablePercentage2;

	@Column(name="stone_table_percentage3")
	private String stoneTablePercentage3;

	@Column(name="stone_symmetry1")
	private String stoneSymmetry1;

	@Column(name="stone_symmetry2")
	private String stoneSymmetry2;

	@Column(name="stone_symmetry3")
	private String stoneSymmetry3;

	@Column(name="stone_polish1")
	private String stonePolish1;

	@Column(name="stone_polish2")
	private String stonePolish2;

	@Column(name="stone_polish3")
	private String stonePolish3;

	@Column(name="stone_girdle1")
	private String stoneGirdle1;

	@Column(name="stone_girdle2")
	private String stoneGirdle2;

	@Column(name="stone_girdle3")
	private String stoneGirdle3;

	@Column(name="stone_culet1")
	private String stoneCulet1;

	@Column(name="stone_culet2")
	private String stoneCulet2;

	@Column(name="stone_culet3")
	private String stoneCulet3;

	@Column(name="stone_fluorescence1")
	private String stoneFluorescence1;

	@Column(name="stone_fluorescence2")
	private String stoneFluorescence2;

	@Column(name="stone_fluorescence3")
	private String stoneFluorescence3;

	@Column(name="pearl_type")
	private String pearlType;

	@Column(name="pearl_minimum_color")
	private String pearlMinimumColor;

	@Column(name="pearl_lustre")
	private String pearlLustre;

	@Column(name="pearl_shape")
	private String pearlShape;

	@Column(name="pearl_uniformity")
	private String pearlUniformity;

	@Column(name="pearl_surface_blemishes")
	private String pearlSurfaceBlemishes;

	@Column(name="nacre_thickness")
	private String nacreThickness;

	@Column(name="pearl_stringing_method")
	private String pearlStringingMethod;

	@Column(name="size_per_pearl")
	private String sizePerPearl;

	@Column(name="number_of_pearls")
	private String numberOfPearls;

	@Column(name="style_name")
	private String styleName;

	@Column(name="color_name")
	private String colorName;

	@Column(name="back_finding")
	private String backFinding;

	public String getItemSku() {
		return itemSku;
	}

	public void setItemSku(String itemSku) {
		this.itemSku = itemSku;
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

	public String getUpdateDelete() {
		return updateDelete;
	}

	public void setUpdateDelete(String updateDelete) {
		this.updateDelete = updateDelete;
	}

	public Double getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(Double standardPrice) {
		this.standardPrice = standardPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getProductSiteLaunchDate() {
		return productSiteLaunchDate;
	}

	public void setProductSiteLaunchDate(String productSiteLaunchDate) {
		this.productSiteLaunchDate = productSiteLaunchDate;
	}

	public String getProductTaxCode() {
		return productTaxCode;
	}

	public void setProductTaxCode(String productTaxCode) {
		this.productTaxCode = productTaxCode;
	}

	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getSaleFromDate() {
		return saleFromDate;
	}

	public void setSaleFromDate(String saleFromDate) {
		this.saleFromDate = saleFromDate;
	}

	public String getSaleEndDate() {
		return saleEndDate;
	}

	public void setSaleEndDate(String saleEndDate) {
		this.saleEndDate = saleEndDate;
	}

	public String getMerchantReleaseDate() {
		return merchantReleaseDate;
	}

	public void setMerchantReleaseDate(String merchantReleaseDate) {
		this.merchantReleaseDate = merchantReleaseDate;
	}

	public String getItemPackageQuantity() {
		return itemPackageQuantity;
	}

	public void setItemPackageQuantity(String itemPackageQuantity) {
		this.itemPackageQuantity = itemPackageQuantity;
	}

	public String getFulfillmentLatency() {
		return fulfillmentLatency;
	}

	public void setFulfillmentLatency(String fulfillmentLatency) {
		this.fulfillmentLatency = fulfillmentLatency;
	}

	public String getRestockDate() {
		return restockDate;
	}

	public void setRestockDate(String restockDate) {
		this.restockDate = restockDate;
	}

	public String getMaxAggregateShipQuantity() {
		return maxAggregateShipQuantity;
	}

	public void setMaxAggregateShipQuantity(String maxAggregateShipQuantity) {
		this.maxAggregateShipQuantity = maxAggregateShipQuantity;
	}

	public String getOfferingCanBeGiftMessaged() {
		return offeringCanBeGiftMessaged;
	}

	public void setOfferingCanBeGiftMessaged(String offeringCanBeGiftMessaged) {
		this.offeringCanBeGiftMessaged = offeringCanBeGiftMessaged;
	}

	public String getOfferingCanBeGiftwrapped() {
		return offeringCanBeGiftwrapped;
	}

	public void setOfferingCanBeGiftwrapped(String offeringCanBeGiftwrapped) {
		this.offeringCanBeGiftwrapped = offeringCanBeGiftwrapped;
	}

	public String getIsDiscontinuedByManufacturer() {
		return isDiscontinuedByManufacturer;
	}

	public void setIsDiscontinuedByManufacturer(String isDiscontinuedByManufacturer) {
		this.isDiscontinuedByManufacturer = isDiscontinuedByManufacturer;
	}

	public String getMissingKeysetReason() {
		return missingKeysetReason;
	}

	public void setMissingKeysetReason(String missingKeysetReason) {
		this.missingKeysetReason = missingKeysetReason;
	}

	public String getDeliveryScheduleGroupId() {
		return deliveryScheduleGroupId;
	}

	public void setDeliveryScheduleGroupId(String deliveryScheduleGroupId) {
		this.deliveryScheduleGroupId = deliveryScheduleGroupId;
	}

	public String getWebsiteShippingWeight() {
		return websiteShippingWeight;
	}

	public void setWebsiteShippingWeight(String websiteShippingWeight) {
		this.websiteShippingWeight = websiteShippingWeight;
	}

	public String getWebsiteShippingWeightUnitOfMeasure() {
		return websiteShippingWeightUnitOfMeasure;
	}

	public void setWebsiteShippingWeightUnitOfMeasure(String websiteShippingWeightUnitOfMeasure) {
		this.websiteShippingWeightUnitOfMeasure = websiteShippingWeightUnitOfMeasure;
	}

	public String getDisplayDimensionsUnitOfMeasure() {
		return displayDimensionsUnitOfMeasure;
	}

	public void setDisplayDimensionsUnitOfMeasure(String displayDimensionsUnitOfMeasure) {
		this.displayDimensionsUnitOfMeasure = displayDimensionsUnitOfMeasure;
	}

	public String getItemDisplayDiameter() {
		return itemDisplayDiameter;
	}

	public void setItemDisplayDiameter(String itemDisplayDiameter) {
		this.itemDisplayDiameter = itemDisplayDiameter;
	}

	public String getItemDisplayHeight() {
		return itemDisplayHeight;
	}

	public void setItemDisplayHeight(String itemDisplayHeight) {
		this.itemDisplayHeight = itemDisplayHeight;
	}

	public String getItemDisplayWidth() {
		return itemDisplayWidth;
	}

	public void setItemDisplayWidth(String itemDisplayWidth) {
		this.itemDisplayWidth = itemDisplayWidth;
	}

	public String getItemDisplayLength() {
		return itemDisplayLength;
	}

	public void setItemDisplayLength(String itemDisplayLength) {
		this.itemDisplayLength = itemDisplayLength;
	}

	public String getItemLength() {
		return itemLength;
	}

	public void setItemLength(String itemLength) {
		this.itemLength = itemLength;
	}

	public String getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(String itemWidth) {
		this.itemWidth = itemWidth;
	}

	public String getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(String itemHeight) {
		this.itemHeight = itemHeight;
	}

	public String getItemDimensionsUnitOfMeasure() {
		return itemDimensionsUnitOfMeasure;
	}

	public void setItemDimensionsUnitOfMeasure(String itemDimensionsUnitOfMeasure) {
		this.itemDimensionsUnitOfMeasure = itemDimensionsUnitOfMeasure;
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

	public String getTargetAudienceKeywords1() {
		return targetAudienceKeywords1;
	}

	public void setTargetAudienceKeywords1(String targetAudienceKeywords1) {
		this.targetAudienceKeywords1 = targetAudienceKeywords1;
	}

	public String getTargetAudienceKeywords2() {
		return targetAudienceKeywords2;
	}

	public void setTargetAudienceKeywords2(String targetAudienceKeywords2) {
		this.targetAudienceKeywords2 = targetAudienceKeywords2;
	}

	public String getTargetAudienceKeywords3() {
		return targetAudienceKeywords3;
	}

	public void setTargetAudienceKeywords3(String targetAudienceKeywords3) {
		this.targetAudienceKeywords3 = targetAudienceKeywords3;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public String getSpecificUsesKeywords1() {
		return specificUsesKeywords1;
	}

	public void setSpecificUsesKeywords1(String specificUsesKeywords1) {
		this.specificUsesKeywords1 = specificUsesKeywords1;
	}

	public String getSpecificUsesKeywords2() {
		return specificUsesKeywords2;
	}

	public void setSpecificUsesKeywords2(String specificUsesKeywords2) {
		this.specificUsesKeywords2 = specificUsesKeywords2;
	}

	public String getSpecificUsesKeywords3() {
		return specificUsesKeywords3;
	}

	public void setSpecificUsesKeywords3(String specificUsesKeywords3) {
		this.specificUsesKeywords3 = specificUsesKeywords3;
	}

	public String getSpecificUsesKeywords4() {
		return specificUsesKeywords4;
	}

	public void setSpecificUsesKeywords4(String specificUsesKeywords4) {
		this.specificUsesKeywords4 = specificUsesKeywords4;
	}

	public String getSpecificUsesKeywords5() {
		return specificUsesKeywords5;
	}

	public void setSpecificUsesKeywords5(String specificUsesKeywords5) {
		this.specificUsesKeywords5 = specificUsesKeywords5;
	}

	public String getThesaurusAttributeKeywords1() {
		return thesaurusAttributeKeywords1;
	}

	public void setThesaurusAttributeKeywords1(String thesaurusAttributeKeywords1) {
		this.thesaurusAttributeKeywords1 = thesaurusAttributeKeywords1;
	}

	public String getThesaurusAttributeKeywords2() {
		return thesaurusAttributeKeywords2;
	}

	public void setThesaurusAttributeKeywords2(String thesaurusAttributeKeywords2) {
		this.thesaurusAttributeKeywords2 = thesaurusAttributeKeywords2;
	}

	public String getThesaurusAttributeKeywords3() {
		return thesaurusAttributeKeywords3;
	}

	public void setThesaurusAttributeKeywords3(String thesaurusAttributeKeywords3) {
		this.thesaurusAttributeKeywords3 = thesaurusAttributeKeywords3;
	}

	public String getThesaurusAttributeKeywords4() {
		return thesaurusAttributeKeywords4;
	}

	public void setThesaurusAttributeKeywords4(String thesaurusAttributeKeywords4) {
		this.thesaurusAttributeKeywords4 = thesaurusAttributeKeywords4;
	}

	public String getThesaurusAttributeKeywords5() {
		return thesaurusAttributeKeywords5;
	}

	public void setThesaurusAttributeKeywords5(String thesaurusAttributeKeywords5) {
		this.thesaurusAttributeKeywords5 = thesaurusAttributeKeywords5;
	}

	public String getThesaurusSubjectKeywords1() {
		return thesaurusSubjectKeywords1;
	}

	public void setThesaurusSubjectKeywords1(String thesaurusSubjectKeywords1) {
		this.thesaurusSubjectKeywords1 = thesaurusSubjectKeywords1;
	}

	public String getThesaurusSubjectKeywords2() {
		return thesaurusSubjectKeywords2;
	}

	public void setThesaurusSubjectKeywords2(String thesaurusSubjectKeywords2) {
		this.thesaurusSubjectKeywords2 = thesaurusSubjectKeywords2;
	}

	public String getThesaurusSubjectKeywords3() {
		return thesaurusSubjectKeywords3;
	}

	public void setThesaurusSubjectKeywords3(String thesaurusSubjectKeywords3) {
		this.thesaurusSubjectKeywords3 = thesaurusSubjectKeywords3;
	}

	public String getThesaurusSubjectKeywords4() {
		return thesaurusSubjectKeywords4;
	}

	public void setThesaurusSubjectKeywords4(String thesaurusSubjectKeywords4) {
		this.thesaurusSubjectKeywords4 = thesaurusSubjectKeywords4;
	}

	public String getThesaurusSubjectKeywords5() {
		return thesaurusSubjectKeywords5;
	}

	public void setThesaurusSubjectKeywords5(String thesaurusSubjectKeywords5) {
		this.thesaurusSubjectKeywords5 = thesaurusSubjectKeywords5;
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

	public String getPlatinumKeywords1() {
		return platinumKeywords1;
	}

	public void setPlatinumKeywords1(String platinumKeywords1) {
		this.platinumKeywords1 = platinumKeywords1;
	}

	public String getPlatinumKeywords2() {
		return platinumKeywords2;
	}

	public void setPlatinumKeywords2(String platinumKeywords2) {
		this.platinumKeywords2 = platinumKeywords2;
	}

	public String getPlatinumKeywords3() {
		return platinumKeywords3;
	}

	public void setPlatinumKeywords3(String platinumKeywords3) {
		this.platinumKeywords3 = platinumKeywords3;
	}

	public String getPlatinumKeywords4() {
		return platinumKeywords4;
	}

	public void setPlatinumKeywords4(String platinumKeywords4) {
		this.platinumKeywords4 = platinumKeywords4;
	}

	public String getPlatinumKeywords5() {
		return platinumKeywords5;
	}

	public void setPlatinumKeywords5(String platinumKeywords5) {
		this.platinumKeywords5 = platinumKeywords5;
	}

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public String getSwatchImageUrl() {
		return swatchImageUrl;
	}

	public void setSwatchImageUrl(String swatchImageUrl) {
		this.swatchImageUrl = swatchImageUrl;
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

	public String getFulfillmentCenterId() {
		return fulfillmentCenterId;
	}

	public void setFulfillmentCenterId(String fulfillmentCenterId) {
		this.fulfillmentCenterId = fulfillmentCenterId;
	}

	public String getPackageLength() {
		return packageLength;
	}

	public void setPackageLength(String packageLength) {
		this.packageLength = packageLength;
	}

	public String getPackageHeight() {
		return packageHeight;
	}

	public void setPackageHeight(String packageHeight) {
		this.packageHeight = packageHeight;
	}

	public String getPackageWidth() {
		return packageWidth;
	}

	public void setPackageWidth(String packageWidth) {
		this.packageWidth = packageWidth;
	}

	public String getPackageDimensionsUnitOfMeasure() {
		return packageDimensionsUnitOfMeasure;
	}

	public void setPackageDimensionsUnitOfMeasure(String packageDimensionsUnitOfMeasure) {
		this.packageDimensionsUnitOfMeasure = packageDimensionsUnitOfMeasure;
	}

	public String getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(String packageWeight) {
		this.packageWeight = packageWeight;
	}

	public String getPackageWeightUnitOfMeasure() {
		return packageWeightUnitOfMeasure;
	}

	public void setPackageWeightUnitOfMeasure(String packageWeightUnitOfMeasure) {
		this.packageWeightUnitOfMeasure = packageWeightUnitOfMeasure;
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

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getProp65() {
		return prop65;
	}

	public void setProp65(String prop65) {
		this.prop65 = prop65;
	}

	public String getCpsiaCautionaryStatement1() {
		return cpsiaCautionaryStatement1;
	}

	public void setCpsiaCautionaryStatement1(String cpsiaCautionaryStatement1) {
		this.cpsiaCautionaryStatement1 = cpsiaCautionaryStatement1;
	}

	public String getCpsiaCautionaryStatement2() {
		return cpsiaCautionaryStatement2;
	}

	public void setCpsiaCautionaryStatement2(String cpsiaCautionaryStatement2) {
		this.cpsiaCautionaryStatement2 = cpsiaCautionaryStatement2;
	}

	public String getCpsiaCautionaryStatement3() {
		return cpsiaCautionaryStatement3;
	}

	public void setCpsiaCautionaryStatement3(String cpsiaCautionaryStatement3) {
		this.cpsiaCautionaryStatement3 = cpsiaCautionaryStatement3;
	}

	public String getCpsiaCautionaryStatement4() {
		return cpsiaCautionaryStatement4;
	}

	public void setCpsiaCautionaryStatement4(String cpsiaCautionaryStatement4) {
		this.cpsiaCautionaryStatement4 = cpsiaCautionaryStatement4;
	}

	public String getCpsiaCautionaryDescription() {
		return cpsiaCautionaryDescription;
	}

	public void setCpsiaCautionaryDescription(String cpsiaCautionaryDescription) {
		this.cpsiaCautionaryDescription = cpsiaCautionaryDescription;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public String getTotalMetalWeight() {
		return totalMetalWeight;
	}

	public void setTotalMetalWeight(String totalMetalWeight) {
		this.totalMetalWeight = totalMetalWeight;
	}

	public String getTotalMetalWeightUnitOfMeasure() {
		return totalMetalWeightUnitOfMeasure;
	}

	public void setTotalMetalWeightUnitOfMeasure(String totalMetalWeightUnitOfMeasure) {
		this.totalMetalWeightUnitOfMeasure = totalMetalWeightUnitOfMeasure;
	}

	public String getTotalDiamondWeight() {
		return totalDiamondWeight;
	}

	public void setTotalDiamondWeight(String totalDiamondWeight) {
		this.totalDiamondWeight = totalDiamondWeight;
	}

	public String getTotalDiamondWeightUnitOfMeasure() {
		return totalDiamondWeightUnitOfMeasure;
	}

	public void setTotalDiamondWeightUnitOfMeasure(String totalDiamondWeightUnitOfMeasure) {
		this.totalDiamondWeightUnitOfMeasure = totalDiamondWeightUnitOfMeasure;
	}

	public String getTotalGemWeight() {
		return totalGemWeight;
	}

	public void setTotalGemWeight(String totalGemWeight) {
		this.totalGemWeight = totalGemWeight;
	}

	public String getTotalGemWeightUnitOfMeasure() {
		return totalGemWeightUnitOfMeasure;
	}

	public void setTotalGemWeightUnitOfMeasure(String totalGemWeightUnitOfMeasure) {
		this.totalGemWeightUnitOfMeasure = totalGemWeightUnitOfMeasure;
	}

	public String getMaterialType1() {
		return materialType1;
	}

	public void setMaterialType1(String materialType1) {
		this.materialType1 = materialType1;
	}

	public String getMaterialType2() {
		return materialType2;
	}

	public void setMaterialType2(String materialType2) {
		this.materialType2 = materialType2;
	}

	public String getMaterialType3() {
		return materialType3;
	}

	public void setMaterialType3(String materialType3) {
		this.materialType3 = materialType3;
	}

	public String getMaterialType4() {
		return materialType4;
	}

	public void setMaterialType4(String materialType4) {
		this.materialType4 = materialType4;
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

	public String getSettingType() {
		return settingType;
	}

	public void setSettingType(String settingType) {
		this.settingType = settingType;
	}

	public String getNumberOfStones() {
		return numberOfStones;
	}

	public void setNumberOfStones(String numberOfStones) {
		this.numberOfStones = numberOfStones;
	}

	public String getClaspType() {
		return claspType;
	}

	public void setClaspType(String claspType) {
		this.claspType = claspType;
	}

	public String getChainType() {
		return chainType;
	}

	public void setChainType(String chainType) {
		this.chainType = chainType;
	}

	public String getRingSize() {
		return ringSize;
	}

	public void setRingSize(String ringSize) {
		this.ringSize = ringSize;
	}

	public String getIsResizable() {
		return isResizable;
	}

	public void setIsResizable(String isResizable) {
		this.isResizable = isResizable;
	}

	public String getRingSizingLowerRange() {
		return ringSizingLowerRange;
	}

	public void setRingSizingLowerRange(String ringSizingLowerRange) {
		this.ringSizingLowerRange = ringSizingLowerRange;
	}

	public String getRingSizingUpperRange() {
		return ringSizingUpperRange;
	}

	public void setRingSizingUpperRange(String ringSizingUpperRange) {
		this.ringSizingUpperRange = ringSizingUpperRange;
	}

	public String getEstatePeriod() {
		return estatePeriod;
	}

	public void setEstatePeriod(String estatePeriod) {
		this.estatePeriod = estatePeriod;
	}

	public String getCertificateNumber1() {
		return certificateNumber1;
	}

	public void setCertificateNumber1(String certificateNumber1) {
		this.certificateNumber1 = certificateNumber1;
	}

	public String getCertificateNumber2() {
		return certificateNumber2;
	}

	public void setCertificateNumber2(String certificateNumber2) {
		this.certificateNumber2 = certificateNumber2;
	}

	public String getCertificateNumber3() {
		return certificateNumber3;
	}

	public void setCertificateNumber3(String certificateNumber3) {
		this.certificateNumber3 = certificateNumber3;
	}

	public String getCertificateNumber4() {
		return certificateNumber4;
	}

	public void setCertificateNumber4(String certificateNumber4) {
		this.certificateNumber4 = certificateNumber4;
	}

	public String getCertificateNumber5() {
		return certificateNumber5;
	}

	public void setCertificateNumber5(String certificateNumber5) {
		this.certificateNumber5 = certificateNumber5;
	}

	public String getCertificateNumber6() {
		return certificateNumber6;
	}

	public void setCertificateNumber6(String certificateNumber6) {
		this.certificateNumber6 = certificateNumber6;
	}

	public String getCertificateNumber7() {
		return certificateNumber7;
	}

	public void setCertificateNumber7(String certificateNumber7) {
		this.certificateNumber7 = certificateNumber7;
	}

	public String getCertificateNumber8() {
		return certificateNumber8;
	}

	public void setCertificateNumber8(String certificateNumber8) {
		this.certificateNumber8 = certificateNumber8;
	}

	public String getCertificateNumber9() {
		return certificateNumber9;
	}

	public void setCertificateNumber9(String certificateNumber9) {
		this.certificateNumber9 = certificateNumber9;
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

	public String getStoneCut1() {
		return stoneCut1;
	}

	public void setStoneCut1(String stoneCut1) {
		this.stoneCut1 = stoneCut1;
	}

	public String getStoneCut2() {
		return stoneCut2;
	}

	public void setStoneCut2(String stoneCut2) {
		this.stoneCut2 = stoneCut2;
	}

	public String getStoneCut3() {
		return stoneCut3;
	}

	public void setStoneCut3(String stoneCut3) {
		this.stoneCut3 = stoneCut3;
	}

	public String getStoneColor1() {
		return stoneColor1;
	}

	public void setStoneColor1(String stoneColor1) {
		this.stoneColor1 = stoneColor1;
	}

	public String getStoneColor2() {
		return stoneColor2;
	}

	public void setStoneColor2(String stoneColor2) {
		this.stoneColor2 = stoneColor2;
	}

	public String getStoneColor3() {
		return stoneColor3;
	}

	public void setStoneColor3(String stoneColor3) {
		this.stoneColor3 = stoneColor3;
	}

	public String getStoneClarity1() {
		return stoneClarity1;
	}

	public void setStoneClarity1(String stoneClarity1) {
		this.stoneClarity1 = stoneClarity1;
	}

	public String getStoneClarity2() {
		return stoneClarity2;
	}

	public void setStoneClarity2(String stoneClarity2) {
		this.stoneClarity2 = stoneClarity2;
	}

	public String getStoneClarity3() {
		return stoneClarity3;
	}

	public void setStoneClarity3(String stoneClarity3) {
		this.stoneClarity3 = stoneClarity3;
	}

	public String getStoneShape1() {
		return stoneShape1;
	}

	public void setStoneShape1(String stoneShape1) {
		this.stoneShape1 = stoneShape1;
	}

	public String getStoneShape2() {
		return stoneShape2;
	}

	public void setStoneShape2(String stoneShape2) {
		this.stoneShape2 = stoneShape2;
	}

	public String getStoneShape3() {
		return stoneShape3;
	}

	public void setStoneShape3(String stoneShape3) {
		this.stoneShape3 = stoneShape3;
	}

	public String getStoneCreationMethod1() {
		return stoneCreationMethod1;
	}

	public void setStoneCreationMethod1(String stoneCreationMethod1) {
		this.stoneCreationMethod1 = stoneCreationMethod1;
	}

	public String getStoneCreationMethod2() {
		return stoneCreationMethod2;
	}

	public void setStoneCreationMethod2(String stoneCreationMethod2) {
		this.stoneCreationMethod2 = stoneCreationMethod2;
	}

	public String getStoneCreationMethod3() {
		return stoneCreationMethod3;
	}

	public void setStoneCreationMethod3(String stoneCreationMethod3) {
		this.stoneCreationMethod3 = stoneCreationMethod3;
	}

	public String getStoneTreatmentMethod1() {
		return stoneTreatmentMethod1;
	}

	public void setStoneTreatmentMethod1(String stoneTreatmentMethod1) {
		this.stoneTreatmentMethod1 = stoneTreatmentMethod1;
	}

	public String getStoneTreatmentMethod2() {
		return stoneTreatmentMethod2;
	}

	public void setStoneTreatmentMethod2(String stoneTreatmentMethod2) {
		this.stoneTreatmentMethod2 = stoneTreatmentMethod2;
	}

	public String getStoneTreatmentMethod3() {
		return stoneTreatmentMethod3;
	}

	public void setStoneTreatmentMethod3(String stoneTreatmentMethod3) {
		this.stoneTreatmentMethod3 = stoneTreatmentMethod3;
	}

	public String getStoneDimensionsUnitOfMeasure1() {
		return stoneDimensionsUnitOfMeasure1;
	}

	public void setStoneDimensionsUnitOfMeasure1(String stoneDimensionsUnitOfMeasure1) {
		this.stoneDimensionsUnitOfMeasure1 = stoneDimensionsUnitOfMeasure1;
	}

	public String getStoneDimensionsUnitOfMeasure2() {
		return stoneDimensionsUnitOfMeasure2;
	}

	public void setStoneDimensionsUnitOfMeasure2(String stoneDimensionsUnitOfMeasure2) {
		this.stoneDimensionsUnitOfMeasure2 = stoneDimensionsUnitOfMeasure2;
	}

	public String getStoneDimensionsUnitOfMeasure3() {
		return stoneDimensionsUnitOfMeasure3;
	}

	public void setStoneDimensionsUnitOfMeasure3(String stoneDimensionsUnitOfMeasure3) {
		this.stoneDimensionsUnitOfMeasure3 = stoneDimensionsUnitOfMeasure3;
	}

	public String getStoneHeight1() {
		return stoneHeight1;
	}

	public void setStoneHeight1(String stoneHeight1) {
		this.stoneHeight1 = stoneHeight1;
	}

	public String getStoneHeight2() {
		return stoneHeight2;
	}

	public void setStoneHeight2(String stoneHeight2) {
		this.stoneHeight2 = stoneHeight2;
	}

	public String getStoneHeight3() {
		return stoneHeight3;
	}

	public void setStoneHeight3(String stoneHeight3) {
		this.stoneHeight3 = stoneHeight3;
	}

	public String getStoneLength1() {
		return stoneLength1;
	}

	public void setStoneLength1(String stoneLength1) {
		this.stoneLength1 = stoneLength1;
	}

	public String getStoneLength2() {
		return stoneLength2;
	}

	public void setStoneLength2(String stoneLength2) {
		this.stoneLength2 = stoneLength2;
	}

	public String getStoneLength3() {
		return stoneLength3;
	}

	public void setStoneLength3(String stoneLength3) {
		this.stoneLength3 = stoneLength3;
	}

	public String getStoneWidth1() {
		return stoneWidth1;
	}

	public void setStoneWidth1(String stoneWidth1) {
		this.stoneWidth1 = stoneWidth1;
	}

	public String getStoneWidth2() {
		return stoneWidth2;
	}

	public void setStoneWidth2(String stoneWidth2) {
		this.stoneWidth2 = stoneWidth2;
	}

	public String getStoneWidth3() {
		return stoneWidth3;
	}

	public void setStoneWidth3(String stoneWidth3) {
		this.stoneWidth3 = stoneWidth3;
	}

	public String getStoneWeight1() {
		return stoneWeight1;
	}

	public void setStoneWeight1(String stoneWeight1) {
		this.stoneWeight1 = stoneWeight1;
	}

	public String getStoneWeight2() {
		return stoneWeight2;
	}

	public void setStoneWeight2(String stoneWeight2) {
		this.stoneWeight2 = stoneWeight2;
	}

	public String getStoneWeight3() {
		return stoneWeight3;
	}

	public void setStoneWeight3(String stoneWeight3) {
		this.stoneWeight3 = stoneWeight3;
	}

	public String getCertificateType1() {
		return certificateType1;
	}

	public void setCertificateType1(String certificateType1) {
		this.certificateType1 = certificateType1;
	}

	public String getCertificateType2() {
		return certificateType2;
	}

	public void setCertificateType2(String certificateType2) {
		this.certificateType2 = certificateType2;
	}

	public String getCertificateType3() {
		return certificateType3;
	}

	public void setCertificateType3(String certificateType3) {
		this.certificateType3 = certificateType3;
	}

	public String getCertificateType4() {
		return certificateType4;
	}

	public void setCertificateType4(String certificateType4) {
		this.certificateType4 = certificateType4;
	}

	public String getCertificateType5() {
		return certificateType5;
	}

	public void setCertificateType5(String certificateType5) {
		this.certificateType5 = certificateType5;
	}

	public String getCertificateType6() {
		return certificateType6;
	}

	public void setCertificateType6(String certificateType6) {
		this.certificateType6 = certificateType6;
	}

	public String getCertificateType7() {
		return certificateType7;
	}

	public void setCertificateType7(String certificateType7) {
		this.certificateType7 = certificateType7;
	}

	public String getCertificateType8() {
		return certificateType8;
	}

	public void setCertificateType8(String certificateType8) {
		this.certificateType8 = certificateType8;
	}

	public String getCertificateType9() {
		return certificateType9;
	}

	public void setCertificateType9(String certificateType9) {
		this.certificateType9 = certificateType9;
	}

	public String getIsLabCreated1() {
		return isLabCreated1;
	}

	public void setIsLabCreated1(String isLabCreated1) {
		this.isLabCreated1 = isLabCreated1;
	}

	public String getIsLabCreated2() {
		return isLabCreated2;
	}

	public void setIsLabCreated2(String isLabCreated2) {
		this.isLabCreated2 = isLabCreated2;
	}

	public String getIsLabCreated3() {
		return isLabCreated3;
	}

	public void setIsLabCreated3(String isLabCreated3) {
		this.isLabCreated3 = isLabCreated3;
	}

	public String getInscription1() {
		return inscription1;
	}

	public void setInscription1(String inscription1) {
		this.inscription1 = inscription1;
	}

	public String getInscription2() {
		return inscription2;
	}

	public void setInscription2(String inscription2) {
		this.inscription2 = inscription2;
	}

	public String getInscription3() {
		return inscription3;
	}

	public void setInscription3(String inscription3) {
		this.inscription3 = inscription3;
	}

	public String getStoneDepthPercentage1() {
		return stoneDepthPercentage1;
	}

	public void setStoneDepthPercentage1(String stoneDepthPercentage1) {
		this.stoneDepthPercentage1 = stoneDepthPercentage1;
	}

	public String getStoneDepthPercentage2() {
		return stoneDepthPercentage2;
	}

	public void setStoneDepthPercentage2(String stoneDepthPercentage2) {
		this.stoneDepthPercentage2 = stoneDepthPercentage2;
	}

	public String getStoneDepthPercentage3() {
		return stoneDepthPercentage3;
	}

	public void setStoneDepthPercentage3(String stoneDepthPercentage3) {
		this.stoneDepthPercentage3 = stoneDepthPercentage3;
	}

	public String getStoneTablePercentage1() {
		return stoneTablePercentage1;
	}

	public void setStoneTablePercentage1(String stoneTablePercentage1) {
		this.stoneTablePercentage1 = stoneTablePercentage1;
	}

	public String getStoneTablePercentage2() {
		return stoneTablePercentage2;
	}

	public void setStoneTablePercentage2(String stoneTablePercentage2) {
		this.stoneTablePercentage2 = stoneTablePercentage2;
	}

	public String getStoneTablePercentage3() {
		return stoneTablePercentage3;
	}

	public void setStoneTablePercentage3(String stoneTablePercentage3) {
		this.stoneTablePercentage3 = stoneTablePercentage3;
	}

	public String getStoneSymmetry1() {
		return stoneSymmetry1;
	}

	public void setStoneSymmetry1(String stoneSymmetry1) {
		this.stoneSymmetry1 = stoneSymmetry1;
	}

	public String getStoneSymmetry2() {
		return stoneSymmetry2;
	}

	public void setStoneSymmetry2(String stoneSymmetry2) {
		this.stoneSymmetry2 = stoneSymmetry2;
	}

	public String getStoneSymmetry3() {
		return stoneSymmetry3;
	}

	public void setStoneSymmetry3(String stoneSymmetry3) {
		this.stoneSymmetry3 = stoneSymmetry3;
	}

	public String getStonePolish1() {
		return stonePolish1;
	}

	public void setStonePolish1(String stonePolish1) {
		this.stonePolish1 = stonePolish1;
	}

	public String getStonePolish2() {
		return stonePolish2;
	}

	public void setStonePolish2(String stonePolish2) {
		this.stonePolish2 = stonePolish2;
	}

	public String getStonePolish3() {
		return stonePolish3;
	}

	public void setStonePolish3(String stonePolish3) {
		this.stonePolish3 = stonePolish3;
	}

	public String getStoneGirdle1() {
		return stoneGirdle1;
	}

	public void setStoneGirdle1(String stoneGirdle1) {
		this.stoneGirdle1 = stoneGirdle1;
	}

	public String getStoneGirdle2() {
		return stoneGirdle2;
	}

	public void setStoneGirdle2(String stoneGirdle2) {
		this.stoneGirdle2 = stoneGirdle2;
	}

	public String getStoneGirdle3() {
		return stoneGirdle3;
	}

	public void setStoneGirdle3(String stoneGirdle3) {
		this.stoneGirdle3 = stoneGirdle3;
	}

	public String getStoneCulet1() {
		return stoneCulet1;
	}

	public void setStoneCulet1(String stoneCulet1) {
		this.stoneCulet1 = stoneCulet1;
	}

	public String getStoneCulet2() {
		return stoneCulet2;
	}

	public void setStoneCulet2(String stoneCulet2) {
		this.stoneCulet2 = stoneCulet2;
	}

	public String getStoneCulet3() {
		return stoneCulet3;
	}

	public void setStoneCulet3(String stoneCulet3) {
		this.stoneCulet3 = stoneCulet3;
	}

	public String getStoneFluorescence1() {
		return stoneFluorescence1;
	}

	public void setStoneFluorescence1(String stoneFluorescence1) {
		this.stoneFluorescence1 = stoneFluorescence1;
	}

	public String getStoneFluorescence2() {
		return stoneFluorescence2;
	}

	public void setStoneFluorescence2(String stoneFluorescence2) {
		this.stoneFluorescence2 = stoneFluorescence2;
	}

	public String getStoneFluorescence3() {
		return stoneFluorescence3;
	}

	public void setStoneFluorescence3(String stoneFluorescence3) {
		this.stoneFluorescence3 = stoneFluorescence3;
	}

	public String getPearlType() {
		return pearlType;
	}

	public void setPearlType(String pearlType) {
		this.pearlType = pearlType;
	}

	public String getPearlMinimumColor() {
		return pearlMinimumColor;
	}

	public void setPearlMinimumColor(String pearlMinimumColor) {
		this.pearlMinimumColor = pearlMinimumColor;
	}

	public String getPearlLustre() {
		return pearlLustre;
	}

	public void setPearlLustre(String pearlLustre) {
		this.pearlLustre = pearlLustre;
	}

	public String getPearlShape() {
		return pearlShape;
	}

	public void setPearlShape(String pearlShape) {
		this.pearlShape = pearlShape;
	}

	public String getPearlUniformity() {
		return pearlUniformity;
	}

	public void setPearlUniformity(String pearlUniformity) {
		this.pearlUniformity = pearlUniformity;
	}

	public String getPearlSurfaceBlemishes() {
		return pearlSurfaceBlemishes;
	}

	public void setPearlSurfaceBlemishes(String pearlSurfaceBlemishes) {
		this.pearlSurfaceBlemishes = pearlSurfaceBlemishes;
	}

	public String getNacreThickness() {
		return nacreThickness;
	}

	public void setNacreThickness(String nacreThickness) {
		this.nacreThickness = nacreThickness;
	}

	public String getPearlStringingMethod() {
		return pearlStringingMethod;
	}

	public void setPearlStringingMethod(String pearlStringingMethod) {
		this.pearlStringingMethod = pearlStringingMethod;
	}

	public String getSizePerPearl() {
		return sizePerPearl;
	}

	public void setSizePerPearl(String sizePerPearl) {
		this.sizePerPearl = sizePerPearl;
	}

	public String getNumberOfPearls() {
		return numberOfPearls;
	}

	public void setNumberOfPearls(String numberOfPearls) {
		this.numberOfPearls = numberOfPearls;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getBackFinding() {
		return backFinding;
	}

	public void setBackFinding(String backFinding) {
		this.backFinding = backFinding;
	}

	@Override
	public String toString() {
		return "JewelryEntity [" + (itemSku != null ? "itemSku=" + itemSku + ", " : "")
				+ (itemName != null ? "itemName=" + itemName + ", " : "")
				+ (manufacturer != null ? "manufacturer=" + manufacturer + ", " : "")
				+ (model != null ? "model=" + model + ", " : "")
				+ (feedProductType != null ? "feedProductType=" + feedProductType + ", " : "")
				+ (itemType != null ? "itemType=" + itemType + ", " : "")
				+ (brandName != null ? "brandName=" + brandName + ", " : "")
				+ (externalProductId != null ? "externalProductId=" + externalProductId + ", " : "")
				+ (externalProductIdType != null ? "externalProductIdType=" + externalProductIdType + ", " : "")
				+ (productDescription != null ? "productDescription=" + productDescription + ", " : "")
				+ (updateDelete != null ? "updateDelete=" + updateDelete + ", " : "")
				+ (standardPrice != null ? "standardPrice=" + standardPrice + ", " : "")
				+ (quantity != null ? "quantity=" + quantity + ", " : "")
				+ (currency != null ? "currency=" + currency + ", " : "")
				+ (productSiteLaunchDate != null ? "productSiteLaunchDate=" + productSiteLaunchDate + ", " : "")
				+ (productTaxCode != null ? "productTaxCode=" + productTaxCode + ", " : "")
				+ (listPrice != null ? "listPrice=" + listPrice + ", " : "")
				+ (salePrice != null ? "salePrice=" + salePrice + ", " : "")
				+ (saleFromDate != null ? "saleFromDate=" + saleFromDate + ", " : "")
				+ (saleEndDate != null ? "saleEndDate=" + saleEndDate + ", " : "")
				+ (merchantReleaseDate != null ? "merchantReleaseDate=" + merchantReleaseDate + ", " : "")
				+ (itemPackageQuantity != null ? "itemPackageQuantity=" + itemPackageQuantity + ", " : "")
				+ (fulfillmentLatency != null ? "fulfillmentLatency=" + fulfillmentLatency + ", " : "")
				+ (restockDate != null ? "restockDate=" + restockDate + ", " : "")
				+ (maxAggregateShipQuantity != null ? "maxAggregateShipQuantity=" + maxAggregateShipQuantity + ", "
						: "")
				+ (offeringCanBeGiftMessaged != null ? "offeringCanBeGiftMessaged=" + offeringCanBeGiftMessaged + ", "
						: "")
				+ (offeringCanBeGiftwrapped != null ? "offeringCanBeGiftwrapped=" + offeringCanBeGiftwrapped + ", "
						: "")
				+ (isDiscontinuedByManufacturer != null
						? "isDiscontinuedByManufacturer=" + isDiscontinuedByManufacturer + ", " : "")
				+ (missingKeysetReason != null ? "missingKeysetReason=" + missingKeysetReason + ", " : "")
				+ (deliveryScheduleGroupId != null ? "deliveryScheduleGroupId=" + deliveryScheduleGroupId + ", " : "")
				+ (websiteShippingWeight != null ? "websiteShippingWeight=" + websiteShippingWeight + ", " : "")
				+ (websiteShippingWeightUnitOfMeasure != null
						? "websiteShippingWeightUnitOfMeasure=" + websiteShippingWeightUnitOfMeasure + ", " : "")
				+ (displayDimensionsUnitOfMeasure != null
						? "displayDimensionsUnitOfMeasure=" + displayDimensionsUnitOfMeasure + ", " : "")
				+ (itemDisplayDiameter != null ? "itemDisplayDiameter=" + itemDisplayDiameter + ", " : "")
				+ (itemDisplayHeight != null ? "itemDisplayHeight=" + itemDisplayHeight + ", " : "")
				+ (itemDisplayWidth != null ? "itemDisplayWidth=" + itemDisplayWidth + ", " : "")
				+ (itemDisplayLength != null ? "itemDisplayLength=" + itemDisplayLength + ", " : "")
				+ (itemLength != null ? "itemLength=" + itemLength + ", " : "")
				+ (itemWidth != null ? "itemWidth=" + itemWidth + ", " : "")
				+ (itemHeight != null ? "itemHeight=" + itemHeight + ", " : "")
				+ (itemDimensionsUnitOfMeasure != null
						? "itemDimensionsUnitOfMeasure=" + itemDimensionsUnitOfMeasure + ", " : "")
				+ (bulletPoint1 != null ? "bulletPoint1=" + bulletPoint1 + ", " : "")
				+ (bulletPoint2 != null ? "bulletPoint2=" + bulletPoint2 + ", " : "")
				+ (bulletPoint3 != null ? "bulletPoint3=" + bulletPoint3 + ", " : "")
				+ (bulletPoint4 != null ? "bulletPoint4=" + bulletPoint4 + ", " : "")
				+ (bulletPoint5 != null ? "bulletPoint5=" + bulletPoint5 + ", " : "")
				+ (targetAudienceKeywords1 != null ? "targetAudienceKeywords1=" + targetAudienceKeywords1 + ", " : "")
				+ (targetAudienceKeywords2 != null ? "targetAudienceKeywords2=" + targetAudienceKeywords2 + ", " : "")
				+ (targetAudienceKeywords3 != null ? "targetAudienceKeywords3=" + targetAudienceKeywords3 + ", " : "")
				+ (catalogNumber != null ? "catalogNumber=" + catalogNumber + ", " : "")
				+ (specificUsesKeywords1 != null ? "specificUsesKeywords1=" + specificUsesKeywords1 + ", " : "")
				+ (specificUsesKeywords2 != null ? "specificUsesKeywords2=" + specificUsesKeywords2 + ", " : "")
				+ (specificUsesKeywords3 != null ? "specificUsesKeywords3=" + specificUsesKeywords3 + ", " : "")
				+ (specificUsesKeywords4 != null ? "specificUsesKeywords4=" + specificUsesKeywords4 + ", " : "")
				+ (specificUsesKeywords5 != null ? "specificUsesKeywords5=" + specificUsesKeywords5 + ", " : "")
				+ (thesaurusAttributeKeywords1 != null
						? "thesaurusAttributeKeywords1=" + thesaurusAttributeKeywords1 + ", " : "")
				+ (thesaurusAttributeKeywords2 != null
						? "thesaurusAttributeKeywords2=" + thesaurusAttributeKeywords2 + ", " : "")
				+ (thesaurusAttributeKeywords3 != null
						? "thesaurusAttributeKeywords3=" + thesaurusAttributeKeywords3 + ", " : "")
				+ (thesaurusAttributeKeywords4 != null
						? "thesaurusAttributeKeywords4=" + thesaurusAttributeKeywords4 + ", " : "")
				+ (thesaurusAttributeKeywords5 != null
						? "thesaurusAttributeKeywords5=" + thesaurusAttributeKeywords5 + ", " : "")
				+ (thesaurusSubjectKeywords1 != null ? "thesaurusSubjectKeywords1=" + thesaurusSubjectKeywords1 + ", "
						: "")
				+ (thesaurusSubjectKeywords2 != null ? "thesaurusSubjectKeywords2=" + thesaurusSubjectKeywords2 + ", "
						: "")
				+ (thesaurusSubjectKeywords3 != null ? "thesaurusSubjectKeywords3=" + thesaurusSubjectKeywords3 + ", "
						: "")
				+ (thesaurusSubjectKeywords4 != null ? "thesaurusSubjectKeywords4=" + thesaurusSubjectKeywords4 + ", "
						: "")
				+ (thesaurusSubjectKeywords5 != null ? "thesaurusSubjectKeywords5=" + thesaurusSubjectKeywords5 + ", "
						: "")
				+ (genericKeywords1 != null ? "genericKeywords1=" + genericKeywords1 + ", " : "")
				+ (genericKeywords2 != null ? "genericKeywords2=" + genericKeywords2 + ", " : "")
				+ (genericKeywords3 != null ? "genericKeywords3=" + genericKeywords3 + ", " : "")
				+ (genericKeywords4 != null ? "genericKeywords4=" + genericKeywords4 + ", " : "")
				+ (genericKeywords5 != null ? "genericKeywords5=" + genericKeywords5 + ", " : "")
				+ (platinumKeywords1 != null ? "platinumKeywords1=" + platinumKeywords1 + ", " : "")
				+ (platinumKeywords2 != null ? "platinumKeywords2=" + platinumKeywords2 + ", " : "")
				+ (platinumKeywords3 != null ? "platinumKeywords3=" + platinumKeywords3 + ", " : "")
				+ (platinumKeywords4 != null ? "platinumKeywords4=" + platinumKeywords4 + ", " : "")
				+ (platinumKeywords5 != null ? "platinumKeywords5=" + platinumKeywords5 + ", " : "")
				+ (mainImageUrl != null ? "mainImageUrl=" + mainImageUrl + ", " : "")
				+ (swatchImageUrl != null ? "swatchImageUrl=" + swatchImageUrl + ", " : "")
				+ (otherImageUrl1 != null ? "otherImageUrl1=" + otherImageUrl1 + ", " : "")
				+ (otherImageUrl2 != null ? "otherImageUrl2=" + otherImageUrl2 + ", " : "")
				+ (otherImageUrl3 != null ? "otherImageUrl3=" + otherImageUrl3 + ", " : "")
				+ (otherImageUrl4 != null ? "otherImageUrl4=" + otherImageUrl4 + ", " : "")
				+ (otherImageUrl5 != null ? "otherImageUrl5=" + otherImageUrl5 + ", " : "")
				+ (otherImageUrl6 != null ? "otherImageUrl6=" + otherImageUrl6 + ", " : "")
				+ (otherImageUrl7 != null ? "otherImageUrl7=" + otherImageUrl7 + ", " : "")
				+ (otherImageUrl8 != null ? "otherImageUrl8=" + otherImageUrl8 + ", " : "")
				+ (fulfillmentCenterId != null ? "fulfillmentCenterId=" + fulfillmentCenterId + ", " : "")
				+ (packageLength != null ? "packageLength=" + packageLength + ", " : "")
				+ (packageHeight != null ? "packageHeight=" + packageHeight + ", " : "")
				+ (packageWidth != null ? "packageWidth=" + packageWidth + ", " : "")
				+ (packageDimensionsUnitOfMeasure != null
						? "packageDimensionsUnitOfMeasure=" + packageDimensionsUnitOfMeasure + ", " : "")
				+ (packageWeight != null ? "packageWeight=" + packageWeight + ", " : "")
				+ (packageWeightUnitOfMeasure != null
						? "packageWeightUnitOfMeasure=" + packageWeightUnitOfMeasure + ", " : "")
				+ (parentChild != null ? "parentChild=" + parentChild + ", " : "")
				+ (parentSku != null ? "parentSku=" + parentSku + ", " : "")
				+ (relationshipType != null ? "relationshipType=" + relationshipType + ", " : "")
				+ (variationTheme != null ? "variationTheme=" + variationTheme + ", " : "")
				+ (countryOfOrigin != null ? "countryOfOrigin=" + countryOfOrigin + ", " : "")
				+ (prop65 != null ? "prop65=" + prop65 + ", " : "")
				+ (cpsiaCautionaryStatement1 != null ? "cpsiaCautionaryStatement1=" + cpsiaCautionaryStatement1 + ", "
						: "")
				+ (cpsiaCautionaryStatement2 != null ? "cpsiaCautionaryStatement2=" + cpsiaCautionaryStatement2 + ", "
						: "")
				+ (cpsiaCautionaryStatement3 != null ? "cpsiaCautionaryStatement3=" + cpsiaCautionaryStatement3 + ", "
						: "")
				+ (cpsiaCautionaryStatement4 != null ? "cpsiaCautionaryStatement4=" + cpsiaCautionaryStatement4 + ", "
						: "")
				+ (cpsiaCautionaryDescription != null
						? "cpsiaCautionaryDescription=" + cpsiaCautionaryDescription + ", " : "")
				+ (departmentName != null ? "departmentName=" + departmentName + ", " : "")
				+ (designer != null ? "designer=" + designer + ", " : "")
				+ (totalMetalWeight != null ? "totalMetalWeight=" + totalMetalWeight + ", " : "")
				+ (totalMetalWeightUnitOfMeasure != null
						? "totalMetalWeightUnitOfMeasure=" + totalMetalWeightUnitOfMeasure + ", " : "")
				+ (totalDiamondWeight != null ? "totalDiamondWeight=" + totalDiamondWeight + ", " : "")
				+ (totalDiamondWeightUnitOfMeasure != null
						? "totalDiamondWeightUnitOfMeasure=" + totalDiamondWeightUnitOfMeasure + ", " : "")
				+ (totalGemWeight != null ? "totalGemWeight=" + totalGemWeight + ", " : "")
				+ (totalGemWeightUnitOfMeasure != null
						? "totalGemWeightUnitOfMeasure=" + totalGemWeightUnitOfMeasure + ", " : "")
				+ (materialType1 != null ? "materialType1=" + materialType1 + ", " : "")
				+ (materialType2 != null ? "materialType2=" + materialType2 + ", " : "")
				+ (materialType3 != null ? "materialType3=" + materialType3 + ", " : "")
				+ (materialType4 != null ? "materialType4=" + materialType4 + ", " : "")
				+ (metalType != null ? "metalType=" + metalType + ", " : "")
				+ (metalStamp != null ? "metalStamp=" + metalStamp + ", " : "")
				+ (settingType != null ? "settingType=" + settingType + ", " : "")
				+ (numberOfStones != null ? "numberOfStones=" + numberOfStones + ", " : "")
				+ (claspType != null ? "claspType=" + claspType + ", " : "")
				+ (chainType != null ? "chainType=" + chainType + ", " : "")
				+ (ringSize != null ? "ringSize=" + ringSize + ", " : "")
				+ (isResizable != null ? "isResizable=" + isResizable + ", " : "")
				+ (ringSizingLowerRange != null ? "ringSizingLowerRange=" + ringSizingLowerRange + ", " : "")
				+ (ringSizingUpperRange != null ? "ringSizingUpperRange=" + ringSizingUpperRange + ", " : "")
				+ (estatePeriod != null ? "estatePeriod=" + estatePeriod + ", " : "")
				+ (certificateNumber1 != null ? "certificateNumber1=" + certificateNumber1 + ", " : "")
				+ (certificateNumber2 != null ? "certificateNumber2=" + certificateNumber2 + ", " : "")
				+ (certificateNumber3 != null ? "certificateNumber3=" + certificateNumber3 + ", " : "")
				+ (certificateNumber4 != null ? "certificateNumber4=" + certificateNumber4 + ", " : "")
				+ (certificateNumber5 != null ? "certificateNumber5=" + certificateNumber5 + ", " : "")
				+ (certificateNumber6 != null ? "certificateNumber6=" + certificateNumber6 + ", " : "")
				+ (certificateNumber7 != null ? "certificateNumber7=" + certificateNumber7 + ", " : "")
				+ (certificateNumber8 != null ? "certificateNumber8=" + certificateNumber8 + ", " : "")
				+ (certificateNumber9 != null ? "certificateNumber9=" + certificateNumber9 + ", " : "")
				+ (gemType1 != null ? "gemType1=" + gemType1 + ", " : "")
				+ (gemType2 != null ? "gemType2=" + gemType2 + ", " : "")
				+ (gemType3 != null ? "gemType3=" + gemType3 + ", " : "")
				+ (stoneCut1 != null ? "stoneCut1=" + stoneCut1 + ", " : "")
				+ (stoneCut2 != null ? "stoneCut2=" + stoneCut2 + ", " : "")
				+ (stoneCut3 != null ? "stoneCut3=" + stoneCut3 + ", " : "")
				+ (stoneColor1 != null ? "stoneColor1=" + stoneColor1 + ", " : "")
				+ (stoneColor2 != null ? "stoneColor2=" + stoneColor2 + ", " : "")
				+ (stoneColor3 != null ? "stoneColor3=" + stoneColor3 + ", " : "")
				+ (stoneClarity1 != null ? "stoneClarity1=" + stoneClarity1 + ", " : "")
				+ (stoneClarity2 != null ? "stoneClarity2=" + stoneClarity2 + ", " : "")
				+ (stoneClarity3 != null ? "stoneClarity3=" + stoneClarity3 + ", " : "")
				+ (stoneShape1 != null ? "stoneShape1=" + stoneShape1 + ", " : "")
				+ (stoneShape2 != null ? "stoneShape2=" + stoneShape2 + ", " : "")
				+ (stoneShape3 != null ? "stoneShape3=" + stoneShape3 + ", " : "")
				+ (stoneCreationMethod1 != null ? "stoneCreationMethod1=" + stoneCreationMethod1 + ", " : "")
				+ (stoneCreationMethod2 != null ? "stoneCreationMethod2=" + stoneCreationMethod2 + ", " : "")
				+ (stoneCreationMethod3 != null ? "stoneCreationMethod3=" + stoneCreationMethod3 + ", " : "")
				+ (stoneTreatmentMethod1 != null ? "stoneTreatmentMethod1=" + stoneTreatmentMethod1 + ", " : "")
				+ (stoneTreatmentMethod2 != null ? "stoneTreatmentMethod2=" + stoneTreatmentMethod2 + ", " : "")
				+ (stoneTreatmentMethod3 != null ? "stoneTreatmentMethod3=" + stoneTreatmentMethod3 + ", " : "")
				+ (stoneDimensionsUnitOfMeasure1 != null
						? "stoneDimensionsUnitOfMeasure1=" + stoneDimensionsUnitOfMeasure1 + ", " : "")
				+ (stoneDimensionsUnitOfMeasure2 != null
						? "stoneDimensionsUnitOfMeasure2=" + stoneDimensionsUnitOfMeasure2 + ", " : "")
				+ (stoneDimensionsUnitOfMeasure3 != null
						? "stoneDimensionsUnitOfMeasure3=" + stoneDimensionsUnitOfMeasure3 + ", " : "")
				+ (stoneHeight1 != null ? "stoneHeight1=" + stoneHeight1 + ", " : "")
				+ (stoneHeight2 != null ? "stoneHeight2=" + stoneHeight2 + ", " : "")
				+ (stoneHeight3 != null ? "stoneHeight3=" + stoneHeight3 + ", " : "")
				+ (stoneLength1 != null ? "stoneLength1=" + stoneLength1 + ", " : "")
				+ (stoneLength2 != null ? "stoneLength2=" + stoneLength2 + ", " : "")
				+ (stoneLength3 != null ? "stoneLength3=" + stoneLength3 + ", " : "")
				+ (stoneWidth1 != null ? "stoneWidth1=" + stoneWidth1 + ", " : "")
				+ (stoneWidth2 != null ? "stoneWidth2=" + stoneWidth2 + ", " : "")
				+ (stoneWidth3 != null ? "stoneWidth3=" + stoneWidth3 + ", " : "")
				+ (stoneWeight1 != null ? "stoneWeight1=" + stoneWeight1 + ", " : "")
				+ (stoneWeight2 != null ? "stoneWeight2=" + stoneWeight2 + ", " : "")
				+ (stoneWeight3 != null ? "stoneWeight3=" + stoneWeight3 + ", " : "")
				+ (certificateType1 != null ? "certificateType1=" + certificateType1 + ", " : "")
				+ (certificateType2 != null ? "certificateType2=" + certificateType2 + ", " : "")
				+ (certificateType3 != null ? "certificateType3=" + certificateType3 + ", " : "")
				+ (certificateType4 != null ? "certificateType4=" + certificateType4 + ", " : "")
				+ (certificateType5 != null ? "certificateType5=" + certificateType5 + ", " : "")
				+ (certificateType6 != null ? "certificateType6=" + certificateType6 + ", " : "")
				+ (certificateType7 != null ? "certificateType7=" + certificateType7 + ", " : "")
				+ (certificateType8 != null ? "certificateType8=" + certificateType8 + ", " : "")
				+ (certificateType9 != null ? "certificateType9=" + certificateType9 + ", " : "")
				+ (isLabCreated1 != null ? "isLabCreated1=" + isLabCreated1 + ", " : "")
				+ (isLabCreated2 != null ? "isLabCreated2=" + isLabCreated2 + ", " : "")
				+ (isLabCreated3 != null ? "isLabCreated3=" + isLabCreated3 + ", " : "")
				+ (inscription1 != null ? "inscription1=" + inscription1 + ", " : "")
				+ (inscription2 != null ? "inscription2=" + inscription2 + ", " : "")
				+ (inscription3 != null ? "inscription3=" + inscription3 + ", " : "")
				+ (stoneDepthPercentage1 != null ? "stoneDepthPercentage1=" + stoneDepthPercentage1 + ", " : "")
				+ (stoneDepthPercentage2 != null ? "stoneDepthPercentage2=" + stoneDepthPercentage2 + ", " : "")
				+ (stoneDepthPercentage3 != null ? "stoneDepthPercentage3=" + stoneDepthPercentage3 + ", " : "")
				+ (stoneTablePercentage1 != null ? "stoneTablePercentage1=" + stoneTablePercentage1 + ", " : "")
				+ (stoneTablePercentage2 != null ? "stoneTablePercentage2=" + stoneTablePercentage2 + ", " : "")
				+ (stoneTablePercentage3 != null ? "stoneTablePercentage3=" + stoneTablePercentage3 + ", " : "")
				+ (stoneSymmetry1 != null ? "stoneSymmetry1=" + stoneSymmetry1 + ", " : "")
				+ (stoneSymmetry2 != null ? "stoneSymmetry2=" + stoneSymmetry2 + ", " : "")
				+ (stoneSymmetry3 != null ? "stoneSymmetry3=" + stoneSymmetry3 + ", " : "")
				+ (stonePolish1 != null ? "stonePolish1=" + stonePolish1 + ", " : "")
				+ (stonePolish2 != null ? "stonePolish2=" + stonePolish2 + ", " : "")
				+ (stonePolish3 != null ? "stonePolish3=" + stonePolish3 + ", " : "")
				+ (stoneGirdle1 != null ? "stoneGirdle1=" + stoneGirdle1 + ", " : "")
				+ (stoneGirdle2 != null ? "stoneGirdle2=" + stoneGirdle2 + ", " : "")
				+ (stoneGirdle3 != null ? "stoneGirdle3=" + stoneGirdle3 + ", " : "")
				+ (stoneCulet1 != null ? "stoneCulet1=" + stoneCulet1 + ", " : "")
				+ (stoneCulet2 != null ? "stoneCulet2=" + stoneCulet2 + ", " : "")
				+ (stoneCulet3 != null ? "stoneCulet3=" + stoneCulet3 + ", " : "")
				+ (stoneFluorescence1 != null ? "stoneFluorescence1=" + stoneFluorescence1 + ", " : "")
				+ (stoneFluorescence2 != null ? "stoneFluorescence2=" + stoneFluorescence2 + ", " : "")
				+ (stoneFluorescence3 != null ? "stoneFluorescence3=" + stoneFluorescence3 + ", " : "")
				+ (pearlType != null ? "pearlType=" + pearlType + ", " : "")
				+ (pearlMinimumColor != null ? "pearlMinimumColor=" + pearlMinimumColor + ", " : "")
				+ (pearlLustre != null ? "pearlLustre=" + pearlLustre + ", " : "")
				+ (pearlShape != null ? "pearlShape=" + pearlShape + ", " : "")
				+ (pearlUniformity != null ? "pearlUniformity=" + pearlUniformity + ", " : "")
				+ (pearlSurfaceBlemishes != null ? "pearlSurfaceBlemishes=" + pearlSurfaceBlemishes + ", " : "")
				+ (nacreThickness != null ? "nacreThickness=" + nacreThickness + ", " : "")
				+ (pearlStringingMethod != null ? "pearlStringingMethod=" + pearlStringingMethod + ", " : "")
				+ (sizePerPearl != null ? "sizePerPearl=" + sizePerPearl + ", " : "")
				+ (numberOfPearls != null ? "numberOfPearls=" + numberOfPearls + ", " : "")
				+ (styleName != null ? "styleName=" + styleName + ", " : "")
				+ (colorName != null ? "colorName=" + colorName + ", " : "")
				+ (backFinding != null ? "backFinding=" + backFinding : "") + "]";
	}

}
