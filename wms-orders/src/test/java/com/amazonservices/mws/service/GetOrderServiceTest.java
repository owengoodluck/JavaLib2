package com.amazonservices.mws.service;

import org.junit.Test;

import com.amazonservices.mws.orders._2013_09_01.service.GetOrderService;

public class GetOrderServiceTest {
	private GetOrderService service = new GetOrderService();
	
	@Test
	public void test(){
		String orderID = "111-2651851-0898603";
		this.service.getOrderByID(orderID );
	}
}
