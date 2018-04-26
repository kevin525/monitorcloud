package com.sys.service;

import com.sys.domain.model.Person;

public interface PersonService {

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