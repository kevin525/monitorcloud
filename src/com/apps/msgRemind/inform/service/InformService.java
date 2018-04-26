package com.apps.msgRemind.inform.service;

import java.util.List;

import com.apps.msgRemind.inform.domain.AsopInform;
import com.apps.msgRemind.mail.domain.AsopMail;
import com.common.pagetag.PageGridPost;
/**
 * 
 * @ClassName: InformService 
 * @Description:短消息 
 * @author 张梦琦 
 * @date 2018年4月9日 下午4:18:29
 */
public interface InformService {

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
	
	/**
	 * 删除邮件
	 * @param ids
	 */
	public void deleteInform (List<String> ids);
	
	public AsopInform getInformById(long id);
}
