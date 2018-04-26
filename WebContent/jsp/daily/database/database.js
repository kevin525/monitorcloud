var fBuildLogContentTr_V = function(con,i){
	
	var dataBaseNetStatus="";
	if(con.dataBaseNetStatus=='0'){
		dataBaseNetStatus="正常";
		appStatus="light_normal.png";
	}else{
		dataBaseNetStatus="<font color='red'>异常</font>";
		appStatus="light_red.png";
	}


	var  isUse ="";
	var numisUse="";
	if(con.isUse == '0'){
		isUse="<span style='color:green'>启用</sapn>";
		numisUse="1";
	}else{
		isUse="<span style='color:red'>禁用</sapn>";
		numisUse="0";
	}
	var environment="";
	if(con.environment == '0'){
		environment="正式环境";
	}else{
		environment="演示环境";
	}
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='systemstate' value="+con.id+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'>"+con.name+"</td>";
	html += "<td align='center'>"+con.dataBaseIp+"</td>" ;
	html += "<td align='center'>"+con.dataBaseType+"</td>" ;
	
	html += "<td align='center'>"+environment+"</td>" ;
	html += "<td align='center'>"+dataBaseNetStatus+"</td>" ;
	
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='editDatabase("+con.id+")'>修改</a>";
	html +="<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='oneDelete("+con.id+")'>删除</a>";
	html +="<a class='layui-btn layui-btn-primary layui-btn-xs' lay-event='del' class='common_table_operate' onclick='changeIsUse("+con.id+","+numisUse+")'>"+isUse+"</a>";
	html +="</td></tr>";
	return html;
};

/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=10;
	sUrl=Config.ROOT+"/database/getDatabaseList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.name=$("input[name='sAppName']").val()==null?"":$("input[name='sAppName']").val();
	oParams.dataBaseIp=$("input[name='sAppIp']").val()==null?"":$("input[name='sAppIp']").val();
	oParams.dataBaseType=$("select[name=sDatabaseType]").val()==null?"":$("select[name=sDatabaseType]").val();
	init();
}
function oneDelete(id){
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
				url : Config.ROOT+"/database/delete.do",
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
function editDatabase(id) {
	parent.layer.open({
		type : 2,
		title : '修改数据库',
		shadeClose : true,
		area : [ '700px', '85%' ],
		content : 'jsp/daily/database/updateDatabase.jsp?id=' + id
	});
};

function changeIsUse(id,isUse){
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/database/setUse.do",
			data : {
				id:id,
				val:isUse
			},
			success : function(con) {
				if(con.data=="ok"){
					init();
				}else if(con.data=="noLogin"){
					parent.layer.msg('您还未登录，请登录', {icon: 2,time: 500});
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
