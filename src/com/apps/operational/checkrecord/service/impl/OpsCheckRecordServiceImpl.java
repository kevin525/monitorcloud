package com.apps.operational.checkrecord.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.operational.checkrecord.dao.OpsCheckRecordDao;
import com.apps.operational.checkrecord.domain.OpsCheckRecord;
import com.apps.operational.checkrecord.service.OpsCheckRecordService;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;

@Service(value="opsCheckRecordService")
public class OpsCheckRecordServiceImpl extends BaseServiceImpl implements OpsCheckRecordService {

	@Autowired
	private OpsCheckRecordDao opsCheckRecordDao;
	

	@Override
	public void saveOrUpdateCheckRecord(OpsCheckRecord checkRecord) {
		opsCheckRecordDao.saveOrUpdateCheckRecord(checkRecord);
		
	}

	@Override
	public OpsCheckRecord getById(long id) {
		return opsCheckRecordDao.getById(id);
	}

	@Override
	public List<OpsCheckRecord> getList(OpsCheckRecord checkRecord,
			PageGridPost pageGridPost) {
		return opsCheckRecordDao.getList(checkRecord, pageGridPost);
	}

	

	@Override
	public void deleteCheckRecord(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	opsCheckRecordDao.deleteCheckRecord(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public List<OpsCheckRecord> getManagerList(OpsCheckRecord checkRecord,
			PageGridPost pageGridPost) {
		return opsCheckRecordDao.getManagerList(checkRecord, pageGridPost);
	}

	@Override
	public OpsCheckRecord getCheckRecord(String name,String createYMD,String environment) {
		return opsCheckRecordDao.getCheckRecord(name, createYMD, environment);
	}


}
