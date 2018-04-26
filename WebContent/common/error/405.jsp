<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<% String loginurl = "http://" + request.getServerName() + ":"
		+ request.getServerPort() + "/" + request.getContextPath(); 
		
	response.sendRedirect(loginurl);	
		
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>405</title>
  </head>
  <body>
  </body>
</html>
