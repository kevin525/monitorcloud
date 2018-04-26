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
	html +=	"<td align='center'>"+sNum+"</td>";
	html +=	"<td align='left'  style='padding:0 3px;'>"+con.monitorName+"</td>";
	html += "<td align='left' style='line-height:20px;'>"+con.warnName+"</td>" ;
	html += "<td align='center'>"+(con.warnWay==null?'':con.warnWay)+"</td>" ;
	html += "<td align='center'>"+con.warnTime+"</td>" ;
	html +="</tr>";
	return html;
};

/*
 * 用户界面初始化
 */
function init(){
	oParams.start=0;
	oParams.pageSize=8;
	oParams.monitorType = monitorType;
	sUrl=Config.ROOT+"/monitor/getCurDayList.do";
	fCallBack = fBuildLogContentTr_V;
	fLoadContents_V(oParams.getStart(),oParams.getPageSize());
}

