package com.owen.wms.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.JewelryEntity;

@Repository("amazonJewelryDao")
public class AmazonJewelryDao extends BaseHibernateDao<JewelryEntity,String> {
	public Boolean isParent(String sku){
		String hql="select count(*) from JewelryEntity e where e.parentSku ='"+sku+"'";
		Query query = this.getSession().createQuery(hql);
		Long result = (Long) query.uniqueResult();
		if(result>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * return all children and itself
	 * @param sku
	 * @return
	 */
	public ArrayList<JewelryEntity>  findBySKUWithChild(String sku){
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
	/**
	 * Return it's parent if exist and all its brother
	 * @param sku
	 * @return
	 */
	public ArrayList<JewelryEntity>  findBySKUWithParentAndAllBrothers(String sku){
		JewelryEntity e = this.get(sku);
		
		if(e!=null && e.getParentSku()!=null && e.getParentSku().trim().length()>0){
			return this.findBySKUWithChild(e.getParentSku().trim());
		}
		
		ArrayList<JewelryEntity> list = new ArrayList<JewelryEntity>();
		if(e!=null){
			list.add(e);
		}
		return list;
		
	}
	
	public List<JewelryEntity> listAllParent(){
		Query query = this.getSession().createQuery("from JewelryEntity e where e.parentSku is null or e.parentSku ='' or e.parentSku = e.itemSku order by updateDate desc");
		List<JewelryEntity> result = query.list();
		return result;
	}
	
	public Page pageListByCriteria(int currentPage,int pageSize,JewelryEntity entity){
		Map<String,Object> criteriaMap = new HashMap();
		StringBuffer hql = new StringBuffer(" from JewelryEntity where 1 = 1 ");
		if(entity!=null){
			if(entity.getItemSku()!=null &&  entity.getItemSku().trim().length()>0){
				hql.append(" and itemSku like :itemSku ");
				criteriaMap.put("itemSku", "%"+entity.getItemSku().trim()+"%");
			}
			if(entity.getItemName()!=null &&  entity.getItemName().trim().length()>0){
				hql.append(" and itemName like :itemName ");
				criteriaMap.put("itemName", "%"+entity.getItemName().trim()+"%");
			}

			if(entity.getParentChild()!=null &&  entity.getParentChild().trim().length()>0){
				if("parent".equals(entity.getParentChild())){
					hql.append(" and (parentSku is null or parentSku ='' or parentSku = itemSku)");
				}
			}
			if(entity.getItemType()!=null &&  entity.getItemType().trim().length()>0){
				hql.append(" and  itemType =:itemType");
				criteriaMap.put("itemType", entity.getItemType().trim());
			}
		}
		List<JewelryEntity> list = this.findPageByQuery(currentPage, pageSize, hql.append(" order by updateDate desc").toString(), criteriaMap);
		int totalCount = this.getTotalCount("select count(*) "+hql.toString(), criteriaMap);
		return new Page(currentPage,pageSize,totalCount,list);
	}
}
