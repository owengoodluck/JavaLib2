package com.owen.wms.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owen.wms.web.dao.ProductJewelryDao;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.entity.ProductJewelryEntity;
import com.owen.wms.web.form.JewelryEntityListPackageForm;
import com.owen.wms.web.service.AmazonProductService;

@Controller
@RequestMapping("/prod")
public class AmazonProductController {
	
	@Autowired
	@Qualifier("productJewelryDao")
	private ProductJewelryDao dao ;
	
	@Autowired
	@Qualifier("amazonProductService")
	private AmazonProductService amazonProductService;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAll(Model model){
		List<JewelryEntity> list = this.amazonProductService.getJewelryList();
		model.addAttribute("list", list);
		return "prod/productList";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		JewelryEntityListPackageForm productsForm = new JewelryEntityListPackageForm();
		productsForm.getList().add(new JewelryEntity());
		model.addAttribute("productsForm", productsForm);
		return "prod/addJewel";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm){
		List<JewelryEntity> list = productsForm.getList();
		for(JewelryEntity ent:list){
			System.out.println(ent.getItemSku() +"-"+ent.getItemName());
		}
//		this.dao.save(jewel);
		return "prod/addJewel";
	}
	
	
}
