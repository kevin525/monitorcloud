package com.apps.msgRemind.sms.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: AsopSms
 * @Description: 短信实体类
 * @author LG
 * @date 2017年9月22日 上午9:33:30
 */
@Entity
@Table(name="asop_sms")
public class AsopSms implements Serializable{

	private static final long serialVersionUID = -7979296841307226791L;
	//短信唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//服务器id或者应用系统id等进行关联
	private long recordId;
	//短信类别，0：服务器信息，1：应用系统信息
	private String type;
	//短信内容
	private String content;
	//短信手机号
	private String phoneNums;
	//发送时间
	private String sendDate;
	//是否启用，0：启用，1：禁用，2：删除
	private String isUse;
	//次序
	private int smsOrder;
	//短信发送状态：0：发送成功，1：发送失败
	private String sendStatus;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhoneNums() {
		return phoneNums;
	}
	public void setPhoneNums(String phoneNums) {
		this.phoneNums = phoneNums;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public int getSmsOrder() {
		return smsOrder;
	}
	public void setSmsOrder(int smsOrder) {
		this.smsOrder = smsOrder;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	
		
}
