package com.apps.warn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: AsopWarnInfo
 * @Description: 告警日志
 * @author LG
 * @date 2017年9月27日 下午4:08:48
 */
@Entity
@Table(name="asop_warn_log")
public class WarningLog implements Serializable{

	private static final long serialVersionUID = -5862604718738763670L;
	//唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	
	//告警名称
	private String warnName;
	
	//监控服务器的id或者应用系统的id
	private String monitorId;
	
	//标识，服务器：server  应用：system
	private String monitorType;
	
	//监控对象名称
	private String monitorName;
	
	//告警描述
	private String warnDesc;
	
	//告警方式，0：邮件，1：短信，2：声音，3：脚本，4：微信，5：App
	private String warnWay;
	
	//告警时间
	private String warnTime;
	
	//告警责任人id
	private String personLiableId;
	
	//告警责任人
	private String personLiable;
	
	//修改时间
	private String modifyDate;
	
	//状态，0：正常，1：异常
	private String status;
	
	//警告次数
	private String warnCount;
	
	//状态：0：未处理，1：处理
	private String flag;
	
	public WarningLog(){}
	
	public WarningLog(String warnTime,String status,String monitorType){
		this.warnTime = warnTime;
		this.status = status;
		this.monitorType = monitorType;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWarnName() {
		return warnName;
	}

	public void setWarnName(String warnName) {
		this.warnName = warnName;
	}

	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public String getWarnDesc() {
		return warnDesc;
	}

	public void setWarnDesc(String warnDesc) {
		this.warnDesc = warnDesc;
	}

	public String getWarnWay() {
		return warnWay;
	}

	public void setWarnWay(String warnWay) {
		this.warnWay = warnWay;
	}

	public String getWarnTime() {
		return warnTime;
	}

	public void setWarnTime(String warnTime) {
		this.warnTime = warnTime;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWarnCount() {
		return warnCount;
	}

	public void setWarnCount(String warnCount) {
		this.warnCount = warnCount;
	}

	public String getPersonLiableId() {
		return personLiableId;
	}

	public void setPersonLiableId(String personLiableId) {
		this.personLiableId = personLiableId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}
	
	
	
	
}
