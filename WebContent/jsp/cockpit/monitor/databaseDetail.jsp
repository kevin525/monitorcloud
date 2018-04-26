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
<script type="text/javascript" src="${configRoot}/jsp/daily/database/database.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.8.2.js"></script>
<script language="javascript">
var params  ={};
var id='<%=request.getParameter("id")%>';
var status='<%=request.getParameter("status")%>';
var type='<%=request.getParameter("type")%>';

$(document).ready(function(e) {
	if(status==0){
		 var html = "<img src='${configRoot}/images/light_normal.png' width='24' height='24' />";
		 $("#status").html(html);
		 $("#info").html("运行正常");
	 }else{
		 $.ajax({
				type : 'post',
				url : '${configRoot}'+"/warningLog/getWarningLog.do",
				data : {id : id,type:type},
				dataType: "json",
				success : function(data) {
					var html = "<img src='${configRoot}/images/light_red.png' width='24' height='24' />";
					$("#status").html(html);
					if(data.data != null && data.data != ""){
						$("#info").html(data.data.warnName);
						$("#warnTimeTr").show();
						$("#warnTime").html(data.data.warnTime);
					}else{
						$("#info").html("运行异常");
					}
				}
		});
	 }
	
	$.ajax({
		type : 'post',
			url : Config.ROOT+"/database/getById.do",
			data : {
				id : id
				},
			success : function(con) {
				if(null !=con.data ){
					var data = con.data;
					$("#name").html(data.name);
					$("#dataBaseName").html(data.dataBaseName);
					$("#dataBaseUser").html(data.dataBaseUser);
					$("#dataBaseIns").html(data.dataBaseIns);
					$("#dataBaseIp").html(data.dataBaseIp);
					$("#dataBaseType").html(data.dataBaseType);
				}else{
					parent.layer.msg('服务器错误', {icon: 2,time: 500});
					closeLayer(true);
				}
			}
		}); 
	
	
});


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
			<td class="tableleft" style="height:30px;line-height:30px;width:120px;" align="center">运行状态
			</td>
			<td  style="height:30px;line-height:30px;">
			   <span id="status"></span>
			</td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">运行信息</td>
			<td  style="height:30px;line-height:30px;">
			   <span id="info"></span>
			</td>
		</tr>
		<tr align="center" id="warnTimeTr" style="display:none;">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">预警时间</td>
			<td style="height:30px;line-height:30px;">
			   <span id="warnTime"></span>
			</td>
		</tr>
	</table>
	
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft"   style="height:30px;line-height:30px;width:120px;" align="center">名称
			</td>
			<td><span id="name"></span></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">数据库类别</td>
			<td><span id="dataBaseType"></span></td>
			
		</tr>
		<tr align="center"   class="databaseName_tr">
			<td class="tableleft" style="height:30px;line-height:30px;"  align="center">数据库名称</td>
			<td><span id="dataBaseName"></span> </td>
		</tr>
		<tr align="center"  class="databaseIns_tr">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">数据库实例</td>
			<td><span id="dataBaseIns"></span> </td>
		</tr>
		<tr align="center"  class="database_tr">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">数据库用户名</td>
			<td><span id="dataBaseUser"></span> </td>
		</tr>
		
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">数据库所在ip地址</td>
			<td><span id="dataBaseIp"></span></td>
		</tr>
		
		<tr >
			<td colspan="2">
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">关闭</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>