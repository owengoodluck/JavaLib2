package com.owen.wms.web.dao;

import org.springframework.stereotype.Repository;

import com.owen.wms.web.entity.AmazonOrderItem;

@Repository("amazonOrderItemDao")
public class AmazonOrderItemDao extends BaseHibernateDao<AmazonOrderItem,String> {

}
