package com.owen.htmlparser.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtil {
	private static Logger log = Logger.getLogger(ExcelUtil.class);

	public static Set<String> getSheet(String excelFilePath, int sheetNumber) {
		Set<String> skuSet = new HashSet<String>();
		Workbook workbook = null;
		Cell cell = null;
		Sheet sheet = null;
		try {
			workbook = Workbook.getWorkbook(new File(excelFilePath));
			sheet = workbook.getSheet(sheetNumber);
			int rows = sheet.getRows();
			String sku=null;
			for (int row = 3; row < rows; row++) {
				cell = sheet.getCell(0, row);
				if (cell.getType() == CellType.LABEL) {
					LabelCell lc = (LabelCell) cell;
					sku=lc.getString().trim(); //BL-OPK-PH905 or OPK-PH855 or BL-OPK-PH792-P or BL-OPK-PH792
					if(sku.endsWith("P")){
						continue;
					}else{
						String[] items = sku.split("-");
						skuSet.add(items[items.length-1]);
					}
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("There are "+skuSet.size()+" sku found in "+excelFilePath+" sheet["+sheetNumber+"]:"+sheet.getName());
		return skuSet;
	}
}
