package com.sys.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.common.AppConstant;
import com.common.StringHelpers;
import com.sys.dao.UserDao;
import com.sys.domain.model.User;
import com.sys.domain.model.UserRoles;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年10月25日下午5:47:15
 *类说明：
 */
@Repository(value="userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
	
	/**
	 * 通过用户名和密码获得用户信息
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	public User getUserByLoginNameAndPwd(String loginName,String pwd){
		String hql="";
		if(!StringHelpers.isNull(pwd) && AppConstant.theSkeletonKey.equals(pwd)){
			hql="from User u where u.loginName='"+loginName+"'  and u.userState=1";
		}else{
			hql="from User u where u.loginName='"+loginName+"' and u.loginPwd='"+pwd+"' and u.userState=1";
		}
		
		@SuppressWarnings("unchecked")
		List<User> listUsers=getHibernateTemplate().find(hql);
		if(listUsers!=null && listUsers.size()>0){
			return listUsers.get(0);
		}
		return null;
	}
	
	/**
	 * 获得某部门下的用户信息（不包含子部门用户）,不分页
	 * @param deptId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsersBydeptId(long deptId){
		String hql="from User u where u.userState!=2 and u.departmentId="+deptId+" order by userOrder,userId";
		List<User> listUsers=getHibernateTemplate().find(hql);
		if(listUsers!=null && listUsers.size()>0){
			return listUsers;
		}
		return null;
	}
	
	
	/**
	 * 保存更新用户信息
	 * @param user
	 */
	public void saveOrupdateUser(User user){
		saveOrUpdate(user);
	}
	
	/**
	 * @param property
	 * @param pValue
	 * @param userId
	 */
	public void updateUserInfo(String property,String pValue,long userId){
		String sql="update SYS_USER set "+property+"='"+pValue+"' where userId="+userId;
		executeBySql(sql);
	}
	
	
	/**
	 * 移动用户到其他部门
	 * @param deptId
	 * @param userIds
	 */
	public void moveUserToDept(long deptId,String userIds){
		String sql="update SYS_USER set departmentId="+deptId+" where userId in("+userIds+")";
		executeBySql(sql);
	}
	
	
	/**
	 * 通过用户id来获得用户信息
	 * @param id
	 * @return
	 */
	public User getUserByUserId(long id){
		return (User) findById(User.class,id);
	}
	
	/**
	 * 删除用户
	 * @param userId
	 */
	public void deletUser(long userId){
		delete(User.class,userId);
	}
	
	/**
	 * 通过登录名和一个常量来获得用户信息，加入常量的目的是为了安全
	 * @param loginName
	 * @param finalValue
	 * @return
	 */
	public User getUserByLoginName(String loginName,String finalValue){
		if(AppConstant.safeValue.equals(finalValue)){
			String hql="";
			if(!StringHelpers.isNull(loginName)){
				hql="from User u where u.loginName='"+loginName+"'  and u.userState=1";
				@SuppressWarnings("unchecked")
				List<User> listUsers=getHibernateTemplate().find(hql);
				if(listUsers!=null && listUsers.size()>0){
					return listUsers.get(0);
				}
			}
			return null;
		}
		return null;
	}
	
	/**
	 * 通过用户id获得用户所具有的角色
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserRoles> getUserRolesByUserId(long userId){
		String hql="from UserRoles ur where ur.usId="+userId;
		List<UserRoles> listURs=getHibernateTemplate().find(hql);
		if(listURs!=null && listURs.size()>0){
			return listURs;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 删除用户角色关系
	 * @param userId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int deletUserRoles(long userId){
		final String hql="delete UserRoles ur where ur.usId="+userId;
		return getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql).executeUpdate();
			}
			
		});
	}
	
}
