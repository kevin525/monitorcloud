package com.apps.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.database.service.DatabaseService;
import com.common.context.ApplicationContextUtil;
import com.common.utils.HttpUtil;
import com.common.utils.UnicomSmsUtil;
import com.sys.util.Config;
import com.sys.util.DBUtil;

/**
 * 
 * @ClassName: AutoTzoawhwebTask 
 * @Description: 定时监测运维平台是否正常运行
 * @author 张梦琦 
 * @date 2018年3月5日 上午10:08:10
 */
public class AutoMonitorTzoawhwebTask {
  
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DatabaseService databaseService = (DatabaseService) ApplicationContextUtil.getBean("databaseService");
	
	/**
	 * @Title: mointorDatabase
	 * @Description: 监控正在使用的数据库
	 * @return void
	 * @throws
	 */
	public void mointorTzoawhweb(){
		System.out.println("-------------"+sdf.format(new Date())+"监测平台正常运行开始---------------");
		List<AsopDatabase> list = null;
		String content="";
		try {
			list = databaseService.getList(null);
			content ="云智能运维监控管理平台运行正常!";
		} catch (Exception e) {
			content ="云智能运维监控管理平台运行异常!";
		}
		UnicomSmsUtil.sendSmsByGet(content, Config.getAttribute("phoneNum"), null);
		System.out.println("-------------"+sdf.format(new Date())+"监测平台正常运行结束---------------");
		
		
	}
	
	
}
