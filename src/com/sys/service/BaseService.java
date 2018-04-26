package com.sys.service;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService {

	@SuppressWarnings("rawtypes")
	public Object findById(Class clazz, Serializable id);

	/**
	 * 通过Id来批量删除对象
	 * @param entityClass
	 * @param entityIds
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityIds);

	/**
	 * 通过Id来删除对象
	 * @param entityClass
	 * @param entityIds
	 */
	public <T> void delete(Class<T> entityClass, Object entityId);
	
	public void saveOrUpdate(Object o);
	
	public void update(Object o);
	
	/**
	 * 批量保存或者更新对象
	 * 
	 * @param lists
	 */
	@SuppressWarnings("rawtypes")
	public void saveAll(Collection lists);

}