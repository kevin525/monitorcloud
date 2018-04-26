package com.apk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apk.model.MonitorBean;
import com.apk.model.ReturnData;
import com.apk.util.BuildBeanUtil;
import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.appsystem.service.AppSystemService;
import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.database.service.DatabaseService;
import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.server.service.ServerService;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.apps.daily.tomcat.service.TomcatService;
import com.common.constants.Constant;
@Controller
@RequestMapping("/appMonitor")
public class AppMonitorController {
	@Autowired
	private AppSystemService appSystemService;
	
	@Autowired
	private ServerService serverService;
	
	@Autowired
	private TomcatService tomcatService;
	
	@Autowired
	private DatabaseService databaseService;
	
	@ResponseBody
	@RequestMapping(value ="/getMonitorList.do",method = RequestMethod.POST)
	public ReturnData getMonitorList(MonitorBean monitorBean){
		
		 List<MonitorBean> list = new ArrayList<MonitorBean>();
		 
		 if(monitorBean.getMonitorType().equals(Constant.DICT_SERVER)){
			 List<AsopServer> asopServerList = serverService.getAsopServerList(new AsopServer("0"));
			 list.addAll(BuildBeanUtil.buildServer2MonitorBean(asopServerList));
			 
		 }else if(monitorBean.getMonitorType().equals(Constant.DICT_DATABASE)){
				
			 List<AsopDatabase> asopDatabaseList = databaseService.getList(new AsopDatabase("0"));
			 list.addAll(BuildBeanUtil.buildDatabase2MonitorBean(asopDatabaseList));
			 
		 }else if(monitorBean.getMonitorType().equals(Constant.DICT_MIDDLEWARE)){
			 
			 List<AsopTomcat> asopTomcatList = tomcatService.getList(new AsopTomcat("0"));
			 list.addAll(BuildBeanUtil.buildMiddleware2MonitorBean(asopTomcatList));
			 
		 }else if(monitorBean.getMonitorType().equals(Constant.DICT_APP_SYSTEM)){
			 
			 List<AsopAppSystem> asopSystemList = appSystemService.getAppSystemList(new AsopAppSystem("0"));
			 list.addAll(BuildBeanUtil.buildSystem2MonitorBean(asopSystemList));
				
		 }else{
			 List<AsopServer> asopServerList = serverService.getAsopServerList(new AsopServer("0"));
			 list.addAll(BuildBeanUtil.buildServer2MonitorBean(asopServerList));
			 
			 List<AsopDatabase> asopDatabaseList = databaseService.getList(new AsopDatabase("0"));
			 list.addAll(BuildBeanUtil.buildDatabase2MonitorBean(asopDatabaseList));
			
			 List<AsopTomcat> asopTomcatList = tomcatService.getList(new AsopTomcat("0"));
			 list.addAll(BuildBeanUtil.buildMiddleware2MonitorBean(asopTomcatList));
			
			 List<AsopAppSystem> asopSystemList = appSystemService.getAppSystemList(new AsopAppSystem("0"));
			 list.addAll(BuildBeanUtil.buildSystem2MonitorBean(asopSystemList));
			
			
			 
		 }
		
		
		
		
		ReturnData data  = new ReturnData(1, "success", list);
		return data;
	}
	
}
