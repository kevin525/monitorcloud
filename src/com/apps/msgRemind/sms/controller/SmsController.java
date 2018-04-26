package com.apps.msgRemind.sms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.msgRemind.sms.domain.AsopSms;
import com.apps.msgRemind.sms.service.SmsService;
import com.apps.warn.domain.WarningLog;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.User;
import com.sys.service.BaseService;


@Controller
@RequestMapping(value="/sms")
public class SmsController {
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private SmsService smsService;
	
	@ResponseBody
	@RequestMapping("/saveOrupdateSms.do")
	public JsonForm saveOrupdateSms(HttpServletRequest request,final AsopSms asopSms){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(null == loginUser){
				return JsonForm.warpJsonForm(false, "noLogin");
			}
			if(asopSms !=null){
				asopSms.setSendDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
				baseService.saveOrUpdate(asopSms);
			}
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	
	/**
	 * 根据id获取短信信息
	 */
	@ResponseBody
	@RequestMapping("/getAsopSmsById.do")
	public JsonForm getAsopSmsById(long id) {
		return JsonForm.warpJsonForm(true, baseService.findById(AsopSms.class,id));
	}
	
	/**
	 * 删除短信信息
	 * @param deleteWarningLog
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteAsopSms.do")
	public JsonForm deleteAsopSms(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				smsService.deleteAsopSms(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	/**
	 * 
	 * @Description: 分页展示短信发送失败列表
	 * @param asopSms
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getAsopSmsFailureList.do")
	public PageGridLoad getAsopSmsFailureList(AsopSms asopSms,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopSms> asopSmsList = smsService.getAsopSmsFailureList(asopSms, pageGridPost);
		postGridLoad.setPageData(asopSmsList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	/**
	 * 
	 * @Description: 分页展示短信发送成功列表
	 * @param asopSms
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getAsopSmsSuccessfulList.do")
	public PageGridLoad getAsopSmsSuccessfulList(AsopSms asopSms,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopSms> asopSmsList = smsService.getAsopSmsSuccessfulList(asopSms, pageGridPost);
		postGridLoad.setPageData(asopSmsList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
	}
	
	/**
	 * 
	 * @Description: 不分页展示短信列表
	 * @param asopWarning
	 * @param pageGridPost
	 * @return PageGridLoad
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getAsopSmsListNoPage.do")
	public PageGridLoad getAsopSmsListNoPage(AsopSms asopSms){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopSms> asopSmsList = smsService.getAsopSmsList(asopSms);
		postGridLoad.setPageData(asopSmsList);;
		return postGridLoad;
	}
	
	
	
	
}
