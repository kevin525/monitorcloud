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
<script type="text/javascript" src="${configRoot}/jsp/daily/middleware/middleware.js"></script>

	<style>
	.check_span{float:right;}
	</style>
<script language="javascript">

var params ={};
function addSystem(){
		 if(check()){
			 $.ajax({
				type : 'post',
				url : "${pageContext.request.contextPath}/middleware/saveMiddleware.do",
				data : params,
				success : function(con) {
					if(con.data=="ok"){
						closeLayer(true);
					}else if(con.data=="noLogin"){
						parent.layer.msg('您还未登录，请登录', {icon: 5,time: 500});
						top.window.location.href="${pageContext.request.contextPath}/login.jsp";
					}else{
						$(".span").text("中间件出错");
					}
				}
			});
	}
}

function iptest(){
	var ip = $("#ip").val();
	var port = $("#port").val();
	var str=ip.replace(/(^\s*)|(\s*$)/g,"");
	if (str!="") {
		$.ajax({
			type : 'post',
			url : "${pageContext.request.contextPath}/middleware/testTelnetConn.do",
			data : {
				ip : $("#ip").val(),
				port : $("#port").val()
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
		parent.layer.msg('中间件错误', {icon: 2,time: 500});
	}
}

function check(){
	var canSubmit=true;
	params.name=$("#name").val();
	params.ip=$("#ip").val();
	params.port=$("#port").val();
	params.remark=$("#remark").val();
	params.personLiable=$("#personLiable").val();
	params.middlewareOrder=$("#middlewareOrder").val();
	params.environment=$("input[name=environment]:checked").val();
	params.status="0";
	params.isUse=$("input[name=isUse]:checked").val();
	 if(!params.name){
		$(".span").text("*中间件名称不能为空");
		canSubmit=false;
	}else if(!params.ip){
		$(".span").text("*中间件ip不能为空");
		canSubmit=false;
	}else if(!params.port){
		$(".span").text("*中间件端口号不能为空");
		canSubmit=false;
	}else if(!(/^[0-9]+$/).test(params.port)){
		$(".span").text("*中间件端口号只能为数字");
		canSubmit=false;
	}else if(!params.personLiable){
		$(".span").text("*责任人不能为空");
		canSubmit=false;
	}else if(!params.middlewareOrder){
		$(".span").text("*排序不能为空");
		canSubmit=false;
	}else if(!(/^[0-9]+$/).test(params.middlewareOrder)){
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
			<td class="tableleft" style="height:30px;line-height:30px;width:15%;" align="center"><span style="color: red">*</span>中间件名称</td>
			<td width="35%"><input type="text" name="name" id="name" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;width:15%;" align="center"><span style="color: red">*</span>中间件ip</td>
			<td width="35%"><input type="text" name="ip" id="ip" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">中间件端口号</td>
			<td><input type="text" name="port" id="port" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color: red">*</span>责任人</td>
			<td><input type="text" name="personLiable" id="personLiable" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color: red">*</span>排序</td>
			<td><input type="text" name="middlewareOrder" id="middlewareOrder" /></td>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">备注</td>
			<td><input type="text" name="remark" id="remark" /></td>
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
				<button class="layui-btn layui-btn-danger" onclick="iptest()">连接测试</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>