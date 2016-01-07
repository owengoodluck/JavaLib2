package com.owen.wms.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.entity.YanWenExpressEntity;

@Repository("yanWenExpressDao")
public class YanWenExpressDao extends BaseHibernateDao<YanWenExpressEntity,String>{
	
	public List<YanWenExpressEntity> listAll(){
		Query query = this.getSession().createQuery("from YanWenExpressEntity e order by sendDate desc");
		List<YanWenExpressEntity> result = query.list();
		return result;
	}
	
	public YanWenExpressEntity getByAmazonOrderId(String amazonOrderId){
		Query query = this.getSession().createQuery("from YanWenExpressEntity where userOrderNumber ='"+amazonOrderId+"'");
		YanWenExpressEntity result = (YanWenExpressEntity) query.uniqueResult();
		return result;
	}
	
	public Map<String,YanWenExpressEntity> getMapByAmazonOrderIds(String[] amazonOrderIds){
		Map<String,YanWenExpressEntity> map = new HashMap();
		StringBuffer hql = new StringBuffer("from YanWenExpressEntity where userOrderNumber in (");
		for(int i=0;i<amazonOrderIds.length;i++){
			hql.append("'"+amazonOrderIds[i]+"'");
			if(i<amazonOrderIds.length-1){
				hql.append(",");
			}
		}
		hql.append(")");
		Query query = this.getSession().createQuery(hql.toString());
		List<YanWenExpressEntity> list = query.list();
		if(list != null){
			for(YanWenExpressEntity e  : list){
				map.put(e.getUserOrderNumber(), e);
			}
		}
		return map;
	}
}
