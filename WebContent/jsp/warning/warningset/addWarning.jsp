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
<link rel="stylesheet" href="${configRoot}/jsp/warning/warningset/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${configRoot}/jsp/warning/warningset/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/warning/warningset/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/warning/warningset/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/warning/warningset/addWarning.js"></script>
<script language="javascript">
	
var params ={};
function addRule(){
	if(check()){
		 $.ajax({
			type : 'post',
			url : Config.ROOT+"/warning/saveOrUpdateRule.do",
			data : params,
			success : function(con) {
				if(con.data=="ok"){
					parent.layer.msg('添加成功', {icon: 1,time: 500});
					closeLayer(true);
				}else if(con.data=="noLogin"){
					parent.layer.msg('您还未登录，请登录', {icon: 5,time: 500});
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
 	params.warningName=$("#warningName").val();
 	params.warningType=$("#warningType").val();
 	if($('#minuteBox').is(':checked')) {
 		var minute = $("#minute").val();
 		if(!(/^[0-9]+$/).test(minute)){
 			$(".span").text("*分钟只能为数字");
 			return false;
 		}else{
 			params.minute = minute;
 		}
 	}
 	params.isUse=$("input[name=isUse]:checked").val();
 	if($('#countBox').is(':checked')) {
 		var count = $("#count").val();
 		if(!(/^[0-9]+$/).test(count)){
 			$(".span").text("*次数只能为数字");
 			canSubmit=false;
 		}else{
 			params.count = count;
 		}
 	}
 	if($('#noramlBox').is(':checked')) {
 		params.isNormal = 1;
 	}
 	if($('#emailBox').is(':checked')) {
 		params.isSendEmail = 1;
 	}
 	if($('#messageBox').is(':checked')) {
 		params.isSendMessage = 1;
 	}
 	if($('#informBox').is(':checked')) {
 		params.isSendInform = 1;
 	}
	
	 if(!params.warningName){
		$(".span").text("*告警规则名称不能为空");
		canSubmit=false;
	}else if(!params.warningType){
		$(".span").text("*告警类别不能为空");
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
		<div id="step_one">
			<table class="table table-bordered table-hover m10">
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;"><span style="color:red;">*</span>告警名称</td>
					<td><input type="text" name="warningName" id="warningName" style="width:500px;"/></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;"><span style="color:red;">*</span>告警类别</td>
					<td>
						<select class="common_search_ipt" name="warningType" id="warningType">
							<option value="server">服务器</option>
							<option value="system">应用系统</option>
							<option value="database">数据库</option>
							<option value="middleware">中间件</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;"><span style="color:red;">*</span>告警条件</td>
					<td>
					    <input type="checkbox" name="minuteBox" id="minuteBox"/>事件发生后，间隔 <input type="text" name="minute" id="minute" style="width:50px;"/>分钟后，再次发送告警<br/>
						<input type="checkbox" name="countBox" id="countBox"/>事件连续发生 <input type="text" name="count" id="count" style="width:50px;"/>次后，停止发送告警<br/>
						<input type="checkbox" name="noramlBox" id="noramlBox"/>当发过告警监测点恢复正常时发送一次告警
						 </td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;"><span style="color:red;">*</span>告警方式</td>
					<td><input type="checkbox" name="emailBox" id="emailBox"/>邮件&nbsp;&nbsp;
						<input type="checkbox" name="messageBox" id="messageBox"/>短信&nbsp;&nbsp;
						<input type="checkbox" name="informBox" id="informBox"/>短消息 &nbsp;&nbsp;
						<input type="checkbox" name="" id="" disabled/>脚本 &nbsp;&nbsp;
						<input type="checkbox" name="" id="" disabled/>App&nbsp;&nbsp;
						<input type="checkbox" name="" id="" disabled/>微信 
					</td>
				</tr>
				<tr>
					<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>是否启用</td>
					<td><input type="radio" name="isUse"  id="isUse" checked="checked" value="0"/>启用
					&nbsp;&nbsp;<input type="radio" name="isUse"   value="1" />禁用</td>
				</tr>
				<tr>
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;">告警描述</td>
					<td><textarea name="warningDesc" id="warningDesc" style="width:500px;"/></textarea></td>
				</tr>
				
				<tr >
					<td colspan="2">
						<button class="layui-btn layui-btn-normal" onclick="addRule()">添加</button>
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