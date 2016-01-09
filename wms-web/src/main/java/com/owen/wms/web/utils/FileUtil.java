package com.owen.wms.web.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

	/**
	 * write string into file
	 * @param input
	 * @param outputFile
	 * @throws IOException 
	 */
	public static void writeStringToFile(String input, File outputFile) throws IOException {
		BufferedWriter bufWriter = null;
		try {
			bufWriter = new BufferedWriter(new FileWriter(outputFile));
			bufWriter.write(input);
			bufWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}finally{
			try {
				if(bufWriter!=null){
					bufWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
