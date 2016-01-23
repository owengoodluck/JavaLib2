package com.owen.wms.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.aspectj.util.FileUtil;
import org.junit.Test;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JsonTest {

	@Test
	public void test() throws Exception{
		File file = new File("C:/Users/owen/git/wms-web/src/test/resources/json/test4.json");
		String json = FileUtil.readAsString(file);
		
		JSONObject jsonObject1 = (JSONObject) JSONSerializer.toJSON(json);
		 Map<String,ArrayList> map = (Map)JSONObject.toBean( jsonObject1, Map.class);
		 for(ArrayList<MorphDynaBean> eachColor: map.values()){
			 for(MorphDynaBean pic:eachColor){
				 try{
					 System.out.println("----------------"+pic.get("hiRes"));
				 }catch(MorphException e){
					 System.out.println("----------------"+pic.get("large"));
				 }
				 
			 }
		 }
	}
}
