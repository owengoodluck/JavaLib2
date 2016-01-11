package com.owen.wms.web.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.mws.config.Owen;
import com.amazonaws.mws.entity.yanwen.ExpressType;
import com.amazonaws.mws.entity.yanwen.GoodsName;
import com.amazonaws.mws.entity.yanwen.Receiver;
import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.amazonaws.mws.service.YanwenService;
import com.amazonaws.mws.util.JaxbUtil;
import com.amazonservices.mws.orders._2013_09_01.model.Address;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResult;
import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.amazonservices.mws.orders._2013_09_01.service.GetOrderService;
import com.owen.wms.web.constants.AmazonOrderStatus;
import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.dao.AmazonOrderDao;
import com.owen.wms.web.dao.AmazonOrderItemDao;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.dao.YanWenExpressDao;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.entity.YanWenExpressEntity;
import com.owen.wms.web.form.ExpressQueryForm;
import com.owen.wms.web.form.YanwenExpress;
import com.owen.wms.web.utils.PdfPrintUtil;

@Service
@Transactional
public class YanwenExpressService {
	private Logger log = Logger.getLogger(this.getClass());
	private YanwenService yanwenService = new YanwenService();
	private GetOrderService getOrderService = new GetOrderService();
	private SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	@Autowired
	@Qualifier("amazonOrderDao")
	private AmazonOrderDao amazonOrderDao;
	
	@Autowired
	@Qualifier("amazonOrderItemDao")
	private AmazonOrderItemDao orderItemDao;	
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;

	@Autowired
	@Qualifier("yanWenExpressDao")
	private YanWenExpressDao yanWenExpressDao;
	
	/**
	 * page query
	 * @param queryForm
	 * @return
	 * @throws Exception
	 */
	public Page pageQuery(ExpressQueryForm queryForm) throws Exception{
		return this.yanWenExpressDao.pageListByCriteria(queryForm);
	}
	
	/**
	 * list all
	 * @return
	 */
	public List<YanWenExpressEntity> listAll(){
		return this.yanWenExpressDao.listAll();
	}
	
	
	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public CreateExpressResponseType createExpressFromAmazonOrder(YanwenExpress form) throws Exception {
		//1. get Amazon order info
		AmazonOrder orderEntity = this.amazonOrderDao.get(form.getAmazonOrderID().trim());
		CreateExpressResponseType result = null;
		if (orderEntity!=null) {
			
			ExpressType et = this.convert(orderEntity,form);
			
			//2.create Yanwen express
			result = this.yanwenService.createExpress(et);
			if(result.isCallSuccess()){
				String epCode=result.getCreatedExpress().getEpcode();
				et.setEpcode(epCode);
				
				//3. down load pdf to local
				String pdfFilePath = this.yanwenService.downloadLabel(epCode, form.getDownloadPath(),form.getAmazonOrderID().trim());
				
				//4. save to DB
				YanWenExpressEntity entity = this.convert2Entity(et);
				this.yanWenExpressDao.saveOrUpdate(entity );
				
				//5. update product stock
				if(form.getSequenceNo()==null || form.getSequenceNo().trim().length()<1){//update only once
					this.updateStock(orderEntity);
				}
				
				//6. print pdf label
				PdfPrintUtil.printViaCommandLine(pdfFilePath);
				
				//7 . update order print status
				orderEntity.setIsPrinted(true);
				this.amazonOrderDao.update(orderEntity);
			}else{
				this.log.error("Fail to create Yanwen express:"+result.getResp().getReason()+result.getResp().getReasonMessage());
			}
		}
		return result;
	}
	
	private void updateStock(AmazonOrder orderEntity){
		if(orderEntity ==null 
				|| orderEntity.getOrderStatus() == AmazonOrderStatus.Canceled.toString() 
				|| orderEntity.getOrderStatus() == AmazonOrderStatus.Pending.toString()){
			return ;
		}
		Set<AmazonOrderItem> orderItems = orderEntity.getOrderItemList();
		if(orderItems != null && !orderItems.isEmpty()){
			for(AmazonOrderItem item:orderItems){
				JewelryEntity jewelryEntity = this.amazonJewelryDao.load(item.getSellerSKU().getItemSku());
				int quantity = item.getQuantityOrdered() == 0 ? item.getQuantityShipped() : item.getQuantityOrdered();//TODO pay attention 
				if(jewelryEntity.getStockQuantity() == null){
					jewelryEntity.setStockQuantity( 0 - quantity );
				}else{
					jewelryEntity.setStockQuantity( jewelryEntity.getStockQuantity() - quantity );
				}
				this.amazonJewelryDao.update(jewelryEntity);
			}
		}
	}
	
