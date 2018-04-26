package com.apps.daily.server.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: AsopServer
 * @Description: 服务器实体类
 * @author LG
 * @date 2017年9月22日 上午9:33:30
 */
@Entity
@Table(name="asop_server")
public class AsopServer implements Serializable{

	private static final long serialVersionUID = -7979296841307226791L;
	//服务器唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//服务器名称
	private String name;
	//服务器ip
	private String ip;
	//服务器型号
	private String model;
	//服务器cpu 
	private String cpu;
	//服务器内存大小
	private String memorySize; 
	//服务器硬盘大小
	private String hardDiskSize;
	//服务器硬盘可用大小
	private String availableHDSzie;
	//操作系统名称
	private String osName;
	//是否启用，0：启用，1：禁用，2：删除
	private String isUse;
	//服务器运行状态，0：正常，1：不正常
	private String status;
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
	private int serverOrder;
	//运行环境：0：正式环境，1：测试环境
	private String environment;
	//最近的一次检查时间
	private String lastCheckDate;
	//累计检查次数
	private int checkCount;
	
	
	public AsopServer() {
		super();
	}
	
	public AsopServer(String isUse) {
		super();
		this.isUse = isUse;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMemorySize() {
		return memorySize;
	}
	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}
	public String getHardDiskSize() {
		return hardDiskSize;
	}
	public void setHardDiskSize(String hardDiskSize) {
		this.hardDiskSize = hardDiskSize;
	}
	public String getAvailableHDSzie() {
		return availableHDSzie;
	}
	public void setAvailableHDSzie(String availableHDSzie) {
		this.availableHDSzie = availableHDSzie;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
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
	public long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public int getServerOrder() {
		return serverOrder;
	}
	public void setServerOrder(int serverOrder) {
		this.serverOrder = serverOrder;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
