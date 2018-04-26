package com.common.excel.export;

public class Access {

	public Access(String sysName, String accessNum, String startDate,String endDate) {
		this.sysName = sysName;
		this.accessNum = accessNum;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	private String sysName;

	private String accessNum;
	
	private String startDate;
	
	private String endDate;

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getAccessNum() {
		return accessNum;
	}

	public void setAccessNum(String accessNum) {
		this.accessNum = accessNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	 
}
