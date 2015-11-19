package test.com.amazonaws.mws.jaxb;

import java.io.File;
import java.io.StringReader;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.amazonaws.mws.util.JaxbUtil;
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
}
