package com.sys.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @ClassName: Resources 
 * @Description: 系统菜单类
 * @author LG
 * @date 2017年3月7日 下午12:59:27
 */
@Entity
@Table(name="sys_resources")
public class Resources extends BaseModel {
	
	private static final long serialVersionUID = -2292445046069026774L;
	//菜单ID
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long resourcesId;
	//菜单名称
	private String resourcesName;
	//菜单别名
	private String resoucesCode;
	//菜单访问地址
	private String resourcesUrl;
	//菜单图标
	private String resoucesImg;
	//菜单父节点
	private long resourcesPid;
	//菜单状态(0代表禁用，1代表启用)
	private int resourcesState;
	//菜单次序
	private int resourcesOrderNum;
	//菜单节点值（1代表一级菜单，2代表二级菜单）
	private int nodeValue;
	
	
	public String getResourcesName() {
		return resourcesName;
	}
	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}
	public String getResourcesUrl() {
		return resourcesUrl;
	}
	public void setResourcesUrl(String resourcesUrl) {
		this.resourcesUrl = resourcesUrl;
	}
	public int getResourcesState() {
		return resourcesState;
	}
	public void setResourcesState(int resourcesState) {
		this.resourcesState = resourcesState;
	}
	public int getResourcesOrderNum() {
		return resourcesOrderNum;
	}
	public void setResourcesOrderNum(int resourcesOrderNum) {
		this.resourcesOrderNum = resourcesOrderNum;
	}
	public String getResoucesImg() {
		return resoucesImg;
	}
	public void setResoucesImg(String resoucesImg) {
		this.resoucesImg = resoucesImg;
	}
	public long getResourcesPid() {
		return resourcesPid;
	}
	public void setResourcesPid(long resourcesPid) {
		this.resourcesPid = resourcesPid;
	}
	public int getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(int nodeValue) {
		this.nodeValue = nodeValue;
	}
	public String getResoucesCode() {
		return resoucesCode;
	}
	public void setResoucesCode(String resoucesCode) {
		this.resoucesCode = resoucesCode;
	}
	public long getResourcesId() {
		return resourcesId;
	}
	public void setResourcesId(long resourcesId) {
		this.resourcesId = resourcesId;
	}
	
	
}
