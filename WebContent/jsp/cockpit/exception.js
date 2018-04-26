$(function(){
	buildTodayMap();
	buildMonthMap();
	buildYearMap();
});


//今日异常累计数量
function buildTodayMap(){
	var gaugeOptions = {
	        chart: {
	            type: 'solidgauge'
	        },
	        title: null,
	        pane: {
	            center: ['50%', '85%'],
	            size: '120%',
	            startAngle: -90,
	            endAngle: 90,
	            background: {
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
	                innerRadius: '60%',
	                outerRadius: '100%',
	                shape: 'arc'
	            }
	        },
	        tooltip: {
	            enabled: false
	        },
	        // the value axis
	        yAxis: {
	            stops: [
	                [0.1, '#DF5353'], // red
	                [0.5, '#DF5353'], // '#DDDF0D'], // yellow
	                [0.9, '#DF5353'], // '#55BF3B'] // green
	            ],
	            lineWidth: 0,
	            minorTickInterval: 22,
	            tickPixelInterval: 22,
	            tickWidth: 0,
	            title: {
	                y: -70
	            },
	            labels: {
	                y: 16
	            }
	        },
	        plotOptions: {
	            solidgauge: {
	                dataLabels: {
	                    y: 5,
	                    borderWidth: 0,
	                    useHTML: true
	                }
	            }
	        }
	    };
	   
	    $('#chart2').highcharts(Highcharts.merge(gaugeOptions, {
	        yAxis: {
	        	minorTickPosition:1,
	            min: 0,
	            max:1
	        },
	        credits: {
	            enabled: false
	        },
	        series: [{
	            name: '异常数量',
	            data: [0],
	            cursor: 'pointer',
	            events: { 
	                click: function(e) {
	                    showExceptionList();
	                } 
	            },
	            dataLabels: {
	                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
	                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
	                '<span style="font-size:12px;color:silver">异常数量</span></div>'
	            },
	            tooltip: {
	                valueSuffix: '异常数量'
	            }
	        }]
	    }));
	
	    $.ajax({
			type : 'post',
			url : Config.ROOT+"/monitor/getCurDayStatistics.do",
			success : function(con) {
				var data = con.data;
				var allNum =data.totalCount;
				var abnormal = data.count;
				var chart = $('#chart2').highcharts(),
	            point,point2,
	            max;
	        if (chart) {
	            point = chart.series[0].points[0];
	            point.update(abnormal);
	            chart.update({
	            	yAxis: {
	    	            min: 0,
	    	            tickInterval:Math.ceil(allNum/4),
	    	            max: allNum
	    	        },
	            })
	        }
			}
		}); 
}

function showExceptionList(){
	parent.layer.open({
		type : 2,
		title : '当天异常日志',
		shadeClose : true,
		area : [ '900px', '75%' ],
		content : 'jsp/cockpit/exception/exceptionDay.jsp'
	});
}

//当月
function buildMonthMap(){
	var options = {
		chart: {
			type: 'column'
        },
        title: {
            text: ''
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '次数'
            }
        },
        legend: {
            enabled: false
        },
        credits:{
        	enabled:false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y}'
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> 次<br/>'
        },
        series: [{
            name: '异常类型',
            colorByPoint: true,
            cursor: 'pointer',
            data: []
        }]
    }
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/monitor/getCurMonthStatistics.do",
			success : function(contents) {
					 options.series[0].data.push({
						 name: "服务器",
						 y: contents.data.serverCount,
						 events: {  
							 click: function () {
	                                showMonthList("server");
	                            }
	                       }

					})
					  options.series[0].data.push({
						 name: "数据库",
			             y: contents.data.databaseCount,
			             events: {  
							 click: function () {
	                                showMonthList("database");
	                            }
	                       }
					})
					  options.series[0].data.push({
						 name: "中间件",
			             y: contents.data.middlewareCount,
			             events: {  
							 click: function () {
	                                showMonthList("middleware");
	                            }
	                       }
					})
					  options.series[0].data.push({
						 name: "应用系统",
			             y: contents.data.appsystemCount,
			             events: {  
							 click: function () {
	                                showMonthList("system");
	                            }
	                       }
					})
					 
					 Highcharts.chart('chart3', options);
					
				 }
		}); 
}

function showMonthList(type){
	parent.layer.open({
		type : 2,
		title : '当月异常日志',
		shadeClose : true,
		area : [ '900px', '75%' ],
		content : 'jsp/cockpit/exception/exceptionMonth.jsp?monitorType=' + type
	});
}



function buildYearMap(){
	var options = { 
		chart: {
            type: 'line'
        },
        title: {
            text: ''
        },
        xAxis: {
            categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
        },
        credits:{
        	enabled:false
        },
        yAxis: {
            title: {
                text: '次数'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: true // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [ ]
    }
	 $.ajax({
			type : 'post',
			url : Config.ROOT+"/monitor/getYearMonthStatistics.do",
			success : function(contents) {
					 options.series.push({
						 name: "服务器",
			            data: contents.data.serverCount,
			            cursor: 'pointer',
			             events: {  
							 click: function () {
	                                showYearList("server");
	                            }
	                       }  
					})
					 options.series.push({
						 name: "数据库",
			             data: contents.data.databaseCount,
			             cursor: 'pointer',
			             events: {  
							 click: function () {
	                                showYearList("database");
	                            }
	                       }  
			            
					})
					 options.series.push({
						 name: "中间件",
			             data: contents.data.middlewareCount,
			             cursor: 'pointer',
			             events: {  
							 click: function () {
	                                showYearList("middleware");
	                            }
	                       }  
					})
					 options.series.push({
						 name: "应用系统",
			             data: contents.data.systemCount,
			             cursor: 'pointer',
			             events: {  
							 click: function () {
	                                showYearList("system");
	                            }
	                       }  
					})
					 Highcharts.chart('chart5', options);
					
				 }
		}); 
}
function showYearList(type){
	parent.layer.open({
		type : 2,
		title : '今年异常日志',
		shadeClose : true,
		area : [ '900px', '75%' ],
		content : 'jsp/cockpit/exception/exceptionYear.jsp?monitorType=' + type
	});
}
