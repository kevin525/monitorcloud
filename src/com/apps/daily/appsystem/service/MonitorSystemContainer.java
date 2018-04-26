package com.apps.daily.appsystem.service;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.utils.PingUtil;
import com.common.StringHelpers;
import com.common.utils.HttpUtil;
import com.sys.util.Config;
import com.sys.util.DBUtil;

/**
 * @ClassName: MonitorSystemContainer
 * @Description: 自动监控应用系统容器
 * @author LG
 * @date 2017年9月25日 下午4:25:28
 */
public  class MonitorSystemContainer {
	
	
	public static AsopAppSystem monitorAppSystem(AsopAppSystem appSystem) throws Exception{
		boolean appNetStatus = getAppNetStatus(appSystem.getAppIp());
		appSystem.setAppNetStatus(appNetStatus==true?"0":"1");
		boolean dataBaseNetStatus=false;
		try{
			dataBaseNetStatus = getDataBaseNetStatus(appSystem);
		} catch (Exception e) {
			dataBaseNetStatus = false;
		}
		appSystem.setDataBaseNetStatus(dataBaseNetStatus==true?"0":"1");
		if(appNetStatus && dataBaseNetStatus){
			
			boolean appStatus = getSystemData(appSystem);
			appSystem.setAppStatus(appStatus==true?"0":"1");
		}else{
			appSystem.setAppStatus("1");
		}
		return appSystem;
	}
	
	/**
	 * @Title: getSystemNetStatus
	 * @Description: 获取系统网络状态
	 * @param ip
	 * @return boolean
	 * @throws
	 */
	public static boolean getAppNetStatus(String ip) throws Exception{
		return PingUtil.ping(ip,Integer.parseInt(Config.getAttribute("pingTimes")), Integer.parseInt(Config.getAttribute("timeOut")));
	}
	
	/**
	 * @Title: getDataBaseNetStatus
	 * @Description: 获取数据库网络状态
	 * @param ip
	 * @return boolean
	 * @throws
	 */
	public static boolean getDataBaseNetStatus(AsopAppSystem appSystem) throws Exception{
		boolean netStatus = PingUtil.ping(appSystem.getDataBaseIp(),Integer.parseInt(Config.getAttribute("pingTimes")), Integer.parseInt(Config.getAttribute("timeOut")));
		if(!netStatus){
			return netStatus;
		}
		if(!StringHelpers.isNull(appSystem.getDataBaseIp()) && !StringHelpers.isNull(appSystem.getDataBaseUser())){
			boolean dbStatus = DBUtil.testDataBaseConn(appSystem.getDataBaseIp(), appSystem.getDataBaseUser(), appSystem.getDataBasePwd(), appSystem.getDataBaseType(),appSystem.getDataBaseIns(),appSystem.getDataBaseName());
			if(netStatus && dbStatus){
				return true;
			}else{
				return false;
			}
		}else{
			return netStatus;
		}
	}
	
	/**
	 * @Title: getSystemData
	 * @Description: 是否能获取到应用系统的值
	 * @param appSystem
	 * @return void
	 * @throws
	 */
	public static  boolean getSystemData(AsopAppSystem appSystem) throws Exception{
		boolean result = HttpUtil.testUrlConnect(appSystem.getUrl());
		return result;
	}
	
}