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
<script type="text/javascript" src="${configRoot}/jsp/daily/database/database.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script language="javascript">

var params ={};
function addDatabase(){
	if(check()){
		 $.ajax({
			type : 'post',
			url : Config.ROOT+"/database/saveDatabase.do",
			data : params,
			success : function(con) {
				if(con.data=="ok"){
					parent.layer.msg("添加成功", {icon: 1,time: 500});
					closeLayer(true);
				}else if(con.data=="noLogin"){
					parent.layer.msg("您还未登录，请登录！", {icon: 1,time: 500});
					top.window.location.href=Config.ROOT+"/login.jsp";
				}else{
					$(".span").text("服务器出错");
				}
			}
		}); 
	}
}

function check(){
	var canSubmit=true;
	params.name=$("#name").val();
	params.dataBaseType=$("input[name=dataBaseType]:checked").val();
	if(params.dataBaseType=="oracle"){
		params.dataBaseIns=$("#dataBaseIns").val();
		params.dataBaseUser=$("#dataBaseUser").val();
		params.dataBasePwd=$("#dataBasePwd").val();
		if(!params.dataBaseIns){
			$(".span").text("*数据库实例不能为空");
			canSubmit=false;
		}else if(!params.dataBaseUser){
			$(".span").text("*数据库用户名不能为空");
			canSubmit=false;
		}else if(!(/^[a-zA-Z0-9_]+$/).test(params.dataBaseIns)){
			$(".span").text("*数据库实例只能为数字、字母、下划线");
			canSubmit=false;
		}else if(!params.dataBasePwd){
			$(".span").text("*数据库密码不能为空");
			canSubmit=false;
		}
	}else if(params.dataBaseType=="mysql"){
		params.dataBaseName=$("#dataBaseName").val();
		params.dataBaseUser=$("#dataBaseUser").val();
		params.dataBasePwd=$("#dataBasePwd").val();
		
		if(!params.dataBaseName){
			$(".span").text("*数据库名称不能为空");
			canSubmit=false;
		}else if(!params.dataBaseUser){
			$(".span").text("*数据库用户名不能为空");
			canSubmit=false;
		}else if(!(/^[a-zA-Z0-9_]+$/).test(params.dataBaseName)){
			$(".span").text("*数据库名称只能为数字、字母、下划线");
			canSubmit=false;
		}else if(!params.dataBasePwd){
			$(".span").text("*数据库密码不能为空");
			canSubmit=false;
		}
		
	}else{
		params.dataBaseName=$("#dataBaseName").val();
		if(!params.dataBaseName){
			$(".span").text("*数据库名称不能为空");
			canSubmit=false;
		}else if(!(/^[a-zA-Z0-9_]+$/).test(params.dataBaseName)){
			$(".span").text("*数据库名称只能为数字、字母、下划线");
			canSubmit=false;
		}
	}
	params.dataBaseIp=$("#dataBaseIp").val();
	params.appOrder=$("#appOrder").val();
	params.isUse=$("input[name=isUse]:checked").val();
	params.environment=$("input[name=environment]:checked").val();
	params.dataBaseNetStatus="0";
	 if(!params.name){
		$(".span").text("*名称不能为空");
		canSubmit=false;
	}else if(!params.dataBaseType){
		$(".span").text("*数据库类别不能为空");
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
function typeClick(type){
	if(type=="oracle"){
		$(".databaseName_tr").hide();
		$(".databaseIns_tr").show();
		$(".database_tr").show();
	}else if(type == "mysql"){
		$(".databaseName_tr").show();
		$(".databaseIns_tr").hide();
		$(".database_tr").show();
	}else{
		$(".databaseName_tr").show();
		$(".databaseIns_tr").hide();
		$(".database_tr").hide();
	}
}
function testConnect(){
	var testParam={};
	testParam.dataBaseType=$("input[name=dataBaseType]:checked").val();
	testParam.dataBaseIns=$("#dataBaseIns").val();
	testParam.dataBaseName=$("#dataBaseName").val();
	testParam.dataBaseUser=$("#dataBaseUser").val();
	testParam.dataBasePwd=$("#dataBasePwd").val();
	testParam.dataBaseIp=$("#dataBaseIp").val();
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/database/testDataBaseConn.do",
			data : testParam,
			success : function(con) {
				if(con.data){
					alert("连接成功");
				}else {
					alert("连接失败");
				}
			}
		}); 
}
</script>
</head>
<body>
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>名称</td>
			<td><input type="text" style="width:350px;" name="name" id="name" /></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库类别</span></td>
			<td><input type="radio" name="dataBaseType"  id="dataBaseType" value="oracle" onclick="typeClick('oracle');"/>oracle
			&nbsp;&nbsp;<input type="radio" name="dataBaseType"  id="dataBaseType" value="mysql" checked="checked" onclick="typeClick('mysql');"/>mysql
				&nbsp;&nbsp;<input type="radio" name="dataBaseType"  id="dataBaseType" value="sqlite"  onclick="typeClick('sqlite');"/>sqlite</td>
		</tr>
		<tr align="center" class="databaseName_tr">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库名称</span></td>
			<td><input type="text" style="width:350px;" name="dataBaseName" id="dataBaseName" /></td>
		</tr>
		<tr align="center" class="databaseIns_tr" style="display:none;">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库实例</span></td>
			<td><input type="text" style="width:350px;" name="dataBaseIns" id="dataBaseIns" /></td>
		</tr>
		<tr align="center"  class="database_tr">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库用户名</span></td>
			<td><input type="text" style="width:350px;" name="dataBaseUser" id="dataBaseUser" /></td>
		</tr>
		<tr align="center"  class="database_tr">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库密码</span></td>
			<td><input type="text" style="width:350px;" name="dataBasePwd" id="dataBasePwd" /></td>
		</tr>
	
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库所在ip地址</span></td>
			<td><input type="text" style="width:350px;" name="dataBaseIp" id="dataBaseIp" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>排序</td>
			<td><input type="text" style="width:350px;" name="appOrder" id="appOrder" /></td>
		</tr>
		<tr>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>是否启用</td>
			<td><input type="radio" name="isUse"  id="isUse" value="0"/>启用
			&nbsp;&nbsp;<input type="radio" name="isUse"  id="isUse" value="1" checked="checked"/>禁用</td>
		</tr>
		<tr>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>运行环境</td>
			<td><input type="radio" name="environment"  id="environment" value="0"/>正式环境
			&nbsp;&nbsp;<input type="radio" name="environment"  id="environment" value="1" checked="checked"/>测试环境</td>
		</tr>
		<tr >
			<td colspan="2">
			    <button class="layui-btn layui-btn-normal" onclick="addDatabase()">添加</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<button class="layui-btn layui-btn-danger" onclick="testConnect()">连接测试</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>