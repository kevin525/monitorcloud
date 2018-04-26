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
 * @ClassName: UserRoles 
 * @Description: 用户--角色关系类 
 * @author LG
 * @date 2017年3月1日 上午10:38:46
 */
@Entity
@Table(name="sys_user_roles")
public class UserRoles implements Serializable{
	
	private static final long serialVersionUID = -1896929437476845329L;
	//主键
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long urid;
	//用户ID
	@Column(insertable=false, updatable=false)
	private long usId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "usId", referencedColumnName = "userId")
	private User user;
	//角色ID
	@Column(insertable=false, updatable=false)
	private long rId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "rId", referencedColumnName = "roleId")
	private Role role;
	
	private int uRState=1;//用户角色状态（1：启用；2：禁用）
	
	
	public long getUrid() {
		return urid;
	}
	public void setUrid(long urid) {
		this.urid = urid;
	}
	
	public long getUsId() {
		return usId;
	}
	public void setUsId(long usId) {
		this.usId = usId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getrId() {
		return rId;
	}
	public void setrId(long rId) {
		this.rId = rId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getuRState() {
		return uRState;
	}
	public void setuRState(int uRState) {
		this.uRState = uRState;
	}
}
