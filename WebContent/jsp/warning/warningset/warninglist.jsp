<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>告警设置</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/warning/warningset/warninglist.js"></script>
</head>
<body>
<div style="margin-top:10px;margin-left:10px;">
<blockquote class="layui-elem-quote">
    <span class="common_search_fieldName">告警规则名称：</span>
    <input type="text" class="common_search_ipt" name="sAppName" placeholder="请输入要查询的告警规则名称" style="margin-right:20px;width:300px;" />
     <button onclick="searchLoginLog()" class="layui-btn layui-btn-sm">查&nbsp;&nbsp;询</button>
    <button style="float:right;" id="common_btn_delete" class="layui-btn layui-btn-sm layui-btn-normal" ><i class="layui-icon"></i> 批量删除</button>
    <button style="float:right;" id="common_btn_add" class="layui-btn layui-btn-sm"><i class="layui-icon"></i>新增</button>
</blockquote>
</div>

<div class="common_table">
    <div class="common_table_bd">
    	<table width="100%">
        	<thead>
            	<tr>
                	<th width="60"><input type="checkbox"></th>
                    <th width="60">序号</th>
                    <th>告警规则名称</th>
                    <th>告警规则类型</th>
                    <th>告警规则</th>
                    <th>发送方式</th>
                     <th width="140">操作</th>
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
$(document).ready(function(e) {
	init();
    $("#common_btn_add").click(function(){
    	parent.layer.open({
    		type : 2,
    		title : '新增告警规则',
    		shadeClose : true,
    		area : [ '700px', '480px' ],
    		content : 'jsp/warning/warningset/addWarning.jsp'
    	});
	});
    
    $("#common_btn_delete").click(function(){
    	var checkval = getCheckedValue("systemstate");
    	if(!checkval || checkval.length<1){
    		parent.layer.confirm('请选择要删除的记录！', {
    			  btn: ['关闭'] //按钮
    		});
    	}else{
    		parent.layer.confirm('您确定要删除该条记录吗？', {
    			  btn: ['确定','取消'] //按钮
    		}, function(){
    			  $.ajax({
    					type : 'post',
    					url : Config.ROOT+"/warning/deleteWarning.do",
			 			data : {
			 				ids : checkval
						},
    					success : function(con) {
    						if(con.data=="ok"){
    							parent.layer.msg('删除成功', {icon: 1,time: 500});
    							init();
    						}else{
    							parent.layer.msg('删除失败', {icon: 2,time: 500});
    						}
    					}
    				});
    		});
    	}
    });
});
</script>
</body>
</html>
