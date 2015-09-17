package com.owen.htmlparser;

public class StringUtil {

	public static String getSubString(String input,String beginString,String endString){
		int lengthOfBeginString= beginString.length();
		int indexBegin=input.indexOf(beginString);
		int indexEnd=-1;
		if(indexBegin!=-1){
			indexBegin+=lengthOfBeginString;
			indexEnd = input.indexOf( endString, indexBegin);
			if(indexEnd!=-1){
				return input.substring(indexBegin,indexEnd);
			}else{
				System.out.println("Can't find end tag");
			}
		}else{
			System.out.println("Can't find begin tag");
		}
		return null;
	}
}
