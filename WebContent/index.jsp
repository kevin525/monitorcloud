<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<title>${systemName}</title>
 <link rel="stylesheet" href="${configRoot}/js/layui/css/layui.css">
  <script type="text/javascript">
	function showMenuContent(menuName,url){
		 var iframe = $("#iframe");
		iframe.attr("src",url);
		$("a").removeClass("layui-this");
		$("dd").removeClass("layui-this");
		$(menuName).parent().addClass("layui-this"); 
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
				$("#common_btn_edit").click(function(){
					parent.layer.open({
					  type: 2,
					  title: '修改密码',
					  shadeClose: true,
					  area: ['600px', '50%'], 
					  content: 'jsp/user/editPwd.jsp'
					});
				});
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
			        		$("#main_l").append(firstHtml);
			        		 $(data).each(function (i){
			        			if(data[i].nodeValue ==2){
			        				//二级级菜单
			        				buildSecond(data[i]);
			        			}
			        		});
			        	    var url =$("#ul_"+firstId+" a:first").attr("url");
			        		var id =$("#ul_"+firstId+" a:first").attr("id");
			        		
			        		$("#li_"+firstId).addClass("layui-nav-itemed");
			        		$("#"+id).addClass("layui-this");
			        		$("#iframe").attr("src",url);  
			        	}else{
			        		alert("您没有任何权限，请联系管理员！")
			        	}
			        }
			    });
				$.ajax({
			        type: "POST",
			        url:Config.ROOT+"/inform/getUnreadCount.do",
			        success: function(con) {
			        	$("#newMessage").html(con.data);
			        }
			    });
	});
	function buildFirst(data){
		var html ="";
	    html += '<li class="layui-nav-item" id="li_'+data.resourcesId+'" >';
	    html +='<a class="" href="javascript:;" onclick="showFirstMenu(this)">'+data.resourcesName+'<span class="layui-nav-more"></span></a>';
	    html +='<dl class="layui-nav-child" id="ul_'+data.resourcesId+'"></dl>';
	    html +='</li>';
	    return html;
	}
	function buildSecond(data){
	    var html ='<dd><a href="javascript:void(0);" url="'+data.resourcesUrl+'" id="'+data.resourcesPid+'" onclick="showMenuContent(this,\''+data.resourcesUrl+'\')">'+data.resourcesName+'</a></dd>';
	     $("#ul_"+data.resourcesPid).append(html); 
	}
	function showFirstMenu(menuName){
		var id = $(menuName).parent().attr("id"); 
		if($("#"+id).hasClass("layui-nav-itemed")){
			$("#"+id).removeClass("layui-nav-itemed");
		}else{
			$("#"+id).addClass("layui-nav-itemed");
		}
	}
</script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo" style="font-size:30px;font-weight:bold;width:450px;">
    <img alt="云智能运维监控管理平台" src="login/images/logo_1.png" width="40" height="40">
    云智能运维监控管理平台</div>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
	    <a href="javascript:void(0)" onclick="showMenuContent(this,'jsp/warningNote/inform/inform.jsp')">新消息<span class="layui-badge" id="newMessage">0</span>&nbsp;&nbsp;</a>
	  </li>
      <li class="layui-nav-item">
                 
        <a href="javascript:;">
          <img src="${configRoot}/images/avatar.png" class="layui-nav-img">
                     您好：<%=user.getPerson().getPersonName()%>
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">个人信息</a></dd>
          <dd><a id="common_btn_edit">修改密码</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="javascript:void(0)" onclick="doLoginOut()">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test" id="main_l">
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <iframe id="iframe" name="iframe" src="" width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe>
  </div>
  
</div>
<script type="text/javascript" src="${configRoot}/js/layui/layui.all.js"></script>
<script type="text/javascript">
$(document).ready(function() {
   	var mainHeight = $(window).height() - $(".layui-header").height();
	$(".main_l").height(mainHeight);
	$(".layui-body").height(mainHeight);
	$("#iframe").height(mainHeight-5);
});
</script>
</body>
</html>

