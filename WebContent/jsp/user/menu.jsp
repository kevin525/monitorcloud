<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单管理</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="common_search">
	<span class="common_search_fieldName">菜单名称：</span>
    <input type="text" class="common_search_ipt" placeholder="请输入要查询的菜单名称" style="margin-right:20px;" />
    <span>菜单分类：</span>
    <select class="common_search_select" style="margin-right:20px;">
    	<option>不限</option>
    	<option>一级菜单</option>
    	<option>二级菜单</option>
    </select>
    <a href="javascript:void(0);" class="btn_common" style="display:inline-block;">查 询</a>
</div>
<div class="common_table">
	<div class="common_table_hd">
    	<a href="javascript:void(0);" class="common_btn_add">
        	<i class="icon_common_add"></i><span class="common_btn_name">新增</span>
        </a>
        <a href="javascript:void(0);" class="common_btn_delete">
        	<i class="icon_common_delete"></i><span class="common_btn_name">批量删除</span>
        </a>
    </div>
    <div class="common_table_bd">
    	<table width="100%">
        	<thead>
            	<tr>
                	<th width="60"><input type="checkbox"></th>
                    <th width="60">序号</th>
                    <th>菜单名称</th>
                    <th>菜单类别</th>
                    <th>父菜单名称</th>
                    <th>菜单别名</th>
                    <th>是否禁用</th>
                    <th width="100">操作</th>
                </tr>
            </thead>
            <tbody>
            	<tr bgcolor="#ffffff">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">1</td>
                    <td align="center">日常运维</td>
                    <td align="center">一级菜单</td>
                    <td align="center">无</td>
                    <td align="center">rcyw</td>
                    <td align="center">否</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                <tr bgcolor="#f8f8f8">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">2</td>
                    <td align="center">系统问题处理</td>
                    <td align="center">一级菜单</td>
                    <td align="center">无</td>
                    <td align="center">xtwtcl</td>
                    <td align="center">否</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                <tr bgcolor="#ffffff">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">3</td>
                    <td align="center">系统运行情况</td>
                    <td align="center">二级菜单</td>
                    <td align="center">日常运维</td>
                    <td align="center">xtyxqk</td>
                    <td align="center">否</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                <tr bgcolor="#f8f8f8">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">4</td>
                    <td align="center">文件备份情况</td>
                    <td align="center">二级菜单</td>
                    <td align="center">日常运维</td>
                    <td align="center">wjbfqk</td>
                    <td align="center">否</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                <tr bgcolor="#f8f8f8">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">5</td>
                    <td align="center">领导只签批一次公文</td>
                    <td align="center">二级菜单</td>
                    <td align="center">系统问题处理</td>
                    <td align="center">ldzqpycgw</td>
                    <td align="center">否</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                <tr bgcolor="#f8f8f8">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">6</td>
                    <td align="center">领导签批多次公文</td>
                    <td align="center">二级菜单</td>
                    <td align="center">系统问题处理</td>
                    <td align="center">ldqpdcgw</td>
                    <td align="center">否</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
            </tbody>
    	</table>
        <!--分页-->
        <div class="pageCount">
            <a href="javascript:void(0);">上一页</a>
            <a href="javascript:void(0);">首页</a>
            <a href="javascript:void(0);" class="curr">1</a>
            <a href="javascript:void(0);">2</a>
            <a href="javascript:void(0);">3</a>
            <a href="javascript:void(0);">4</a>
            <a href="javascript:void(0);">5</a>
            <a href="javascript:void(0);">6</a>
            <a href="javascript:void(0);">7</a>
            <a href="javascript:void(0);">8</a>
            <a href="javascript:void(0);">9</a>
            <a href="javascript:void(0);">10</a>
            <a href="javascript:void(0);">下一页</a>
            <a href="javascript:void(0);">最后一页</a>
            <span>共<strong>30</strong>页 <strong>400</strong>条</span>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
    $(".common_btn_add").click(function(){
		parent.layer.open({
		  type: 2,
		  title: '新增会员单位',
		  shadeClose: true,
		  area: ['900px', '90%'], 
		  content: 'addNewMember.html'
		});
	});
});
</script>
</body>
</html>
