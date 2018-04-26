package com.apps.operational.checkrecord.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: OpsCheckRecord 
 * @Description: 巡检记录，
 * @author 张梦琦 
 * @date 2017年11月29日 下午2:41:36
 */
@Entity
@Table(name="ops_check_record")
public class OpsCheckRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1658757455135803711L;
	//数据库唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//检查项名称
	private String name;
	
	private String remark;
	//责任人
	private String personLiable;
	//创建人ID
	private long createUserId;
	//创建时间
	private String createDate;
	
	//运行环境：0：正式环境，1：测试环境
	private String environment;
	//是否启用，0：启用，2：删除
	private String isUse;
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
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
		
}
