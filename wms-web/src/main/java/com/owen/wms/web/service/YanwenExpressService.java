package com.owen.wms.web.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.mws.config.Owen;
import com.amazonaws.mws.entity.yanwen.ExpressType;
import com.amazonaws.mws.entity.yanwen.GoodsName;
import com.amazonaws.mws.entity.yanwen.Receiver;
import com.amazonaws.mws.service.YanwenService;
import com.amazonaws.mws.util.JaxbUtil;
import com.amazonservices.mws.orders._2013_09_01.model.Address;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResult;
import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.amazonservices.mws.orders._2013_09_01.service.GetOrderService;
import com.owen.wms.web.form.YanwenExpress;

@Service
public class YanwenExpressService {
	private Logger log = Logger.getLogger(this.getClass());
	private YanwenService yanwenService = new YanwenService();
	private GetOrderService getOrderService = new GetOrderService();

	public void createExpressFromAmazonOrder(YanwenExpress form) {
		GetOrderResponse order = getOrderService.getOrderByID(form.getAmazonOrderID().trim());
		if (order != null) {
			ExpressType et = this.convert(order,form);
			this.yanwenService.createExpress(et);
		}
	}

	private ExpressType convert(GetOrderResponse od,YanwenExpress form) {
		GetOrderResult orderResult = od.getGetOrderResult();
		Order order = orderResult.getOrders().get(0);//TODO TBC
		
		ExpressType et = new ExpressType();
		et.setUserid(Owen.yanwenUserId);
		et.setSendDate(form.getSendDate()+"T00:00:00");// 2015-07-09T00:00:00
		et.setQuantity(form.getQuantity());
		et.setChannel(form.getChannel());//中文 ， 中邮北京平邮小包
		et.setUserOrderNumber(order.getAmazonOrderId());

		Address address = order.getShippingAddress();
		Receiver rc = new Receiver();
		et.setReceiver(rc);
		rc.setUserid(Owen.yanwenUserId);
		rc.setName(address.getName());
		rc.setPhone(address.getPhone());
		rc.setCountry(form.getCountry()); 
		rc.setState(address.getStateOrRegion());
		rc.setCity(address.getCity());
		rc.setAddress1(address.getAddressLine1());
		rc.setAddress2(address.getAddressLine2());
		rc.setPostcode(address.getPostalCode());

		GoodsName gn = new GoodsName();
		et.setGoodsName(gn);
		gn.setUserid(Owen.yanwenUserId);
		gn.setNameCh(form.getNameChinese());
		gn.setNameEn(form.getNameEnglish());
		gn.setDeclaredValue(form.getDeclaredValue());
		gn.setDeclaredCurrency(form.getDeclaredCurrency());
		gn.setWeight(form.getWeight());

		this.log.info(JaxbUtil.toXml(et));
		return et;
		
	}
}
