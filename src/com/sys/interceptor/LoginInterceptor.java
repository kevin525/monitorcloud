package com.sys.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sys.domain.model.User;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年11月9日下午2:40:10
 *类说明：
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
    private static final String[] IGNORE_URI = {"/login/sysLogin.do","/login.jsp","/radomValidateCode/getRandcode.do", "/js/","/images/","/css/"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	 boolean flag = false;
    	 String url = request.getRequestURL().toString();
    	//String clientSessionId = request.getParameter("ssid");
        //String serverSessionId = request.getSession().getId();
        //if (serverSessionId.equals(clientSessionId)) {
             for (String s : IGNORE_URI) {
                 if (url.contains(s)) {
                 	if(url.contains("/login/sysLogin.do") && url.endsWith(s)){
                 		flag = true;
      	                break;
                 	}else if(url.contains("/login/sysLogin.do") && !url.endsWith(s)){
                 		
                 	}else{
     	                flag = true;
     	                break;
                 	}
                 }
             }
             if (!flag) {
             	User user=(User) request.getSession().getAttribute("user");
                 if (user != null){
                 	flag = true;
                 }else{
                 	/*flag = true;
                 	String loginurl = "http://" + request.getServerName() + ":"
             				+ request.getServerPort() + "/" + request.getContextPath();
                 	response.sendRedirect(loginurl); */
                 	/*PrintWriter out =null;
                 	 out = response.getWriter();
     				out.print("<script>window.top.location.href='" + loginurl
     						+ "'</script>");
     				out.flush();
     				out.close();*/
                	 flag = false;
                	 request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);  
                 }
             }
       /* }else{
        	flag = false;
       	 	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }*/
       
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
