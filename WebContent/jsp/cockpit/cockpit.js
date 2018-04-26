var run = 0;
$(function(){
	buildFirstMap();//项目预警情况
	getMainTotal();//运维总体数量
	setTimeout('myrefresh()',10*60*1000);
});
function myrefresh() 
{ 
window.location.reload(); 
} 

//项目预警情况
function buildFirstMap(){
	 var options = {
		chart: {
	        type: 'tilemap',
	        inverted: true,
	        height: '95%'
	    },
	    title: {
	        text: ''
	    },
	    credits:{
	    	enabled:false
	    },
	    xAxis: {
	        visible: false
	    },
	    yAxis: {
	        visible: false
	    },
	    colorAxis: {
	        dataClasses: [{
	            from: 0,
	            to: 0,
	            color: '#008000',
	            name: '正常'
	        }, {
	            from: 1,
	            color: '#CE0000',
	            name: '异常'
	        }]
	    },
	    tooltip: {
			    headerFormat: '',
		        pointFormat:' <b> {point.name}</b> '
	    },
	    plotOptions: {
	        series: {
	            dataLabels: {
	                enabled: true,
	                format: '{point.hc-a2}',
	                color: '#ffffff',
	                style: {
	                    textOutline: false
	                }
	            }
	        }
	    },
	    series: [{
	        name: '预警情况',
	        data: [],
	        cursor: 'pointer', 
	        events: { 
                click: function(e) {
                    showMonitorDetail(e.point.id,e.point.type,e.point.value);
                } 
            } 
	    }]
	}
	  $.ajax({
			type : 'post',
			url : Config.ROOT+"/monitor/monitorInfo.do",
			success : function(contents) {
				 if(contents && contents.data){
					// loadRealtimeData(contents.data.system,"breakNews_system");
					 loadRealtimeData(contents.data.server,"breakNews_server");
					 loadRealtimeData(contents.data.database,"breakNews_database");
					 loadRealtimeData(contents.data.middleware,"breakNews_middleware");
					 $(contents.data.totalList).each(function(i,obj){
						 var state="";
						 if(obj.status==0){
							 state="【"+obj.typeName+"】"+obj.name+":运行正常";
						 }else  if(obj.status==1){
							 state="【"+obj.typeName+"】"+obj.name+":运行异常";
						 }
						 options.series[0].data.push({
							 	'hc-a2': obj.name,
							   	name:state,
					            x: i,
					            y: (i+1)%3,
					            value:parseInt(obj.status),
					            id:obj.id,
					            type:obj.type
						})
					 });
					 
					 
					 Highcharts.chart('chart1', options);
					
				 }
			}
		}); 
}
function showMonitorDetail(id,type,status){
	if(type=="server"){
		buildServerMonitor(id,type,status);//构建服务器详情
	}else if(type=="system"){
		buildAppSystemMonitor(id,type,status);//构建应用系统详情
	}else if(type=="database"){
		buildDatabaseMonitor(id,type,status);//构建数据库详情
	}else if(type=="middleware"){
		buildMiddlewareMonitor(id,type,status);//构建中间件详情
	}
}

function buildServerMonitor(id,type,status){
	parent.layer.open({
		type : 2,
		title : '服务器预警监控',
		shadeClose : true,
		area : [ '900px', '75%' ],
		content : 'jsp/cockpit/monitor/serverDetail.jsp?id=' + id+'&status='+status+'&type='+type
	});
}

function buildAppSystemMonitor(id,type,status){
	parent.layer.open({
		type : 2,
		title : '应用系统预警监控',
		shadeClose : true,
		area : [ '900px', '75%' ],
		content : 'jsp/cockpit/monitor/systemDetail.jsp?id=' + id+'&status='+status+'&type='+type
	});
}

function buildDatabaseMonitor(id,type,status){
	parent.layer.open({
		type : 2,
		title : '数据库预警监控',
		shadeClose : true,
		area : [ '900px', '80%' ],
		content : 'jsp/cockpit/monitor/databaseDetail.jsp?id=' + id+'&status='+status+'&type='+type
	});
}

function buildMiddlewareMonitor(id,type,status){
	parent.layer.open({
		type : 2,
		title : '中间件预警监控',
		shadeClose : true,
		area : [ '900px', '80%' ],
		content : 'jsp/cockpit/monitor/middlewareDetail.jsp?id=' + id+'&status='+status+'&type='+type
	});
}

/**
 * 该方法暂没用到
 * 有参数，表示鼠标放在该面板，显示该单条数据
 */
function onRealtimeMonitorOne(param) {
	if (run == 1) {
		clearInterval(sh);
		count = 4;
		fromOneToTarget(param);
	}
}

/**
 * 该方法，暂没用到
 * 没有参数，表示鼠标离开该面板
 */
function outRealtimeMonitorOne() {
	if (run == 1) {
		count = 0;
	}
}

/**
 * 加载实时监控数据
 * @param realtimeData
 */
