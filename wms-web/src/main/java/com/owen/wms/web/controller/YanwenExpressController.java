package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.owen.wms.web.form.YanwenExpress;
import com.owen.wms.web.service.YanwenExpressService;

@Controller
@RequestMapping("/yanwen")
public class YanwenExpressController {
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String defaultDownloadPtha = "C:/Users/owen/Desktop/Amazon/燕文物流/运单打印";
	
	@Autowired
	private YanwenExpressService service;
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String getDownload(Model model,HttpServletRequest request) {
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
		return "createYanwenExpress";
	}

	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String postDownload(@ModelAttribute("express") YanwenExpress express,Model model) throws Exception {
		CreateExpressResponseType result = this.service.createExpressFromAmazonOrder( express);
		if(result!=null){
			if(result.isCallSuccess()){
				express.setAmazonOrderID(null);
				express.setChannel(null);
				express.setNameChinese(null);
				model.addAttribute("createSuccessIndicator", "快递单创建成功！");
			}else{
				model.addAttribute("createSuccessIndicator", "快递单创建失败： "+result.getResp().getReasonMessage());
			}
		}else{
			model.addAttribute("createSuccessIndicator", "快递单创建失败：无法获取亚马逊订单号【"+express.getAmazonOrderID()+"】信息");
		}
		return "createYanwenExpress";
	}
}