package com.balatamilmani.restangular.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Required;

public class RootDaoImpl<T> implements RootDao<T> {

	
	private final Class<T> persistentClass;
	
	private EntityManager entityManager;
	
	public RootDaoImpl() {
		this.persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public RootDaoImpl(final Class<T> persistentClass){
		this.persistentClass = persistentClass;
	}
	
	@Required
	@PersistenceContext(unitName="rstPersistenceUnit")
	public void setEntityManager(final EntityManager em){
		this.entityManager = em;
	}
	
	@Override
	public Class<T> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EntityManager getEntityManager(){
		return this.entityManager;
	}
	
	public Class<T> getPersistentClass(){
		return this.persistentClass;
	}
	
	

	@Override
	public T findById(Long id) {
		return getEntityManager().find(persistentClass, id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		final Query query = getEntityManager().createQuery("from " + persistentClass.getCanonicalName());
		return query.getResultList();
	}

	@Override
	public List<T> findByNamedQueryAndNamedParams(String query, Map<String, ? extends Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T save(T entity) {
		EntityManager em = getEntityManager();
		final T entitySaved = em.merge(entity);
		em.flush();
		
		return entitySaved;
	}

	@Override
	public void insert(T entity) {
		getEntityManager().persist(entity);
		
	}

	@Override
	public List<T> saveAll(List<T> entityList) {
		final List<T> entitySavedList = new ArrayList<T>();
		for(final T entity: entityList){
			T entitySaved = getEntityManager().merge(entity);
			entitySavedList.add(entitySaved);
		}
		getEntityManager().flush();
		return entitySavedList;
	}

	@Override
	public void insertAll(List<T> entityList) {
		int batchSize = 100;
		int count = 0;
		getEntityManager().setFlushMode(FlushModeType.COMMIT);
		for(T entity: entityList){
			getEntityManager().persist(entity);
			
			if(++count % batchSize == 0){
				getEntityManager().flush();
				getEntityManager().clear();
			}
		}
		
		getEntityManager().flush();
		getEntityManager().clear();
		getEntityManager().setFlushMode(FlushModeType.AUTO);
	}

	@Override
	public void remove(List<T> entityList) {
		for(T entity : entityList){
			getEntityManager().remove(entity);
		}
		getEntityManager().flush();
	}

	@Override
	public void remove(T entity) {
		getEntityManager().remove(entity);
	}

}
