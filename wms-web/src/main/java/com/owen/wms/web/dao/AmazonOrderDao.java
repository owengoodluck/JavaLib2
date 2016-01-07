package com.owen.wms.web.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.constants.AmazonOrderStatus;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;

@Repository("amazonOrderDao")
public class AmazonOrderDao extends BaseHibernateDao<AmazonOrder,String> {
	
	public List<AmazonOrder> getOrdersByOrderIDList(String[] orderIdArray){
		StringBuffer hql = new StringBuffer("from AmazonOrder where amazonOrderId in ( ");
		for(int i = 0 ; i< orderIdArray.length;i ++){
			hql.append("'"+orderIdArray[i]+"'");
			if(i < orderIdArray.length-1){
				hql.append(",");
			}
		}
		hql.append(" ) ");
		Query query = this.getSession().createQuery(hql.toString());
		List<AmazonOrder> list = query.list();
		return list;
	}
	
	public Boolean checkIfOrderLoadedBefore(String amazonOrderId){
		String hql = "select  count(*) from AmazonOrder where amazonOrderId = '" + amazonOrderId.trim()+"'";
		Query query = this.getSession().createQuery(hql);
		Long result = (Long) query.uniqueResult();
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * return all order list which status is not Pending
	 * 
	Unshipped,
	Shipped,
	 * @return
	 */
	public List<AmazonOrder> listAllShippedAndUnShipped(){
		String hql ="from AmazonOrder where orderStatus ='Shipped' or orderStatus = 'Unshipped' order by purchaseDate desc";
		Query query = this.getSession().createQuery(hql);
		List<AmazonOrder> result = query.list();
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
