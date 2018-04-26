package com.sys.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: LoginLog 
 * @Description: 用户登录日志类 
 * @author LG 
 * @date 2017年3月1日 上午10:36:43
 */
@Entity
@Table(name="sys_login_log")
public class LoginLog implements Serializable {
	
	private static final long serialVersionUID = 1477495655092449199L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long loginLogId;
	private long userId;//登录用户ID
	private String loginName;//登录名
	private String personName;//登录用户姓名
	private String loginDate;//登录日期
	private Date loginOutDate;//退出时间
	private String loginIp;//登录ip
	private Date logCreateTime;//日志创建时间

	
	public long getLoginLogId() {
		return loginLogId;
	}
	public void setLoginLogId(long loginLogId) {
		this.loginLogId = loginLogId;
	}
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
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public Date getLoginOutDate() {
		return loginOutDate;
	}
	public void setLoginOutDate(Date loginOutDate) {
		this.loginOutDate = loginOutDate;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLogCreateTime() {
		return logCreateTime;
	}
	public void setLogCreateTime(Date logCreateTime) {
		this.logCreateTime = logCreateTime;
	}
	
}
