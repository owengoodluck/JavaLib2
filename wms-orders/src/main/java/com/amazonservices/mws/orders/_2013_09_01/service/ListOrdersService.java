/*******************************************************************************
 * Copyright 2009-2015 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Orders
 * API Version: 2013-09-01
 * Library Version: 2015-09-24
 * Generated: Fri Sep 25 20:06:20 GMT 2015
 */
package com.amazonservices.mws.orders._2013_09_01.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.amazonaws.mws.config.Owen;
import com.amazonservices.mws.client.MwsUtl;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrders;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersException;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersByNextTokenResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersByNextTokenResult;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersResult;
import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.amazonservices.mws.orders._2013_09_01.model.ResponseHeaderMetadata;
import com.amazonservices.mws.orders._2013_09_01.samples.MarketplaceWebServiceOrdersSampleConfig;
import com.amazonservices.mws.orders._2013_09_01.util.XMLGregorianCalendarUtil;


/** Sample call for ListOrders. */
public class ListOrdersService {
	private static Integer maxResultsPerPage = 20;
    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    private static ListOrdersResponse invokeListOrders(
            MarketplaceWebServiceOrders client, 
            ListOrdersRequest request) {
        try {
            // Call the service.
            ListOrdersResponse response = client.listOrders(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            return response;
        } catch (MarketplaceWebServiceOrdersException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }

    /**
     *  Command line entry point.
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	Date createdAfterDate= sdf.parse("20151115");
    	Date createdBeforeDate = null;
    	String orderStatus = null;
    	listOrders(createdAfterDate,createdBeforeDate,orderStatus);
    }

    public static ArrayList<Order> listOrders(Date createdAfterDate,Date createdBeforeDate,String orderStatus){
    	ArrayList<Order> orderList = new ArrayList<Order> ();
    	//1. get ListOrdersResponse base on input parameters
    	ListOrdersResponse resp = listOrdersResponse( createdAfterDate,createdBeforeDate, orderStatus );
    	if(resp!=null && resp.getListOrdersResult()!=null){
    		ListOrdersResult listOrdersResult = resp.getListOrdersResult();
    		orderList.addAll(listOrdersResult.getOrders());
    		
    		//2. get next pages if exist
    		String nextToken = listOrdersResult.getNextToken();
    		ListOrdersByNextTokenResponse listOrdersByNextTokenResponse = null;
    		ListOrdersByNextTokenResult listOrdersByNextTokenResponseResult = null;
    		while( nextToken!=null && nextToken.trim().length()>0){
    			listOrdersByNextTokenResponse = ListOrdersByNextTokenService.listOrdersByNextToken(nextToken);
    			listOrdersByNextTokenResponseResult = listOrdersByNextTokenResponse.getListOrdersByNextTokenResult();
    			orderList.addAll(listOrdersByNextTokenResponseResult.getOrders());
    			nextToken = listOrdersByNextTokenResponseResult.getNextToken();
    		}
    	}
    	return orderList;
    }
    
    private static ListOrdersResponse listOrdersResponse(Date createdAfterDate,Date createdBeforeDate,String orderStatus ){
    	// Get a client connection.
    	// Make sure you've set the variables in MarketplaceWebServiceOrdersSampleConfig.
    	MarketplaceWebServiceOrdersClient client = MarketplaceWebServiceOrdersSampleConfig.getClient();
    	// Create a request.
    	ListOrdersRequest request = new ListOrdersRequest();
    	request.setMaxResultsPerPage(maxResultsPerPage);
    	
    	request.setCreatedAfter(XMLGregorianCalendarUtil.dateToXmlDate(createdAfterDate));
    	
    	if(createdBeforeDate!=null){
    		request.setCreatedBefore(XMLGregorianCalendarUtil.dateToXmlDate(createdBeforeDate));
    	}
    	
        request.setSellerId(Owen.sellerId);
        
        List<String> marketplaceId = new ArrayList<String>();
        marketplaceId.add("ATVPDKIKX0DER");//TODO
        request.setMarketplaceId(marketplaceId);
        
        String mwsAuthToken = "example";
        request.setMWSAuthToken(mwsAuthToken);
        
        if(orderStatus!=null){
        	List<String> orderStatuses = new ArrayList<String>();
        	orderStatuses.add(orderStatus);
        	request.setOrderStatus(orderStatuses);
        }
        
//        XMLGregorianCalendar lastUpdatedAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
//        request.setLastUpdatedAfter(lastUpdatedAfter);
//        XMLGregorianCalendar lastUpdatedBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
//        request.setLastUpdatedBefore(lastUpdatedBefore);
//        List<String> fulfillmentChannel = new ArrayList<String>();
//        request.setFulfillmentChannel(fulfillmentChannel);
//        List<String> paymentMethod = new ArrayList<String>();
//        request.setPaymentMethod(paymentMethod);
//        String buyerEmail = "example";
//        request.setBuyerEmail(buyerEmail);
//        String sellerOrderId = "example";
//        request.setSellerOrderId(sellerOrderId);
//        List<String> tfmShipmentStatus = new ArrayList<String>();
//        request.setTFMShipmentStatus(tfmShipmentStatus);
        
        // Make the call.
        ListOrdersResponse response = ListOrdersService.invokeListOrders(client, request);
        
        String nextToken = response.getListOrdersResult().getNextToken();
        if(nextToken !=null){
        	ListOrdersByNextTokenService.listOrdersByNextToken(nextToken);
        }
    
        return response;
    }
}
