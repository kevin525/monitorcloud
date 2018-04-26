<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>领导只签批一次</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/problem/leaderSignOne.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/problem/leaderNameMap.js"></script>
</head>
<body>
      <div class="common_table_hd" style="margin-left:20px;">
			<a href="javascript:void(0);" class="common_btn_add"> <i
				class="icon_common_add"></i><span class="common_btn_name">第一步：根据流程id查询</span>
			</a>
		</div>
		<div class="common_search">
			<span class="common_search_fieldName">流程id：</span> <input type="text"
				class="common_search_ipt" id="flowId"
				placeholder="请输入流程id" style="margin-right: 20px;" /> 
			<span class="common_search_fieldName">年份：</span> <input type="text"
				class="common_search_ipt" id="docYear"
				placeholder="请输入年份" style="margin-right: 20px;width:100px;" /> 
			<span class="common_search_fieldName">文号：</span> <input type="text"
				class="common_search_ipt" id="docNum"
				placeholder="请输入文号" style="margin-right: 20px;width:100px;" />
			<a href="javascript:void(0);" class="btn_common"
				style="display: inline-block;" onclick="searchInfo();">查 询</a>
		</div>
		
   <div class="common_table_hd" style="margin-left:20px;">
		<a href="javascript:void(0);" class="common_btn_add"> <i
			class="icon_common_add"></i><span class="common_btn_name">第二步：删除领导签批笔迹</span>
		</a>
	</div>
	<div class="common_table">
		
		<div class="common_table_bd">
			<table width="100%">
				<thead>
					<tr>
						<th width="60">id</th>
						<th width="100">流程id</th>
						<th width="250">标题</th>
						<th width="100">发送人</th>
						<th width="100">领导</th>
						<th width="100">状态</th>
						<th width="50">年份</th>
						<th width="50">文号</th>
						<th width="70">操作</th>
					</tr>
				</thead>
				<tbody class="constructList" id="approvalList">

				</tbody>
			</table>
		</div>
	</div>
	
    <div class="common_table_hd" style="margin-left:20px;">
		<a href="javascript:void(0);" class="common_btn_add"> <i
			class="icon_common_add"></i><span class="common_btn_name">第三步：删除领导公文签批记录</span>
		</a>
	</div>
	<div class="common_table">
		
		<div class="common_table_bd">
			<table width="100%">
				<thead>
					<tr>
						<th width="60">id</th>
						<th width="100">流程id</th>
						<th width="250">标题</th>
						<th width="100">发送人</th>
						<th width="100">领导</th>
						<th width="50">年份</th>
						<th width="50">文号</th>
						<th width="70">操作</th>
					</tr>
				</thead>
				<tbody class="constructList" id="messageList">
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="common_table_hd" style="margin-left:20px;">
		<a href="javascript:void(0);" class="common_btn_add"> <i
			class="icon_common_add"></i><span class="common_btn_name">第四步：删除办理流程记录</span>
		</a>
	</div>
	<div class="common_table">
		
		<div class="common_table_bd">
			<table width="100%">
				<thead>
					<tr>
						<th width="30%">办理时间</th>
						<th width="60%">办理人员</th>
						<!-- <th width="200">办理意见</th> -->
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody class="constructList" id="flowList">

				</tbody>
			</table>
		</div>
	</div>
	<div class="common_table_hd" style="margin-left:20px;">
		<a href="javascript:void(0);" class="common_btn_add"> <i
			class="icon_common_add"></i><span class="common_btn_name">第五步：从PC端重新把公文送给领导</span>
		</a>
	</div>
	<script type="text/javascript">
$(document).ready(function() {
    //创建新用户
	$(".common_btn_add").click(function(){
		parent.layer.open({
		  type: 2,
		  title: '添加优秀施工企业',
		  shadeClose: true,
		  area: ['900px', '90%'], 
		  content: "${appName}/construct/addConstruct.jsp",
		  cancel: function(index){
              //layer.close(index);
          }
		});
	});
});
</script>
</body>
</html>