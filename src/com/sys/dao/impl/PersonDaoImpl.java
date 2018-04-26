package com.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.sys.dao.PersonDao;
import com.sys.domain.model.Person;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年11月7日上午11:02:11
 *类说明：
 */
@Repository(value="personDao")
public class PersonDaoImpl extends BaseDaoImpl implements PersonDao {

	/**
	 * 保存更新个人信息
	 * @param user
	 */
	public void saveOrupdatePerson(Person person){
		saveOrUpdate(person);
	}
	
	/**
	 * 通过用户id来获得个人信息
	 * @param id
	 * @return
	 */
	public Person getPersonByPerId(long id){
		return (Person) findById(Person.class,id);
	}
	
	/**
	 * 删除个人
	 * @param personId
	 */
	public void deletPerson(long personId){
		delete(Person.class,personId);
	}
	
	/**
	 * @param property
	 * @param pValue
	 * @param personId
	 */
	public void updatePersonInfo(String property,String pValue,long personId){
		String sql="update SYS_PERSON set "+property+"='"+pValue+"' where personId="+personId;
		executeBySql(sql);
	}
}
