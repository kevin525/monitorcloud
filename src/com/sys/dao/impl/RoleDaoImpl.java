package com.sys.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.RoleDao;
import com.sys.domain.model.Role;
import com.sys.domain.model.RoleResources;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年10月25日下午7:15:56
 *类说明：
 */
@Repository(value="roleDao")
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao{
	
	/**
	 * 通过用户id获得用户拥有的角色
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRolesByuserId(long userId){
		String hql="select ur.role from UserRoles ur where ur.usId="+userId;
		@SuppressWarnings("unchecked")
		List<Role> listRoles=getHibernateTemplate().find(hql);
		if(listRoles!=null && listRoles.size()>0){
			return listRoles;
		}else{
			return null;
		}
	}
	
	
	
	/**
	 * 通过用户id获得用户拥有的角色(分页展示)
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getPageUserRolesByuserId(long userId,String roleName,PageGridPost pageGridPost){
		String hql="select ur.role from UserRoles ur where ur.role.roleState=1  and  ur.usId="+userId;
		if(StringUtils.isNotBlank(roleName)){
			hql=hql+" and ur.role.roleName like '%"+roleName+"%'  ";
		}
		List<Role> listRoles=((List<Role>) queryByPage(hql.toString(),pageGridPost));
		if(listRoles!=null && listRoles.size()>0){
			return listRoles;
		}
		return null;
	}
	
	
	

	/**
	 * 角色的保存
	 * @param role
	 */
	public void saveOrUpdateRole(Role role){
		saveOrUpdate(role);
	}
	
	/**
	 * 分页查询展示角色列表
	 * @param role
	 * @param pageGridPost
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getRoleList(Role role,PageGridPost pageGridPost){
		StringBuffer hql=new StringBuffer("from Role rl where 1=1 ");
		if(role!=null){
			if(!StringHelpers.isNull(role.getRoleName())){
				hql.append(" and rl.roleName like '%"+ role.getRoleName()+"%'");
			}
			if(role.getRoleState()!=0){
				hql.append(" and rl.roleState="+role.getRoleState());
			}
		}
		hql.append(" order by rl.roleState,rl.roleCreatTime desc ");
		List<Role> listRoles=((List<Role>) queryByPage(hql.toString(),pageGridPost));
		if(listRoles!=null && listRoles.size()>0){
			return listRoles;
		}
		return null;
	}
	
	/**
	 * 通过角色id获得角色所具有的权限
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RoleResources> getRoleResourcesByRoleId(long roleId){
		String hql="from RoleResources rp where rp.roId="+roleId;
		List<RoleResources> listRPs=getHibernateTemplate().find(hql);
		if(listRPs!=null && listRPs.size()>0){
			return listRPs;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 通过角色id来获得角色信息
	 * @param id
	 * @return
	 */
	public Role getRoleByRoleId(long id){
		return (Role) findById(Role.class,id);
	}
	
	
	/**
	 * 删除(也从角色权限表中删除,也从用户角色表中删除数据)
	 * @param privelegeId
	 */
	@SuppressWarnings("rawtypes")
	public void deleteRole(long roleId){
		//删除角色权限表中的相关数据---开始
		String querysql="select count(roId) from SYS_ROLE_RESOURCES rp where rp.roId="+roleId;
		List list=queryBySQL(querysql);
		if(list!=null && list.size()>0){
			String str=String.valueOf(list.get(0));
			if(!"0".equals(str)){
				String sql="delete from SYS_ROLE_RESOURCES  where roId="+roleId;
				executeBySql(sql);//删除角色权限表中的权限
			}
		}
		//删除角色权限表中的相关数据---结束
		//删除用户权限表中的相关数据---开始
		String urQuerysql="select count(rId) from SYS_USER_ROLES ur where ur.rId="+roleId;
		List urList=queryBySQL(urQuerysql);
		if(urList!=null && urList.size()>0){
			String str=String.valueOf(urList.get(0));
			if(!"0".equals(str)){
				String sql="delete from SYS_USER_ROLES  where rId="+roleId;
				executeBySql(sql);//删除角色权限表中的权限
			}
		}
		//删除用户权限表中的相关数据---结束
		delete(Role.class, roleId);//角色表中的角色
	}



	@Override
	public List<Role> getRoleListByCriteria(Role role) {
		StringBuffer hql=new StringBuffer("from Role rl where 1=1 ");
		if(role!=null){
			if(!StringHelpers.isNull(role.getRoleName())){
				hql.append(" and rl.roleName like '%"+ role.getRoleName()+"%'");
			}
			if(role.getRoleState()!=0){
				hql.append(" and rl.roleState="+role.getRoleState());
			}
		}
		hql.append(" order by rl.roleCreatTime desc ");
		List<Role> listRoles=((List<Role>) query(hql.toString()));
		if(listRoles!=null && listRoles.size()>0){
			return listRoles;
		}
		return null;
	}
}
