<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/jsp/daily/serverMonitoring.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
	<style>
	.check_span{float:right;}
	</style>
<script language="javascript">
var params ={};
var id='<%=request.getParameter("id")%>';
var status='<%=request.getParameter("status")%>';
var type='<%=request.getParameter("type")%>';
 $(function(){
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
			url : '${configRoot}'+"/server/getServerInfo.do",
			data : {id : id},
			dataType: "json",
			success : function(data) {
				$("#name").html(data.name);
				$("#model").html(data.model);
				$("#personLiable").html(data.personLiable);
				$("#cpu").html(data.cpu);
				$("#osName").html(data.osName);
				$("#ip").html(data.ip);
				$("#memorySize").html(data.memorySize);
				$("#hardDiskSize").html(data.hardDiskSize);
				$("#availableHDSzie").html(data.availableHDSzie);
				$("#serverOrder").html(data.serverOrder);
			}
	});
 });




function iptest(){
	var ip = $("#ip").html();
	var str=ip.replace(/(^\s*)|(\s*$)/g,"");
	if (str!="") {
		$.ajax({
			type : 'post',
			url : "${pageContext.request.contextPath}/server/testServerConn.do",
			data : {
				ip : $("#ip").text()
			},
			success : function(con) {
				if(con.data==true){
					parent.layer.msg('连接成功', {icon: 1,time: 500});
				}else{
					parent.layer.msg('连接失败', {icon: 5,time: 500});
				}
			}
		});
		
	} else if(str=="") {
		parent.layer.msg('请输入ip地址', {icon: 5,time: 500});
	}else {
		parent.layer.msg('服务器错误', {icon: 2,time: 500});
	}
	 
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
			<td class="tableleft" style="height:30px;line-height:30px;width:120px;" align="center">运行状态
			</td>
			<td colspan="3" style="height:30px;line-height:30px;">
			   <span id="status"></span>
			</td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">运行信息</td>
			<td colspan="3" style="height:30px;line-height:30px;">
			   <span id="info"></span>
			</td>
		</tr>
		<tr align="center" id="warnTimeTr" style="display:none;">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">预警时间</td>
			<td colspan="3" style="height:30px;line-height:30px;">
			   <span id="warnTime"></span>
			</td>
		</tr>
	</table>
	
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;width:120px;" align="center">服务器名称
			</td>
			<td  style="height:30px;line-height:30px;"><span id="name"></span></td>
			<td class="tableleft" style="height:30px;line-height:30px;width:120px;" align="center">服务器ip</td>
			<td  style="height:30px;line-height:30px;"><span id="ip"></span></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">服务器型号</td>
			<td><span id="model" style="height:30px;line-height:30px;"></span></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">服务器cpu</td>
			<td><span id="cpu" style="height:30px;line-height:30px;"></span></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">内存大小(G)</td>
			<td><span id="memorySize" style="height:30px;line-height:30px;"></span></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">硬盘大小(G)</td>
			<td><span id="hardDiskSize" style="height:30px;line-height:30px;"></span></td>
		</tr>	
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">硬盘可用大小(G)</td>
			<td><span id="availableHDSzie" style="height:30px;line-height:30px;"></span></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">操作系统名称</td>
			<td><span id="osName" style="height:30px;line-height:30px;"></span></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">责任人</td>
			<td><span id="personLiable" style="height:30px;line-height:30px;"></span></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">排序</td>
			<td style="height:30px;line-height:30px;" ><span id="serverOrder"></span></td>
		</tr>
		<tr>
			<td colspan="4">
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">关闭</button>
				 <button class="layui-btn layui-btn-danger" onclick="iptest()">ip连接测试</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>