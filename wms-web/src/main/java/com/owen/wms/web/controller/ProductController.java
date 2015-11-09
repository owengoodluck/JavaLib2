package com.owen.wms.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		model.addAttribute("newProduct", null);
		return "addProduct";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") String newProduct, 
			BindingResult result, HttpServletRequest request){
		return null;
	}
	
	@InitBinder
	public void initializeBinder(WebDataBinder binder){
		binder.setDisallowedFields("unitsInOrder", "discontinued", "imageSource");
		binder.setAllowedFields("productId", "name", "unitPrice", "description", 
				"manufacturer", "category", "unitsInStock", "productImage", "condition");
	}
	
	@RequestMapping("/invalidPromoCode")
	public String invalidPromoCode(){
		return "invalidPromoCode";
	}
}
