package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owen.wms.web.form.YanwenExpress;
import com.owen.wms.web.service.YanwenExpressService;

@Controller
@RequestMapping("/yanwen")
public class YanwenExpressController {
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String defaultDownloadPtha = "C:/Users/owen/Desktop/Amazon/燕文物流/运单打印";
	
	private YanwenExpressService service = new YanwenExpressService();
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String getDownload(Model model) {
		YanwenExpress express = new YanwenExpress();
		express.setDeclaredCurrency("USD");
		express.setQuantity(1);
		express.setDeclaredValue(12.50);
		express.setWeight(50);
		express.setSendDate(sdf.format(new Date()));
		express.setDownloadPath(defaultDownloadPtha);
		express.setCountry("美国");
		model.addAttribute("express", express);
		return "createYanwenExpress";
	}

	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String postDownload(@ModelAttribute("express") YanwenExpress express) throws Exception {
		this.service.createExpressFromAmazonOrder( express);
		return "createYanwenExpress";
	}
}