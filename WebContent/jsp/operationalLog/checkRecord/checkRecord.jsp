<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>巡检记录</title>
<%@ include file="/WEB-INF/jsp/common/initVariables.jsp"%>
<link href="${configRoot}/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${configRoot}/jsp/operationalLog/checkRecord/checkRecord.js"></script>
<script type="text/javascript" src="${configRoot}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${configRoot}/js/osplugins/layer/layer.js"></script>
</head>

<body>
	<div class="layui-tab layui-tab-brief" lay-filter="demo">
		<ul class="layui-tab-title">
			<li lay-id="11" class="layui-this" onclick="getModel();" >巡检单</li>
			<li lay-id="22" onclick="getRecord();">巡检日志</li>
			<li lay-id="33">巡检项</li>
		</ul>
		<div class="layui-tab-content" style="height: 100px;">
			<div class="layui-tab-item layui-show">
				 <div class="common_table_bd" style="margin:0 auto;height:450px;overflow-y:scroll;overflow-x:hidden;">
					<table width="90%" style="margin:0 auto;">
							<tr align="center">
								<td class="tableleft" style="height:30px;line-height:30px;" align="center">
									<span style="color:red;">*</span>运维记录名称
								</td>
								<td  colspan="2">
									<input type="text" style="width:90%;height:90%;" name="name" id="name" value="运维记录"/>
								</td>
							</tr>
							<tr>
								<td class="tableleft"  style="height:30px;line-height:30px;" align="center">
									<span style="color:red;">*</span>运行环境
								</td>
								<td colspan="2" >
									<input type="radio" name="environment"  id="environment" value="0"/>正式环境&nbsp;&nbsp;
									<input type="radio" name="environment"  id="environment" value="1" checked="checked"/>测试环境
								</td>
							</tr>
							<tr align="center">
								<td class="tableleft" style="height:30px;line-height:30px;" align="center">
									备注
								</td>
								<td colspan="2">
									<input type="text" style="width:90%;height:90%;" name="remark" id="remark" />
								</td>
							</tr>
						<tbody id="mbody_from" >
						
						</tbody>
						<tr> <td colspan='3' align="center">
							<button class="layui-btn layui-btn-normal" onclick="add()">添加</button>
						</td></tr>
					</table><br></br>
				 </div> 
			</div>
			<div class="layui-tab-item">
				
				 <div style="margin-top:10px;margin-left:10px;">
					<blockquote class="layui-elem-quote">
					    <span class="common_search_fieldName">巡检日期：</span>
					    <input type="text" class="common_search_ipt" name="sAppName" placeholder="请选择日期" style="margin-right:20px;" />
					     <button onclick="searchLoginLog()" class="layui-btn layui-btn-sm">查&nbsp;&nbsp;询</button>
					    <button style="float:right;" id="common_btn_delete" class="layui-btn layui-btn-sm layui-btn-normal" ><i class="layui-icon"></i> 批量删除</button>
					    <button style="float:right;" id="common_btn_add" data-type="tabChange" class="layui-btn layui-btn-sm"><i class="layui-icon"></i>新增</button>
					</blockquote>
					
				</div>
				
				<div class="common_table">
					<div class="common_table_bd">
						<table width="100%">
							<thead>
								<tr>
									<th width="60"><input type="checkbox" onclick="qx(this)"></th>
									<th width="60">序号</th>
									<th>名称</th>
									<th>责任人</th>
									<th>巡检环境</th>
									<th>巡检日期</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="mbody">
							
							</tbody>
						</table>
						<!-- 分页 -->
						<div class="pageCount" id="pageId"></div>

					</div>
				</div> 
			</div>
			<div class="layui-tab-item">
				<iframe frameborder="0" id="iframe2" height="500"
					src="${configRoot}/jsp/operationalLog/checkRecord/checkRecordModel.jsp"
					width="100%"></iframe> 
					
			</div>
		</div>
	</div>
<script src="../../js/layui/layui.js"></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
	<script>
		layui.use('element', function() {
			 var $ = layui.jquery, element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
			 var active = {
			    tabChange: function(){
			      //切换到指定Tab项
			      element.tabChange('demo', '11'); //切换到：用户管理
			    }
			  }; 
			  $('#common_btn_add').on('click', function(){
				    var othis = $(this), type = othis.data('type');
				    active[type] ? active[type].call(this, othis) : '';
				  }); 
		}); 
	</script>
	<script type="text/javascript">
		$(document).ready(function(e) {
			getModel();
			
			$("#common_btn_delete").click(function(){
			    	var checkval = getCheckedValue("systemstate");
			    	if(!checkval || checkval.length<1){
			    		parent.layer.confirm('请选择要删除的记录！', {
			    			  btn: ['关闭'] //按钮
			    		});
			    	}else{
			    		parent.layer.confirm('您确定要删除该条记录吗？', {
			    			  btn: ['确定','取消'] //按钮
			    		}, function(){
			    			  $.ajax({
			    					type : 'post',
			    					url : Config.ROOT+"/opsCheckRecord/delete.do",
						 			data : {
						 				ids : checkval
									},
			    					success : function(con) {
			    						if(con.data=="ok"){
			    							parent.layer.msg('删除成功', {icon: 1,time: 500});
			    							getRecord();
			    						}else{
			    							parent.layer.msg('删除失败', {icon: 2,time: 500});
			    						}
			    					}
			    				});
			    		});
			    	}
			    });
		 });
	</script>
	
</body>
</html>
