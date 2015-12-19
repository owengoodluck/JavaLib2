package com.owen.wms.web.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.entity.AmazonOrder;

@Repository("amazonOrderDao")
public class AmazonOrderDao extends BaseHibernateDao<AmazonOrder,Long> {
	public AmazonOrder getByOrderID(String amazonOrderId){
		Criterion criteria = Restrictions.eq("amazonOrderId", amazonOrderId);
		AmazonOrder order = this.uniqueResult(criteria);
//		order.getOrderItemList();//fetch item list detail
		return order;
	}
	
	public void batchSave(List<AmazonOrder> orderList){
		if(orderList!=null &&!orderList.isEmpty()){
			for(AmazonOrder od : orderList){
				this.save(od);
			}
		}
	}
	public void batchSaveOrUpdate(List<AmazonOrder> orderList){
		if(orderList!=null &&!orderList.isEmpty()){
			for(AmazonOrder od : orderList){
				this.saveOrUpdate(od);
			}
		}
	}
	
}