function loadRealtimeData(realtimeData,breakNews) {
	var root = $("#"+breakNews);
	var monitor = '<ul id="'+breakNews+'List" style="height:140px;overflow:hidden;">';
	var redLight = "<img src='../../images/light_red.png' width='24' height='24' />";
	var normalLight = "<img src='../../images/light_normal.png' width='24' height='24' />";
	for (var i = 0; i < realtimeData.length; i++) {
		var rtd = realtimeData[i];
		var monitorEvent = "showMonitorDetail(\""+rtd.id+"\",\""+rtd.type+"\",\""+rtd.status+"\")";
		if(rtd.status=='0'){
			monitor += '<li class="clearfix" style="height:40px;line-height:40px;cursor:pointer;" onclick='+monitorEvent+'>';
			monitor += '<span class="ipAddress">【'+ rtd.typeName + '】</span>';
			monitor += '<span class="ipAddress">' + rtd.name+ '</span>';
			monitor += '<span class="ipAddress" style="float:right;">'+ normalLight + '</span></li>';
		}else{
			monitor += '<li class="clearfix" style="height:40px;line-height:40px;cursor:pointer;" onclick='+monitorEvent+'>';
			monitor += '<span class="ipAddress"><font style="color:red;">【'+ rtd.typeName + '】</font></span>';
			monitor += '<span class="ipAddress"><font style="color:red;">' + rtd.name+ '</font></span>';
			monitor += '<span class="ipAddress" style="float:right;">'+ redLight + '</span></li>';
		}
		
	}
	monitor += '</ul>';
	root.html(monitor);
	
	var scroll2 = new ScrollText(breakNews+"List", "pre2", "next2",true, 150, true,realtimeData.length);
}


function getMainTotal(){
	$.ajax({
		type : 'post',
		url : Config.ROOT+"/project/getMainTotal.do",
		success : function(data) {
			 $("#server").html(data[1]);
			 $("#database").html(data[2]);
			 $("#middleware").html(data[3]);
			 $("#system").html(data[4]);
				
		}
	}); 
}

function ScrollText(content, btnPrevious, btnNext, autoStart, timeout,
		isSmoothScroll,count) {
	this.Speed = 10;
	this.Timeout = timeout;
	this.stopscroll = false;// 是否停止滚动的标志位
	this.isSmoothScroll = isSmoothScroll;// 是否平滑连续滚动
	this.LineHeight = 25;// 默认高度。可以在外部根据需要设置
	this.NextButton = this.$(btnNext);
	this.PreviousButton = this.$(btnPrevious);
	this.ScrollContent = this.$(content);
	if(count>2){
		this.ScrollContent.innerHTML += this.ScrollContent.innerHTML;// 为了平滑滚动再加一遍
	}
	
	if (this.PreviousButton) {
		this.PreviousButton.onclick = this.GetFunction(this, "Previous");
		this.PreviousButton.onmouseover = this.GetFunction(this, "MouseOver");
		this.PreviousButton.onmouseout = this.GetFunction(this, "MouseOut");
	}
	if (this.NextButton) {
		this.NextButton.onclick = this.GetFunction(this, "Next");
		this.NextButton.onmouseover = this.GetFunction(this, "MouseOver");
		this.NextButton.onmouseout = this.GetFunction(this, "MouseOut");
	}
	this.ScrollContent.onmouseover = this.GetFunction(this, "MouseOver");
	this.ScrollContent.onmouseout = this.GetFunction(this, "MouseOut");
	if (autoStart) {
		this.Start();
	}
}
ScrollText.prototype = {
	$ : function(element) {
		return document.getElementById(element);
	},
	Previous : function() {
		this.stopscroll = true;
		this.Scroll("up");
	},
	Next : function() {
		this.stopscroll = true;
		this.Scroll("down");
	},
	Start : function() {
		if (this.isSmoothScroll) {
			this.AutoScrollTimer = setInterval(this.GetFunction(this,
					"SmoothScroll"), this.Timeout);
		} else {
			this.AutoScrollTimer = setInterval(this.GetFunction(this,
					"AutoScroll"), this.Timeout);
		}
	},
	Stop : function() {
		clearTimeout(this.AutoScrollTimer);
		this.DelayTimerStop = 0;
	},
	MouseOver : function() {
		this.stopscroll = true;
	},
	MouseOut : function() {
		this.stopscroll = false;
	},
	AutoScroll : function() {
		if (this.stopscroll) {
			return;
		}
		this.ScrollContent.scrollTop++;
		if (parseInt(this.ScrollContent.scrollTop) % this.LineHeight != 0) {
			this.ScrollTimer = setTimeout(this.GetFunction(this, "AutoScroll"),
					this.Speed);
		} else {
			if (parseInt(this.ScrollContent.scrollTop) >= parseInt(this.ScrollContent.scrollHeight) / 2) {
				this.ScrollContent.scrollTop = 0;
			}
			clearTimeout(this.ScrollTimer);
			// this.AutoScrollTimer =
			// setTimeout(this.GetFunction(this,"AutoScroll"), this.Timeout);
		}
	},
	SmoothScroll : function() {
		if (this.stopscroll) {
			return;
		}
		this.ScrollContent.scrollTop++;
		if (parseInt(this.ScrollContent.scrollTop) >= parseInt(this.ScrollContent.scrollHeight) / 2) {
			this.ScrollContent.scrollTop = 0;
		}
	},
	Scroll : function(direction) {
		if (direction == "up") {
			this.ScrollContent.scrollTop--;
		} else {
			this.ScrollContent.scrollTop++;
		}
		if (parseInt(this.ScrollContent.scrollTop) >= parseInt(this.ScrollContent.scrollHeight) / 2) {
			this.ScrollContent.scrollTop = 0;
		} else if (parseInt(this.ScrollContent.scrollTop) <= 0) {
			this.ScrollContent.scrollTop = parseInt(this.ScrollContent.scrollHeight) / 2;
		}
		if (parseInt(this.ScrollContent.scrollTop) % this.LineHeight != 0) {
			this.ScrollTimer = setTimeout(this.GetFunction(this, "Scroll",
					direction), this.Speed);
		}
	},
	GetFunction : function(variable, method, param) {
		return function() {
			variable[method](param);
		}
	}
}
function ignoreError() {
	return true;
}
window.onerror = ignoreError;