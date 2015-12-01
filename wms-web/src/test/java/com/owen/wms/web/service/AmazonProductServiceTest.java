package com.owen.wms.web.service;

import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

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
public class AmazonProductServiceTest {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	@Qualifier("amazonProductService")
	private AmazonProductService amazonProductService;
	
	@Test
	public void loadExcelData2DB() throws Exception {
		String excelPath = "C:/Users/owen/git/wms-web/src/test/resources/Owen_listing.xls";
		this.amazonProductService.loadExcelData2DB(excelPath, "Necklace");
	}
	
	@Test
	public void write2Excel() throws Exception {
		String excelPath = "C:/Users/owen/git/wms-web/src/test/resources/copy.xls";
		this.amazonProductService.write2Excel(excelPath);
	}
	
	
}
