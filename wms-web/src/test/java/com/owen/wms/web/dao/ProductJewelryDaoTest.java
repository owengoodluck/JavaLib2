package com.owen.wms.web.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.wms.web.entity.ProductJewelryEntity;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class ProductJewelryDaoTest {
	
	@Autowired
	@Qualifier("productJewelryDao")
	ProductJewelryDao dao ;
	
	@Test
	public void testSave(){
		ProductJewelryEntity entity = new ProductJewelryEntity();
		entity.setItemSku("112");
		System.out.println(this.dao.save(entity));
	}
	
	@Test
	public void testSaveOrUpate(){
		ProductJewelryEntity entity = new ProductJewelryEntity();
		entity.setItemSku("112");
		this.dao.saveOrUpdate(entity);
	}
	
	@Test
	public void testList(){
		List<ProductJewelryEntity> list = dao.list();
		System.out.println(list.size());
	}
}
