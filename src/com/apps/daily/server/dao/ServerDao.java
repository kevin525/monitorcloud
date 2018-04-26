package com.apps.daily.server.dao;

import java.util.List;

import com.apps.daily.server.domain.AsopServer;
import com.common.pagetag.PageGridPost;

/**
 * @ClassName: ServerDao
 * @Description: 服务器dao层
 * @author LG
 * @date 2017年9月22日 上午10:02:51
 */
public interface ServerDao {

	
	/**
	 * @Title: saveOrupdateServer
	 * @Description: 保存或者更新服务器信息
	 * @param @param server
	 * @return void
	 * @throws
	 */
	public void saveOrupdateServer(AsopServer server);
	
	/**
	 * @Title: getAsopServerById
	 * @Description: 根据服务器id获取服务器信息
	 * @param id
	 * @return AsopServer
	 * @throws
	 */
	public AsopServer getAsopServerById(long id);
	
	
	
	/**
	 * @Title: deleteAsopServer
	 * @Description: 删除服务器信息
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteAsopServer(long id);
	
	
	/**
	 * @Title: save
	 * @Description: 保存对象
	 * @param o
	 * @return void
	 * @throws
	 */
	public void save(Object o);
	
	
	/**
	 * @Title: getAsopServerList
	 * @Description: 分页展示服务器列表
	 * @param asopServer
	 * @param pageGridPost
	 * @return List<AsopServer>
	 * @throws
	 */
	public List<AsopServer> getAsopServerList(AsopServer asopServer,PageGridPost pageGridPost);
	
	/**
	 * @Title: getAsopServerList
	 * @Description: 不分页展示服务器列表
	 * @param asopServer
	 * @return List<AsopServer>
	 * @throws
	 */
	public List<AsopServer> getAsopServerList(AsopServer asopServer);
	
	/**
	 * @Title: isUseServer
	 * @Description: 是否启用或者禁用服务器
	 * @param id
	 * @param val
	 * @return void
	 * @throws
	 */
	public void isUseServer(long id,String val);

}