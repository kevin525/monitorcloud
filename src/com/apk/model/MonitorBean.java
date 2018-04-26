package com.apk.model;

public class MonitorBean {

	private String monitorName;
	
	private String monitorType;
	
	private String monitorIp;
	
	private Integer checkCount;
	
	private String lastCheckTime;
	//0正常 1异常
	private String runStauts;

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}

	public String getMonitorIp() {
		return monitorIp;
	}

	public void setMonitorIp(String monitorIp) {
		this.monitorIp = monitorIp;
	}

	public Integer getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}

	public String getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(String lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public String getRunStauts() {
		return runStauts;
	}

	public void setRunStauts(String runStauts) {
		this.runStauts = runStauts;
	}
	
	
}
