package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.form.OrderSynchronizeForm;
import com.owen.wms.web.service.AmazonOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	@Qualifier("amazonOrderService")
	private AmazonOrderService amazonOrderService ;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listOrder(Model model) {
		model.addAttribute("orderList", getOrderList());
		
		OrderSynchronizeForm synForm = new OrderSynchronizeForm();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		synForm.setStartDateStr(this.sdf.format(cal.getTime()));
		model.addAttribute("synForm",synForm);
		return "order/orders";
	}
	
	@RequestMapping(value="/detail/{orderId}", method = RequestMethod.GET)
	public String getOrderById(Model model,@PathVariable("orderId") String orderId) {
		AmazonOrder result = this.amazonOrderService.getByOrderID(orderId);
		model.addAttribute("order", result);
		return "order/orderDetail";
	}
	
	@RequestMapping(value = "/synchronzieOrders", method = RequestMethod.POST)
	public String exportExcel(@ModelAttribute("synForm") OrderSynchronizeForm synForm,Model model) throws Exception{
		//1.synchronize orders
		Date createdAfterDate = this.sdf.parse(synForm.getStartDateStr());//not include this day ??
		Date createdBeforeDate = null;
		if(synForm.getEndDateStr()!=null && synForm.getEndDateStr().trim().length()>0){
			createdBeforeDate= this.sdf.parse(synForm.getEndDateStr());//include this day ??
		}
		String orderStatus = null; 
		amazonOrderService.synchronizeOrderToLocalDB(createdAfterDate, createdBeforeDate, orderStatus);
		
		//2. get order list and return 
		model.addAttribute("orderList", getOrderList());
		return "order/orders";
	}
	
	private List<AmazonOrder> getOrderList(){
		return this.amazonOrderService.getOrderList();
	}
}
