package com.apps.tasks;

import org.springframework.stereotype.Component;

import com.apps.warn.domain.ErrorLog;
import com.apps.warn.service.WarningLogService;
import com.common.context.ApplicationContextUtil;
import com.common.servlet.InitServlet;
import com.common.threadPool.ErrorLogPool;

/**
 * @ClassName: WarningLogTask
 * @Description: 告警日志任务处理线程
 * @author LG
 * @date 2017年11月29日 下午2:09:39
 */
@Component 
public class ErrorLogTask extends Thread{
	
	public ErrorLogTask(){
		super();
	}
	
	public void run(){
		ErrorLogPool pool = InitServlet.getErrorPool();
		while(true){
			try{
				ErrorLog errorLog = pool.loadMessage();
				if (null != errorLog) {
					WarningLogService warningLogService = (WarningLogService) ApplicationContextUtil.getBean("warningLogService");
					warningLogService.saveOrupdateError(errorLog);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
}
