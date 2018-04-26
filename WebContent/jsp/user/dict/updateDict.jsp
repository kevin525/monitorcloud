<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="../../css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="../../css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="../../css/style.css" />
<script type="text/javascript"
	src="../../js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../../jsp/daily/systemState.js"></script>


	<style>
	.check_span{float:right;}
	</style>
<script language="javascript">
var params  ={};
var id='<%=request.getParameter("id")%>';
var appNetStatus=null;
var dataBaseNetStatus=null;
var appStatus=null;
$(document).ready(function(e) {

	$.ajax({
		type : 'post',
			url : Config.ROOT+"/appSystem/getAppSystemById.do",
			data : {
				id : id
				},
			success : function(con) {
				if(null !=con.data ){
					var data = con.data;
					$("#isUse[value="+data.isUse+"]").attr("checked","checked");
					$("#environment[value="+data.environment+"]").attr("checked","checked");
					$("#dateBaseType[value="+data.dateBaseType+"]").attr("checked","checked");
					$("#appName").val(data.appName);
					$("#shortName").val(data.shortName);
					$("#appIp").val(data.appIp);
					$("#dataBaseName").val(data.dataBaseName);
					$("#dataBaseIp").val(data.dataBaseIp);
					$("#appOrder").val(data.appOrder);
					$("#appName").val(data.appName);
					appNetStatus=data.appNetStatus;
					dataBaseNetStatus=data.dataBaseNetStatus;
					appStatus=data.appStatus;
					
				}else{
					alert("数据异常");
					closeLayer(true);
				}
			}
		}); 
	
	
});
function editSystem(){
	if(check()){
		 $.ajax({
			type : 'post',
			url : "${pageContext.request.contextPath}/appSystem/saveOrUpdateAppSystem.do",
			data : params,
			success : function(con) {
				if(con.data=="ok"){
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
	params.appNetStatus=appNetStatus
	params.dataBaseNetStatus=dataBaseNetStatus;
	params.appStatus=appStatus;
	params.appName=$("#appName").val();
	params.shortName=$("#shortName").val();
	params.appIp=$("#appIp").val();
	params.dateBaseType=$("input[name=dateBaseType]:checked").val();
	params.dataBaseName=$("#dataBaseName").val();
	params.dataBaseIp=$("#dataBaseIp").val();
	params.appOrder=$("#appOrder").val();
	params.isUse=$("input[name=isUse]:checked").val();
	params.environment=$("input[name=environment]:checked").val();
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
			<td class="tableleft"   style="height:30px;line-height:30px;" align="center">应用名称
			</td>
			<td><input type="text" name="appName" id="appName" /></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">应用短名</span></td>
			<td><input type="text" name="shortName" id="shortName" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">应用所在的ip地址</td>
			<td><input type="text" name="appIp" id="appIp"   /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">数据库类别</span></td>
			<td><input type="radio" name="dateBaseType"  id="dateBaseType" value="oracle"/>oracle
			&nbsp;&nbsp;<input type="radio" name="dateBaseType"  id="dateBaseType" value="mysql" checked="checked"/>mysql</td>
			
			
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;"  align="center">数据库名称</span></td>
			<td><input type="text" name="dataBaseName" id="dataBaseName" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">数据库所在ip地址</span></td>
			<td><input type="text" name="dataBaseIp" id="dataBaseIp"  /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">排序</td>
			<td><input type="text" name="appOrder" id="appOrder" /></td>
		</tr>
		<tr>
			<td class="tableleft" style="height:30px;line-height:30px;"  align="center">是否启用</td>
			<td><input type="radio" name="isUse"  id="isUse" value="0"/>启用
			&nbsp;&nbsp;<input type="radio" name="isUse"  id="isUse" value="1" checked="checked"/>禁用</td>
		</tr>
		<tr>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">运行环境</td>
			<td><input type="radio" name="environment"  id="environment" value="0"/>正式环境
			&nbsp;&nbsp;<input type="radio" name="environment"  id="environment" value="1" checked="checked"/>测试环境</td>
		</tr>
		<tr >
			<td colspan="2">
					<button class="btn btn-primary" type="button" onclick="editSystem()">确定</button>
					&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="closeLayer(false)">取消</button>
					<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>