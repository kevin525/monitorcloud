<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/back_layout.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript">
$(function(){
	$("#oldPwd").blur(function(){
		var new1 = $("#oldPwd").val();
        if(new1 == ""){
      	  $("#oldPwdSpan").text("原密码不能为空!");
      	  return false;
        }else{
          $("#oldPwdSpan").text("");
          return true;
        }
   });
	  $("#newPwd").blur(function(){
		  var new2 = $("#newPwd").val();
        if(new2 == ""){
      	  $("#newPwdSpan").text("新密码不能为空!");
      	  return false;
        }else{
        	$("#newPwdSpan").text("");
        	return true;
        }
       
   });
	$("#newPwd2").blur(function(){
		 var new2 = $("#newPwd").val();
		 var new3 = $("#newPwd2").val();
        if(new3 == ""){
      	  $("#newPwd2Span").text("确认密码不能为空!");
      	  return false;
        }else{
        	$("#newPwd2Span").text("");
        }
        if(new2!=new3){
			$("#newPwd2Span").text("密码不一致");
			$("#newPwd2").val("");
			return false;
		 }else{
			 $("#newPwd2Span").text("");
		 }
       return true;
   });
});
function check(){
	var new1 = $("#oldPwd").val();
	var new2 = $("#newPwd").val();
	var new3 = $("#newPwd2").val();
	if(new1 == ""){
    	$("#oldPwdSpan").text("原密码不能为空!");
    	return false;
    }else{
    	$("#oldPwdSpan").text("");
    }
	if(new2 == ""){
    	$("#newPwdSpan").text("新密码不能为空!");
    	return false;
    }else{
    	$("#newPwdSpan").text("");
    }
	if(new3 == ""){
    	 $("#newPwd2Span").text("确认密码不能为空!");
    	 return false;
    }else{
    	$("#newPwd2Span").text("");
    }
	if(new1 != "" && new2 != "" && new3 !=""){
		return true;
	}
	 
}

function submit(){
	if(check()){
		$.ajax({
	        type: "POST",
	        url:Config.ROOT+"/user/modifyUserPwd.do",
	        data:{
				'newPwd' : $("#newPwd").val(), 
				'oldPwd': $("#oldPwd").val()
			},
	        success: function(data) {
	        	if(data.success){
	        		parent.layer.msg(data.data, {icon: 1,time: 500});
	        		closeLayer(true);
	        	}else{
	        		parent.layer.msg(data.data, {icon: 5,time: 500});
	        	}
	        }
	    });
	}
	
}
		
function closeLayer(isrefresh){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index)
}
</script>
</head>

<body>
<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;width:100px;" align="center"><span style="color:red;">*</span>原密码
			</td>
			<td>
			  <input type="password" style="width:240px;"  placeholder="请输入原密码" id="oldPwd" onblur=""/>
			  <span id="oldPwdSpan" style="color:red;"></span>
			  </td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>新密码</td>
			<td>
			 <input type="password"  style="width:240px;" placeholder="请输入新密码"  id="newPwd" />
			 <span id="newPwdSpan" style="color:red;"></span>
			 </td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>确认密码</td>
			<td>
			   <input type="password" style="width:240px;" placeholder="请再次输入密码"  id="newPwd2"  />
			   <span id="newPwd2Span" style="color:red;"></span>
			   </td>
		</tr>
		
		
		<tr>
			<td colspan="2">
			    <button class="layui-btn layui-btn-normal" onclick="submit()">提交</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>
</body>
</html>
