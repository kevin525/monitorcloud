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
<script type="text/javascript" src="${configRoot}/jsp/user/role/role.js"></script>

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
				url : "${pageContext.request.contextPath}/role/saveOrUpdateRole.do",
				data : params,
				success : function(con) {
					if(con.data=="ok"){
						parent.layer.msg('添加成功', {icon: 1,time: 500});
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

function check(){
	var canSubmit=true;
	params.roleName=$("#roleName").val();
	params.roleState=$("input[name=roleState]:checked").val();
	 if(!params.roleName){
		$(".span").text("*角色名称不能为空");
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
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color: red">*</span>角色名称</td>
			<td><input type="text" name="roleName" id="roleName" /></td>
		</tr>
		<tr>
			<td class="tableleft" style="height:30px;line-height:30px;" align="center">角色状态</td>
			<td><input type="radio" name="roleState"  id="roleState" value="1"/>启用
			&nbsp;&nbsp;<input type="radio" name="roleState"  id="roleState" value="2" checked="checked"/>禁用</td>
		</tr>
		<tr>
			<td colspan="2">
			<button class="layui-btn layui-btn-normal" onclick="addSystem()">添加</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>