package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owen.wms.web.dao.Page;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.form.OrderQueryForm;
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
	private int defaultPageSize = 20;
	
	@RequestMapping(value = "/confirmShipment", method = RequestMethod.POST)
	public String confirmShipment(Model model,HttpServletRequest request) throws Exception{
		String[] amazonOrderIds = request.getParameterValues("amazonOrderIds");
		if(amazonOrderIds!=null && amazonOrderIds.length>0){
			String tmpFolder = request.getSession().getServletContext().getRealPath("/tmp");
			this.amazonOrderService.confirmShipFulfillment(amazonOrderIds, tmpFolder);
		}
		
		return listOrder(model) ;//TODO
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listOrder(Model model) {
		AmazonOrder order = new AmazonOrder();
		Page page = this.amazonOrderService.pageListByCriteria(1, defaultPageSize, order );
		OrderQueryForm orderQueryForm = new OrderQueryForm();
		orderQueryForm.setCurrentPage(page.getCurrentPage());
		orderQueryForm.setPageSize(page.getPageSize());
		model.addAttribute("page", page);
		model.addAttribute("orderQueryForm", orderQueryForm);

		model.addAttribute("currentMenu", "order");
		return "order/orders";
	}
	
	@RequestMapping(value="/queryOrders", method = RequestMethod.POST)
	public String queryOrders(Model model,@ModelAttribute("orderQueryForm") OrderQueryForm orderQueryForm) throws Exception {
		AmazonOrder order = new AmazonOrder();
		if(orderQueryForm.getQueryOrderStatus() != null && orderQueryForm.getQueryOrderStatus().trim().length()>0){
			order.setOrderStatus(orderQueryForm.getQueryOrderStatus());
		}
		if(orderQueryForm.getQueryOrderID() != null && orderQueryForm.getQueryOrderID().trim().length()>0){
			order.setAmazonOrderId(orderQueryForm.getQueryOrderID());
		}
		if(orderQueryForm.getQueryOrderDateFrom() != null && orderQueryForm.getQueryOrderDateFrom().trim().length()>0){
			order.setPurchaseDateFrom(this.sdf.parse(orderQueryForm.getQueryOrderDateFrom() ));
		}
		if (orderQueryForm.getQueryOrderDateTo() != null && orderQueryForm.getQueryOrderDateTo().trim().length()>0){
			order.setPurchaseDateTo(this.sdf.parse(orderQueryForm.getQueryOrderDateTo() ));
		}
		Page page = this.amazonOrderService.pageListByCriteria(orderQueryForm.getCurrentPage(), orderQueryForm.getPageSize(), order );
		
		model.addAttribute("page", page);
		model.addAttribute("currentMenu", "order");
		return "order/orders";
	}
	
	@RequestMapping(value="/detail/{orderId}", method = RequestMethod.GET)
	public String getOrderById(Model model,@PathVariable("orderId") String orderId) {
		AmazonOrder result = this.amazonOrderService.getByOrderID(orderId);
		model.addAttribute("order", result);
		model.addAttribute("currentMenu", "order");
		return "order/orderDetail";
	}
	
	private void setSynchronizeForm(Model model){
		OrderSynchronizeForm synForm = new OrderSynchronizeForm();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		synForm.setStartDateStr(this.sdf.format(cal.getTime()));
		model.addAttribute("synForm",synForm);
	}
	
	@RequestMapping(value = "/synchronzieOrders", method = RequestMethod.GET)
	public String preSynchronize(Model model) throws Exception{
		this.setSynchronizeForm(model);
		model.addAttribute("currentMenu", "order");
		return "order/synchronzieOrders";
	}
	
	
	@RequestMapping(value = "/synchronzieOrders", method = RequestMethod.POST)
	public String synchronize(@ModelAttribute("synForm") OrderSynchronizeForm synForm,Model model) throws Exception{
		//1.synchronize orders
		Date createdAfterDate = this.sdf.parse(synForm.getStartDateStr());//not include this day ??
		Date createdBeforeDate = null;
		if(synForm.getEndDateStr()!=null && synForm.getEndDateStr().trim().length()>0){
			createdBeforeDate= this.sdf.parse(synForm.getEndDateStr());//include this day ??
		}
		String orderStatus = null; 
		amazonOrderService.synchronizeOrderToLocalDB(createdAfterDate, createdBeforeDate, orderStatus);
		
		//2. get order list and return 
		return this.listOrder(model);
	}
	
	private List<AmazonOrder> getOrderList(){
		return this.amazonOrderService.getOrderList();
	}
}
