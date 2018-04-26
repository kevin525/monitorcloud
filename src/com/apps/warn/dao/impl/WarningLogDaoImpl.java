package com.apps.warn.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.apps.warn.dao.WarningLogDao;
import com.apps.warn.domain.ErrorLog;
import com.apps.warn.domain.WarningLog;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;
import com.sys.domain.model.User;
@Repository(value="warningLogDao")
public class WarningLogDaoImpl extends BaseDaoImpl implements WarningLogDao{

	@Override
	public void saveOrupdateWarning(WarningLog warningLog) {
		this.saveOrUpdate(warningLog);
	}
	
	@Override
	public void saveOrupdateError(ErrorLog errorLog) {
		this.saveOrUpdate(errorLog);
	}

	@Override
	public WarningLog getWarningLogById(long id) {
		return (WarningLog)this.findById(WarningLog.class, id);
	}

	@Override
	public void deleteWarningLog(long id) {
		WarningLog warning = (WarningLog) this.findById(WarningLog.class,id);
		this.delete(warning);
		
	}

	@Override
	public void save(Object o) {
		this.save(o);
	}

	@Override
	public List<WarningLog> getWarningLogList(WarningLog warningLog,
			PageGridPost pageGridPost) {
		 StringBuffer hql=new StringBuffer("from WarningLog a where 1=1 ");
			
			if(warningLog!=null){
				if(!StringHelpers.isNull(warningLog.getWarnName())){
					hql.append(" and a.warnName like '%"+ warningLog.getWarnName() +"%'");
				}
				if(!StringHelpers.isNull(warningLog.getMonitorType())){
					hql.append(" and a.monitorType like '%"+ warningLog.getMonitorType() +"%'");
				}
				if(!StringHelpers.isNull(warningLog.getStatus())){
					hql.append(" and a.status like '%"+ warningLog.getStatus() +"%'");
				} 
				if(!StringHelpers.isNull(warningLog.getFlag())){
					hql.append(" and a.flag like '%"+ warningLog.getFlag() +"%'");
				}
				if(!StringHelpers.isNull(warningLog.getWarnTime())){
					hql.append(" and a.warnTime like '"+ warningLog.getWarnTime() +"%'");
				}
			}
			hql.append(" order by a.warnTime desc ");
			List<WarningLog> warningList = ((List<WarningLog>) queryByPage(hql.toString(),pageGridPost));
			if(warningList !=null && warningList.size()>0){
				return warningList;
			}
			return null;
	}

	@Override
	public List<WarningLog> getWarningLogList(WarningLog warningLog) {
		StringBuffer hql=new StringBuffer("from WarningLog a where 1=1 ");
		
		if(warningLog!=null){
			if(!StringHelpers.isNull(warningLog.getWarnName())){
				hql.append(" and a.warnName like '%"+ warningLog.getWarnName() +"%'");
			}
			if(!StringHelpers.isNull(warningLog.getMonitorId())){
				hql.append(" and a.monitorId like '%"+ warningLog.getMonitorId() +"%'");
			}
			if(!StringHelpers.isNull(warningLog.getMonitorType())){
				hql.append(" and a.monitorType like '%"+ warningLog.getMonitorType() +"%'");
			}
			if(!StringHelpers.isNull(warningLog.getStatus())){
				hql.append(" and a.status like '%"+ warningLog.getStatus() +"%'");
			} 
			if(!StringHelpers.isNull(warningLog.getFlag())){
				hql.append(" and a.flag like '%"+ warningLog.getFlag() +"%'");
			}
			if(!StringHelpers.isNull(warningLog.getWarnTime())){
				hql.append(" and a.warnTime like '"+ warningLog.getWarnTime() +"%'");
			}
			
		}
		hql.append(" order by a.warnTime desc ");
		List<WarningLog> warningList = ((List<WarningLog>) this.query(hql.toString()));
		if(warningList !=null && warningList.size()>0){
			return warningList;
		}
		return null;
	}

	@Override
	public List<WarningLog> getProcessedWarningLogList(WarningLog warningLog,
			PageGridPost pageGridPost) {
		 StringBuffer hql=new StringBuffer("from WarningLog a where flag=1 ");
			
			if(warningLog!=null){
				if(!StringHelpers.isNull(warningLog.getWarnName())){
					hql.append(" and a.warnName like '%"+ warningLog.getWarnName() +"%'");
				}
			}
			hql.append(" order by a.warnTime desc ");
			List<WarningLog> warningList = ((List<WarningLog>) queryByPage(hql.toString(),pageGridPost));
			if(warningList !=null && warningList.size()>0){
				return warningList;
			}
			return null;
	}

	@Override
	public void updateWarning(WarningLog warningLog) {
		this.saveOrUpdate(warningLog);
	}

	@Override
	public WarningLog getWarningLog(String id, String type) {
		    StringBuffer hql=new StringBuffer("from WarningLog where monitorId=:monitorId and monitorType=:monitorType order by warnTime desc");
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("monitorId", id);
		    params.put("monitorType", type);
		   
			List<WarningLog> warningList = (List<WarningLog>)this.query(hql.toString(), params);
			if(warningList !=null && warningList.size()>0){
				return warningList.get(0);
			}
			return null;
	}

	@Override
	public int getWarningLogCount(WarningLog warningLog) {
StringBuffer hql=new StringBuffer("from WarningLog a where 1=1 ");
		
		if(warningLog!=null){
			if(!StringHelpers.isNull(warningLog.getMonitorType())){
				hql.append(" and a.monitorType like '%"+ warningLog.getMonitorType() +"%'");
			}
			if(!StringHelpers.isNull(warningLog.getStatus())){
				hql.append(" and a.status like '"+ warningLog.getStatus() +"'");
			} 
			if(!StringHelpers.isNull(warningLog.getWarnTime())){
				hql.append(" and a.warnTime like '"+ warningLog.getWarnTime() +"%'");
			}
			
		}
		List<WarningLog> warningList = ((List<WarningLog>) this.query(hql.toString()));
		if(warningList !=null && warningList.size()>0){
			return warningList.size();
		}
		return 0;
	}

	/**
	 * 今日异常：服务器，数据库，中间件
	 */
	@Override
	public List<WarningLog> getWarningLogDayList(WarningLog warningLog,PageGridPost pageGridPost) {
		StringBuffer hql=new StringBuffer("from WarningLog a where 1=1 ");
		
		if(warningLog!=null){
			if(!StringHelpers.isNull(warningLog.getMonitorType())){
				hql.append(" and a.monitorType in ("+ warningLog.getMonitorType() +")");
			}
			if(!StringHelpers.isNull(warningLog.getStatus())){
				hql.append(" and a.status like '"+ warningLog.getStatus() +"'");
			} 
			if(!StringHelpers.isNull(warningLog.getWarnTime())){
				hql.append(" and a.warnTime like '"+ warningLog.getWarnTime() +"%'");
			}
			
		}
		List<WarningLog> warningList = ((List<WarningLog>) queryByPage(hql.toString(),pageGridPost));
		if(warningList !=null && warningList.size()>0){
			return warningList;
		}
		return null;
	}

}
