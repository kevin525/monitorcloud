package com.sys.service;

import java.util.List;

import com.common.pagetag.PageGridPost;
import com.sys.domain.model.Role;
import com.sys.domain.model.RoleResources;

public interface RoleService {

	/**
	 * 通过用户id获得用户拥有的角色
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRolesByuserId(long userId);
	
	/**
	 * 角色的保存
	 * @param role
	 */
	public void saveOrUpdateRole(Role role);
	
	/**
	 * 分页查询展示角色列表
	 * @param role
	 * @param pageGridPost
	 * @return
	 */
	public List<Role> getRoleList(Role role,PageGridPost pageGridPost);
	
	/**
	 * 获得符合条件的角色 不分页
	 * @param role
	 * @return
	 */
	public List<Role> getRoleListByCriteria(Role role);
	
	/**
	 * 通过角色id获得角色所具有的权限
	 * @param userId
	 * @return
	 */
	public List<RoleResources> getRoleResourcesByRoleId(long roleId);
	
	/**
	 * 通过角色id来获得角色信息
	 * @param id
	 * @return
	 */
	public Role getRoleByRoleId(long id);
	
	/**
	 * 通过用户id获得用户拥有的角色(分页展示)
	 * @param userId
	 * @return
	 */
	public List<Role> getPageUserRolesByuserId(long userId,String roleName,PageGridPost pageGridPost);
	
	/**
	 * 删除角色(也从角色权限表中删除,也从用户角色表中删除数据)
	 * @param privelegeId
	 */
	public void deleteRole(long roleId);
	
	/**
	 * 批量删除
	 * @param roleIds
	 */
	public void deleteRoles(List<String> roleIds);

}