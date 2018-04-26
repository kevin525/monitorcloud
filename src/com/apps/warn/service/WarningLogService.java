package com.apps.warn.service;

import java.util.List;

import com.apps.warn.domain.ErrorLog;
import com.apps.warn.domain.WarningLog;
import com.common.pagetag.PageGridPost;

public interface WarningLogService {

	/**
	 * @Title: saveOrupdateAppSystem
	 * @Description: 保存或者更新告警日志信息
	 * @param warningLog
	 * @return void
	 * @throws
	 */
	public void saveOrupdateWarning(WarningLog warningLog);
	
	/**
	 * @Title: saveOrupdateError
	 * @Description: 保存错误日志
	 * @param errorLog
	 * @return void
	 * @throws
	 */
	public void saveOrupdateError(ErrorLog errorLog);
	
	/**
	 * @Title: getWarningById
	 * @Description: 根据根据id查找告警日志信息
	 * @param id
	 * @return WarningLog
	 * @throws
	 */
	public WarningLog getWarningLogById(long id);
	
	/**
	 * @Title: getWarningLog
	 * @Description: 根据id和类型获取告警日志
	 * @param id
	 * @param type
	 * @return
	 * @return WarningLog
	 * @throws
	 */
	public WarningLog getWarningLog(String id,String type);
	
	/**
	 * @Title: deleteWarningLog
	 * @Description: 删除告警日志信息
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteWarningLog(long id);
	
	/**
	 * @Title: deleteAllWarning
	 * @Description: 删除告警日志信息
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteAllWarning(List<String> ids);
	
	/**
	 * @Title: save
	 * @Description: 保存对象
	 * @param o
	 * @return void
	 * @throws
	 */
	public void save(Object o);
	
	
	/**
	 * @Title: getWarningLogList
	 * @Description: 分页展示告警日志信息
	 * @param warningLog
	 * @param pageGridPost
	 * @return List<WarningLog>
	 * @throws
	 */
	public List<WarningLog> getWarningLogList(WarningLog warningLog,PageGridPost pageGridPost);
	
	/**
	 * @Title: getWarningLogList
	 * @Description: 分页展示告警日志信息
	 * @param warningLog
	 * @return List<WarningLog>
	 * @throws
	 */
	public List<WarningLog> getWarningLogList(WarningLog warningLog);
	
	public int getWarningLogCount(WarningLog warningLog);
	
	public List<WarningLog> getWarningLogDayList(WarningLog warningLog,PageGridPost pageGridPost);
	
	public void updateWarning(WarningLog warningLog);
	
	public List<WarningLog> getProcessedWarningLogList(WarningLog warningLog,PageGridPost pageGridPost);
	
	/**
	 * @Title: getServerCount
	 * @Description: 获得服务器异常数量
	 * @param year
	 * @return int[]
	 * @throws
	 */
	public int[] getServerCount(String year);
	
	/**
	 * @Title: getDatebaseCount
	 * @Description: 获得数据库异常数量
	 * @param year
	 * @return int[]
	 * @throws
	 */
	public int[] getDatabaseCount(String year);
	
	/**
	 * @Title: getMiddlewareCount
	 * @Description: 获得中间件异常数量
	 * @param year
	 * @return int[]
	 * @throws
	 */
	public int[] getMiddlewareCount(String year);
	
	/**
	 * @Description: 获得中间件异常数量
	 * @param year
	 * @return
	 */
	public int[] getSystemCount(String year);
}
