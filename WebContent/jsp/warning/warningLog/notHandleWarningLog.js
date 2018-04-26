var fBuildLogContentTr_V = function(con,i){
	var  isUse ="";
	var numisUse="";
	if(con.flag == '1'){
		isUse="<span style='color:green'>已处理</sapn>";
		numisUse="0";
	}else{
		isUse="<span style='color:red'>未处理</sapn>";
		numisUse="1";
	}
	
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='systemstate' value="+con.id+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'>"+con.monitorName+"</td>";
	html += "<td align='left' style='line-height:20px;'>"+con.warnName+"</td>" ;
	html += "<td align='center'>"+(con.warnWay==null?'':con.warnWay)+"</td>" ;
	html += "<td align='center'>"+con.warnTime+"</td>" ;
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='editSystemState("+con.id+")'>处理</a>";
	html +="<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='oneDelete("+con.id+")'>删除</a>";
	html +="</td></tr>";
	return html;
};

/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=8;
	oParams.flag ="0";
	sUrl=Config.ROOT+"/warningLog/getWarningLogList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.warnName=$("input[name='sAppName']").val()==null?"":$("input[name='sAppName']").val();
	init();
}
function oneDelete(id){
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
				url : Config.ROOT+"/warningLog/deleteWarningLog.do",
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
		title : '处理告警信息',
		shadeClose : true,
		area : [ '700px', '500px' ],
		content : 'jsp/warning/warningLog/updateWarningLog.jsp?id=' + id
	});
};
function showWarnDesc(desc){
	alert(desc);
}
function changeIsUse(id,isUse){
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/warning/setWaringUse.do",
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
				}else if(con.data =="repeat"){
					alert("告警规则同类别只允许启用一个！");
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
