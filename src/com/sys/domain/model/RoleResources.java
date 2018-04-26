package com.sys.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @ClassName: RolePrivilege 
 * @Description: 角色-权限对应类
 * @author LG
 * @date 2017年3月1日 上午10:40:09
 */
@Entity
@Table(name="sys_role_resources")
public class RoleResources implements Serializable{

	
	private static final long serialVersionUID = -2096518910689606253L;
	//ID
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	//角色主键
	@Column(insertable=false,updatable=false)
	private long roId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="roId",referencedColumnName="roleId")
	private Role role;
	//权限主键
	@Column(insertable=false,updatable=false)
	private long rsId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rsId",referencedColumnName="resourcesId")
	private Resources resources;
	//角色权限状态（1:启用2：禁用）
	private int rPrivState=1;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRoId() {
		return roId;
	}
	public void setRoId(long roId) {
		this.roId = roId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public long getRsId() {
		return rsId;
	}
	public void setRsId(long rsId) {
		this.rsId = rsId;
	}
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	public int getrPrivState() {
		return rPrivState;
	}
	public void setrPrivState(int rPrivState) {
		this.rPrivState = rPrivState;
	}
	
	
	
	
	
}
