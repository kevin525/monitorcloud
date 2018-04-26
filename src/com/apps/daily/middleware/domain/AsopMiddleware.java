package com.apps.daily.middleware.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: AsopMiddleware
 * @Description: 中间件实体类
 * @author LG
 * @date 2017年9月22日 上午9:33:30
 */
@Entity
@Table(name="asop_middleware")
public class AsopMiddleware implements Serializable{

	private static final long serialVersionUID = 1L;
	//服务器唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//中间件名称
	private String name;
	//中间件ip
	private String ip;
	//中间件端口号
	private String port;
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
	private int middlewareOrder;
	//运行环境：0：正式环境，1：测试环境
	private String environment;
	//最近的一次检查时间
	private String lastCheckDate;
	//累计检查次数
	private int checkCount;
	//备注
	private int remark;
	
	public AsopMiddleware(){
		
	}
	
    public AsopMiddleware(String isUse){
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
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
	public int getMiddlewareOrder() {
		return middlewareOrder;
	}
	public void setMiddlewareOrder(int middlewareOrder) {
		this.middlewareOrder = middlewareOrder;
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

	public int getRemark() {
		return remark;
	}

	public void setRemark(int remark) {
		this.remark = remark;
	}
	
	
	
		
}
