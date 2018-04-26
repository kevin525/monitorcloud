package com.apps.daily.database.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据库实体类
 * @author an
 *
 */
@Entity
@Table(name="asop_database")
public class AsopDatabase implements Serializable{

	private static final long serialVersionUID = -7979296841307226791L;
	//数据库唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//名称，例如党政信息工作管理系统数据库
	private String name;
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
	//数据库网络状态，0：正常，1：异常
	private String dataBaseNetStatus;
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
	
	//最近的一次检查时间
	private String lastCheckDate;
	//累计检查次数
	private int checkCount;
	
	public AsopDatabase() {
		super();
	}
	
	public AsopDatabase(String isUse) {
		super();
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
	public String getDataBaseType() {
		return dataBaseType;
	}
	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
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
	public String getDataBaseNetStatus() {
		return dataBaseNetStatus;
	}
	public void setDataBaseNetStatus(String dataBaseNetStatus) {
		this.dataBaseNetStatus = dataBaseNetStatus;
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
