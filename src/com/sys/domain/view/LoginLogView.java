package com.sys.domain.view;

import java.util.Date;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年11月23日下午1:42:53
 *类说明：
 */
public class LoginLogView {
	private String beginTime;//开始时间
	private String endTime;//结束时间
	
	private long userId;//登录用户ID
	private String loginName;//登录名
	private String personName;//登录用户姓名
	private String loginDate;//登录日期
	private Date loginOutDate;//退出时间
	private String loginIp;//登录ip
	private Date logCreateTime;//日志创建时间
	private long deptId;//部门id
	private String userDept;//用户所在部门名称
	
	
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

}
