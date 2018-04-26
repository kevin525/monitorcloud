<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件备份情况</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/js/osplugins/layer/layer.js"></script>
</head>

<body>
<div class="common_search">
	<span class="common_search_fieldName">备份时间：</span>
    <input type="text" class="common_search_ipt" placeholder="开始时间" style="width:100px;" />
    <span>~</span>
    <input type="text" class="common_search_ipt" placeholder="结束时间" style="margin-right:10px;width:100px;" />
    <span>文件名称：</span>
    <input type="text" class="common_search_ipt" placeholder="请输入要查询的提出人" style="margin-right:20px;" />
    <a href="javascript:void(0);" class="btn_common" style="display:inline-block;">查 询</a>
</div>
<div class="common_table">
	<div class="common_table_hd">
    </div>
    <div class="common_table_bd">
    	<table width="100%">
        	<thead>
            	<tr>
                    <th width="60">序号</th>
                    <th>文件名称</th>
                    <th>正式环境ip</th>
                    <th>备份环境ip</th>
                    <th>正式环境文件大小</th>
                    <th>备份环境文件大小</th>
                    <th>是否正常</th>
                    <th>运行状态</th>
                    <th>备份时间</th>
                </tr>
            </thead>
            <tbody>
            	<tr bgcolor="#ffffff">
                    <td align="center">1</td>
                    <td align="left" style="padding:0 3px;">通州OA文件</td>
                    <td align="center">10.171.251.67</td>
                    <td align="center">10.171.251.46</td>
                    <td align="center">25M</td>
                    <td align="center">24M</td>
                    <td align="center">异常</td>
                    <td align="center"><img src="../../images/light_red.png" width="24" height="24" /></td>
                    <td align="center">2017-03-03</td>
                </tr>
                <tr bgcolor="#f8f8f8">
                    <td align="center">2</td>
                    <td align="left" style="padding:0 3px;">移动服务端文件</td>
                    <td align="center">10.171.251.80</td>
                    <td align="center">10.171.251.26</td>
                    <td align="center">40M</td>
                    <td align="center">40M</td>
                    <td align="center">正常</td>
                    <td align="center"><img src="../../images/light_normal.png" width="24" height="24" /></td>
                    <td align="center">2017-03-03</td>
                </tr>
                <tr bgcolor="#ffffff">
                    <td align="center">3</td>
                    <td align="left" style="padding:0 3px;">党政信息文件</td>
                    <td align="center">10.171.251.36</td>
                    <td align="center">10.171.251.26</td>
                    <td align="center">25M</td>
                    <td align="center">24M</td>
                    <td align="center">异常</td>
                    <td align="center"><img src="../../images/light_red.png" width="24" height="24" /></td>
                    <td align="center">2017-03-01</td>
                </tr>
                <tr bgcolor="#f8f8f8">
                    <td align="center">4</td>
                    <td align="left" style="padding:0 3px;">流程数据库</td>
                    <td align="center">10.171.251.71</td>
                    <td align="center">10.171.251.26</td>
                    <td align="center">25M</td>
                    <td align="center">25M</td>
                    <td align="center">正常</td>
                    <td align="center"><img src="../../images/light_normal.png" width="24" height="24" /></td>
                    <td align="center">2017-03-01</td>
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
