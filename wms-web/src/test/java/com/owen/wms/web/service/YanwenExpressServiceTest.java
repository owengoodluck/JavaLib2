package com.owen.wms.web.service;

import org.junit.Test;

import com.owen.wms.web.form.YanwenExpress;

public class YanwenExpressServiceTest {

	private YanwenExpressService service = new YanwenExpressService();
	
	@Test
	public void test() throws Exception{
		YanwenExpress express  = new YanwenExpress();
		express.setAmazonOrderID("111-2651851-0898603");
		this.service.createExpressFromAmazonOrder( express  );
	}
}
