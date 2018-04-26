package com.apk.util;

import java.util.ArrayList;
import java.util.List;

import com.apk.model.MonitorBean;
import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.common.constants.Constant;

public class BuildBeanUtil {
	
	public static List<MonitorBean> buildServer2MonitorBean(List<AsopServer> asopServerList){
		 List<MonitorBean> list = new ArrayList<MonitorBean>();
		for (AsopServer server : asopServerList) {
			MonitorBean bean = new MonitorBean();
			bean.setMonitorName(server.getName());
			bean.setMonitorIp(server.getIp());
			bean.setMonitorType(Constant.DICT_SERVER);
			bean.setCheckCount(server.getCheckCount());
			bean.setLastCheckTime(server.getLastCheckDate());
			bean.setRunStauts(server.getStatus());
			list.add(bean);
		}
		
		return list;
	}
	public static List<MonitorBean> buildSystem2MonitorBean(List<AsopAppSystem> asopSystemsList){
		 List<MonitorBean> list = new ArrayList<MonitorBean>();
		for (AsopAppSystem system : asopSystemsList) {
			MonitorBean bean = new MonitorBean();
			bean.setMonitorName(system.getAppName());
			bean.setMonitorIp(system.getAppIp());
			bean.setMonitorType(Constant.DICT_APP_SYSTEM);
			bean.setCheckCount(system.getCheckCount());
			bean.setLastCheckTime(system.getLastCheckDate());
			bean.setRunStauts(system.getAppStatus());
			list.add(bean);
		}
		
		return list;
	}
	public static List<MonitorBean> buildDatabase2MonitorBean(List<AsopDatabase> databaseList){
		 List<MonitorBean> list = new ArrayList<MonitorBean>();
		for (AsopDatabase database : databaseList) {
			MonitorBean bean = new MonitorBean();
			bean.setMonitorName(database.getName());
			bean.setMonitorIp(database.getDataBaseIp());
			bean.setMonitorType(Constant.DICT_DATABASE);
			bean.setCheckCount(database.getCheckCount());
			bean.setLastCheckTime(database.getLastCheckDate());
			bean.setRunStauts(database.getDataBaseNetStatus());
			list.add(bean);
		}
		
		return list;
	}
	
	public static List<MonitorBean> buildMiddleware2MonitorBean(List<AsopTomcat> tomcatList){
		 List<MonitorBean> list = new ArrayList<MonitorBean>();
		for (AsopTomcat tomcat : tomcatList) {
			MonitorBean bean = new MonitorBean();
			bean.setMonitorName(tomcat.getName());
			
			String managerUrl = tomcat.getManagerUrl().replace("http://", "")
					.replace("/manager", "");
			String ip = managerUrl.split(":")[0];
			
			bean.setMonitorIp(ip);
			bean.setMonitorType(Constant.DICT_MIDDLEWARE);
			bean.setCheckCount(tomcat.getCheckCount());
			bean.setLastCheckTime(tomcat.getLastCheckDate());
			bean.setRunStauts(tomcat.getTomcatStatus());
			list.add(bean);
		}
		
		return list;
	}
}
