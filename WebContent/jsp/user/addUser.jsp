<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sys.domain.view.PersonView"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加用户界面</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script language="javascript">
	//电话验证
	function check(){
		var phone= $("#mobile").val();
		if(phone== ""){
			$("#moblieprompt").html("手机号码不能为空！");
			return false;
		}
		var regexP = new RegExp("^1[0-9]{10}$");//手机号码
		if(regexP.test(phone)){// || phone == (phone.match(regexP2))){
			$("#moblieprompt").html("");
			return true;
		}else{
			$("#moblieprompt").html("手机号码格式不正确！请重新输入！");
			$("#mobile").focus();
			return false;
		}
	}
	//登录名验证
	function loginNameCheck(){
		var	 loginName =$("#loginn").val(); 
		var regexP = new RegExp("^[a-zA-Z0-9]+$");
		if(regexP.test(loginName)){// || phone == (phone.match(regexP2))){
			$("#moblieprompt").html("");
			return true;
		}else{
			$("#moblieprompt").html("登录账号只能由英文字母和数字中的一种或两种组成！");
			$("#loginn").focus();
			return false;
		}
	}
	//添加
	function submitForm() {
		
		var personId = $("#personId").val();
		var personName = $("#personName").val();
		var loginName= $("#loginn").val();
		var personTel = $("#mobile").val();
		var personState= $("input[name=personState]:checked").val();
		var sex=$("input[name=sex]:checked").val();
		var role=$("input[name=role]:checked").val();
		if(!check() || !loginNameCheck()){
			return ;
			}
		$.ajax({
			type : 'post',
			url : Config.ROOT+"/user/addUser.do",
			data : {
				personId : personId,
				personName : personName,
				loginName : loginName,
				personTel : personTel,
				personState : personState,
				sex : sex,
				role:role
			},
			success : function(con) {
				if(con=="success"){
					parent.layer.msg('添加成功', {icon: 1,time: 500});
					closeLayer(true);
				}
			}
		})
	} 
	function closeLayer(isrefresh){
		if(isrefresh){
			parent.window.iframe.init();
		}
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index)
		
	}
	$(document).ready(function(e) {
		var  oParams={};
		oParams.roleState=1;
		$.ajax({
			type : 'post',
			url : Config.ROOT+'/role/getRoleListByCriteria.do',
			data : oParams,
			success : function(con) {
				var data = con.data;
				var html = "";
				 $(data).each(function(i){
					html += "<span style='margin-right:5px;'>"+this.roleName+"</span>"+
					"<input type='radio' name='role' checked='checked' value="+this.roleId+" />&nbsp;&nbsp;&nbsp;&nbsp;";
				 });
				 $("#mbody").html(html);
			}
		});
	});

	
</script>
</head>
<body>

		<table class="table table-bordered table-hover m10">
			<tr align="center">
				<td class="tableleft" align="center" height="25">姓名</td>
				<td><input type="text" name="personName" id="personName" /></td>
			</tr>
			<tr align="center">
				<td class="tableleft" align="center" height="25">登录账号</td>
				<td><input type="text" name="loginName" id="loginn" /></td>
			</tr>
			<tr>
				<td class="tableleft" align="center" height="25">手机号</td>
				<td><input type="text" name="personTel" id="mobile"
					onblur="check()" />
					 </td>
			</tr>
			<tr>
				<td class="tableleft" align="center" height="25">角色</td>
				
				<td style="vertical-align:middle;" id="mbody">
				
				
				</td>
			</tr>
			<tr>
				<td class="tableleft" align="center" height="25">性别</td>
				
				<td style="vertical-align:middle;">
				<span style="margin-right:5px;">女</span><input type="radio" name="sex"  id="sex" value="1" checked="checked"/>
				&nbsp;&nbsp;&nbsp;&nbsp;<span style="margin-right:5px;">男</span><input type="radio" name="sex"  id="sex" value="0"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft" align="center" height="25">状态</td>
				<td>
				<span style="margin-right:5px;">启用</span><input type="radio" name="personState"  value="1" checked="checked"/>
				&nbsp;&nbsp;&nbsp;&nbsp;<span style="margin-right:5px;">禁用</span><input type="radio" name="personState"  value="0"/>
				</td>
			</tr>
			<tr >
			<td colspan="2">
			    <button class="layui-btn layui-btn-normal" onclick="submitForm()">添加</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<span id="moblieprompt" class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
		</table>
</body>
</html>