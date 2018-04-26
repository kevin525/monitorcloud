package com.apps.daily.tomcat.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: AsopTomcat 
 * @Description: 中间件实体类
 * @author 张梦琦 
 * @date 2017年11月9日 上午10:23:47
 */
@Entity
@Table(name="asop_tomcat")
public class AsopTomcat implements Serializable{

	private static final long serialVersionUID = 558743863398789826L;
	//数据库唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//中间件名称
	private String name;
	//所包含项目名称
	private String webAppName;
	//manager地址
	private String managerUrl;//"http://10.171.251.74:80/manager";
	//manager用户名
	private String managerUserName;
	//manager密码
	private String managerPassword;
	//tomcat版本
	private String version;
	//manager角色
	private String managerRole;
	 //运行状态 0运行   1 停止
    private String tomcatStatus;
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
	//是否启用，0：启用，1：禁用，2：删除
	private String isUse;
	//运行环境：0：正式环境，1：测试环境
	private String environment;
	//最近的一次检查时间
	private String lastCheckDate;
	//累计检查次数
	private int checkCount;
	
	
	public AsopTomcat() {
		super();
	}
	
	public AsopTomcat(String isUse) {
		super();
		this.isUse = isUse;
	}

	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebAppName() {
		return webAppName;
	}
	public void setWebAppName(String webAppName) {
		this.webAppName = webAppName;
	}
	public String getManagerUrl() {
		return managerUrl;
	}
	public void setManagerUrl(String managerUrl) {
		this.managerUrl = managerUrl;
	}
	public String getManagerUserName() {
		return managerUserName;
	}
	public void setManagerUserName(String managerUserName) {
		this.managerUserName = managerUserName;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getManagerRole() {
		return managerRole;
	}
	public void setManagerRole(String managerRole) {
		this.managerRole = managerRole;
	}
	public String getTomcatStatus() {
		return tomcatStatus;
	}
	public void setTomcatStatus(String tomcatStatus) {
		this.tomcatStatus = tomcatStatus;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
