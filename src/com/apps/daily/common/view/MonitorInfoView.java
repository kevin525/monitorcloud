package com.apps.daily.common.view;

public class MonitorInfoView {

	    //唯一标识
		private String id;
		//类别：server、middleware、database
		private String type;
		//类型名称
		private String typeName;
		//名称
		private String name;
		//运行状态
		private String status;
		//最近一次检查时间
		private String lastCheckDate;
		//累计检查次数
		private int checkCount;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getLastCheckDate() {
			return lastCheckDate;
		}
		public void setLastCheckDate(String lastCheckDate) {
			this.lastCheckDate = lastCheckDate;
		}
		public int getCheckCount() {
			return checkCount;
		}
		public void setCheckCount(int checkCount) {
			this.checkCount = checkCount;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
}
