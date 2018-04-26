var fBuildLogContentTr_V = function(con,i){
	var roleState="";
	if(con.roleState==1){
		roleState="启用";
	}else{
		roleState="禁用";
	}
	
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.roleId+"'  class='aaa'  bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='roleName' value="+con.roleId+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='center'>"+con.roleName+"</td>";
	html +=	"<td align='center'>"+con.roleCreatTime+"</td>";
	html +=	"<td align='center'>"+roleState+"</td>";
	
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='getid("+con.roleId+")'>修改</a>";
	html +="<a id="+ con.roleId+ " class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='oneDelete(this.id)'>删除</a>";
	html +="</td></tr>";
	return html;
};


/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=10;
	sUrl=Config.ROOT+"/role/getPageRoleDataAndView.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.roleName=$("input[name='roleName']").val()==null?"":$("input[name='roleName']").val();
	init();
}

function oneDelete(id){
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
			     url : Config.ROOT+"/role/deleteRole.do",
				data : {
					roleId : id
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

function getid(roleId) {
	parent.layer.open({
		type : 2,
		title : '修改角色',
		shadeClose : true,
		area : [ '550px', '35%' ],
		content : 'jsp/user/role/updateRole.jsp?roleId=' + roleId
	});
};

