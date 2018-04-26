package com.common.osplugins;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JsonUtil {
	static Log logger = LogFactory.getLog(JsonUtil.class);

	/**
	 * 转换为json字符串时，过滤集合类型
	 */
	public static JsonConfig COLLECTION_FILTER = new JsonConfig();

	/**
	 * 转换为json字符串时，过滤volumns，files，documents属性
	 */
	
	private static JsonConfig FILTER = new JsonConfig();

	/**
	 * 将对象obj转化为json字符串
	 * 
	 * @param obj
	 * @param jsonConfig
	 * @return
	 */
	public synchronized static String toString(Object obj) {
		if (obj instanceof List)
			return JSONArray.fromObject(obj).toString();
		else
			return JSONObject.fromObject(obj).toString();
	}

	/**
	 * 将对象obj转化为json字符串，允许指定一个JsonConfig参数
	 * 
	 * @see ces.gdda.App#COLLECTION_FILTER
	 * @see ces.gdda.App#ARCHIVE_FILTER
	 * @param obj
	 * @param jsonConfig
	 * @return
	 */
	public synchronized static String toString(Object obj, JsonConfig jsonConfig) {
		if (obj instanceof Collection)
			return JSONArray.fromObject(obj, jsonConfig).toString();
		else
			return JSONObject.fromObject(obj, jsonConfig).toString();
	}

	/**
	 * 将对象obj转化为json字符串，允许指定一个JsonConfig参数
	 * 
	 * @see ces.gdda.App#COLLECTION_FILTER
	 * @see ces.gdda.App#ARCHIVE_FILTER
	 * @param obj
	 * @param jsonConfig
	 * @return
	 */
	public synchronized static String toString(Object obj, String[] filters) {
		FILTER.setExcludes(filters);
		if (obj instanceof Collection)
			return JSONArray.fromObject(obj, FILTER).toString();
		else
			return JSONObject.fromObject(obj, FILTER).toString();
	}

	/**
	 * 将异常e转化为json字符串
	 * 
	 * @param e
	 * @return
	 */
	public synchronized static String toString(Throwable e) {
		Throwable now = e;
		Throwable next = now.getCause();
		while (next != null) {
			now = next;
			next = now.getCause();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", now.toString());
		map.put("message", now.getMessage() == null ? "" : now.getMessage());
		return JSONObject.fromObject(map).toString();
	}
	
	public static String listToJsonString(List<?> list){
		JsonConfig jsonc=new JsonConfig();
		jsonc.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		JSONArray jsona=new JSONArray();
		if(list!=null){
			for(Object be:list){
				JSONObject jsonObject =JSONObject.fromObject(be,jsonc);
				jsona.add(jsonObject);
			}
		}
		return jsona.toString();
	}
	
	/**
	 * 将json字符串转为为指定对象的list
	 * @param jsonString
	 * @param entityClass
	 * @return
	 */
	public static List<?> JsonToList(String jsonString,Class<?> entityClass){
		JSONArray jsonarray = JSONArray.fromObject(jsonString);  
		List<?> list = (List<?>)JSONArray.toCollection(jsonarray, entityClass);  
		return list;
	}
}
