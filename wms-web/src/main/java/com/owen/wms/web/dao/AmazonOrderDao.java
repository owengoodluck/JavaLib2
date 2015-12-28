package com.owen.wms.web.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.constants.AmazonOrderStatus;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;

@Repository("amazonOrderDao")
public class AmazonOrderDao extends BaseHibernateDao<AmazonOrder,String> {
	/**
	 * return all order list which status is not Pending
	 * @return
	 */
	public List<AmazonOrder> listNonPendingOrder(){
		Criterion orderStatusIsPending = Restrictions.eq("orderStatus", "Pending");
		Criteria criteria = createCriteria(Restrictions.not(orderStatusIsPending));
		criteria.addOrder(Order.desc("purchaseDate"));//"purchaseDate",false
		List<AmazonOrder> result = this.list(criteria);
		return result;
	}
	
	/**
	 * list by status 
	 * @return
	 */
	public List<AmazonOrder> listByStatus(AmazonOrderStatus status){
		Criteria criteria = createCriteria();
		criteria.addOrder(Order.desc("purchaseDate"));//"purchaseDate",false
		if(AmazonOrderStatus.All != status){
			Criterion orderStatus = Restrictions.eq("orderStatus", status);
			criteria.add(orderStatus);
		}
		List<AmazonOrder> result = this.list(criteria);
		return result;
	}

	public AmazonOrder getByOrderID(String amazonOrderId){
		Criterion criteria = Restrictions.eq("amazonOrderId", amazonOrderId);
		AmazonOrder order = this.uniqueResult(criteria);
		Set<AmazonOrderItem> list = order.getOrderItemList();
		if(list != null){
			for(AmazonOrderItem i:list){
				Hibernate.initialize(i.getSellerSKU());
			}
		}
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
