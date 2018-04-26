package com.apps.msgRemind.inform.dao;


import java.util.List;

import com.apps.msgRemind.inform.domain.AsopInform;
import com.common.pagetag.PageGridPost;

/**
 * 
 * @ClassName: InformDao 
 * @Description: 短消息
 * @author 张梦琦 
 * @date 2018年4月9日 下午4:14:50
 */
public interface InformDao {

	
	/**
	 * 保存或更新短消息
	 * @param inform
	 */
	public void saveOrupdateInform(AsopInform inform);
	
	/**
	 * 分页获取邮件列表
	 * @param mail
	 * @param pageGridPost
	 * @return
	 */
	public List<AsopInform> getListByPage (AsopInform inform,PageGridPost pageGridPost);
	
	public List<AsopInform> getListNoPage (AsopInform inform);
	
	public void deleteInform(long id);
	
	public AsopInform getInformById(long id);
}