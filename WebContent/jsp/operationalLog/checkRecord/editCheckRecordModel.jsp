<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加巡检记录</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/jsp/operationalLog/checkRecord/checkRecord.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>

<style type="text/css">
.yseno{
	margin-left: 10px;
	display: inline-block;
}
</style>
<script language="javascript">
var id='<%=request.getParameter("id")%>';
var params ={};
$(document).ready(function(e) {
	$.ajax({
		type : 'post',
		url : Config.ROOT+"/opsCheckRecordModel/getById.do",
		data: {id : id},
		success : function(con) {
			if(null != con.data ){
				$(":radio[name='isUse'][value='" + con.data.isUse + "']").prop("checked", "checked");
				$(":radio[name='nodeValue'][value='" + con.data.nodeValue + "']").prop("checked", "checked");
				if(con.data.nodeValue==2){
					showParentList(con.data.fatherId);
				}
				$("#name").val(con.data.name);
				$("#shortName").val(con.data.shortName);
				$("#modelOrder").val(con.data.modelOrder);
				
				params.id = id;
				params.ownerId=ownerId;
				params.fatherId= fatherId;
				params.createDate= createDate;
				
			}else{
				alert("数据异常");
				closeLayer(true);
			}
		}
	});
	
});
//显示一级菜单列表
function showParentList(fatherId){
	var model ={};
	model.nodeValue="1";
	model.isUse="0";
	$.ajax({
		type : 'post',
		url : Config.ROOT+"/opsCheckRecordModel/getModelList.do",
		data: model,
		success : function(con) {
			var contents = con.pageData;
			if(contents==null){
				$("#fatherIdHtml").html('<select id="mySelect" name="mySelect1"></select>');
			}else{
				var html ="";
				html += '<select id="mySelect" name="mySelect">';
				for(var i in contents){             
					html += '<option value="'+contents[i].id+'">'+contents[i].name+'</option>';
		        }
		        html +='</select>';
				$("#fatherIdHtml").html(html);
				if(null !=fatherId){
					  $("#mySelect").val(fatherId);
				}
			}
		}
	}); 
	$("#firstMenu").show();
}

//隐藏一级菜单列表
function hideParentList(){
	$("#firstMenu").hide();
	$("#mySelect").remove();
	$("#fatherIdHtml").html('<select id="mySelect" name="mySelect1"></select>');
}

function closeLayer(isrefresh){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index)
	
}

function edit(){
	if(check()){
		$.ajax({
			type : 'post',
			url : Config.ROOT+"/opsCheckRecordModel/saveOrUpdateModel.do",
			data: params,
			success : function(con) {
				if(con.data=='ok'){
					alert("修改成功");
					closeLayer(true);
				}else{
					alert("修改失败");
				}
			}
		});
		
	}
}
function check(){
	

	var canSubmit=true;
	params.name=$("#name").val();
	params.shortName=$("#shortName").val();
	params.modelOrder=$("#modelOrder").val();
	params.isUse=$('input:radio[name="isUse"]:checked').val();
	var mySelect = $("#mySelect option:selected").val();
	if(mySelect==null){
		params.nodeValue="1";
		params.fatherId="0";
	}else{
		params.nodeValue="2";
		params.fatherId=mySelect;
	}
	 if(!params.name){
		$(".span").text("*巡检项名称不能为空");
		canSubmit=false;
	}else if(!params.shortName){
		$(".span").text("*巡检项别名不能为空");
		canSubmit=false;
	}else if(!params.modelOrder){
		$(".span").text("*排序不能为空");
		canSubmit=false;
	}else if(!(/^[0-9]+$/).test(params.modelOrder)){
		$(".span").text("*排序只能为数字");
		canSubmit=false;
	}
	return canSubmit;
}
</script>
</head>
<body>
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>巡检项名称
			</td>
			<td><input type="text" style="width:400px;" name="name" id="name" /></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>巡检项别名</td>
			<td><input type="text"  style="width:400px;" name="shortName" id="shortName" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>类别</td>
			<td><input type="radio" name="nodeValue"   value="1" checked="checked" onclick="hideParentList()"/>一级巡检项
			&nbsp;&nbsp;<input type="radio" name="nodeValue"   value="2" onclick="showParentList()"/>二级巡检项
			</td>
		</tr>
		
		<tr align="center" style="display:none" id="firstMenu">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>一级巡检项</td>
			<td><div id="fatherIdHtml"></div></td>
		</tr>
		
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>排序</td>
			<td><input type="text" style="width:400px;" name="modelOrder" id="modelOrder" /></td>
		</tr>
		<tr>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>是否启用</td>
			<td><input type="radio" name="isUse"  value="0"/>启用
			&nbsp;&nbsp;<input type="radio" name="isUse"  value="1" checked="checked"/>禁用</td>
		</tr>
		<tr>
			<td colspan="2">
					<button class="btn btn-primary" type="button" onclick="edit()">修改</button>
					&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="closeLayer(false)">取消</button>
					<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>