package com.owen.wms.web.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getUTCtime(){
		// 1、取得本地时间：  
        Calendar cal = Calendar.getInstance() ;  
        // 2、取得时间偏移量：  
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);  
        // 3、取得夏令时差：  
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);  
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：  
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));  
        
		return cal.getTime();
	}
	
	public static void main(String[] args){
		System.out.println(getUTCtime());
	}
}
