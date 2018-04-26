package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.pagetag.PageGridPost;
import com.sys.dao.LoginLogDao;
import com.sys.domain.model.LoginLog;
import com.sys.domain.view.LoginLogView;
import com.sys.service.LoginLogService;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年11月23日下午12:31:08
 *类说明：
 */
@Service(value="loginLogService")
public class LoginLogServiceImpl extends BaseServiceImpl implements LoginLogService{
	@Autowired
	private LoginLogDao loginLogDao;
	
	
	/**
	 * 保存登录日志
	 * @param loginLog
	 */
	public void saveOrUpdLoginLog(LoginLog loginLog){
		loginLogDao.saveOrUpdLoginLog(loginLog);
	}

	/**
	 * 删除登录日志
	 * @param loginLogId
	 */
	public void deletLoginLog(long loginLogId){
		loginLogDao.deletLoginLog(loginLogId);
	}
	
	/**
	 * 获得用户的登录日志,分页
	 * @param logView
	 * @param pageGridPost
	 * @return
	 */
	public List<LoginLog>  getPageloginLogData(LoginLogView logView,PageGridPost pageGridPost){
		return loginLogDao.getPageloginLogData(logView, pageGridPost);
	}
	
	/**
	 * 获得某用户最新的登录记录日志
	 * @param userId
	 * @return
	 */
	public  LoginLog getNewLogByUserId(long userId){
		return loginLogDao.getNewLogByUserId(userId);
	}

}
