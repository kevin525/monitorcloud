package com.apps.msgRemind.mail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.msgRemind.mail.domain.AsopMail;
import com.apps.msgRemind.mail.service.MailService;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;

@Controller
@RequestMapping(value="/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	/**
	 * 获取成功列表
	 * @param mail
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMailSuccessfulList.do")
	public PageGridLoad getMailSuccessfulList(AsopMail mail,PageGridPost pageGridPost){
		mail.setSendStatus(Constant.MAIL_SEND_SUCCESS);
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopMail> maiList = mailService.getListByPage(mail, pageGridPost);
		postGridLoad.setPageData(maiList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
		
		
	}
	/**
	 * 获取失败列表
	 * @param mail
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMailFailureList.do")
	public PageGridLoad getMailFailureList(AsopMail mail,PageGridPost pageGridPost){
		mail.setSendStatus(Constant.MAIL_SEND_FAIL);
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopMail> maiList = mailService.getListByPage(mail, pageGridPost);
		postGridLoad.setPageData(maiList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
		
		
	}
	
	@ResponseBody
	@RequestMapping("/deleteAsopMail.do")
	public JsonForm deleteAsopMail(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				mailService.deleteMails(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
}
