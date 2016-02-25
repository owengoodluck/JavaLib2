package com.owen.wms.web.controller;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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

import com.owen.wms.web.constants.ParentChild;
import com.owen.wms.web.constants.RelationshipType;
import com.owen.wms.web.constants.UpdateModel;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.form.JewelryEntityListPackageForm;
import com.owen.wms.web.form.ProdQueryForm;
import com.owen.wms.web.service.AmazonProductService;
import com.owen.wms.web.thread.PictureDownLoadThread;
import com.owen.wms.web.utils.ExcelKeywrodsUtil;
import com.owen.wms.web.utils.NullAwareBeanUtilsBean;

@Controller
@RequestMapping("/prod")
//@SessionAttributes("productsForm")
public class AmazonProductController {
	private Logger log = Logger.getLogger(this.getClass());
	private String defaultPathToExportExcel = "C:/Users/Fang/Desktop/tmp";
	private int defaultPageSize = 20;
	
	private ExecutorService pool = Executors.newFixedThreadPool(3);
	
	@Autowired
	@Qualifier("amazonProductService")
	private AmazonProductService amazonProductService;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAll(Model model){
		//1.query
		JewelryEntity queryEntity = new JewelryEntity();
		queryEntity.setParentChild("parent");
		Page page = this.amazonProductService.pageListByCriteria(1, defaultPageSize, queryEntity );
		
		ProdQueryForm prodQueryForm =  new ProdQueryForm();
		prodQueryForm.setCurrentPage(1);
		prodQueryForm.setPageSize(defaultPageSize);
		prodQueryForm.setParentChild("parent");
		//2.response
		model.addAttribute("page", page);
		model.addAttribute("prodQueryForm", prodQueryForm);
		model.addAttribute("currentMenu", "prod");
		return "prod/productList";
	}

