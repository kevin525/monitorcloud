package com.sys.service;

import java.util.List;

import com.common.pagetag.PageGridPost;
import com.sys.domain.model.LoginLog;
import com.sys.domain.view.LoginLogView;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年11月23日下午12:34:06
 *类说明：
 */
public interface LoginLogService {

	/**
	 * 保存登录日志
	 * @param loginLog
	 */
	public void saveOrUpdLoginLog(LoginLog loginLog);

	/**
	 * 删除登录日志
	 * @param loginLogId
	 */
	public void deletLoginLog(long loginLogId);
	
	/**
	 * 获得用户的登录日志,分页
	 * @param logView
	 * @param pageGridPost
	 * @return
	 */
	public List<LoginLog>  getPageloginLogData(LoginLogView logView,PageGridPost pageGridPost);
	
	/**
	 * 获得某用户最新的登录记录日志
	 * @param userId
	 * @return
	 */
	public  LoginLog getNewLogByUserId(long userId);

}