<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工作周报</title>
<style type="text/css">
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td{margin:0;padding:0;font-size:14px;color:#333333;font-family:"Microsoft YaHei";}
table{border-collapse:collapse;border-spacing:0;}

.mForm{width:900px; margin:10px auto 10px; border:1px solid #999999; background-color:#ffffff; padding:20px 40px;}
.mFormTile{height:40px; line-height:40px; font-size:28px; text-align:center;}
.mFormCon{}
.mFormTimeDuration{text-align:right; margin-top:10px; font-weight:bold; font-size:15px;}
.ipt_mForm_startTime, .ipt_mForm_endTime{width:140px; height:20px; line-height:20px; padding:0 3px; border:none; border-bottom:1px solid #666666;}
.mFormTable{padding:20px 0;}
.mFormTable table tr td{line-height:40px; border:1px solid #666666;}
.ipt_mFormTable{width:98%; height:40px; line-height:40px; border:none; font-size:14px; color:#333333; font-family:"Microsoft YaHei"; padding-left:2px; outline:none;}
.txa_mFormTable{width:99%; min-height:40px; line-height:40px; border:none; overflow:auto; padding:0 2px; outline:none;}
.txa_mFormTable_suggest{width:99%; min-height:160px; line-height:40px; border:none; overflow:auto; padding:0 2px; outline:none;}
.btn_mFormTable_addRow{display:block; position:absolute; top:8px; right:-30px; width:24px; height:24px; background:url(images/btn_add.png) no-repeat;}
.currentWeek_mFormTable, .nextWeek_mFormTable{position:relative;}
.btn_mFormTable_submit{display:block; width:197px; height:41px; line-height:41px; margin:0 auto; text-align:center; color:#ffffff; font-size:18px; text-decoration:none;}
.btn_mFormTable_submit:link{background:url(images/btn_mFormTable_submit_linkBg.png) no-repeat;}
.btn_mFormTable_submit:hover{background:url(images/btn_mFormTable_submit_hoverBg.png) no-repeat;}
.btn_mFormTable_submit:active{background:url(images/btn_mFormTable_submit_activeBg.png) no-repeat;}
</style>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<script src="${configRoot}/js/jquery/jquery.tablednd.0.9.rc1.js" type="text/javascript"></script>
</head>

<body bgcolor="#f8f8f8">
<div class="mForm">
    <h1 class="mFormTile">运维记录</h1>
    <div class="mFormCon">
        <div class="mFormTimeDuration">
            <span>起止时间：</span>
            <input type="text" class="ipt_mForm_startTime" />
            <span>~</span>
            <input type="text" class="ipt_mForm_endTime" />
        </div>
        <div class="mFormTable">
        	<table width="900">
            	<tr>
                	<td width="100" align="center" bgcolor="#e2f2ff"><strong>填报人</strong></td>
                    <td width="379"><input type="text" class="ipt_mFormTable" /></td>
                    <td width="100" align="center" bgcolor="#e2f2ff"><strong>填报时间</strong></td>
                    <td width="321"><input type="text" class="ipt_mFormTable" /></td>
                </tr>
            </table>
            <div class="currentWeek_mFormTable">
            	<a href="javascript:void(0);" title="添加" class="btn_mFormTable_addRow"></a>
                <table width="900" id="mFormTable1">
                    <tr>
                        <td width="100" align="center" bgcolor="#cccccc"><strong>序号</strong></td>
                        <td width="380" align="center" bgcolor="#cccccc"><strong>本周工作内容</strong></td>
                        <td width="100" align="center" bgcolor="#cccccc"><strong>进度</strong></td>
                        <td width="280" align="center" bgcolor="#cccccc"><strong>备注</strong></td>
                        <td width="40" align="center" bgcolor="#cccccc"><strong>操作</strong></td>
                    </tr>
                    <tr id="1">
                        <td align="center" bgcolor="#f0f0f0"><strong>1</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td><input type="text" class="ipt_mFormTable" /></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                    <tr id="2">
                        <td align="center" bgcolor="#f0f0f0"><strong>2</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td><input type="text" class="ipt_mFormTable" /></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                    <tr id="3">
                        <td align="center" bgcolor="#f0f0f0"><strong>3</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td><input type="text" class="ipt_mFormTable" /></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                    <tr id="4">
                        <td align="center" bgcolor="#f0f0f0"><strong>4</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td><input type="text" class="ipt_mFormTable" /></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                    <tr id="5">
                        <td align="center" bgcolor="#f0f0f0"><strong>5</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td><input type="text" class="ipt_mFormTable" /></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                </table>
            </div>
            <div class="nextWeek_mFormTable">
            	<a href="javascript:void(0);" title="添加" class="btn_mFormTable_addRow"></a>
                <table width="900" id="mFormTable2">
                    <tr>
                        <td colspan="2" align="center" bgcolor="#cccccc"><strong>下周工作计划</strong></td>
                        <td width="40" align="center" bgcolor="#cccccc"><strong>操作</strong></td>
                    </tr>
                    <tr id="111">
                        <td align="center" bgcolor="#f0f0f0" width="100"><strong>1</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                    <tr id="112">
                        <td align="center" bgcolor="#f0f0f0"><strong>2</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                    <tr id="113">
                        <td align="center" bgcolor="#f0f0f0"><strong>3</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                    <tr id="114">
                        <td align="center" bgcolor="#f0f0f0"><strong>4</strong></td>
                        <td><div contenteditable="true" class="txa_mFormTable"></div></td>
                        <td align="center"><a title="删除" href="javascript:void(0);"><img src="../../images/btn_miuns.png" /></a></td>
                    </tr>
                 </table>
             </div>
             <table width="900" id="mFormTable3">
                <tr>
                	<td align="center" bgcolor="#cccccc" name="biaozhi"><strong>存在问题及建议</strong></td>
                </tr>
                <tr>
                    <td name="biaozhi"><div contenteditable="true" class="txa_mFormTable_suggest"></div></td>
                </tr>
            </table>
        </div>
    </div>
    <a href="javascript:void(0);" class="btn_mFormTable_submit">提　交</a>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#mFormTable1').tableDnD();
	$('#mFormTable2').tableDnD();
});
</script>
</body>
</html>
