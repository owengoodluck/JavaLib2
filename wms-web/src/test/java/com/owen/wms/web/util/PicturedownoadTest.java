package com.owen.wms.web.util;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.htmlparser.service.impl.AlibabaGroupPictureDownloadServiceImpl;
import com.owen.htmlparser.util.PictureDownloadUtil;
import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.entity.JewelryEntity;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class PicturedownoadTest {
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	AmazonJewelryDao dao ;
	
	@Test
	public void downloadAliTest(){
		String url ="http://detail.1688.com/offer/41435077419.html?spm=0.0.0.0.GDYTol";
		AlibabaGroupPictureDownloadServiceImpl d = new AlibabaGroupPictureDownloadServiceImpl();
		d.downloadPictue(url, "C:/Users/owen/Desktop/tmp",null);
	}
	
	@Test
	public void downloadTest(){
		List<JewelryEntity> list = this.dao.list();
		File folder = new File("C:/Users/owen/git/wms-web/WebContent/img");
		this.downLoadPicture(list, folder);
	}
	
	private void downLoadPicture(List<JewelryEntity> list,File folder){
		if(list == null || list.isEmpty()){
			return;
		}else{
			for(JewelryEntity e: list){
				if(e.getMainImageUrl()!=null){
					PictureDownloadUtil.downloadPicture(e.getMainImageUrl(), folder,null);
				}
			}
		}
	}
	
}
