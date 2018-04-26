package com.apps.operational.checkrecord.service;

import java.util.List;

import com.apps.operational.checkrecord.domain.OpsCheckRecord;
import com.apps.operational.checkrecord.domain.OpsCheckRecordInfo;
import com.apps.operational.checkrecord.domain.OpsCheckRecordModel;
import com.common.pagetag.PageGridPost;

/**
 * 
 * @ClassName: OpsCheckRecordInfoService 
 * @Description: 巡检详情
 * @author 张梦琦 
 * @date 2017年12月4日 上午11:05:28
 */
public interface OpsCheckRecordInfoService {
	
	public void saveOrUpdateInfo(OpsCheckRecordInfo info);
	
	public List<OpsCheckRecordInfo> getByRecordId(long recordId);

}