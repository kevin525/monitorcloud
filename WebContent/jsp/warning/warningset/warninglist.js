var fBuildLogContentTr_V = function(con,i){
	var  isUse ="";
	var numisUse="";
	if(con.isUse == '0'){
		isUse="<span style='color:green'>启用</sapn>";
		numisUse="1";
	}else{
		isUse="<span style='color:red'>禁用</sapn>";
		numisUse="0";
	}
	
	var warningType = "";
	if(con.warningType == "server"){
		warningType = "服务器";
	}else if(con.warningType == "system"){
		warningType = "应用系统";
	}else if(con.warningType == "database"){
		warningType = "数据库";
	}else if(con.warningType == "middleware"){
		warningType = "中间件";
	}
	
	var sendWay = "";
	if(con.isSendEmail == 1){
		sendWay += "邮件"+" ";
	}
	
	if(con.isSendMessage == 1){
		sendWay += "短信"+" ";
	}
	
	if(con.isSendInform == 1){
		sendWay += "短消息"+" ";
	}
	
	var rule = "";
	if(con.minute != null){
		rule += "<div>&nbsp;事件发生后，间隔<span style='color:red;font-weight:bold;font-size:16px;'>"+con.minute+"</span>分钟后，再次发送告警;</div>";
	}
	
	if(con.count != null){
		rule += "<div>&nbsp;事件连续发生<span style='color:red;font-weight:bold;font-size:16px;'>"+con.count+"</span>次后，停止发送告警;</div>";
	}
	
	if(con.isNormal != null){
		rule += "<div>&nbsp;当发过告警监测点恢复正常时发送<span style='color:red;font-weight:bold;font-size:16px;'>1</span>次告警;</div>";
	}
	
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='systemstate' value="+con.id+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'>"+con.warningName+"</td>";
	html += "<td align='center'>"+warningType+"</td>" ;
	html += "<td align='left' style='line-height:25px;'>"+rule+"</td>" ;
	html += "<td align='center'>"+sendWay+"</td>" ;
	
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
	sUrl=Config.ROOT+"/warning/getWarningList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.warningName=$("input[name='sAppName']").val()==null?"":$("input[name='sAppName']").val();
	init();
}
function oneDelete(id){
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			    type : 'post',
			     url : Config.ROOT+"/warning/deleteWarning.do",
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
		title : '修改告警规则',
		shadeClose : true,
		area : [ '700px', '480px' ],
		content : 'jsp/warning/warningset/updateWarning.jsp?id=' + id
	});
};

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
					parent.layer.msg('您还未登录，请登录', {icon: 5,time: 500});
					top.window.location.href=Config.ROOT+"/login.jsp";
				}else if(con.data =="repeat"){
					parent.layer.msg('告警规则同类别只允许启用一个', {icon: 5,time: 500});
					
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
