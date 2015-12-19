package com.owen.wms.web.dao;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AmazonOrderDaoTest {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	@Qualifier("amazonOrderDao")
	private AmazonOrderDao dao ;
	
	@Test
	@Ignore
	public void testSave(){
		AmazonOrder ao =new AmazonOrder();
		ao.setAmazonOrderId("123");
		
		Set<AmazonOrderItem> aoiSet  = new HashSet<AmazonOrderItem>();
		AmazonOrderItem aoi =new AmazonOrderItem();
		aoi.setASIN("a1");
		aoi.setSellerSKU("sku1");
		aoi.setOrder(ao);
		aoi.setOrderItemId("1");
		aoiSet.add(aoi );
		
		ao.setOrderItemList(aoiSet);
		this.dao.save(ao);
	}
	
	@Test
	@Ignore
	public void testSaveOrUpdate(){
		AmazonOrder ao =new AmazonOrder();
		ao.setAmazonOrderId("123");
		
		Set<AmazonOrderItem> aoiSet  = new HashSet<AmazonOrderItem>();
		AmazonOrderItem aoi =new AmazonOrderItem();
		aoi.setASIN("a1");
		aoi.setSellerSKU("sku1");
		aoi.setOrderItemId("1");
		aoi.setOrder(ao);
		aoiSet.add(aoi );
		
		ao.setOrderItemList(aoiSet);
		this.dao.saveOrUpdate(ao);
	}
	
	@Test
	public void testGetByOrderID(){
//		AmazonOrder result = this.dao.getByOrderID("123");
//		Assert.assertNull(result);
		AmazonOrder result1 = this.dao.getByOrderID("106-9264650-3211447");
		System.out.println("-----"+result1.getOrderItemList().size());
		if(result1.getOrderItemList()!=null){
			for(AmazonOrderItem item : result1.getOrderItemList()){
				System.out.println("-----"+item.getSellerSKU());
			}
		}
		Assert.assertNotNull(result1);
	}
	

	@Test
	public void testListAll() throws Exception{
		List<AmazonOrder> list = this.dao.list("purchaseDate",false);
		if(list!=null){
			for(AmazonOrder od: list){
				System.out.println(od);
			}
		}
	}
	
}
