package com.common.context;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2015年3月3日下午5:39:11
 *类说明：
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationContextUtil implements ApplicationContextAware {
	/** 
	* 以静态变量保存ApplicationContext,可在任意代码中取出ApplicaitonContext. 
	*/ 
	private static ApplicationContext context;

	/** 
	* 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量. 
	*/  
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ApplicationContextUtil.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static Object getBean(Class<?> clazz) {
		return context.getBean(clazz);
	}

	public static boolean containsBean(HttpServletRequest request,
			String beanName) {
		WebApplicationContext wct = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		return wct.containsBean(beanName);
	}

	public static boolean containsBean(String beanName) {
		if (context == null)
			throw new RuntimeException("ApplicationContext对象未实例化.");
		return context.containsBean(beanName);
	}
}