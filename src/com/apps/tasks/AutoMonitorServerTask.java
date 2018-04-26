package com.apps.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.server.service.ServerService;
import com.apps.utils.PingUtil;
import com.apps.utils.WarningUtil;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.WarningLogService;
import com.common.FormatDateUtil;
import com.common.constants.Constant;
import com.common.context.ApplicationContextUtil;
import com.common.servlet.InitServlet;
import com.common.threadPool.ErrorLogPool;
import com.common.threadPool.WarningLogPool;
import com.sys.util.Config;

/**
 * @ClassName: AutoMonitorServerTask
 * @Description: 定时监测服务器运行情况
 * @author LG
 * @date 2017年9月22日 下午3:00:02
 */
public class AutoMonitorServerTask {
  
	ServerService serverService = (ServerService) ApplicationContextUtil.getBean("serverService");
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	WarningLogService warningLogService =  (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
	
	/**
	 * @Title: mointorServer
	 * @Description: 监控正在使用的服务器
	 * @return void
	 * @throws
	 */
	public void mointorServer(){
		AsopServer asopServer = new AsopServer();
		asopServer.setIsUse(Constant.ACTIVITY);//正在使用的服务器
		List<AsopServer> asopServerList = serverService.getAsopServerList(asopServer);
		if(asopServerList != null && asopServerList.size() > 0){
			for(AsopServer server:asopServerList){
				checkupServer(server);
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
	private void checkupServer(AsopServer server){
		StringWriter sw = new StringWriter(); 
		try {
			System.out.println(sdf.format(new Date())+"---服务器开始检查：【"+server.getName()+"】，ip是：【"+server.getIp()+"】");
			String ip = server.getIp();
			boolean result = PingUtil.ping(ip,Integer.parseInt(Config.getAttribute("pingTimes")), Integer.parseInt(Config.getAttribute("timeOut")));
			if(result){
				server.setStatus(Constant.NORMAL_STATUS);//服务器正常
			}else{
				try {
					Thread.sleep(30000);
					boolean resultAgain = PingUtil.ping(ip,Integer.parseInt(Config.getAttribute("pingTimes")), Integer.parseInt(Config.getAttribute("timeOut")));
				    if(resultAgain){
				    	server.setStatus(Constant.NORMAL_STATUS);//服务器正常
				    }else{
				    	server.setStatus(Constant.EXCEPTION_STATUS);//服务器异常
				    }
				} catch (InterruptedException e) {
					e.printStackTrace();
					server.setStatus(Constant.EXCEPTION_STATUS);//服务器异常
				}
			}
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(sw));  
			ErrorLogPool pool = InitServlet.getErrorPool();
			pool.saveMessage(WarningUtil.buildErrorLog(server, sw.toString()));//构建错误日志,放入消息池
			server.setStatus(Constant.EXCEPTION_STATUS);//服务器异常
		} finally{
			putWarningLogPool(server);
			server.setCheckCount(server.getCheckCount()+1);
			server.setLastCheckDate(sdf.format(new Date()));
			serverService.saveOrupdateServer(server);
			System.out.println(sdf.format(new Date())+"---服务器检查结束：【"+server.getName()+"】，ip是：【"+server.getIp()+"】，检查结果：【"+("0".equals(server.getStatus())?"运行正常":"运行异常")+"】");
		}
	}
	/**
	 * 根据情况来判断要不要放入告警池，异常放入，正常：且之前有异常未处理，放入，并且更新之前的为已处理
	 * @param server
	 */
	private void putWarningLogPool(AsopServer server){
		if(Constant.NORMAL_STATUS.equals(server.getStatus())){
			//监测为正常运行
			WarningLog log = new WarningLog();
			log.setMonitorId(server.getId()+"");
			log.setMonitorType(Constant.DICT_SERVER);
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
				pool.saveMessage(WarningUtil.buildServerWarningLog(server));
			}
		}else{
			WarningLogPool pool = InitServlet.getPool();
			pool.saveMessage(WarningUtil.buildServerWarningLog(server));
		}
	}
}
