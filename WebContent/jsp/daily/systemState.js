var fBuildLogContentTr_V = function(con,i){
	var appNetStatus="";
	if(con.appNetStatus=='0'){
		appNetStatus="正常";
	}else{
		appNetStatus="<font color='red'>异常</font>";
	}
	
	var dataBaseNetStatus="";
	if(con.dataBaseNetStatus=='0'){
		dataBaseNetStatus="正常";
	}else{
		dataBaseNetStatus="<font color='red'>异常</font>";
	}
	var appStatus="";
	if(con.appStatus=='0'){
		appStatus="light_normal.png";
	}else{
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
	var name ="";
	if(con.dataBaseType=="oracle"){
		name=con.dataBaseIns;
	}else{
		name=con.dataBaseName;
	}
	var env="";
	if(con.environment=="0"){
		env="正式环境";
	}else{
		env="演示环境";
	}
	
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='systemstate' value="+con.id+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'>"+con.appName+"</td>";
	html += "<td align='center'>"+con.appIp+"</td>" ;
	html += "<td align='center'>"+con.dataBaseIp+"</td>" ;
	html += "<td align='center'>"+con.dataBaseType+"</td>" ;
	html += "<td align='center'>"+(con.dataBaseName==null?'':con.dataBaseName)+"</td>" ;
	html += "<td align='center'>"+appNetStatus+"</td>" ;
	html += "<td align='center'>"+dataBaseNetStatus+"</td>" ;
	html += "<td align='center'>"+env+"</td>" ;
	
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='editSystemState("+con.id+")'>修改</a>";
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
	sUrl=Config.ROOT+"/appSystem/getAppSystemList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.appName=$("input[name='sAppName']").val()==null?"":$("input[name='sAppName']").val();
	oParams.appIp=$("input[name='sAppIp']").val()==null?"":$("input[name='sAppIp']").val();
	init();
}
function oneDelete(id){
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
				url : Config.ROOT+"/appSystem/deleteAppSystem.do",
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
function editSystemState(id) {
	parent.layer.open({
		type : 2,
		title : '修改系统',
		shadeClose : true,
		area : [ '800px', '80%' ],
		content : 'jsp/daily/updateSystemState.jsp?id=' + id
	});
};

function changeIsUse(id,isUse){
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/appSystem/setAppSystemUse.do",
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
					parent.layer.msg('服务器出错', {icon:2,time: 500});
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
