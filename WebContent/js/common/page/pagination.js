var sUrl = "";//访问的URL
function PageInfo(){ 
	this.pageSize =6;
    this.start =0;
    this.totalCount=0; 
    this.getPageSize = function() {
        return this.pageSize;
    };
    this.setPageSize = function(pageSize) {
    	this.pageSize=pageSize;
    };
	
    this.getStart = function() {
        return this.start;
    };
    this.setStart = function(start) {
    	this.start=start;
    };
    
    this.getTotalCount = function() {
        return this.totalCount;
    };
    this.setTotalCount = function(totalCount) {
    	this.totalCount=totalCount;
    };
	
} 
var oParams = new PageInfo();//分页参数

var fCallBack;//回调函数（如：Controller 返回JSON数据的处理方法）
/**
 * 加载数据列表
 * @param start 分页起始数
 * @param pageSize 每页显示数量
 */
function fLoadContents_V(start,pageSize){	
	oParams.setStart(start);
	oParams.setPageSize(pageSize);
	counts = 0;
	infos = new Array();
	fLoadPageContents_V(sUrl,oParams,function(contents){
		 var s='';
		 var tc=0;
		 if(contents && contents.pageData){
			 $(contents.pageData).each(function(i){
				 counts = i + 2;
				 var tr=fCallBack(this,i);
				 s+=tr;
			 });
		 }
		 $("#mbody").html(s);
		 if(contents && contents.totalCount){
			 tc = contents.totalCount;
		 }
		 oParams.setTotalCount(tc);
		 initPage(oParams.getStart(),oParams.getPageSize(), oParams.getTotalCount());
	});
};
/**
 * 向服务器段请求加载分页列表数据
 * @param url   请求链接
 * @param page  分页对象
 * @param callBack  回调函数
 */
function fLoadPageContents_V(url,page,callBack) {
	$.ajax({
		type : "post",
		url : url,
		data : page,
		dataType : "json",
		async : false,
		success : function(obj) {
			callBack(obj);
		},
		error : function() {
			ArtMsgIFrame.Error("加载失败，请稍后重试！");
		}
	});
};
/*
 * 初始化分页展示
 */
function initPage(start, limit, totalCount){
	//processHeight();
	processPages(start, limit, totalCount,false);
	//buildPageInfo(start, limit, totalCount,false);
}

/**
 * 分页通用js
 * @param curPage：当前页，从1页开始计算
 * @param totalPageNum：总页数
 * @param totalContentNum：总条数
 * @param isShowTotalNum：是否显示总条数，true代表显示，false代表不显示
 * @returns {String}
 */
var processPages = function(start, limit, totalCount,isShowTotalNum) {
	
	var totalContentNum = parseInt(totalCount);
	curPage = start / limit + 1;
	curPage = curPage <= 0 ? 1 : curPage;
	var totalPageNum = totalCount / limit;
	totalPageNum = parseInt(totalPageNum) + (totalCount % limit > 0 ? 1 : 0);
	var tohtml = '';
	if(totalPageNum==0){
			tohtml ="<div name='resultDiv' style='margin-top:20px;width:100%;text-align:center;font-size:16px;'><span style='color: rgb(11, 112, 220);'>无可显示数据</span></div>";
	}else{
		
	    var j = 8;
	    var f = 2;
	    var tohtml = "<div class='pageCountGreen'>";
	    if (curPage <= 1) {
	        tohtml += "<a href='javascript:void(0);' onclick='javascript:goPage(" + (1) + ")'>首页</a>";
	        tohtml += "<a href='javascript:void(0);'>上一页</a>";
	        tohtml += "<a href='javascript:void(0)' class='curr')'>1</a>";
	    }
	    if (curPage > 1) {
	        tohtml += "<a href='javascript:void(0);' onclick='javascript:goPage(" + (1) + ")'>首页</a>";
	        tohtml += "<a href='javascript:void(0)' onclick='javascript:goPage(" + (curPage - 1) + ")'>上一页</a>";
	        tohtml += "<a href='javascript:void(0)' onclick='javascript:goPage(" + (1) + ")'>1</a>";
	    }
	    if (curPage == 6 && totalPageNum > 7) {
	    	j += 1;
	    	f += 1;
	    }else if((totalPageNum - curPage < 2) && totalPageNum > 7) {
	    	j = curPage + 3;
	    	f = curPage - 4;
	    } else if (curPage > 6) {
	    	j = curPage + 3;
	    	f = curPage - 3;
	    }
	    if(curPage >= 6 && totalPageNum > 7) {
	    	tohtml+="<a style='border:0px;'>...</a>";
	    }
	    for(var i = f; i <= totalPageNum; i++) {
	    	if (i != curPage) {
		    	if ((parseInt(i) < parseInt(j))) {
		    		tohtml += "<a href='javascript:void(0)' onclick='javascript:goPage(" + (parseInt(i)) + ")'>" + (parseInt(i)) + "</a>";
		    	}
	    	}else {
	    		tohtml += "<a href='javascript:void(0)' class='curr' onclick='javascript:goPage(" + curPage + ")'>" + curPage + "</a>";
	    	}	
	    }
	    if((curPage < totalPageNum - 2) && totalPageNum > 7) {
	    	tohtml+="<a style='border:0px;'>...</a>";
	    }
	    if (curPage >= totalPageNum) {
	        tohtml += "<a href='javascript:void(0);'>下一页</a>";
	        tohtml += "<a href='javascript:void(0);')>最后一页</a>";
	    }
	    if (curPage < totalPageNum) {
	        tohtml += "<a href='#' onclick='javascript:goPage(" + (curPage + 1) + ")'>下一页</a>";
	        tohtml += "<a href='javascript:void(0);' onclick='javascript:goPage(" + totalPageNum + ")'>最后一页</a>";
	    }
	    if(isShowTotalNum){
	    	tohtml += "<span>共<strong>" + totalPageNum + "</strong>页 <strong>" + totalContentNum + "</strong>条</span></div>";
	    }
    }
    $('#pageId').empty();
	$('#pageId').html(tohtml);
}

