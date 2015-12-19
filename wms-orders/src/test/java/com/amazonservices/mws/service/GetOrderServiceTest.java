package com.amazonservices.mws.service;

import org.junit.Test;

import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.service.GetOrderService;

public class GetOrderServiceTest {
	private GetOrderService service = new GetOrderService();
	
	@Test
	public void test(){
		String orderID = "116-6760525-0869037";
		GetOrderResponse resp = this.service.getOrderByID(orderID );
		System.out.println(resp);
	}
}
