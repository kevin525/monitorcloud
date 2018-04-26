package com.sys.domain.view;

import java.util.Date;

import javax.persistence.Column;

import com.sys.domain.model.Person;
import com.sys.domain.model.Role;

public class PersonView {
	private long personId;
	//姓名
	@Column(length=100)
	private String personName;
	private long userId;
	private String loginName;
	private String personTel;
	private String  createDate;
	private Role role;
	private int personState;
	private int sex;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPersonTel() {
		return personTel;
	}
	public void setPersonTel(String personTel) {
		this.personTel = personTel;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getPersonState() {
		return personState;
	}
	public void setPersonState(int personState) {
		this.personState = personState;
	}
	public PersonView(int personId, String personName, String loginName, String personTel, String createDate,
			int personState) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.loginName = loginName;
		this.personTel = personTel;
		this.createDate = createDate;
		this.personState = personState;
	}
	public PersonView() {
		super();
	}
	@Override
	public String toString() {
		return "PersonView [personId=" + personId + ", personName=" + personName + ", loginName=" + loginName
				+ ", personTel=" + personTel + ", createDate=" + createDate + ", personState=" + personState + "]";
	}

}
