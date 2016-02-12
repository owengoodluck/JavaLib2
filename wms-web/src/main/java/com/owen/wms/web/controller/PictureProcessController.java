package com.owen.wms.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owen.htmlparser.service.PictureDownloadService;
import com.owen.htmlparser.service.impl.AlibabaGroupPictureDownloadServiceImpl;
import com.owen.htmlparser.service.impl.AmazonPictureDownloadServiceImpl;
import com.owen.wms.web.form.PictureDownloadPackage;
import com.owen.wms.web.form.URLString;

@Controller
@RequestMapping("/picture")
public class PictureProcessController {
	private Logger log = Logger.getLogger(this.getClass());
	
	private PictureDownloadService alibabaPictureDownloadService = new AlibabaGroupPictureDownloadServiceImpl();
	private PictureDownloadService amazonPictureDownloadService = new AmazonPictureDownloadServiceImpl();
	
	@RequestMapping(value="/download", method = RequestMethod.GET)
	public String getDownload(Model model) {
		PictureDownloadPackage picPackage = new PictureDownloadPackage();
		picPackage.setDownloadPath("C:/Users/Fang/Desktop/tmp_pic");
		model.addAttribute("picPackage", picPackage);
		model.addAttribute("currentMenu", "pic");
		return "downloadPicture";
	}

	@RequestMapping(value="/download", method = RequestMethod.POST)
	public String postDownload(@ModelAttribute("picPackage") PictureDownloadPackage picPackage,Model model) {
		for(URLString url:picPackage.getUrlList()){
			if(url!=null && url.getUrl().trim().length()>0){
				this.log.info(url.getUrl());
				if(url.getUrl().indexOf("www.amazon.com")>-1){
					this.amazonPictureDownloadService.downloadPictue(url.getUrl(), picPackage.getDownloadPath(),picPackage.getPicSize());
				}else{
					this.alibabaPictureDownloadService.downloadPictue(url.getUrl(), picPackage.getDownloadPath(),picPackage.getPicSize());
				}
			}
		}
		this.log.info("---------------------------------------------------------------------");
		this.log.info("---------------------------Download Complete------------------------------------------");
		this.log.info("---------------------------------------------------------------------");
		model.addAttribute("currentMenu", "pic");
		model.addAttribute("msg", "下载完成");
		return "downloadPicture";
	}
}