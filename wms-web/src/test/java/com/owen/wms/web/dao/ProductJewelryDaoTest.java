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

import com.owen.wms.web.entity.JewelryEntity;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class ProductJewelryDaoTest {
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	AmazonJewelryDao dao ;
	
	@Test
	public void testSave(){
		JewelryEntity entity = new JewelryEntity();
		entity.setItemSku("112");
		System.out.println(this.dao.save(entity));
	}
	
	@Test
	public void testSaveOrUpate(){
		JewelryEntity entity = new JewelryEntity();
		entity.setItemSku("112");
		this.dao.saveOrUpdate(entity);
	}
	
	@Test
	public void testList(){
		List<JewelryEntity> list = dao.list();
		System.out.println(list.size());
	}
	
	@Test
	public void listAllParent(){
		List<JewelryEntity> list = dao.listAllParent();
		if(list!=null && !list.isEmpty()){
			for(JewelryEntity e:list){
				System.out.println(e.getItemSku()+"---"+e.getParentChild());
//				Assert.assertNull(e.getParentSku());
			}
		}
	}
	
	@Test
	public void findBySKU(){
		String sku = "BL-1001-P";
//		sku="BL-1001-CB1";
		List<JewelryEntity> list = dao.findBySKU(sku);
		System.out.println(list.size());
		for(JewelryEntity ent:list){
			System.out.println(ent.getItemSku()+"----------"+ent.getParentSku());
		}
	}
}
