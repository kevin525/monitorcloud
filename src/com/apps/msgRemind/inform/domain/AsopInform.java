package com.apps.msgRemind.inform.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: AsopSms 
 * @Description: 邮件
 * @author 张梦琦 
 * @date 2018年4月9日 下午2:43:18
 */
@Entity
@Table(name="asop_inform")
public class AsopInform implements Serializable{

	private static final long serialVersionUID = 7197256110945239549L;
	//邮件唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//监控id
	private String monitorId;
	//监控类型
	private String monitorType;
	//内容
	private String content;
	//发送时间
	private String sendDate;
	//阅读状态，是否已读 0 已读 1 未读
	private String readStatus;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}
	public String getMonitorType() {
		return monitorType;
	}
	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	
}
