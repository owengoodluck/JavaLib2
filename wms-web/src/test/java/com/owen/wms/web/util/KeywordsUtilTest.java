package com.owen.wms.web.util;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.utils.ExcelKeywrodsUtil;

public class KeywordsUtilTest {

	@Test
	public void test(){
		File keywordsExcel = new File("C:/Users/owen/Desktop/crystal-necklace-sort-highest.xls");
		ExcelKeywrodsUtil.getKeywords(keywordsExcel);
	}
	
	@Test
	public void test2(){
		File keywordsExcel = new File("C:/Users/owen/Desktop/crystal-necklace-sort-highest.xls");
		ArrayList<JewelryEntity> prodList = new ArrayList();
		prodList.add(new JewelryEntity());
		prodList.add(new JewelryEntity());
		prodList.add(new JewelryEntity());
		ExcelKeywrodsUtil.setKeywords(prodList , keywordsExcel, 5);
		for(JewelryEntity e : prodList){
			System.out.println(e.getGenericKeywords1());
			System.out.println(e.getGenericKeywords2());
			System.out.println(e.getGenericKeywords3());
			System.out.println(e.getGenericKeywords4());
			System.out.println(e.getGenericKeywords5());
			System.out.println("-------------------------------");
		}
	}
}
