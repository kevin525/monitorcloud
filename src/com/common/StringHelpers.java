package com.common;
/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2014年10月25日下午5:14:57
 *类说明：
 */
public class StringHelpers {
	/**
	 * 是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		return obj == null || "".equals(obj) || "null".equals(obj);
	}
	
	public static String getFormateIP(String ip){
		if(ip!=null && !"".equals(ip)){
			if("0.0.0.0".equals(ip)){
				ip="127.0.0.1";
				return ip;
			}else{
				return ip;
			}
		}else{
			return "";
		}
	}
}
