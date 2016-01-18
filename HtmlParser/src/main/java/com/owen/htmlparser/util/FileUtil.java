package com.owen.htmlparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger log = Logger.getLogger(FileUtil.class);

	public static File createSubFolder(String downloadRootFolder) {
		File rootFolder = new File(downloadRootFolder);
		File subFolder = null;
		if (!rootFolder.exists()) {
			rootFolder.mkdir();
		}
		String[] list = rootFolder.list();
		if (list == null || list.length == 0) {
			subFolder = new File(downloadRootFolder + "/1");
		} else {
			subFolder = new File(downloadRootFolder + "/" + (list.length + 1));
		}
		subFolder.mkdir();
		return subFolder;
	}

	public static void write2File(String content, File file) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(content);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void write2File(String content, String filePath, String... extensions) {
		if (extensions != null && extensions.length > 0) {
			for (String ext : extensions) {
				if ("url".equals(ext)) {
					createInternetShortcut(filePath+".url"  ,content);
				} else {
					write2File(content, new File(filePath + "." + ext));
				}
			}
		} else {
			write2File(content, new File(filePath));
		}
	}

	public static void createInternetShortcut( String filePath, String target) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filePath);
			fw.write("[InternetShortcut]\r\n");
			fw.write("URL=" + target + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile2String( String filePath) {
		StringBuffer buf = new StringBuffer();
		FileInputStream fin = null;
		BufferedReader br = null;
		try{
			fin = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(fin));
			String str = br.readLine();
			while (str != null) {
				buf.append(str);
				str = br.readLine();
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			return null;
		}finally{
			try {
				if(br!=null ) br.close();
			} catch (IOException e) { }
			try {
				if(fin!=null ) fin.close();
			} catch (IOException e) { }
		}
		return buf.toString();
	}

	public static void copy(String oldPath, String newPath){
		copy(new File(oldPath), new File(newPath));
	}
	
	public static void copy(File oldfile, File newFile) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int byteread = 0;
			// File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				inStream = new FileInputStream(oldfile);
				fs = new FileOutputStream(newFile);
				byte[] buffer = new byte[4096];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
			}
			fs.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
		}finally{
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e) { }
			}
			if(inStream!=null){
				try {
					inStream.close();
				} catch (IOException e) { }
			}
		}
	}
}
