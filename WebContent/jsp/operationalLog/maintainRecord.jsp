<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>运维记录</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/base-min.css" rel="stylesheet" type="text/css">
<link href="${configRoot}/css/popup.css" rel="stylesheet" type="text/css">
</head>

<body>

<div class="userQuery">
	<div class="h15"></div>
    <!--模糊查询-->
	<div class="advancedQuery clearfix" style="margin-left:20px;margin-top:20px;">
    	<span class="fl" >时间：</span>
    	<input type="text" class="ipt fl" style="width:100px;" placeholder="开始时间" />
    	<span class="fl" >~</span><input type="text" class="ipt fl" style="width:100px;" placeholder="结束时间" />
    	<a href="javascript:void(0);" class="btn_search_advancedQuery fl">查询</a> 
    	<a href="javascript:void(0);" class="btn_search_advancedQuery fl">导出</a> 
    	<a href="addRecord.html" class="btn_search_advancedQuery fl" style="background-color:#ffa200;">新建</a>
    </div>
    <div class="h15"></div>
    
    <!--高级查询按钮-->
    <div class="queryCategory_line"></div>
    <a href="javascript:void(0);" class="btn_advancedQuery"></a>
    <!--查询结果-->
    <div class="queryResult">
    	<!--查询结果列表-->
    	<div class="queryResult_list clearfix bc">
        	<div class="queryResult_item">
                <div class="expertInfo clearfix">
                    <div class="expertInfo_l fl">
                        <span class="avatar" style="background-color:#f2f2f2;"><img src="../../images/report.png"/></span>
                    </div>
                    <div class="expertInfo_r fr">
                        <span><strong>创建人员：</strong>李莉</span>
                        <span><strong>时间：</strong>2017-03-02</span>
                        <span><strong>手机：</strong>13900108767</span>
                    </div>
                </div>
                <div class="operateTool">
                	<a href="javascript:void(0);" class="chatTool"><span class="vm">修改</span></a>
                </div>
            </div>
            <div class="queryResult_item">
                <div class="expertInfo clearfix">
                    <div class="expertInfo_l fl">
                        <span class="avatar" style="background-color:#f2f2f2;"><img src="../../images/report.png"/></span>
                    </div>
                    <div class="expertInfo_r fr">
                        <span><strong>创建人员：</strong>李莉</span>
                        <span><strong>时间：</strong>2017-03-02</span>
                        <span><strong>手机：</strong>13900108767</span>
                    </div>
                </div>
                <div class="operateTool">
                	<a href="javascript:void(0);" class="chatTool"><span class="vm">修改</span></a>
                </div>
            </div>
            <div class="queryResult_item">
                <div class="expertInfo clearfix">
                    <div class="expertInfo_l fl">
                        <span class="avatar" style="background-color:#f2f2f2;"><img src="../../images/report.png"/></span>
                    </div>
                    <div class="expertInfo_r fr">
                        <span><strong>创建人员：</strong>李莉</span>
                        <span><strong>时间：</strong>2017-03-02</span>
                        <span><strong>手机：</strong>13900108767</span>
                    </div>
                </div>
                <div class="operateTool">
                	<a href="javascript:void(0);" class="chatTool"><span class="vm">修改</span></a>
                </div>
            </div>
            <div class="queryResult_item">
                <div class="expertInfo clearfix">
                    <div class="expertInfo_l fl">
                        <span class="avatar" style="background-color:#f2f2f2;"><img src="../../images/report.png"/></span>
                    </div>
                    <div class="expertInfo_r fr">
                        <span><strong>创建人员：</strong>李莉</span>
                        <span><strong>时间：</strong>2017-03-02</span>
                        <span><strong>手机：</strong>13900108767</span>
                    </div>
                </div>
                <div class="operateTool">
                	<a href="javascript:void(0);" class="chatTool"><span class="vm">修改</span></a>
                </div>
            </div>
            <div class="queryResult_item">
                <div class="expertInfo clearfix">
                    <div class="expertInfo_l fl">
                        <span class="avatar" style="background-color:#f2f2f2;"><img src="../../images/report.png"/></span>
                    </div>
                    <div class="expertInfo_r fr">
                        <span><strong>创建人员：</strong>李莉</span>
                        <span><strong>时间：</strong>2017-03-02</span>
                        <span><strong>手机：</strong>13900108767</span>
                    </div>
                </div>
                <div class="operateTool">
                	<a href="javascript:void(0);" class="chatTool"><span class="vm">修改</span></a>
                </div>
            </div>
            <div class="queryResult_item">
                <div class="expertInfo clearfix">
                    <div class="expertInfo_l fl">
                        <span class="avatar" style="background-color:#f2f2f2;"><img src="../../images/report.png"/></span>
                    </div>
                    <div class="expertInfo_r fr">
                        <span><strong>创建人员：</strong>李莉</span>
                        <span><strong>时间：</strong>2017-03-02</span>
                        <span><strong>手机：</strong>13900108767</span>
                    </div>
                </div>
                <div class="operateTool">
                	<a href="javascript:void(0);" class="chatTool"><span class="vm">修改</span></a>
                </div>
            </div>
            
            <div class="queryResult_item">
                <div class="expertInfo clearfix">
                    <div class="expertInfo_l fl">
                        <span class="avatar" style="background-color:#f2f2f2;"><img src="../../images/report.png"/></span>
                    </div>
                    <div class="expertInfo_r fr">
                        <span><strong>创建人员：</strong>李莉</span>
                        <span><strong>时间：</strong>2017-03-02</span>
                        <span><strong>手机：</strong>13900108767</span>
                    </div>
                </div>
                <div class="operateTool">
                	<a href="javascript:void(0);" class="chatTool"><span class="vm">修改</span></a>
                </div>
            </div>
            <div class="queryResult_item">
                <div class="expertInfo clearfix">
                    <div class="expertInfo_l fl">
                        <span class="avatar" style="background-color:#f2f2f2;"><img src="../../images/report.png"/></span>
                    </div>
                    <div class="expertInfo_r fr">
                        <span><strong>创建人员：</strong>李莉</span>
                        <span><strong>时间：</strong>2017-03-02</span>
                        <span><strong>手机：</strong>13900108767</span>
                    </div>
                </div>
                <div class="operateTool">
                	<a href="javascript:void(0);" class="chatTool"><span class="vm">修改</span></a>
                </div>
            </div>
            
        </div>
        <div class="h20"></div>
        <!--分页-->
        <div class="clearfix tc">
            <div class="pagin bc">
                <span class="prev-disabled">上一页<b></b></span>
                <a href="javascript:void(0);" class="current">1</a>
                <a href="javascript:void(0);">2</a>
                <a href="javascript:void(0);">3</a>
                <span class="text">…</span>
                <a href="javascript:void(0);">667</a>
                <a href="javascript:void(0);" class="next">下一页<b></b></a>
            </div>
        </div>
    </div>
    
    
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	// 高级检索
    $(".btn_advancedQuery").toggle(
		function(){
			$(".queryCategory").slideDown();
			$(".queryCategory_line").hide();
		},
		function(){
			$(".queryCategory").slideUp();
			$(".queryCategory_line").show();
		}
	);
	// 操作-编辑
	$(".queryCategory_item_edit").bind("click",function(){
		$(".queryResult_item_edite").show();
	});
});
</script>
</body>
</html>
