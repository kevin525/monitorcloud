<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sys.domain.view.PersonView"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改用户界面</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<style>
.role_div{
	display:inline-block;
	margin:0 10px;
}

</style>
<script language="javascript">
var personId=null;
var id ='<%=request.getParameter("id")%>';
var roleid = null;
$(document).ready(function(e) {
	$.ajax({
		type : 'post',
		url : Config.ROOT + '/user/getUserByUserId.do',
		data : {
			userId : id
		},
		async : false,
		success : function(con) {

			if (null != con.data) {
				var data = con.data;
				personId = data.personId;
				$("#personState[value='"+ data.personState + "']").attr("checked", "checked");
				$("#sex[value='" + data.sex + "']").attr("checked", "checked");
				if (data.role != null) {
					roleid = data.role.roleId;
				}
				$("#personName").val(data.personName);
				$("#mobile").val(data.personTel);
				$("#createDate").val(data.createDate);
			} else {
				parent.layer.msg('数据异常', {icon: 2,time: 500});
				closeLayer(true);
			}

		}

	});
	var oParams = {};
	oParams.roleState = 1;
	$.ajax({
		type : 'post',
		url : Config.ROOT+ '/role/getRoleListByCriteria.do',
		data : oParams,
		async : false,
		success : function(con) {
			var data = con.data;
			var html = "";
			var flag = false;
			$(data).each(function(i) {
				if (this.roleId == roleid) {
					flag = true;
				}
				html += "<div class='role_div'><span style='margin-right:5px;'>"
						+ this.roleName
						+ "</span>"
						+ "<input type='radio' name='role' value="+this.roleId+" /></div>";
			});
			$("#mbody").html(html);
			if (flag) {
				$("input[value=" + roleid + "]").attr("checked", 'checked');
			} else {
				var rs = document.getElementsByName("role");
				rs[0].checked = true;
			}
		}
	});

});
	//电话验证
	function check() {
		var phone = $("#mobile").val();
		if (phone == "") {
			$("#moblieprompt").html("手机号码不能为空！");
			return false;
		}
		var regexP = new RegExp("^1[0-9]{10}$");//手机号码
		if (regexP.test(phone)) {// || phone == (phone.match(regexP2))){
			$("#moblieprompt").html("");
			return true;
		} else {
			$("#moblieprompt").html("手机号码格式不正确！请重新输入！");
			$("#mobile").focus();
			return false;
		}
	}
	function getid() {
		var createDate = $("#createDate").val();
		var personName = $("#personName").val();
		var personTel = $("#mobile").val();
		var sex = $(':radio[name="sex"]:checked').val();
		var personState = $(':radio[name="personState"]:checked').val();
		var role = $("input[name=role]:checked").val();
		if (!check()) {
			return;
		}
		$.ajax({
			type : 'post',
			url : Config.ROOT + "/user/oneUpdate.do",
			data : {
				personId : personId,
				personName : personName,
				personTel : personTel,
				sex : sex,
				personState : personState,
				createDate : createDate,
				role : role,
				userId : id
			},
			success : function(data) {
				if (data == "success") {
					parent.layer.msg('修改成功', {icon: 1,time: 500});
					closeLayer(true);
				} 
			}
		})
	}
	function closeLayer(isrefresh) {
		if (isrefresh) {
			parent.window.iframe.init();
		}
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index)

	}
</script>
</head>
<body>
	<table class="table table-bordered table-hover m10">
		<tr align="center" style="display: none">
			<td class="tableleft" align="center">ID</td>
			<td><input type="text" name="personId" id="personId"
				readonly="readonly" /></td>
		</tr>
		<tr align="center" style="display: none">
			<td class="tableleft" align="center">时间</td>
			<td><input type="text" name="createDate" id="createDate"
				readonly="readonly" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft" align="center">姓名</td>
			<td><input type="text" name="personName" id="personName" /></td>
		</tr>
		<tr>
			<td class="tableleft" align="center">手机号</td>
			<td><input type="text" name="personTel" id="mobile"
				onblur="check()" /></td>
		</tr>
		<tr>
			<td class="tableleft" align="center">角色</td>
			<td id="mbody"></td>
		</tr>
		<tr>
			<td class="tableleft" align="center">性别</td>
			<td>&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;<input type="radio" name="sex"
				id="sex" value="0" checked="checked" />
				&nbsp;&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;<input type="radio" name="sex"
				id="sex" value="1" /></td>
		</tr>
		<tr>
			<td class="tableleft" align="center">状态</td>
			<td>&nbsp;&nbsp;&nbsp;禁用&nbsp;&nbsp;<input type="radio" name="personState"
				id="personState" value="0" /> &nbsp;&nbsp;启用&nbsp;&nbsp;<input
				type="radio" name="personState" id="personState" value="1"
				checked="checked" /></td>
		</tr>
		<tr >
			<td colspan="2">
			    <button class="layui-btn layui-btn-normal" onclick="getid()">确定</button>
				<button class="layui-btn layui-btn-primary" onclick="closeLayer(false)">取消</button>
				<span id="moblieprompt" class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr>
	</table>

</body>
</html>