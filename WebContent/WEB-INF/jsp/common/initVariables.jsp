<%@page import="com.sys.domain.model.User"%>
<%@page import="com.sys.util.Config"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.lt.com/securitytag" prefix="secTag"%>
<c:set var="configRoot" value="${pageContext.request.contextPath}" />

<%
	String path = request.getContextPath();
	String requestServer = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String basePath = requestServer + path;
	User user = (User)request.getSession().getAttribute("loginUser");
	String systemName = Config.getAttribute("systemName");
	systemName = new String(systemName.getBytes("ISO-8859-1"), "utf-8");
%>
<c:set var="systemName" value="<%= systemName%>" />
<script type="text/javascript">
	Config = {
		ROOT  : '<%=path%>'
		}
</script>
<script src='<%=request.getContextPath() %>/js/jquery/jquery-1.11.3.min.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/page/pagination.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/osplugins/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Highcharts-6.0.2/code/highcharts.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/layui/css/layui.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layui/layui.js"></script>
<%@ include file="/WEB-INF/jsp/common/include-artdialog.jsp"%>
<script type="text/javascript">
	var user = '<%=user%>';
</script>