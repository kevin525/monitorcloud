package com.common.osplugins;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JAVA对象转JSON时的日期处理对象
 * @author litao
 *
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private String format ="yyyy-MM-dd hh:mm:ss"; 
	@SuppressWarnings("unused")
	private DateFormat dateFormat;
	public DateJsonValueProcessor() {
		
	}
	/**  
	 * 构造方法.  
	 *  
	 * @param datePattern <SPAN class=hilite2><SPAN class=hilite2>日期</SPAN></SPAN>格式  
	 */
	public DateJsonValueProcessor(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception ex) {
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		}
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		//return dateFormat.format((Date) value);
		if(value instanceof Date){
			SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.UK);
			return sdf.format(value);
		}  
		 return value == null ? "" : value.toString();  
	}
}
