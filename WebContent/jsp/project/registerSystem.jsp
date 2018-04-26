<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上线系统情况</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form id="constructForm" action="${appName}/construct/addConstruct.do" method="post" target="innerFrame">
<div class="addNewMember">
	<table width="100%" align="center">
    	<tr>
        	<td width="150" align="center" bgcolor="#eeeeee"><strong class="emphasize">*</strong>系统名称</td>
            <td bgcolor="#ffffff" style="padding-left:4px;"><input class="ipt_normal" id="company" name="company" onkeypress="javascript:return goSubmit();"/></td>
        </tr>
        <tr>
        	<td width="150" align="center" bgcolor="#eeeeee"><strong class="emphasize">*</strong>系统服务器ip</td>
            <td bgcolor="#ffffff" style="padding-left:4px;"><input class="ipt_normal" id="company" name="company" onkeypress="javascript:return goSubmit();"/></td>
        </tr>
        <tr>
        	<td width="150" align="center" bgcolor="#eeeeee"><strong class="emphasize">*</strong>系统数据库ip</td>
            <td bgcolor="#ffffff" style="padding-left:4px;"><input class="ipt_normal" id="company" name="company" onkeypress="javascript:return goSubmit();"/></td>
        </tr>
        <tr>
        	<td width="150" align="center" bgcolor="#eeeeee"><strong class="emphasize">*</strong>开发负责人</td>
            <td bgcolor="#ffffff" style="padding-left:4px;"><input class="ipt_normal" id="company" name="company" onkeypress="javascript:return goSubmit();"/></td>
        </tr>
        <tr>
        	<td width="150" align="center" bgcolor="#eeeeee"><strong class="emphasize">*</strong>维护负责人</td>
            <td bgcolor="#ffffff" style="padding-left:4px;"><input class="ipt_normal" id="company" name="company" onkeypress="javascript:return goSubmit();"/></td>
        </tr>
        
         <tr>
        	<td width="150" align="center" bgcolor="#eeeeee">是否上线</td>
            <td bgcolor="#ffffff" style="padding-left:4px;">
            	<select class="select_normal" name="isRecommend">
                    <option selected="selected"  value="false">否</option>
                	<option value="true">是</option>
                </select>
            </td>
        </tr>
        
       
    </table>
    <input type="hidden" name="contentUrl" id="contentUrl" value=""> 
    <a href="javascript:void(0);" onclick="submit()" class="btn_common" style="margin-left:150px; margin-top:20px;">提 交</a>
</div>
</form>
<iframe id="innerFrame"  style="height:0;display:none" name="innerFrame">
	
</iframe>
</body>
</html>
