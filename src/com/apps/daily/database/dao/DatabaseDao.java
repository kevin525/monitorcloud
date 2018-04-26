package com.apps.daily.database.dao;

import java.util.List;

import com.apps.daily.database.domain.AsopDatabase;
import com.common.pagetag.PageGridPost;
/**
 * 
 * @ClassName: DatabaseDao 
 * @Description:数据库dao层 
 * @author 张梦琦 
 * @date 2017年11月3日 上午10:19:56
 */
public interface DatabaseDao {

	/**
	 * 保存或更新
	 * @param database
	 */
	public void saveOrUpdateDatabase(AsopDatabase database);
	
	/**
	 * 根据id获取数据库
	 * @param id
	 */
	public AsopDatabase getDatabaseById(long id);
	
	/**
	 * 分页获取符合条件的数据库
	 * @param database
	 * @param pageGridPost
	 * @return
	 */
	public List<AsopDatabase> getList(AsopDatabase database,PageGridPost pageGridPost);
	
	/**
	 * 获取符合条件的数据库
	 * @param database
	 * @param pageGridPost
	 * @return
	 */
	public List<AsopDatabase> getList(AsopDatabase database);
	
	/**
	 * 启用或禁用数据库监控
	 * @param id
	 * @param val
	 */
	public void isUseDatabase(long id,String val);
	
	/**
	 * 删除数据库
	 * @param id
	 */
	public void deleteDatabase(long id);

}