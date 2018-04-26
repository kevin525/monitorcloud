package com.apps.warn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.warn.dao.WarningDao;
import com.apps.warn.domain.AsopWarning;
import com.apps.warn.service.WarningService;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;

@Service(value="warningService")
public class WarningServiceImpl implements WarningService{

	@Autowired
	private WarningDao warningDao;
	
	@Override
	public AsopWarning findUseType(String typeName, String isUse) {
		return warningDao.findUseType(typeName, isUse);
	}

	@Override
	public void deleteAsopWarning(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	warningDao.deleteWarning(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public void saveOrupdateWarning(AsopWarning asopWarning) {
		warningDao.saveOrupdateWarning(asopWarning);
		
	}

	@Override
	public AsopWarning getWarningById(long id) {
		return warningDao.getWarningById(id);
	}


	@Override
	public List<AsopWarning> getWarningList(AsopWarning asopWarning,
			PageGridPost pageGridPost) {
		return warningDao.getWarningList(asopWarning, pageGridPost);
	}

	@Override
	public List<AsopWarning> getWarningList(AsopWarning asopWarning) {
		return warningDao.getWarningList(asopWarning);
	}

	@Override
	public void isUseWarning(long id, String val) {
		warningDao.isUseWarning(id, val);
	}

	@Override
	public void deleteWarning(long id) {
		warningDao.deleteWarning(id);
	}

	@Override
	public void save(Object o) {
		warningDao.save(o);
	}
	
}
