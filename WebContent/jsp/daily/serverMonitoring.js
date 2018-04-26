var fBuildLogContentTr_V = function(con,i){
	
	
	
	var status="";
	if(con.status=='0'){
		status="light_normal.png";
	}else{
		status="light_red.png";
	}
	var environment="";
	if(con.environment=='0'){
		environment="正式环境";
	}else{
		environment="测试环境";
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
	
	
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='servername' value="+con.id+" ></td>";
	html +=	"<td align='center' width='25px'>"+sNum+"</td>";
	html +=	"<td align='left' width='160px'>&nbsp;"+con.name+"</td>";
	html += "<td align='center' width='160px'>"+con.ip+"</td>" ;
	html += "<td align='center' width='150px'>"+con.model+"</td>" ;
	html += "<td align='center' width='130px'>"+environment+"</td>" ;
	
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='getid("+con.id+")'>修改</a>";
	html +="<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='oneDelete("+con.id+")'>删除</a>";
	html +="<a class='layui-btn layui-btn-primary layui-btn-xs' lay-event='del' id="+con.id+","+con.name+","+con.ip+","+con.model+","+con.cpu+","+con.memorySize+","+con.hardDiskSize+","+con.availableHDSzie+","+con.osName+","+con.personLiable+","+con.serverOrder+","+con.environment+","+con.status+","+con.isUse+" class='common_table_operate' onclick='changeIsUse(this.id,"+numisUse+")'>"+isUse+"</a>";
	html +="</td></tr>";
	return html;
};

/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=10;
	sUrl=Config.ROOT+"/server/getServerList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.name=$("input[name='serverName']").val()==null?"":$("input[name='serverName']").val();
	oParams.ip=$("input[name='serverIp']").val()==null?"":$("input[name='serverIp']").val();
	init();
}
function oneDelete(id){
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
				url : Config.ROOT+"/server/deleteServer.do",
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
		title : '服务器',
		shadeClose : true,
		area : [ '900px', '75%' ],
		content : 'jsp/daily/updateServerMonitoring.jsp?id=' + id
				
	});
};

function changeIsUse(sourceStr,isUse){
	var con = sourceStr.split(",");
	var params={};
	params.id = con[0];
	params.val = isUse;
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/server/setServerUse.do",
			data : params,
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
	var name = document.getElementsByName("servername");
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