package com.apps.operational.checkrecord.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apps.operational.checkrecord.domain.OpsCheckRecord;
import com.apps.operational.checkrecord.domain.OpsCheckRecordModel;
import com.apps.operational.checkrecord.service.OpsCheckRecordModelService;
import com.apps.operational.checkrecord.service.OpsCheckRecordService;
import com.common.FormatDateUtil;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.osplugins.JsonForm;
import com.common.pagetag.PageGridLoad;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.User;
import com.sys.service.BaseService;
/**
 * 
 * @ClassName: OpsCheckRecordModelController 
 * @Description: 巡检项控制层
 * @author 张梦琦 
 * @date 2017年12月1日 下午1:31:20
 */
@Controller
@RequestMapping(value="/opsCheckRecordModel")
public class OpsCheckRecordModelController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private OpsCheckRecordService checkRecordService;
	
	@Autowired
	private OpsCheckRecordModelService checkRecordModelService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 巡检项列表
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getModelList.do")
	public PageGridLoad getModelList(HttpServletRequest request,OpsCheckRecordModel model){
		PageGridLoad postGridLoad = new PageGridLoad();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(null ==loginUser.getIsManager() ||loginUser.getIsManager() !=2){
			model.setOwnerId(loginUser.getUserId());
		}
		List<OpsCheckRecordModel> list = checkRecordModelService.getListByModel(model);
		
		postGridLoad.setPageData(list);
		return postGridLoad;
	}
	/**
	 * 分页显示巡检项列表
	 * @param request
	 * @param model
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getModelListByPage.do")
	public PageGridLoad getModelListByPage(HttpServletRequest request,OpsCheckRecordModel model,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(null ==loginUser.getIsManager() || loginUser.getIsManager() !=2 ){
			
			model.setOwnerId(loginUser.getUserId());
		}
		List<OpsCheckRecordModel> list = checkRecordModelService.getListByModel(model,pageGridPost);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		postGridLoad.setPageData(list);
		return postGridLoad;
	}
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateModel.do")
	public JsonForm  saveOrUpdateModel(HttpServletRequest request,OpsCheckRecordModel model){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			model.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
			model.setOwnerId(loginUser.getUserId());
			model.setOwnerName(loginUser.getPerson().getPersonName());
			checkRecordModelService.saveOrUpdateModel(model);
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);	
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, "fail");
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/deleteModel.do")
	public JsonForm deleteModel(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				checkRecordModelService.deleteModel(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	@ResponseBody
	@RequestMapping("/setUse.do")
	public JsonForm setUse(long id,String val){
		try {
			if(!StringHelpers.isNull(val)){
				checkRecordModelService.isUseModel(id, val);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getById.do")
	public JsonForm getById(long id) {
		
		return JsonForm.warpJsonForm(true, checkRecordModelService.getById(id));
	}
}
