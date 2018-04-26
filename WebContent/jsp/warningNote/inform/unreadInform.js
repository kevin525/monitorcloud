var fBuildLogContentTr_V = function(con,i){
	
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='systemstate' value="+con.id+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px; line-height:20px;'>"+con.content+"</td>";
	html += "<td align='center'>"+con.sendDate+"</td>" ;
	html +="<td align='center'>";
//	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='showWarnDesc("+con.id+")'>处理结果</a>";
	html +="<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='readInform("+con.id+")'>已读</a>";
	html +="</td></tr>";
	return html;
};

/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=8;
	oParams.readStatus="1";
	sUrl=Config.ROOT+"/inform/getInformSuccessfulList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
	$("#newMessage",window.parent.document).html(oParams.totalCount);
}

function searchLoginLog(){
	oParams.content=$("input[name='sAppName']").val()==null?"":$("input[name='sAppName']").val();
	init();
}

function readInform(id){
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/inform/setInformRead.do",
			data : {
				id:id
			},
			success : function(con) {
				if(con.data=="ok"){
					init();
					parent.window.iframe.init();
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
