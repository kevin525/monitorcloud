package com.apps.msgRemind.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.msgRemind.sms.dao.SmsDao;
import com.apps.msgRemind.sms.domain.AsopSms;
import com.apps.msgRemind.sms.service.SmsService;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;

@Service(value="smsService")
public class SmsServiceImpl extends BaseServiceImpl implements SmsService {

	@Autowired
	private SmsDao smsDao;
	
	@Override
	public void saveOrupdateSms(AsopSms asopSms) {
		this.saveOrUpdate(asopSms);
	}

	@Override
	public AsopSms getAsopSmsById(long id) {
		return (AsopSms) this.findById(AsopSms.class, id);
	}

	@Override
	public void deleteAsopSms(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	smsDao.deleteAsopSms(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public List<AsopSms> getAsopSmsFailureList(AsopSms asopSms,
			PageGridPost pageGridPost) {
		return smsDao.getAsopSmsFailureList(asopSms, pageGridPost);
	}
	
	@Override
	public List<AsopSms> getAsopSmsSuccessfulList(AsopSms asopSms,
			PageGridPost pageGridPost) {
		return smsDao.getAsopSmsSuccessfulList(asopSms, pageGridPost);
	}

	@Override
	public List<AsopSms> getAsopSmsList(AsopSms asopSms) {
		return smsDao.getAsopSmsList(asopSms);
	}


	@Override
	public void save(Object o) {
		this.save(o);
	}

	@Override
	public void isUseServer(long id, String val) {
		smsDao.isUseServer(id, val);
	}

	@Override
	public List<AsopSms> getAsopSmsListByDate(AsopSms asopSms) {
		return smsDao.getAsopSmsListByDate(asopSms);
	}

}
