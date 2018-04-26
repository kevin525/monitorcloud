<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加数据库</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/jsp/daily/tomcat/tomcatjs"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script language="javascript">
var params  ={};
var id='<%=request.getParameter("id")%>';
$(document).ready(function(e) {
	$.ajax({
		type : 'post',
			url : Config.ROOT+"/tomcat/getById.do",
			data : {
				id : id
				},
			success : function(con) {
				if(null !=con.data ){
					var data = con.data;
					$("#isUse[value="+data.isUse+"]").attr("checked","checked");
					$("#environment[value="+data.environment+"]").attr("checked","checked");
					$("#name").val(data.name);
					$("#webAppName").val(data.webAppName);
					var managerUrl = data.managerUrl.replace("http://","").replace("/manager","");
					var ip =managerUrl.split(":")[0];
					var port =managerUrl.split(":")[1];
					$("#ip").val(ip);
					$("#port").val(port);
					$("#managerUserName").val(data.managerUserName);
					$("#managerPassword").val(data.managerPassword);
					$("#managerRole").val(data.managerRole);
					$("#appOrder").val(data.appOrder);
					$("#selector").val(data.version);
					params.tomcatStatus=data.tomcatStatus;
				}else{
					parent.layer.msg('数据异常', {icon: 1,time: 500});
					closeLayer(true);
				}
			}
		}); 
	
	
});
function editTomcat(){
	if(check()){
		 $.ajax({
			type : 'post',
			url : Config.ROOT+"/tomcat/udateTomcat.do",
			data : params,
			success : function(con) {
				if(con.data=="ok"){
					parent.layer.msg('修改成功', {icon: 1,time: 500});
					closeLayer(true);
				}else{
					$(".span").text("服务器出错");
				}
			}
		}); 
	}
}
function check(){
	var canSubmit=true;
	params.id=id;
	params.name=$("#name").val();
	params.webAppName=$("#webAppName").val();
	params.managerUrl="http://"+$("#ip").val()+":"+$("#port").val()+"/manager";//"http://10.171.251.74:80/manager";
	params.managerUserName=$("#managerUserName").val();
	params.managerPassword=$("#managerPassword").val();
	params.managerRole=$("#managerRole").val();
	params.version=$("#selector").val();
	params.appOrder=$("#appOrder").val();
	params.isUse=$("input[name=isUse]:checked").val();
	params.environment=$("input[name=environment]:checked").val();
	 if(!params.name){
		$(".span").text("*名称不能为空");
		canSubmit=false;
	}else if(!params.webAppName){
		$(".span").text("*项目名称不能为空");
		canSubmit=false;
	}else if(!$("#ip").val()){
		$(".span").text("*ip不能为空");
		canSubmit=false;
	}else if(!$("#port").val()){
		$(".span").text("*端口号不能为空");
		canSubmit=false;
	}else if(!params.managerUserName){
		$(".span").text("*角色用户名不能为空");
		canSubmit=false;
	}else if(!params.managerPassword){
		$(".span").text("*角色密码不能为空");
		canSubmit=false;
	}else if(!params.appOrder){
		$(".span").text("*排序不能为空");
		canSubmit=false;
	}else if(!(/^[0-9]+$/).test(params.appOrder)){
		$(".span").text("*排序只能为数字");
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
function testConnect(){
	var testParam={};
	testParam.managerUrl="http://"+$("#ip").val()+":"+$("#port").val()+"/manager";
	testParam.webAppName=$("#webAppName").val();
	testParam.managerUserName=$("#managerUserName").val();
	testParam.managerPassword=$("#managerPassword").val();
	testParam.version=$("#selector").val();
	if(!testParam.webAppName){
		$(".span").text("*项目名称不能为空");
		return;
	}else if(!$("#ip").val()){
		$(".span").text("*ip不能为空");
		return;
	}else if(!$("#port").val()){
		$(".span").text("*端口号不能为空");
		return;
	}else if(!testParam.managerUserName){
		$(".span").text("*角色用户名不能为空");
		return;
	}else if(!testParam.managerPassword){
		$(".span").text("*角色密码不能为空");
		return;
	}
	  $.ajax({
			type : 'post',
			url : Config.ROOT+"/tomcat/testTomcatConn.do",
			data : testParam,
			success : function(con) {
				if(con.data){
					parent.layer.msg('连接成功', {icon: 1,time: 500});
				}else {
					parent.layer.msg('连接失败', {icon: 5,time: 500});
				}
			}
		});  
}
</script>
</head>
<body>
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>中间件名称</td>
			<td><input type="text" style="" name="name" id="name" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>部署的项目</td>
			<td><input type="text" style="" name="webAppName" id="webAppName" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>中间件所在ip</td>
			<td><input type="text" style="" name="ip" id="ip"/></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>中间件端口号</td>
			<td><input type="text" style="" name="port" id="port"/></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>角色用户名</td>
			<td><input type="text" style="" name="managerUserName" id="managerUserName" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>角色密码</td>
			<td><input type="text" style="" name="managerPassword" id="managerPassword" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>角色</td>
			<td colspan="3"><input type="text" style="width:100%;" name="managerRole" id="managerRole"/></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>中间件版本</span></td>
			<td>
			<select id="selector">
				<option>5</option>
				<option>6</option>
				<option>7</option>
				<option>8</option>
				<option>9</option>
			</select>
			</td>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>排序</td>
			<td><input type="text" style="" name="appOrder" id="appOrder" /></td>
		</tr>
	
		<tr>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>是否启用</td>
			<td><input type="radio" name="isUse"  id="isUse" value="0"/>启用
			&nbsp;&nbsp;<input type="radio" name="isUse"  id="isUse" value="1" checked="checked"/>禁用</td>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>运行环境</td>
			<td><input type="radio" name="environment"  id="environment" value="0"/>正式环境
			&nbsp;&nbsp;<input type="radio" name="environment"  id="environment" value="1" checked="checked"/>测试环境</td>
		</tr>
		<tr >
			<td colspan="4">
			    <button class="layui-btn layui-btn-normal" onclick="editTomcat()">确定</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<button class="layui-btn layui-btn-danger" onclick="testConnect()">连接测试</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>