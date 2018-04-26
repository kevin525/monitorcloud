var fBuildLogContentTr_V = function(con,i){
	var personState="";
	if(con.personState==0){
		personState="是";
	}else{
		personState="否";
	}
	var sex="";
	if(con.sex==0){
		sex="男";
	}else{
		sex="女";
	}
	
	var role="";
	if(con.role ==null){
		role ="无"
	}else{
		role =con.role.roleName;
	}
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.personId+"'  class='aaa'  bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='n' value="+con.personId+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>" +
			"<td align='center'>"+con.personName+"</td>" +
			"<td align='center'>"+role+"</td>" +
			"<td align='center'>"+sex+"</td>" +
			"<td align='center'>"+con.loginName+"</td>" +
			"<td align='center'>"+con.personTel+"</td>" +
			"<td align='center'>"+con.createDate+"</td>" +
			"<td align='center'>"+personState+"</td>";
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='editUser("+con.userId+")'>修改</a>";
	html +="<a id="+ con.personId+ " class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='oneDelete(this.id)'>删除</a>";
	html +="</td></tr>";
			
	return html;
};



//添加用户
function addUser(){
	parent.layer.open({
		type : 2,
		title : '新增用户',
		shadeClose : true,
		area : [ '700px', '380px' ],
		content : 'jsp/user/addUser.jsp'
	});
	
}
/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=8;
	sUrl=Config.ROOT+"/user/userList2.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.start=0;
	oParams.departmentId=deptId;
	oParams.loginName=$("input[name='loginName']").val()==null?"":$("input[name='loginName']").val();
	oParams.loginPwd=$("input[name='loginPwd']").val()==null?"":$("input[name='loginPwd']").val();
	sUrl=Config.ROOT+"/user/userList2.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}
//修改
function editUser(id) {
	parent.layer.open({
		type : 2,
		title : '修改用户',
		shadeClose : true,
		area : [ '600px', '350px' ],
		content : 'jsp/user/updateUser.jsp?id=' + id
	});
};

//控制全选
function qx(a) {
	var name = document.getElementsByName("n");
	for (var i = 0; i < name.length; i++) {
		name[i].checked = a.checked;
	}
}

//确定删除
function oneDelete(id) {
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
			url : Config.ROOT+"/user/oneDelete.do",
				data : {
					personId : id
			},
				success : function(con) {
					if(con=="success"){
						parent.layer.msg('删除成功', {icon: 1,time: 500});
						init();
					}else{
						parent.layer.msg('删除失败', {icon: 2,time: 500});
					}
				}
			});
	});
	
}

//单个删除
function okDelete(id) {
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
			url : Config.ROOT+"/user/oneDelete.do",
				data : {
					personId : id
			},
				success : function(con) {
					console.log(con);
					if(con=="success"){
						parent.layer.msg('删除成功', {icon: 1,time: 500});
						init();
					}else{
						parent.layer.msg('删除失败', {icon: 2,time: 500});
					}
				}
			});
	});
}

//查询
function doSelect() {
	var name = $("#psname").val();
	if (trimStr(name) == "") {
		alert("用户名不能为空");
		$("#psname").val("");
		return;
	}
	oParams.start=0;
	oParams.pageSize=8;
	oParams.queryPara=name;
	sUrl=Config.ROOT+"/user/oneSelect.do",
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

//去空格
function trimStr(str){
	str =str.replace(/(^\s*)|(\s*$)/g,"");
	return str;
	}
//批量删除
function dodelete() {
	var str = "";
	$("input[name='n']:checked").each(function() {
		str += $(this).val() + ",";
	})
	
	if(str==""){
		parent.layer.confirm('请选择要删除的记录！', {
			  btn: ['关闭'] //按钮
		});
	}else{
		parent.layer.confirm('您确定要删除该条记录吗？', {
			  btn: ['确定','取消'] //按钮
		}, function(){
			  $.ajax({
					type : 'post',
					url :  Config.ROOT+"/user/delete.do",
		 			data : {
		 				str : str
					},
					success : function(con) {
						if(con=="success"){
							parent.layer.msg('删除成功', {icon: 1,time: 500});
							init();
						}else{
							parent.layer.msg('删除失败', {icon: 2,time: 500});
						}
					}
				});
		});
	}
	
}
