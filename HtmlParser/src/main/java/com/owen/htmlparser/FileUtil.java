package com.owen.htmlparser;

import java.io.File;

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
}
