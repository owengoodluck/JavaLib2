package com.owen.htmlparser.util;
import java.io.File;

import org.junit.Test;

import com.owen.htmlparser.util.StringUtil;

public class KeywordsTest {

	@Test
	public void testGetKeyWord() throws Exception{
		File file=new File("C:/Users/owen/git/HtmlParser/src/test/resources/keywords.txt");
		StringUtil.uniqueKeyword(file);
	}
}
