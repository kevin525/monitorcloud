package com.apps.warn.service;

import java.util.List;

import com.apps.warn.domain.AsopWarning;
import com.common.pagetag.PageGridPost;

public interface WarningService {

	public AsopWarning findUseType(String typeName,String isUse);
	
	/**
	 * @Title: deleteAsopWarning
	 * @Description: 删除告警规则信息
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteAsopWarning(List<String> ids);
	
	
	/**
	 * @Title: saveOrupdateAppSystem
	 * @Description: 保存或者更新告警规则信息
	 * @param asopWarning
	 * @return void
	 * @throws
	 */
	public void saveOrupdateWarning(AsopWarning asopWarning);
	
	/**
	 * @Title: getWarningById
	 * @Description: 根据应用系统id获取告警规则信息
	 * @param id
	 * @return AsopWarning
	 * @throws
	 */
	public AsopWarning getWarningById(long id);
	
	
	
	/**
	 * @Title: deleteWarning
	 * @Description: 删除告警规则信息
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteWarning(long id);
	
	
	/**
	 * @Title: save
	 * @Description: 保存对象
	 * @param o
	 * @return void
	 * @throws
	 */
	public void save(Object o);
	
	
	/**
	 * @Title: getWarningList
	 * @Description: 分页展示告警规则列表
	 * @param asopWarning
	 * @param pageGridPost
	 * @return List<AsopWarning>
	 * @throws
	 */
	public List<AsopWarning> getWarningList(AsopWarning asopWarning,PageGridPost pageGridPost);
	
	/**
	 * @Title: getWarningList
	 * @Description: 不分页展示告警规则列表
	 * @param asopWarning
	 * @return List<AsopWarning>
	 * @throws
	 */
	public List<AsopWarning> getWarningList(AsopWarning asopWarning);
	
	/**
	 * @Title: isUseWarning
	 * @Description: 是否启用或者禁用告警规则
	 * @param id
	 * @param val
	 * @return void
	 * @throws
	 */
	public void isUseWarning(long id,String val);
}
