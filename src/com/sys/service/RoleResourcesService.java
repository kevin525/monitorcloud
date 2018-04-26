package com.sys.service;

import java.util.List;

import com.sys.domain.model.RoleResources;

public interface RoleResourcesService {
	/**
	 * 通过角色id获得权限id
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
	 * 根据菜单id 删掉权限记录
	 * @param roleid
	 * @param rsId
	 * @return
	 */
	public void  deleteRoleResources(List<String> ids) ;

}