package com.sys.domain.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = 5002338992891483848L;
	//创建人ID
	private long createUserId;
	//创建时间
	private Date createDate;
	//修改人
	private long modifyUserId;
	//修改时间
	private Date modifyDate;
	
	
	public long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	

}
