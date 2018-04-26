/*
 * 用户界面初始化
 */
function init() {
	getServerInfo();
	getDatabaseInfo();
	getTomcatInfo();
	getSystemInfo();
}
function getServerInfo() {
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/server/getServerListNoPage.do",
		data : {
			isUse : "0"
		},
		success : function(data) {
			var html = "";
			for ( var i in data) {
				var con = data[i];
				
				var status = "";
				if (con.status == '0') {
					status = "light_normal.png";
				} else {
					status = "light_red.png";
				}
				var num = parseInt(i)+1;
				var lastCheckDate=con.lastCheckDate == null ? "" :con.lastCheckDate;
				html += "<div style='background:#f8f8f8; padding:5px 10px; margin-bottom:5px;margin-left:20px; margin-right:20px;'>"
						+"<span style='margin-right:5px;'>【"+num+"】</span>"
						+"<a href='javascript:showMonitorDetail(\""+con.id+"\",\"server\",\""+con.status+"\");'>"
						+ con.name + "(" + con.ip + ")</a>";
				html += "<span class='lastCheckDate'>最近检查时间："
					+ lastCheckDate+ "</span>";
				html += "<span  class='status_light' ><img src='../../images/"
						+ status + "' width='24' height='24' /></span>";
				html +="<span class='checkCount' >" +
						"<div class='count_loading'>" +
							"<div id='number_loading'>"+con.checkCount+"</div>" +
							"<div id='loading'>"+
								" <div class='bl_loading'></div>" +
								"<div class='tr_loading'></div>" +
							"</div>" +
						"</div>"+
						"</span>";
				html += "</div>";
			}
			$("#server").html(html);
		}
	});
}

function getDatabaseInfo() {
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/database/getDatabaseListNoPage.do",
		data : {
			isUse : "0"
		},
		success : function(data) {
			var html = "";
			for ( var i in data) {
				var con = data[i];

				var status = "";
				if (con.dataBaseNetStatus == '0') {
					status = "light_normal.png";
				} else {
					status = "light_red.png";
				}
				var num = parseInt(i)+1;
				var lastCheckDate=con.lastCheckDate == null ? "":con.lastCheckDate;
				html += "<div style='background:#f8f8f8; padding:5px 10px; margin-bottom:5px;margin-left:20px; margin-right:20px;'>"
						+"<span style='margin-right:5px;'>【"+num+"】</span>"
						+"<a href='javascript:showMonitorDetail(\""+con.id+"\",\"database\",\""+con.dataBaseNetStatus+"\");'>"
						+ con.name + "(" + con.dataBaseIp + ")</a>";
				html += "<span class='lastCheckDate'>最近检查时间："
					+ lastCheckDate+ "</span>";
				html += "<span  class='status_light' ><img src='../../images/"
						+ status + "' width='24' height='24' /></span>";
				html +="<span  class='checkCount' >" +
						"<div class='count_loading'>" +
							"<div id='number_loading'>"+con.checkCount+"</div>" +
							"<div id='loading'>"+
								" <div class='bl_loading'></div>" +
								"<div class='tr_loading'></div>" +
							"</div>" +
						"</div>"+
						"</span>";
				html += "</div>";
			}
			$("#database").html(html);
		}
	});
}
function getTomcatInfo(id) {
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/middleware/getMiddlewareListNoPage.do",
		data : {
			isUse : "0"
		},
		success : function(data) {
			var html = "";
			for ( var i in data) {
				var con = data[i];

				var status = "";
				if (con.status == '0') {
					status = "light_normal.png";
				} else {
					status = "light_red.png";
				}
				var num = parseInt(i)+1;
				var url = con.ip.replace("http://", "")
						.replace("/manager", "");
				var ip = url.split(":")[0];
				var lastCheckDate=con.lastCheckDate == null ? "":con.lastCheckDate;
				html += "<div style='background:#f8f8f8; padding:5px 10px; margin-bottom:5px;margin-left:20px; margin-right:20px;'>"
						+"<span style='margin-right:5px;'>【"+num+"】</span>"
						+"<a href='javascript:showMonitorDetail(\""+con.id+"\",\"middleware\",\""+con.status+"\");'>"
						+ con.name + "(" + ip + ")</a>";
				html += "<span class='lastCheckDate'>最近检查时间："
					+ lastCheckDate+ "</span>";
				html += "<span  class='status_light' ><img src='../../images/"
						+ status + "' width='24' height='24' /></span>";
				html +="<span  class='checkCount' >" +
						"<div class='count_loading'>" +
							"<div id='number_loading'>"+con.checkCount+"</div>" +
							"<div id='loading'>"+
								" <div class='bl_loading'></div>" +
								"<div class='tr_loading'></div>" +
							"</div>" +
						"</div>"+
						"</span>";
				html += "</div>";
			}
			$("#tomcat").html(html);
		}
	});
}
function getSystemInfo() {
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/appSystem/getSystemListNoPage.do",
		data : {
			isUse : "0"
		},
		success : function(data) {
			var html = "";
			for ( var i in data) {
				var con = data[i];

				var status = "";
				if (con.appStatus == '0') {
					status = "light_normal.png";
				} else {
					status = "light_red.png";
				}
				var num = parseInt(i)+1;
				var lastCheckDate=con.lastCheckDate == null ? "":con.lastCheckDate;
				html += "<div style='background:#f8f8f8; padding:5px 10px; margin-bottom:5px;margin-left:20px; margin-right:20px;'>"
						+"<span style='margin-right:5px;'>【"+num+"】</span>"
						+"<a href='javascript:showMonitorDetail(\""+con.id+"\",\"system\",\""+con.appStatus+"\");'>"
						+ con.appName + "(" + con.appIp + ")</a>";
				html += "<span class='lastCheckDate' >最近检查时间："
					+ lastCheckDate + "</span>";
				html += "<span class='status_light' ><img src='../../images/"
						+ status + "' width='24' height='24' /></span>";
				html +="<span  class='checkCount' >" +
						"<div class='count_loading'>" +
							"<div id='number_loading'>"+con.checkCount+"</div>" +
							"<div id='loading'>"+
								" <div class='bl_loading'></div>" +
								"<div class='tr_loading'></div>" +
							"</div>" +
						"</div>"+
						"</span>";
				html += "</div>";
			}
			$("#system").html(html);
		}
	});
}

