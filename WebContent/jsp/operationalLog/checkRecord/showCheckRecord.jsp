<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>巡检记录</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${configRoot}/css/style.css" />
<script type="text/javascript" src="${configRoot}/jsp/operationalLog/checkRecord/checkRecord.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>

<style type="text/css">
.yseno{
	margin-left: 10px;
	display: inline-block;
}
</style>
<script language="javascript">
var id='<%=request.getParameter("id")%>';
var params ={};
$(document).ready(function(e) {
	$.ajax({
		type : 'post',
		url : Config.ROOT+"/opsCheckRecord/getRecordById.do",
		data: {id : id},
		success : function(con) {
			var data = con.data;
			if(null != data ){
				var html="";
				var aa=[];
				$(data).each(function(i){
					var nodeValue=data[i].opsCheckRecordModel.nodeValue;
					if(nodeValue==1){
						html += "<tr><td>"+data[i].opsCheckRecordModel.name+"</td>";
						html += "<td>"+data[i].state+"</td>";
						html += "<td>"+data[i].opsCheckRecordModel+"</td>";
						html += "</tr>"
					} else{
						aa.push(data[i].opsCheckRecordModel.fatherId);
					}
					
				});	
				alert(aa);
				$(data).each(function(j){
					var nodeValue=data[j].opsCheckRecordModel.nodeValue;
					if(nodeValue==2){
						html += "<tr><td>"+data[j].opsCheckRecordModel.name+"</td>";
						html += "<td>"+data[j].state+"</td></tr>";
					} 
					
				});
				$("table").html(html);
			}else{
				alert("数据异常");
				closeLayer(true);
			}
		}
	});
	
});

</script>
</head>
<body>
private long id;
	//所属记录表id
	private long checkRecordId;
	
	//权限主键检查项id
	private long modelId;
	
	//检查项值 0：正常  1 异常
	private String state;
	                           
	
	private String name;
	
	private String remark;
	//责任人
	private String personLiable;
	//创建人ID
	private long createUserId;
	//创建时间
	private String createDate;
	
	//运行环境：0：正式环境，1：测试环境
	private String environment;
	//是否启用，0：启用，2：删除
	private String isUse;
	<table class="table table-bordered table-hover m10">
		<!-- <tr align="center">
			<td class="tableleft" style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>巡检项名称
			</td>
			<td><input type="text" style="width:400px;" name="name" id="name" /></td>
			
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>巡检项别名</td>
			<td><input type="text"  style="width:400px;" name="shortName" id="shortName" /></td>
		</tr>
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>类别</td>
			<td><input type="radio" name="nodeValue"   value="1" checked="checked" onclick="hideParentList()"/>一级巡检项
			&nbsp;&nbsp;<input type="radio" name="nodeValue"   value="2" onclick="showParentList()"/>二级巡检项
			</td>
		</tr>
		
		<tr align="center" style="display:none" id="firstMenu">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>一级巡检项</td>
			<td><div id="fatherIdHtml"></div></td>
		</tr>
		
		<tr align="center">
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>排序</td>
			<td><input type="text" style="width:400px;" name="modelOrder" id="modelOrder" /></td>
		</tr>
		<tr>
			<td class="tableleft"  style="height:30px;line-height:30px;" align="center"><span style="color:red;">*</span>是否启用</td>
			<td><input type="radio" name="isUse"  value="0"/>启用
			&nbsp;&nbsp;<input type="radio" name="isUse"  value="1" checked="checked"/>禁用</td>
		</tr>
		<tr>
			<td colspan="2">
					<button class="btn btn-primary" type="button" onclick="edit()">修改</button>
					&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="closeLayer(false)">取消</button>
					<span class="span" style="color:red;float:right;width:350px;text-align:right;"></span>
			</td>
		</tr> -->
	</table>


</body>
</html>