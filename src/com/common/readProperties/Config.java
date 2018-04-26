package com.common.readProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2015年3月3日下午5:11:34
 *类说明：读取properties文件中的内容
 */
public class Config {
	  static Logger logger = Logger.getLogger(Config.class.getName());
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	 private static HashMap<String, String> VALUES = new HashMap();
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	 private static List<String> INIT_RESNAME = new ArrayList();
	  private static String resourceName = "application";
	  private static ResourceBundle resource = null;
	  private static Config instance = null;
	  
	  public static Config getInstance(String fileName)
	  {
	    try {
			resourceName = fileName;
			if ((!INIT_RESNAME.contains(fileName)) && (
			  (resource == null) || (!resourceName.equals(fileName)))) {
			  logger.info("加载" + resourceName + ".properties资源文件");
			  Locale locale = Locale.getDefault();
			  resource = ResourceBundle.getBundle(fileName,locale);
			}

			if (instance == null)
			  instance = new Config();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return instance;
	  }
	  
	  public static String getAttribute(String key)
	  {
	    String rkey = "application_" + key;
	    String value = (String)VALUES.get(rkey);
	    if (StringUtils.isBlank(value)) {
	      try {
	        if ((resource == null) || (!resourceName.equals("application"))) {
	          resourceName = "application";
	          //logger.info("加载" + resourceName + ".properties资源文件");
	          resource = ResourceBundle.getBundle(resourceName);
	        }
	        //logger.info("加载" + resourceName + ".properties资源文件的[" + key + "]属性值");
	        value = resource.getString(key);
	        VALUES.put(rkey, value);
	      } catch (RuntimeException e) {
	    	  e.printStackTrace();
	        //logger.warn("资源文件" + resourceName + ".properties没有[" + key + "]属性");
	      }
	    }
	    return value;
	  }

	  public String getValue(String key)
	  {
	    String rkey = resourceName + "_" + key;
	    String value = (String)VALUES.get(rkey);
	    if (StringUtils.isBlank(value)) {
	      logger.info("加载" + resourceName + ".properties资源文件的[" + key + "]属性值");
	      value = resource.getString(key);
	      VALUES.put(rkey, value);
	    }
	    return value;
	  }

	  @SuppressWarnings("rawtypes")
	public static void initConfig(String fileName)
	  {
	    logger.info("加载" + fileName + ".properties资源文件的所有属性值[start]");
	    INIT_RESNAME.add(fileName);
	    resource = ResourceBundle.getBundle(fileName);
	    Set keys = resource.keySet();
	    if ((keys == null) || (keys.isEmpty())) return;
	    for (Iterator iterator = keys.iterator(); iterator.hasNext(); ) {
	      String key = (String)iterator.next();
	      logger.info("加载[" + key + "]属性值");
	      VALUES.put(fileName + "_" + key, resource.getString(key));
	    }
	    logger.info("加载" + fileName + ".properties资源文件的所有属性值[end]");
	  }
}
