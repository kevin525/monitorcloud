package com.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.server.dao.ServerDao;
import com.apps.daily.server.domain.AsopServer;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.MenuResourceDao;
import com.sys.dao.impl.BaseDaoImpl;
import com.sys.domain.model.Resources;

/**
 * @ClassName: MenuResourcesImpl
 * @Description: MenuResourceDao实现类
 * @author LG
 * @date 2017年9月22日 上午10:16:19
 */
@Repository(value="menuResourcesDao")
public class MenuResourcesImpl extends BaseDaoImpl implements MenuResourceDao {

	
	@Override
	public void save(Object o) {
		this.save(o);
	}

	@Override
	public void saveOrupdateResource(Resources resource) {
		this.saveOrupdateResource(resource);
	}

	@Override
	public Resources getResourcesById(long id) {
//		return this.getResourcesById(id);
		return (Resources) this.findById(Resources.class, id);
	}

	@Override
	public void deleteResources(long id) {
		Resources resources = (Resources) this.findById(Resources.class,id);
		this.delete(resources);
	}

	@Override
	public List<Resources> getResourcesList(Resources resources,
			PageGridPost pageGridPost) {
        StringBuffer hql=new StringBuffer("from Resources a where 1=1 ");
		if(resources !=null){
			if(!StringHelpers.isNull(resources.getResourcesName())){
				hql.append(" and a.resourcesName like '%"+ resources.getResourcesName() +"%'");
			}
		}
		hql.append(" order by a.resourcesState desc,a.nodeValue asc,a.resourcesOrderNum asc ");
		List<Resources> resourcesList = ((List<Resources>) queryByPage(hql.toString(),pageGridPost));
		if(resourcesList !=null && resourcesList.size()>0){
			return resourcesList;
		}
		return null;
	}

	@Override
	public List<Resources> getResourcesList(Resources resources) {
		StringBuffer hql=new StringBuffer("from Resources a where a.resourcesState = 1 ");
		if(resources !=null){
			if(!StringHelpers.isNull(resources.getResourcesName())){
				hql.append(" and a.name like '%"+ resources.getResourcesName() +"%'");
			}
			hql.append(" and a.resourcesPid = "+ resources.getResourcesPid());
		}
		hql.append(" order by a.nodeValue asc,a.resourcesOrderNum asc ");
		List<Resources> resourcesList = ((List<Resources>) query(hql.toString()));
		if(resourcesList !=null && resourcesList.size()>0){
			return resourcesList;
		}
		return null;
	}

	@Override
	public void isUseResources(long id, int val) {
		Resources resources = (Resources) this.findById(Resources.class,id);
		resources.setResourcesState(val);
		this.update(resources);
	}

}
