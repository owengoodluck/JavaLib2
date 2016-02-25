package com.owen.wms.web.service;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.owen.wms.web.constants.ParentChild;
import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.dao.Page;
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
	
	private Set<String> omitColumnNames4Parent = new HashSet();
	{
		omitColumnNames4Parent.add("external_product_id");
		omitColumnNames4Parent.add("external_product_id_type");
		omitColumnNames4Parent.add("standard_price");
		omitColumnNames4Parent.add("quantity");
		omitColumnNames4Parent.add("currency");
		omitColumnNames4Parent.add("list_price");
		omitColumnNames4Parent.add("bullet_point2");
		omitColumnNames4Parent.add("bullet_point3");
		omitColumnNames4Parent.add("bullet_point4");
		omitColumnNames4Parent.add("bullet_point5");
		omitColumnNames4Parent.add("generic_keywords1");
		omitColumnNames4Parent.add("generic_keywords2");
		omitColumnNames4Parent.add("generic_keywords3");
		omitColumnNames4Parent.add("generic_keywords4");
		omitColumnNames4Parent.add("generic_keywords5");
		omitColumnNames4Parent.add("other_image_url1");
		omitColumnNames4Parent.add("other_image_url2");
		omitColumnNames4Parent.add("other_image_url3");
		omitColumnNames4Parent.add("other_image_url4");
		omitColumnNames4Parent.add("other_image_url5");
		omitColumnNames4Parent.add("parent_sku");
		omitColumnNames4Parent.add("relationship_type");
		omitColumnNames4Parent.add("color_name");
	}
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;
	
	public Boolean isParent(String sku){
		return this.amazonJewelryDao.isParent(sku);
	}
	
	/**
	 * page query
	 * @param currentPage
	 * @param pageSize
	 * @param entity
	 * @return
	 */
	public Page pageListByCriteria(int currentPage,int pageSize,JewelryEntity entity){
		return this.amazonJewelryDao.pageListByCriteria(currentPage, pageSize, entity);
	}
	
	public void saveOrUpdate(JewelryEntity entity){
		this.amazonJewelryDao.saveOrUpdate(entity);
	}
	public List<JewelryEntity> getJewelryList(){
		List<JewelryEntity> list = this.amazonJewelryDao.list("itemSku", true);
		return list;
	}
	
	public List<JewelryEntity> findBySKUWithChild(String sku){
		List<JewelryEntity> list = this.amazonJewelryDao.findBySKUWithChild(sku);
		return list;
	}
	
	public List<JewelryEntity> findBySKUWithParentAndAllBrothers(String sku){
		List<JewelryEntity> list = this.amazonJewelryDao.findBySKUWithParentAndAllBrothers(sku);
		return list;
	}
	
	
	public JewelryEntity getById(String sku){
		return this.amazonJewelryDao.get(sku);
	}
	
	public void saveOrUpdate(List<JewelryEntity> list){
		if(list == null || list.isEmpty()){
			return;
		}
		Date now = new Date();
		for(JewelryEntity e: list){
			e.setUpdateDate(now);
			this.amazonJewelryDao.saveOrUpdate(e);
		}
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
			ArrayList<JewelryEntity> list = this.amazonJewelryDao.findBySKUWithChild(sku);
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
		int totalColumns= st.getColumns();//for version it's 220
		String[] columnNames = new String[totalColumns];
		for(int i=0;i<totalColumns;i++){
			columnNames[i] = st.getCell(i, 2).getContents();
		}
		
		//3. write data to excel
		for(int row =0; row < list.size();row ++){
			JewelryEntity ent = list.get(row);
			this.log.info(row+"------------------------");
			for(int col=0;col<totalColumns;col++){
				Object columnValue =  this.getValueByJaveReflect(ent, columnNames[col]);
				if(columnValue == null){
					if("generic_keywords".equals(columnNames[col])){
						String keywords = ent.getGenericKeywords1()
								+" " +ent.getGenericKeywords2()
								+" " +ent.getGenericKeywords3()
								+" " +ent.getGenericKeywords4()
								+" " +ent.getGenericKeywords5()
								+" " +ent.getNewKeywords();
						int lenght = keywords.length()>1000 ? 999 :keywords.length()-1;
						sheet1.addCell(new Label( col,row+3,keywords.substring(0,lenght)));
					}else{
						sheet1.addCell(new Label( col,row+3,null ));
					}
				}else{
					if(ParentChild.parent.toString().equals(ent.getParentChild())){
						//some column for parent is no needed
						if( this.omitColumnNames4Parent.contains(columnNames[col])){
							sheet1.addCell(new Label( col,row+3,null ));
						}else{
							sheet1.addCell(new Label( col,row+3,columnValue.toString() ));
						}
					}else{
						sheet1.addCell(new Label( col,row+3,columnValue.toString() ));
					}
				}
			}
		}
		
		targetBoook.write();
		targetBoook.close();
		sourceBook.close();
	}
	
	private boolean isNeedInParent(String columnName){
		
		return true;
	}
	
	private void setValueByJaveReflect(JewelryEntity target,String columnName,String value) throws Exception{
		if(value == null || value.trim().length()<1){
			return;
		}
		Class clazz = JewelryEntity.class; 
        Method setMethod = null;
        if("list_price".equals(columnName) ||"standard_price".equals(columnName)){
        	setMethod = clazz.getDeclaredMethod(asembleSetMethodName(columnName), Double.class);
        	setMethod.invoke(target, Double.valueOf(value));
        }else if("quantity".equals(columnName) ){
        	setMethod = clazz.getDeclaredMethod(asembleSetMethodName(columnName), Integer.class);
        	setMethod.invoke(target, Integer.valueOf(value));
        }else{
        	setMethod = clazz.getDeclaredMethod(asembleSetMethodName(columnName), String.class);
        	setMethod.invoke(target, value);
        }
	}
	
	private Object getValueByJaveReflect(JewelryEntity target,String columnName) {
		Class clazz = JewelryEntity.class; 
		try{
			Method getMethod = clazz.getDeclaredMethod(asembleGetMethodName(columnName));
			Object value = getMethod.invoke(target);
			return value;
		}catch(Exception e){
			this.log.error("fail  to get column value for columnName = "+columnName);
			return null;
		}
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
		try{
			setMethodName = StringUtils.deleteUnderscoreAndInitalNext(columnName);//itemName
			setMethodName = "get" + setMethodName.substring(0, 1).toUpperCase() +setMethodName.substring(1);//setItemName
			
		}catch(Exception e){
			this.log.error("faile to assembel GET method for column columnName = "+columnName);
		}
		return setMethodName;
		
	}
}
