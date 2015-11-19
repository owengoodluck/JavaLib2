package com.amazonaws.mws.util;

import java.io.File;
import java.io.Reader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public class JaxbUtil {
	static Logger logger= Logger.getLogger(JaxbUtil.class);

	public static String toXml(Object obj){
		String xmlString = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(obj, writer);
			xmlString = writer.toString();
		} catch (JAXBException e) {
			logger.error(e.getMessage(),e);
		}
		
		return xmlString;
	}
	
	public static Object toObj(File xmlFile,Class objClass){
		Object obj = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(objClass);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			obj = jaxbUnmarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			logger.error(e.getMessage(),e);
		}
		return obj;
	}
	
	public static Object toObj(Reader fileReader,Class objClass){
		Object obj = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(objClass);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			obj = jaxbUnmarshaller.unmarshal(fileReader);
		} catch (JAXBException e) {
			logger.error(e.getMessage(),e);
		}
		
		return obj;
	}
}
