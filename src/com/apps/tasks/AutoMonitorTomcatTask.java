package com.apps.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.apps.daily.tomcat.domain.AsopTomcat;
import com.apps.daily.tomcat.service.TomcatService;
import com.apps.utils.WarningUtil;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.WarningLogService;
import com.common.FormatDateUtil;
import com.common.constants.Constant;
import com.common.context.ApplicationContextUtil;
import com.common.servlet.InitServlet;
import com.common.threadPool.ErrorLogPool;
import com.common.threadPool.WarningLogPool;
import com.common.utils.TomcatHTMLUtil;

/**
 * 
 * @ClassName: AutoMonitorDatabaseTask 
 * @Description: 定时监测tomcat运行情况
 * @author 张梦琦 
 * @date 2017年11月8日 上午9:54:18
 */
public class AutoMonitorTomcatTask {
  
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	TomcatService tomcatService = (TomcatService) ApplicationContextUtil.getBean("tomcatService");
	WarningLogService warningLogService =  (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
	
	/**
	 * @Title: mointorTomcat
	 * @Description: 监控Tomcat中间件运行情况
	 * @return void
	 * @throws
	 */
	public void mointorTomcat(){
		AsopTomcat asopTomcat = new AsopTomcat();
		asopTomcat.setIsUse(Constant.ACTIVITY);//正在使用的中间件
		List<AsopTomcat> asopTomcats = tomcatService.getList(asopTomcat);
		if(asopTomcats != null && asopTomcats.size() > 0){
			for(AsopTomcat tomcat :asopTomcats){
				checkupTomcat(tomcat);
			}
			
		}
		
	}
	
	/**
	 * @Title: checkupServer
	 * @Description: 检查各个服务器运行状态
	 * @param server
	 * @return void
	 * @throws
	 */
	private void checkupTomcat(AsopTomcat tomcat){
		StringWriter sw = new StringWriter();
		try {
			System.out.println(sdf.format(new Date())+"---Tomcat中间件开始检查：【"+tomcat.getName()+"】，appName是：【"+tomcat.getWebAppName()+"】");
			String webAppName = tomcat.getWebAppName().split(",")[0];
			boolean result=	TomcatHTMLUtil.startWebApp(tomcat.getManagerUrl(),webAppName, tomcat.getManagerUserName(), tomcat.getManagerPassword(),Integer.parseInt( tomcat.getVersion()));
			
			if(result ){
				tomcat.setTomcatStatus(Constant.NORMAL_STATUS);//运行
			}else{
				tomcat.setTomcatStatus(Constant.EXCEPTION_STATUS);//停止
			}
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(sw));  
			ErrorLogPool pool = InitServlet.getErrorPool();
			pool.saveMessage(WarningUtil.buildErrorLog(tomcat, sw.toString()));//构建错误日志,放入消息池
			tomcat.setTomcatStatus(Constant.EXCEPTION_STATUS);//停止
		} finally{
			putWarningLogPool(tomcat);
			tomcat.setCheckCount(tomcat.getCheckCount()+1);
			tomcat.setLastCheckDate(sdf.format(new Date()));
			tomcatService.saveOrUpdateTomcat(tomcat);
			System.out.println(sdf.format(new Date())+"---Tomcat中间件检查结束：【"+tomcat.getName()+"】，ip是：【"+tomcat.getWebAppName()+"】，检查结果：【"+("0".equals(tomcat.getTomcatStatus())?"运行正常":"运行异常")+"】");
		}
		
	}
	/**
	 * 根据情况来判断要不要放入告警池，异常放入，正常：且之前有异常未处理，放入，并且更新之前的为已处理
	 * @param tomcat
	 */
	private void putWarningLogPool(AsopTomcat tomcat){
		if(Constant.NORMAL_STATUS.equals(tomcat.getTomcatStatus())){
			//监测为正常运行
			WarningLog log = new WarningLog();
			log.setMonitorId(tomcat.getId()+"");
			log.setMonitorType(Constant.DICT_MIDDLEWARE);
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
				pool.saveMessage(WarningUtil.buildTomcatWarningLog(tomcat));
			}
		}else{
			WarningLogPool pool = InitServlet.getPool();
			pool.saveMessage(WarningUtil.buildTomcatWarningLog(tomcat));
		}
	}
}
