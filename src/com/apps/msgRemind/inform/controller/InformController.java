package com.apps.msgRemind.inform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.msgRemind.inform.domain.AsopInform;
import com.apps.msgRemind.inform.service.InformService;
import com.apps.msgRemind.mail.domain.AsopMail;
import com.apps.msgRemind.mail.service.MailService;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;

@Controller
@RequestMapping(value="/inform")
public class InformController {
	
	@Autowired
	private InformService informService;
	
	/**
	 * 获取成功列表
	 * @param mail
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInformSuccessfulList.do")
	public PageGridLoad getInformSuccessfulList(AsopInform infrom,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		List<AsopInform> maiList = informService.getListByPage(infrom, pageGridPost);
		postGridLoad.setPageData(maiList);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		return postGridLoad;
		
		
	}
	
	@ResponseBody
	@RequestMapping("/deleteAsopInform.do")
	public JsonForm deleteAsopMail(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				informService.deleteInform(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	@ResponseBody
	@RequestMapping("/setInformRead.do")
	public JsonForm setInformRead(String id){
		
		AsopInform inform = informService.getInformById(Long.parseLong(id));
		if(null != inform){
			inform.setReadStatus(Constant.INFORM_READ);
			informService.saveOrupdateInform(inform);
			return JsonForm.warpJsonForm(false, Constant.RETURN_OK);
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
/*	@ResponseBody
	@RequestMapping("/setInformReadList.do")
	public JsonForm setInformReadList(String id){
		if(!StringHelpers.isNull(id)){
			String[] arr = id.split(",");
			List<String> list = java.util.Arrays.asList(arr);
		}
		AsopInform inform = informService.getInformById(list);
		if(null != inform){
			inform.setReadStatus(Constant.INFORM_READ);
			informService.saveOrupdateInform(inform);
			return JsonForm.warpJsonForm(false, Constant.RETURN_OK);
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}*/
	
	@ResponseBody
	@RequestMapping("/getUnreadCount.do")
	public JsonForm getUnreadCount(){
		AsopInform inform = new AsopInform();
		inform.setReadStatus(Constant.INFORM_UNREAD);
		List<AsopInform> list= informService.getListByPage(inform, null);
		if(null != list && list.size()>0){
			return JsonForm.warpJsonForm(true, list.size());
		}else{
			return JsonForm.warpJsonForm(true, 0);
			
		}
	}
}
