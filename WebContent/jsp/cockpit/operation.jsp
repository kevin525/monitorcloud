<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目监控</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${configRoot}/jsp/cockpit/operation.js"></script>
 <style type="">
 .show_hide{ color:white; float:right; margin-right:100px; font-weight:normal; }
 .show_hide:hover{ color:yellow; cursor:pointer; }
 /* 最近检查时间 */
 .lastCheckDate{ position:absolute; left:40%;}
 .status_light{ position:absolute; left:75%;}
 /* 以下是检查次数的转圈样式 */
 .checkCount{ position:absolute; left:90%;}
 .count_loading { width: 60px;  height: 60px; position: relative; }
 #loading { 
	 width: 30px; 
	 height: 30px; 
	 background-color: transparent; 
	 border-radius: 50%;  
	 position: relative; 
	 top: 0px; 
	 left: 0px; 
	 border: 5px solid transparent;  
	 border-bottom-color: #ff7f00; 
	 border-left-color: #ff7f00; 
	 border-top-color: #00f; 
	 border-right-color: #00f; 
	 animation: rightRotate 2s linear infinite normal;
 }
 .bl_loading { border: 8px solid transparent;
     border-left-color: #ff7f00;
     width: 0;
     height: 0;
     position: absolute;
     top: 0;
     left: 0;
     transform: rotate(-45deg) translate(8px, -8px);
     /*z-index: 1;*/
 }

 .tr_loading {
     border: 8px solid transparent;
     border-right-color: #00f;
     width: 0;
     height: 0;
     position: absolute;
     bottom: 0;
     right: 0;
     transform: rotate(-45deg) translate(-8px, 8px);
 }

 #number_loading {
     position: absolute;
     top: 0px;
     left: 0px;
     width: 40px;
     height: 40px;
     line-height: 40px;
     text-align: center;
 }

 @keyframes rightRotate {
     from {
         transform: rotate(0deg);
     }
     50% {
	 	transform: rotate(180deg);
	 }
     to {
         transform: rotate(360deg);
     }
 }
</style>
</head>

<body>
<div class="common_table" style="margin-top:10px; ">
    <div class="common_table_bd">
    	<table width="100%" ">
             <tbody id="mbody">
             <tr id="server_tr">
				<td align="left"  style="padding:0 3px;">
					<div style="padding-left:10px;background-color:#009688;color:white;font-weight:bold;font-size:16px;">
					服务器监控台
					<a class="show_hide serverShow"  style="display:none;" onclick="show('server')">展开</a>
					<a class="show_hide serverHide" onclick="hide('server')">收起</a>
					</div>
					<div class='items' style='margin-bottom:20px;'class='items' style='margin-bottom:10px;'>
						<fieldset style="border:1px solid #dddddd;margin-top:10px;">
						<div id="server"></div>		
						</fieldset>
					</div>
            	 </td>
             </tr>
              <tr id="database_tr">
				<td align="left"  style="padding:0 3px;">
					<div style="padding-left:10px;background-color:#009688;color:white;font-weight:bold;font-size:16px;">
					数据库监控台
					<a class="show_hide databaseShow"  style="display:none;" onclick="show('database')">展开</a>
					<a class="show_hide databaseHide" onclick="hide('database')">收起</a>
					</div>
					<div class='items' style='margin-bottom:20px;'class='items' style='margin-bottom:10px;'>
						<fieldset style="border:1px solid #dddddd;margin-top:10px;">
						<div id="database"></div>		
						</fieldset>
					</div>
            	 </td>
             </tr>
              <tr id="tomcat_tr">
				<td align="left"  style="padding:0 3px;">
					<div style="padding-left:10px;background-color:#009688;color:white;font-weight:bold;font-size:16px;">
					中间件监控台
					<a class="show_hide tomcatShow"  style="display:none;" onclick="show('tomcat')">展开</a>
					<a class="show_hide tomcatHide" onclick="hide('tomcat')">收起</a>
					</div>
					<div class='items' style='margin-bottom:20px;'class='items' style='margin-bottom:10px;'>
						<fieldset style="border:1px solid #dddddd;margin-top:10px;">
						<div id="tomcat"></div>		
						</fieldset>
					</div>
            	 </td>
             </tr>
              <tr id="system_tr">
				<td align="left"  style="padding:0 3px;">
					<div style="padding-left:10px;background-color:#009688;color:white;font-weight:bold;font-size:16px;">
					应用系统监控台
					<a class="show_hide systemShow"  style="display:none;" onclick="show('system')">展开</a>
					<a class="show_hide systemHide" onclick="hide('system')">收起</a>
					</div>
					<div class='items' style='margin-bottom:20px;'class='items' style='margin-bottom:10px;'>
						<fieldset style="border:1px solid #dddddd;margin-top:10px;">
						<div id="system"></div>		
						</fieldset>
					</div>
            	 </td>
             </tr>
				</tbody>
			</table>
           <br />
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	init();
});
</script>
</body>
</html>
