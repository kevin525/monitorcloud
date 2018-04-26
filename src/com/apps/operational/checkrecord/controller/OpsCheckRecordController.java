package com.apps.operational.checkrecord.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.operational.checkrecord.domain.OpsCheckRecord;
import com.apps.operational.checkrecord.domain.OpsCheckRecordInfo;
import com.apps.operational.checkrecord.domain.OpsCheckRecordModel;
import com.apps.operational.checkrecord.service.OpsCheckRecordInfoService;
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
 * @ClassName: OpsCheckRecordController 
 * @Description: 巡检记录控制层
 * @author 张梦琦 
 * @date 2017年11月29日 下午3:35:52
 */
@Controller
@RequestMapping(value="/opsCheckRecord")
public class OpsCheckRecordController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private OpsCheckRecordService checkRecordService;
	
	@Autowired
	private OpsCheckRecordModelService checkRecordModelService;
	
	@Autowired
	private OpsCheckRecordInfoService checkRecordInfoService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 
	 * @param request
	 * @param normal
	 * @param abnormal
	 * @param environment
	 * @param name
	 * @param remark
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateInfo.do")
	public JsonForm updatecheckInfo(HttpServletRequest request,String normal,String abnormal,String  environment ,String name,String remark){
		try {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if(null == loginUser){
				return JsonForm.warpJsonForm(false, "noLogin");
			}
			OpsCheckRecord record = new OpsCheckRecord();
			record.setIsUse("0");
			record.setEnvironment(environment);
			record.setCreateUserId(loginUser.getUserId());
			record.setPersonLiable(loginUser.getPerson().getPersonName());
			record.setCreateDate(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
			record.setName(name);
			record.setRemark(remark);
			checkRecordService.saveOrUpdateCheckRecord(record);
			long recordId = record.getId();
			if(!StringHelpers.isNull(normal)){
				String[] normalStr=  normal.split(",");
				for (String n : normalStr) {
					OpsCheckRecordInfo info = new OpsCheckRecordInfo();
					info.setCheckRecordId(recordId);
					info.setModelId(Long.parseLong(n));
					info.setState("0");
					info.setOpsCheckRecordModel(checkRecordModelService.getById(Long.parseLong(n)));
					checkRecordInfoService.saveOrUpdateInfo(info);
				}
			}
			if(!StringHelpers.isNull(abnormal)){
				String[] abnormalStr=  abnormal.split(",");
				for (String abn : abnormalStr) {
					OpsCheckRecordInfo info = new OpsCheckRecordInfo();
					info.setCheckRecordId(recordId);
					info.setModelId(Long.parseLong(abn));
					info.setOpsCheckRecordModel(checkRecordModelService.getById(Long.parseLong(abn)));
					info.setState("1");
					checkRecordInfoService.saveOrUpdateInfo(info);
				}
			}
				
			
			
			
			return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
		}
	}
	/**
	 * 分页显示巡检项列表
	 * @param request
	 * @param model
	 * @param pageGridPost
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRecordListByPage.do")
	public PageGridLoad getRecordListByPage(HttpServletRequest request,OpsCheckRecord checkRecord,PageGridPost pageGridPost){
		PageGridLoad postGridLoad = new PageGridLoad();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(null ==loginUser.getIsManager() ||loginUser.getIsManager() !=2){
			
			checkRecord.setCreateUserId(loginUser.getUserId());
		}
		List<OpsCheckRecord> list = checkRecordService.getList(checkRecord, pageGridPost);
		postGridLoad.setTotalCount(pageGridPost.getTotalCount());
		postGridLoad.setPageData(list);
		return postGridLoad;
	}
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete.do")
	public JsonForm delete(String ids){
		try {
			if(!StringHelpers.isNull(ids)){
				String[] arr = ids.split(",");
				List<String> list = java.util.Arrays.asList(arr);
				checkRecordService.deleteCheckRecord(list);
				return JsonForm.warpJsonForm(true, Constant.RETURN_OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonForm.warpJsonForm(false, Constant.RETURN_FAILED);
	}
	
	
	/**
	 * 根据id获取中间件信息
	 */
	@ResponseBody
	@RequestMapping("/getRecordById.do")
	public JsonForm getById(long id) {
		List<OpsCheckRecordInfo> infos = checkRecordInfoService.getByRecordId(id);
		Set<Long> fatherIds = new HashSet<Long>();
		List<OpsCheckRecordInfo> firstList = new ArrayList<OpsCheckRecordInfo>(); 
		List<OpsCheckRecordInfo> secondList = new ArrayList<OpsCheckRecordInfo>(); 
		for (OpsCheckRecordInfo info : infos) {
			if(info.getOpsCheckRecordModel().getNodeValue() == 2){
				fatherIds.add(info.getOpsCheckRecordModel().getFatherId());
				secondList.add(info);
			}else{
				firstList.add(info);
			}
		}
		return JsonForm.warpJsonForm(true, infos);
	}
}
