<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改系统</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<style>
	.check_span{float:right;}
</style>
<script language="javascript">
var params  ={};
var id='<%=request.getParameter("id")%>';
var warningType = "";
$(document).ready(function(e) {
	$.ajax({
		type : 'post',
			url : Config.ROOT+"/warningLog/getWarningLogById.do",
			data : {
				id : id
				},
			success : function(con) {
				if(null !=con.data ){
					var data = con.data;
					$("#monitorName").html(data.monitorName);
					$("#projectName").html(data.projectName);
					$("#personLiable").html(data.personLiable);
					$("#warnName").html(data.warnName);
					$("#warnTime").html(data.warnTime);
					$("#warnWay").html(data.warnWay);
					$("#warnDesc").html(data.warnDesc);
					$(":radio[name='flag'][value='" + data.flag + "']").attr("checked", "checked");
					
					
				}else{
					alert("数据异常");
					closeLayer(true);
				}
			}
		}); 
	
});
function updateLog(){
	if(check()){
		 $.ajax({
			type : 'post',
			url : Config.ROOT+"/warningLog/updateLog.do",
			data : params,
			success : function(con) {
				if(con.data=="ok"){
					parent.layer.msg("修改成功！", {icon: 1,time: 500});
					closeLayer(true);
				}else if(con.data=="noLogin"){
					alert("您还未登录，请登录！");
					top.window.location.href=Config.ROOT+"/login.jsp";
				}else if(con.data=="repeat"){
					$(".span").text("告警规则已经存在，并且在使用中，不允许重复添加！");
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
	params.monitorName = $("#monitorName").text();
	//alert("monitorName"+params.monitorName);
	params.warnName = $("#warnName").text();
	params.warnTime = $("#warnTime").text();
	params.warnWay = $("#warnWay").text();
	params.flag =$("input[name=flag]:checked").val();
	params.warnDesc = $("#warnDesc").val();
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
	<div id="step_one">
			<table class="table table-bordered table-hover m10">
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;width:100px;"><span style="color:red;">*</span>告警对象</td>
					<td><div id="monitorName"></div></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;"><span style="color:red;">*</span>告警原因</td>
					<td><div id="warnName"></div></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;"><span style="color:red;">*</span>告警时间</td>
					<td><div id="warnTime"></div></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;"><span style="color:red;">*</span>告警方式</td>
					<td><div id="warnWay"></div></td>
				</tr>
				<tr>
					<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>处理状态</td>
					<td><input type="radio" name="flag" checked value="1"/>已处理
					&nbsp;&nbsp;<input type="radio" name="flag"   value="0" />未处理</td>
				</tr>
				<tr>
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;">处理意见</td>
					<td><textarea name="warnDesc" id="warnDesc" style="width:500px;height:100px;"/></textarea></td>
				</tr>
				
				<tr >
					<td colspan="2">
					    <button class="layui-btn layui-btn-normal" onclick="updateLog()">确定</button>
					    <button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
						<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
					</td>
				</tr>
			</table>
		</div>
		
		<div style="text-align:center;">
		  <p id="moblieprompt" style="font-size: 12px;color:red;"></p> 
		</div>


</body>
</html>