package com.owen.wms.web.service;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.utils.StringUtils;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service("amazonProductService")
public class AmazonProductService {
	private Logger log = Logger.getLogger(this.getClass());

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;
	
	public List<JewelryEntity> getJewelryList(){
		List<JewelryEntity> list = this.amazonJewelryDao.list("itemSku", true);
		return list;
	}
	
	public List<JewelryEntity> findBySKU(String sku){
		List<JewelryEntity> list = this.amazonJewelryDao.findBySKU(sku);
		return list;
	}
	
	public List<JewelryEntity> listAllParentProduct(){
		List<JewelryEntity> list = this.amazonJewelryDao.listAllParent();
		return list;
	}
	
	public void loadExcelData2DB(String excelPath,String sheetName) throws Exception{
		Workbook book = Workbook.getWorkbook(new File(excelPath));
		Sheet st = book.getSheet(sheetName);
		int totalColumns= 220;
		int totalRows = st.getRows();
		
		//1. get columns name
		String[] columnNames = new String[totalColumns];
		for(int i=0;i<totalColumns;i++){
			columnNames[i] = st.getCell(i, 2).getContents();
		}
		
		String value = null;
		for(int row=3;row<totalRows;row++){
			JewelryEntity entity = new JewelryEntity();
			int col;
			for(col=0;col<totalColumns;col++){
				Cell cell = st.getCell(col, row);
				if(CellType.EMPTY == cell.getType()){
					if(0==col){
						break ;
					}else{
						value = null;
					}
				}else{
					value = cell.getContents();
					if(0==col && value!=null && value.trim().length()<1){
						break ;
					}
				}
				this.setValueByJaveReflect(entity, columnNames[col], value);
			}
			
			if(0!=col){
				System.out.println(entity);
				this.amazonJewelryDao.saveOrUpdate(entity);
			}
		}
		book.close();
	}
	
	public void write2Excel2(String[] skuList,String excelPath) throws Exception{ 
		if(skuList==null || skuList.length<1){
			return;
		}
		ArrayList<JewelryEntity> listAll = new ArrayList<JewelryEntity>();
		for(String sku: skuList){
			ArrayList<JewelryEntity> list = this.amazonJewelryDao.findBySKU(sku);
			listAll.addAll(list);
		}
		this.write2Excel(listAll, excelPath);
		this.log.info("excel is exported to "+excelPath);
	}
	
	public void write2Excel(List<JewelryEntity> list,String excelPath) throws Exception{ 
		if(list==null && list.isEmpty()){
			this.log.warn("Product list is empty !");
			return;
		}
		//1. copy excel head from template
		File templateFile =new ClassPathResource("template/JewelryTemplate.xls").getFile();
		String templateFilePath = templateFile.getAbsolutePath();
		
		Workbook sourceBook = Workbook.getWorkbook(new File(templateFilePath));
		WritableWorkbook targetBoook = Workbook.createWorkbook(new File(excelPath),sourceBook);
		WritableSheet sheet1 = targetBoook.getSheet(0); 
		sheet1.getSettings().setHorizontalFreeze(1);  // 设置列冻结
		sheet1.getSettings().setVerticalFreeze(3);  // 设置行冻结前2行
        
		//2. get columns name
		Sheet st = sourceBook.getSheet(0);
		int totalColumns= 220;
		String[] columnNames = new String[totalColumns];
		for(int i=0;i<totalColumns;i++){
			columnNames[i] = st.getCell(i, 2).getContents();
		}
		
		//3. write data to excel
		for(int row =0; row < list.size();row ++){
			for(int col=0;col<totalColumns;col++){
				Object columnValue =  this.getValueByJaveReflect(list.get(row), columnNames[col]);
				if(columnValue == null){
					sheet1.addCell(new Label( col,row+3,null ));
				}else{
					sheet1.addCell(new Label( col,row+3,columnValue.toString() ));
				}
			}
		}
		
		targetBoook.write();
		targetBoook.close();
		sourceBook.close();
	}
	
	private void setValueByJaveReflect(JewelryEntity target,String columnName,String value) throws Exception{
		Class clazz = JewelryEntity.class; 
        Method setMethod = clazz.getDeclaredMethod(asembleSetMethodName(columnName), String.class);
        setMethod.invoke(target, value);
	}
	
	private Object getValueByJaveReflect(JewelryEntity target,String columnName) throws Exception{
		Class clazz = JewelryEntity.class; 
        Method getMethod = clazz.getDeclaredMethod(asembleGetMethodName(columnName));
        Object value = getMethod.invoke(target);
        return value;
	}
	
	/**
	 * 
	 * @param columnName , eg. item_name
	 * return setItemName
	 */
	private String asembleSetMethodName(String columnName){
		String setMethodName = null ;
		setMethodName = StringUtils.deleteUnderscoreAndInitalNext(columnName);//itemName
		setMethodName = "set" + setMethodName.substring(0, 1).toUpperCase() +setMethodName.substring(1);//setItemName
		return setMethodName;
		
	}
	
	private String asembleGetMethodName(String columnName){
		String setMethodName = null ;
		setMethodName = StringUtils.deleteUnderscoreAndInitalNext(columnName);//itemName
		setMethodName = "get" + setMethodName.substring(0, 1).toUpperCase() +setMethodName.substring(1);//setItemName
		return setMethodName;
		
	}
}
