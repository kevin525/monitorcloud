package com.apps.daily.middleware.dao;

import java.util.List;

import com.apps.daily.middleware.domain.AsopMiddleware;
import com.common.pagetag.PageGridPost;

/**
 * @ClassName: MiddlewareDao
 * @Description: 中间件dao层
 * @author LG
 * @date 2017年9月22日 上午10:02:51
 */
public interface MiddlewareDao {
	
	/**
	 * @Title: saveOrupdateServer
	 * @Description: 保存或者更新中间件信息
	 * @param @param middleware
	 * @return void
	 * @throws
	 */
	public void saveOrupdateMiddleware(AsopMiddleware middleware);
	
	/**
	 * @Title: getAsopMiddlewareById
	 * @Description: 根据中间件id获取中间件信息
	 * @param id
	 * @return AsopMiddleware
	 * @throws
	 */
	public AsopMiddleware getAsopMiddlewareById(long id);
	
	
	
	/**
	 * @Title: deleteAsopMiddleware
	 * @Description: 删除中间件信息
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteAsopMiddleware(long id);
	
	
	/**
	 * @Title: save
	 * @Description: 保存对象
	 * @param o
	 * @return void
	 * @throws
	 */
	public void save(Object o);
	
	
	/**
	 * @Title: getAsopMiddlewareList
	 * @Description: 分页展示中间件列表
	 * @param asopMiddleware
	 * @param pageGridPost
	 * @return List<AsopServer>
	 * @throws
	 */
	public List<AsopMiddleware> getAsopMiddlewareList(AsopMiddleware asopMiddleware,PageGridPost pageGridPost);
	
	/**
	 * @Title: getAsopMiddlewareList
	 * @Description: 不分页展示中间件列表
	 * @param asopMiddleware
	 * @return List<AsopMiddleware>
	 * @throws
	 */
	public List<AsopMiddleware> getAsopMiddlewareList(AsopMiddleware asopMiddleware);
	
	/**
	 * @Title: isUseAsopMiddleware
	 * @Description: 是否启用或者禁用中间件
	 * @param id
	 * @param val
	 * @return void
	 * @throws
	 */
	public void isUseAsopMiddleware(long id,String val);
	
}