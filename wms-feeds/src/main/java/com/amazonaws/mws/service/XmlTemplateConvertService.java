package com.amazonaws.mws.service;

import java.math.BigInteger;
import java.util.List;

import com.amazonaws.mws.config.DocumentVersion;
import com.amazonaws.mws.config.MessageType;
import com.amazonaws.mws.config.Owen;
import com.amazonaws.mws.config.ProductOperationType;
import com.amazonaws.mws.jaxb.entity.AmazonEnvelope;
import com.amazonaws.mws.jaxb.entity.AmazonEnvelope.Message;
import com.amazonaws.mws.jaxb.entity.Header;
import com.amazonaws.mws.jaxb.entity.Product;
import com.amazonaws.mws.jaxb.entity.Product.DescriptionData;

public class XmlTemplateConvertService {
	private static final int columnCount=220;
	private static final int sheetNumber=3;
	
	public static AmazonEnvelope convert2Envelope(String excelFilePath,int sheetNumber){
		AmazonEnvelope env = new AmazonEnvelope();
		List<String[]> list = ExcelParseService.getSheet(excelFilePath, sheetNumber, columnCount);
		if(list!=null && list.size()>3){
			//message list
			List<Message> messageList = env.getMessage(); 
			for(int i =3;i<list.size();i++){
				String[] row = list.get(i);
				
				//header
				Header header = new Header();
				env.setHeader(header );
				header.setMerchantIdentifier(Owen.sellerId);
				header.setDocumentVersion(DocumentVersion.version1_01.toString());
//				header.setOverrideReleaseId(value);
				
				//effectivedate
//				GregorianCalendar c = new GregorianCalendar();
//				c.setTime(new Date());
//				XMLGregorianCalendar effectiveDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
//				env.setEffectiveDate(effectiveDate );
				
				//message type
				env.setMessageType(MessageType.Product.toString());
				
				//message list
				Message msg = convert2Message(row);
				msg.setMessageID(BigInteger.valueOf(i-2));
				messageList.add(msg);
			}
		}
		return env;
	
	}
	public static AmazonEnvelope convert2Envelope(String excelFilePath){
		return convert2Envelope(excelFilePath,sheetNumber);
	}
	
	private static Message convert2Message(String[] row){
		Message msg = new Message();
		msg.setOperationType(ProductOperationType.PartialUpdate.toString());
		msg.setProduct(convert2Product(row));
		return msg;
	}
	
	private static Product convert2Product(String[] row){
		Product p = new Product();
		p.setSKU(row[0]);
		
		DescriptionData descriptionData = new DescriptionData();
		descriptionData.setTitle(row[1]);
		p.setDescriptionData( descriptionData );
		
		return p;
	}
	
}
