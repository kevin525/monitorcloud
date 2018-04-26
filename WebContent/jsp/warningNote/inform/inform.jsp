<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短消息通知</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
</head>
<body>
 
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
  	<li lay-id="33" class="layui-this">未读</li>
    <li lay-id="11" ><a href="javascript:changeRead()">已读</a></li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
  <div class="layui-tab-item layui-show">
     <jsp:include page="unreadInform.jsp"/>
    </div>
    <div class="layui-tab-item "  >
     <iframe frameborder="0" id="iframe3"  name="readeiframe" height="500"
					src="${configRoot}/jsp/warningNote/inform/readInform.jsp"
					width="100%"></iframe>
    </div>
  </div>
</div> 

<script type="text/javascript">

layui.use('element', function(){
  var $ = layui.jquery,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
  //触发事件
  
});
function changeRead(){
	readeiframe.window.init();
}
</script>
</body>
</html>
