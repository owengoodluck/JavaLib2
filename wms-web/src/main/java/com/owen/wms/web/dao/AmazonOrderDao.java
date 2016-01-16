package com.owen.wms.web.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.constants.AmazonOrderStatus;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.form.OrderStatisticEntity;

@Repository("amazonOrderDao")
public class AmazonOrderDao extends BaseHibernateDao<AmazonOrder,String> {
	
	public List<AmazonOrder> getOrdersByOrderIDList(String[] orderIdArray){
		if(orderIdArray==null || orderIdArray.length<1){
			return new ArrayList();
		}
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
	
	public Page pageListByCriteria(int currentPage,int pageSize,AmazonOrder order){
		Map<String,Object> criteriaMap = new HashMap();
		StringBuffer hql = new StringBuffer(" from AmazonOrder where 1 =1 ");
		if(order!=null){
			if(order.getAmazonOrderId()!=null &&  order.getAmazonOrderId().trim().length()>0){
				hql.append(" and amazonOrderId like :amazonOrderId ");
				criteriaMap.put("amazonOrderId", "%"+order.getAmazonOrderId().trim()+"%");
			}else{
				if(order.getPurchaseDateFrom()!=null){
					hql.append(" and purchaseDate >=  :purchaseDateFrom ");
					criteriaMap.put("purchaseDateFrom", order.getPurchaseDateFrom() );
				}
				
				if(order.getPurchaseDateTo()!=null){
					hql.append(" and purchaseDate <  :purchaseDateTo ");
					criteriaMap.put("purchaseDateTo", order.getPurchaseDateTo() );
				}
				
				if(order.getOrderStatus()!=null){
					hql.append(" and orderStatus =  :orderStatus ");
					criteriaMap.put("orderStatus", order.getOrderStatus() );
				}
			}
		}
		List<AmazonOrder> list = this.findPageByQuery(currentPage, pageSize, hql.append(" order by purchaseDate desc").toString(), criteriaMap);
		int totalCount = this.getTotalCount("select count(*) "+hql.toString(), criteriaMap);
		return new Page(currentPage,pageSize,totalCount,list);
	}
	
	/**
	 * select sellerSKU,sell_count,latest_date,stock_quantity,main_image_url from (
			select sellerSKU,count(*) as sell_count, max(purchaseDate) latest_date from(
				select sellerSKU,purchaseDate
				from amazon_order o,amazon_order_item i
				where i.orderID = o.amazonOrderId
			) a
			group by sellerSKU
			) b ,amz_jewelry c
		where b.sellerSKU = c.item_sku
		order by sell_count desc;
	 */
	public List<OrderStatisticEntity> statistics(String orderBy){
		StringBuffer sql = new StringBuffer();
		sql.append(" select item_sku as itemSku ,sell_count as sellCount,latest_date as latestDate,stock_quantity as stockQuantity,main_image_url as mainImageUrl from ( ");
		sql.append("	select sellerSKU,count(*) as sell_count, max(purchaseDate) latest_date from(");
		sql.append(" 		select sellerSKU,purchaseDate");
		sql.append(" 		from amazon_order o,amazon_order_item i");
		sql.append(" 		where i.orderID = o.amazonOrderId");
		sql.append(" 		and (o.orderStatus = 'Shipped' or o.orderStatus = 'Unshipped' )");
		sql.append("	) a");
		sql.append(" 	group by sellerSKU");
		sql.append(" ) b ,amz_jewelry c");
		sql.append(" where b.sellerSKU = c.item_sku ");
		if("latestDate".equalsIgnoreCase(orderBy)){
			sql.append(" order by  latestDate desc");
		}else{
			sql.append(" order by  sellCount desc");
		}
		System.out.println(sql.toString());
		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		
		query.setResultTransformer(new ResultTransformer(){
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				OrderStatisticEntity e = new OrderStatisticEntity();
				e.setItemSku(tuple[0].toString());
				e.setSellCount(Integer.valueOf(tuple[1].toString()));
				Timestamp t = ( Timestamp )tuple[2] ;
				e.setLatestDate(new Date(t.getTime()));
				e.setStockQuantity(Integer.valueOf(tuple[3].toString()));
				e.setMainImageUrl(tuple[4].toString());
				return e;
			}

			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		
		List<OrderStatisticEntity> list = query.list();
		return list;
	}
}
