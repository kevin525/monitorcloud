package com.apps.warn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @ClassName: Warning
 * @Description: 告警规则设置
 * @author LG
 * @date 2017年11月28日 上午10:46:58
 */
@Entity
@Table(name="asop_warning")
public class AsopWarning  implements Serializable{

	private static final long serialVersionUID = 319252808380465590L;
	//告警id
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	//告警名称
	private String warningName;
	//告警类别：服务器server、应用系统system、数据库database、中间件middleware
	private String warningType;
	//告警描述
	private String warningDesc;
	//是否启用，0：启用，1：禁用，2：删除
	private String isUse;
	//间隔 xx分钟，再次发送告警 
	private String minute;
	//事件连续发生 xx次后，停止发送告警
	private String count;
	//当发过告警监测点恢复正常时发送一次告警，0：不发送，1：发送； 
	private String isNormal;
	//是否发送邮件，0：不发送，1：发送； 
	private String isSendEmail;
	//是否发送短信，0：不发送，1：发送； 
	private String isSendMessage;
	//是否发送短消息，0：不发送，1：发送；
	private String isSendInform;
	//创建人ID
	private long createUserId;
	//创建时间
	private String createDate;
	//修改人
	private long modifyUserId;
	//修改时间
	private String modifyDate;
	public String getWarningName() {
		return warningName;
	}
	public void setWarningName(String warningName) {
		this.warningName = warningName;
	}
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public String getWarningDesc() {
		return warningDesc;
	}
	public void setWarningDesc(String warningDesc) {
		this.warningDesc = warningDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getIsNormal() {
		return isNormal;
	}
	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}
	public String getIsSendEmail() {
		return isSendEmail;
	}
	public void setIsSendEmail(String isSendEmail) {
		this.isSendEmail = isSendEmail;
	}
	public String getIsSendMessage() {
		return isSendMessage;
	}
	public void setIsSendMessage(String isSendMessage) {
		this.isSendMessage = isSendMessage;
	}
	public String getIsSendInform() {
		return isSendInform;
	}
	public void setIsSendInform(String isSendInform) {
		this.isSendInform = isSendInform;
	}
	public long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
