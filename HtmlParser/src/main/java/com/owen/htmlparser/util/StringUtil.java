package com.owen.htmlparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class StringUtil {
	private static Logger log = Logger.getLogger(StringUtil.class);
	
	private static String[] ignoreListArray = {"at","for","18k"};
	private static Set<String> ignoreSet = new HashSet<String>();
	
	public static String getSubString(String input, String beginString, String endString) {
		int lengthOfBeginString = beginString.length();
		int indexBegin = input.indexOf(beginString);
		int indexEnd = -1;
		if (indexBegin != -1) {
			indexBegin += lengthOfBeginString;
			indexEnd = input.indexOf(endString, indexBegin);
			if (indexEnd != -1) {
				return input.substring(indexBegin, indexEnd);
			} else {
				log.error("Can't find end tag "+endString);
			}
		} else {
			log.error("Can't find begin tag "+beginString);
		}
		return null;
	}

	public static List<String> iteratorGetSubStringList(String input,String labelStart,String labelEnd){
		List<String> subStringList = new ArrayList<String>();
		String subString=null;
		int lengthOfOriginalLabel= labelStart.length();
		int indexBegin=input.indexOf(labelStart);
		int indexEnd=-1;
		if(indexBegin < 0){
			log.warn("can't find start lable "+labelStart);
		}else{
			while( indexBegin !=-1 ){
				indexBegin+=lengthOfOriginalLabel;
				indexEnd = input.indexOf( labelEnd ,indexBegin);
				if(indexEnd !=-1 ){
					subString = input.substring(indexBegin, indexEnd);
					subStringList.add(subString);
				}else{
					log.warn("can't find original lable ");
					break;
				}
				indexBegin=input.indexOf(labelStart,indexEnd);
			}
		}
		return subStringList;
	}
	
	public static Set<String> uniqueKeyword(File sourceFile) throws Exception {
		List<String> ignoreList = Arrays.asList(ignoreListArray);
		Set<String> result = new HashSet<String>();
		FileInputStream fin = new FileInputStream(sourceFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		String str = br.readLine();
		String[] s = null;
		while (str != null) {
			s = str.split(" ");
			for (int i = 0; i < s.length; i++) {
				String key = s[i].toLowerCase().trim();
				if(!ignoreList.contains(key) && isNotNumber(key) ){
					result.add(key);
				}else{
					System.out.println(key);
				}
			}
			str = br.readLine();
		}
		br.close();
		fin.close();
		StringBuffer buf= new StringBuffer();
		int count =0;
		for(String key:result){
			buf.append(key).append(",");
			count++;
		}
		System.out.println(buf.toString());
		System.out.println("total count = "+count);
		return result;
	}
	
	private static boolean isNotNumber(String key){
		try{
			Double.valueOf(key);
			return false;
		}catch(Exception e){
			return true;
		}
	}
}
