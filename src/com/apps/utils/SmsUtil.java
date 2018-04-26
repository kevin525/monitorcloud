package com.apps.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.apps.msgRemind.sms.domain.AsopSms;
import com.common.constants.Constant;

public class SmsUtil {
	public static AsopSms buildAsopSms(String content,long recordId,String type,String phoneNum){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		AsopSms sms = new AsopSms();
		sms.setIsUse(Constant.ACTIVITY);
		sms.setRecordId(recordId);
		sms.setSendDate(sdf.format(new Date()));
		sms.setType(type);
		sms.setContent(content);
		sms.setPhoneNums(phoneNum);
		sms.setSmsOrder(0);
		sms.setSendStatus(Constant.SMS_SEND_FAIL);
		return sms;
	}
}
