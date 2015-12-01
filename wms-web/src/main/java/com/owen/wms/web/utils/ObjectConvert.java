package com.owen.wms.web.utils;

import java.util.List;

import com.amazonaws.mws.jaxb.entity.FashionNecklaceBraceletAnklet;
import com.amazonaws.mws.jaxb.entity.FashionNecklaceBraceletAnklet.VariationData;
import com.amazonaws.mws.jaxb.entity.Jewelry;
import com.amazonaws.mws.jaxb.entity.Jewelry.ProductType;
import com.amazonaws.mws.jaxb.entity.Product;
import com.amazonaws.mws.jaxb.entity.Product.DescriptionData;
import com.amazonaws.mws.jaxb.entity.StandardProductID;
import com.owen.wms.web.entity.ProductJewelryEntity;

public class ObjectConvert {

	public void convert(ProductJewelryEntity i){
		Product p = new Product();
		p.setSKU(i.getItemSku());
		
		StandardProductID productId = new StandardProductID();
		p.setStandardProductID(productId );
		productId.setType(i.getExternalProductIdType());
		productId.setValue(i.getExternalProductId());
		
		DescriptionData desc = new DescriptionData();
		p.setDescriptionData(desc );
		desc.setTitle(i.getItemName());
		desc.setBrand(null);
		desc.setManufacturer(null);
		desc.setItemType(i.getItemType());
		desc.setDescription(i.getProductDescription());
		
		//bullet points
		List<String> bulletPoints = desc.getBulletPoint();
		if(i.getBulletPoint1() !=null && i.getBulletPoint1().trim().length()>0){
			bulletPoints.add(i.getBulletPoint1());
		}
		if(i.getBulletPoint2() !=null && i.getBulletPoint2().trim().length()>0){
			bulletPoints.add(i.getBulletPoint2());
		}
		if(i.getBulletPoint3() !=null && i.getBulletPoint3().trim().length()>0){
			bulletPoints.add(i.getBulletPoint3());
		}
		if(i.getBulletPoint4() !=null && i.getBulletPoint4().trim().length()>0){
			bulletPoints.add(i.getBulletPoint4());
		}
		if(i.getBulletPoint5() !=null && i.getBulletPoint5().trim().length()>0){
			bulletPoints.add(i.getBulletPoint5());
		}
		
		//search keywords
		List<String> keyWords = desc.getSearchTerms();
		if(i.getGenericKeywords1() !=null && i.getGenericKeywords1().trim().length()>0){
			keyWords.add(i.getGenericKeywords1());
		}
		if(i.getGenericKeywords2() !=null && i.getGenericKeywords2().trim().length()>0){
			keyWords.add(i.getGenericKeywords2());
		}
		if(i.getGenericKeywords3() !=null && i.getGenericKeywords3().trim().length()>0){
			keyWords.add(i.getGenericKeywords3());
		}
		if(i.getGenericKeywords4() !=null && i.getGenericKeywords4().trim().length()>0){
			keyWords.add(i.getGenericKeywords4());
		}
		if(i.getGenericKeywords5() !=null && i.getGenericKeywords5().trim().length()>0){
			keyWords.add(i.getGenericKeywords5());
		}
		
		FashionNecklaceBraceletAnklet nba = new FashionNecklaceBraceletAnklet();
		ProductType pt = new ProductType();
		pt.setFashionNecklaceBraceletAnklet( nba );
		Jewelry jew = new Jewelry();
		jew.setProductType( pt );
		nba.setCountryOfOrigin(i.getCountryOfOrigin());
		nba.setMetalStamp(i.getMetalStamp());
		
		
		VariationData variationData = new VariationData();
		nba.setVariationData(variationData );
		variationData.setMetalType(i.getMetalType());
	}
}
