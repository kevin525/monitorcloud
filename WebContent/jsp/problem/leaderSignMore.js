function searchMoreInfo() {
	var url = Config.ROOT+"/leaderSignMore/getLeaderSignMore.do";
	$.ajax({
	    type: "POST",
	    url:url,
	    data:{
	    	'flowId':$("#flowId").val(),
	    	'docYear':$("#docYear").val(),
	    	'docNum':$("#docNum").val(),
	    },
	    dataType : "json",
	    success: function(data) {
	       buildDocApprovalContent(data.docApprovalList);
	       bulidMessageLogContent(data.messageLogList);
	       bulidFlowContent(data.freeFlowList);
	    }
	});
}

/**
 * 构建公文列表信息
 * @param approval
 */
function buildDocApprovalContent(approvalInfo){
	var approvalList = $("#approvalList");
	var content = '';
	if(approvalInfo.length > 0){
		$(approvalInfo).each(function (i) {
		     if(i%2==0){
		          content +='<tr bgcolor="#ffffff">';
		     }else{
		    	  content +='<tr bgcolor="#f8f8f8">';
		     }
		     var subtitle = this.title;
		     if(subtitle.length >25){
		    	 subtitle = subtitle.substring(0,25)+"...";
		     }
		     
		     content +='<td align="center">'+this.id+'</td>';
		     content +='<td align="center">'+this.flowRelationId+'</td>';
			 content +='<td align="left" style="padding: 0 3px;" title="'+this.title+'">'+subtitle+'</td>';
			 content +='<td align="center">'+this.sendUser+'</td>';
			 content +='<td align="center">'+getLeaderName(this.receiveUnit)+'</td>';
			 content +='<td align="center"><input type="text" id="endTime'+this.id+'" style="width:120px;" value="'+(this.endTime==null?'':this.endTime)+'"></td>';
			 content +='<td align="center"><input type="text" id="modifyDate'+this.id+'" style="width:120px;" value="'+(this.modifyDate==null?'':this.modifyDate)+'"></td>';
			 content +='<td align="center">'+this.docYear+'</td>';
			 content +='<td align="center">'+this.docNum+'</td>';
			 content +='<td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right: 5px;" onclick="saveRecord('+this.id+')">保存</a></td>';
		     content +='</tr>'; 
		});
	}else{
		 content +='<tr bgcolor="#ffffff">';
	     content +='<td colspan="10" align="center"><span style="color:#0b70dc">没有满足条件的数据!</span></td>';
	     content +='</tr>'; 
	}
	
	
	approvalList.html(content);
	
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
		     content +='<td align="center">'+this.id+'</td>';
		     content +='<td align="center">'+this.flowRelationId+'</td>';
			 content +='<td align="left" style="padding: 0 3px;" title="'+this.title+'">'+subtitle+'</td>';
			 content +='<td align="center">'+this.sendUser+'</td>';
			 content +='<td align="center">'+getLeaderName(this.receiveUnit)+'</td>';
			 content +='<td align="center">'+this.completeTime+'</td>';
			 content +='<td align="center">'+this.docYear+'</td>';
			 content +='<td align="center">'+this.docNum+'</td>';
		     content +='</tr>'; 
		});
	}else{
		 content +='<tr bgcolor="#ffffff">';
	     content +='<td colspan="8" align="center"><span style="color:#0b70dc">没有满足条件的数据!</span></td>';
	     content +='</tr>'; 
	}
	
	messageList.html(content);
}

/**
 * 构建流程记录信息
 * @param flowInfo
 */
function bulidFlowContent(flowInfo){
	var flowList = $("#flowList");
	var content = '';
	if(flowInfo.length > 0){
		$(flowInfo).each(function (i) {
		     if(i%2==0){
		          content +='<tr bgcolor="#ffffff">';
		     }else{
		    	  content +='<tr bgcolor="#f8f8f8">';
		     }
		     var subtitle = this.title;
		     if(subtitle.length >25){
		    	 subtitle = subtitle.substring(0,25)+"...";
		     }
		     
		     var optime = this.start_time;
		     optime = formatDate(optime);
		     var operator = getLeaderName(this.author_name);
		     var operateName = operator+"（"+this.operate_name+"）";
		     var opinion = this.opinion;
		     if(opinion != null && opinion !=''){
			     opinion = escape2Html(opinion);
                 opinion = delHtmlTag(opinion );
		     }
		     
		     content +='<td align="center">'+optime+'</td>';
		     content +='<td align="left">&nbsp;&nbsp;'+operateName+'</td>';
			 /*content +='<td align="left">&nbsp;&nbsp;'+opinion+'</td>';*/
			 content +='<td align="center"><a href="javascript:void(0);" class="common_table_operate" style="margin-right: 5px;" onclick="deleteRecord('+this.id+',&quot;flow&quot;)">删除</a></td>';
		     content +='</tr>';  
		});
	}else{
		 content +='<tr bgcolor="#ffffff">';
	     content +='<td colspan="3" align="center"><span style="color:#0b70dc">没有满足条件的数据!</span></td>';
	     content +='</tr>'; 
	}
	
	flowList.html(content);

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
/**
 * 删除记录
 * @param id
 */
function deleteRecord(id,type) {
	if(window.confirm('您确定要删除吗？')){
		$.ajax({
		    type:'POST',
		    url:Config.ROOT+"/leaderSignMore/delSignOne.do",
		    data: {
		    	  'id': id,
		    	  'type':type,
		    	  'flowId':$("#flowId").val()
		     },
            dataType: "json",
            success: function (data) {
            	alert("删除成功!");
            	searchMoreInfo();
            }
		});
     }else{
        return false;
     }
}

/**
 * 修改公文的结束时间和修改时间
 * @param id
 */
function saveRecord(id){
	var endTime = $("#endTime"+id+"").val();
	var modifyDate = $("#modifyDate"+id+"").val();
	$.ajax({
	    type:'POST',
	    url:Config.ROOT+"/leaderSignMore/saveSignMore.do",
	    data: {
	    	  'id': id,
	    	  'endTime':endTime,
	    	  'modifyDate':modifyDate,
	    	  'flowId':$("#flowId").val()
	     },
        dataType: "json",
        success: function (data) {
        	alert("保存成功!");
        	searchMoreInfo();
        }
	});
}

function copySignPic(){
	if(window.confirm('您确定要处理图片吗？')){
		$.ajax({
		    type: "POST",
		    url:Config.ROOT+"/copySignPic/copySignPic.do",
		    data:{
		    	'flowId':$("#flowId").val(),
		    	'docYear':$("#docYear").val(),
		    	'docNum':$("#docNum").val()
		    },
		    dataType : "json",
		    success: function(data) {
		    	if(data.data =='success'){
		    		alert("图片处理成功!");
		    	}else{
		    		alert("图片处理失败!");
		    	}
		    }
		});
	}
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

