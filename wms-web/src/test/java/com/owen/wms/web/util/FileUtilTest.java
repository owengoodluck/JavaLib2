package com.owen.wms.web.util;

import java.io.File;

import org.junit.Test;

import com.owen.wms.web.utils.FileUtil;

public class FileUtilTest {

	@Test
	public void testWriteString2File(){
		String input ="<xml>test123</xml>";
		File outputFile = new File("C:/Users/owen/git/wms-web/src/test/resources/testWriteString2File.xml");
		FileUtil.writeStringToFile(input , outputFile );
	}
}
