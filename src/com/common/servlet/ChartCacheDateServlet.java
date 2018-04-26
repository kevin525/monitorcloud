package com.common.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.alibaba.fastjson.JSON;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.WarningLogService;
import com.common.constants.Constant;
import com.common.context.ApplicationContextUtil;
import com.common.readProperties.Config;
import com.common.utils.TimeUtil;
import com.sys.util.MemcachedUtil;

@WebServlet("/chartCache")
public class ChartCacheDateServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	String flag = Config.getAttribute("isUseMemcache");
    	if(flag != null && "true".equals(flag)){
    		createChartCache();
    	}
    }
	
	public static void createChartCache(){
		createChartCacheByDay();
		createChartCacheByYear();
		createChartCacheByMonth();
	}
   
	/**
	 * @Title: createChartCacheByYear
	 * @Description: 按年份缓存异常数据
	 * @return void
	 * @throws
	 */
	public static void createChartCacheByYear(){
		WarningLogService warningLogService = (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
		String year = TimeUtil.GetCurYear();
		int[] serverCount  = warningLogService.getServerCount(year);
		int[] databaseCount = warningLogService.getDatabaseCount(year);
		int[] middlewareCount = warningLogService.getMiddlewareCount(year);
		int[] systemcount = warningLogService.getMiddlewareCount(year);
		MemcachedUtil client = MemcachedUtil.getInstance(); 
		client.set("chart:servercount:exception", JSON.toJSONString(serverCount));
		client.set("chart:databasecount:exception", JSON.toJSONString(databaseCount));
		client.set("chart:middlewarecount:exception", JSON.toJSONString(middlewareCount));
		client.set("chart:systemcount:exception", JSON.toJSONString(systemcount));
		client.set("chart:year:exception", year);
	}
	
	/**
	 * @Title: createChartCacheByYear
	 * @Description: 按日期缓存异常数据
	 * @return void
	 * @throws
	 */
	public static void createChartCacheByDay(){
		
		String yearMonthDay = TimeUtil.GetCurDateEx();
		String YearMont = TimeUtil.GetCurYearMonth();

		MemcachedUtil client = MemcachedUtil.getInstance(); 
		client.set("chartDay:count:exception", getCount(yearMonthDay));
		client.set("chartDay:totalCount:exception", getCount(YearMont));
		client.set("chart:day:exception", yearMonthDay);
	}
	public static Integer getCount(String time){
		WarningLogService warningLogService = (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
		//服务器
		int serverCount =warningLogService.getWarningLogCount(new WarningLog(time, Constant.EXCEPTION_STATUS, Constant.DICT_SERVER));
		//数据库
		int databaseCount =warningLogService.getWarningLogCount(new WarningLog(time, Constant.EXCEPTION_STATUS, Constant.DICT_DATABASE));
		//中间件
		int middlewareCount =warningLogService.getWarningLogCount(new WarningLog(time, Constant.EXCEPTION_STATUS, Constant.DICT_MIDDLEWARE));
		
		int systemCount =warningLogService.getWarningLogCount(new WarningLog(time, Constant.EXCEPTION_STATUS, Constant.DICT_APP_SYSTEM));
	
		int count =serverCount+databaseCount+middlewareCount+systemCount;
		return count;
	}
	
	/**
	 * @Title: createChartCacheByYear
	 * @Description: 按月份缓存异常数据
	 * @return void
	 * @throws
	 */
	public static void createChartCacheByMonth(){
		WarningLogService warningLogService = (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
		String month = TimeUtil.GetCurYearMonth();
		int serverCount =warningLogService.getWarningLogCount(new WarningLog(month,Constant.EXCEPTION_STATUS,Constant.DICT_SERVER));
		int databaseCount =warningLogService.getWarningLogCount(new WarningLog(month,Constant.EXCEPTION_STATUS,Constant.DICT_DATABASE));
		int middlewareCount =warningLogService.getWarningLogCount(new WarningLog(month,Constant.EXCEPTION_STATUS,Constant.DICT_MIDDLEWARE));
		int appsystemCount =warningLogService.getWarningLogCount(new WarningLog(month,Constant.EXCEPTION_STATUS,Constant.DICT_APP_SYSTEM));
		MemcachedUtil client = MemcachedUtil.getInstance(); 
		client.set("chartMonth:servercount:exception", serverCount);
		client.set("chartMonth:databasecount:exception", databaseCount);
		client.set("chartMonth:middlewarecount:exception", middlewareCount);
		client.set("chartMonth:appsystemcount:exception", appsystemCount);
		client.set("chartMonth:month:exception", month);
	}
}
