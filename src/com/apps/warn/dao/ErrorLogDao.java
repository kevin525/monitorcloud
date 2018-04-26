package com.apps.warn.dao;

import java.util.List;

import com.apps.warn.domain.ErrorLog;
import com.common.pagetag.PageGridPost;

public interface ErrorLogDao {

	/**
	 * 分页展示错误日志
	 * @param errorLog
	 * @param pageGridPost
	 * @return
	 */
	public List<ErrorLog> getErrorLogList(ErrorLog errorLog, PageGridPost pageGridPost);
	
	/**
	 * 删除错误日志
	 * @param id
	 */
	public void deleteErrorLog(long id);

}
