package com.common.quartz;

import java.util.Date;


import com.common.FormatDateUtil;
import com.common.threadPool.PoolManager;

public class PushUserQuarz {
	@SuppressWarnings("static-access")
	public void pushUser(){
		try {
			PoolManager.getInstance().getThreadPool().execute(new Runnable() {
				public void run() {
					System.out.println(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date())+"推送用户开始★★★★★★★★★★★");  
					//new PushUserInfo().pushUserDataInfo();
					System.out.println(FormatDateUtil.formatDateToString("yyyy-MM-dd HH:mm:ss", new Date())+"★★★★★★ 推送用户结束★★★★★"); 
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
