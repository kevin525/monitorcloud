package com.sys.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Person
 * @Description: 用户详细信息类
 * @author 用户详细信息
 * @date 2017年3月1日 上午10:41:35
 */
@Entity
@Table(name = "sys_person")
public class Person implements Serializable {

	private static final long serialVersionUID = 2149268789071392228L;
	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long personId = 0;
	//private int personId = 0;
	// 姓名
	@Column(length = 100)
	private String personName;
	// 性别0:男1：女
	private int sex;
	// 职务
	private String personOffice;
	// 手机号
	private String personTel;
	// 办公电话
	private String personOfficeTel;
	// email
	private String personEmail;
	// 地址
	private String personAddress;
	// 状态0:禁用1:启用2:删除
	private int personState;
	// 次序
	private int personOrderNum;
	/** 人名称拼音 */
	private String pinyin;
	// 是否是领导 状态0:不是1:是
	private int isLeader;
	// 是否显示全是系统0只显示开通的系统，1显示全部系统
	private Integer displayAllSys;
	// ip地址
	private String ip;
	// 用户描述
	private String description;

	// 创建人ID
	private long createUserId;
	// 创建时间
	private Date createDate;
	// 修改人
	private long modifyUserId;
	// 修改时间
	private Date modifyDate;


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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPersonOffice() {
		return personOffice;
	}

	public void setPersonOffice(String personOffice) {
		this.personOffice = personOffice;
	}

	public String getPersonTel() {
		return personTel;
	}

	public void setPersonTel(String personTel) {
		this.personTel = personTel;
	}

	public String getPersonOfficeTel() {
		return personOfficeTel;
	}

	public void setPersonOfficeTel(String personOfficeTel) {
		this.personOfficeTel = personOfficeTel;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public int getPersonState() {
		return personState;
	}

	public void setPersonState(int personState) {
		this.personState = personState;
	}

	public int getPersonOrderNum() {
		return personOrderNum;
	}

	public void setPersonOrderNum(int personOrderNum) {
		this.personOrderNum = personOrderNum;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(int isLeader) {
		this.isLeader = isLeader;
	}

	public Integer getDisplayAllSys() {
		return displayAllSys;
	}

	public void setDisplayAllSys(Integer displayAllSys) {
		this.displayAllSys = displayAllSys;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person(int personId, String personName, int sex, String personTel, int personState) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.sex = sex;
		this.personTel = personTel;
		this.personState = personState;
	}

	public Person(int personId, String personName, int sex, String personTel, int personState, Date createDate) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.sex = sex;
		this.personTel = personTel;
		this.personState = personState;
		this.createDate = createDate;
	}

	public Person(String personName, int sex, String personTel, int personState, Date createDate) {
		super();
		this.personName = personName;
		this.sex = sex;
		this.personTel = personTel;
		this.personState = personState;
		this.createDate = createDate;
	}

	public Person(int personId, String personName, int sex, String personOffice, String personTel,
			String personOfficeTel, String personEmail, String personAddress, int personState, int personOrderNum,
			String pinyin, int isLeader, Integer displayAllSys, String ip, String description, long createUserId,
			Date createDate, long modifyUserId, Date modifyDate) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.sex = sex;
		this.personOffice = personOffice;
		this.personTel = personTel;
		this.personOfficeTel = personOfficeTel;
		this.personEmail = personEmail;
		this.personAddress = personAddress;
		this.personState = personState;
		this.personOrderNum = personOrderNum;
		this.pinyin = pinyin;
		this.isLeader = isLeader;
		this.displayAllSys = displayAllSys;
		this.ip = ip;
		this.description = description;
		this.createUserId = createUserId;
		this.createDate = createDate;
		this.modifyUserId = modifyUserId;
		this.modifyDate = modifyDate;
	}

	public Person() {
		super();
	}

	public Person(String personName, int sex, String personTel, int personState, String pinyin, Date createDate) {
		super();
		this.personName = personName;
		this.sex = sex;
		this.personTel = personTel;
		this.personState = personState;
		this.pinyin = pinyin;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Person [personName=" + personName + ", personTel=" + personTel + ", personState=" + personState
				+ ", createDate=" + createDate + "]";
	}

}
