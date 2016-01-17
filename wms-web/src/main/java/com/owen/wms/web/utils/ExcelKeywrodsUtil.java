package com.owen.wms.web.utils;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

public class ExcelKeywrodsUtil {
	private static Logger log = Logger.getLogger(ExcelKeywrodsUtil.class);

	public static void getKeywords(File excelKeywordFile){
		List<String[]> list = ExcelUtil.readExcel(excelKeywordFile, 0, 1, 1);
		StringBuffer buf = new StringBuffer();
		String blank = " ";
		int index =0;
		for(String[] strArray : list){
			if(buf.length()> 50){
				index ++;
				if(index%6==0){
					log.info(index++);
				}
				log.info(buf.toString());
				buf.delete(0, buf.length()-1);
			}
			if(strArray[0].trim().length()>1){
				buf.append(strArray[0].trim());
				buf.append(blank);
			}
		}
	}
}
