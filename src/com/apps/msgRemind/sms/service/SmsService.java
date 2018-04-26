package com.apps.msgRemind.sms.service;

import java.util.List;

import com.apps.msgRemind.sms.domain.AsopSms;
import com.common.pagetag.PageGridPost;

/**
 * @ClassName: SmsDao
 * @Description: 短信service层
 * @author LG
 * @date 2017年9月22日 上午10:02:51
 */
public interface SmsService {

	
	/**
	 * @Title: saveOrupdateSms
	 * @Description: 保存或者更新短信信息
	 * @param @param asopSms
	 * @return void
	 * @throws
	 */
	public void saveOrupdateSms(AsopSms asopSms);
	
	/**
	 * @Title: getAsopSmsById
	 * @Description: 根据短信id获取短信信息
	 * @param id
	 * @return AsopServer
	 * @throws
	 */
	public AsopSms getAsopSmsById(long id);
	
	
	
	/**
	 * @Title: deleteAsopSms
	 * @Description: 删除短信信息
	 * @param id
	 * @return void
	 * @throws
	 */
	public void deleteAsopSms(List<String> ids);
	
	
	/**
	 * @Title: save
	 * @Description: 保存对象
	 * @param o
	 * @return void
	 * @throws
	 */
	public void save(Object o);
	
	
	/**
	 * @Title: getAsopSmsList
	 * @Description: 分页展示短信发送失败列表
	 * @param asopSms
	 * @param pageGridPost
	 * @return List<AsopServer>
	 * @throws
	 */
	public List<AsopSms> getAsopSmsFailureList(AsopSms asopSms,PageGridPost pageGridPost);
	
	/**
	 * @Title: getAsopSmsList
	 * @Description: 分页展示短信发送成功列表
	 * @param asopSms
	 * @param pageGridPost
	 * @return List<AsopServer>
	 * @throws
	 */
	public List<AsopSms> getAsopSmsSuccessfulList(AsopSms asopSms,PageGridPost pageGridPost);
	
	/**
	 * @Title: getAsopServerList
	 * @Description: 不分页展示短信列表
	 * @param asopServer
	 * @return List<AsopServer>
	 * @throws
	 */
	public List<AsopSms> getAsopSmsList(AsopSms asopSms);
	
	/**
	 * @Title: isUseServer
	 * @Description: 是否启用或者禁用服务器
	 * @param id
	 * @param val
	 * @return void
	 * @throws
	 */
	public void isUseServer(long id,String val);
	
	/**
	 * @Title: getAsopSmsListByDate
	 * @Description: 根据时间间隔来获取列表
	 * @param asopSms
	 * @return
	 * @return List<AsopSms>
	 * @throws
	 */
	public List<AsopSms> getAsopSmsListByDate(AsopSms asopSms);

}