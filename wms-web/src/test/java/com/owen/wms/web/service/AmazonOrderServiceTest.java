package com.owen.wms.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AmazonOrderServiceTest {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	@Qualifier("amazonOrderService")
	private AmazonOrderService amazonOrderService;
	
	@Test
	public void getOrderList() {
		this.amazonOrderService.getOrderList();
	}
	
	@Test
	@Ignore
	public void testSynchronizeOrderToLocalDB() throws Exception{
		Date createdAfterDate = this.sdf.parse("20151119");
		String orderStatus = null; 
		Date createdBeforeDate =null;
		this.amazonOrderService.synchronizeOrderToLocalDB(createdAfterDate,createdBeforeDate,orderStatus);
	}
	
	
}
