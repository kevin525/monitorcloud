var fBuildLogContentTr_V = function(con,i){
	
	
	var  nodeValue="";
	if(con.nodeValue=="1"){
		nodeValue="一级巡检";
	}else if (con.nodeValue=="2"){
		nodeValue="二级巡检";
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
	html +=	"<td align='left'  style='padding:0 3px;'>"+con.name+"</td>";
	html += "<td align='center'>"+con.shortName+"</td>" ;
	html += "<td align='center'>"+con.ownerName+"</td>" ;
	html += "<td align='center'>"+nodeValue+"</td>" ;
	html += "<td align='center'>"+con.modelOrder+"</td>" ;
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='editModel("+con.id+")'>修改</a>";
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
	oParams.pageSize=6;
	sUrl=Config.ROOT+"/opsCheckRecordModel/getModelListByPage.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}
function searchLoginLog(){
	oParams.name=$("input[name='sAppName']").val()==null?"":$("input[name='sAppName']").val();
	init();
}
function oneDelete(id){
	 if(confirm("你确定要删除该条记录吗？")){
	$.ajax({
		type : 'post',
			url : Config.ROOT+"/opsCheckRecordModel/deleteModel.do",
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
function editModel(id) {
	parent.layer.open({
		type : 2,
		title : '修改巡检项',
		shadeClose : true,
		area : [ '750px', '80%' ],
		content : Config.ROOT+'/jsp/operationalLog/checkRecord/editCheckRecordModel.jsp?id=' + id,
		end: function () {
            init();
        }
	});
};

function changeIsUse(id,isUse){
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/opsCheckRecordModel/setUse.do",
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
