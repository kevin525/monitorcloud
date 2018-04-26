<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet"type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/user/privilege/privilege.js"></script>
<script type="text/javascript" src="${configRoot}/js/osplugins/layer/layer.js"></script>
<link rel="stylesheet" href="${configRoot}/jsp/user/privilege/ztree/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${configRoot}/jsp/user/privilege/ztree/privilege.css" type="text/css">
<script type="text/javascript" src="${configRoot}/jsp/user/privilege/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/user/privilege/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/user/privilege/ztree/jquery.ztree.excheck.js"></script>
<style>
	.common_table_bd .table1 {float:left}
	.common_table_bd .table2 {float:right}
</style>
<script language="javascript">
$(document).ready(function(){
	 init(); 
});

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


</script>

</head>

<body>
<div style="margin-top:10px;margin-left:10px;">
<blockquote class="layui-elem-quote">
    <span class="common_search_fieldName">角色名称：</span>
    <input type="text" class="common_search_ipt" name="roleName" placeholder="请输入要查询的角色" style="margin-right:20px;" />
     <button onclick="searchLoginLog()" class="layui-btn layui-btn-sm">查&nbsp;&nbsp;询</button>
</blockquote>
</div>
<div class="common_table">
		<div class="common_table_bd">
		<div style=" height:300px;">
			<table width="40%" class="table1" >
				<thead>
					<tr>
						<th width="60">序号</th>
						<th width="250">角色名称</th>
					</tr>
				</thead>
				<tbody id="mbody">
				</tbody>
			</table>
			
			<table width="58%" class="table2" height="260">
					<tr >
						<th width="58%" align="left">&nbsp;菜单功能<a href="javascript:saveMenuAndRole()" style="text-decoration:none;float:right;"><img src="ztree/img/base.png">保存&nbsp;&nbsp;&nbsp;&nbsp;</a>
								</th>
					</tr>
					<tr height="224">
						<td >
						<div id="ztree"  style="overflow:auto;height:260px;">
								<ul id="treeDemo" class="ztree"></ul>
						</div>
						 </td>
					</tr>
			</table>
			</div>
		</div>
	</div>
</body>
</html>
