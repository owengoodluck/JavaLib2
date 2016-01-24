package com.owen.wms.web.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.owen.wms.web.entity.JewelryEntity;

public class ExcelKeywrodsUtil {
	private static Logger log = Logger.getLogger(ExcelKeywrodsUtil.class);
	private static final String blank = " ";
	
	public static void getKeywords(File excelKeywordFile){
		List<String[]> list = ExcelUtil.readExcel(excelKeywordFile, 0, 1, 1);
		StringBuffer buf = new StringBuffer();
		int index =0;
		int groupIndex=0;
		for(String[] strArray : list){
			if(buf.length()> 50){
				index ++;
				groupIndex=index%5==0?index/5:index/5+1;
				log.info(groupIndex+"---"+buf.toString());
				if(index%5==0){
					log.info(groupIndex);
				}
				buf.delete(0, buf.length()-1);
			}
			if(strArray[0].trim().length()>1){
				buf.append(strArray[0].trim());
				buf.append(blank);
			}
		}
	}
	
	public static void setKeywords(ArrayList<JewelryEntity> prodList,File excelKeywordFile,int groupStartIndex){
		List<String[]> list = ExcelUtil.readExcel(excelKeywordFile, 0, 1, 1);
		StringBuffer buf = new StringBuffer();
		int index =0;
		int groupIndex=0;
		int prodIndex=0;
		for(String[] strArray : list){
			if(buf.length()>= 50){
				index ++;
				groupIndex=index%5==0?index/5:index/5+1;
				log.info(groupIndex+"---"+buf.toString());
				if(groupIndex >= groupStartIndex && !prodList.isEmpty()){
					//set key word for prodList
					if( prodIndex < prodList.size()){
						switch(index%5){
							case 1 : prodList.get(prodIndex).setGenericKeywords1(buf.toString().trim()); break;
							case 2 : prodList.get(prodIndex).setGenericKeywords2(buf.toString().trim()); break;
							case 3 : prodList.get(prodIndex).setGenericKeywords3(buf.toString().trim()); break;
							case 4 : prodList.get(prodIndex).setGenericKeywords4(buf.toString().trim()); break;
							case 0 : prodList.get(prodIndex).setGenericKeywords5(buf.toString().trim()); break;
							default:;
						}
					}
				}
				if( groupIndex >= groupStartIndex  && index%5==0){
					prodIndex++;
					log.info(groupIndex);
				}
				buf.delete(0, buf.length()-1);
			}
			if(strArray[0].trim().length()>1){
				buf.append(strArray[0].trim());
				buf.append(blank);
			}
		}
	}
}
