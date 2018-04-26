package com.apps.msgRemind.mail.domain;

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
@Table(name="asop_mail")
public class AsopMail implements Serializable{

	private static final long serialVersionUID = 2938227048377189134L;
	
	//邮件唯一标识
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//监控id
	private String monitorId;
	//监控类型
	private String monitorType;
	//邮件内容
	private String content;
	//邮件标题
	private String subject;
	//接受邮箱地址
	private String receiveAddress;
	//发送邮箱地址
	private String sendAddress;
	//发送时间
	private String sendDate;
	//次序
	private int mailOrder;
	//发送状态：0：发送成功，1：发送失败
	private String sendStatus;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public int getMailOrder() {
		return mailOrder;
	}
	public void setMailOrder(int mailOrder) {
		this.mailOrder = mailOrder;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	
}
