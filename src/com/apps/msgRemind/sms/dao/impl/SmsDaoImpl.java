package com.apps.msgRemind.sms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.daily.server.domain.AsopServer;
import com.apps.msgRemind.sms.dao.SmsDao;
import com.apps.msgRemind.sms.domain.AsopSms;
import com.common.StringHelpers;
import com.common.constants.Constant;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;
import com.sys.util.Config;

/**
 * @ClassName: SmsDaoImpl
 * @Description: SmsDao实现类
 * @author LG
 * @date 2017年9月22日 上午10:16:19
 */
@Repository(value="smsDao")
public class SmsDaoImpl extends BaseDaoImpl implements SmsDao {

	@Override
	public void isUseServer(long id, String val) {
		AsopServer asopServer = (AsopServer) this.findById(AsopServer.class,id);
		asopServer.setIsUse(val);
		this.update(asopServer);

	}

	@Override
	public void saveOrupdateSms(AsopSms asopSms) {
		this.saveOrUpdate(asopSms);
	}

	@Override
	public AsopSms getAsopSmsById(long id) {
		return (AsopSms) this.findById(AsopSms.class, id);
	}

	@Override
	public void deleteAsopSms(long id) {
		AsopSms asopSms = (AsopSms) this.findById(AsopSms.class,id);
		asopSms.setIsUse(Constant.DELETE);
		this.update(asopSms);
	}

	@Override
	public List<AsopSms> getAsopSmsFailureList(AsopSms asopSms,
			PageGridPost pageGridPost) {
        StringBuffer hql=new StringBuffer("from AsopSms a where isUse=0 and sendStatus=1 ");
		
		if(asopSms!=null){
			if(!StringHelpers.isNull(asopSms.getIsUse())){
				hql.append(" and a.isUse ="+asopSms.getIsUse());
			}
			if(!StringHelpers.isNull(asopSms.getContent())){
				hql.append(" and a.content like '%"+ asopSms.getContent() +"%'");
			}
		}
		
		hql.append(" order by a.sendDate desc ");
		List<AsopSms> asopSmsList = ((List<AsopSms>) queryByPage(hql.toString(),pageGridPost));
		if(asopSmsList !=null && asopSmsList.size()>0){
			return asopSmsList;
		}
		return null;
	}
	
	@Override
	public List<AsopSms> getAsopSmsSuccessfulList(AsopSms asopSms,
			PageGridPost pageGridPost) {
        StringBuffer hql=new StringBuffer("from AsopSms a where isUse=0 and sendStatus=0 ");
		
		if(asopSms!=null){
			if(!StringHelpers.isNull(asopSms.getIsUse())){
				hql.append(" and a.isUse ="+asopSms.getIsUse());
			}
			if(!StringHelpers.isNull(asopSms.getContent())){
				hql.append(" and a.content like '%"+ asopSms.getContent() +"%'");
			}
		}
		hql.append(" order by a.sendDate desc ");
		List<AsopSms> asopSmsList = ((List<AsopSms>) queryByPage(hql.toString(),pageGridPost));
		if(asopSmsList !=null && asopSmsList.size()>0){
			return asopSmsList;
		}
		return null;
	}

	@Override
	public List<AsopSms> getAsopSmsList(AsopSms asopSms) {
        StringBuffer hql=new StringBuffer("from AsopSms a where 1=1 ");
		
        if(asopSms!=null){
			if(!StringHelpers.isNull(asopSms.getIsUse())){
				hql.append(" and a.isUse ="+asopSms.getIsUse());
			}
			
			if(!StringHelpers.isNull(asopSms.getRecordId())){
				hql.append(" and a.recordId ="+asopSms.getRecordId());
			}
			
			hql.append(" and a.type ="+asopSms.getType());
			
			if(!StringHelpers.isNull(asopSms.getSendDate())){
				String sendDate = asopSms.getSendDate();
				String ymd = sendDate.substring(0,10);
				int hour = Integer.parseInt(sendDate.substring(11,sendDate.indexOf(":")));
				if(hour >= 0 && hour <= 12){//一天中前12个小时
					hql.append(" and a.sendDate >= '"+ymd+" 00:00:00'"+" and a.sendDate <= '"+ymd+" 11:59:59'");
				}else{
					hql.append(" and a.sendDate >= '"+ymd+" 12:00:00'"+" and a.sendDate <= '"+ymd+" 23:59:59'");
				}
				
			}
		}
		hql.append(" order by a.smsOrder desc ");
		List<AsopSms> asopServerList = ((List<AsopSms>) this.query(hql.toString()));
		if(asopServerList !=null && asopServerList.size()>0){
			return asopServerList;
		}
		return null;
	}

	@Override
	public List<AsopSms> getAsopSmsListByDate(AsopSms asopSms) {
		StringBuffer sql = new StringBuffer("select * from asop_sms a where 1=1 ");
		if(asopSms!=null){
			if(!StringHelpers.isNull(asopSms.getIsUse())){
				sql.append(" and a.isUse ="+asopSms.getIsUse());
			}
			
			if(!StringHelpers.isNull(asopSms.getRecordId())){
				sql.append(" and a.recordId ="+asopSms.getRecordId());
			}
			
			sql.append(" and a.type ="+asopSms.getType());
			
			sql.append(" and a.sendDate >= date_sub(NOW(),interval  "+Config.getAttribute("intervalMinute")+" minute)");
			
			sql.append(" and a.sendDate <= date_sub(NOW(),interval 0 minute)");
		}  
		
		List<AsopSms> asopServerList =  this.queryBySQL(sql.toString());
		if(asopServerList !=null && asopServerList.size()>0){
			return asopServerList;
		}
		return null;
	}

}
