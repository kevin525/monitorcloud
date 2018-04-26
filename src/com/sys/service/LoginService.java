package com.sys.service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.common.pagetag.PageGridPost;
import com.sys.domain.model.Person;
import com.sys.domain.model.User;
import com.sys.domain.view.PersonView;

public interface LoginService {
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	public User UserLogin(User user);

	/**
	 * 分页查询用户
	 * 
	 * @return
	 */
	public List<User> personList(PageGridPost pageGridPost);

	/**
	 * 查询用户的总条数
	 * 
	 * @return
	 */
	public String countPerson();

	/**
	 * 批量删除
	 * 
	 * @param list
	 * @return
	 */
	public int deleteperId(Class<Person> Person, long[] entityIds);

	/**
	 * 单个删除person
	 * 
	 * @param list
	 * @return
	 */
	public int oneDelete(Class<Person> Person,  long personId);
	/**
	 * 单个删除user
	 * 
	 * @param list
	 * @return
	 */
	public int oneUserDelete(Class<User> User,long userId);
	/**
	 * 根据名字查询
	 * @param personName
	 * @return
	 */
	public List<User> oneSelect(String personName);
	/**
	 * 修改
	 * @param userId
	 * @return
	 */
	public int oneUpdate(Person person);
	/**
	 * 添加用户
	 * @param personView
	 * @return
	 */
	public int oneInsert(Person person,User user);
	
	/**
	 * @Title: getAllPersonList
	 * @Description: 获取所有用户
	 * @return
	 * @return List<User>
	 * @throws
	 */
	public List<User> getAllPersonList();
}
