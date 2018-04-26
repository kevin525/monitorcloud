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
	
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='systemstate' value="+con.id+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'>"+con.appName+"</td>";
	html += "<td align='center'>"+con.appIp+"</td>" ;
	html += "<td align='center'>"+con.dataBaseIp+"</td>" ;
	html += "<td align='center'>"+con.dateBaseType+"</td>" ;
	html += "<td align='center'>"+con.dataBaseName+"</td>" ;
	html += "<td align='center'>"+appNetStatus+"</td>" ;
	html += "<td align='center'>"+dataBaseNetStatus+"</td>" ;
	html += "<td align='center'><img src='../../images/"+appStatus+"' width='24' height='24' /></td>";
//	html += "<td align='center'><a href='javascript:changeIsUse("+con.isUse+")'>"+isUse+"</a></td>";
	html +="<td align='center'><a  href='javascript:void(0);' class='common_table_operate' style='margin-right:5px;'  onclick='getid("+con.id+")'>修改</a>" +
			"<a id="+ con.id+ " href='javascript:void(0);' class='common_table_operate' onclick='oneDelete(this.id)' style='margin-right:5px;' >删除</a>" +
					"<a  href='javascript:void(0);'   class='common_table_operate' onclick='changeIsUse("+con.id+","+numisUse+")'>"+isUse+"</a></td></tr>";
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
	 if(confirm("你确定要删除该条记录吗？")){
	$.ajax({
		type : 'post',
			url : Config.ROOT+"/appSystem/deleteAppSystem.do",
			data : {
				ids : id
		},
			success : function(con) {
				if(con.data=="ok"){
					alert("删除成功");
					init();
				}else{
					alert("删除失败");
				}
			}
		}); 
	 }
}
//修改
function getid(id) {
	parent.layer.open({
		type : 2,
		title : '修改系统',
		shadeClose : true,
		area : [ '600px', '90%' ],
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
					alert("您还未登录，请登录！");
					top.window.location.href=Config.ROOT+"/login.jsp";
				}else{
					alert("服务器出错");
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