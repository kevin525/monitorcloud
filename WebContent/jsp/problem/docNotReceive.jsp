<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>领导收不到公文</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/problem/docNotReceive.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/problem/leaderNameMap.js"></script>
</head>
<body>
<div class="common_search">
	<span class="common_search_fieldName">公文标题：</span>
    <input type="text" class="common_search_ipt" name="sAppName" placeholder="请输入要查询的公文标题" style="margin-right:20px;" />
    <span class="common_search_fieldName">流程id：</span>
    <input type="text" class="common_search_ipt" name="sAppIp" placeholder="请输入应用所在的流程id" style="margin-right:20px;" />
    <a href="javascript:searchLoginLog();" class="btn_common" style="display:inline-block;">查 询</a>
</div>
	<div class="common_table">
		<div class="common_table_hd">
		<a href="javascript:void(0);" class="common_btn_add">
        	<i class="icon_common_add"></i><span class="common_btn_name">批量处理</span>
        </a>
        <a href="javascript:void(0);" class="common_btn_delete">
        	<i class="icon_common_delete"></i><span class="common_btn_name">批量删除</span>
        </a>
        
    </div>
		<div class="common_table_bd">
			<table width="100%">
				<thead>
					<tr>
					    <th width="40"><input type="checkbox" onclick="qx(this)"></th>
					    <th width="40">序号</th>
						<th width="50">公文id</th>
						<th width="80">流程id</th>
						<th width="300">标题</th>
						<th width="80">发送人</th>
						<th width="80">领导</th>
						<th width="50">年份</th>
						<th width="50">文号</th>
						<th width="100">发送时间</th>
						<th width="80">操作</th>
					</tr>
				</thead>
				<tbody  id="mbody">
				</tbody>
			</table>
			<!--分页-->
			<div class="pageCount" id="pageId"></div>
		</div>
	</div>
<script type="text/javascript">
$(".common_btn_delete").click(function(){
	var checkval = getCheckedValue("status");
	
	if(!checkval || checkval.length<1)
		alert("请选择要删除的记录！");
	else{
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/docNotReceive/deleteDocNotReceive.do",
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
	
});

$(".common_btn_add").click(function(){
	var checkval = getCheckedValue("status");
	if(!checkval || checkval.length<1)
		alert("请选择要处理的记录！");
	else{
	  var arr = checkval.split(",");
	  for(var i=0;i<arr.length;i++){
		  var id = arr[i];
		  updateDocStatus(id,'1');
	  }
	  
	}
	
});
</script>
	
</body>
</html>