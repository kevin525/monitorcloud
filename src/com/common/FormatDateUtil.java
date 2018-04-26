package com.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年11月23日下午4:16:25
 *类说明：
 */
public class FormatDateUtil {
	private static String defaultDatePattern = "yyyy-MM-dd";
	@SuppressWarnings("unused")
	private static  String FM_DATE_AND_TIME = "yyyy-MM-dd HH:mm:ss";
	
	public static Date formatStringToDate(String pattern,String dateString){
		if(StringHelpers.isNull(pattern)){
			pattern=defaultDatePattern;
		}
		if(!StringHelpers.isNull(dateString)){
			SimpleDateFormat sd=new SimpleDateFormat(pattern);
			try {
				return sd.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	public static String formatDateToString(String pattern,Date dateString){
		if(StringHelpers.isNull(pattern)){
			pattern=defaultDatePattern;
		}
		if(!StringHelpers.isNull(dateString)){
			SimpleDateFormat sd=new SimpleDateFormat(pattern);
			try {
				return sd.format(dateString);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

}
