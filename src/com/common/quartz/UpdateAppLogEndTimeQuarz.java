package com.common.quartz;

import java.util.Date;

import com.common.FormatDateUtil;
import com.common.OnlineUserList;
import com.common.context.ApplicationContextUtil;
import com.common.encryption.RSAUtils;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2015年3月16日下午3:41:36
 *类说明：
 */
public class UpdateAppLogEndTimeQuarz {
	
	public void updateAppLogEndTime(){
		System.out.println(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date())+"★★★★更新用户退出的时间开始★★★★★★★");  
        try {
			initDbLog();
			OnlineUserList.logoutALLRegist();//把userList中的所有token与用户的信息删除
			initRSAMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
       System.out.println(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date())+"★★★★★★更新用户退出的时间 结束★★★★★"); 
	}
	
	 private void initDbLog(){        //初始化数据库，把d_log中没有结束时间的时间置为当前时间
	    	///AppLogService appLogService = (AppLogService) ApplicationContextUtil
			//.getBean("appLogService");
	    	//appLogService.updateEndTime(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
	 }
	 
	 /**
	 * 清空RSA的秘钥和公钥
	 */
	private void initRSAMap(){
		 RSAUtils.removeRSAMap();
	 }
}