/*
 * 翻页操作
 */
var goPage = function(o){
	if(!o)o=1;
	var start = (o-1)*oParams.getPageSize();
	fLoadContents_V(start,oParams.getPageSize());
}

var getSpDisNum = function(disNum){
	if(disNum%2==0)
		return disNum/2;
	else return (disNum+1)/2;
}
/*
 * 分页栏展示
 */
var processPages1 = function(start, limit, totalCount){
	var curPage = 0; //当前页
	var disNum = 6;
	curPage = start / limit + 1;
	curPage = curPage <= 0 ? 1 : curPage;
	var totalPage = totalCount / limit;
	totalPage = parseInt(totalPage) + (totalCount % limit > 0 ? 1 : 0);
	var html = '';
		if(totalCount<1){
			html+='<span class="prev">没有可显示的数据</span>';
		}else{
			if(curPage<=1){
				html += '<span class="prev-disabled">上一页</span>';
			} else {
				html += '<a href="javascript:void(0)" class="prev" onclick="goPage(' + (parseInt(curPage)-1) + ')">上一页</a>';
			}
			if(totalPage<=disNum){
				for(var i = 1; i <= totalPage; i++){
					if(curPage == i){
						html += '<a href="javascript:void(0)" class="current">' + i + '</a>';
					}else{
						html += '  <a href="javascript:void(0)" onclick="goPage(' + i + ')">' + i + '</a>';
					}
				}
			}else{
				var start = 1;
				if(curPage>getSpDisNum(disNum)){
					start = curPage-getSpDisNum(disNum);
				}
				if(start> (totalPage - disNum +1))
					start = totalPage - disNum +1;
				var end = start + disNum -1;
				if(start>2){
					html += '<a href="javascript:void(0)" onclick="goPage(1)">' + 1 + '</a><span cless="text">...</span>';
				}
				if(start<0)start =1;
				for(var i = start; i <= end; i++){
					if(curPage == i){
						html += '<a href="javascript:void(0)" class="current">' + i + '</a>';
					}else{
						html += '  <a href="javascript:void(0)" onclick="goPage(' + i + ')">' + i + '</a>';
					}
				}
				if(end!=totalPage){
					html += '<span cless="text">...</span><a href="javascript:void(0)" onclick="goPage(' + totalPage + ')">' + totalPage + '</a>';
				}
			}
		/*	if(totalPage >= 1){
				if(curPage == totalPage){
					 html += '   <a class="current" href="javascript:void(0)" onclick="goPage(' + totalPage + ')">'  +  totalPage + '</a>';
				} else {
					 html += '   <a href="javascript:void(0)" onclick="goPage(' + totalPage + ')">' + totalPage + '</a>';
				}
		    }*/
			
			if(curPage < totalPage){
				html += '   <a href="javascript:void(0);" onclick="goPage(' + (parseInt(curPage)+1) + ')" class="next">下一页</a>';
			} else {
				//html += '   <a href="javascript:void(0);" class="next-disabled">下一页<b></b></a>';
			}
		}
	$('#pageId').empty();
	$('#pageId').html(html);
}

function getDisColor(i){
	if(i && (i+1)%2==0){ return "#F0F0F0";
	}else{ return "#FFFFFF"; }
}

var checkAll = function(o){
	var flag = $("#checkbox").attr("checked") || false;
	if(o)flag = o.checked;
	$("input[name='index_chk']").attr("checked", flag);
};

/**
 * 获取checkbox选择的值
 * @param name 获取的对象名 默认：index_chk
 */
var getCheckedValue = function(name){
	var values = new Array(), nam = name || 'index_chk';
	$("input[name='"+nam+"']").each(function(){
		if(this.checked=='checked' || this.checked==true){
			if(this.value)
 	            values.push(this.value);
		}
    });
   return values.toString();
};
