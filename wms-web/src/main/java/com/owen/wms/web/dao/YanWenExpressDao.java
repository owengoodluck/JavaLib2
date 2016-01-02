package com.owen.wms.web.dao;

import java.util.List;

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
}
