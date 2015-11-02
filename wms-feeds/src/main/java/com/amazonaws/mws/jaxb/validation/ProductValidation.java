package com.amazonaws.mws.jaxb.validation;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import com.amazonaws.mws.jaxb.entity.Product;

public class ProductValidation {

	public static void validate(Product product) throws Exception{
		JAXBContext jc = JAXBContext.newInstance(Product.class);
        JAXBSource source = new JAXBSource(jc, product);
 
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
        Schema schema = sf.newSchema(new File("C:/Users/owen/git/wms-feeds/src/main/resources/xsd/Product.xsd")); 
 
        Validator validator = schema.newValidator();
        validator.setErrorHandler(null);
        validator.validate(source);
	}
}
