package com.owen.htmlparser.util;
import java.io.File;
import java.util.Set;


import org.junit.Test;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtilTest {
	@Test
	public void test1(){
		String excelFilePath="C:/Users/owen/Documents/亚马逊-勿删/Owen_listing.xls";
		
		Set<String> skuSet=ExcelUtil.getSheet(excelFilePath, 1);
		
		for(String str:skuSet){
			System.out.println(str);
		}
	}
	
	@Test
	public void testGenerate() throws Exception{
		String excelFilePath="C:/Users/owen/Documents/test.xls";
		ExcelUtil.generateExcel(excelFilePath);
	}
	
	@Test
	public void testCopyAndUpdate()throws Exception{
		Workbook sourceBook = Workbook.getWorkbook(new File("C:/Users/owen/git/wms-feeds/src/main/resources/template/JewelryTemplate.xls"));
		WritableWorkbook targetBoook = Workbook.createWorkbook(new File("C:/Users/owen/git/wms-feeds/src/main/resources/sample/copy.xls"),sourceBook);
		WritableSheet sheet1 = targetBoook.getSheet(0); 
		sheet1.addCell(new Label( 0,3,"123456" ));
		targetBoook.write();
		targetBoook.close();
		
		sourceBook.close();
	}
	
	@Test
	public void testGetColumns()throws Exception{
		Workbook book = Workbook.getWorkbook(new File("C:/Users/owen/git/wms-feeds/src/main/resources/template/JewelryTemplate.xls"));
		Sheet st = book.getSheet(0);
		int totalColumns= st.getColumns();
		for(int col=0;col<totalColumns;col++){
			Cell cell = st.getCell(col, 2);
			System.out.println("@Column(name=\""+cell.getContents()+"\")");
			System.out.println("private String "+deleteUnderscoreAndInitalNext(cell.getContents())+";");
			System.out.println();
		}
		book.close();
	}
	
	private String deleteUnderscoreAndInitalNext(String input){
		StringBuffer buf = new StringBuffer();
		if(input!=null){
			int start =0;
			int index = input.indexOf("_");
			while(index!=-1){
				buf.append(input.substring(start,index));
				char next = input.charAt(index+1);
				buf.append(String.valueOf(next).toUpperCase());
				start = index+2;
				index = input.indexOf("_",start);
			}
			buf.append(input.substring(start));
			return buf.toString();
		}
		return input;
	}
	
	@Test
	public void test(){
		this.updateExcel(new File("C:/Users/owen/git/wms-feeds/src/main/resources/sample/Jewelry_template.xls"), "sheet1");
	}
	
	public void updateExcel(File sourceXlsFile, String sheetName){  
        try {  
            Workbook rwb = Workbook.getWorkbook(sourceXlsFile);//原xls文件  
            WritableWorkbook wwb = Workbook.createWorkbook(sourceXlsFile, rwb);//临时xls文件  
            WritableSheet sheet = wwb.getSheet(sheetName);//工作表  
            WritableCell c00 = sheet.getWritableCell(0, 5);//第一列第一行单元格  
            if (c00.getType() == CellType.LABEL) {  
                ((Label) c00).setString("has modified.");  
            }  
            sheet.addCell(new Label( 0,6,"1" ));
            wwb.write();  
            wwb.close();  
            rwb.close();  
            System.out.println("更新完成.");  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }   
    }  
}
