package com.apps.warn.controller;

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

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.appsystem.service.AppSystemService;
import com.apps.daily.database.domain.AsopDatabase;
import com.apps.daily.database.service.DatabaseService;
import com.apps.daily.server.domain.AsopServer;
import com.apps.daily.server.service.ServerService;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.apps.daily.tomcat.service.TomcatService;
import com.apps.warn.domain.AsopWarning;
import com.apps.warn.service.WarningService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.User;
import com.sys.service.BaseService;

@Controller
@RequestMapping(value="/warning")
public class WarningController {
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private AppSystemService appSystemService;
	
	@Autowired
	private ServerService serverService;
	
	@Autowired
	private TomcatService tomcatService;
	
	@Autowired
	private DatabaseService databaseService;
	
	@Autowired
	private WarningService warningService;
	
	
	
	@ResponseBody
	@RequestMapping("/saveOrUpdateRule.do")
	public JsonForm saveOrUpdateRule(HttpServletRequest request,final AsopWarning warning){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(null == loginUser){
				return JsonForm.warpJsonForm(false, "noLogin");
			}
			if(warning !=null){
				warning.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				warning.setModifyUserId(loginUser.getUserId());
				warning.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				AsopWarning asopWarning = warningService.findUseType(warning.getWarningType(), Constant.ACTIVITY);
				if(asopWarning != null){
					if(asopWarning.getId() != warning.getId()){
						return JsonForm.warpJsonForm(true, "repeat");
					}else{
						baseService.saveOrUpdate(warning);
						return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
					}
					
				}else{
					baseService.saveOrUpdate(warning);
					return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
				}
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * @Title: getWarningList
	 * @Description: 分页获取告警规则列表
	 * @param asopWarning
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getWarningList.do")
	public PageGridLoad getAppSystemList(AsopWarning asopWarning,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopWarning> warningList = warningService.getWarningList(asopWarning, pageGridPost);
		postGridLoad.setPageData(warningList);
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
	@RequestMapping("/getWarningListNoPage.do")
	public PageGridLoad getWarningListNoPage(AsopWarning asopWarning){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopWarning> warningList = warningService.getWarningList(asopWarning);
		postGridLoad.setPageData(warningList);
		return postGridLoad;
	}
	
	/**
	 * 删除告警规则
	 * @param deleteAppSystem
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteWarning.do")
	public JsonForm deleteWarning(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				warningService.deleteAsopWarning(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * 根据id获取告警规则
	 */
	@ResponseBody
	@RequestMapping("/getWarningById.do")
	public JsonForm getWarningById(long id) {
		return JsonForm.warpJsonForm(true, baseService.findById(AsopWarning.class,id));
	}
	/**
	 * @Title: setServerUse
	 * @Description: 设置告警规则启用、禁用状态
	 * @param id
	 * @return
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/setWaringUse.do")
	public JsonForm setWaringUse(long id,String val){
		try {
			if(!StringHelpers.isNull(val)){
				AsopWarning warning = (AsopWarning)baseService.findById(AsopWarning.class,id);
				AsopWarning asopWarning = warningService.findUseType(warning.getWarningType(), Constant.ACTIVITY);
				if(asopWarning != null){
					if(asopWarning.getId() != id && Constant.ACTIVITY.equals(val)){
						return JsonForm.warpJsonForm(true, "repeat");
					}else{
						warningService.isUseWarning(id, val);
						return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
					}
				}else{
					warningService.isUseWarning(id, val);
					return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
}
