package com.owen.wms.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.entity.JewelryEntity;

@Repository("amazonJewelryDao")
public class AmazonJewelryDao extends BaseHibernateDao<JewelryEntity,String> {
	/**
	 * 
	 * @param sku
	 * @return
	 */
	public ArrayList<JewelryEntity>  findBySKU(String sku){
		ArrayList<JewelryEntity> list = new ArrayList<JewelryEntity>();
		Criteria parentCriteria = createCriteria();
		parentCriteria.add(Restrictions.eq("itemSku", sku));
		JewelryEntity  parent =this.uniqueResult(parentCriteria);
		if(parent!=null){
			list.add(parent);
			
			//find children
			Criteria childCriteria = createCriteria(Restrictions.eq("parentSku", sku));
			childCriteria.addOrder(Order.asc("itemSku"));
			List<JewelryEntity> result = this.list(childCriteria);
			if(result !=null && !result.isEmpty()){
				list.addAll(result);
			}
		}
		return list;
	}
}
