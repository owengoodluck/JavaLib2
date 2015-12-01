package com.owen.wms.web.utils;

public class StringUtils {
	public static String deleteUnderscoreAndInitalNext(String input){
		StringBuffer buf = new StringBuffer();
		if(input!=null){
			int start =0;
			int index = input.indexOf("_");
			while(index!=-1){
				buf.append(input.substring(start,index));
				char next = input.charAt(index+1);
				buf.append(String.valueOf(next).toUpperCase());
				start = index+2;
				index = input.indexOf("_",start);
			}
			buf.append(input.substring(start));
			return buf.toString();
		}
		return input;
	}
}
