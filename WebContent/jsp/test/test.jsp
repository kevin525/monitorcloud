<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供开发使用</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/test/test.js"></script>
</head>

<body>
<div class="common_search">
	<span class="common_search_fieldName">名称：</span>
    <input type="text" class="common_search_ipt" placeholder="请输入要查询的名称" style="margin-right:20px;" />
    <span>选择分类：</span>
    <select class="common_search_select" style="margin-right:20px;">
    	<option>不限</option>
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
        <a href="javascript:void(0);" class="common_btn_import">
        	<i class="icon_common_import"></i><span class="common_btn_name">导入数据</span>
        </a>
        <a href="javascript:void(0);" class="common_btn_scan">
        	<i class="icon_common_scan"></i><span class="common_btn_name">预览施工企业</span>
        </a>
    </div>
    <div class="common_table_bd">
    	<table width="100%">
        	<thead>
            	<tr>
                	<th width="60"><input type="checkbox"></th>
                    <th width="60">序号</th>
                    <th>登录名</th>
                    <th>密码</th>
                    <th width="100">操作</th>
                </tr>
            </thead>
            <tbody id="mbody">
            <!--  
            	<tr bgcolor="#ffffff">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">1</td>
                    <td align="left" style="padding:0 3px;">中国电力建设企业协会</td>
                    <td align="center">北京市</td>
                    <td align="left" style="padding:0 3px;">http://www.cepca.org.cn/</td>
                    <td align="center">2015-6-25</td>
                    <td align="center">是</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                <tr bgcolor="#f8f8f8">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">2</td>
                    <td align="left" style="padding:0 3px;">中国电力建设企业协会</td>
                    <td align="center">北京市</td>
                    <td align="left" style="padding:0 3px;">http://www.cepca.org.cn/</td>
                    <td align="center">2015-6-25</td>
                    <td align="center">是</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                <tr bgcolor="#ffffff">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">3</td>
                    <td align="left" style="padding:0 3px;">中国电力建设企业协会</td>
                    <td align="center">北京市</td>
                    <td align="left" style="padding:0 3px;">http://www.cepca.org.cn/</td>
                    <td align="center">2015-6-25</td>
                    <td align="center">是</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                <tr bgcolor="#f8f8f8">
                	<td align="center"><input type="checkbox"></td>
                    <td align="center">4</td>
                    <td align="left" style="padding:0 3px;">中国电力建设企业协会</td>
                    <td align="center">北京市</td>
                    <td align="left" style="padding:0 3px;">http://www.cepca.org.cn/</td>
                    <td align="center">2015-6-25</td>
                    <td align="center">是</td>
                    <td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right:5px;">修改</a><a href="javascript:void(0);" class="common_table_operate">删除</a></td>
                </tr>
                -->
            </tbody>
           
    	</table>
        <!--分页-->
        <div class="pageCount" id="pageId">
        
        </div>
        <!--
        <div class="pageId" id="pageId">
          
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
			-->    
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
