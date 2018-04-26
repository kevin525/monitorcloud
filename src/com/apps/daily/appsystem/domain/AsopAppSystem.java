package com.apps.daily.appsystem.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: AsopAppSystem
 * @Description: 应用系统实体类
 * @author LG
 * @date 2017年9月22日 上午9:33:30
 */
@Entity
@Table(name="asop_app_system")
public class AsopAppSystem implements Serializable{

	private static final long serialVersionUID = -7979296841307226791L;
	//服务器唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//应用名称，例如党政信息工作管理系统
	private String appName;
	//应用短名，系统的唯一标识，例如bsweb
	private String shortName;
	//应用所在的ip地址
	private String appIp;
	//数据库类别:0:mysql,1:oracle
	private String dataBaseType;
	//数据库名称
	private String dataBaseName;
	//数据库用户名
	private String dataBaseUser;
	//数据库密码
	private String dataBasePwd;
	//oracle实例
	private String dataBaseIns;
	//数据库所在ip地址
	private String dataBaseIp;
	//是否启用，0：启用，1：禁用，2：删除
	private String isUse;
	//应用系统网络状态，0：正常，1：异常
	private String appNetStatus;
	//数据库网络状态，0：正常，1：异常
	private String dataBaseNetStatus;
	//应用系统运行状态，0：正常，1：异常
	private String appStatus;
	//责任人
	private String personLiable;
	//创建人ID
	private long createUserId;
	//创建时间
	private String createDate;
	//修改人
	private long modifyUserId;
	//修改时间
	private String modifyDate;
	//次序
	private int appOrder;
	//运行环境：0：正式环境，1：测试环境
	private String environment;
	//系统访问的url,或者拥有监测的url
	private String url;
	//最近的一次检查时间
	private String lastCheckDate;
	//累计检查次数
	private int checkCount;
	
	public AsopAppSystem() {
		super();
	}
	
	
	public AsopAppSystem(String isUse) {
		super();
		this.isUse = isUse;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getAppIp() {
		return appIp;
	}
	public void setAppIp(String appIp) {
		this.appIp = appIp;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getDataBaseIp() {
		return dataBaseIp;
	}
	public void setDataBaseIp(String dataBaseIp) {
		this.dataBaseIp = dataBaseIp;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getAppNetStatus() {
		return appNetStatus;
	}
	public void setAppNetStatus(String appNetStatus) {
		this.appNetStatus = appNetStatus;
	}
	public String getDataBaseNetStatus() {
		return dataBaseNetStatus;
	}
	public void setDataBaseNetStatus(String dataBaseNetStatus) {
		this.dataBaseNetStatus = dataBaseNetStatus;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getPersonLiable() {
		return personLiable;
	}
	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}
	public long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getAppOrder() {
		return appOrder;
	}
	public void setAppOrder(int appOrder) {
		this.appOrder = appOrder;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDataBaseUser() {
		return dataBaseUser;
	}
	public void setDataBaseUser(String dataBaseUser) {
		this.dataBaseUser = dataBaseUser;
	}
	public String getDataBasePwd() {
		return dataBasePwd;
	}
	public void setDataBasePwd(String dataBasePwd) {
		this.dataBasePwd = dataBasePwd;
	}
	public String getDataBaseIns() {
		return dataBaseIns;
	}
	public void setDataBaseIns(String dataBaseIns) {
		this.dataBaseIns = dataBaseIns;
	}
	public String getDataBaseType() {
		return dataBaseType;
	}
	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}
	public String getLastCheckDate() {
		return lastCheckDate;
	}
	public void setLastCheckDate(String lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}
	public int getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(int checkCount) {
		this.checkCount = checkCount;
	}
	
	
		
}
