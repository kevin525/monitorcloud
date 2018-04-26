package com.sys.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: OperationLog 
 * @Description: 系统操作日志
 * @author LG
 * @date 2017年3月1日 上午10:37:42
 */
@Entity
@Table(name="sys_operation_log")
public class OperationLog implements Serializable {
	
	private static final long serialVersionUID = 7271140119288612093L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long opLogId;
	private long userId;//登录用户ID
	private String loginName;//登录名
	private String personName;//登录用户姓名
	private String opDate;//操作日期
	private String opType;//保存：修改：删除：其他
	private String opContent;//操作内容
	
	public long getOpLogId() {
		return opLogId;
	}
	public void setOpLogId(long opLogId) {
		this.opLogId = opLogId;
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
	
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}
	public String getOpContent() {
		return opContent;
	}
	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	
}
