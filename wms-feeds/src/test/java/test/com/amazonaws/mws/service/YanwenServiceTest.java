package test.com.amazonaws.mws.service;

import org.junit.Ignore;
import org.junit.Test;

import com.amazonaws.mws.config.Owen;
import com.amazonaws.mws.entity.yanwen.ExpressType;
import com.amazonaws.mws.entity.yanwen.GoodsName;
import com.amazonaws.mws.entity.yanwen.Receiver;
import com.amazonaws.mws.service.YanwenService;
import com.amazonaws.mws.util.JaxbUtil;

public class YanwenServiceTest {
	private String downloaFolde = "C:/Users/owen/Desktop/tmp";
	private String epCode = "11641321902";
	private YanwenService service = new YanwenService ();
	
	@Test
	public void testGetChannels(){
		System.out.println(this.service.getChannels());
	}
	
	@Test
	public void testQueryStatus(){
		System.out.println(this.service.queryStatus(1,epCode));
	}
	
	@Test
	public void testDownloadLable(){
		this.service.downloadLabel(epCode,downloaFolde);
	}
	
	/**
	 * <ChannelType>
			<Id>105</Id>
			<Name>中邮北京平邮小包</Name>
			<Status>true</Status>
		</ChannelType>
	 */
	@Test
//	@Ignore
	public void testCreateExpress(){
		ExpressType et  = new ExpressType ();
		et.setUserid(Owen.yanwenUserId);
		et.setChannel("中邮北京平邮小包");
		et.setUserOrderNumber("test-6");
		et.setSendDate("2015-11-19T00:00:00");//2014-07-09T00:00:00
		et.setQuantity(1);
		
		Receiver rc = new Receiver();
		et.setReceiver(rc );
		rc.setUserid(Owen.yanwenUserId);
		rc.setName("name");
		rc.setPhone("815-718-3860");
		rc.setCountry("美国");
		rc.setState("AM");
		rc.setCity("NewYork");
		rc.setAddress1(" address 1 ");
		rc.setPostcode("1234546");
		
		GoodsName gn = new  GoodsName();
		et.setGoodsName(gn );
		gn.setUserid(Owen.yanwenUserId);
		gn.setNameCh("不锈钢饰品 吊坠");
		gn.setNameEn("Stainless Steel Necklace Pendant");
		gn.setDeclaredValue(123);
		gn.setDeclaredCurrency("USD");
		gn.setWeight(60);
		
		System.out.println(JaxbUtil.toXml(et));
		this.service.createExpress( et  );
	}
}
