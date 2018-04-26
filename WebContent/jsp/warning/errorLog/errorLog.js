$(function(){
	init();
	 $("#delete_pl").click(function(){
	    	var checkval = getCheckedValue("errorLogId_D");
	    	if(!checkval || checkval.length<1){
	    		parent.layer.confirm('请选择要删除的记录！', {
	    			  btn: ['关闭'] //按钮
	    		});
	    	}else{
	    		parent.layer.confirm('您确定要删除该条记录吗？', {
	    			  btn: ['确定','取消'] //按钮
	    		}, function(){
	    			  $.ajax({
	    					type : 'post',
		    				url : Config.ROOT+"/warningLog/deleteErrorLog.do",
				 			data : {
				 				ids : checkval
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
	    });
	 
	 $("#errorLogNameUl li").click(function(){
		 $(this).addClass("layui-this");
		 $(this).siblings('li').removeClass();
		 var str = $(this).text();
		 oParams.errorName = str;
		 if(str == "所有"){
			 oParams.errorName = null;
		 }
		 init();
	 })
	 
	 $("#allCK").click(function(){
		 if($(this).is(":checked")){
			 $("input[name='errorLogId_D']").prop("checked","checked");
		 }else{
			 $("input[name='errorLogId_D']").removeAttr("checked")
		 }
	 });
});

var fBuildLogContentTr_V = function(con,i){
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr id='_tr"+con.id+"'   bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox' name='errorLogId_D' value="+con.id+" ></td>";
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'><a href='javascript:void(0)' onclick='errorLogDetail("+con.id+")'>"+con.errorTitle+"</a></td>";
	html += "<td align='center'>"+con.serverIp+"</td>" ;
	html += "<td align='center'>"+con.errorTime+"</td>" ;
	html +="<td align='center'>";
	html +="<a class='layui-btn layui-btn-xs' lay-event='edit' onclick='errorLogDetail("+con.id+")'>详情</a>";
	html +="<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del' onclick='oneDelete("+con.id+")'>删除</a>";
	html +="</td>";
	html +="</tr>";
	return html;
};

/*
 * 用户界面初始化
 */
function init(){
	$("#allCK").removeAttr("checked");
	oParams.start=0;
	oParams.pageSize=8;
	sUrl=Config.ROOT+"/warningLog/getErrorLogList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

//错误详情
function errorLogDetail(id) {
	parent.layer.open({
		type : 2,
		title : '错误详情',
		shadeClose : true,
		area : [ '900px', '500px' ],
		content : 'jsp/warning/errorLog/errorLogDetail.jsp?id=' + id
	});
}

function oneDelete(id){
	parent.layer.confirm('您确定要删除该条记录吗？', {
		  btn: ['确定','取消'] //按钮
	}, function(){
		  $.ajax({
			type : 'post',
				url : Config.ROOT+"/warningLog/deleteErrorLog.do",
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


