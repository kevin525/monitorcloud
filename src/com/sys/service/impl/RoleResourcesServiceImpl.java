package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.StringHelpers;
import com.sys.dao.RoleResourcesDao;
import com.sys.domain.model.RoleResources;
import com.sys.service.RoleResourcesService;

@Service(value="roleResourcesService")
public class RoleResourcesServiceImpl extends BaseServiceImpl implements RoleResourcesService {
	@Autowired
	private RoleResourcesDao roleResourcesDao;
	

	@Override
	public List<Long> getResourcesIdByRoleId(long roleId) {
		return roleResourcesDao.getResourcesIdByRoleId(roleId);
	}

	@Override
	public void saveOrUpdateRoleResources(RoleResources roleResources) {
		 roleResourcesDao.saveOrUpdateRoleResources(roleResources);
		
	}

	@Override
	public  RoleResources getByRoidRsid(String roleid, String rsId) {
		return roleResourcesDao.getByRoidRsid(roleid, rsId);
	}

	@Override
	public void deleteRoleResources(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	roleResourcesDao.deleteRoleResources(Long.parseLong(id));
			    }
			}
		}
		
	}
}
