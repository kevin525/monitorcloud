package com.sys.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2015年10月20日上午11:40:42
 *类说明：生成足够随机数的Token,来防止CSRF攻击（需要在用户会话期间使用的场景）
 */
public final  class CSRFTokenManager {
	
	/**
     * The token parameter name
     */
    static final String CSRF_PARAM_NAME = "CSRFToken";

    /**
     * The location on the session which stores the token
     */
    public static final  String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class
            .getName() + ".tokenval";

    public static String getTokenForSession(HttpSession session) {
        String token = null;
        
        // I cannot allow more than one token on a session - in the case of two
        // requests trying to
        // init the token concurrently
       synchronized (session) {
            token = (String) session
                    .getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
            if (null == token) {
                token = UUID.randomUUID().toString();
                session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
            }
        }
        return token;
    }

    /**
     * Extracts the token value from the session
     * 
     * @param request
     * @return
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        return request.getParameter(CSRF_PARAM_NAME);
    }

}
