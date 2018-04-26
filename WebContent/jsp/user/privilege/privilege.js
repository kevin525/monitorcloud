var fBuildLogContentTr_V = function(con, i) {
	var html = "";
	var sNum = oParams.start + i + 1;
	html += "<tr id='tr_" + con.roleId + "' >";
	html += "<td align='center'>" + sNum + "</td>";
	html += "<td align='center'  onclick='getmenu(" + con.roleId + ");'>"
			+ con.roleName + "</td>";
	html += "</tr>";
	return html;
};
/*
 * 界面初始化
 */
function init() {
	oParams.start = 0;
	oParams.pageSize = 7;
	sUrl = Config.ROOT + "/role/getPageRoleDataAndView.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(), oParams.getPageSize());

}
function searchLoginLog() {
	oParams.roleName = $("input[name='roleName']").val() == null ? "" : $(
			"input[name='roleName']").val();
	init();
}
var roId = "";
var flag = false;
function getmenu(roleid) {
	flag = true;
	roId = roleid;
	$("#mbody tr").each(function() {
		$(this).attr("bgColor", "");
	});
	$("#tr_" + roleid).attr("bgColor", "#eeeeee");
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/menuResource/getAllMenuList.do",
		data : {
			roleid : roleid
		},
		async : false,// 同步方式
		success : function(con) {
			var contents = con.data;
			if (contents != null) {
				var zNodes = new Array();
				for ( var i in contents) {
					zNodes[i] = {
						id : contents[i].resourcesId,
						pId : contents[i].resourcesPid,
						name : contents[i].resourcesName,
						open : true
					};
				}
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			}
			var msg = con.msg;
			if( null!= msg ){
				var aa = msg.split(",");
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var checkeds = treeObj.getNodes();
				var checked = treeObj.transformToArray(checkeds);
				$(aa).each(function(i) {
					$(checked).each(function(j) {
						if ($(this).attr("id") == aa[i]) {
							treeObj.checkNode(this, true, false);
						}
					});
				});
			}
		}
	});
}
function contains(arr, obj) {
	var i = arr.length;
	while (i--) {
		if (arr[i] == obj) {
			return true;
		}
	}
	return false;
}

function saveMenuAndRole() {
	if (!flag) {
		alert("请选择要保存的角色及菜单！");
		return;
	}
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var checkeds = treeObj.getNodes();
	var checked = treeObj.transformToArray(checkeds);
	var checkIds = "";
	var nocheckIds = "";
	$(checked).each(function() {
		if ($(this).is(':checked')) {
			var treeId = $(this).attr("id");
			checkIds += treeId + ",";
		} else {
			var treeId = $(this).attr("id");
			nocheckIds += treeId + ",";
		}
	});
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/roleResources/save.do",
		data : {
			roId : roId,
			checkIds : checkIds,
			nocheckIds : nocheckIds
		},
		async : false,// 同步方式
		success : function(con) {
			parent.layer.msg(con.data, {icon: 1,time: 500});
		}
	});
}
/*
 * 翻页操作
 */
var goPage = function(o) {
	flag = false;
	$("#treeDemo").html("");
	if (!o)
		o = 1;
	var start = (o - 1) * oParams.getPageSize();
	fLoadContents_V(start, oParams.getPageSize());
}
