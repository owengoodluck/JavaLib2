import org.apache.log4j.Logger;
import org.junit.Test;

import com.amazonaws.mws.entity.jaxb.AmazonEnvelope;
import com.amazonaws.mws.util.JaxbUtil;

public class JaxbUtilTest {
	private Logger logger= Logger.getLogger(this.getClass());
	
	@Test
	public void test(){
		AmazonEnvelope ae = new AmazonEnvelope();
		ae.setMarketplaceName("test");
		String xmlString=JaxbUtil.toXml(ae);
		this.logger.info("\n"+xmlString);
	}
}
