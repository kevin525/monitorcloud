package com.apps.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.database.service.DatabaseService;
import com.apps.utils.PingUtil;
import com.apps.utils.WarningUtil;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.WarningLogService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.context.ApplicationContextUtil;
import com.common.servlet.InitServlet;
import com.common.threadPool.ErrorLogPool;
import com.common.threadPool.WarningLogPool;
import com.sys.util.Config;
import com.sys.util.DBUtil;

/**
 * 
 * @ClassName: AutoMonitorDatabaseTask 
 * @Description: 定时监测数据库运行情况
 * @author 张梦琦 
 * @date 2017年11月8日 上午9:54:18
 */
public class AutoMonitorDatabaseTask {
  
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DatabaseService databaseService = (DatabaseService) ApplicationContextUtil.getBean("databaseService");
	WarningLogService warningLogService = (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
	/**
	 * @Title: mointorDatabase
	 * @Description: 监控正在使用的数据库
	 * @return void
	 * @throws
	 */
	public void mointorDatabase(){
		AsopDatabase asopDatabase = new AsopDatabase();
		asopDatabase.setIsUse(Constant.ACTIVITY);//正在使用的数据库
		List<AsopDatabase> asopDatabases = databaseService.getList(asopDatabase);
		if(asopDatabases != null && asopDatabases.size() > 0){
			for(AsopDatabase database:asopDatabases){
				checkupDataBase(database);
			}
			
		}
		
	}
	
	/**
	 * @Title: checkupDataBase
	 * @Description: 检查各个数据库运行情况
	 * @param server
	 * @return void
	 * @throws
	 */
	private void checkupDataBase(AsopDatabase database){
		StringWriter sw = new StringWriter(); 
		try {
			System.out.println(sdf.format(new Date())+"---数据库开始检查：【"+database.getName()+"】，ip是：【"+database.getDataBaseIp()+"】");
			String ip = database.getDataBaseIp();
			boolean result = PingUtil.ping(ip,Integer.parseInt(Config.getAttribute("pingTimes")), Integer.parseInt(Config.getAttribute("timeOut")));
			if(result && !StringHelpers.isNull(database.getDataBaseIp())){
				boolean dbStatus = DBUtil.testDataBaseConn(database.getDataBaseIp(), database.getDataBaseUser(), database.getDataBasePwd(), database.getDataBaseType(),database.getDataBaseIns(),database.getDataBaseName());
				if(result && dbStatus){
					database.setDataBaseNetStatus(Constant.NORMAL_STATUS);//数据库正常
				}else{
					database.setDataBaseNetStatus(Constant.EXCEPTION_STATUS);//数据库异常
				}
			}else{
				database.setDataBaseNetStatus(Constant.EXCEPTION_STATUS);//数据库异常
			}
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(sw));  
			ErrorLogPool pool = InitServlet.getErrorPool();
			pool.saveMessage(WarningUtil.buildErrorLog(database, sw.toString()));//构建错误日志,放入消息池
			database.setDataBaseNetStatus(Constant.EXCEPTION_STATUS);//数据库异常
		} finally{
			putWarningLogPool(database);
			database.setCheckCount(database.getCheckCount()+1);
			database.setLastCheckDate(sdf.format(new Date()));
			databaseService.saveOrUpdateDatabase(database);
			System.out.println(sdf.format(new Date())+"---数据库检查结束：【"+database.getName()+"】，ip是：【"+database.getDataBaseIp()+"】，检查结果：【"+("0".equals(database.getDataBaseNetStatus())?"运行正常":"运行异常")+"】");
		
		}
	}
	/**
	 * 根据情况来判断要不要放入告警池，异常放入，正常：且之前有异常未处理，放入，并且更新之前的为已处理
	 * @param database
	 */
	private void putWarningLogPool(AsopDatabase database){
		if(Constant.NORMAL_STATUS.equals(database.getDataBaseNetStatus())){
			//监测为正常运行
			WarningLog log = new WarningLog();
			log.setMonitorId(database.getId()+"");
			log.setMonitorType(Constant.DICT_DATABASE);
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
				pool.saveMessage(WarningUtil.buildDataBaseWarningLog(database));
			}
		}else{
			WarningLogPool pool = InitServlet.getPool();
			pool.saveMessage(WarningUtil.buildDataBaseWarningLog(database));
		}
	}
}
