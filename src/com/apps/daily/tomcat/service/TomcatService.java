package com.apps.daily.tomcat.service;

import java.util.List;

import com.apps.daily.common.view.MonitorInfoView;
import com.apps.daily.tomcat.domain.AsopTomcat;
import com.common.pagetag.PageGridPost;

/**
 * 
 * @ClassName: TomcatService 
 * @Description: 
 * @author 张梦琦 
 * @date 2017年11月9日 下午3:15:39
 */
public interface TomcatService {
	/**
	 * 保存或更新
	 * @param tomcat
	 */
	public void saveOrUpdateTomcat(AsopTomcat tomcat);
	
	/**
	 * 根据id获取中间件
	 * @param id
	 */
	public AsopTomcat getTomcatById(long id);
	
	/**
	 * 分页获取符合条件的中间件
	 * @param tomcat
	 * @param pageGridPost
	 * @return
	 */
	public List<AsopTomcat> getList(AsopTomcat tomcat,PageGridPost pageGridPost);
	
	/**
	 * 获取符合条件的中间件
	 * @param tomcat
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
	 * @param ids
	 */
	public void deleteTomcat(List<String> ids);
	
	/**
	 * @Title: getTomcatMonitor
	 * @Description: 获得tomcat中间件预警情况
	 * @return List<MonitorInfoView>
	 * @throws
	 */
	public List<MonitorInfoView> getTomcatMonitor();
}