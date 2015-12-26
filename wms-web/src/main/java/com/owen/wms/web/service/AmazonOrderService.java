package com.owen.wms.web.service;

import static com.owen.wms.web.utils.XMLGregorianCalendarUtil.xmlDate2Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.amazonservices.mws.orders._2013_09_01.model.OrderItem;
import com.amazonservices.mws.orders._2013_09_01.service.ListOrderItemsService;
import com.amazonservices.mws.orders._2013_09_01.service.ListOrdersService;
import com.owen.wms.web.dao.AmazonOrderDao;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.entity.JewelryEntity;;

@Service("amazonOrderService")
@Transactional
public class AmazonOrderService {
	private Logger log = Logger.getLogger(this.getClass());

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	@Qualifier("amazonOrderDao")
	private AmazonOrderDao dao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public AmazonOrder getByOrderID(String orderId){
		AmazonOrder order = this.dao.getByOrderID(orderId);
		return order;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<AmazonOrder> getOrderList(){
//		List<AmazonOrder> orderList = this.dao.list("purchaseDate",false);
		List<AmazonOrder> orderList = this.dao.listNonPendingOrder();
		return orderList;
	}
	
	public void synchronizeOrderToLocalDB(Date createdAfterDate, Date createdBeforeDate, String orderStatus) {
		// 1. get order list
		List<Order> orderList = ListOrdersService.listOrders(createdAfterDate, createdBeforeDate, orderStatus);
		if (orderList != null && !orderList.isEmpty()) {
			// 2.get order items
			for (Order od : orderList) {
				List<OrderItem> orderItems = ListOrderItemsService.listOrderItems(od.getAmazonOrderId());
				od.setOrderItems(orderItems);
			}
		}

		List<AmazonOrder> localDBOrderList = this.converOrderList(orderList);
		this.dao.batchSaveOrUpdate(localDBOrderList);
	}

	private List<AmazonOrder> converOrderList(List<Order> orderList) {
		List<AmazonOrder> localDBOrderList = new ArrayList<AmazonOrder>();
		if (orderList != null && !orderList.isEmpty()) {
			for (Order od : orderList) {
				localDBOrderList.add(this.converOrder(od));
			}
		}
		return localDBOrderList;
	}

	private AmazonOrder converOrder(Order od) {
		AmazonOrder ao = new AmazonOrder();
		if (od != null) {
			ao.setAmazonOrderId(od.getAmazonOrderId());
			ao.setPurchaseDate(xmlDate2Date(od.getPurchaseDate()));
			ao.setPurchaseDate(xmlDate2Date(od.getPurchaseDate()));
			ao.setLastUpdateDate(xmlDate2Date(od.getLastUpdateDate()));
			ao.setOrderStatus(od.getOrderStatus());
			ao.setFulfillmentChannel(od.getFulfillmentChannel());
			ao.setSalesChannel(od.getSalesChannel());
			ao.setShipServiceLevel(od.getShipServiceLevel());
			ao.setNumberOfItemsShipped(od.getNumberOfItemsShipped());
			ao.setNumberOfItemsUnshipped(od.getNumberOfItemsUnshipped());
			ao.setMarketplaceId(od.getMarketplaceId());
			ao.setShipmentServiceLevelCategory(od.getShipmentServiceLevelCategory());
			ao.setOrderType(od.getOrderType());
			ao.setEarliestShipDate(xmlDate2Date(od.getEarliestShipDate()));
			ao.setLatestShipDate(xmlDate2Date(od.getLatestShipDate()));
			ao.setIsBusinessOrder(od.getIsBusinessOrder());
			ao.setIsPrime(od.getIsPrime());
			ao.setIsPremiumOrder(od.getIsPremiumOrder());
			if(od.getOrderItems() !=null && !od.getOrderItems().isEmpty()){
				ao.setOrderItemList(new HashSet(this.converOrderItemList(ao,od.getOrderItems())));
			}
		}
		return ao;
	}

	private List<AmazonOrderItem> converOrderItemList(AmazonOrder ao,List<OrderItem> orderList) {
		if (orderList != null && !orderList.isEmpty()) {
			List<AmazonOrderItem> localDBOrderItemList = new ArrayList<AmazonOrderItem>();
			for (OrderItem od : orderList) {
				AmazonOrderItem item = this.converOrderItem(od);
				item.setOrder(ao);
				localDBOrderItemList.add(item);
			}
			return localDBOrderItemList;
		}else{
			return null;
		}
	}
	
	private AmazonOrderItem converOrderItem(OrderItem od){
		AmazonOrderItem ao = new AmazonOrderItem();
		if(od!=null){
			ao.setASIN(od.getASIN());
			JewelryEntity ent = new JewelryEntity();
			ent.setItemSku(od.getSellerSKU());
			ao.setSellerSKU(ent );
			ao.setOrderItemId(od.getOrderItemId());
			ao.setTitle(od.getTitle());
			ao.setQuantityOrdered(od.getQuantityOrdered());
			ao.setQuantityShipped(od.getQuantityShipped());
			if(od.getItemPrice()!=null){
				ao.setItemPriceCurrencyCode(od.getItemPrice().getCurrencyCode());
				ao.setItemPriceAmount(Double.valueOf(od.getItemPrice().getAmount()));
			}
			if(od.getShippingPrice()!=null){
				ao.setShippingPriceCurrencyCode(od.getShippingPrice().getCurrencyCode());
				ao.setShippingPriceAmount(Double.valueOf(od.getShippingPrice().getAmount()));
			}
			if(od.getGiftWrapPrice()!=null){
				ao.setGiftWrapPriceCurrencyCode(od.getGiftWrapPrice().getCurrencyCode());
				ao.setGiftWrapPriceAmount(Double.valueOf(od.getGiftWrapPrice().getAmount()));
			}
			if(od.getItemTax()!=null){
				ao.setItemTaxCurrencyCode(od.getItemTax().getCurrencyCode());
				ao.setItemTaxAmount(Double.valueOf(od.getItemTax().getAmount()));
			}
			if(od.getShippingTax()!=null){
				ao.setShippingTaxCurrencyCode(od.getShippingTax().getCurrencyCode());
				ao.setShippingTaxAmount(Double.valueOf(od.getShippingTax().getAmount()));
			}
			if(od.getGiftWrapTax()!=null){
				ao.setGiftWrapTaxCurrencyCode(od.getGiftWrapTax().getCurrencyCode());
				ao.setGiftWrapTaxAmount(Double.valueOf(od.getGiftWrapTax().getAmount()));
			}
			if(od.getShippingDiscount()!=null){
				ao.setShippingDiscountCurrencyCode(od.getShippingDiscount().getCurrencyCode());
				ao.setShippingDiscountAmount(Double.valueOf(od.getShippingDiscount().getAmount()));
			}
			if(od.getPromotionDiscount()!=null){
				ao.setPromotionDiscountCurrencyCode(od.getPromotionDiscount().getCurrencyCode());
				ao.setPromotionDiscountAmount(Double.valueOf(od.getPromotionDiscount().getAmount()));
			}
			ao.setConditionId(od.getASIN());
			ao.setConditionSubtypeId(od.getASIN());
		}
		return ao;
	}
}
