package com.owen.wms.web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.wms.web.entity.JewelryEntity;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class ProductJewelryDaoTest {
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	AmazonJewelryDao dao ;
	
	@Test
	public void testSave(){
		JewelryEntity entity = new JewelryEntity();
		entity.setItemSku("112");
		System.out.println(this.dao.save(entity));
	}
	
	@Test
	public void testSaveOrUpate(){
		JewelryEntity entity = new JewelryEntity();
		entity.setItemSku("112");
		this.dao.saveOrUpdate(entity);
	}
	
	@Test
	public void testList(){
		List<JewelryEntity> list = dao.list();
		System.out.println(list.size());
	}
	
	@Test
	public void updateTitle(){
		List<JewelryEntity> list = dao.list();
		for(JewelryEntity ent: list){
			String title = ent.getItemName();
			if(title.trim().startsWith("CVO Jewelry")){
				title=title.replace("CVO Jewelry", "Fashion Jewelry");
				ent.setItemName(title);
				this.dao.update(ent);
				System.out.println(ent.getItemName());
			}else if(title.trim().startsWith("CVO")){
				title=title.replace("CVO", "");
				ent.setItemName(title);
				this.dao.update(ent);
				System.out.println(ent.getItemName());
			}
		}
	}
	
	@Test
	public void listAllParent(){
		List<JewelryEntity> list = dao.listAllParent();
		if(list!=null && !list.isEmpty()){
			for(JewelryEntity e:list){
				ArrayList<JewelryEntity> childList = dao.findBySKUWithChild(e.getItemSku());
				System.out.println(e.getItemSku()+"---"+e.getParentChild()+"--"+childList.size());
//				Assert.assertNull(e.getParentSku());
			}
		}
	}
	
	/**
	 * OPK-GX435-P
	 * OPK-GX810
	 * R-1002-P
	 * NP-522111117648-P
	 * NP-42556752885
	 * NP-1260959816-Purple
	 * BL-OPK-PH980-P
	 * BL-1261460472-P
	 */
	@Test
	public void updateSKU(){
		List<JewelryEntity> list = dao.listAllParent();
		long index = 66688859510L;
		String oldParentSKU=null;
		if(list!=null && !list.isEmpty()){
			for(JewelryEntity e:list){
				index++;
				oldParentSKU = e.getItemSku();
				if(oldParentSKU.startsWith("R-1000") || oldParentSKU.startsWith("R-1001") || oldParentSKU.startsWith("OPK-PH855-P")||oldParentSKU.startsWith("Bag")){
					continue;
				}
				ArrayList<JewelryEntity> childList = dao.findBySKUWithChild(oldParentSKU);
				System.out.println(e.getItemSku()+"---"+e.getParentChild()+"--"+childList.size());
				if(childList.size()>0){
					for(JewelryEntity c : childList){
						JewelryEntity ent = c.clone();
						String newSku = this.getNewSKU(c.getItemSku(), index);
						ent.setItemSku(newSku);
						if(c.getParentSku()!=null && c.getParentSku().trim().length()>0){
							String newParentSku = this.getNewSKU(c.getParentSku(), index);
							ent.setParentSku(newParentSku);
						}
//						this.dao.deleteObject(c);
//						this.dao.save(ent);
					}
				}else{
				}
				
			}
		}
	}
	
	private String getNewSKU(String sku,long index){
		int index1 = sku.indexOf("-");
		int index2 = sku.lastIndexOf("-");
		String reslut = null;
		if(index1 != index2){
			reslut = sku.substring(0,index1)+"-"+index+sku.substring(index2);
		}else{
			reslut = sku.substring(0,index1)+"-"+index;
		}
		
		System.out.println(sku+">>>>"+reslut);
		return reslut;
	}
	
	@Test
	public void findBySKU(){
		String sku = "BL-1001-P";
//		sku="BL-1001-CB1";
		List<JewelryEntity> list = dao.findBySKUWithChild(sku);
		System.out.println(list.size());
		for(JewelryEntity ent:list){
			System.out.println(ent.getItemSku()+"----------"+ent.getParentSku());
		}
	}
	
	@Test
	public void findBySKUWithParentAndAllBrothers(){
		String sku = "BL-1261460472-C1";
		List<JewelryEntity> list = dao.findBySKUWithParentAndAllBrothers(sku);
		System.out.println(list.size());
		for(JewelryEntity ent:list){
			System.out.println(ent.getItemSku()+"----------"+ent.getParentSku());
		}
	}
	
	@Test
	public void testIsParent(){
		Assert.assertTrue(this.dao.isParent("NP-522111117648-P"));
		Assert.assertTrue(!this.dao.isParent("NP-522111117648-C1"));
	}
	
	@Test
	public void testUuid(){
		UUID uuid = UUID.randomUUID();
		String s = UUID.randomUUID().toString();
		System.out.println(s);
	}
}
