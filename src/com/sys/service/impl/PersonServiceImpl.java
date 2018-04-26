package com.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.dao.PersonDao;
import com.sys.domain.model.Person;
import com.sys.service.PersonService;

@Service(value="personService")
public class PersonServiceImpl extends BaseServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;

	/**
	 * 保存更新个人信息
	 * @param user
	 */
	public void saveOrupdatePerson(Person person){
		personDao.saveOrupdatePerson(person);
	}

	/**
	 * 通过用户id来获得个人信息
	 * @param id
	 * @return
	 */
	public Person getPersonByPerId(long id){
		return personDao.getPersonByPerId(id);
	}
	
	/**
	 * 删除个人
	 * @param personId
	 */
	public void deletPerson(long personId){
		personDao.deletPerson(personId);
	}
	
	/**
	 * @param property
	 * @param pValue
	 * @param personId
	 */
	public void updatePersonInfo(String property,String pValue,long personId){
		personDao.updatePersonInfo(property, pValue, personId);
	}

}
