<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>监控情况</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/modules/oldie.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/modules/heatmap.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/modules/tilemap.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/highcharts-3d.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/highcharts-more.js"></script>
<script type="text/javascript" src="${configRoot}/js/Highcharts-6.0.2/code/modules/solid-gauge.js"></script>
<script type="text/javascript" src="${configRoot}/jsp/cockpit/cockpit.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery.event.ue.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery.udraggable.js"></script>

</head>

<body>
	<div class="common_wrap">
		<div class="knowledgeDB">
			<div class="knowledgeDB_con">
				<div class="firstWay">
					<div class="knowledgeDB_chart clearfix">
						<div class="knowledgeDB_chart_item_big">
							<div class="knowledgeDB_chart_title"
								style="margin-top: 20px; font-size: 30px;">运维监控预警情况</div>
							<div id="chart1"
								style="width: 750px; height: 750px; margin: 0 auto"></div>
						</div>
						<div class="knowledgeDB_chart_item_small" style="height: 190px;">

							<div class="yuan">
								<div class="yuan_text">
									<span>服务器</span> <span class="count" id="server"></span>
								</div>
							</div>
							<div class="yuan2">
								<div class="yuan_text2">
									<span>数据库</span> <span class="count" id="database"></span>
								</div>
							</div>
							<div class="yuan3">
								<div class="yuan_text3">
									<span>中间件</span> <span class="count" id="middleware"></span>
								</div>
							</div>
							<div class="yuan4">
								<div class="yuan_text4">
									<span>系统</span> <span class="count" id="system"></span>
								</div>
							</div>
						</div>
						<div class="knowledgeDB_chart_item_small" style="height: 400px;">
							<div class="realtimeMonitor">
								<div class="common_table_bd">
									<table width="100%">
										<thead>
											<tr>
												<th style="font-size: 18px;">服务器预警</th>
											</tr>
										</thead>
									</table>
								</div>

								<div id="breakNews_server" class="realtimeMonitor_bd"></div>
							</div>
							<br/>
							<div class="realtimeMonitor">
								<div class="common_table_bd">
									<table width="100%">
										<thead>
											<tr>
												<th style="font-size: 18px;">数据库预警</th>
											</tr>
										</thead>
									</table>
								</div>

								<div id="breakNews_database" class="realtimeMonitor_bd"></div>
							</div>
							<br/>
							<div class="realtimeMonitor">
								<div class="common_table_bd">
									<table width="100%">
										<thead>
											<tr>
												<th style="font-size: 18px;">中间件预警</th>
											</tr>
										</thead>
									</table>
								</div>

								<div id="breakNews_middleware" class="realtimeMonitor_bd">
								</div>
							</div>

						<!-- <div class="realtimeMonitor" >
								<div class="common_table_bd">
									<table width="100%">
										<thead>
											<tr>
												<th style="font-size: 18px;">应用系统预警</th>
											</tr>
										</thead>
									</table>
								</div>
	
								<div id="breakNews_system" class="realtimeMonitor_bd "></div>
							</div> -->
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
