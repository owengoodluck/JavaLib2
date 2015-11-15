package com.owen.wms.web.dao;

import java.util.List;

public interface IBaseDAO<T> {
	public void add(T t);

	public void update(T t);

	public void delete(int id);

	public T load(int id);

	public List<T> findAll();
	
	public List<T> list(String hql, Object[] args);
}