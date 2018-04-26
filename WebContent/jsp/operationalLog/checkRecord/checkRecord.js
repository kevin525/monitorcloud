var fBuildLogContentTr_V = function(con,i){
	
	
	var  environment ="";
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
	html += "<td align='center'>"+con.personLiable+"</td>" ;
	html += "<td align='center'>"+environment+"</td>" ;
	html += "<td align='center'>"+con.createDate+"</td>" ;
	html += "<td align='center'>"+(con.remark==null?"":con.remark)+"</td>" ;
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='showRecord("+con.id+")'>查看</a>";
	html +="<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='oneDelete("+con.id+")'>删除</a>";
	html +="</td></tr>";
	
	return html;
};

/*
 * 用户界面初始化
 */
function getRecord(){
	
	//巡检日志
	oParams.start=0;
	oParams.pageSize=6;
	oParams.isUse="0";
	sUrl=Config.ROOT+"/opsCheckRecord/getRecordListByPage.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
	
}
function getModel(){
	var model={};
	model.isUse="0";
	model.nodeValue="1";
	$.ajax({
		type : 'post',
		url : Config.ROOT+"/opsCheckRecordModel/getModelList.do",
		data : model,
		async: false,
		success : function(con) {
			var contents = con.pageData;
			var html="";
			$("#mbody_from").html("");
			if (contents != null) {
				$(contents).each(function(j){
					var model2={};
					model2.isUse="0";
					model2.nodeValue="2";
					model2.fatherId=contents[j].id;
					$.ajax({
						type : 'post',
						url : Config.ROOT+"/opsCheckRecordModel/getModelList.do",
						data : model2,
						async: false,
						success : function(con) {
							var data = con.pageData;
							if (data != null) {
								$(data).each(function(i){
									if(i==0){
										html+="<tr ><td class='tableleft' align='center'  width='300' rowspan="+data.length+"  style='font-weight:bold;'>"+contents[j].name+"</td>"
										html += "<td width='300'  >"+data[i].name+"</td>" ;
										html += "<td ><input type='radio' name="+data[i].id+" value='0' checked='checked'>正常&nbsp;";
										html +=	"<input type='radio' name="+data[i].id+" value='1'>异常</td>" ;
										html += "</tr>";
									}else{
										
										html += "<tr><td  class='tableleft'  width='300'>"+data[i].name+"</td>" ;
										html += "<td ><input type='radio' name="+data[i].id+" value='0' checked='checked'>正常&nbsp;";
										html +=	"<input type='radio' name="+data[i].id+" value='1'>异常</td>" ;
										html += "</tr>";
									}
								});
									
							}else{
								html += "<tr><td class='tableleft' align='center' style='font-weight:bold;'>"+contents[j].name+"</td>" ;
								html += "<td colspan='2' ><input type='radio' name="+contents[j].id+" value='0' checked='checked'>正常&nbsp;";
								html +=	"<input type='radio' name="+contents[j].id+" value='1'>异常</td>" ;
								html += "</tr>";
							}
						}
					});
				});
				$("#mbody_from").append(html);
				
			}
		}
	});
	
	
}
function add(){
	var normal = "";
	var abnormal = "";
	$("input[type=radio]:checked").each(function(i){
		var val = $(this).val();
		var id = $(this).attr("name");
		if(id !="environment"){
			if(val =="0"){
				normal += id+",";
			}else if(val == "1"){
				abnormal += id+",";
			}
		}
	});
	$.ajax({
		type : 'post',
		url : Config.ROOT+"/opsCheckRecord/saveOrUpdateInfo.do",
		data : {
			normal : normal,
			abnormal : abnormal,
			environment : $("input[name=environment]:checked").val(),
			name : $("#name").val(),
			remark : $("#remark").val()
		},
			success : function(con) {
				if(con.data=="ok"){
					alert("提交成功");
					
				}else{
					alert("提交失败");
				}
			}
		}); 
}
function searchLoginLog(){
	oParams.name=$("input[name='sAppName']").val()==null?"":$("input[name='sAppName']").val();
	init();
}
function showRecord(id){
	/*parent.layer.open({
		type : 2,
		title : '运维记录',
		shadeClose : true,
		area : [ '750px', '80%' ],
		content : Config.ROOT+'/jsp/operationalLog/checkRecord/showCheckRecord.jsp?id='+id
	});*/
	window.open(Config.ROOT+"/jsp/operationalLog/checkRecord/showCheckRecord.jsp?id="+id);
	
}

function oneDelete(id){
	if(confirm("你确定要删除该条记录吗？")){
		$.ajax({
			type : 'post',
			url : Config.ROOT+"/opsCheckRecord/delete.do",
			data : {
				ids : id
			},
			success : function(con) {
				if(con.data=="ok"){
					alert("删除成功");
					getRecord();
				}else{
					alert("删除失败");
				}
			}
		}); 
	 }
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
