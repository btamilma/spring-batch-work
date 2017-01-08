package com.balatamilmani.restangular.dao;

import java.util.List;
import java.util.Map;

public interface RootDao<T> {

	Class<T> getEntityClass();
	
	T findById(final Long id);
	
	List<T> findAll();
	
	List<T> findByNamedQueryAndNamedParams(final String query, final Map<String, ? extends Object> paramMap);
	
	T save(final T entity);
	
	void insert(T entity);
	
	List<T> saveAll(List<T> entityList);
	
	void insertAll(List<T> entityList);
	
	void remove (List<T> entityList);
	
	void remove (T entity);
	
	
	
}
