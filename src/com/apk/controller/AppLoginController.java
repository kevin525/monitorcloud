package com.apk.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apk.model.ReturnData;
import com.common.StringHelpers;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.service.impl.LoginServiceImpl;

@Controller
@RequestMapping("/appLogin")
public class AppLoginController {

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@Autowired
	private BaseService baseService;

	
	@RequestMapping(value ="/login.do",method = RequestMethod.POST)
	@ResponseBody
	public ReturnData loginAppUser(HttpServletRequest request, User s) {
		User loginUser = loginServiceImpl.UserLogin(s);
		if(loginUser != null){
			if(loginUser.getPerson().getPersonState()==0 ||loginUser.getPerson().getPersonState()==2){
				return	new ReturnData(0, "error");
			}else{
				request.getSession().setAttribute("loginUser", loginUser);
				return	new ReturnData(1, "success",loginUser);
			}
			
		}else{
			return new ReturnData(0, "error");
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
