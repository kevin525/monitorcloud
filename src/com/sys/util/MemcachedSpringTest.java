package com.sys.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.danga.MemCached.MemCachedClient;

public class MemcachedSpringTest {

	private MemCachedClient cachedClient;
	
	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("resources/spring/memcached.xml");
		cachedClient = (MemCachedClient)context.getBean("memcachedClient");
	}
	
	@Test
	public void testMemcachedSpring() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		String json = JSON.toJSONString(list);
		cachedClient.set("hello", "world");
		cachedClient.set("name", "张三");
		cachedClient.set("sex", "男");
		cachedClient.set("json", json);
		System.out.println(cachedClient.get("json").toString());
		String result = cachedClient.get("json").toString();
		List<String> ts = (List<String>) JSONArray.parseArray(result, String.class);
		for(int i=0;i<ts.size();i++){
			System.out.println(ts.get(i));
		}
	
		
	}
	
	public static List<String> getAllKeys(MemCachedClient memCachedClient) {
		List<String> list = new ArrayList<String>();
		Map<String, Map<String, String>> items = memCachedClient.statsItems();
		for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext();) {
			String itemKey = itemIt.next();
			Map<String, String> maps = items.get(itemKey);
		    for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext();) {
		    	String mapsKey = mapsIt.next();
                String mapsValue = maps.get(mapsKey);
                if (mapsKey.endsWith("number")) {  //memcached key 类型  item_str:integer:number_str
                	String[] arr = mapsKey.split(":");
                    int slabNumber = Integer.valueOf(arr[1].trim());
                    int limit = Integer.valueOf(mapsValue.trim());
                    Map<String, Map<String, String>> dumpMaps = memCachedClient.statsCacheDump(slabNumber, limit);
                    for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext();) {
                        String dumpKey = dumpIt.next();
                        Map<String, String> allMap = dumpMaps.get(dumpKey);
                        for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext();) {
                            String allKey = allIt.next();
                            list.add(allKey.trim());

                        }
                    }
                }
			}
		}
		return list;
	}
}