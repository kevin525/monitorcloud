package com.apps.daily.appsystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.appsystem.service.AppSystemService;
import com.apps.daily.appsystem.service.MonitorSystemContainer;
import com.apps.daily.server.domain.AsopServer;
import com.apps.msgRemind.sms.domain.AsopSms;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.common.utils.HttpUtil;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
import com.sys.util.Config;
/**
 * @ClassName: AppSystemController
 * @Description: 应用系统控制层
 * @author LG
 * @date 2017年9月22日 上午10:39:52
 */
@Controller
@RequestMapping(value="/appSystem")
public class AppSystemController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private AppSystemService appSystemService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	@RequestMapping("/saveAppSystem.do")
	public JsonForm saveAppSystem(HttpServletRequest request,final AsopAppSystem appSystem){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(appSystem !=null){
				appSystem.setCreateUserId(loginUser.getUserId());
				appSystem.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
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
	 * @Title: saveOrUpdateAppSystem
	 * @Description: 创建、修改应用系统信息
	 * @param request
	 * @param appSystem
	 * @return JsonForm
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/updateAppSystem.do")
	public JsonForm updateAppSystem(HttpServletRequest request,final AsopAppSystem appSystem){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(appSystem !=null){
				appSystem.setModifyDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				appSystem.setModifyUserId(loginUser.getUserId());
				AsopAppSystem system = appSystemService.getAppSystemById(appSystem.getId());
				if(null == system){
					return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
				}
				appSystem.setCheckCount(system.getCheckCount());
				appSystem.setLastCheckDate(system.getLastCheckDate());
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
	
}
