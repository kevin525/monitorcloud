package com.apps.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.apps.daily.middleware.domain.AsopMiddleware;
import com.apps.daily.middleware.service.MiddlewareService;
import com.apps.utils.TelnetUtil;
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
 * @ClassName: AutoMonitorMiddlewareTask
 * @Description: 定时监测中间件运行情况
 * @author LG
 * @date 2017年9月22日 下午3:00:02
 */
public class AutoMonitorMiddlewareTask {
  
	MiddlewareService middlewareService = (MiddlewareService) ApplicationContextUtil.getBean("middlewareService");
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	WarningLogService warningLogService =  (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
	
	/**
	 * @Title: mointorServer
	 * @Description: 监控正在使用的中间件
	 * @return void
	 * @throws
	 */
	public void mointorMiddleware(){
		List<AsopMiddleware> asopMiddlewareList = middlewareService.getAsopMiddlewareList(new AsopMiddleware(Constant.ACTIVITY));
		if(asopMiddlewareList != null && asopMiddlewareList.size() > 0){
			for(AsopMiddleware middleware:asopMiddlewareList){
				checkupMiddleware(middleware);
			}
		}
	}
	
	private void checkupMiddleware(AsopMiddleware middleware){
		StringWriter sw = new StringWriter(); 
		try {
			System.out.println(sdf.format(new Date())+"---应用服务中间件开始检查：【"+middleware.getName()+"】，ip是：【"+middleware.getIp()+"】，端口是：【"+middleware.getPort()+"】");
			boolean result = TelnetUtil.testTelnet(middleware.getIp(), Integer.parseInt(middleware.getPort()));
			if(result){
				middleware.setStatus(Constant.NORMAL_STATUS);//中间件正常
			}else{
				try {
					Thread.sleep(30000);
					boolean resultAgain = TelnetUtil.testTelnet(middleware.getIp(), Integer.parseInt(middleware.getPort()));
				    if(resultAgain){
				    	middleware.setStatus(Constant.NORMAL_STATUS);//服务器正常
				    }else{
				    	middleware.setStatus(Constant.EXCEPTION_STATUS);//服务器异常
				    }
				} catch (InterruptedException e) {
					e.printStackTrace();
					middleware.setStatus(Constant.EXCEPTION_STATUS);//服务器异常
				}
			}
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(sw));  
			ErrorLogPool pool = InitServlet.getErrorPool();
			pool.saveMessage(WarningUtil.buildErrorLog(middleware, sw.toString()));//构建错误日志,放入消息池
			middleware.setStatus(Constant.EXCEPTION_STATUS);//服务器异常
		} finally{
			putWarningLogPool(middleware);
			middleware.setCheckCount(middleware.getCheckCount()+1);
			middleware.setLastCheckDate(sdf.format(new Date()));
			middlewareService.saveOrupdateMiddleware(middleware);
			System.out.println(sdf.format(new Date())+"---应用服务中间件检查结束：【"+middleware.getName()+"】，ip是：【"+middleware.getIp()+"】，端口是：【"+middleware.getPort()+"】，检查结果：【"+("0".equals(middleware.getStatus())?"运行正常":"运行异常")+"】");
		}
	}
	
	/**
	
	/**
	 * 根据情况来判断要不要放入告警池，异常放入，正常：且之前有异常未处理，放入，并且更新之前的为已处理
	 * @param middleware
	 */
	private void putWarningLogPool(AsopMiddleware middleware){
		if(Constant.NORMAL_STATUS.equals(middleware.getStatus())){
			//监测为正常运行
			WarningLog log = new WarningLog();
			log.setMonitorId(middleware.getId()+"");
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
				pool.saveMessage(WarningUtil.buildMiddlewareWarningLog(middleware));
			}
		}else{
			WarningLogPool pool = InitServlet.getPool();
			pool.saveMessage(WarningUtil.buildMiddlewareWarningLog(middleware));
		}
	}
}
