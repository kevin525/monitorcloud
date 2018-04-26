package com.apps.warn.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.warn.dao.ErrorLogDao;
import com.apps.warn.domain.ErrorLog;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.dao.impl.BaseDaoImpl;

@Repository(value = "errorLogDao")
public class ErrorLogDaoImpl extends BaseDaoImpl implements ErrorLogDao {

	@Override
	public List<ErrorLog> getErrorLogList(ErrorLog errorLog, PageGridPost pageGridPost) {
		StringBuffer hql = new StringBuffer("from ErrorLog a where 1=1 ");
		if(errorLog!=null){
			if(!StringHelpers.isNull(errorLog.getErrorName())){
				hql.append(" and a.errorName = '" + errorLog.getErrorName()+"'");
			}
		}
		hql.append(" order by a.errorTime desc ");
		List<ErrorLog> errorLogList = ((List<ErrorLog>) queryByPage(hql.toString(), pageGridPost));
		if (errorLogList != null && errorLogList.size() > 0) {
			return errorLogList;
		}
		return null;
	}
	
	@Override
	public void deleteErrorLog(long id) {
		ErrorLog errorLog = (ErrorLog) this.findById(ErrorLog.class,id);
		this.delete(errorLog);
		
	}

}
