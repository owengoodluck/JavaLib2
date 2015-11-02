package com.amazonaws.mws.jaxb.validation;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import com.amazonaws.mws.jaxb.entity.AmazonEnvelope;
import com.amazonaws.mws.jaxb.entity.Product;

public class AmazonEnvlopeValidation {

	public static void validate(AmazonEnvelope envelope) throws Exception{
		JAXBContext jc = JAXBContext.newInstance(AmazonEnvelope.class);
        JAXBSource source = new JAXBSource(jc, envelope);
 
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
        Schema schema = sf.newSchema(new File("C:/Users/owen/git/wms-feeds/src/main/resources/xsd/amzn-envelope.xsd")); 
 
        Validator validator = schema.newValidator();
        validator.setErrorHandler(null);
        validator.validate(source);
	}
}
