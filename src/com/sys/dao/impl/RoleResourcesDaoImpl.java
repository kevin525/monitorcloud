package com.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.dao.RoleResourcesDao;
import com.sys.domain.model.RoleResources;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年10月25日下午7:15:56
 *类说明：
 */
@Repository(value="roleResourcesDao")
public class RoleResourcesDaoImpl extends BaseDaoImpl implements RoleResourcesDao{
	


	@Override
	public void saveOrUpdateRoleResources(RoleResources roleResources) {
		saveOrUpdate(roleResources);
		
	}



	@Override
	public List<Long> getResourcesIdByRoleId(long roleId) {
		String hql="select ur.rsId from RoleResources ur where ur.rPrivState=1 and ur.roId="+roleId;
		@SuppressWarnings("unchecked")
		List<Long> listRsId=getHibernateTemplate().find(hql);
		if(listRsId!=null && listRsId.size()>0){
			return listRsId;
		}else{
			return null;
		}
	}



	@Override
	public RoleResources getByRoidRsid(String roleid, String rsId) {
		String hql="select ur from RoleResources ur where ur.roId="+roleid +" and ur.rsId="+rsId;
		@SuppressWarnings("unchecked")
		List<RoleResources> listRsId=getHibernateTemplate().find(hql);
		if(listRsId!=null && listRsId.size()>0){
			return listRsId.get(0);
		}else{
			return null;
		}
	}



	@Override
	public void deleteRoleResources(long id) {
		String hql="select ur from RoleResources ur where ur.rsId="+id;
		@SuppressWarnings("unchecked")
		List<RoleResources> listRsId=getHibernateTemplate().find(hql);
		if(listRsId!=null && listRsId.size()>0){
			for (RoleResources roleResources : listRsId) {
				this.delete(roleResources);
			}
		}
		
		
	}
}
