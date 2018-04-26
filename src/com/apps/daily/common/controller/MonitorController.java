package com.apps.daily.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.appsystem.service.AppSystemService;
import com.apps.daily.common.view.MonitorInfoView;
import com.apps.daily.database.service.DatabaseService;
import com.apps.daily.middleware.service.MiddlewareService;
import com.apps.daily.server.service.ServerService;
import com.apps.daily.tomcat.service.TomcatService;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.WarningLogService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.common.readProperties.Config;
import com.common.utils.TimeUtil;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.util.MemcachedUtil;

/**
 * @ClassName: MonitorController
 * @Description: 监控预警情况
 * @author LG
 * @date 2018年4月9日 上午10:32:27
 */
@Controller
@RequestMapping(value="/monitor")
public class MonitorController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private AppSystemService appSystemService;
	
	@Autowired
	private ServerService serverService;
	
	@Autowired
	private MiddlewareService middlewareService;
	
	@Autowired
	private DatabaseService databaseService;
	
	@Autowired
	private WarningLogService warningLogService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@ResponseBody
	@RequestMapping("/monitorInfo")
	public JsonForm getMonitorInfo(){
		try {
			 List<MonitorInfoView> monitorViewList = new ArrayList<MonitorInfoView>();
			Map<String, List<MonitorInfoView>> map = new HashMap<String, List<MonitorInfoView>>();
			
			List<MonitorInfoView> systemList =  appSystemService.getAppSystemMonitor();
			map.put(Constant.DICT_APP_SYSTEM,systemList);
			monitorViewList.addAll(systemList);
			
			List<MonitorInfoView> serverList = serverService.getServerMonitor();
			map.put(Constant.DICT_SERVER, serverList);
			monitorViewList.addAll(serverList);
			
			
			List<MonitorInfoView> databaseList =  databaseService.getDatabaseMonitor();
			map.put(Constant.DICT_DATABASE,databaseList );
			monitorViewList.addAll(databaseList);
			
			List<MonitorInfoView> middlewareList =  middlewareService.getMiddlewareMonitor();
			map.put(Constant.DICT_MIDDLEWARE, middlewareList);
			monitorViewList.addAll(middlewareList);
			
			
			map.put("totalList", monitorViewList);
			
			
			return JsonForm.warpJsonForm(true, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(true, Constant.RETURN_FAILED);
	}
	
	
	
	
	
	
	
	/**
	 * @Title: getAppSystemList
	 * @Description: 分页获取应用系统列表
	 * @param appSystem
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getAppSystemList.do")
	public PageGridLoad getAppSystemList(AsopAppSystem appSystem,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopAppSystem> appSystemsist = appSystemService.getAppSystemList(appSystem, pageGridPost);
		postGridLoad.setPageData(appSystemsist);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	/**
	 * @Title: getAppSystemList
	 * @Description: 不分页获取应用系统列表
	 * @param appSystem
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getAppSystemListNoPage.do")
	public PageGridLoad getAppSystemListNoPage(AsopAppSystem appSystem){
		PageGridLoad postGridLoad = new PageGridLoad();
		try {
			List<AsopAppSystem> appSystemsist = appSystemService.getAppSystemList(appSystem);
			postGridLoad.setPageData(appSystemsist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postGridLoad;
	}
	
	/**
	 * @Title: saveOrUpdateAppSystem
	 * @Description: 创建、修改应用系统信息
	 * @param request
	 * @param appSystem
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateAppSystem.do")
	public JsonForm saveOrUpdateAppSystem(HttpServletRequest request,final AsopAppSystem appSystem){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(appSystem !=null){
				if(appSystem.getId() ==0){
					appSystem.setCreateUserId(loginUser.getUserId());
					appSystem.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				}
				appSystem.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				appSystem.setModifyUserId(loginUser.getUserId());
				baseService.saveOrUpdate(appSystem);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 删除应用系统
	 * @param deleteAppSystem
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteAppSystem.do")
	public JsonForm deleteAppSystem(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				appSystemService.deleteAppSystem(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * @Title: getAppSystem
	 * @Description: 获取应用系统信息后，跳转到指定的页面
	 * @param id
	 * @param url
	 * @return
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getAppSystem.do")
	public ModelAndView getAppSystem(long id, String url) {
		ModelAndView mav = new ModelAndView(url);
		mav.addObject("appSystem", baseService.findById(AsopAppSystem.class,id));
		return mav;
	}
	
	/**
	 * 根据id获取应用信息
	 */
	@ResponseBody
	@RequestMapping("/getAppSystemById.do")
	public JsonForm getAppSystemById(long id) {
		
		return JsonForm.warpJsonForm(true, baseService.findById(AsopAppSystem.class,id));
	}
	/**
	 * @Title: setServerUse
	 * @Description: 设置应用系统启用、禁用状态
	 * @param id
	 * @return
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/setAppSystemUse.do")
	public JsonForm setAppSystemUse(long id,String val){
		try {
			if(!StringHelpers.isNull(val)){
				appSystemService.isUseAppSystem(id, val);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	@ResponseBody
	@RequestMapping("/getSystemListNoPage.do")
	public List<AsopAppSystem> getSystemListNoPage(AsopAppSystem asopAppSystem){
		List<AsopAppSystem> asopSystemList = null;
		try {
			asopSystemList = appSystemService.getAppSystemList(asopAppSystem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asopSystemList;
	}
	/**
	 * 获取当年异常列表
	 * @param warningLog
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExceptionYearLogList.do")
	public PageGridLoad getExceptionYearLogList(WarningLog warningLog,PageGridPost pageGridPost){
		String time = TimeUtil.GetCurYear();
		warningLog.setWarnTime(time);
		List<WarningLog> list = warningLogService.getWarningLogList(warningLog,pageGridPost);
		 
		PageGridLoad postGridLoad = new PageGridLoad();
		postGridLoad.setPageData(list);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
		 
	}
	/**
	 * 获取当月异常列表
	 * @param warningLog
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExceptionMonthLogList.do")
	public PageGridLoad getExceptionMonthLogList(WarningLog warningLog,PageGridPost pageGridPost){
		String time = TimeUtil.GetCurYearMonth();
		warningLog.setWarnTime(time);
		List<WarningLog> list = warningLogService.getWarningLogList(warningLog,pageGridPost);
		 
		PageGridLoad postGridLoad = new PageGridLoad();
		postGridLoad.setPageData(list);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
		 
	}
	/**
	 * 当天异常统计
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCurDayStatistics.do")
	public JsonForm getCurDayStatistics(){
		//获得当前日期
		Map<String,Integer> map = new HashMap<String, Integer>();
		
		String yearMonthDay = TimeUtil.GetCurDateEx();
		String YearMont = TimeUtil.GetCurYearMonth();
		String flag = Config.getAttribute("isUseMemcache");
		int count=0;
		int totalCount=0;
		if(flag != null && "true".equals(flag)){
			MemcachedUtil client = MemcachedUtil.getInstance();
			String cacheDay = (String) client.get("chart:day:exception");
			if(!StringHelpers.isNull(cacheDay) && yearMonthDay.equals(cacheDay)){
				count = (Integer) client.get("chartDay:count:exception");
				totalCount = (Integer) client.get("chartDay:totalCount:exception");
			}else{
				count = getCount(yearMonthDay);
				totalCount = getCount(YearMont);
			}
		}else{
			count = getCount(yearMonthDay);
			totalCount = getCount(YearMont);
		}
		
		map.put("count", count);
		map.put("totalCount",totalCount );
		return JsonForm.warpJsonForm(true, map);
		
	}
	public Integer getCount(String time){
		WarningLog log = new WarningLog();
		log.setWarnTime(time);
		log.setStatus(Constant.EXCEPTION_STATUS);
		
		//服务器
		log.setMonitorType(Constant.DICT_SERVER);
		int serverCount =warningLogService.getWarningLogCount(log);
		//数据库
		log.setMonitorType(Constant.DICT_DATABASE);
		int databaseCount =warningLogService.getWarningLogCount(log);
		//中间件
		log.setMonitorType(Constant.DICT_MIDDLEWARE);
		int middlewareCount =warningLogService.getWarningLogCount(log);
		//应用系统 
		log.setMonitorType(Constant.DICT_APP_SYSTEM);
		int systemCount =warningLogService.getWarningLogCount(log);
		
		int count =serverCount+databaseCount+middlewareCount+systemCount;
		return count;
	}
	/**
	 * 当月异常统计
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCurMonthStatistics.do")
	public JsonForm getCurMonthStatistics(){
		//获得当前年份月份
		String month = TimeUtil.GetCurYearMonth();
		String flag = Config.getAttribute("isUseMemcache");
		int serverCount=0;
		int databaseCount=0;
		int middlewareCount=0;
		int appsystemCount=0;
		if(flag != null && "true".equals(flag)){
			MemcachedUtil client = MemcachedUtil.getInstance();
			String cacheYear = (String) client.get("chartMonth:month:exception");
			if(!StringHelpers.isNull(cacheYear) && month.equals(cacheYear)){
				serverCount = (Integer) client.get("chartMonth:servercount:exception");
				databaseCount = (Integer) client.get("chartMonth:databasecount:exception");
				middlewareCount = (Integer) client.get("chartMonth:middlewarecount:exception");
				appsystemCount = (Integer) client.get("chartMonth:appsystemcount:exception");
			}else{
				serverCount  = warningLogService.getWarningLogCount(new WarningLog(month, Constant.EXCEPTION_STATUS, Constant.DICT_SERVER));
				databaseCount = warningLogService.getWarningLogCount(new WarningLog(month, Constant.EXCEPTION_STATUS, Constant.DICT_DATABASE));
				middlewareCount = warningLogService.getWarningLogCount(new WarningLog(month, Constant.EXCEPTION_STATUS, Constant.DICT_MIDDLEWARE));
				appsystemCount = warningLogService.getWarningLogCount(new WarningLog(month, Constant.EXCEPTION_STATUS, Constant.DICT_APP_SYSTEM));
			}
		}else{
			serverCount  = warningLogService.getWarningLogCount(new WarningLog(month, Constant.EXCEPTION_STATUS, Constant.DICT_SERVER));
			databaseCount = warningLogService.getWarningLogCount(new WarningLog(month, Constant.EXCEPTION_STATUS, Constant.DICT_DATABASE));
			middlewareCount = warningLogService.getWarningLogCount(new WarningLog(month, Constant.EXCEPTION_STATUS, Constant.DICT_MIDDLEWARE));
			appsystemCount = warningLogService.getWarningLogCount(new WarningLog(month, Constant.EXCEPTION_STATUS, Constant.DICT_APP_SYSTEM));
		}
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("serverCount", serverCount);
		map.put("databaseCount", databaseCount);
		map.put("middlewareCount", middlewareCount);
		map.put("appsystemCount", appsystemCount);
		
		return JsonForm.warpJsonForm(true, map);
		
	}
	@ResponseBody
	@RequestMapping("/getYearMonthStatistics.do")
	public JsonForm getYearMonthStatistics(){
		//获得当前年份
		String year = TimeUtil.GetCurYear();
		String flag = Config.getAttribute("isUseMemcache");
		int[] serverCount = {};
		int[] databaseCount ={};
		int[] middlewareCount = {};
		int[] systemCount = {};
		if(flag != null && "true".equals(flag)){
			MemcachedUtil client = MemcachedUtil.getInstance();
			String cacheYear = (String) client.get("chart:year:exception");
			if(!StringHelpers.isNull(cacheYear) && year.equals(cacheYear)){
				serverCount = stringToIntArray(serverCount,(String) client.get("chart:servercount:exception"));
				databaseCount = stringToIntArray(databaseCount,(String) client.get("chart:databasecount:exception"));
				middlewareCount = stringToIntArray(middlewareCount,(String) client.get("chart:middlewarecount:exception"));
				systemCount = stringToIntArray(systemCount,(String) client.get("chart:systemcount:exception"));
			}else{
				serverCount  = warningLogService.getServerCount(year);
				databaseCount = warningLogService.getDatabaseCount(year);
				middlewareCount = warningLogService.getMiddlewareCount(year);
				systemCount = warningLogService.getSystemCount(year);
			}
		}else{
			serverCount  = warningLogService.getServerCount(year);
			databaseCount = warningLogService.getDatabaseCount(year);
			middlewareCount = warningLogService.getMiddlewareCount(year);
			systemCount = warningLogService.getSystemCount(year);
		}
		
		Map<String,int[]> map = new HashMap<String, int[]>();
		map.put("serverCount", serverCount);
		map.put("databaseCount", databaseCount);
		map.put("middlewareCount", middlewareCount);
		map.put("systemCount", systemCount);
		
		return JsonForm.warpJsonForm(true, map);
		
	}
	
	/**
	 * @Title: stringToIntArray
	 * @Description: 字符串数组转换的int[]
	 * @param result
	 * @param original
	 * @return int[]
	 * @throws
	 */
	private int[] stringToIntArray(int[] result,String original){
		if(!StringHelpers.isNull(original)){
			List<Integer> list = (List<Integer>) JSONArray.parseArray(original, Integer.class);
			result = new int[list.size()];
			for(int i=0;i<list.size();i++){
				result[i] = list.get(i);
			}
		}
		return result;
	}
	
	/**
	 * @Title: getAppSystemList
	 * @Description: 分页获取今日异常情况
	 * @param appSystem
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getCurDayList.do")
	public PageGridLoad getCurDayList(WarningLog warningLog,PageGridPost pageGridPost){
		String yearMonthDay = TimeUtil.GetCurDateEx();
		warningLog.setWarnTime(yearMonthDay);
		warningLog.setStatus(Constant.EXCEPTION_STATUS);
		
		warningLog.setMonitorType("'"+Constant.DICT_SERVER+"','"+Constant.DICT_DATABASE+"','"+Constant.DICT_MIDDLEWARE+"'");
		List<WarningLog> warningLogServerList = warningLogService.getWarningLogDayList(warningLog,pageGridPost);

		PageGridLoad postGridLoad = new PageGridLoad();
		postGridLoad.setPageData(warningLogServerList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
}
