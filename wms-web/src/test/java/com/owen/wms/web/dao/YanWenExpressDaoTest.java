package com.owen.wms.web.dao;

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

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class YanWenExpressDaoTest {
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
}