	@RequestMapping(value="/queryProd", method = RequestMethod.POST)
	public String queryProd(Model model,@ModelAttribute("prodQueryForm") ProdQueryForm prodQueryForm) throws Exception {
		//1.query
		JewelryEntity queryEntity = new JewelryEntity();
		queryEntity.setParentChild(prodQueryForm.getParentChild());
		queryEntity.setItemSku(prodQueryForm.getItemSKU());
		queryEntity.setItemName(prodQueryForm.getItemName());
		queryEntity.setItemType(prodQueryForm.getItemType());
		Page page = this.amazonProductService.pageListByCriteria(prodQueryForm.getCurrentPage(), prodQueryForm.getPageSize(), queryEntity );
		
		//2.response
		model.addAttribute("page", page);
		model.addAttribute("currentMenu", "prod");
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
	
	@RequestMapping(value = "/loadKeywords", method = RequestMethod.POST)
	public String loadKeywords(Model model,@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request) throws Exception{
		String keywordsExcelFilePath = request.getParameter("keywordsExcelFilePath");
		String keywordsExcelStartIndex = request.getParameter("keywordsExcelStartIndex");
		String isNewVersion = request.getParameter("isNewVersion");
		
		File excelKeywordFile = new File(keywordsExcelFilePath);
		if(excelKeywordFile.exists()){
			if("true".equals(isNewVersion)){
				ExcelKeywrodsUtil.setKeywords4NewVersion(productsForm.getList(), excelKeywordFile, Integer.valueOf(keywordsExcelStartIndex));
			}else{
				ExcelKeywrodsUtil.setKeywords(productsForm.getList(), excelKeywordFile, Integer.valueOf(keywordsExcelStartIndex));
			}
		}else{
			this.log.error(keywordsExcelFilePath+" file does not exist !");
			model.addAttribute("errorMsg",keywordsExcelFilePath+" file does not exist !");
		}
		
		model.addAttribute("currentMenu", "prod");
		model.addAttribute("keywordsExcelFilePath", keywordsExcelFilePath);
		model.addAttribute("keywordsExcelStartIndex", keywordsExcelStartIndex);
		return "prod/addKeyword";
	}
	@RequestMapping(value = "/edit/{sku}", method = RequestMethod.GET)
	public String eddit(Model model,@PathVariable("sku") String sku){
		ArrayList<JewelryEntity> list = (ArrayList<JewelryEntity>) this.amazonProductService.findBySKUWithChild(sku);
		JewelryEntityListPackageForm productsForm = new JewelryEntityListPackageForm();
		productsForm.setList(list);
		model.addAttribute("productsForm", productsForm);
		print(list);
		model.addAttribute("currentMenu", "prod");
		return "prod/addTitle";
	}
	
	@RequestMapping(value = "/addTitle", method = RequestMethod.GET)
	public String addTitleGet(Model model){
		JewelryEntityListPackageForm productsForm = new JewelryEntityListPackageForm();
		productsForm.getList().add(new JewelryEntity());
		model.addAttribute("productsForm", productsForm);
		model.addAttribute("currentMenu", "prod");
		return "prod/addTitle";
	}
	
	//-------------------------------------------------------
	@RequestMapping(value = "/saveTab", method = RequestMethod.POST)
	public String saveTab(Model model,@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request) throws Exception{
		String tabName = request.getParameter("tabName");
		
		//1. save pic if pic tab
		ArrayList<JewelryEntity> list = productsForm.getList();
		String imgPath = request.getSession().getServletContext().getRealPath("/img");
		this.downLoadPicture(list, new File(imgPath));
		
		//2. enrich info
		this.enrichInfo(productsForm);
		
		//3.save data
		this.saveOrUpate(productsForm);
		model.addAttribute("currentMenu", "prod");
		return "prod/"+tabName;
	}
	
	
	private void enrichInfo(JewelryEntityListPackageForm productsForm){
		if(productsForm!=null && productsForm.getList()!=null && !productsForm.getList().isEmpty()){
			ArrayList<JewelryEntity> list = productsForm.getList();
			for(JewelryEntity e: list){
				
				//1. set parent child
				String parentSku = e.getParentSku();
				if(parentSku!=null && parentSku.trim().length()>0){
					e.setParentChild(ParentChild.child.toString());
					e.setRelationshipType(RelationshipType.Variation.toString());
				}else{
					if(this.amazonProductService.isParent(e.getItemSku())){
						e.setParentChild(ParentChild.parent.toString());
					}
				}
				
				//2.setFeedProductTypeByItemType
				String itemType = e.getItemType();
				if(itemType!=null){
					itemType = itemType.toLowerCase();
					switch (itemType){
					case "pendant-necklaces":e.setFeedProductType("FashionNecklaceBraceletAnklet");break;
					case "link-bracelets":e.setFeedProductType("FashionNecklaceBraceletAnklet");break;
					case "rings":e.setFeedProductType("FashionRing");break;
					default:;//TODO TBC
					}
				}
				
				//3. set description
				if(e.getProductDescription() == null || e.getProductDescription().trim().length()<1){
					e.setProductDescription(e.getItemName());
				}
				
				//4.by default setUpdateDelete as Update
				e.setUpdateDelete(UpdateModel.Update.toString());
			}
		}
	}
	
	private void setFeedProductTypeByItemType(JewelryEntityListPackageForm productsForm){
		if(productsForm!=null && productsForm.getList()!=null && !productsForm.getList().isEmpty()){
			ArrayList<JewelryEntity> list = productsForm.getList();
			for(JewelryEntity e: list){
				String itemType = e.getItemType();
				if(itemType!=null){
					itemType = itemType.toLowerCase();
					switch (itemType){
					case "pendant-necklaces":e.setFeedProductType("FashionNecklaceBraceletAnklet");break;
					case "link-bracelets":e.setFeedProductType("FashionNecklaceBraceletAnklet");break;
					case "rings":e.setFeedProductType("FashionRing");break;
					default:;//TODO TBC
					}
				}
			}
		}
	}
	
	@RequestMapping(value = "/export2Excel", method = RequestMethod.POST)
	public String export2Excel(Model model,@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request) throws Exception{
		this.saveOrUpate(productsForm);
		ArrayList<JewelryEntity> list = productsForm.getList();
		if(list!=null && !list.isEmpty()){
			String excelFilePath = this.defaultPathToExportExcel+"/"+list.get(0).getItemSku()+".xls";
			this.amazonProductService.write2Excel(list, excelFilePath);
		}
		return "prod/addPurchaseUrl";
	}
	
	private void saveOrUpate(JewelryEntityListPackageForm productsForm) throws Exception{
		NullAwareBeanUtilsBean nullAwareBeanUtil = new NullAwareBeanUtilsBean();
		if(productsForm == null || productsForm.getList() == null || productsForm.getList().isEmpty()){
			return;
		}else{
			ArrayList<JewelryEntity> list =productsForm.getList() ;
			for(int i = 0;i<list.size();i++){
				JewelryEntity form = list.get(i);
				form.setUpdateDate(new Date());
				JewelryEntity en = this.amazonProductService.getById(form.getItemSku());
				if(en ==null){
					this.amazonProductService.saveOrUpdate(form);
				}else{
					try {
						nullAwareBeanUtil.copyProperties(en, form);
						this.amazonProductService.saveOrUpdate(en);
						list.set(i, en);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
						throw new Exception();
					}
				}
			}
		}
	}
	
	private void downLoadPicture(ArrayList<JewelryEntity> list,File folder){
		if(list == null || list.isEmpty()){
			return;
		}else{
			for(JewelryEntity e: list){
				if(e.getMainImageUrl()!=null && e.getMainImageUrl().trim().length()>0){
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
