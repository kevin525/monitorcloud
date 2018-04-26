package com.apps.msgRemind.mail.service;

import java.util.List;

import com.apps.msgRemind.mail.domain.AsopMail;
import com.common.pagetag.PageGridPost;
/**
 * 
 * @ClassName: MailService 
 * @Description: 邮件service层
 * @author 张梦琦 
 * @date 2018年4月9日 下午2:53:11
 */
public interface MailService {

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
	
	/**
	 * 删除邮件
	 * @param ids
	 */
	public void deleteMails (List<String> ids);
}
