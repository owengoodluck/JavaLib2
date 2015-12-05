package com.owen.wms.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.form.JewelryEntityListPackageForm;
import com.owen.wms.web.service.AmazonProductService;

@Controller
@RequestMapping("/prod")
@SessionAttributes("productsForm")
public class AmazonProductController {
	
	@Autowired
	@Qualifier("amazonProductService")
	private AmazonProductService amazonProductService;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAll(Model model){
		List<JewelryEntity> list = null;//
//		list = this.amazonProductService.getJewelryList();
		list = this.amazonProductService.findBySKU("NP-35447597783-P");
		model.addAttribute("list", list);
		return "prod/productList";
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
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addTitle";
		}else{
			return "prod/addPicture";
		}
	}
	
	@RequestMapping(value = "/addPicture", method = RequestMethod.POST)
	public String addPicture(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		ArrayList<JewelryEntity> list = productsForm.getList();
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addBulletPoint";
		}else{
			return "prod/addKeyword";
		}
	}
	
	@RequestMapping(value = "/addKeyword", method = RequestMethod.POST)
	public String addKeyword(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		ArrayList<JewelryEntity> list = productsForm.getList();
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addPicture";
		}else{
			return "prod/addPrice";
		}
	}
	
	@RequestMapping(value = "/addPrice", method = RequestMethod.POST)
	public String addPrice(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		ArrayList<JewelryEntity> list = productsForm.getList();
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addKeyword";
		}else{
			return "prod/addOtherinfo";
		}
	}
	@RequestMapping(value = "/addOtherinfo", method = RequestMethod.POST)
	public String addOtherinfo(@ModelAttribute("productsForm") JewelryEntityListPackageForm productsForm,HttpServletRequest request){
		ArrayList<JewelryEntity> list = productsForm.getList();
		String preOrNext = request.getParameter("preOrNext");
		if("pre".equals(preOrNext)){
			return "prod/addPrice";
		}else{
			return "prod/addTitle";
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
}
