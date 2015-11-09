package com.owen.wms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to Owen WMS-WEB!");
		model.addAttribute("tagline", "The one and only amazing webstore");
		return "forward:/welcome/greeting";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greeting(Model model){
		
		return "welcome";
	}

}