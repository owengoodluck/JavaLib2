package com.owen.wms.web.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.entity.JewelryEntity;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AmazonProductExcelExportTest {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	@Qualifier("amazonProductService")
	private AmazonProductService amazonProductService;
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;
	
	@Test
	public void loadExcelData2DB() throws Exception {
		String excelPath = "C:/Users/owen/git/wms-web/src/test/resources/Owen_listing.xls";
//		this.amazonProductService.loadExcelData2DB(excelPath, "Necklace");
//		this.amazonProductService.loadExcelData2DB(excelPath, "Rings");//
//		this.amazonProductService.loadExcelData2DB(excelPath, "Bracelets");
		

//		String excelPath = "C:/Users/owen/Desktop/tmp/NP-39313310587-P.xls";
//		this.amazonProductService.loadExcelData2DB(excelPath, "Sheet1");
	}
	
	@Test
	public void write2Excel() throws Exception {
		String excelPath = "C:/Users/owen/Desktop/tmp/copy.xls";
		List<JewelryEntity> list = this.amazonJewelryDao.list("itemSku", true);
		this.amazonProductService.write2Excel(list,excelPath);
	}
	
	@Test
	public void getResouce() throws IOException{
		Resource resource = new ClassPathResource("test_config.xml");  
		System.out.println(resource.getFile().getAbsolutePath());
		File templateFile =new ClassPathResource("template/Flat.File.Jewelry.xls").getFile(); 
		System.out.println(templateFile.getAbsolutePath());
	}
	
}
