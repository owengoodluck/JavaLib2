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

import com.owen.wms.web.entity.AmazonOrder;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AmazonOrderDaoTest {
	
	@Autowired
	@Qualifier("amazonOrderDao")
	AmazonOrderDao dao ;
	
	@Test
	public void testGetByOrderID(){
		AmazonOrder result = this.dao.getByOrderID("123");
		Assert.assertNull(result);
	}
	

	@Test
	public void testListAll() throws Exception{
		List<AmazonOrder> list = this.dao.list();
		if(list!=null){
			for(AmazonOrder od: list){
				System.out.println(od.getIsBusinessOrder());
			}
		}
	}
	
}
