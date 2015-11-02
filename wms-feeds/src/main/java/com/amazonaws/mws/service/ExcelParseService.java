package com.amazonaws.mws.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelParseService {

	public static List<String[]> getSheet(String excelFilePath, int sheetNumber,int totalColumns) {
		List<String[]> rowList = new ArrayList<String[]>();
		Workbook workbook = null;
		Cell cell = null;
		Sheet sheet = null;
		try {
			workbook = Workbook.getWorkbook(new File(excelFilePath));
			sheet = workbook.getSheet(sheetNumber);
			int totalRows = sheet.getRows();
			for (int row = 0; row < totalRows; row++) {
				String[]  rowArray = new String[totalColumns];
				for(int col=0;col<totalColumns;col++){
					cell = sheet.getCell(col, row);
					String value =null;
					if (cell.getType() == CellType.LABEL){
						LabelCell lc = (LabelCell) cell;
						value = lc.getString().trim();
					}
					rowArray[col] = value;
				}
				if( row>2 && rowArray[0]==null){//break if sku is null
					break;
				}
				rowList.add(rowArray);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rowList;
	}

}
