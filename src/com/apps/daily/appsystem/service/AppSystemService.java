package com.apps.daily.appsystem.service;

import java.util.List;

import com.apps.daily.appsystem.domain.AsopAppSystem;
import com.apps.daily.common.view.MonitorInfoView;
import com.common.pagetag.PageGridPost;

/**
 * @ClassName: AppSystemService
 * @Description: 应用系统业务逻辑处理层
 * @author LG
 * @date 2017年9月22日 上午10:02:51
 */
public interface AppSystemService {

	
	/**
	 * @Title: saveOrupdateAppSystem
	 * @Description: 保存或者更新应用系统信息
	 * @param appSystem
	 * @return void
	 * @throws
	 */
	public void saveOrupdateAppSystem(AsopAppSystem appSystem);
	
	/**
	 * @Title: getAppSystemById
	 * @Description: 根据应用系统id获取应用系统信息
	 * @param id
	 * @return AsopAppSystem
	 * @throws
	 */
	public AsopAppSystem getAppSystemById(long id);
	
	
	
	/**
	 * @Title: deleteAppSystem
	 * @Description: 删除应用系统信息
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteAppSystem(List<String> ids);
	
	
	/**
	 * @Title: save
	 * @Description: 保存对象
	 * @param o
	 * @return void
	 * @throws
	 */
	public void save(Object o);
	
	
	/**
	 * @Title: getAppSystemList
	 * @Description: 分页展示应用系统列表
	 * @param appSystem
	 * @param pageGridPost
	 * @return List<AsopAppSystem>
	 * @throws
	 */
	public List<AsopAppSystem> getAppSystemList(AsopAppSystem appSystem,PageGridPost pageGridPost);
	
	/**
	 * @Title: getAppSystemList
	 * @Description: 不分页展示应用系统列表
	 * @param appSystem
	 * @return List<AsopAppSystem>
	 * @throws
	 */
	public List<AsopAppSystem> getAppSystemList(AsopAppSystem appSystem) ;
	
	/**
	 * @Title: isUseServer
	 * @Description: 是否启用或者禁用应用系统
	 * @param id
	 * @param val
	 * @return void
	 * @throws
	 */
	public void isUseAppSystem(long id,String val);
	
	/**
	 * @Title: getAppSystemMonitor
	 * @Description: 获得系统监控预警情况
	 * @return
	 * @return List<MonitorInfoView>
	 * @throws
	 */
	public List<MonitorInfoView> getAppSystemMonitor();
	

}