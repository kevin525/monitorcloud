package com.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.LoginLogDao;
import com.sys.domain.model.LoginLog;
import com.sys.domain.view.LoginLogView;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年11月23日下午12:05:11
 *类说明：
 */
@Repository(value="/loginLogDao")
public class LoginLogDaoImpl extends BaseDaoImpl implements LoginLogDao{
	
	/**
	 * 保存登录日志
	 * @param loginLog
	 */
	public void saveOrUpdLoginLog(LoginLog loginLog){
		 saveOrUpdate(loginLog);
	}
	
	/**
	 * 删除登录日志
	 * @param loginLogId
	 */
	public void deletLoginLog(long loginLogId){
		delete(LoginLog.class,loginLogId);
	}
	
	/**
	 * 获得用户的登录日志,分页
	 * @param logView
	 * @param pageGridPost
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LoginLog>  getPageloginLogData(LoginLogView logView,PageGridPost pageGridPost){
		StringBuffer hql=new StringBuffer("from LoginLog log where 1=1 ");
		if(logView!=null){
			if(logView.getDeptId()!=0){
				hql.append(" and log.deptId="+ logView.getDeptId());
			}
			if(!StringHelpers.isNull(logView.getLoginName())){
				hql.append(" and log.loginName like '%"+logView.getLoginName()+"%'");
			}
			if(!StringHelpers.isNull(logView.getPersonName())){
				hql.append(" and log.personName like '%"+logView.getPersonName()+"%'");
			}
			
			if(!StringHelpers.isNull(logView.getLoginIp())){
				hql.append(" and log.loginIp like '%"+logView.getLoginIp()+"%'");
			}
			
			if(!StringHelpers.isNull(logView.getBeginTime())){
				hql.append(" and log.loginDate>='"+logView.getBeginTime()+" 00:00:00'");
			}
			
			if(!StringHelpers.isNull(logView.getEndTime())){
				hql.append(" and log.loginDate<='"+logView.getEndTime()+" 23:59:59'");
			}
		}
		hql.append(" order by log.logCreateTime desc,log.loginLogId desc ");
		List<LoginLog> listLoginLogs=(List<LoginLog>) queryByPage(hql.toString(),pageGridPost);
		if(listLoginLogs!=null && listLoginLogs.size()>0){
			return listLoginLogs;
		}
		return null;
		
	}
	
	/**
	 * 获得某用户最新的登录记录日志
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  LoginLog getNewLogByUserId(long userId){
		StringBuffer hql=new StringBuffer("from LoginLog log where log.userId="+userId+" order by log.loginDate desc");
		List<LoginLog> list=query(hql.toString());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	

}
