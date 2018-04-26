package com.apps.operational.checkrecord.domain;

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
 * 
 * @ClassName: OpsCheckRecordInfo 
 * @Description: 运维详情表
 * @author 张梦琦 
 * @date 2017年11月30日 下午4:10:01
 */
@Entity
@Table(name="ops_check_record_info")
public class OpsCheckRecordInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1658757455135803711L;
	//数据库唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//所属记录表id
	private long checkRecordId;
	
	//权限主键检查项id
	@Column(insertable=false,updatable=false)
	private long modelId;
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=OpsCheckRecordModel.class)
	@JoinColumn(name="modelId",referencedColumnName="id")
	private OpsCheckRecordModel opsCheckRecordModel;
	
	//检查项值 0：正常  1 异常
	private String state;
	
	public OpsCheckRecordModel getOpsCheckRecordModel() {
		return opsCheckRecordModel;
	}
	public void setOpsCheckRecordModel(OpsCheckRecordModel opsCheckRecordModel) {
		this.opsCheckRecordModel = opsCheckRecordModel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCheckRecordId() {
		return checkRecordId;
	}
	public void setCheckRecordId(long checkRecordId) {
		this.checkRecordId = checkRecordId;
	}
	public long getModelId() {
		return modelId;
	}
	public void setModelId(long modelId) {
		this.modelId = modelId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
		
}
