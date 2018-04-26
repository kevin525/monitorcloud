<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增菜单</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/daily/systemState.js"></script>
<script language="javascript">
//显示一级菜单列表
function showParentList(){
	$("#firstMenu").show();
	$.ajax({
		type : 'post',
		url : "${configRoot}/appSystem/saveOrUpdateAppSystem.do",
		data : params,
		success : function(con) {
			if(con.data=="ok"){
				closeLayer(true);
			}else if(con.data=="noLogin"){
				alert("您还未登录，请登录！");
				top.window.location.href="${configRoot}/login.jsp";
			}else{
				$(".span").text("服务器出错");
			}
		}
	}); 
}

//隐藏一级菜单列表
function hideParentList(){
	$("#firstMenu").hide();
}


var params ={};
function addSystem(){
	if(check()){
		 $.ajax({
			type : 'post',
			url : "${pageContext.request.contextPath}/appSystem/saveOrUpdateAppSystem.do",
			data : params,
			success : function(con) {
				if(con.data=="ok"){
					closeLayer(true);
				}else if(con.data=="noLogin"){
					alert("您还未登录，请登录！");
					top.window.location.href="${pageContext.request.contextPath}/login.jsp";
				}else{
					$(".span").text("服务器出错");
				}
			}
		}); 
	}
}
function check(){
	var canSubmit=true;
	params.appName=$("#appName").val();
	params.shortName=$("#shortName").val();
	params.appIp=$("#appIp").val();
	params.dateBaseType=$("input[name=dateBaseType]:checked").val();
	params.dataBaseName=$("#dataBaseName").val();
	params.dataBaseIp=$("#dataBaseIp").val();
	params.appOrder=$("#appOrder").val();
	params.isUse=$("input[name=isUse]:checked").val();
	params.environment=$("input[name=environment]:checked").val();
	params.appNetStatus="0";
	params.dataBaseNetStatus="0";
	params.appStatus="0";
	 if(!params.appName){
		$(".span").text("*应用名称不能为空");
		canSubmit=false;
	}else if(!params.shortName){
		$(".span").text("*应用短名不能为空");
		canSubmit=false;
	}else if(!params.appIp){
		$(".span").text("*应用所在的ip地址不能为空");
		canSubmit=false;
	}else if(!params.dateBaseType){
		$(".span").text("*数据库类别不能为空");
			canSubmit=false;
	}else if(!params.dataBaseName){
		$(".span").text("*数据库名称不能为空");
		canSubmit=false;
	}else if(!params.dataBaseIp){
		$(".span").text("*数据库所在ip地址不能为空");
		canSubmit=false;
	}else if(!params.appOrder){
		$(".span").text("*排序不能为空");
		canSubmit=false;
	}else if(!(/^[0-9]+$/).test(params.appOrder)){
		$(".span").text("*排序只能为数字");
		canSubmit=false;
	}else if(!(/^[a-zA-Z]+$/).test(params.dataBaseName)){
		$(".span").text("*数据库名称只能为英文字母");
		canSubmit=false;
	}else if(!(/^[a-zA-Z]+$/).test(params.shortName)){
		$(".span").text("*应用短名只能为英文字母");
		canSubmit=false;
	}
	return canSubmit;
}
function closeLayer(isrefresh){
	if(isrefresh){
		
		parent.window.iframe.init();
	}
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index)
	
}
</script>
</head>
<body>
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">菜单名称
			</td>
			<td><input type="text" style="width:400px;" name="resourcesName" id="resourcesName" /></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">菜单别名</span></td>
			<td><input type="text"  style="width:400px;" name="resoucesCode" id="resoucesCode" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">访问地址</td>
			<td><input type="text" style="width:400px;" name="resourcesUrl" id="resourcesUrl" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">菜单图标</td>
			<td><input type="text" style="width:400px;" name="resoucesImg" id="resoucesImg" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">菜单类别</span></td>
			<td><input type="radio" name="nodeValue"   value="1" checked="checked" onclick="hideParentList()"/>一级菜单
			&nbsp;&nbsp;<input type="radio" name="nodeValue"   value="2" onclick="showParentList()"/>二级菜单</td>
		</tr>
		
		<tr align="center" style="display:none" id="firstMenu">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">一级菜单</td>
			<td><input type="text" style="width:400px;" name=resourcesPid id="resourcesPid" /></td>
		</tr>
		
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">排序</td>
			<td><input type="text" style="width:400px;" name="resourcesOrderNum" id="resourcesOrderNum" /></td>
		</tr>
		<tr>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">是否启用</td>
			<td><input type="radio" name="resourcesState"  value="1"/>启用
			&nbsp;&nbsp;<input type="radio" name="resourcesState"  value="0" checked="checked"/>禁用</td>
		</tr>
		<tr>
			<td colspan="2">
					<button class="btn btn-primary" type="button" onclick="addSystem()">添加</button>
					&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="closeLayer(false)">取消</button>
					<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>