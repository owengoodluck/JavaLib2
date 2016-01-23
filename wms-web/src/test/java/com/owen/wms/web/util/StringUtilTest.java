package com.owen.wms.web.util;

import org.junit.Test;

import com.owen.htmlparser.util.StringUtil;

import junit.framework.Assert;

public class StringUtilTest {

	@Test
	public void test(){
		String a = "\"http://cbu01.alicdn.com/img/ibank/2014/419/471/1834174914_514610680.jpg\"";
		Assert.assertTrue(a.startsWith("\"http:"));
	}
	
	@Test
	public void test1(){
		String url = "http://www.amazon.com/Dolphins-Swarovski-Zirconia-Crystal-Necklace/dp/B00NQVXCCS/ref=sr_1_126?ie=UTF8&qid=1453477732&sr=8-126&keywords=crystal+necklace+for+women";
		String asin = StringUtil.getAmazonASINFromUrl(url);
		System.out.println(asin);
		Assert.assertEquals("B00NQVXCCS", asin);
	}
}
