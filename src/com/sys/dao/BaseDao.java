package com.sys.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseDao {

	/**
	 * 通过HQL语句查询，返回List
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List query(String hql);

	/**
	 * 通过HQL语句查询，带参数Object[]
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List query(String hql, Object[] params);

	/**
	 * 通过HQL语句查询，带参数Map
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List query(String hql, Map<String, ?> params);

	/**
	 * 通过sql语句查询，返回List
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryBySQL(String sql);

	/**
	 * 通过主键查找指定的Class对象
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object findById(Class clazz, Serializable id);

	/**
	 * 保存对象
	 * 
	 * @param o
	 */
	public void save(Object o);

	/**
	 * 保存或者更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(Object o);

	/**
	 * 批量保存或者更新对象
	 * 
	 * @param lists
	 */
	@SuppressWarnings("rawtypes")
	public void saveAll(Collection lists);

	/**
	 * 更新对象
	 * 
	 * @param o
	 */
	public void update(Object o);

	/**
	 * 删除对象
	 * 
	 * @param o
	 */
	public void delete(Object o);

	/**
	 * 批量删除对象
	 * 
	 * @param lists
	 */
	@SuppressWarnings("rawtypes")
	public void deleteAll(Collection lists);

	/**
	 * 批量删除对象，通过指定的主键
	 * 
	 * @param clazz
	 * @param primaryKey
	 * @param ids
	 */
	@SuppressWarnings("rawtypes")
	public void deleteAll(Class clazz, String primaryKey, Serializable[] ids);
	
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
}