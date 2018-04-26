<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>巡检项</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/operationalLog/checkRecord/checkRecordModel.js"></script>
<script type="text/javascript" src="${configRoot}/js/osplugins/layer/layer.js"></script>
</head>
<body>
	<div style="margin-top:10px;margin-left:10px;">
		<blockquote class="layui-elem-quote">
		    <span class="common_search_fieldName">巡检项名称：</span>
		    <input type="text" class="common_search_ipt" name="sAppName" placeholder="请输入名称" style="margin-right:20px;" />
		     <button onclick="searchLoginLog()" class="layui-btn layui-btn-sm">查&nbsp;&nbsp;询</button>
		    <button style="float:right;" id="common_btn_delete" class="layui-btn layui-btn-sm layui-btn-normal" ><i class="layui-icon"></i> 批量删除</button>
		    <button style="float:right;" id="common_btn_add" class="layui-btn layui-btn-sm"><i class="layui-icon"></i>自定义巡检项</button>
		</blockquote>
	</div>
	<div class="common_table">
		<div class="common_table_bd">
			<table width="100%">
				<thead>
					<tr>
						<th width="60"><input type="checkbox" onclick="qx(this)"></th>
						<th width="60">序号</th>
						<th>名称</th>
						<th>短名</th>
						<th>创建者</th>
						<th>等级</th>
						<th>排序</th>
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
			$("#common_btn_add").click( function() {
				parent.layer.open({
					type : 2,
					title : '新增巡检项',
					shadeClose : true,
					area : [ '750px', '80%' ],
					content : Config.ROOT+'/jsp/operationalLog/checkRecord/addCheckRecordModel.jsp',
					end: function () {
			                init();
			            }
				});
			 });
			$("#common_btn_delete").click(function(){
		    	var checkval = getCheckedValue("systemstate");
		    	
		    	if(!checkval || checkval.length<1)
		    		alert("请选择要删除的记录！");
		    	else{
		    		if(confirm("你确定要删除该条记录吗？")){
		    		
				    	 $.ajax({
				 			type : 'post',
				 			url : Config.ROOT+"/opsCheckRecordModel/deleteModel.do",
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
