package com.owen.wms.web.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDAO<T> {
	@Autowired  
    @Qualifier("sessionFactory")  
    private SessionFactory sessionFactory;  
	
    private Class entityClass;
    
    private Class getEntityClass(){
        if(entityClass==null){
        	ParameterizedType supClass = (ParameterizedType) getClass().getGenericSuperclass();
        	Type[] types = supClass.getActualTypeArguments();
            entityClass =   types[0].getClass(); 
        }
        return entityClass;
    }

	@Override
	public void add(T t) {
		super.getHibernateTemplate().save(t);
	}
    
    @Override
    public void update(T t) {
        super.getHibernateTemplate().update(t);
    }

    @Override
    public void delete(int id) {
        //先加载对象再删除
        super.getHibernateTemplate().delete(this.load(id));
    }

    @Override
    public T load(int id) {
        return (T) super.getHibernateTemplate().get(getEntityClass(), id);
        
    }

    /**
     * 这个方法的弊端：
     * 1， select * from User 这样的语句不行
     * 2， 查询结果是Object[],需要自己额外处理
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> list(String hql, Object[] args) {
        
        //select * 的处理，可以利用反射来做
        
        Query query=this.getSession().createQuery(hql);
        for(int i=0;i<args.length;i++){
            query.setParameter(i, args[i]);
        } 
        return listToBean(query.list(),hql);
    //    return query.list();
    }

    /**
     * 自己写代码，作映射
     * @param list
     * @param hql
     * @return
     */
    private  List<T> listToBean(List<Object[]> list,String hql){
        
        //"select "占7个长度 
        String[] columns=hql.substring(7, hql.indexOf("from")).split("\\s*,\\s*");
        List<T> resultList=new ArrayList<T>();
        
        List<Field> fields=new ArrayList<Field>();
        //测试发现：getDeclaredField(column)获取的是基本类型值
        //测试发现：getField(column)获取的是非基本类型值
        Field[] fieldsTemp=getEntityClass().getDeclaredFields(); //获取所有的列
        
        //得到每次需要的列
        for(int i=0;i<fieldsTemp.length;i++){
            String temp=fieldsTemp[i].getName(); //属性名
            boolean flag=false; //假设当前这个不是要或者的属性名
            for(String column:columns){
                if(column.equals(temp)){
                    flag=true;
                    break;
                }
            }
            if(flag){
                fields.add(fieldsTemp[i]);
            }    
        }
        
        try {
                for(int i=0;i<list.size();i++){
                    Object[] objs=list.get(i);
                    T tclass=(T) getEntityClass().newInstance();
                    for(int j=0;j<fields.size();j++){
                        fields.get(j).setAccessible(true);
                        fields.get(j).set(tclass,objs[j]);
                    }
                    resultList.add(tclass);
                }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
    
        return resultList;
    }

	@Override
	public List<T> findAll() {
		String hql = "select a from " + this.getEntityClass().getSimpleName() + " a";
		List<T> result = (List<T>) this.getHibernateTemplate().find(hql);
		return result;
	}
}
