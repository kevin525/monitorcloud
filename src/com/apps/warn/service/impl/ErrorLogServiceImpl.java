package com.apps.warn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.warn.dao.ErrorLogDao;
import com.apps.warn.domain.ErrorLog;
import com.apps.warn.service.ErrorLogService;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;

@Service(value = "errorLogService")
public class ErrorLogServiceImpl implements ErrorLogService {

	@Autowired
	private ErrorLogDao errorLogDao;

	@Override
	public List<ErrorLog> getErrorLogList(ErrorLog errorLog, PageGridPost pageGridPost) {
		return errorLogDao.getErrorLogList(errorLog, pageGridPost);
	}
	
	@Override
	public void deleteAllError(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	errorLogDao.deleteErrorLog(Long.parseLong(id));
			    }
			}
		}
		
	}

}
