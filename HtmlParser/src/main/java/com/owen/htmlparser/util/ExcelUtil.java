package com.owen.htmlparser.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
	
	public static void generateExcel(String filePath) throws Exception{
		WritableWorkbook book = Workbook.createWorkbook( new File(filePath));
//      生成名为“第一页”的工作表，参数0表示这是第一页
        WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
        SheetSettings ss = sheet.getSettings();
        // ss.setHorizontalFreeze(2);  // 设置列冻结
        ss.setVerticalFreeze(2);  // 设置行冻结前2行

        WritableFont font1 =new WritableFont(WritableFont.createFont("微软雅黑"), 10 ,WritableFont.BOLD);
        WritableFont font2 =new WritableFont(WritableFont.createFont("微软雅黑"), 9 ,WritableFont.NO_BOLD);
        WritableCellFormat wcf = new WritableCellFormat(font1);
        WritableCellFormat wcf2 = new WritableCellFormat(font2);
        WritableCellFormat wcf3 = new WritableCellFormat(font2);//设置样式，字体
        
     // wcf2.setBackground(Colour.LIGHT_ORANGE);
        wcf.setAlignment(Alignment.CENTRE);  //平行居中
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
        wcf3.setAlignment(Alignment.CENTRE);  //平行居中
        wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
        wcf3.setBackground(Colour.LIGHT_ORANGE);
        wcf2.setAlignment(Alignment.CENTRE);  //平行居中
        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中

        sheet.mergeCells( 1 , 0 , 13 , 0 ); // 合并单元格  
        //          在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
//      以及单元格内容为test
        Label titleLabel = new Label( 1 , 0 , " 采暖市场部收入、成本、利润明细表 ",wcf);
//      将定义好的单元格添加到工作表中
        sheet.addCell(titleLabel);
        sheet.setRowView(1, 500); // 设置第一行的高度  20121111
        int[] headerArrHight = {13,10,30,20,20,25,7,10,15,20,13,15,15,30};
        String headerArr[] = {"年份","月份","经销商","合同号","产品","规格","数量","单价(元)","收款金额(元)","收款不含税价(元)","成本(元)","毛利(元)","毛利率","备注"};
        for (int i = 0; i < headerArr.length; i++) {
            sheet.addCell(new Label( i , 1 , headerArr[i],wcf));
            sheet.setColumnView(i, headerArrHight[i]);
        }           
        DecimalFormat df = new DecimalFormat("#.00");
        
        double sumZ = 0.00; //收款总额
        double sumT = 0.00; // 收款不含税价
        double sumC = 0.00; // 成本
        double sumM = 0.00; // 毛利
       
        sheet.addCell(new Label( 0 , 13 ,"合计：" ,wcf));
        sheet.addCell(new Label( 8 , 13 ,df.format(sumZ) ,wcf));
        sheet.addCell(new Label( 9 , 13 ,df.format(sumT) ,wcf));
        sheet.addCell(new Label( 10 , 13, df.format(sumC) ,wcf));
        sheet.addCell(new Label( 11 , 13 ,df.format(sumM) ,wcf));
        sheet.addCell(new Label( 13 , 15 ,"导出时间：" + new Date().toLocaleString() ,wcf3));
         
//      写入数据并关闭文件
        book.write();
        book.close();
	}
}
