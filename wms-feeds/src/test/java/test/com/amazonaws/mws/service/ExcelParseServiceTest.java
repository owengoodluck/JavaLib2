package test.com.amazonaws.mws.service;

import java.util.List;

import org.junit.Test;

import com.amazonaws.mws.service.ExcelParseService;

public class ExcelParseServiceTest {

	@Test
	public void test(){
		ExcelParseService service = new ExcelParseService();
		List<String[]> list = service.getSheet("C:/Users/owen/Desktop/Amazon/Flat.File.Jewelry.xls", 3, 220);
		if(list!=null){
			int i =0;
			for(String[] rowArray: list){
				StringBuffer buf = new StringBuffer(i+":");
				i++;
				String lable="-";
				for(String value:rowArray){
					buf.append(value);
					buf.append(lable);
				}
				System.out.println(buf.toString());
			}
		}else{
			System.out.println("list is null");
		}
	}
}
