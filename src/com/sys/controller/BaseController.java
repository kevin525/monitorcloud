package com.sys.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Title:所有页面控制类的 基类，可以简化时则继承它
 * Description:封装所有的日志、用户的审计信息、会话操作、对象转换
 * @author 作者  李涛
 * @version 1.0
 * 创建时间：2014年10月25日下午4:43:01
 */
public  abstract class BaseController extends MultiActionController {
	/**
	 * 把信息放入session.
	 * 
	 * @param request
	 *            the request
	 * @param attributeKey
	 *            the attribute key
	 * @param obj
	 *            the obj
	 */
	public void putSession(final HttpServletRequest request,
			final String attributeKey, final Object obj) {
		request.getSession().setAttribute(attributeKey, obj);
	}

	/**
	 * 从session中取得信息.
	 * 
	 * @param request
	 *            the request
	 * @param attributeKey
	 *            the attribute key
	 * @return the session
	 */
	public Object getSession(final HttpServletRequest request,
			final String attributeKey) {
		return request.getSession().getAttribute(attributeKey);
	}

	/**
	 * 从request或session中移除对象
	 * 
	 * @param request
	 * @param key
	 * @author litao
	 * @since 2014-10-25
	 */
	public void removeElementFromSession(HttpServletRequest request, String key) {
		request.removeAttribute(key);
		request.getSession().removeAttribute(key);
	}
	/**
	 * 清空Session
	 * @param request
	 * @author 李涛
	 * @since 2014-10-25
	 */
	public void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			session.removeAttribute(attributeNames.nextElement());
		}
	}
}
