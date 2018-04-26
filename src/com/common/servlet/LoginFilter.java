package com.common.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sys.domain.model.User;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String uri = (String)request.getRequestURI();
		
		if(!uri.endsWith("login.jsp") && !uri.endsWith("login.do")){ 
			HttpSession session = request.getSession(true);  
			User loginUser = (User) session.getAttribute("loginUser");  
			if (loginUser == null) {  
				response.sendRedirect(request.getContextPath()+"/login.jsp");
	            return;
			}
		}  
		filterChain.doFilter(request, response);  

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
