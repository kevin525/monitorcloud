<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录管理</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function doLogin(){
   //window.location.href="main.jsp";
   var loginName = $("#loginName").val();
   var loginPwd = $("#loginPwd").val();
   var authImage = $("#authImage").val();

   if(''==$("#loginName").val()||null==$("#loginName").val()) {
	   $("#resultShow").css("display","block");
  	   $("#result").html("用户名不能为空!");
  	   return false;
  	   
	}else if(''==$("#loginPwd").val()||null==$("#loginPwd").val()) {
		$("#resultShow").css("display","block");
	  	$("#result").html("密码不能为空!");
	  	return false;
	}
	$.ajax({
        type: "post",
        url:Config.ROOT+"/login/login.do",
        data:{
			'loginName' : loginName,        
			'loginPwd' : loginPwd,
			'authImage':authImage
		},
        success: function(data) {
           if(data=="success"){
        	   window.location.href = Config.ROOT+"/index.jsp";
           }else {
        	   $("#resultShow").css("display","block");
        	   $("#result").html(data);
           }
        }
    });
}

function reFlashImg(){
	//document.images["pageFrame"].src = "controller/AuthCode?dumy="+Math.random();
	var imgNode = document.getElementById("vimg");                  
//重新加载验证码，达到刷新的目的  
imgNode.src = "util/AuthCode?dumy=" + Math.random();  // 防止浏览器缓存的问题   
	}
	
//回车提交事件
$(document).keyup(function(event){
  if(event.keyCode ==13){
	  doLogin();
  }
});

if (window != top) {
	top.location.href = location.href;
}

</script>
</head>

<body style="background:#efefef;">
<div class="top clearfix">
	<span class="logo">
    	<i class="icon_logo_small"></i>通州OA维护管理平台
    </span>
</div>
<div class="loginframe">
<form method="post" id="loginForm">
	<div><img src="images/logo_big.png" width="72" height="72" /></div>
    <div class="login_sysName">登录管理</div>
    <div style="margin-top:15px;"><input type="text" id ="loginName" name="loginName" class="ipt_username_login" placeholder="请输入用户名" /></div>
    <div style="margin-top:15px;"><input type="password" id ="loginPwd" name="loginPwd" class="ipt_username_pwd" placeholder="请输入密码" /></div>
    <div style="margin-top:15px;">
        <input type="text" id ="authImage" name="auth_image" size="8" class="ipt_username_pwd" style="width:155px;vertical-align: middle;" placeholder="验证码" />
        <img id="vimg" name="pageFrame" onclick="reFlashImg()" src="util/AuthCode" style="width:100px;height:40px;vertical-align: middle;">
    </div>
		 
	<div style="margin-top:15px;color:red;font-size:18px;display:none" id="resultShow"><span id="result"></span></div>
		
	
    <div style="margin-top:20px;"><a href="javascript:void(0);" class="btn_login_login" onclick="doLogin()">立即登录</a></div>
    <div style="margin-top:10px;">© 2016 航天四创</div>
	
</form>
</div>
<script type="text/javascript">
 $(document).ready(function() {
    var mTop = $(window).height() - $(".loginframe").height() - $(".top").height();
	$(".loginframe").css("margin-top",mTop/2 - 40);
}); 
</script>
</body>
</html>
