<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>告警设置</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/user/role/role.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/js/osplugins/layer/layer.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/warning/errorLog/errorLog.js"></script>
</head>

<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title" id="errorLogNameUl">
 	<li value="" class="layui-this">所有</li>
    <li value="server">服务器</li>
    <li value="system">应用系统</li>
    <li value="database">数据库</li>
    <li value="middleware">中间件</li>
  </ul>
</div> 
<div style="margin-left: 5px;">
	<button style="float:left;" class="layui-btn layui-btn-sm layui-btn-normal" id="delete_pl"><i class="layui-icon"></i> 批量删除</button>
</div>
<br/>
<div class="common_table">
	<br/>
    <div class="common_table_bd">
    	<table width="100%">
        	<thead>
            	<tr>
                	<th width="60"><input type="checkbox" id="allCK"></th>
                	<th>序号</th>
                    <th>错误名称</th>
                    <th>服务器ip</th>
                    <th>错误时间</th>
                    <th>操作</th>
                    <!-- <th>错误详情</th>
                    <th>错误类别</th>
                    <th>所属类别id</th>
                    <th>错误类型</th> -->
                </tr>
            </thead>
            <tbody id="mbody">
				</tbody>
    	</table>
        <!--分页-->
        <div class="pageCount" id="pageId"></div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>
