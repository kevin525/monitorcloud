<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/daily/serverMonitoring.js"></script>

<script type="text/javascript"
	src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
	<style>
	.check_span{float:right;}
	</style>
<script language="javascript">

var params ={};
function addSystem(){
		 if(check()){
			 $.ajax({
				type : 'post',
				url : "${pageContext.request.contextPath}/server/saveServer.do",
				data : params,
				success : function(con) {
					if(con.data=="ok"){
						closeLayer(true);
					}else if(con.data=="noLogin"){
						parent.layer.msg('您还未登录，请登录', {icon: 5,time: 500});
						top.window.location.href="${pageContext.request.contextPath}/login.jsp";
					}else{
						$(".span").text("服务器出错");
					}
				}
			});
	}
}

function iptest(){
	var ip = $("#ip").val();
	var str=ip.replace(/(^\s*)|(\s*$)/g,"");
	if (str!="") {
		$.ajax({
			type : 'post',
			url : "${pageContext.request.contextPath}/server/testServerConn.do",
			data : {
				ip : $("#ip").val()
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

function check(){
	var canSubmit=true;
	params.name=$("#name").val();
	params.ip=$("#ip").val();
	params.model=$("#model").val();
	params.cpu=$("#cpu").val();
	params.memorySize=$("#memorySize").val();
	params.hardDiskSize=$("#hardDiskSize").val();
	params.availableHDSzie=$("#availableHDSzie").val();
	params.osName=$("#osName").val();
	params.personLiable=$("#personLiable").val();
	params.serverOrder=$("#serverOrder").val();
	params.environment=$("input[name=environment]:checked").val();
	params.status="0";
	params.isUse=$("input[name=isUse]:checked").val();
	 if(!params.name){
		$(".span").text("*服务器名称不能为空");
		canSubmit=false;
	}else if(!params.ip){
		$(".span").text("*服务器ip不能为空");
		canSubmit=false;
	}else if(!params.personLiable){
		$(".span").text("*责任人不能为空");
		canSubmit=false;
	}else if(!params.serverOrder){
		$(".span").text("*排序不能为空");
		canSubmit=false;
	}else if(!(/^[0-9]+$/).test(params.serverOrder)){
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
</script>
</head>
<body>
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;width:15%;" align="center"><span style="color: red">*</span>服务器名称</td>
			<td width="35%"><input type="text" name="name" id="name" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;width:15%;" align="center"><span style="color: red">*</span>服务器ip</td>
			<td width="35%"><input type="text" name="ip" id="ip" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">服务器型号</td>
			<td><input type="text" name="model" id="model" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">服务器cpu</td>
			<td><input type="text" name="cpu" id="cpu" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">内存大小(G)</td>
			<td><input type="text" name="memorySize" id="memorySize" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">硬盘大小(G)</td>
			<td><input type="text" name="hardDiskSize" id="hardDiskSize" /></td>
		</tr>	
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">硬盘可用大小(G)</td>
			<td><input type="text" name="availableHDSzie" id="availableHDSzie" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">操作系统名称</td>
			<td><input type="text" name="osName" id="osName" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color: red">*</span>责任人</td>
			<td><input type="text" name="personLiable" id="personLiable" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color: red">*</span>排序</td>
			<td><input type="text" name="serverOrder" id="serverOrder" /></td>
		</tr>
		<tr>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">运行环境</td>
			<td><input type="radio" name="environment"  id="environment" value="0" checked="checked"/>正式环境
			&nbsp;&nbsp;<input type="radio" name="environment"  id="environment" value="1" />测试环境</td>
			<td class="tableleft" style="height:30px;line-height:30px;border-bottom: 1px solid #dddddd;" align="center">是否启用</td>
			<td style="border-bottom: 1px solid #dddddd;"><input type="radio" name="isUse"  id="isUse" value="0"/>启用
			&nbsp;&nbsp;<input type="radio" name="isUse"  id="isUse" value="1" checked="checked"/>禁用</td>
		</tr>
		<tr>
			<td colspan="2">
			    <button class="layui-btn layui-btn-normal" onclick="addSystem()">添加</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<button class="layui-btn layui-btn-danger" onclick="iptest()">ip连接测试</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>