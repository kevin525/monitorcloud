package com.sys.dao;

import java.util.List;

import com.sys.domain.model.RoleResources;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年10月25日下午8:53:01
 *类说明：
 */
public interface RoleResourcesDao {

	/**
	 * 通过角色id获得角色权限
	 * @param userId
	 * @return
	 */
	public List<Long> getResourcesIdByRoleId(long roleId);
	
	/**
	 * 角色权限的保存
	 * @param role
	 */
	public void saveOrUpdateRoleResources(RoleResources roleResources);
	
	/**
	 * 根据角色id和菜单id 获得记录
	 * @param roleid
	 * @param rsId
	 * @return
	 */
	public RoleResources getByRoidRsid(String roleid,String rsId);
	/**
	 * 根据菜单id 删掉权限角色记录
	 * @param roleid
	 * @param rsId
	 * @return
	 */
	public void  deleteRoleResources(long id) ;
	
	

}