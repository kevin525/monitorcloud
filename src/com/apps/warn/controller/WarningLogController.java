package com.apps.warn.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.warn.domain.ErrorLog;
import com.apps.warn.domain.WarningLog;
import com.apps.warn.service.ErrorLogService;
import com.apps.warn.service.WarningLogService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.User;
import com.sys.service.BaseService;

@Controller
@RequestMapping(value="/warningLog")
public class WarningLogController {
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private WarningLogService warningLogService;
	
	@Autowired
	private ErrorLogService errorLogService;
	
	
	@ResponseBody
	@RequestMapping("/saveOrUpdateLog.do")
	public JsonForm saveOrUpdateLog(HttpServletRequest request,final WarningLog warningLog){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(null == loginUser){
				return JsonForm.warpJsonForm(false, "noLogin");
			}
			if(warningLog !=null){
				warningLog.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				baseService.saveOrUpdate(warningLog);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	@ResponseBody
	@RequestMapping("/updateLog.do")
	public JsonForm UpdateLog(HttpServletRequest request,final WarningLog warningLog){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(null == loginUser){
				return JsonForm.warpJsonForm(false, "noLogin");
			}
			if(warningLog !=null){
				warningLog.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				warningLogService.updateWarning(warningLog);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * @Title: getWarningLogList
	 * @Description: 分页获取告警日志
	 * @param warningLog
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getWarningLogList.do")
	public PageGridLoad getWarningLogList(WarningLog warningLog,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<WarningLog> warningLogList = warningLogService.getWarningLogList(warningLog, pageGridPost);
		postGridLoad.setPageData(warningLogList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	/**
	 * @Title: getWarningLogList
	 * @Description: 分页获取告警日志
	 * @param warningLog
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getProcessedWarningLogList.do")
	public PageGridLoad getProcessedWarningLogList(WarningLog warningLog,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<WarningLog> warningLogList = warningLogService.getProcessedWarningLogList(warningLog, pageGridPost);
		postGridLoad.setPageData(warningLogList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	/**
	 * 错误日志
	 * 
	 * @param errorLog
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getErrorLogList.do")
	public PageGridLoad getErrorLogList(ErrorLog errorLog, PageGridPost pageGridPost) {
		PageGridLoad postGridLoad = new PageGridLoad();
		List<ErrorLog> errorLogList = errorLogService.getErrorLogList(errorLog, pageGridPost);
		postGridLoad.setPageData(errorLogList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	/**
	 * @Title: getWarningListNoPage
	 * @Description: 不分页获取告警规则列表
	 * @param asopWarning
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getWarningLogListNoPage.do")
	public PageGridLoad getWarningLogListNoPage(WarningLog warningLog){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<WarningLog> warningLogList = warningLogService.getWarningLogList(warningLog);
		postGridLoad.setPageData(warningLogList);;
		return postGridLoad;
	}
	
	/**
	 * 删除告警日志
	 * @param deleteWarningLog
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteWarningLog.do")
	public JsonForm deleteWarningLog(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				warningLogService.deleteAllWarning(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * 删除错误日志
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteErrorLog.do")
	public JsonForm deleteErrorLog(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				errorLogService.deleteAllError(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * 根据id获取告警日志
	 */
	@ResponseBody
	@RequestMapping("/getWarningLogById.do")
	public JsonForm getWarningLogById(long id) {
		return JsonForm.warpJsonForm(true, baseService.findById(WarningLog.class,id));
	}
	
	/**
	 * 根据监控的id和类型获取监控日志
	 */
	@ResponseBody
	@RequestMapping("/getWarningLog.do")
	public JsonForm getWarningLog(String id,String type) {
		return JsonForm.warpJsonForm(true, warningLogService.getWarningLog(id, type));
	}
	
	
	/**
	 * 根据id获取错误日志
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getErrorLogById.do")
	public JsonForm getErrorLogById(long id) {
		return JsonForm.warpJsonForm(true, baseService.findById(ErrorLog.class,id));
	}
}
