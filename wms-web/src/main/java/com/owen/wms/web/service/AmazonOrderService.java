package com.owen.wms.web.service;

import static com.owen.wms.web.utils.XMLGregorianCalendarUtil.xmlDate2Date;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.mws.jaxb.entity.AmazonEnvelope;
import com.amazonaws.mws.jaxb.entity.AmazonEnvelope.Message;
import com.amazonaws.mws.jaxb.entity.Header;
import com.amazonaws.mws.jaxb.entity.OrderFulfillment;
import com.amazonaws.mws.jaxb.entity.OrderFulfillment.FulfillmentData;
import com.amazonaws.mws.jaxb.entity.OrderFulfillment.Item;
import com.amazonaws.mws.service.OrderFulfillmentService;
import com.amazonaws.mws.util.JaxbUtil;
import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.amazonservices.mws.orders._2013_09_01.model.OrderItem;
import com.amazonservices.mws.orders._2013_09_01.service.ListOrderItemsService;
import com.amazonservices.mws.orders._2013_09_01.service.ListOrdersService;
import com.amazonservices.mws.orders._2013_09_01.util.XMLGregorianCalendarUtil;
import com.owen.wms.web.dao.AmazonOrderDao;
import com.owen.wms.web.dao.AmazonOrderItemDao;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.dao.YanWenExpressDao;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.entity.YanWenExpressEntity;
import com.owen.wms.web.form.OrderStatisticEntity;
import com.owen.wms.web.utils.DateUtil;
import com.owen.wms.web.utils.FileUtil;;

@Service("amazonOrderService")
@Transactional
public class AmazonOrderService {
	private Logger log = Logger.getLogger(this.getClass());

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	@Qualifier("amazonOrderDao")
	private AmazonOrderDao dao;
	
	@Autowired
	@Qualifier("amazonOrderItemDao")
	private AmazonOrderItemDao orderItemDao;

	@Autowired
	@Qualifier("yanWenExpressDao")
	private YanWenExpressDao yanWenExpressDao ;
	
