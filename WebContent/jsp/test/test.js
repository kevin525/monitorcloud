var fBuildLogContentTr_V = function(con,i){
	var html = "";
	var sNum=oParams.start+i+1;
	html += "<tr bgcolor='"+(parseInt(i+2)%2==0?'#ffffff':'#f8f8f8')+"'>";
	html += "<td align='center'><input type='checkbox'></td>";
	html +=	"<td align='center'>"+sNum+"</td><td align='center'>"+con.loginName+"</td><td align='center'>"+con.loginPwd+"</td>"
	html +="<td align='center'><a href='javascript:void(0);' class='common_table_operate' style='margin-right:5px;'>修改</a><a href='javascript:void(0);' class='common_table_operate'>删除</a></td></tr>";
	return html;
};

$(document).ready(function(){
	init();
});
/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=8;
	sUrl=Config.ROOT+"/test/getList2.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

function searchLoginLog(){
	oParams.start=0;
	oParams.departmentId=deptId;
	oParams.loginName=$("input[name='loginName']").val()==null?"":$("input[name='loginName']").val();
	oParams.loginPwd=$("input[name='loginPwd']").val()==null?"":$("input[name='loginPwd']").val();
	sUrl=Config.ROOT+"/test/getList2.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}