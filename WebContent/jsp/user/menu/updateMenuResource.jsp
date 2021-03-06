<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/user/menu/menuResource.js"></script>


	<style>
	.check_span{float:right;}
	</style>
<script language="javascript">
var params  ={};
var id='<%=request.getParameter("id")%>';
$(document).ready(function(e) {
	$.ajax({
		type : 'post',
			url : Config.ROOT+"/menuResource/getMenuResource.do",
			data : {id : id},
			success : function(con) {
				if(null != con.data ){
					$(":radio[name='resourcesState'][value='" + con.data.resourcesState + "']").prop("checked", "checked");
					$(":radio[name='nodeValue'][value='" + con.data.nodeValue + "']").prop("checked", "checked");
					if(con.data.nodeValue==2){
						showParentList(con.data.resourcesPid);
					}
					$("#resourcesName").val(con.data.resourcesName);
					$("#resoucesCode").val(con.data.resoucesCode);
					$("#resourcesUrl").val(con.data.resourcesUrl);
					$("#resoucesImg").val(con.data.resoucesImg);
					$("#resourcesOrderNum").val(con.data.resourcesOrderNum);
				}else{
					parent.layer.msg('数据异常', {icon: 2,time: 500});
					closeLayer(true);
				}
			}
		}); 
	
	
});
//显示一级菜单列表
function showParentList(resourcesId){
	$.ajax({
		type : 'post',
		url : "${configRoot}/menuResource/getFirstMenuList.do",
		data : params,
		success : function(con) {
			var contents = con.data;
			if(contents==null){
				$("#resourcesPidHtml").html('<select id="mySelect" name="mySelect1"></select>');
			}else{
				var html ="";
				html += '<select id="mySelect" name="mySelect">';
				for(var i in contents){             
					html += '<option value="'+contents[i].resourcesId+'">'+contents[i].resourcesName+'</option>';
		        }
		        html +='</select>';
				$("#resourcesPidHtml").html(html);
				if(null !=resourcesId){
					  $("#mySelect").val(resourcesId);
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
	$("#resourcesPidHtml").html('<select id="mySelect" name="mySelect1"></select>');
}

function editMenuResource(){
	if(check()){
		 $.ajax({
			type : 'post',
			url : "${configRoot}/menuResource/saveOrUpdateMenuResource.do",
			data : params,
			success : function(con) {
				if(con.data=="ok"){
					parent.layer.msg('修改成功', {icon: 1,time: 500});
					closeLayer(true);
				}else{
					$(".span").text("服务器出错");
				}
			}
		}); 
	}
}
function check(){
	var canSubmit=true;
	params.resourcesId=id;
	params.resourcesName=$("#resourcesName").val();
	params.resoucesCode=$("#resoucesCode").val();
	params.resourcesUrl=$("#resourcesUrl").val();
	params.resourcesOrderNum=$("#resourcesOrderNum").val();
	params.resourcesState=$('input:radio[name="resourcesState"]:checked').val();
	var mySelect = $("#mySelect").val();
	if(mySelect==null){
		params.nodeValue="1";
		params.resourcesPid="0";
	}else{
		params.nodeValue="2";
		params.resourcesPid=mySelect;
	}
	 if(!params.resourcesName){
		$(".span").text("*菜单名称不能为空");
		canSubmit=false;
	}else if(!params.resoucesCode){
		$(".span").text("*菜单别名不能为空");
		canSubmit=false;
	}else if(!params.resourcesUrl){
		$(".span").text("*访问地址不能为空");
		canSubmit=false;
	}else if(!params.resourcesOrderNum){
		$(".span").text("*排序不能为空");
		canSubmit=false;
	}else if(!(/^[0-9]+$/).test(params.resourcesOrderNum)){
		$(".span").text("*排序只能为数字");
		canSubmit=false;
	}
	return canSubmit;
}
function closeLayer(isrefresh){
	if(isrefresh){
		parent.window.iframe.init();
	}
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index)
	
}
</script>
</head>
<body>
	<table class="table table-bordered table-hover m10">
		<tr align="center">
			<td class="tableleft"   style="height:30px;line-height:30px;" align="center">菜单名称
			</td>
			<td><input type="text" style="width:400px;" name="resourcesName" id="resourcesName" /></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">菜单别名</td>
			<td><input type="text"  style="width:400px;" name="resoucesCode" id="resoucesCode" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">访问地址</td>
			<td><input type="text" style="width:400px;" name="resourcesUrl" id="resourcesUrl" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">菜单图标</td>
			<td><input type="text" style="width:400px;" name="resoucesImg" id="resoucesImg" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">菜单类别</td>
			<td><input type="radio" name="nodeValue"   value="1" onclick="hideParentList()"/>一级菜单
			&nbsp;&nbsp;<input type="radio" name="nodeValue"   value="2" onclick="showParentList()"/>二级菜单</td>
		</tr>
		
		<tr align="center" style="display:none" id="firstMenu">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">一级菜单</td>
			<td><div id="resourcesPidHtml"></div></td>
		</tr>
		
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">排序</td>
			<td><input type="text" style="width:400px;" name="resourcesOrderNum" id="resourcesOrderNum" /></td>
		</tr>
		<tr>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center">是否启用</td>
			<td><input type="radio" name="resourcesState"  value="1"/>启用
			&nbsp;&nbsp;<input type="radio" name="resourcesState"  value="0"/>禁用</td>
		</tr>
		<tr >
			<td colspan="2">
			    <button class="layui-btn layui-btn-normal" onclick="editMenuResource()">确定</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>


</body>
</html>