	public List<OrderStatisticEntity> orderStatistics(String orderBy){
		return this.dao.statistics(orderBy);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public AmazonOrder getByOrderID(String orderId){
		AmazonOrder order = this.dao.getByOrderID(orderId);
		YanWenExpressEntity express = this.yanWenExpressDao.getByAmazonOrderId(orderId);
		if(express != null){
			order.setIsPrinted(true);
		}
		return order;
	}
	
	public Page pageListByCriteria(int currentPage, int pageSize,AmazonOrder order){
		Page page = this.dao.pageListByCriteria(currentPage, pageSize, order);
		List<AmazonOrder> list = page.getList();
		if(list!=null){
			for(AmazonOrder o: list){
				//TODO optimize
				YanWenExpressEntity express = this.yanWenExpressDao.getByAmazonOrderId(o.getAmazonOrderId());
				if(express != null){
					o.setIsPrinted(true);
				}
			}
		}
		return page;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<AmazonOrder> getOrderList(){
		List<AmazonOrder> orderList = this.dao.listAllShippedAndUnShipped();
		return orderList;
	}
	
	public void synchronizeOrderToLocalDB(Date createdAfterDate, Date createdBeforeDate, String orderStatus) {
		// 1. get order list
		ArrayList<Order> orderList = ListOrdersService.listOrders(createdAfterDate, createdBeforeDate, orderStatus);
		if (orderList != null && !orderList.isEmpty()) {
			// 2.get order items
			for (Order od : orderList) {
				//TODO  need to avoid reload orderItems many times 
				if( !this.dao.checkIfOrderLoadedBefore(od.getAmazonOrderId())){
					List<OrderItem> orderItems = ListOrderItemsService.listOrderItems(od.getAmazonOrderId());
					od.setOrderItems(orderItems);
				}
			}
		}

		List<AmazonOrder> localDBOrderList = this.converOrderList(orderList);
		this.dao.batchSaveOrUpdate(localDBOrderList);
	}

	/**
	 * TODO checkIfOrderItemsLoadedInLocalDB
	 * @param orderItems
	 * @return
	 */
	private Boolean checkIfOrderItemsLoadedInLocalDB(List<OrderItem> orderItems){
		boolean result = false;
		if(orderItems == null){
			result = false;
		}
		for(OrderItem it: orderItems){
			AmazonOrderItem item = this.orderItemDao.get(it.getOrderItemId());
			if(item !=null){
				result = true;
				break;
			}else{
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param orderIdArray
	 * @throws Exception 
	 */
	public void confirmShipFulfillment(String[] orderIdArray,String fileFoder) throws Exception{
		//1. get express map
		Map<String, YanWenExpressEntity> expressMap = this.yanWenExpressDao.getMapByAmazonOrderIds(orderIdArray);
		String[] orderIdList =expressMap.keySet().toArray(new String[]{});
		
		//2. get Oreder list which has already create express
		List<AmazonOrder> orderList = this.dao.getOrdersByOrderIDList(orderIdList);
		
		//3. create AmazonEnvelope
		AmazonEnvelope envelope = this.createEnvelop4OrderFulfillment(orderList, expressMap);
		
		//4. write content to xml file
		String xmlString=JaxbUtil.toXml(envelope);
		String filePath = fileFoder.endsWith("/") ? fileFoder+"fulfill_":fileFoder+"/fulfill_";
		File file = new File(filePath + this.sdf.format(new Date())+".xml");
		FileUtil.writeStringToFile(xmlString, file);
		
		//5.submit to Amazon
		OrderFulfillmentService.confirmShipment(file);
	}
	
	
	public AmazonEnvelope createEnvelop4OrderFulfillment(List<AmazonOrder> orderList,Map<String,YanWenExpressEntity> expressMap){
		AmazonEnvelope e = new AmazonEnvelope();
		//1. set header
		Header header  = new Header();
		e.setHeader(header  );
		header.setMerchantIdentifier("A75HRC7E5LZBX");
		header.setDocumentVersion("1.01");
		
		//2.set MessageType
		e.setMessageType("OrderFulfillment");
		
		//3. set message
		int i = 1;
		for(AmazonOrder order: orderList){
			Message msg = new Message();
			YanWenExpressEntity express = expressMap.get(order.getAmazonOrderId());
			msg.setMessageID(BigInteger.valueOf(i++));
			msg.setOrderFulfillment(getOrderFulfillmentFeed(order,express));
			e.getMessage().add( msg );
		}
		return e;
	}

	public OrderFulfillment getOrderFulfillmentFeed(AmazonOrder order,YanWenExpressEntity express){
    	OrderFulfillment of = new OrderFulfillment();
    	of.setAmazonOrderID(order.getAmazonOrderId());
    	//1.shipping confirmation date
    	XMLGregorianCalendar fulfillmentDate = XMLGregorianCalendarUtil.dateToXmlDate(DateUtil.getUTCtime());
		of.setFulfillmentDate(fulfillmentDate );
		
		//2.express information 
    	FulfillmentData fulfillmentData = new FulfillmentData();
    	//You can use CarrierName instead of CarrierCode if the list of options for CarrierCode (in the base XSD) does not contain the carrier you used.
    	//fulfillmentData.setCarrierCode("China Post");
    	String channel = express.getChannel();
    	switch(channel){
	    	case "中邮北京平邮小包" : ;
	    	case "中邮北京挂号小包" : {
	    		fulfillmentData.setCarrierName("China Post");
	    		fulfillmentData.setShippingMethod("Standard");
	    		fulfillmentData.setShipperTrackingNumber(express.getEpcode());
	    	};break;
	    	case "中邮北京E邮宝(线下)" : ;
	    	case "中邮上海E邮宝(线下)" : {
	    		fulfillmentData.setCarrierName("EUB");
	    		fulfillmentData.setShippingMethod("Standard");
	    		fulfillmentData.setShipperTrackingNumber(express.getEpcode());
	    	};break;
	    	default:;
    	}
    	
    	//fulfillmentData.setShippingMethod("");//TODO TBC
		of.setFulfillmentData(fulfillmentData);
		
		//3. order items detail 
		Set<AmazonOrderItem> items = order.getOrderItemList();
		if(items!=null ){
			for(AmazonOrderItem i : items){
				Item item  = new Item();
				item.setAmazonOrderItemCode(i.getOrderItemId());
				int quantity = i.getQuantityOrdered() !=null ?i.getQuantityOrdered() : i.getQuantityShipped();
				item.setQuantity(BigInteger.valueOf(quantity));
				of.getItem().add( item  );
			}
		}
    	return of;
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
			if(od.getShippingAddress()!=null){
				ao.setShippingAddressName(od.getShippingAddress().getName());
				ao.setShippingAddressAddressLine1(od.getShippingAddress().getAddressLine1());
				ao.setShippingAddressAddressLine2(od.getShippingAddress().getAddressLine2());
				ao.setShippingAddressAddressLine3(od.getShippingAddress().getAddressLine3());
				ao.setShippingAddressCity(od.getShippingAddress().getCity());
				ao.setShippingAddressCountryCode(od.getShippingAddress().getCounty());
				ao.setShippingAddressCounty(od.getShippingAddress().getCounty());
				ao.setShippingAddressDistrict(od.getShippingAddress().getDistrict());
				ao.setShippingAddressStateOrRegion(od.getShippingAddress().getStateOrRegion());
				ao.setShippingAddressPostalCode(od.getShippingAddress().getPostalCode());
				ao.setShippingAddressPhone(od.getShippingAddress().getPhone());
			}
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
		AmazonOrderItem ao =null ;//TODO this.orderItemDao.get(od.getOrderItemId());
		if(ao == null){
			ao = new AmazonOrderItem();
		}
		if(od!=null){
			ao.setOrderItemId(od.getOrderItemId());
			ao.setASIN(od.getASIN());
			if(ao.getSellerSKU() == null){
				JewelryEntity ent = new JewelryEntity();
				ent.setItemSku(od.getSellerSKU());
				ao.setSellerSKU(ent );
			}
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
			ao.setConditionId(od.getConditionId());
			ao.setConditionSubtypeId(od.getConditionSubtypeId());
		}
		return ao;
	}
}
