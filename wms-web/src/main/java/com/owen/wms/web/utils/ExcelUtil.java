package com.owen.wms.web.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtil {

	public static List<String[]> readExcel(File excelFile,Integer sheetNumber,Integer totalColumns,Integer ignoreHeadRows ){
		
		List<String[]> rows = new ArrayList<String[]>();
		try {
			//1. get book
			Workbook book = Workbook.getWorkbook(excelFile);
			
			//2. get columns name
			Sheet st = book.getSheet(sheetNumber);
			if(totalColumns == null || totalColumns == 0){
				totalColumns= st.getColumns();
			}
			if(ignoreHeadRows == null  ){
				ignoreHeadRows =0;
			}
			int totalRows = st.getRows();
			
			//3.iterate
			for(int row =ignoreHeadRows;row<totalRows;row ++ ){
				String[] rowContent = new String[totalColumns];
				for(int col=0;col<totalColumns;col++){
					rowContent[col] = st.getCell(col, row).getContents();
				}
				rows.add(rowContent);
			}
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		return rows;
	}
}
