<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>通州OA维护管理平台</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function showMenuContent(menuName,url){
	var iframe = $("#iframe");
	iframe.attr("src",url);
	var name = $(menuName).text();
	var crumbName = $("#crumb_name");
	crumbName.html(name);
	$("a").removeClass("curr");
	$(menuName).addClass("curr");
}

function doLoginOut(){
	$.ajax({
        type: "POST",
        url:Config.ROOT+"/login/loginOut.do",
        success: function(data) {
        	window.location.href = "login.jsp";
        }
    });
}

$(document).ready(
		function() {
			
			$.ajax({
		        type: "POST",
		        url:Config.ROOT+"/roleResources/getResources.do",
		    	async : false,// 同步方式
		        success: function(con) {
	        		var data = con.data;
		        	if(null != data){
		        		var firstHtml="";
		        		var firstId="";
		        		$(data).each(function (i){
		        			if(data[i].nodeValue ==1){
		        				//一级菜单
		        				firstHtml += buildFirst(data[i]);
		        				if(i==0){
		        					firstId=data[i].resourcesId;
		        				}
		        			}
		        		});
		        		$(".main_l").append(firstHtml);
		        		$(data).each(function (i){
		        			if(data[i].nodeValue ==2){
		        				//二级级菜单
		        				buildSecond(data[i]);
		        			}
		        		});
		        		var url =$("#ul_"+firstId+" a:first").attr("class");
		        		var id =$("#ul_"+firstId+" a:first").attr("id");
		        		$("#crumb_name").html($("#"+id).text());
		        		$("#ul_"+id).show();
		        		$("#"+id).addClass("curr");
		        		$("#iframe").attr("src",url);
		        	}else{
		        		alert("啥权限也没有")
		        	}
		        }
		    });
			$(".sidebarNav").click(
					function() {
						$(this).next("div").slideToggle("slow").siblings(".sidebarNav_bd:visible").slideUp("slow");
					});
});
function buildFirst(data){
	var html ="";
	//一级菜单
	html += '<div class="sidebarNav" >';
	html += '<div class="sidebarNav_hd" ><i class="icon_dataManagement"></i>'+data.resourcesName+'</div>';
	html += '</div>';
	html += '<div class="sidebarNav_bd" id="ul_'+data.resourcesId+'"></div>';
    html += '<div class="mainBlank"></div>';
    return html;
}
function buildSecond(data){
	var html ='<a href="javascript:void(0);" class="'+data.resourcesUrl+'" id="'+data.resourcesPid+'" onclick="showMenuContent(this,\''+data.resourcesUrl+'\')">'+data.resourcesName+'</a>';
     $("#ul_"+data.resourcesPid).append(html); 
		
	
}		
</script>
</head>

<body>
<div class="top clearfix">
	<span class="logo">
    	<i class="icon_logo_small"></i>通州OA维护管理平台
    </span>
    <div style="float:right;">
    	<span class="user">您好：<%=user.getPerson().getPersonName()%> </span>
        <a href="javascript:void(0);" class="btn_close" onclick="doLoginOut()">退出</a>
    </div>
</div>
<div class="main">
	<div class="main_l" >
	    	  
    </div>
    <div class="main_r">
    	<div class="crumb">
        	<i class="icon_home"></i>
        	<!--  
        	<span class="crumb_name">系统维护</span><span class="crumb_step">></span>
        	-->
        	<span class="crumb_name" id="crumb_name"></span>
        </div>
    	<iframe id="iframe" name="iframe" src="" width="100%" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
   	var mainHeight = $(window).height() - $(".top").height();
	$(".main_l").height(mainHeight);
	$(".main_r").height(mainHeight);
	$("#iframe").height(mainHeight - 41);
});
</script>
</body>
</html>
