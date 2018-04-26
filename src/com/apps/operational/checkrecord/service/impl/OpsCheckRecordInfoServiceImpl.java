package com.apps.operational.checkrecord.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.operational.checkrecord.dao.OpsCheckRecordInfoDao;
import com.apps.operational.checkrecord.dao.opsCheckRecordModelDao;
import com.apps.operational.checkrecord.domain.OpsCheckRecordInfo;
import com.apps.operational.checkrecord.service.OpsCheckRecordInfoService;
import com.sys.service.impl.BaseServiceImpl;
@Service(value="opsCheckRecordInfoService")
public class OpsCheckRecordInfoServiceImpl extends BaseServiceImpl implements OpsCheckRecordInfoService{

	@Autowired
	private OpsCheckRecordInfoDao opsCheckRecordInfoDao;
	
	
	@Override
	public void saveOrUpdateInfo(OpsCheckRecordInfo info) {
		
		this.saveOrUpdate(info);
		
	}

	@Override
	public List<OpsCheckRecordInfo> getByRecordId(long recordId) {
		return opsCheckRecordInfoDao.getByRecordId(recordId);
	}

}
