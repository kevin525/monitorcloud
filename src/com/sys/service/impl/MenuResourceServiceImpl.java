package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.MenuResourceDao;
import com.sys.domain.model.Resources;
import com.sys.service.MenuResourceService;

@Service(value="menuResourceService")
public class MenuResourceServiceImpl extends BaseServiceImpl implements MenuResourceService {

	@Autowired
	private MenuResourceDao menuResourceDao;
	
	@Override
	public void save(Object o) {
        menuResourceDao.save(o);
	}

	@Override
	public void saveOrupdateResource(Resources resource) {
		menuResourceDao.saveOrupdateResource(resource);
	}

	@Override
	public Resources getResourcesById(long id) {
		return menuResourceDao.getResourcesById(id);
	}

	@Override
	public void deleteResources(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	menuResourceDao.deleteResources(Long.parseLong(id));
			    }
			}
		}
	}

	@Override
	public List<Resources> getResourcesList(Resources resources,
			PageGridPost pageGridPost) {
		return menuResourceDao.getResourcesList(resources, pageGridPost);
	}

	@Override
	public List<Resources> getResourcesList(Resources resources) {
		return menuResourceDao.getResourcesList(resources);
	}

	@Override
	public void isUseResources(long id, int val) {
		menuResourceDao.isUseResources(id, val);
	}


}
