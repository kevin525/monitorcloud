package com.apps.daily.tomcat.dao;

import java.util.List;

import com.apps.daily.tomcat.domain.AsopTomcat;
import com.common.pagetag.PageGridPost;
/**
 * 
 * @ClassName: TomcatDao 
 * @Description: 中间件dao层 
 * @author 张梦琦 
 * @date 2017年11月9日 上午10:52:48
 */
public interface TomcatDao {

	/**
	 * 保存或更新
	 * @param Tomcat
	 */
	public void saveOrUpdateTomcat(AsopTomcat tomcat);
	
	/**
	 * 根据id获取中间件
	 * @param id
	 */
	public AsopTomcat getTomcatById(long id);
	
	/**
	 * 分页获取符合条件的中间件
	 * @param Tomcat
	 * @param pageGridPost
	 * @return
	 */
	public List<AsopTomcat> getList(AsopTomcat tomcat,PageGridPost pageGridPost);
	
	/**
	 * 获取符合条件的中间件
	 * @param Tomcat
	 * @param pageGridPost
	 * @return
	 */
	public List<AsopTomcat> getList(AsopTomcat tomcat);
	
	/**
	 * 启用或禁用中间件监控
	 * @param id
	 * @param val
	 */
	public void isUseTomcat(long id,String val);
	
	/**
	 * 删除中间件
	 * @param id
	 */
	public void deleteTomcat(long id);

}