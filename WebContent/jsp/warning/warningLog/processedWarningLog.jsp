<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>告警设置</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/warning/warningLog/processedWarningLog.js"></script>
</head>
<body>
<blockquote class="layui-elem-quote">
<span class="common_search_fieldName">告警原因：</span>
    <input type="text" class="common_search_ipt" name="sAppName" placeholder="请输入要查询的告警原因" style="margin-right:20px;width:300px;" />
     <button onclick="searchLoginLog()" class="layui-btn layui-btn-sm">查&nbsp;&nbsp;询</button>
    <button style="float:right;" class="layui-btn layui-btn-sm layui-btn-normal" id="common_btn_delete"><i class="layui-icon"></i> 批量删除</button>
</blockquote>
<div class="common_table">
    <div class="common_table_bd">
    	<table width="100%">
        	<thead>
            	<tr>
                	<th width="40"><input type="checkbox" id="allckb"></th>
                    <th width="40">序号</th>
                    <th width="80">告警对象</th>
                    <th >告警原因</th>
                    <th width="120">告警方式</th>
                    <th width="160">告警时间</th>
                    <th width="120">操作</th>
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
    $("#common_btn_delete").click(function(){
    	var checkval = getCheckedValue("systemstate");
    	if(!checkval || checkval.length<1){
    		parent.layer.confirm('请选择要删除的记录！', {
    			  btn: ['关闭'] //按钮
    		});
    	}else{
    		parent.layer.confirm('您确定要删除选中记录吗？', {
    			  btn: ['确定','取消'] //按钮
    		}, function(){
    			  $.ajax({
    					type : 'post',
	    				url : Config.ROOT+"/warningLog/deleteWarningLog.do",
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
	 $("#allckb").click(function(){
		 if($(this).is(":checked")){
			 $("input[name='systemstate']").prop("checked","checked");
		 }else{
			 $("input[name='systemstate']").removeAttr("checked")
		 }
	 });
});
</script>
</body>
</html>
