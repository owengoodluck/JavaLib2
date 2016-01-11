package com.owen.wms.web.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.wms.web.entity.YanWenExpressEntity;
import com.owen.wms.web.form.ExpressQueryForm;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class YanWenExpressDaoTest {

	private SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	@Qualifier("yanWenExpressDao")
	private YanWenExpressDao dao ;
	
	@Test
	public void testSave(){
		YanWenExpressEntity entity = new YanWenExpressEntity();
		entity.setEpcode("1");
		entity.setChannel("荷兰邮政挂号小包(不含电)");
		this.dao.saveOrUpdate(entity );
	}

	@Test
	public void testPageQuery() throws Exception{
		ExpressQueryForm queryForm = new ExpressQueryForm();
		queryForm.setSendDateFrom("2015-12-29");
		Page page = this.dao.pageListByCriteria(queryForm );
		List<YanWenExpressEntity> list = page.getList();
		for(YanWenExpressEntity e: list){
			System.out.println(e.getEpcode());
		}
		System.out.println(page.getTotalCount());
	}
	
	@Test
	public void testGetByAmazonOrderId(){
		Assert.assertNull(this.dao.getByAmazonOrderId("xxx"));
		Assert.assertNotNull(this.dao.getByAmazonOrderId("104-1073592-2461824"));
	}
	
	
}
