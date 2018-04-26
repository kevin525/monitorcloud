package com.sys.dao;

import java.util.List;

import com.common.pagetag.PageGridPost;
import com.sys.domain.model.User;
import com.sys.domain.model.UserRoles;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年10月25日下午6:07:06
 *类说明：
 */
public interface UserDao {

	/**
	 * 通过用户名和密码获得用户信息
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	public User getUserByLoginNameAndPwd(String loginName, String pwd);
	
	/**
	 * 通过获得某部门下的用户信息（不包含子部门用户）
	 * @param deptId
	 * @return
	 */
	public List<User> getUsersBydeptId(long deptId);
	
	
	/**
	 * 保存更新用户信息
	 * @param user
	 */
	public void saveOrupdateUser(User user);
	
	/**
	 * 通过用户id来获得用户信息
	 * @param id
	 * @return
	 */
	public User getUserByUserId(long id);
	
	
	
	/**
	 * 删除用户
	 * @param userId
	 */
	public void deletUser(long userId);
	
	
	/**
	 * 通过登录名和一个常量来获得用户信息，加入常量的目的是为了安全
	 * @param loginName
	 * @param finalValue
	 * @return
	 */
	public User getUserByLoginName(String loginName,String finalValue);
	
	/**
	 * @param property
	 * @param pValue
	 * @param userId
	 */
	public void updateUserInfo(String property,String pValue,long userId);
	
	/**
	 * 移动用户到其他部门
	 * @param deptId
	 * @param userIds
	 */
	public void moveUserToDept(long deptId,String userIds);
	
	/**
	 * 保存对象
	 * 
	 * @param o
	 */
	public void save(Object o);
	
	public int executeBySql(final String sql);
	
	/**
	 * 通过用户id获得用户所具有的角色
	 * @param userId
	 * @return
	 */
	public List<UserRoles> getUserRolesByUserId(long userId);

	/**
	 * 删除用户角色关系
	 * @param userId
	 * @return
	 */
	public int deletUserRoles(long userId);
}