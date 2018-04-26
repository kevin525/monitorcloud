package com.apps.daily.project.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: AsopProject
 * @Description: 项目实体类
 * @author LG
 * @date 2017年9月22日 上午9:33:30
 */
@Entity
@Table(name="asop_project")
public class AsopProject implements Serializable{

	private static final long serialVersionUID = -7979296841307226791L;
	//服务器唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//项目名称
	private String projectName;
	//是否启用，0：启用，1：禁用，2：删除
	private String isUse;
	//项目运行状态，0：正常，1：异常
	private String projectStatus;
	//责任人
	private String personLiable;
	//责任人id
	private String personLiableId;
	//联系方式
	private String contact;
	//创建人ID
	private long createUserId;
	//创建时间
	private String createDate;
	//修改人
	private long modifyUserId;
	//修改时间
	private String modifyDate;
	//次序
	private int projectOrder;
	//运行环境：0：上线，1：未上线
	private String isOnline;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
	public int getProjectOrder() {
		return projectOrder;
	}
	public void setProjectOrder(int projectOrder) {
		this.projectOrder = projectOrder;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPersonLiableId() {
		return personLiableId;
	}
	public void setPersonLiableId(String personLiableId) {
		this.personLiableId = personLiableId;
	}
	
	
	
		
}
