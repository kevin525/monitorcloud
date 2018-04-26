package com.sys.domain.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * @ClassName: User 
 * @Description: 用户表  
 * @author LG
 * @date 2017年3月1日 上午10:39:49
 */
@Entity
@Table(name="sys_user")
public class User implements Serializable{
	
	private static final long serialVersionUID = -6351788915400575951L;
	//用户ID
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long userId=0;
	//private int userId=0;
	//登录名
	@Column(length=100)
	private String loginName;
	//密码
	@Column(length=100)
	private String loginPwd;
	//状态0:禁用1:启用2:删除
	private int userState;
	//次序
	private int userOrder;
	private Integer isManager;//1:普通用户2：管理员
	//用户对应的person
	@Column(insertable=false, updatable=false)
	private Long    perId;
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Person.class)
	@JoinColumn(name = "perId", referencedColumnName = "personId")
	private Person person;
	
	//创建人ID
	private long createUserId;
	//创建时间
	private Date createDate;
	//修改人
	private long modifyUserId;
	//修改时间
	private Date modifyDate;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public int getUserOrder() {
		return userOrder;
	}
	public void setUserOrder(int userOrder) {
		this.userOrder = userOrder;
	}
	
	public Long getPerId() {
		return perId;
	}
	public void setPerId(Long perId) {
		this.perId = perId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public User() {
		super();
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

	public User(String loginName, String loginPwd) {
		super();
		this.loginName = loginName;
		this.loginPwd = loginPwd;
	}
	

	public Integer getIsManager() {
		return isManager;
	}
	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", loginName=" + loginName + ", loginPwd=" + loginPwd + ", userState="
				+ userState + ", userOrder=" + userOrder + ", isManager=" + isManager + ", perId=" + perId + ", person="
				+ person + ", createUserId=" + createUserId + ", createDate=" + createDate + ", modifyUserId="
				+ modifyUserId + ", modifyDate=" + modifyDate + "]";
	}
	
	

}
