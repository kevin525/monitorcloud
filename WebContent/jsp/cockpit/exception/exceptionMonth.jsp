<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>当月异常</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/cockpit/exception/exceptionMonth.js"></script>
<script language="javascript">
var monitorType='<%=request.getParameter("monitorType")%>';

$(document).ready(function(e) {
	
	init();
});


function closeLayer(isrefresh){
	if(isrefresh){
		parent.window.iframe.init();
	}
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index)
	
}

</script>
</head>

<body>
<div class="common_table">
    <div class="common_table_bd">
    	<table width="100%">
        	<thead>
            	<tr style="text-align: center">
                    <th width="40">序号</th>
                    <th width="80">告警对象</th>
                    <th >告警原因</th>
                    <th width="120">告警方式</th>
                    <th width="160">告警时间</th>
                </tr>
            </thead>
            <tbody id="mbody">
				</tbody>
    	</table>
        <!--分页-->
        <div class="pageCount" id="pageId"></div>
    </div>
</div>
</body>
</html>
