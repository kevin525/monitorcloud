package com.apps.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.middleware.domain.AsopMiddleware;
import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.apps.warn.domain.ErrorLog;
import com.apps.warn.domain.WarningLog;
import com.common.constants.Constant;

public class WarningUtil {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @Title: buildWarningLog
	 * @Description: 构建服务器告警日志
	 * @param server
	 * @return void
	 * @throws
	 */
	public static WarningLog buildServerWarningLog(AsopServer server){
		WarningLog warningLog = new WarningLog();
		warningLog.setMonitorId(String.valueOf(server.getId()));
		warningLog.setMonitorName(Constant.DICT_SERVER_NAME);
		warningLog.setMonitorType(Constant.DICT_SERVER);
		warningLog.setWarnTime(sdf.format(new Date()));
		warningLog.setModifyDate(sdf.format(new Date()));
		warningLog.setStatus(server.getStatus());
		if(server.getStatus().equals(Constant.NORMAL_STATUS)){
			//正常
			warningLog.setFlag(Constant.WARN_HANDLE);
			warningLog.setWarnName("【"+server.getName()+":"+server.getIp()+"】,服务器网络恢复正常！");
		}else{
			warningLog.setFlag(Constant.WARN_NOT_HANDLE);
			warningLog.setWarnName("【"+server.getName()+":"+server.getIp()+"】,服务器网络不通出现异常，请注意检查！");
		}
		return warningLog;
	}
	
	/**
	 * 构建中间件错误日志
	 * @param middleware
	 * @return
	 */
	public static WarningLog buildMiddlewareWarningLog(AsopMiddleware middleware){
		WarningLog warningLog = new WarningLog();
		warningLog.setMonitorId(String.valueOf(middleware.getId()));
		warningLog.setMonitorName(Constant.DICT_MIDDLEWARE_NAME);
		warningLog.setMonitorType(Constant.DICT_MIDDLEWARE);
		warningLog.setWarnTime(sdf.format(new Date()));
		warningLog.setModifyDate(sdf.format(new Date()));
		warningLog.setStatus(middleware.getStatus());
		if(middleware.getStatus().equals(Constant.NORMAL_STATUS)){
			//正常
			warningLog.setFlag(Constant.WARN_HANDLE);
			warningLog.setWarnName("【"+middleware.getName()+":"+middleware.getIp()+","+middleware.getPort()+"】,应用服务中间件恢复正常！");
		}else{
			warningLog.setFlag(Constant.WARN_NOT_HANDLE);
			warningLog.setWarnName("【"+middleware.getName()+":"+middleware.getIp()+","+middleware.getPort()+"】,应用服务中间件出现异常，请注意检查！");
		}
		return warningLog;
	}
	
	/**
	 * @Title: buildErrorLog
	 * @Description: 构建服务器错误日志信息
	 * @param server
	 * @param msg
	 * @return
	 * @return ErrorLog
	 * @throws
	 */
	public static ErrorLog buildErrorLog(AsopServer server,String msg){
		ErrorLog errorLog = new ErrorLog();
		errorLog.setErrorType(Constant.DICT_SERVER);
		errorLog.setErrorName(Constant.DICT_SERVER_NAME);
		errorLog.setErrorTime(sdf.format(new Date()));
		errorLog.setErrorDetail(msg);
		errorLog.setServerIp(server.getIp());
		errorLog.setAppId(server.getId());
		errorLog.setErrorTitle("【"+server.getName()+":"+server.getIp()+"】,服务器出现异常！");
		return errorLog;
	}
	
	/**
	 * 构建中间件错误日志
	 * @param middleware
	 * @param msg
	 * @return
	 */
	public static ErrorLog buildErrorLog(AsopMiddleware middleware,String msg){
		ErrorLog errorLog = new ErrorLog();
		errorLog.setErrorType(Constant.DICT_MIDDLEWARE);
		errorLog.setErrorName(Constant.DICT_MIDDLEWARE_NAME);
		errorLog.setErrorTime(sdf.format(new Date()));
		errorLog.setErrorDetail(msg);
		errorLog.setServerIp(middleware.getIp());
		errorLog.setAppId(middleware.getId());
		errorLog.setErrorTitle("【"+middleware.getName()+":"+middleware.getIp()+","+middleware.getPort()+"】,应用服务中间件出现异常！");
		return errorLog;
	}
	
	
	/**
	 * @Title: buildErrorLog
	 * @Description: 构建数据库错误日志信息
	 * @param server
	 * @param msg
	 * @return
	 * @return ErrorLog
	 * @throws
	 */
	public static ErrorLog buildErrorLog(AsopDatabase database,String msg){
		ErrorLog errorLog = new ErrorLog();
		errorLog.setErrorType(Constant.DICT_DATABASE);
		errorLog.setErrorName(Constant.DICT_DATABASE_NAME);
		errorLog.setErrorTime(sdf.format(new Date()));
		errorLog.setErrorDetail(msg);
		errorLog.setServerIp(database.getDataBaseIp());
		errorLog.setAppId(database.getId());
		errorLog.setErrorTitle("【"+database.getName()+":"+database.getDataBaseIp()+"】,数据库出现异常！");
		return errorLog;
	}
	
	
	/**
	 * @Title: buildErrorLog
	 * @Description: 构建数据库错误日志信息
	 * @param server
	 * @param msg
	 * @return
	 * @return ErrorLog
	 * @throws
	 */
	public static ErrorLog buildErrorLog(AsopAppSystem app,String msg){
		ErrorLog errorLog = new ErrorLog();
		errorLog.setErrorType(Constant.DICT_APP_SYSTEM);
		errorLog.setErrorName(Constant.DICT_APP_SYSTEM_NAME);
		errorLog.setErrorTime(sdf.format(new Date()));
		errorLog.setErrorDetail(msg);
		errorLog.setServerIp(app.getAppIp());
		errorLog.setAppId(app.getId());
		errorLog.setErrorTitle("【"+app.getAppName()+":"+app.getAppIp()+"】,应用系统出现异常！");
		return errorLog;
	}
	
	
	/**
	 * @Title: buildErrorLog
	 * @Description: 构建数据库错误日志信息
	 * @param server
	 * @param msg
	 * @return
	 * @return ErrorLog
	 * @throws
	 */
	public static ErrorLog buildErrorLog(AsopTomcat tomcat,String msg){
		ErrorLog errorLog = new ErrorLog();
		errorLog.setErrorType(Constant.DICT_MIDDLEWARE);
		errorLog.setErrorName(Constant.DICT_MIDDLEWARE_NAME);
		errorLog.setErrorTime(sdf.format(new Date()));
		errorLog.setErrorDetail(msg);
		errorLog.setServerIp(tomcat.getWebAppName());
		errorLog.setAppId(tomcat.getId());
		errorLog.setErrorTitle("【"+tomcat.getName()+":"+tomcat.getWebAppName()+"】,Tomcat运行出现异常！");
		return errorLog;
	}
	
