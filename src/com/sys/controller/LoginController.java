package com.sys.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.domain.model.Person;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.service.impl.LoginServiceImpl;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Class<Person> Person = null;

	private static final Class<User> User = null;

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@Autowired
	private BaseService baseService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public String loginUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String code = (String) session.getAttribute("rand");
		String codey = request.getParameter("authImage");
		codey = codey.toUpperCase();
		String sname = request.getParameter("loginName");
		String pwd = request.getParameter("loginPwd");
		User s = new User(sname, pwd);
		User loginUser = loginServiceImpl.UserLogin(s);
		if(!code.equals(codey)){
			return "验证码错误！";
		}
		if(loginUser != null){
			if(loginUser.getPerson().getPersonState()==0){
				return "用户已禁用！";
				
			}else if(loginUser.getPerson().getPersonState()==2){
				return "用户已删除！";
				
			}else{
				request.getSession().setAttribute("loginUser", loginUser);
				return "success";
			}
			
		}else{
			return "用户不存在或密码错误！";
		}

	}
	@RequestMapping("/loginApp.do")
	@ResponseBody
	public String loginAppUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String sname = request.getParameter("loginName");
		String pwd = request.getParameter("loginPwd");
		User s = new User(sname, pwd);
		User loginUser = loginServiceImpl.UserLogin(s);
		if(loginUser != null){
			if(loginUser.getPerson().getPersonState()==0){
				return "用户已禁用！";
				
			}else if(loginUser.getPerson().getPersonState()==2){
				return "用户已删除！";
				
			}else{
				request.getSession().setAttribute("loginUser", loginUser);
				return "success";
			}
			
		}else{
			return "用户不存在或密码错误！";
		}

	}
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping("/loginOut.do")
	@ResponseBody
	public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException{
			request.getSession().invalidate();//销毁session
//		response.sendRedirect("/login.jsp");
	}
	
}
