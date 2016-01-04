package com.owen.wms.web.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.Test;

import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.utils.NullAwareBeanUtilsBean;

import junit.framework.Assert;


public class PropertiesCopyUtilTest {

	@Test
	public void testCopy(){
		NullAwareBeanUtilsBean util = new NullAwareBeanUtilsBean();
		JewelryEntity s = new JewelryEntity();
		s.setItemSku("sku");
		s.setCreateDate(new Date());
		s.setStandardPrice(1.2);
		JewelryEntity t = new JewelryEntity();
		t.setBulletPoint1("xxx");
		try {
			util.copyProperties(t, s);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(s.getItemSku(), t.getItemSku());
		Assert.assertEquals(s.getCreateDate(), t.getCreateDate());
		Assert.assertEquals(s.getStandardPrice(), t.getStandardPrice());
		Assert.assertEquals("xxx",t.getBulletPoint1());
		Assert.assertNull(t.getBulletPoint2());
	}
}
