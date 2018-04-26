package com.apps.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.apps.msgRemind.inform.domain.AsopInform;
import com.apps.warn.domain.WarningLog;
import com.common.constants.Constant;

public class InformUtil {

	 public static AsopInform buildAsopInform(WarningLog log,String content){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			AsopInform inform = new AsopInform();
			
			inform.setMonitorId(log.getMonitorId());
			inform.setMonitorType(log.getMonitorType());
			inform.setContent(content);
			inform.setSendDate(sdf.format(new Date()));
			inform.setReadStatus(Constant.INFORM_UNREAD);
			return inform;
		}
}
