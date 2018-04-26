<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加系统</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/jsp/daily/systemState.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script language="javascript">
var params ={};
function addSystem(){
	if(check()){
		 $.ajax({
			type : 'post',
			url : Config.ROOT+"/appSystem/saveAppSystem.do",
			data : params,
			success : function(con) {
				if(con.data=="ok"){
					parent.layer.msg('添加成功', {icon: 1,time: 500});
					closeLayer(true);
				}else if(con.data=="noLogin"){
					parent.layer.msg('您还未登录，请登录', {icon: 5,time: 500});
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
	params.appName=$("#appName").val();
	params.shortName=$("#shortName").val();
	params.appIp=$("#appIp").val();
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
	/* params.dataBaseName=$("#dataBaseName").val();
	params.dataBaseUser=$("#dataBaseUser").val();
	params.dataBasePwd=$("#dataBasePwd").val();
	params.dataBaseIns=$("#dataBaseIns").val(); */
	params.dataBaseIp=$("#dataBaseIp").val();
	params.appOrder=$("#appOrder").val();
	params.isUse=$("input[name=isUse]:checked").val();
	params.environment=$("input[name=environment]:checked").val();
	params.appNetStatus="0";
	params.dataBaseNetStatus="0";
	params.appStatus="0";
	params.url=$("#url").val();
	 if(!params.appName){
		$(".span").text("*应用名称不能为空");
		canSubmit=false;
	}else if(!params.shortName){
		$(".span").text("*应用短名不能为空");
		canSubmit=false;
	}else if(!params.appIp){
		$(".span").text("*应用所在的ip地址不能为空");
		canSubmit=false;
	}else if(!params.dataBaseType){
		$(".span").text("*数据库类别不能为空");
			canSubmit=false;
	}else if(!params.dataBaseIp){
		$(".span").text("*数据库所在ip地址不能为空");
		canSubmit=false;
	}else if(!params.url){
		$(".span").text("*应用系统监测访问地址不能为空");
		canSubmit=false;
	}else if(!params.appOrder){
		$(".span").text("*排序不能为空");
		canSubmit=false;
	}else if(!(/^[0-9]+$/).test(params.appOrder)){
		$(".span").text("*排序只能为数字");
		canSubmit=false;
	}else if(!(/^[a-zA-Z0-9_]+$/).test(params.dataBaseName)){
		$(".span").text("*数据库名称只能为数字、字母、下划线");
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

function openMonitorURL(){
	var url = $("#url").val();
	if(url == ""){
		$(".span").text("*监测访问地址为空，不能访问！");
	}else{
		window.open(url);
	}
}
</script>
</head>
<body>
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft"   style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>应用名称
			</td>
			<td><input type="text" style="" name="appName" id="appName" /></td>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>应用短名</span></td>
			<td><input type="text" style="" name="shortName" id="shortName" /></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>应用所在的ip地址</td>
			<td colspan="3"><input type="text" style="" name="appIp" id="appIp"   /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库库类别</span></td>
			<td colspan="3"><input type="radio" name="dataBaseType"  id="dataBaseType" value="oracle" onclick="typeClick('oracle');"/>oracle
			&nbsp;&nbsp;<input type="radio" name="dataBaseType"  id="dataBaseType" value="mysql"  onclick="typeClick('mysql');"checked="checked"/>mysql
			&nbsp;&nbsp;<input type="radio" name="dataBaseType"  id="dataBaseType" value="sqlite"  onclick="typeClick('sqlite');"/>sqlite</td>
			
		</tr>
		<tr align="center"   class="databaseName_tr">
			<td class="tableleft" style="height:30px;line-height:30px;"  align="center"><span style="color:red;">*</span>数据库名称</span></td>
			<td colspan="3"><input type="text" style="" name="dataBaseName" id="dataBaseName" /></td>
		</tr>
		<tr align="center"   class="databaseIns_tr" style="display:none;">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库实例</span></td>
			<td colspan="3"><input type="text" style="" name="dataBaseIns" id="dataBaseIns" /></td>
		</tr>
		<tr align="center" class="database_tr">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库用户名</span></td>
			<td><input type="text" style="" name="dataBaseUser" id="dataBaseUser" /></td>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库密码</span></td>
			<td><input type="text" style="" name="dataBasePwd" id="dataBasePwd" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>数据库所在ip地址</span></td>
			<td><input type="text" style="" name="dataBaseIp" id="dataBaseIp"  /></td>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>排序</td>
			<td><input type="text" style="" name="appOrder" id="appOrder" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>监测访问地址</td>
			<td  colspan="3"><input type="text" style="width:350px;" name="url" id="url" /></td>
		</tr>
		<tr>
			<td class="tableleft" style="height:30px;line-height:30px;"  align="center"><span style="color:red;">*</span>是否启用</td>
			<td><input type="radio" name="isUse"  id="isUse" value="0"/>启用
			&nbsp;&nbsp;<input type="radio" name="isUse"  id="isUse" value="1" checked="checked"/>禁用</td>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>运行环境</td>
			<td><input type="radio" name="environment"  id="environment" value="0"/>正式环境
			&nbsp;&nbsp;<input type="radio" name="environment"  id="environment" value="1" checked="checked"/>测试环境</td>
		</tr>

		<tr >
			<td colspan="4">
			    <button class="layui-btn layui-btn-normal" onclick="addSystem()">添加</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<button class="layui-btn layui-btn-danger" onclick="openMonitorURL()">监测地址访问测试</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>