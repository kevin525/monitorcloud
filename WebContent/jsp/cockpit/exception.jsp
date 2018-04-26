<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>监控情况</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/modules/oldie.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/modules/heatmap.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/modules/tilemap.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/highcharts-3d.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/highcharts-more.js"></script>
 <script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/modules/solid-gauge.js"></script>
 <script type="text/javascript" src="${configRoot}/jsp/cockpit/exception.js"></script> 

</head>

<body>
<div class="common_wrap">
	<div class="knowledgeDB">
		<div class="knowledgeDB_con">
			<div class="firstWay">
				<div class="knowledgeDB_chart clearfix">
					<div class="knowledgeDB_chart_item">
						<div class="knowledgeDB_chart_title">今日异常累计数量</div>
						<div id="chart2" style="width:435px; height:350px; margin: 0 auto"></div>
					</div>
					<div class="knowledgeDB_chart_item">
						<div class="knowledgeDB_chart_title">当月异常累计次数</div>
						<div id="chart3" style="width:435px; height:350px; margin: 0 auto"></div>
					</div>
					
					<div class="knowledgeDB_chart_item" style="width:1050px;">
						<div class="knowledgeDB_chart_title">
						<span id="nowYear">
							<script type="text/javascript">
							var myDate = new Date();
							var year= myDate.getFullYear();
							$("#nowYear").text(year);
							</script>
						</span>年异常次数情况
						</div>

						<div id="chart5" style="width:1000px; height:350px; margin: 0 auto"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
