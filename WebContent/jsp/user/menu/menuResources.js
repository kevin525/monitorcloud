var fBuildLogContentTr_V = function(con,i){
	var  isUse ="";
	var resourcesState="";
	if(con.resourcesState == '1'){
		isUse="<span style='color:green'>启用</sapn>";
		resourcesState="0";
	}else{
		isUse="<span style='color:red'>禁用</sapn>";
		resourcesState="1";
	}
	
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='systemstate' value="+con.resourcesId+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'>"+con.resourcesName+"</td>";
	html += "<td align='center'>"+con.resoucesCode+"</td>" ;
	html += "<td align='center'>"+(con.resourcesUrl==null?'':con.resourcesUrl)+"</td>" ;
	html += "<td align='center'>"+(con.nodeValue==1?'一级菜单':'二级菜单')+"</td>" ;
	
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='getid("+con.resourcesId+")'>修改</a>";
	html +="<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='oneDelete("+con.resourcesId+")'>删除</a>";
	html +="<a class='layui-btn layui-btn-primary layui-btn-xs' lay-event='del' class='common_table_operate' onclick='changeIsUse("+con.resourcesId+","+resourcesState+")'>"+isUse+"</a>";
	html +="</td></tr>";
	
	return html;
};

/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=10;
	sUrl=Config.ROOT+"/menuResource/getMenuResourceList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.resourcesName=$("input[name='sAppName']").val()==null?"":$("input[name='sAppName']").val();
	init();
}
function oneDelete(id){
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
			     url : Config.ROOT+"/menuResource/deleteMenuResource.do",
				data : {
					ids : id
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
//修改
function getid(id) {
	parent.layer.open({
		type : 2,
		title : '修改菜单',
		shadeClose : true,
		area : [ '600px', '70%' ],
		content : 'jsp/user/menu/updateMenuResource.jsp?id=' + id
	});
};

function changeIsUse(id,isUse){
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/menuResource/setUseStatus.do",
			data : {
				id:id,
				val:isUse
			},
			success : function(con) {
				if(con.data=="ok"){
					init();
				}else if(con.data=="noLogin"){
					parent.layer.msg('您还未登录，请登录', {icon: 5,time: 500});
					top.window.location.href=Config.ROOT+"/login.jsp";
				}else{
					parent.layer.msg('服务器出错', {icon: 2,time: 500});
				}
			}
		}); 
}
var getCheckedValue = function(name){
	var values = new Array();
	$("input[name='"+name+"']").each(function(){
		if(this.checked=='checked' || this.checked==true){
			if(this.value)
 	            values.push(this.value);
		}
    })
   return values.toString();
};
//控制全选
function qx(a) {
	var name = document.getElementsByName("systemstate");
	for (var i = 0; i < name.length; i++) {
		name[i].checked = a.checked;
	}
}
/*function appNameCheck(){
	var appName= $("input[name='appName']").val();
	//首先不能为空
	if(!trimStr(appName)){
		//$("#appName").after("*用户名不能为空");
		//$("#appName").css("color","red");
		return false;
	}
}

//去空格
function trimStr(str){
	str =str.replace(/(^\s*)|(\s*$)/g,"");
	return str;
	}*/