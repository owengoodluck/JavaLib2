package com.owen.wms.web.service;

import org.junit.Test;

public class YanwenExpressServiceTest {

	private YanwenExpressService service = new YanwenExpressService();
	
	@Test
	public void test(){
		this.service.createExpressFromAmazonOrder("111-2651851-0898603", "中邮北京平邮小包");
	}
}
