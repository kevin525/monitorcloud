package com.sys.service.impl;


import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.dao.BaseDao;
import com.sys.service.BaseService;

@Service(value="baseService")
public class BaseServiceImpl implements BaseService {
	@Autowired
	protected BaseDao baseDao;
	
	@SuppressWarnings("rawtypes")
	public Object findById(Class clazz, Serializable id){
		return baseDao.findById(clazz, id);
	}
	
	/**
	 * 通过Id来批量删除对象
	 * @param entityClass
	 * @param entityIds
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityIds){
		 baseDao.delete(entityClass, entityIds);
	}
	
	/**
	 * 通过Id来删除对象
	 * @param entityClass
	 * @param entityIds
	 */
	public <T> void delete(Class<T> entityClass, Object entityId){
		baseDao.delete(entityClass, entityId);
	}
	
	public void saveOrUpdate(Object o){
		baseDao.saveOrUpdate(o);
	}
	
	/**
	 * 批量保存或者更新对象
	 * 
	 * @param lists
	 */
	@SuppressWarnings("rawtypes")
	public void saveAll(Collection lists){
		baseDao.saveAll(lists);
	}

	@Override
	public void update(Object o) {
		baseDao.update(o);
	}
}
