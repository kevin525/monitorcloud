package com.apps.operational.checkrecord.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.operational.checkrecord.dao.opsCheckRecordModelDao;
import com.apps.operational.checkrecord.domain.OpsCheckRecordModel;
import com.apps.operational.checkrecord.service.OpsCheckRecordModelService;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;

@Service(value="opsCheckRecordModelService")
public class OpsCheckRecordModelServiceImpl extends BaseServiceImpl implements OpsCheckRecordModelService{
	
	@Autowired
	private opsCheckRecordModelDao checkRecordModelDao;
	
	
	@Override
	public List<OpsCheckRecordModel> getListByModel(
			OpsCheckRecordModel checkRecordModel) {
		return checkRecordModelDao.getListByModel(checkRecordModel);
	}


	@Override
	public void saveOrUpdateModel(OpsCheckRecordModel checkRecordModel) {
		checkRecordModelDao.saveOrUpdateModel(checkRecordModel);
	}


	@Override
	public List<OpsCheckRecordModel> getListByModel(
			OpsCheckRecordModel checkRecordModel, PageGridPost pageGridPost) {
		return checkRecordModelDao.getListByModel(checkRecordModel, pageGridPost);
	}


	@Override
	public void deleteModel(List<String> list) {
		for (String id : list) {
			OpsCheckRecordModel model = (OpsCheckRecordModel) baseDao.findById(OpsCheckRecordModel.class, Long.parseLong(id));
			baseDao.delete(model);
		}
		
	}


	@Override
	public void isUseModel(long id, String value) {
		checkRecordModelDao.isUseModel(id, value);
		
	}


	@Override
	public OpsCheckRecordModel getById(long id) {
		return (OpsCheckRecordModel) this.findById(OpsCheckRecordModel.class, id);
	}

}
