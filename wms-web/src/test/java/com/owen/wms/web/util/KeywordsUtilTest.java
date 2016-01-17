package com.owen.wms.web.util;

import java.io.File;

import org.junit.Test;

import com.owen.wms.web.utils.ExcelKeywrodsUtil;

public class KeywordsUtilTest {

	@Test
	public void test(){
		File keywordsExcel = new File("C:/Users/owen/Desktop/Amazon/necklace-pendant-sort-highest.xls");
		ExcelKeywrodsUtil.getKeywords(keywordsExcel);
	}
}
