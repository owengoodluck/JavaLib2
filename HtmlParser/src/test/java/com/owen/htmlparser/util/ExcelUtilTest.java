package com.owen.htmlparser.util;
import java.util.Set;

import org.junit.Test;

import com.owen.htmlparser.util.ExcelUtil;

public class ExcelUtilTest {
	@Test
	public void test1(){
		String excelFilePath="C:/Users/owen/Documents/����ѷ-��ɾ/Owen_listing.xls";
		
		Set<String> skuSet=ExcelUtil.getSheet(excelFilePath, 1);
		
		for(String str:skuSet){
			System.out.println(str);
		}
		
	}
}
