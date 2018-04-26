package com.apps.warn.service;

import java.util.List;

import com.apps.warn.domain.ErrorLog;
import com.common.pagetag.PageGridPost;

public interface ErrorLogService {

	/**
	 * 分页展示错误日志列表
	 * 
	 * @param errorLog
	 * @param pageGridPost
	 * @return
	 */
	public List<ErrorLog> getErrorLogList(ErrorLog errorLog, PageGridPost pageGridPost);
	
	/**
	 * 删除错误日志
	 * @param ids
	 */
	public void deleteAllError(List<String> ids);

}
