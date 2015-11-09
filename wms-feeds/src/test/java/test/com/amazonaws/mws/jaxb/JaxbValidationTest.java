package test.com.amazonaws.mws.jaxb;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.amazonaws.mws.jaxb.entity.AmazonEnvelope;
import com.amazonaws.mws.jaxb.entity.Product;
import com.amazonaws.mws.jaxb.entity.StandardProductID;
import com.amazonaws.mws.jaxb.validation.AmazonEnvlopeValidation;
import com.amazonaws.mws.jaxb.validation.ProductValidation;
import com.amazonaws.mws.service.JewelryExcelTemplateConvertService;
import com.amazonaws.mws.util.JaxbUtil;

public class JaxbValidationTest {
	private Logger logger= Logger.getLogger(this.getClass());
	
	@Test
	public void testValidation(){
		Product p = new Product();
		p.setSKU("NP-520902601166-O");
		StandardProductID standardProductID = new StandardProductID();
		standardProductID.setType("UPC");
		standardProductID.setValue("12345678");
		p.setStandardProductID(standardProductID );
		String xmlString=JaxbUtil.toXml(p);
		this.logger.info("\n"+xmlString);
		
		try {
			ProductValidation.validate(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void envelopeValidationTest() throws Exception{
		AmazonEnvlopeValidation val = new AmazonEnvlopeValidation();
		String excelFilePath="C:/Users/owen/Desktop/Amazon/Jewelry_format.xls";
		AmazonEnvelope envelope = JewelryExcelTemplateConvertService.convert2Envelope(excelFilePath,0);
		String xmlString=JaxbUtil.toXml(envelope);
		System.out.println(xmlString);
		val.validate(envelope );
	}
}
