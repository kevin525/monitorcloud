$(function(){
	/*$.ajax({
	    type: "POST",
	    url:Config.ROOT+"/docNotReceive/docNotReceive.do",
	    dataType : "json",
	    success: function(data) {
	    	alert(data);
	       bulidMessageLogContent(data);
	    }
	});*/
	init();
})

/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=10;
	sUrl=Config.ROOT+"/docNotReceive/getNotReceiveList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

var fBuildLogContentTr_V = function(con,i){
	var html = "";
	var sNum=oParams.start+i+1;
	var id = con.id;
	var docId = con.docId==null?"":con.docId;
	var freeflowId = con.freeflowId==null?"":con.freeflowId;
	var docTitle = con.docTitle==null?"":con.docTitle;
	var sendUser = con.sendUser==null?"":con.sendUser;
	var receiveUser = con.receiveUser==null?"":con.receiveUser;
	var docYear = con.docYear==null?"":con.docYear;
	var docNum = con.docNum==null?"":con.docNum;
	var createDate = con.createDate==null?"":con.createDate;
	var status = con.status;
	var op = "";
	if(status == "0"){
		op = "<a href='javascript:void(0);' class='common_table_operate' style='margin-right:5px;'  onclick='updateDocStatus("+id+",1)'>处理</a>";
	}else{
		op = "<a href='javascript:void(0);' class='common_table_operate' style='margin-right:5px;color:green;'>已处理</a>"
	}
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='status' value="+con.id+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'>"+docId+"</td>";
	html += "<td align='center'>"+freeflowId+"</td>" ;
	html += "<td align='left'>"+docTitle+"</td>" ;
	html += "<td align='center'>"+sendUser+"</td>" ;
	html += "<td align='center'>"+receiveUser+"</td>" ;
	html += "<td align='center'>"+docYear+"</td>" ;
	html += "<td align='center'>"+docNum+"</td>" ;
	html += "<td align='center'>"+createDate+"</td>" ;
	html +="<td align='center'>"+op+"<a href='javascript:void(0);' class='common_table_operate' onclick='oneDelete("+id+")' style='margin-right:5px;' >删除</a></td></tr>";
	return html;
};


function oneDelete(id){
	if(confirm("你确定要删除该条记录吗？")){
		$.ajax({
			type : 'post',
				url : Config.ROOT+"/docNotReceive/deleteDocNotReceive.do",
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

function updateDocStatus(id,value){
	var params={};
	params.id = id;
	params.val = value;
    $.ajax({
			type : 'post',
			url : Config.ROOT+"/docNotReceive/updateDocStatus.do",
			data : params,
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
	var name = document.getElementsByName("status");
	for (var i = 0; i < name.length; i++) {
		name[i].checked = a.checked;
	}
}


/**
 * 构建公文记录信息
 * @param message
 */
function bulidMessageLogContent(messageInfo){
	var messageList = $("#messageList");
	var content = '';
	if(messageInfo.length > 0){
		$(messageInfo).each(function (i) {
		     if(i%2==0){
		          content +='<tr bgcolor="#ffffff">';
		     }else{
		    	  content +='<tr bgcolor="#f8f8f8">';
		     }
		     var subtitle = this.title;
		     if(subtitle.length >25){
		    	 subtitle = subtitle.substring(0,25)+"...";
		     }
		     content +='<td align="center">'+(i+1)+'</td>';
		     content +='<td align="center">'+this.id+'</td>';
		     content +='<td align="center">'+this.flowRelationId+'</td>';
			 content +='<td align="left" style="padding: 0 3px;" title="'+this.title+'">'+subtitle+'</td>';
			 content +='<td align="center">'+this.sendUser+'</td>';
			 content +='<td align="center">'+getLeaderName(this.receiveUnit)+'</td>';
			 content +='<td align="center">'+this.docYear+'</td>';
			 content +='<td align="center">'+this.docNum+'</td>';
		     content +='</tr>'; 
		});
	}else{
		 content +='<tr bgcolor="#ffffff">';
	     content +='<td colspan="8" align="center"><span style="color:#0b70dc">没有收不到的公文!</span></td>';
	     content +='</tr>'; 
	}
	
	messageList.html(content);
}


function formatDate(optime){
	var year = optime.substring(0,4);
	var month = optime.substring(4,6);
	var day = optime.substring(6,8);
	var hour = optime.substring(8,10);
	var minute = optime.substring(10,12);
	var second = optime.substring(12,14);
	return year+"-"+month+"-"+day +" "+hour+":"+minute+":"+second;
}

function delHtmlTag(str){
	  var formatStr = str.replace(/<[^>]+>/g,"");//去掉所有的html标记
	  formatStr = formatStr.replace(/(^\s+)|(\s+$)/g,"");
	  formatStr = formatStr.replace(/&nbsp;/ig, "");
	  formatStr = formatStr.replace(/\s/g,"");    
  return formatStr;
}

function escape2Html(str) {
	 var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
	 return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
} 


