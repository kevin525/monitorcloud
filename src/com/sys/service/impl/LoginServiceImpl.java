package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.pagetag.PageGridPost;
import com.sys.dao.LoginDao;
import com.sys.domain.model.Person;
import com.sys.domain.model.User;
import com.sys.domain.view.PersonView;
import com.sys.service.LoginService;
@Service(value="LoginService")
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */

	public User UserLogin(User user) {
		return loginDao.UserLogin(user);
	}
	/**
	 * 分页查询用户
	 * 
	 * @return
	 */
	@Override
	public List<User> personList(PageGridPost pageGridPost) {
		return loginDao.personList(pageGridPost);
	}
	/**
	 * 查询用户的总条数
	 * @return
	 */
	@Override
	public String countPerson() {
		return loginDao.countPerson();
	}
	/**
	 * 批量删除
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public int deleteperId(Class<Person> Person,long[] entityIds) {
		return loginDao.deleteperId(Person, entityIds);
	}
	/**
	 * 单个删除person
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public int oneDelete(Class<Person> Person, long personId) {
		return loginDao.oneDelete(Person, personId);
	}
	/**
	 * 单个删除user
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public int oneUserDelete(Class<User> User, long userId) {
		return loginDao.oneUserDelete(User, userId);
	}
	/**
	 * 查询
	 * @param personName
	 * @return
	 */
	@Override
	public List<User> oneSelect(String personName) {
		return loginDao.oneSelect(personName);
	}
	
	/**
	 * 修改
	 */
	@Override
	public int oneUpdate(Person person) {
		return loginDao.oneUpdate(person);
	}
	/**
	 * 添加
	 */
	@Override
	public int oneInsert(Person person,User user) {
		return loginDao.oneInsert(person,user);
	}
	@Override
	public List<User> getAllPersonList() {
		return loginDao.getAllPersonList();
	}
	

}
