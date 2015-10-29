package com.amazonaws.mws.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
}
