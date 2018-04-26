package com.sys.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Role 
 * @Description: 角色类 
 * @author LG
 * @date 2017年3月1日 上午10:40:59
 */
@Entity
@Table(name="sys_role")
public class Role extends BaseModel{
	
	private static final long serialVersionUID = -7469176185566995712L;
	//角色ID
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long roleId=0;
	//角色名称
	private String roleName;
	//角色状态
	private int roleState;//2:禁用；1：开启
	//角色创建时间
	private String roleCreatTime;
	
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleState() {
		return roleState;
	}
	public void setRoleState(int roleState) {
		this.roleState = roleState;
	}
	public String getRoleCreatTime() {
		return roleCreatTime;
	}
	public void setRoleCreatTime(String roleCreatTime) {
		this.roleCreatTime = roleCreatTime;
	}
}
