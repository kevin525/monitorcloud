package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.dao.UserDao;
import com.sys.domain.model.User;
import com.sys.domain.model.UserRoles;
import com.sys.service.UserService;

@Service(value="userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	/**
	 * 通过用户名和密码获得用户信息
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	public User getUserByLoginNameAndPwd(String loginName, String pwd){
		return userDao.getUserByLoginNameAndPwd(loginName, pwd);
	}
	
	
	/**
	 * 保存更新用户信息
	 * @param user
	 */
	public void saveOrupdateUser(User user){
		userDao.saveOrupdateUser(user);
	}
	
	/**
	 * 通过用户id来获得用户信息
	 * @param id
	 * @return
	 */
	public User getUserByUserId(long id){
		return userDao.getUserByUserId(id);
	}
	
	
	/**
	 * 删除用户
	 * @param userId
	 */
	public void deletUser(long userId){
		userDao.deletUser(userId);
	}
	
	/**
	 * 通过登录名和一个常量来获得用户信息，加入常量的目的是为了安全
	 * @param loginName
	 * @param finalValue
	 * @return
	 */
	public User getUserByLoginName(String loginName,String finalValue){
		return userDao.getUserByLoginName(loginName,finalValue);
	}
	
	/**
	 * @param property
	 * @param pValue
	 * @param userId
	 */
	public void updateUserInfo(String property,String pValue,long userId){
		 userDao.updateUserInfo(property, pValue, userId);
	}
	
	
	/**
	 * 移动用户到其他部门
	 * @param deptId
	 * @param userIds
	 */
	public void moveUserToDept(long deptId,String userIds){
		userDao.moveUserToDept(deptId, userIds);
	}
	
	/**
	 * 保存对象
	 * 
	 * @param o
	 */
	public void save(Object o){
		userDao.save(o);
	}
	public void saveOrUpdate(Object o){
		baseDao.saveOrUpdate(o);
	}
	
	public int executeBySql(final String sql){
		return userDao.executeBySql(sql);
	}
	
	/**
	 * 通过用户id获得用户所具有的角色
	 * @param userId
	 * @return
	 */
	public List<UserRoles> getUserRolesByUserId(long userId){
		return userDao.getUserRolesByUserId(userId);
	}
	
	/**
	 * 删除用户角色关系
	 * @param userId
	 * @return
	 */
	public int deletUserRoles(long userId){
		return userDao.deletUserRoles(userId);
	}
	
}
