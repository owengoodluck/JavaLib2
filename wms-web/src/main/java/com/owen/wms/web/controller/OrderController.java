package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.service.AmazonOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	@Qualifier("amazonOrderService")
	private AmazonOrderService amazonOrderService ;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listOrder(Model model) {
		List<AmazonOrder> orderList = this.amazonOrderService.getOrderList();
//		for(AmazonOrder od : orderList){
//			od.getAmazonOrderId();
//		}
		model.addAttribute("orderList", orderList);
		return "orders";
	}

}
