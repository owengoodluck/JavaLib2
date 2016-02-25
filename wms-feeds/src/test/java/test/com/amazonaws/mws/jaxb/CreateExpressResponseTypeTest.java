package test.com.amazonaws.mws.jaxb;

import java.io.File;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.mws.config.Owen;
import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.amazonaws.mws.jaxb.entity.AmazonEnvelope;
import com.amazonaws.mws.jaxb.entity.AmazonEnvelope.Message;
import com.amazonaws.mws.jaxb.entity.Header;
import com.amazonaws.mws.jaxb.entity.OrderFulfillment;
import com.amazonaws.mws.jaxb.entity.OrderFulfillment.FulfillmentData;
import com.amazonaws.mws.jaxb.entity.OrderFulfillment.Item;
import com.amazonaws.mws.util.JaxbUtil;
import com.amazonservices.mws.orders._2013_09_01.util.XMLGregorianCalendarUtil;
import com.owen.htmlparser.util.FileUtil;

public class CreateExpressResponseTypeTest {
	private Logger logger= Logger.getLogger(this.getClass());
	
	private String resposneSuccessXMLFilePath="C:/Users/owen/git/wms-feeds/src/test/resources/yanwen_create_response_success.xml";
	private String resposneFailXMLFilePath=   "C:/Users/owen/git/wms-feeds/src/test/resources/yanwen_create_response_fail.xml";
	
	
	@Test
	public void testRespSuccess(){
		String xml = FileUtil.readFile2String(resposneSuccessXMLFilePath);
		CreateExpressResponseType obj = (CreateExpressResponseType) JaxbUtil.toObj(new StringReader(xml), CreateExpressResponseType.class);
		Assert.assertTrue(obj.isCallSuccess());
	}
	
	@Test
	public void testRespFail(){
		CreateExpressResponseType obj = (CreateExpressResponseType) JaxbUtil.toObj(new File(resposneFailXMLFilePath), CreateExpressResponseType.class);
		Assert.assertTrue(!obj.isCallSuccess());
	}
	
	@Test
	public void testOrderFulfillmentFeed(){
		AmazonEnvelope e = this.createEnvelop4OrderShip();
		System.out.println(JaxbUtil.toXml(e));
		
	}
	public OrderFulfillment getOrderFulfillmentFeed(){
    	String xml = null;
    	OrderFulfillment of = new OrderFulfillment();
    	of.setAmazonOrderID("102-8616663-2297045");
    	XMLGregorianCalendar fulfillmentDate = XMLGregorianCalendarUtil.dateToXmlDate(new Date());
		of.setFulfillmentDate(fulfillmentDate );
    	FulfillmentData fulfillmentData = new FulfillmentData();
    	//You can use CarrierName instead of CarrierCode if the list of options for CarrierCode (in the base XSD) does not contain the carrier you used.
    	//fulfillmentData.setCarrierCode("China Post");
    	fulfillmentData.setCarrierName("China Post");
    	fulfillmentData.setShipperTrackingNumber("11806100967");
    	//fulfillmentData.setShippingMethod("");//TODO TBC
		of.setFulfillmentData(fulfillmentData);
		
		Item item  = new Item();
		item.setAmazonOrderItemCode("16040570009458");
		item.setQuantity(BigInteger.valueOf(1));
		of.getItem().add(item  );
    	return of;
    }
	
	private AmazonEnvelope createEnvelop4OrderShip(){
		AmazonEnvelope e = new AmazonEnvelope();
		//1. set header
		Header header  = new Header();
		e.setHeader(header  );
		header.setMerchantIdentifier(Owen.sellerId);
		header.setDocumentVersion("1.01");
		
		//2.set MessageType
		e.setMessageType("OrderFulfillment");
		
		//3. set message
		Message msg = new Message();
		e.getMessage().add(msg );
		msg.setMessageID(BigInteger.valueOf(1));
		msg.setOrderFulfillment(getOrderFulfillmentFeed());
		
		return e;
	}
}
