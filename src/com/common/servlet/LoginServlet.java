package com.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.assoft.cloudclient.common.ActionResultInfo;
import com.assoft.cloudclient.common.AsopSsoInfo;
import com.assoft.cloudclient.common.AsopUser;
import com.assoft.cloudclient.servlet.AsopIdsServlet;
import com.assoft.cloudclient.tool.AsopclientTool;
import com.sys.domain.model.User;
import com.sys.service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends AsopIdsServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
    
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		ServletContext servletContext = config.getServletContext();
		WebApplicationContext wc =  WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		userService = (UserService) wc.getBean("userService");
	}
	
	@Override
	public void login(AsopUser user, HttpServletRequest request)
			throws Exception {
		if(user != null && user.getLoginName() != null){
			User userInfo = userService.getUserByLoginName(user.getLoginName(), "sso");
			if(userInfo != null){
				request.getSession().setAttribute("loginUser", userInfo);
			}
		}
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String action=request.getParameter(AsopclientTool.ACTION);
		if(AsopclientTool.ACTION_SSO_LOGIN.equals(action)){
			loginHandleExt(request, response);
		}else if(AsopclientTool.ACTION_SSO_USERRECEIVE.equals(action)){
			String xmlContent=request.getParameter(AsopclientTool.INFO);
			ActionResultInfo<String> resultInfo=receiveUserSyncInfo(xmlContent);
			putAjaxUtf8Str2Resp(response, resultInfo);
		}else if(AsopclientTool.ACTION_SSO_DEPTRECEIVE.equals(action)){
			String xmlContent=request.getParameter(AsopclientTool.INFO);
			ActionResultInfo<String> resultInfo=receiveDeptSyncInfo(xmlContent);
			putAjaxUtf8Str2Resp(response, resultInfo);
		}
	}
	
	private void loginHandleExt(HttpServletRequest request,HttpServletResponse response) {
		String key=request.getParameter(AsopclientTool.KEY);
		String rmiUrl=request.getParameter(AsopclientTool.RMI_URL);
		AsopSsoInfo ssoInfo=null;
		try{
			rmiUrl=AsopclientTool.decodeBase64(rmiUrl);
			ssoInfo=AsopclientTool.getInstance().loadUserByKey(rmiUrl,key);
			AsopUser user=ssoInfo.getUser();
			login(user, request);
			User userInfo = (User)request.getSession().getAttribute("loginUser");
			if(userInfo != null){
				String url=gainServiceUrl2Redirect(ssoInfo);
				response.sendRedirect(url);
			}else{
				/*response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('登录失败!');</script>");*/
			    response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putAjaxUtf8Str2Resp(response,ActionResultInfo.buildErrorResultInfo(e.getMessage()));
		}
	}
	
	
	
	@Override
	public ActionResultInfo<String> receiveUserSyncInfo(String xmlContent) {
		return null;
	}

	@Override
	public ActionResultInfo<String> receiveDeptSyncInfo(String xmlContent) {
		return null;
	}
       
    

}
