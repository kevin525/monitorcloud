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
	
function closeLayer(isrefresh){
	if(isrefresh){
		parent.window.iframe.init();
	}
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index)
	
}
var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

var zNodes =[
	{id:1, pId:0, name:"全部", open:true},
	{id:2, pId:1, name:"应用",open:false},
	{id:102, pId:2, name:"待办桌面"},
	{id:103, pId:2, name:"党政信息"},
	{id:104, pId:2, name:"门户"},
	{id:105, pId:2, name:"统一用户"},

	{id:3, pId:1, name:"服务器", open:false},
	{id:201, pId:3, name:"服务器66"},
	{id:206, pId:3, name:"服务器37"},
	{id:207, pId:3, name:"服务器13"},
];

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);

});

</script>
</head>
<body>
		<div id="step_one">
			<table class="table table-bordered table-hover m10">
				<tr align="center">
					<td class="tableleft" align="center" height="25">告警名称</td>
					<td><input type="text" name="" id="" /></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25">告警范围</td>
					<td>
						<DIV id="tree">
							<ul id="treeDemo" class="ztree"></ul>
						</DIV>
					</td>
				</tr>
				<tr>
					<td class="tableleft" align="center" height="25">告警描述</td>
					<td><textarea name="" id="" /></textarea>
						 </td>
				</tr>
			</table>
			<div style="text-align:center;">
			   <button class="btn btn-primary" style="width:200px;height:40px;" type="button" onclick="oneNext('step_two','step_one')">下一步</button>
						&nbsp;&nbsp;
			   <button type="reset" class="btn btn-success" style="width:200px;height:40px;" name="backid" onclick="closeLayer(false)" id="backid" >取消</button>
			</div>
		</div>
		<div id="step_two"  style="display:none;">
			<table class="table table-bordered table-hover m10">
				<tr align="center">
					<td class="tableleft" align="center" height="25">事件</td>
					<td><input type="checkbox" name="" id="" />异常</td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25">告警条件</td>
					<td>
						<input type="radio"/>当事件连续发生 2次时，发送告警，再每 3次发送一次告警<br/>
						<input type="radio"/>在 60分钟内，有 3次同样状态事件发送告警
					</td>
				</tr>
				<tr>
					<td class="tableleft" align="center" height="25">个性化设置</td>
					<td>
						<input type="checkbox" name="" id="" />事件连续发生 0次后，停止发送告警<br/>
						<input type="checkbox" name="" id="" />当发过告警监测点恢复正常时发送一次告警
						 </td>
				</tr>
			</table>
			<div style="text-align:center;">
			   	<button class="btn btn-primary" style="width:200px;height:40px;" type="button" onclick="tabDiv('step_one','step_two')">上一步</button>
						&nbsp;&nbsp;
			  	<button class="btn btn-primary" style="width:200px;height:40px;" type="button" onclick="twoNext('step_three','step_two')">下一步</button>
						&nbsp;&nbsp;
			  	<button type="reset" class="btn btn-success" style="width:200px;height:40px;" name="backid" onclick="closeLayer(false)" id="backid" >取消</button>
			</div>
		</div>
		<div id="step_three" style="display:none;">
			<table class="table table-bordered table-hover m10">
				<tr align="center">
					<td class="tableleft" align="center" height="25">告警方式</td>
					<td><input type="checkbox" name="" id="" />邮件
						<input type="checkbox" name="" id="" />短消息
						<input type="checkbox" name="" id="" />声音 
						<input type="checkbox" name="" id="" />脚本 
						<input type="checkbox" name="" id="" />App
					</td>
				</tr>
				
			</table>
			<div style="text-align:center;">
			   	<button class="btn btn-primary" style="width:200px;height:40px;" type="button" onclick="tabDiv('step_two','step_three')">上一步</button>
						&nbsp;&nbsp;
				<button class="btn btn-primary" style="width:200px;height:40px;" type="button" onclick="submit()">完成</button>
						&nbsp;&nbsp;
			   	<button type="reset" class="btn btn-success" style="width:200px;height:40px;" name="backid" onclick="closeLayer(false)" id="backid" >取消</button>
			</div>
		</div>
		<div style="text-align:center;">
		  <p id="moblieprompt" style="font-size: 12px;color:red;"></p> 
		</div>
		
</body>
</html>