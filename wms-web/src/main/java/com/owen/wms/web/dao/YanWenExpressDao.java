package com.owen.wms.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.YanWenExpressEntity;
import com.owen.wms.web.form.ExpressQueryForm;

@Repository("yanWenExpressDao")
public class YanWenExpressDao extends BaseHibernateDao<YanWenExpressEntity,String>{

	private SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd");
	
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
	
	//Page query
	public Page pageListByCriteria(ExpressQueryForm queryForm) throws Exception{
		Map<String,Object> criteriaMap = new HashMap();
		StringBuffer hql = new StringBuffer("from YanWenExpressEntity where 1=1 ");
		if(queryForm.getChannel()!=null && queryForm.getChannel().trim().length()>0){
			hql.append(" and channel = :channel");
			criteriaMap.put("channel", queryForm.getChannel());
		}
		if(queryForm.getExpressID()!=null && queryForm.getExpressID().trim().length()>0){
			hql.append(" and epcode like :epcode");
			criteriaMap.put("epcode", "%"+queryForm.getExpressID()+"%");
		}
		if(queryForm.getOrderID()!=null && queryForm.getOrderID().trim().length()>0){
			hql.append(" and userOrderNumber like :userOrderNumber");
			criteriaMap.put("userOrderNumber", "%"+queryForm.getOrderID()+"%");
		}
		if(queryForm.getReceiver()!=null && queryForm.getReceiver().trim().length()>0){
			hql.append(" and name like :name");
			criteriaMap.put("name", "%"+queryForm.getReceiver()+"%");
		}
		if(queryForm.getSendDateFrom()!=null && queryForm.getSendDateFrom().trim().length()>0){
			hql.append(" and sendDate >= :sendDateF");
			criteriaMap.put("sendDateF", this.sdf.parse(queryForm.getSendDateFrom()));
		}

		if(queryForm.getSendDateTo()!=null && queryForm.getSendDateTo().trim().length()>0){
			hql.append(" and sendDate < :sendDateT");
			criteriaMap.put("sendDateT", this.sdf.parse(queryForm.getSendDateTo()));
		}

		List list = this.findPageByQuery(queryForm.getCurrentPage(), queryForm.getPageSize(), hql.append(" order by sendDate desc").toString(), criteriaMap);
		int totalCount = this.getTotalCount("select count(*) "+hql.toString(), criteriaMap);
		return new Page(queryForm.getCurrentPage(), queryForm.getPageSize(),totalCount,list);
		
	}
}
