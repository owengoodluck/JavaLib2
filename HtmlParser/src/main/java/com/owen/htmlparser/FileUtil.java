package com.owen.htmlparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

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
					try {
						createInternetShortcut(filePath+".url"  ,content);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					write2File(content, new File(filePath + "." + ext));
				}
			}
		} else {
			write2File(content, new File(filePath));
		}
	}

	public static void createInternetShortcut( String filePath, String target)
			throws IOException {
		FileWriter fw = new FileWriter(filePath);
		fw.write("[InternetShortcut]\r\n");
		fw.write("URL=" + target + "\n");
		fw.flush();
		fw.close();
	}
}
