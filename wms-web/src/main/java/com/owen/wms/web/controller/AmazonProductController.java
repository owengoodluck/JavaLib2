package com.owen.wms.web.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.form.JewelryEntityListPackageForm;
import com.owen.wms.web.service.AmazonProductService;
import com.owen.wms.web.thread.PictureDownLoadThread;

@Controller
@RequestMapping("/prod")
@SessionAttributes("productsForm")
public class AmazonProductController {
	private Logger log = Logger.getLogger(this.getClass());
	private String defaultPathToExportExcel = "C:/Users/owen/Desktop/tmp";
	
	private ExecutorService pool = Executors.newFixedThreadPool(3);
	
	@Autowired
	@Qualifier("amazonProductService")
	private AmazonProductService amazonProductService;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAll(Model model){
		List<JewelryEntity> list = null;//
//		list = this.amazonProductService.getJewelryList();
//		list = this.amazonProductService.findBySKU("NP-35447597783-P");
		list = this.amazonProductService.listAllParentProduct();
		model.addAttribute("list", list);
		return "prod/productList";
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public String exportExcel(Model model,HttpServletRequest request) throws Exception{
		String[] skuList = request.getParameterValues("itemSkuList");
		String exportFolder = request.getParameter("exportFolder");
		if(skuList!=null && skuList.length>0){
			String excelFilePath = exportFolder+"/"+skuList[0]+".xls";//"C:/Users/owen/git/wms-web/src/test/resources/copy.xls";
			this.amazonProductService.write2Excel2(skuList, excelFilePath);
		}else{
			this.log.warn("SKU list is null");
		}
		return listAll(model);
	}
	
	@RequestMapping(value = "/edit/{sku}", method = RequestMethod.GET)
	public String eddit(Model model,@PathVariable("sku") String sku){
		ArrayList<JewelryEntity> list = (ArrayList<JewelryEntity>) this.amazonProductService.findBySKU(sku);
		JewelryEntityListPackageForm productsForm = new JewelryEntityListPackageForm();
		productsForm.setList(list);
		model.addAttribute("productsForm", productsForm);
		print(list);
		return "prod/addTitle";
	}
	
	@RequestMapping(value = "/addTitle", method = RequestMethod.GET)
	public String addTitleGet(Model model){
		JewelryEntityListPackageForm productsForm = new JewelryEntityListPackageForm();
		productsForm.getList().add(new JewelryEntity());
		model.addAttribute("productsForm", productsForm);
		return "prod/addTitle";
	}
	
	//-------------------------------------------------------
	@RequestMapping(value = "/addTitle", method = RequestMethod.POST)
	public String addTitlePost(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		this.saveOrUpate(productsForm);
		ArrayList<JewelryEntity> list = productsForm.getList();
		for(int i=0;;i++){
			String p = request.getParameter("list["+i+"].itemSku");
			if(p==null){
				int size = list.size();
				int index = i;
				while(size > i){
					list.remove(index);
					i++;
				}
				break;
			}
		}
		return "prod/addBulletPoint";
	}
	
	@RequestMapping(value = "/addBulletPoint", method = RequestMethod.POST)
	public String addBulletPoint(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		this.saveOrUpate(productsForm);
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addTitle";
		}else{
			return "prod/addPicture";
		}
	}
	
	@RequestMapping(value = "/addPicture", method = RequestMethod.POST)
	public String addPicture(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		this.saveOrUpate(productsForm);
		ArrayList<JewelryEntity> list = productsForm.getList();
		String imgPath = request.getSession().getServletContext().getRealPath("/img");
		this.downLoadPicture(list, new File(imgPath));
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addBulletPoint";
		}else{
			return "prod/addKeyword";
		}
	}
	
	@RequestMapping(value = "/addKeyword", method = RequestMethod.POST)
	public String addKeyword(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		this.saveOrUpate(productsForm);
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addPicture";
		}else{
			return "prod/addPrice";
		}
	}
	
	@RequestMapping(value = "/addPrice", method = RequestMethod.POST)
	public String addPrice(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		this.saveOrUpate(productsForm);
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addKeyword";
		}else{
			return "prod/addOtherinfo";
		}
	}
	
	@RequestMapping(value = "/addOtherinfo", method = RequestMethod.POST)
	public String addOtherinfo(Model model,@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request) throws Exception{
		this.saveOrUpate(productsForm);
		ArrayList<JewelryEntity> list = productsForm.getList();
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addPrice";
		}else{
			if(list!=null && !list.isEmpty()){
				String excelFilePath = this.defaultPathToExportExcel+"/"+list.get(0).getItemSku()+".xls";
				this.amazonProductService.write2Excel(list, excelFilePath);
			}
			return listAll(model);
		}
	}
	
	private void saveOrUpate(JewelryEntityListPackageForm productsForm){
		if(productsForm == null || productsForm.getList() == null || productsForm.getList().isEmpty()){
			return;
		}else{
			this.amazonProductService.saveOrUpdate(productsForm.getList());
		}
	}
	
	private void downLoadPicture(ArrayList<JewelryEntity> list,File folder){
		if(list == null || list.isEmpty()){
			return;
		}else{
			for(JewelryEntity e: list){
				if(e.getMainImageUrl()!=null){
					this.pool.execute(new PictureDownLoadThread(e.getMainImageUrl(), folder));
				}
			}
		}
	}
	private void print(ArrayList<JewelryEntity> list){
		if(list==null || list.isEmpty()){
			return ;
		}
		for(JewelryEntity entity:list){
			System.out.println(entity.getItemSku()+"---"+entity.getItemName());
		}
	}
	
	public void closeThreadPool(){
		this.pool.shutdown();
	}
}