	private ExpressType convert(AmazonOrder orderEntity, YanwenExpress form) {
		ExpressType et = new ExpressType();
		et.setUserid(Owen.yanwenUserId);
		et.setSendDate(form.getSendDate()+"T00:00:00");// 2015-07-09T00:00:00
		et.setQuantity(form.getQuantity());
		et.setChannel(form.getChannel());//中文 ， 中邮北京平邮小包
		String userOrderNumber = orderEntity.getAmazonOrderId();
		if(form.getSequenceNo()!=null && form.getSequenceNo().trim().length()>0){
			userOrderNumber+="_"+form.getSequenceNo();
		}
		et.setUserOrderNumber(userOrderNumber);

		Receiver rc = new Receiver();
		et.setReceiver(rc);
		rc.setUserid(Owen.yanwenUserId);
		rc.setName(orderEntity.getShippingAddressName());
		rc.setPhone(orderEntity.getShippingAddressPhone());
		rc.setCountry(form.getCountry()); 
		rc.setState(orderEntity.getShippingAddressStateOrRegion());
		rc.setCity(orderEntity.getShippingAddressCity());
		rc.setAddress1(orderEntity.getShippingAddressAddressLine1());
		rc.setAddress2(orderEntity.getShippingAddressAddressLine2());
		rc.setPostcode(orderEntity.getShippingAddressPostalCode());

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

	@Deprecated
	public CreateExpressResponseType createExpressFromAmazonOrderWMS(YanwenExpress form) throws Exception {
		//1. get Amazon order info
		GetOrderResponse order = getOrderService.getOrderByID(form.getAmazonOrderID().trim());
		CreateExpressResponseType result = null;
		if (order != null 
			&& order.getGetOrderResult()!=null 
			&& order.getGetOrderResult().getOrders()!=null 
			&&!order.getGetOrderResult().getOrders().isEmpty()) {
			
			ExpressType et = this.convert(order,form);
			
			//2.create Yanwen express
			result = this.yanwenService.createExpress(et);
			if(result.isCallSuccess()){
				String epCode=result.getCreatedExpress().getEpcode();
				et.setEpcode(epCode);
				
				//3. down load pdf to local
				String pdfFilePath = this.yanwenService.downloadLabel(epCode, form.getDownloadPath(),form.getAmazonOrderID().trim());
				
				//4. save to DB
				YanWenExpressEntity entity = this.convert2Entity(et);
				this.yanWenExpressDao.saveOrUpdate(entity );
				
				//5. print pdf label
				PdfPrintUtil.printViaCommandLine(pdfFilePath);
			}else{
				this.log.error("Fail to create Yanwen express:"+result.getResp().getReason()+result.getResp().getReasonMessage());
			}
		}
		
		return result;
	}

	private ExpressType convert(GetOrderResponse od,YanwenExpress form) {
		GetOrderResult orderResult = od.getGetOrderResult();
		Order order = orderResult.getOrders().get(0);//TODO TBC
		
		ExpressType et = new ExpressType();
		et.setUserid(Owen.yanwenUserId);
		et.setSendDate(form.getSendDate()+"T00:00:00");// 2015-07-09T00:00:00
		et.setQuantity(form.getQuantity());
		et.setChannel(form.getChannel());//中文 ， 中邮北京平邮小包
		String userOrderNumber = order.getAmazonOrderId();
		if(form.getSequenceNo()!=null && form.getSequenceNo().trim().length()>0){
			userOrderNumber+="_"+form.getSequenceNo();
		}
		et.setUserOrderNumber(userOrderNumber);

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
	
	private YanWenExpressEntity convert2Entity(ExpressType i) throws Exception{
		YanWenExpressEntity e = new YanWenExpressEntity();
		e.setEpcode(i.getEpcode());
		e.setUserid(i.getUserid());
		e.setChannel(i.getChannel());
		e.setUserOrderNumber(i.getUserOrderNumber());
		e.setSendDate(this.sdf.parse(i.getSendDate()));
		e.setMemo(i.getMemo());
		
		if(i.getGoodsName()!=null){
			GoodsName g = i.getGoodsName();
			e.setNameCh(g.getNameCh());
			e.setNameEn(g.getNameEn());
			e.setWeight(g.getWeight());
			e.setDeclaredValue(g.getDeclaredValue());
			e.setDeclaredCurrency(g.getDeclaredCurrency());
		}
		
		if(i.getReceiver()!=null){
			Receiver r = i.getReceiver();
			e.setName(r.getName());
			e.setPhone(r.getPhone());
			e.setMobile(r.getMobile());
			e.setEmail(r.getEmail());
			e.setCompany(r.getCompany());
			e.setCountry(r.getCountry());
			e.setPostcode(r.getPostcode());
			e.setState(r.getState());
			e.setCity(r.getCity());
			e.setAddress1(r.getAddress1());
			e.setAddress2(r.getAddress2());
		}
		return e;
	}
}
