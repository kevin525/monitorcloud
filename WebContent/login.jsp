<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<title>${systemName}</title>
<link href="${configRoot}/login/css/login.css" rel="stylesheet" type="text/css" />
<script src="${configRoot}/login/js/cloud.js" type="text/javascript"></script>
<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
}); 
	
	function doLogin(){
		   var loginName = $("#loginName").val();
		   var loginPwd = $("#loginPwd").val();
		   var authImage = $("#authImage").val();

		   if(''==$("#loginName").val()||null==$("#loginName").val()) {
			   $("#result").css("display","block");
		  	   $("#result").html("用户名不能为空!");
		  	   return false;
		  	   
			}else if(''==$("#loginPwd").val()||null==$("#loginPwd").val()) {
				$("#result").css("display","block");
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
		        	   $("#result").css("display","block");
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

<body style="background-color:#1c77ac; background-image:url(login/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>
<div class="loginbody"> <div style="font-size:28px; line-height:70px; text-align:center; color:#ffffff; margin-top:75px; font-weight:bold; letter-spacing:5px;">云智能运维监控管理平台</div>
  <div class="loginbox">
    <ul>
      <li>
        <input name="loginName"  id ="loginName" type="text" class="loginuser" placeholder="用户名"/>
      </li>
      <li>
        <input id ="loginPwd" name="loginPwd" type="password" class="loginpwd" placeholder="密码"/>
      </li>
      <li>
        <input id ="authImage" name="auth_image" type="text" class="loginyzm" style="width:180px;" placeholder="验证码"/>
        <img id="vimg" name="pageFrame" onclick="reFlashImg()" src="util/AuthCode" style="width:100px;height:40px;vertical-align: middle;">
      </li>
      <li>
        <input name="" type="button" class="loginbtn" onclick="doLogin()" value="登录"/>
        <span id="result" style="color:red;font-size:18px;display:none;float:right;margin-right:20px;"></span> 
      </li>
    </ul>
  </div>
</div>
</body>
</html>
