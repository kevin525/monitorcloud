<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统运行情况</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/user/menu/menuResources.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/js/osplugins/layer/layer.js"></script>
</head>

<body>
<div class="common_search">
	<span class="common_search_fieldName">菜单名称：</span>
    <input type="text" class="common_search_ipt" name="sAppName" placeholder="请输入要查询的系统名称" style="margin-right:20px;" />
    <span class="common_search_fieldName">菜单分类：</span>
    <input type="text" class="common_search_ipt" name="sAppIp" placeholder="请输入应用所在的ip地址" style="margin-right:20px;" />
    <a href="javascript:searchLoginLog();" class="btn_common" style="display:inline-block;">查 询</a>
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
            	    <th width="60"><input type="checkbox" onclick="qx(this)"></th>
                    <th width="60">序号</th>
                    <th>系统名称</th>
                    <th>所在服务器</th>
                    <th>数据库ip</th>
                     <th>数据库类别</th>
                     <th>数据库名称</th>
                    <th>服务器网络状态</th>
                    <th>数据库网络状态</th>
                    <th>系统运行情况</th>
                     <th>操作</th>
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
    $(".common_btn_add").click(function(){
		parent.layer.open({
		  type: 2,
		  title: '新增菜单',
		  shadeClose: true,
		  area: ['600px', '90%'], 
		  content: 'jsp/user/menu/addMenuResource.jsp'
		});
	});
    
    $(".common_btn_delete").click(function(){
    	var checkval = getCheckedValue("systemstate");
    	
    	if(!checkval || checkval.length<1)
    		alert("请选择要删除的记录！");
    	else{
    		 if(confirm("你确定要删除该条记录吗？")){
    		
		    	 $.ajax({
		 			type : 'post',
		 			url : "${pageContext.request.contextPath}/appSystem/deleteAppSystem.do",
		 			data : {
		 				ids : checkval
					},
		 			success : function(con) {
		 				if(con.data=="ok"){
		 					alert("删除成功");
		 					init();
		 				}else{
		 					alert("删除失败");
		 				}
		 			}
		 		}); 
    		 }
    	}
    	
    });
    
    
});

</script>
</body>
</html>