function searchLoginLog() {
	oParams.projectName = $("input[name='sAppName']").val() == null ? "" : $(
			"input[name='sAppName']").val();
	init();
}
function oneDeleteServer(id) {
	if (confirm("你确定要删除该条记录吗？")) {
		$.ajax({
			type : 'post',
			url : Config.ROOT + "/server/deleteServer.do",
			data : {
				ids : id
			},
			success : function(con) {
				if (con.data == "ok") {
					alert("删除成功");
					init();
				} else {
					alert("删除失败");
				}
			}
		});
	}
}
function oneDeleteSystem(id) {
	if (confirm("你确定要删除该条记录吗？")) {
		$.ajax({
			type : 'post',
			url : Config.ROOT + "/appSystem/deleteAppSystem.do",
			data : {
				ids : id
			},
			success : function(con) {
				if (con.data == "ok") {
					alert("删除成功");
					init();
				} else {
					alert("删除失败");
				}
			}
		});
	}
}
function oneDeleteDatabase(id) {
	if (confirm("你确定要删除该条记录吗？")) {
		$.ajax({
			type : 'post',
			url : Config.ROOT + "/database/delete.do",
			data : {
				ids : id
			},
			success : function(con) {
				if (con.data == "ok") {
					alert("删除成功");
					init();
				} else {
					alert("删除失败");
				}
			}
		});
	}
}
function oneDeleteTomcat(id) {
	if (confirm("你确定要删除该条记录吗？")) {
		$.ajax({
			type : 'post',
			url : Config.ROOT + "/tomcat/delete.do",
			data : {
				ids : id
			},
			success : function(con) {
				if (con.data == "ok") {
					alert("删除成功");
					init();
				} else {
					alert("删除失败");
				}
			}
		});
	}
}
// 修改
function editDatabase(id) {
	parent.layer.open({
		type : 2,
		title : '修改数据库',
		shadeClose : true,
		area : [ '600px', '90%' ],
		content : 'jsp/daily/database/updateDatabase.jsp?id=' + id
	});
};
// 修改
function editTomcat(id) {
	parent.layer.open({
		type : 2,
		title : '修改中间件',
		shadeClose : true,
		area : [ '750px', '80%' ],
		content : 'jsp/daily/tomcat/updateTomcat.jsp?id=' + id
	});
};

// 修改
function editSystemState(id) {
	parent.layer.open({
		type : 2,
		title : '修改系统',
		shadeClose : true,
		area : [ '600px', '90%' ],
		content : 'jsp/daily/updateSystemState.jsp?id=' + id
	});
};

// 修改
function editServer(id) {
	parent.layer.open({
		type : 2,
		title : '服务器',
		shadeClose : true,
		area : [ '900px', '90%' ],
		content : 'jsp/daily/updateServerMonitoring.jsp?id=' + id

	});
};
function changeIsUseServer(id, isUse) {
	var params = {};
	params.id = id;
	params.val = isUse;
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/server/setServerUse.do",
		data : params,
		success : function(con) {
			if (con.data == "ok") {
				init();
			} else {
				alert("服务器出错");
			}
		}
	});
}
function changeIsUseSystem(id, isUse) {
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/appSystem/setAppSystemUse.do",
		data : {
			id : id,
			val : isUse
		},
		success : function(con) {
			if (con.data == "ok") {
				init();
			} else {
				alert("服务器出错");
			}
		}
	});
}
function changeIsUseDatabase(id, isUse) {
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/database/setUse.do",
		data : {
			id : id,
			val : isUse
		},
		success : function(con) {
			if (con.data == "ok") {
				init();
			} else if (con.data == "noLogin") {
				alert("您还未登录，请登录！");
				top.window.location.href = Config.ROOT + "/login.jsp";
			} else {
				alert("服务器出错");
			}
		}
	});
}
function changeIsUseTomcat(id, isUse) {
	$.ajax({
		type : 'post',
		url : Config.ROOT + "/tomcat/setUse.do",
		data : {
			id : id,
			val : isUse
		},
		success : function(con) {
			if (con.data == "ok") {
				init();
			} else if (con.data == "noLogin") {
				alert("您还未登录，请登录！");
				top.window.location.href = Config.ROOT + "/login.jsp";
			} else {
				alert("服务器出错");
			}
		}
	});
}
function show(className){
	$("#"+className).show();
	$("."+className+"Hide").css("display","");
	$("."+className+"Show").css("display","none");
}
function hide(className){
	$("#"+className).hide();
	$("."+className+"Show").css("display","");
	$("."+className+"Hide").css("display","none");
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
		content : 'jsp/cockpit/monitor/tomcatDetail.jsp?id=' + id+'&status='+status+'&type='+type
	});
}