package com.apps.warn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: ErrorLog
 * @Description: 错误日志
 * @author LG
 * @date 2018年2月5日 下午2:39:28
 */
@Entity
@Table(name="asop_error_log")
public class ErrorLog implements Serializable{

	private static final long serialVersionUID = -5862604718738763670L;
	//唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	
	//错误名称
	private String errorTitle;
	
	//错误详情
	private String errorDetail;
	
	//错误类别：服务器、应用系统、数据库、中间件
	private String errorName;
	
	//错误类型：server、system、database、middleware
    private String errorType;
	
	//告警时间
	private String errorTime;
	
	//项目名称
	private String projectName;
	
	//服务器ip
	private String serverIp;
	
	//所属服务器、应用系统、数据库、中间件的id
	private long appId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(String errorTime) {
		this.errorTime = errorTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	

	
	
	
	
	
}
