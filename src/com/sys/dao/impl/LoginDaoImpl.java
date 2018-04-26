package com.sys.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.common.pagetag.PageGridPost;
import com.sys.dao.LoginDao;
import com.sys.domain.model.Person;
import com.sys.domain.model.User;
import com.sys.domain.view.PersonView;

@Repository(value = "loginDao")
public class LoginDaoImpl extends BaseDaoImpl implements LoginDao {


	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public User UserLogin(User user) {
	 @SuppressWarnings("unchecked")
	    String hql = "from User where loginName=:loginName and loginPwd=:loginPwd";
	    Map<String, String> map = new HashMap<String,String>();
	    map.put("loginName", user.getLoginName());
	    map.put("loginPwd", user.getLoginPwd());
		List<User> list = this.query(hql, map);
		return (User) (list.size() > 0 ? list.get(0) : null);
	}
	/**
	 * 分页查询用户
	 * 
	 * @return
	 */
	@Override
	public List<User> personList(PageGridPost pageGridPost) {
		
		String hql=" from User";
		@SuppressWarnings("unchecked")
		List<User> listUsers=(List<User>)this.queryByPage(hql, pageGridPost);
		if(listUsers!=null && listUsers.size()>0){
			return listUsers;
		}
		return null;
	}
	
	@Override
	public List<User> getAllPersonList() {
		String hql=" from User where userState='1'";
		@SuppressWarnings("unchecked")
		List<User> listUsers=(List<User>)this.query(hql);
		if(listUsers!=null && listUsers.size()>0){
			return listUsers;
		}
		return null;
	}
	/**
	 * 查询用户的总条数
	 * @return
	 */
	@Override
	public String countPerson() {
		String hql="select count(*) from User";
		@SuppressWarnings("rawtypes")
		List list=this.query(hql);
		String s=String.valueOf(list.get(0));
		return s;
	}
	/**
	 * 批量删除
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public int deleteperId(Class<Person> Person,long[] entityIds) {
		this.delete(Person.class, entityIds);
		return 1;
	}
	/**
	 * 单个删除person
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public int oneDelete(Class<Person> Person, long personId) {
		this.delete(Person.class, personId);
		return 1;
	}
	/**
	 * 单个删除user
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public int oneUserDelete(Class<User> User, long userId) {
		this.delete(User.class, userId);
		return 1;
	}
	/**
	 * 查询
	 * @param personName
	 * @return
	 */
	@SuppressWarnings("all")
	@Override
	public List<User> oneSelect(String personName) {
		List<Person> list = new ArrayList<Person>();
		if(personName.equals("")){
			String hql="from Person";
			list = this.query(hql);
		}else{
			String hql = "from Person where personName like '%"+ personName +"%'";
		    Map<String, String> map = new HashMap<String,String>();
		    map.put("personName", personName);
		    list = this.query(hql);
			
		}
		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < list.size(); i++) {
			String sql="from User where userId  = "+list.get(i).getPersonId();
			List<User> users = this.query(sql);
			
			userList.addAll(users);
		}
		return userList;
	}
	
	/**
	 * 修改
	 * @param personView
	 * @return
	 */
	@Override
	public int oneUpdate(Person person) {
		this.update(person);
			return 1;
	}
	
	/**
	 * 添加
	 */
	@Override
	public int oneInsert(Person person,User user) {
		person.setCreateUserId(1);
		person.setIsLeader(1);
		person.setModifyUserId(1);
		person.setPersonOrderNum(1);
		this.saveOrUpdate(person);
		
		user.setCreateUserId(1);
		user.setModifyUserId(1);
		user.setLoginPwd("123456");
		user.setUserState(1);
		user.setUserOrder(1);
		user.setPerson(person);
		this.saveOrUpdate(user);
		
		return 1;
	}
	
}
