package com.apps.msgRemind.mail.dao;


import java.util.List;

import com.apps.msgRemind.mail.domain.AsopMail;
import com.common.pagetag.PageGridPost;

/**
 * 
 * @ClassName: MailDao 
 * @Description: 邮件dao
 * @author 张梦琦 
 * @date 2018年4月9日 下午2:50:44
 */
public interface MailDao {

	
	/**
	 * @Title: saveOrupdateSms
	 * @Description: 保存或者更新短信信息
	 * @param @param asopSms
	 * @return void
	 * @throws
	 */
	public void saveOrupdateMail(AsopMail mail);
	
	/**
	 * 分页获取邮件列表
	 * @param mail
	 * @param pageGridPost
	 * @return
	 */
	public List<AsopMail> getListByPage (AsopMail mail,PageGridPost pageGridPost);
	
	public void deleteMail(long id);
}