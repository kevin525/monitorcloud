<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误日志</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script language="javascript">
var params  ={};
var id='<%=request.getParameter("id")%>';
var warningType = "";
$(document).ready(function(e) {
	$.ajax({
		type : 'post',
			url : Config.ROOT+"/warningLog//getErrorLogById.do",
			data : {
				id : id
				},
			success : function(con) {
				if(null !=con.data ){
					var data = con.data;
					$("#errorTitle").html(data.errorTitle);
					$("#projectName").html(data.projectName);
					$("#serverIp").html(data.serverIp);
					$("#errorTime").html(data.errorTime);
					$("#errorName").html(data.errorName);
					$("#errorDetail").html(data.errorDetail);
				}else{
					alert("数据异常");
					closeLayer(true);
				}
			}
		}); 
	
});
function check(){
	var canSubmit=true;
	params.id=id;
	params.flag =$("input[name=flag]:checked").val();
	params.warnDesc = $('#warnDesc').val();
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
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;width:100px;">错误名称</td>
					<td><div id="errorTitle"></div></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;">服务器ip</td>
					<td><div id="serverIp"></div></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;">错误时间</td>
					<td><div id="errorTime"></div></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;">错误类型</td>
					<td><div id="errorName"></div></td>
				</tr>
				<tr align="center">
					<td class="tableleft" align="center" height="25" style="vertical-align: middle;">错误详情</td>
					<td><div id="errorDetail"></div></td>
				</tr>
				
			</table>
		</div>
		
		<div style="text-align:center;">
		  <p id="moblieprompt" style="font-size: 12px;color:red;"></p> 
		</div>


</body>
</html>