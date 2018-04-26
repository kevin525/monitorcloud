package com.apps.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.appsystem.service.AppSystemService;
import com.apps.daily.appsystem.service.MonitorSystemContainer;
import com.apps.utils.WarningUtil;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.WarningLogService;
import com.common.FormatDateUtil;
import com.common.constants.Constant;
import com.common.context.ApplicationContextUtil;
import com.common.servlet.InitServlet;
import com.common.threadPool.ErrorLogPool;
import com.common.threadPool.WarningLogPool;

/**
 * @ClassName: AutoMonitorAppSystemTask
 * @Description: 定时监控应用系统运行情况
 * @author LG
 * @date 2017年9月25日 下午4:52:25
 */
public class AutoMonitorAppSystemTask {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	AppSystemService appSystemService = (AppSystemService) ApplicationContextUtil.getBean("appSystemService");
	WarningLogService warningLogService =  (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
	/**
	 * @Title: mointorAppSystem
	 * @Description: 监控正在使用的应用系统
	 * @return void
	 * @throws
	 */
	public void mointorAppSystem(){
		AsopAppSystem appSystem = new AsopAppSystem();
		appSystem.setIsUse(Constant.ACTIVITY);//正在使用的应用系统
	
		List<AsopAppSystem> appSystemList = appSystemService.getAppSystemList(appSystem);
		if(appSystemList != null && appSystemList.size() > 0){
			for(AsopAppSystem app:appSystemList){
				checkupAppSystem(app);
			}
		}
	}
	
	/**
	 * @Title: checkupAppSystem
	 * @Description: 检查各个应用系统运行情况
	 * @param app
	 * @return void
	 * @throws
	 */
	private void checkupAppSystem(AsopAppSystem app){
		StringWriter sw = new StringWriter();
		//MonitorSystemContainer container = new MonitorSystemContainer();
		AsopAppSystem system = null;
		try {
			System.out.println(sdf.format(new Date())+"---应用系统开始检查：【"+app.getAppName()+"】，ip是：【"+app.getAppIp()+"】");
			system = MonitorSystemContainer.monitorAppSystem(app);
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(sw));  
			ErrorLogPool pool = InitServlet.getErrorPool();
			pool.saveMessage(WarningUtil.buildErrorLog(app, sw.toString()));//构建错误日志,放入消息池
			if(system ==null ){
				system = appSystemService.getAppSystemById(app.getId());
			}
			system.setAppStatus(Constant.EXCEPTION_STATUS);
		} finally{
			putWarningLogPool(system);
			system.setLastCheckDate(sdf.format(new Date()));
			system.setCheckCount(system.getCheckCount()+1);
			appSystemService.saveOrupdateAppSystem(system);
			System.out.println(sdf.format(new Date())+"---应用系统检查结束：【"+app.getAppName()+"】，ip是：【"+app.getAppIp()+"】，检查结果：【"+("0".equals(app.getAppStatus())?"运行正常":"运行异常")+"】");
		}
		
	}
	
	
	
	
	/**
	 * 根据情况来判断要不要放入告警池，异常放入，正常：且之前有异常未处理，放入，并且更新之前的为已处理
	 * @param appSystem
	 */
	private void putWarningLogPool(AsopAppSystem appSystem){
		if(Constant.NORMAL_STATUS.equals(appSystem.getAppStatus())){
			//监测为正常运行
			WarningLog log = new WarningLog();
			log.setMonitorId(appSystem.getId()+"");
			log.setMonitorType(Constant.DICT_APP_SYSTEM);
			log.setStatus(Constant.EXCEPTION_STATUS);
			log.setFlag(Constant.WARN_NOT_HANDLE);
			List<WarningLog> list = warningLogService.getWarningLogList(log);
			if(null !=list && list.size()>0){
				for (WarningLog warningLog : list) {
					warningLog.setFlag(Constant.WARN_HANDLE);
					warningLog.setWarnDesc("系统恢复正常，已自动处理！");
					warningLog.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
					warningLogService.saveOrupdateWarning(warningLog);
				}
				//说明之前有告警，但是此刻是正常的。判断规则来发送短信
				WarningLogPool pool = InitServlet.getPool();
				pool.saveMessage(WarningUtil.buildAppSystemWarningLog(appSystem));
			}
		}else{
			WarningLogPool pool = InitServlet.getPool();
			pool.saveMessage(WarningUtil.buildAppSystemWarningLog(appSystem));
		}
	}
}
