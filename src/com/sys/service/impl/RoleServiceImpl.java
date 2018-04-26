package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.RoleDao;
import com.sys.domain.model.Role;
import com.sys.domain.model.RoleResources;
import com.sys.service.RoleService;

@Service(value="roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	/**
	 * 通过用户id获得用户拥有的角色
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRolesByuserId(long userId){
		return roleDao.getUserRolesByuserId(userId);
	}
	
	/**
	 * 角色的保存
	 * @param role
	 */
	public void saveOrUpdateRole(Role role){
		roleDao.saveOrUpdateRole(role);
	}
	
	/**
	 * 分页查询展示角色列表
	 * @param role
	 * @param pageGridPost
	 * @return
	 */
	public List<Role> getRoleList(Role role,PageGridPost pageGridPost){
		return roleDao.getRoleList(role, pageGridPost);
	}
	
	/**
	 * 通过角色id获得角色所具有的权限
	 * @param userId
	 * @return
	 */
	public List<RoleResources> getRoleResourcesByRoleId(long roleId){
		return roleDao.getRoleResourcesByRoleId(roleId);
	}
	
	/**
	 * 通过角色id来获得角色信息
	 * @param id
	 * @return
	 */
	public Role getRoleByRoleId(long id){
		return roleDao.getRoleByRoleId(id);
	}
	
	/**
	 * 通过用户id获得用户拥有的角色(分页展示)
	 * @param userId
	 * @return
	 */
	public List<Role> getPageUserRolesByuserId(long userId,String roleName,PageGridPost pageGridPost){
		return roleDao.getPageUserRolesByuserId(userId,roleName, pageGridPost);
	}
	/**
	 * 删除角色(也从角色权限表中删除,也从用户角色表中删除数据)
	 * @param privelegeId
	 */
	public void deleteRole(long roleId){
		//第一步：删除用户角色关系
		//第二步：删除角色包含的权限关系
		//第三步：删除角色
		try {
			roleDao.deleteRole(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoles(List<String> roleIds) {
		if(roleIds != null && roleIds.size() > 0){
			for(String roleId:roleIds){
			    if(!StringHelpers.isNull(roleId)){
			    	roleDao.deleteRole(Long.parseLong(roleId));
			    }
			}
		}
	}

	@Override
	public List<Role> getRoleListByCriteria(Role role) {
		return roleDao.getRoleListByCriteria(role);
	}
}