	/**
	 * @Title: buildAppSystemWarningLog
	 * @Description: 构建应用系统告警日志
	 * @param app
	 * @return
	 * @return WarningLog
	 * @throws
	 */
	public static WarningLog buildAppSystemWarningLog(AsopAppSystem app){
		WarningLog warningLog = new WarningLog();
		warningLog.setWarnName("【"+app.getAppName()+":"+app.getAppIp()+"】,应用系统运行异常，请注意检查！");
		warningLog.setMonitorId(String.valueOf(app.getId()));
		warningLog.setMonitorName(Constant.DICT_APP_SYSTEM_NAME);
		warningLog.setMonitorType(Constant.DICT_APP_SYSTEM);
		warningLog.setWarnTime(sdf.format(new Date()));
		warningLog.setModifyDate(sdf.format(new Date()));
		warningLog.setStatus(app.getAppStatus());
		warningLog.setFlag(Constant.WARN_NOT_HANDLE);
		if(app.getAppStatus().equals("0")){
			//正常
			warningLog.setFlag(Constant.WARN_HANDLE);
			warningLog.setWarnName("【"+app.getAppName()+":"+app.getAppIp()+"】,应用系统恢复正常！");
		}
		return warningLog;
	}
	
	
	/**
	 * @Title: buildDataBaseWarningLog
	 * @Description: 构建数据库告警日志,有正常日志 也有异常日志
	 * @param database
	 * @return
	 * @return WarningLog
	 * @throws
	 */
	public static WarningLog buildDataBaseWarningLog(AsopDatabase database){
		WarningLog warningLog = new WarningLog();
		warningLog.setWarnName("【"+database.getName()+":"+database.getDataBaseIp()+"】,数据库运行异常，请注意检查！");
		warningLog.setMonitorId(String.valueOf(database.getId()));
		warningLog.setMonitorName(Constant.DICT_DATABASE_NAME);
		warningLog.setMonitorType(Constant.DICT_DATABASE);
		warningLog.setWarnTime(sdf.format(new Date()));
		warningLog.setModifyDate(sdf.format(new Date()));
//		warningLog.setStatus(Constant.EXCEPTION_STATUS);
		warningLog.setStatus(database.getDataBaseNetStatus());
		warningLog.setFlag(Constant.WARN_NOT_HANDLE);
		if(database.getDataBaseNetStatus().equals("0")){
			//正常
			warningLog.setFlag(Constant.WARN_HANDLE);
			warningLog.setWarnName("【"+database.getName()+":"+database.getDataBaseIp()+"】,数据库恢复正常！");
		}
		return warningLog;
	}
	
	
	/**
	 * @Title: buildTomcatWarningLog
	 * @Description: 构建tomcat告警日志
	 * @param tomcat
	 * @return
	 * @return WarningLog
	 * @throws
	 */
	public static WarningLog buildTomcatWarningLog(AsopTomcat tomcat){
		WarningLog warningLog = new WarningLog();
		warningLog.setWarnName("【"+tomcat.getName()+":"+tomcat.getWebAppName()+"】,Tomcat运行异常，请注意检查！");
		warningLog.setMonitorId(String.valueOf(tomcat.getId()));
		warningLog.setMonitorName(Constant.DICT_MIDDLEWARE_NAME);
		warningLog.setMonitorType(Constant.DICT_MIDDLEWARE);
		warningLog.setWarnTime(sdf.format(new Date()));
		warningLog.setModifyDate(sdf.format(new Date()));
//		warningLog.setStatus(Constant.EXCEPTION_STATUS);
		warningLog.setStatus(tomcat.getTomcatStatus());
		warningLog.setFlag(Constant.WARN_NOT_HANDLE);
		if(tomcat.getTomcatStatus().equals("0")){
			//正常
			warningLog.setFlag(Constant.WARN_HANDLE);
			warningLog.setWarnName("【"+tomcat.getName()+":"+tomcat.getWebAppName()+"】,Tomcat运行恢复正常！");
		}
		return warningLog;
	}

}
