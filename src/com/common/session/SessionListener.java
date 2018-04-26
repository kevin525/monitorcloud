package com.common.session;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.common.context.ApplicationContextUtil;
import com.sys.domain.model.LoginLog;
import com.sys.domain.model.User;
import com.sys.service.LoginLogService;

import jxl.common.Logger;


/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2015年3月22日下午7:13:02
 *类说明： 监听所有会话的创建和失效,供后期监控及分析用
 */
public class SessionListener implements HttpSessionListener {
	Logger logger = Logger.getLogger(SessionListener.class);
	private static final Map<String, HttpSession> context = new HashMap<String, HttpSession>();

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		context.put(e.getSession().getId(), e.getSession());
		logger.info("已创建新会话，当前会话数量："+context.size());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		User user=(User) e.getSession().getAttribute("user");
		if(user!=null){
			//更新用户登录日志
			LoginLogService loginLogService=(LoginLogService) ApplicationContextUtil.getBean("loginLogService");
			LoginLog loginLog=loginLogService.getNewLogByUserId(user.getUserId());
			loginLog.setLoginOutDate(new Date());
			loginLogService.saveOrUpdLoginLog(loginLog);
		}
		context.remove(e.getSession().getId());
		logger.info("已销毁会话，当前会话数量："+context.size());
	}
	/**
	 * 根据sessionId来获取一个已有会话
	 * @param sessionId
	 * @return
	 * @author 
	 * @since 2012-3-2
	 */
	public synchronized static HttpSession getSession(String sessionId) {
		return context.get(sessionId);
	}
}
