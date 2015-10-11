package com.owen.htmlparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtil {
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
				System.out.println("Can't find end tag");
			}
		} else {
			System.out.println("Can't find begin tag");
		}
		return null;
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
