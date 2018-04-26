package com.apps.msgRemind.mail.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.msgRemind.mail.dao.MailDao;
import com.apps.msgRemind.mail.domain.AsopMail;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;

/**
 * 
 * @ClassName: MailDaoImpl 
 * @Description: 邮件dao实现类
 * @author 张梦琦 
 * @date 2018年4月9日 下午2:52:23
 */
@Repository(value="mailDao")
public class MailDaoImpl extends BaseDaoImpl implements MailDao{

	
	@Override
	public void saveOrupdateMail(AsopMail mail) {
			this.saveOrUpdate(mail);
	}

	@Override
	public List<AsopMail> getListByPage(AsopMail mail, PageGridPost pageGridPost) {
		 StringBuffer hql=new StringBuffer("from AsopMail a where 1=1 ");
			if(mail!=null){
				if(!StringHelpers.isNull(mail.getSendStatus())){
					hql.append(" and a.sendStatus like '"+ mail.getSendStatus() +"'");
				}
				if(!StringHelpers.isNull(mail.getContent())){
					hql.append(" and a.content like '%"+ mail.getContent() +"%'");
				}
			}
			hql.append(" order by a.sendDate desc ");
			List<AsopMail> list = ((List<AsopMail>) queryByPage(hql.toString(),pageGridPost));
			if(list !=null && list.size()>0){
				return list;
			}
			return null;
	}

	@Override
	public void deleteMail(long id) {
		AsopMail mail = (AsopMail) this.findById(AsopMail.class, id);
		delete(mail);
	}

}
