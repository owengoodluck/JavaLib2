package com.owen.wms.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.owen.wms.web.dao.ProductJewelryDao;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.form.JewelryEntityListPackageForm;
import com.owen.wms.web.service.AmazonProductService;

@Controller
@RequestMapping("/prod")
@SessionAttributes("productsForm")
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
		ArrayList<JewelryEntity> list = productsForm.getList();
		return "prod/addPicture";
	}
	
	@RequestMapping(value = "/addPicture", method = RequestMethod.POST)
	public String addPicture(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		ArrayList<JewelryEntity> list = productsForm.getList();
		return "prod/addTitle";
	}
	
	@RequestMapping(value = "/addOtherinfo", method = RequestMethod.POST)
	public String addOtherinfo(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		ArrayList<JewelryEntity> list = productsForm.getList();
		return "prod/addTitle";
	}
	
}
