package com.apps.operational.checkrecord.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @ClassName: OpsCheckRecordModel 
 * @Description: 巡检项，每个人都有属于自己的巡检项
 * @author 张梦琦 
 * @date 2017年11月30日 下午3:48:01
 */
@Entity
@Table(name="ops_check_record_model")
public class OpsCheckRecordModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7052368880440121248L;

	
	//数据库唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//名称
	private String name;
	//短名
	private String shortName;
	//所属人
	private long ownerId;
	//所属人
	private String ownerName;
	//状态，0：启用，1：禁用，2：删除
	private String isUse;
	//父节点
	private long fatherId;
	//等级 1级菜单 2级菜单
	private int nodeValue;
	//创建时间
	private String createDate;
	//次序
	private int modelOrder;
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public int getModelOrder() {
		return modelOrder;
	}
	public void setModelOrder(int modelOrder) {
		this.modelOrder = modelOrder;
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public long getFatherId() {
		return fatherId;
	}
	public void setFatherId(long fatherId) {
		this.fatherId = fatherId;
	}
	public int getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(int nodeValue) {
		this.nodeValue = nodeValue;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	
}
