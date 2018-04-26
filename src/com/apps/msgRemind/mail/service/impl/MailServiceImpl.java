package com.apps.msgRemind.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import com.apps.msgRemind.mail.dao.MailDao;
import com.apps.msgRemind.mail.domain.AsopMail;
import com.apps.msgRemind.mail.service.MailService;
import com.apps.msgRemind.sms.dao.SmsDao;
import com.apps.msgRemind.sms.domain.AsopSms;
import com.apps.msgRemind.sms.service.SmsService;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;
/**
 * 
 * @ClassName: MailServiceImpl 
 * @Description: 邮件service层实现类
 * @author 张梦琦 
 * @date 2018年4月9日 下午2:54:41
 */
@Service(value="mailService")
public class MailServiceImpl extends BaseServiceImpl implements MailService {

	@Autowired
	private MailDao mailDao;

	@Override
	public void saveOrupdateMail(AsopMail mail) {
		mailDao.saveOrupdateMail(mail);
		
	}

	@Override
	public List<AsopMail> getListByPage(AsopMail mail, PageGridPost pageGridPost) {
		return mailDao.getListByPage(mail, pageGridPost);
	}

	@Override
	public void deleteMails(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	mailDao.deleteMail(Long.parseLong(id));
			    }
			}
		}
	}

	
}
