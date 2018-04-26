package com.apps.operational.checkrecord.service;

import java.util.List;

import com.apps.operational.checkrecord.domain.OpsCheckRecord;
import com.apps.operational.checkrecord.domain.OpsCheckRecordModel;
import com.common.pagetag.PageGridPost;

/**
 * 
 * @ClassName: OpsCheckRecordModelService 
 * @Description: 巡检项service层
 * @author 张梦琦 
 * @date 2017年12月1日 上午9:31:46
 */
public interface OpsCheckRecordModelService {
	/**
	 * 根据给定条件获取
	 * @param checkRecordModel
	 * @return
	 */
	public List<OpsCheckRecordModel> getListByModel(OpsCheckRecordModel checkRecordModel);
	public List<OpsCheckRecordModel> getListByModel(OpsCheckRecordModel checkRecordModel,PageGridPost pageGridPost);
	
	/**
	 * 保存或更新巡检项
	 * @param checkRecordModel
	 */
	public void saveOrUpdateModel(OpsCheckRecordModel checkRecordModel);
	
	public void deleteModel(List<String> list);
	
	/**
	 * 禁用或启用
	 * @param id
	 * @param value
	 */
	public void isUseModel(long id,String value);
	
	public OpsCheckRecordModel getById(long id);
	

}