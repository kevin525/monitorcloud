package com.apps.warn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.warn.dao.WarningLogDao;
import com.apps.warn.domain.ErrorLog;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.WarningLogService;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;

@Service(value="warningLogService")
public class WarningLogServiceImpl implements WarningLogService{

	@Autowired
	private WarningLogDao warningLogDao;
	@Override
	public void saveOrupdateWarning(WarningLog warningLog) {
		warningLogDao.saveOrupdateWarning(warningLog);
	}
	
	@Override
	public void updateWarning(WarningLog warningLog) {
		warningLogDao.updateWarning(warningLog);
	}
	
	@Override
	public void saveOrupdateError(ErrorLog errorLog) {
		warningLogDao.saveOrupdateError(errorLog);
	}

	@Override
	public WarningLog getWarningLogById(long id) {
		return warningLogDao.getWarningLogById(id);
	}

	@Override
	public void deleteWarningLog(long id) {
		warningLogDao.deleteWarningLog(id);		
	}

	@Override
	public void deleteAllWarning(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	warningLogDao.deleteWarningLog(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public void save(Object o) {
		warningLogDao.save(o);
	}

	@Override
	public List<WarningLog> getWarningLogList(WarningLog warningLog,
			PageGridPost pageGridPost) {
		return warningLogDao.getWarningLogList(warningLog,pageGridPost);
	}
	
	@Override
	public List<WarningLog> getProcessedWarningLogList(WarningLog warningLog,
			PageGridPost pageGridPost) {
		return warningLogDao.getProcessedWarningLogList(warningLog,pageGridPost);
	}

	@Override
	public List<WarningLog> getWarningLogList(WarningLog warningLog) {
		return warningLogDao.getWarningLogList(warningLog);
	}

	@Override
	public WarningLog getWarningLog(String id, String type) {
		return warningLogDao.getWarningLog(id, type);
	}

	@Override
	public int getWarningLogCount(WarningLog warningLog) {
		return warningLogDao.getWarningLogCount(warningLog);
	}
	
	@Override
	public List<WarningLog> getWarningLogDayList(WarningLog warningLog,PageGridPost pageGridPost) {
		return warningLogDao.getWarningLogDayList(warningLog,pageGridPost);
	}

	@Override
	public int[] getServerCount(String year) {
		WarningLog log = new WarningLog();
		int[] serverCount = new int[12]; 
		for (int i = 1; i < 13; i++) {
			String month = i > 9 ? i+"" : "0"+i;
			String warnTime = year+"-"+month;
			log.setWarnTime(warnTime);
			log.setMonitorType(Constant.DICT_SERVER);
			log.setStatus(Constant.EXCEPTION_STATUS);
			int count =this.getWarningLogCount(log);
			serverCount[i-1]=count;
		}
		return serverCount;
	}

	@Override
	public int[] getDatabaseCount(String year) {
		WarningLog log = new WarningLog();
		int[] databaseCount = new int[12]; 
		//服务器
		for (int i = 1; i < 13; i++) {
			String month = i > 9 ? i+"" : "0"+i;
			String warnTime = year+"-"+month;
			log.setWarnTime(warnTime);
			log.setMonitorType(Constant.DICT_DATABASE);
			log.setStatus(Constant.EXCEPTION_STATUS);
			int count =this.getWarningLogCount(log);
			databaseCount[i-1]=count;
		}
		return databaseCount;
	}

	@Override
	public int[] getMiddlewareCount(String year) {
		WarningLog log = new WarningLog();
		int[] middlewareCount = new int[12]; 
		//服务器
		for (int i = 1; i < 13; i++) {
			String month = i > 9 ? i+"" : "0"+i;
			String warnTime = year+"-"+month;
			log.setWarnTime(warnTime);
			log.setMonitorType(Constant.DICT_MIDDLEWARE);
			log.setStatus(Constant.EXCEPTION_STATUS);
			int count =this.getWarningLogCount(log);
			middlewareCount[i-1]=count;
		}
		return middlewareCount;
	}

	@Override
	public int[] getSystemCount(String year) {
		WarningLog log = new WarningLog();
		int[] middlewareCount = new int[12]; 
		//服务器
		for (int i = 1; i < 13; i++) {
			String month = i > 9 ? i+"" : "0"+i;
			String warnTime = year+"-"+month;
			log.setWarnTime(warnTime);
			log.setMonitorType(Constant.DICT_APP_SYSTEM);
			log.setStatus(Constant.EXCEPTION_STATUS);
			int count =this.getWarningLogCount(log);
			middlewareCount[i-1]=count;
		}
		return middlewareCount;
	}

}
