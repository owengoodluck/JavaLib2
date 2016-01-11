package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.entity.YanWenExpressEntity;
import com.owen.wms.web.form.ExpressQueryForm;
import com.owen.wms.web.form.OrderQueryForm;
import com.owen.wms.web.form.YanwenExpress;
import com.owen.wms.web.service.YanwenExpressService;

@Controller
@RequestMapping("/yanwen")
public class YanwenExpressController {
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String defaultDownloadPtha = "C:/Users/owen/Desktop/Amazon/燕文物流/运单打印";
	private int defaultPageSize = 20;
	
	@Autowired
	private YanwenExpressService service;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listAll(Model model,HttpServletRequest request) throws Exception {
		ExpressQueryForm expressQueryForm = new ExpressQueryForm();
		expressQueryForm.setCurrentPage(1);
		expressQueryForm.setPageSize(defaultPageSize);
		
		
		Page page = this.service.pageQuery(expressQueryForm);
		model.addAttribute("page", page);
		model.addAttribute("expressQueryForm", expressQueryForm);
		model.addAttribute("currentMenu", "express");
		return "express/expressList";
	}

	@RequestMapping(value="/pageQuery", method = RequestMethod.POST)
	public String pageQuery(Model model,@ModelAttribute("expressQueryForm") ExpressQueryForm expressQueryForm) throws Exception {
		Page page = this.service.pageQuery(expressQueryForm);
		model.addAttribute("page", page);
		model.addAttribute("expressQueryForm", expressQueryForm);
		model.addAttribute("currentMenu", "express");
		return "express/expressList";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String preCreateExpress(Model model,HttpServletRequest request) {
		String amazonOrderID = request.getParameter("amazonOrderID");
		YanwenExpress express = new YanwenExpress();
		express.setAmazonOrderID(amazonOrderID);
		express.setDeclaredCurrency("USD");
		express.setQuantity(1);
		express.setDeclaredValue(9);
		express.setWeight(50);
		express.setSendDate(sdf.format(new Date()));
		express.setDownloadPath(defaultDownloadPtha);
		express.setCountry("美国");
		
		model.addAttribute("express", express);
		model.addAttribute("currentMenu", "express");
		return "createYanwenExpress";
	}

	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createExpress(@ModelAttribute("express") YanwenExpress express,Model model) throws Exception {
		CreateExpressResponseType result = null;
		if("remoteAmz".equals(express.getMethodToGetOrder())){
			result = this.service.createExpressFromAmazonOrderWMS(express);
		}else{
			result = this.service.createExpressFromAmazonOrder( express);
		}
		String orderId = express.getAmazonOrderID();
		if(result!=null){
			if(result.isCallSuccess()){
				express.setAmazonOrderID(null);
				express.setChannel(null);
				express.setNameChinese(null);
				model.addAttribute("createSuccessIndicator","订单["+ orderId+"] 快递单创建成功！快递单号 ：" +result.getResp().getEpcode());
			}else{
				model.addAttribute("createSuccessIndicator", "快递单创建失败： "+result.getResp().getReasonMessage());
			}
		}else{
			model.addAttribute("createSuccessIndicator", "快递单创建失败：无法获取亚马逊订单号【"+express.getAmazonOrderID()+"】信息");
		}
		model.addAttribute("currentMenu", "express");
		return "createYanwenExpress";
	}
}