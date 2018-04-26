package com.sys.dao;

import com.sys.domain.model.Person;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年11月7日上午11:04:56
 *类说明：
 */
public interface PersonDao {

	/**
	 * 保存更新个人信息
	 * @param user
	 */
	public void saveOrupdatePerson(Person person);

	/**
	 * 通过用户id来获得个人信息
	 * @param id
	 * @return
	 */
	public Person getPersonByPerId(long id);
	
	/**
	 * 删除个人
	 * @param personId
	 */
	public void deletPerson(long personId);
	
	
	/**
	 * @param property
	 * @param pValue
	 * @param personId
	 */
	public void updatePersonInfo(String property,String pValue,long personId);

}