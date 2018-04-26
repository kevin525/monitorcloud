<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/user/user.js"></script>
<script type="text/javascript"
	src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/js/osplugins/layer/layer.js"></script>
<script language="javascript">
$(document).ready(function(){
	init();
});
</script>
</head>

<body>
<div style="margin-top:10px;margin-left:10px;">
<blockquote class="layui-elem-quote">
    <span class="common_search_fieldName">姓名：</span> 
		<input type="text" class="common_search_ipt" placeholder="请输入要查询的姓名" id="psname"	style="margin-right: 20px;" /> 
     <button onclick="searchLoginLog()" class="layui-btn layui-btn-sm">查&nbsp;&nbsp;询</button>
    <button onclick="dodelete()" style="float:right;" id="common_btn_delete" class="layui-btn layui-btn-sm layui-btn-normal" ><i class="layui-icon"></i> 批量删除</button>
    <button onclick="addUser()" style="float:right;" id="common_btn_add" class="layui-btn layui-btn-sm"><i class="layui-icon"></i>新增</button>
</blockquote>
</div>
	<div class="common_table">
		<div class="common_table_bd">
			<table width="100%">
				<thead>
					<tr>
						<th width="60"><input type="checkbox" onclick="qx(this)"></th>
						<th width="60">序号</th>
						<th>姓名</th>
						<th>角色</th>
						<th>性别</th>
						<th>登录账号</th>
						<th>手机号</th>
						<th>登记时间</th>
						<th>是否禁用</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody id="mbody">
				</tbody>
			</table>
			<!--分页-->
			<div class="pageCount" id="pageId"></div>
		</div>
	</div>
</body>
</html>
