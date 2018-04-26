package com.sys.dao;

import java.util.List;

import com.apps.daily.server.domain.AsopServer;
import com.common.pagetag.PageGridPost;
import com.sys.domain.model.Resources;

/**
 * @ClassName: MenuResourceDao
 * @Description: 菜单资源dao层
 * @author LG
 * @date 2017年9月22日 上午10:02:51
 */
public interface MenuResourceDao {

	
	/**
	 * @Title: saveOrupdateResource
	 * @Description: 保存或者更新菜单资源
	 * @param server
	 * @return void
	 * @throws
	 */
	public void saveOrupdateResource(Resources resource);
	
	/**
	 * @Title: getResourcesById
	 * @Description: 根据服务器id获取菜单资源
	 * @param id
	 * @return AsopServer
	 * @throws
	 */
	public Resources getResourcesById(long id);
	
	
	
	/**
	 * @Title: deleteResources
	 * @Description: 删除菜单资源
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteResources(long id);
	
	
	/**
	 * @Title: save
	 * @Description: 保存对象
	 * @param o
	 * @return void
	 * @throws
	 */
	public void save(Object o);
	
	
	/**
	 * @Title: getResourcesList
	 * @Description: 分页展示菜单资源
	 * @param asopServer
	 * @param pageGridPost
	 * @return List<AsopServer>
	 * @throws
	 */
	public List<Resources> getResourcesList(Resources resources,PageGridPost pageGridPost);
	
	/**
	 * @Title: getResourcesList
	 * @Description: 不分页展示菜单资源
	 * @param asopServer
	 * @return List<AsopServer>
	 * @throws
	 */
	public List<Resources> getResourcesList(Resources resources);
	
	/**
	 * @Title: isUseResources
	 * @Description: 是否启用或者禁用菜单资源
	 * @param id
	 * @param val
	 * @return void
	 * @throws
	 */
	public void isUseResources(long id,int val);
}