package com.sys.util;


import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * @ClassName: MemcachedUtil
 * @Description: memcached缓存工具类
 * @author LG
 * @date 2018年4月3日 下午4:27:30
 */
public class MemcachedUtil {

	 private static MemCachedClient cachedClient = new MemCachedClient("neeaMemcachedPool");
	 private static MemcachedUtil memcachedUtil;
	 //是否启用MemCached内存数据库
	 protected static boolean enUsed = true;
	 
	 //从配置文件读为false，直接在代码里读true
	 protected static boolean config = false;
	 
	 //定义可用的MemCached服务器列表，用于分布式存储
	 private static String[] serverListArr = new String[1];
	    
	 //定义各MemCached服务器的负载权重列表，与服务器列表按先后顺序对应
	 private static Integer[] weightListArr = new Integer[1];
	 
	 //定义MemCached服务器运行环境表，配置文件中关于参数相关数据将保存到该表
	 private static Map<String, String> serverConfig;
	 
	 //定义MemCached服务器运行状态表，用于保存各状态的中文解释
	 protected static HashMap<String, String> statsItems;
	    
	 private MemcachedUtil(){
		 
	 }
	 
	 public static MemcachedUtil getInstance(){
		 if(memcachedUtil == null){
			 synchronized (MemcachedUtil.class) {
				if(memcachedUtil == null){
					memcachedUtil = new MemcachedUtil();
				}
			}
		 }
		 return memcachedUtil;
	 }
	 

	 
	 
	 public String[] servers(){
		 if(enUsed){
			 return serverListArr;
		 }
		 return null;
	 }
	 
	 /**
	  * @Title: used
	  * @Description: 是否启用memcached缓存
	  * @return boolean
	  * @throws
	  */
	 public static boolean used(){
		 return enUsed;
	 }
	 
	 /**
	  * @Title: add 插入新记录
	  * @Description: 添加缓存中不存在的key,如果key已经存在，不会放进缓存
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @return boolean 操作结果
	  * @throws
	  */
	 public boolean add(String key,Object value){
		 if(enUsed){
			 return cachedClient.add(key, value);
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: add 插入新记录并设置超时日期
	  * @Description: 添加缓存中不存在的key,如果key已经存在，不会放进缓存
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @param expireDate 失效时间
	  * @return boolean 操作结果
	  * @throws
	  */
	 public boolean add(String key,Object value,Date expireDate){
		 if(enUsed){
			 return cachedClient.add(key, value,expireDate);
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: add 插入新记录并设置超时天数
	  * @Description: 记录的Key在缓存中不存在
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @param expireDays 超时天数
	  * @return boolean
	  * @throws
	  */
	 public boolean add(String key,Object value,int expireDays){
		 if(enUsed){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, expireDays);
			return cachedClient.add(key, value,calendar.getTime());
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: 插入新记录或更新已有记录
	  * @Description: 记录的Key在缓存中不存在则插入；否则更新
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @return boolean 操作结果
	  * @throws
	  */
	 public boolean set(String key,Object value){
		 if(enUsed){
			 return cachedClient.set(key, value);
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: set 插入新记录或更新已有记录，并设置超时日期
	  * @Description: 记录的Key在缓存中不存在则插入；否则更新
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @param expireDate 超时日期
	  * @return boolean
	  * @throws
	  */
	 public boolean set(String key,Object value,Date expireDate){
		 if(enUsed){
			 return cachedClient.set(key, value,expireDate);
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: set 插入新记录或更新已有记录，并设置超时天数
	  * @Description: 记录的Key在缓存中不存在则插入；否则更新
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @param expireDays 超时天数
	  * @return boolean
	  * @throws
	  */
	 public boolean set(String key,Object value,int expireDays){
		 if(enUsed){
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(new Date());
			 calendar.add(Calendar.DATE, expireDays);
			 return cachedClient.set(key, value,expireDays);
		 }
		 return false;
	 }
	
	 /**
	  * @Title: replace 更新已有记录
	  * @Description: 记录的Key在缓存中已经存在
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @return boolean
	  * @throws
	  */
	 public boolean replace(String key,Object value){
		 if(enUsed){
			 return cachedClient.replace(key, value);
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: replace 更新已有记录，并设置超时日期
	  * @Description: 该值在缓存中已经存在
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @param expireDate 超时日期
	  * @return boolean
	  * @throws
	  */
	 public boolean replace(String key,Object value,Date expireDate){
		 if(enUsed){
			 return cachedClient.replace(key, value, expireDate);
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: replace 更新已有记录，并设置超时天数
	  * @Description: 该值在缓存中已经存在
	  * @param key 记录的主键
	  * @param value 记录的内容
	  * @param expireDays 超时天数
	  * @return boolean
	  * @throws
	  */
	 public boolean replace(String key,Object value,int expireDays){
		 if(enUsed){
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(new Date());
			 calendar.add(Calendar.DATE, expireDays);
			 return cachedClient.replace(key, value, calendar.getTime());
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: get
	  * @Description: 返回单条记录
	  * @param key 记录的主键
	  * @return Object 记录的内容
	  * @throws
	  */
	 public Object get(String key){
		 if(enUsed){
			return cachedClient.get(key); 
		 }
		 return null;
	 }
	 
	 /**
	  * @Title: get
	  * @Description: 返回多条记录
	  * @param keys 记录的主键数组
	  * @return Map<String,Object> 多条记录的内容
	  * @throws
	  */
	 public Map<String, Object> get(String[] keys){
		 if(enUsed){
			return cachedClient.getMulti(keys); 
		 }
		 return null;
	 }
	 
	 /**
	  * @Title: delete 删除记录
	  * @Description: 执行该方法之后，使用stats的统计结果会同步更新
	  * @param key 记录的主键
	  * @return boolean
	  * @throws
	  */
	 public boolean delete(String key){
		 if(enUsed){
			 return cachedClient.delete(key);
		 }
		 return false;
	 }
	 
	 /**
	  * @Title: incr
	  * @Description: 增长值加1
	  * @param key 记录的主键
	  * @return long
	  * @throws
	  */
	 public long incr(String key){
		 if(enUsed){
			 return cachedClient.incr(key);
		 }
		 return 0;
	 }
	 
	 /**
	  * @Title: incr
	  * @Description: 增长值 按rate增加
	  * @param key 记录的主键
	  * @param rate 增长的幅度
	  * @return long
	  * @throws
	  */
	 public long incr(String key,long rate){
		 if(enUsed){
			 return cachedClient.incr(key, rate);
		 }
		 return 0;
	 }
	 
	 /**
	  * @Title: decr
	  * @Description: 减少值 减1
	  * @param key 记录的主键
	  * @return long
	  * @throws
	  */
	 public long decr(String key){
		 if(enUsed){
			 return cachedClient.decr(key);
		 }
		 return 0;
	 }
	 
	 /**
	  * @Title: decr
	  * @Description: 减少值 按rate减少
	  * @param key 记录的主键
	  * @param rate 减少的幅度
	  * @return long
	  * @throws
	  */
	 public long decr(String key,long rate){
		 if(enUsed){
			 return cachedClient.decr(key, rate);
		 }
		 return 0;
	 }
	 
	 /**
	  * @Title: flushAll 清空全部缓存数据,慎用！！
	  * @Description:  执行该方法之后，使用stats的统计结果不会马上发生变化，每get一个不存在的item之后，该item的值才会被动清空
	  * @return
	  * @return boolean
	  * @throws
	  */
	 public boolean flushAll(){
		 if(enUsed){
			 return cachedClient.flushAll();
		 }
		 return false;
	 }
	 
	/**
     * @category 返回所有缓存服务器当前的运行状态
     * @return
     * 
     *   Map
     *    |-- Key : ServerName01, Value : LinkedHashMap
     *    |                                |-- Key : statName01, Value : statValue
     *    |                                |-- ...
     *    |
     *    |-- Key : ServerName02, Value : LinkedHashMap
     *    |                                |-- Key : statName01, Value : statValue
     *    |                                |-- ...
     *    |
     *    |-- ...
     *  
     */ 
	 public Map<String,LinkedHashMap<String, String>> stats(){
		 if(enUsed){
			 Map<String,LinkedHashMap<String, String>> retMap = new HashMap<String,LinkedHashMap<String, String>>();
		     for(String server : serverListArr){
		        LinkedHashMap<String, String> serverStats = this.stats(server);
		        retMap.put(server, serverStats);
		     }
		     return retMap;
		 }
		 return null;
	 }
	 
	 
	   public LinkedHashMap<String, String> stats(String server){
	        if(!enUsed) return null;
	        LinkedHashMap<String, String> retMap = new LinkedHashMap<String, String>();
	        Map<String, Map<String, String>> statsList = cachedClient.stats(new String[]{server});
	        DecimalFormat format = new DecimalFormat("0.0");
	        for(Object serverTitle : statsList.keySet().toArray()){
	            Map<String, String> serverStats = (Map<String, String>)statsList.get(serverTitle);
	            retMap.put(statsItems.get("pid"), serverStats.get("pid").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("version"), serverStats.get("version").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("pointer_size"), serverStats.get("pointer_size").replaceAll("\\r\\n", "") + "位");
	            retMap.put(statsItems.get("time"), new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(serverStats.get("time").replaceAll("\\r\\n", "")) * 1000)).toString());
	            retMap.put(statsItems.get("uptime"), format.format(Double.parseDouble(serverStats.get("uptime").replaceAll("\\r\\n", ""))/(60*60)) + "小时");
	            retMap.put(statsItems.get("connection_structures"), serverStats.get("connection_structures").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("total_connections"), serverStats.get("total_connections").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("curr_connections"), serverStats.get("curr_connections").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("limit_maxbytes"), format.format(Double.parseDouble(serverStats.get("limit_maxbytes").replaceAll("\\r\\n", ""))/(1024*1024)) + "MB");
	            retMap.put(statsItems.get("bytes"), format.format(Double.parseDouble(serverStats.get("bytes").replaceAll("\\r\\n", ""))/(1024*1024)) + "MB");
	            retMap.put(statsItems.get("bytes_written"), format.format(Double.parseDouble(serverStats.get("bytes_written").replaceAll("\\r\\n", ""))/(1024)) + "KB");
	            retMap.put(statsItems.get("bytes_read"), format.format(Double.parseDouble(serverStats.get("bytes_read").replaceAll("\\r\\n", ""))/(1024)) + "KB");
	            retMap.put(statsItems.get("total_items"), serverStats.get("total_items").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("curr_items"), serverStats.get("curr_items").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("cmd_get"), serverStats.get("cmd_get").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("get_hits"), serverStats.get("get_hits").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("get_misses"), serverStats.get("get_misses").replaceAll("\\r\\n", ""));
	            retMap.put(statsItems.get("cmd_set"), serverStats.get("cmd_set").replaceAll("\\r\\n", ""));
	        }
	        return retMap;
	    }
	   
	   /**
	     * @category 返回指定服务器及Slab中当前使用的item列表
	     * @param server 服务器地址:端口
	     * @param slab SlabId
	     * @param counter 最多显示items条数
	     * @return
	     */
	    public LinkedList<String> items(String server, int slab, int counter){
	        if(enUsed){
	        	LinkedList<String> ret = new LinkedList<String>();
	 	        Map<String, String> itemsKey = cachedClient.statsCacheDump(new String[]{server}, slab, counter).get(server);
	 	        for(Object key : itemsKey.keySet().toArray()){
	 	            ret.add(key.toString());
	 	        }
	 	        return ret;
	        }
	        return null;
	    }
	    
	    /**
	     * @category 返回指定服务器当前使用的SlabsID列表
	     * @param server 服务器地址:端口
	     * @return
	     */
	    public LinkedList<Integer> slabs(String server){
	    	if(enUsed){
	    		LinkedList<Integer> slabsId = new LinkedList<Integer>();
		        Map<String, String> itemsMap = cachedClient.statsItems(new String[]{server}).get(server);
		        Object[] itemsArr = itemsMap.keySet().toArray();
		        for(int i=0, len=itemsArr.length; i<len; i+=2){
		            slabsId.add(Integer.parseInt(itemsArr[i].toString().split(":")[1]));
		        }
		        return slabsId;
	    	}
	        return null;
	    }

	  //设置全局静态参数，以下代码在整个服务器运行周期内仅运行一次！
	    static {
	        if(enUsed && config){ //如果已启用memcached缓存服务
	        	//初始化MemCached运行环境配置
	            //首先初始化各参数默认值，然后加载配置文件，遍历其中的参数值并进行覆盖。
	            initConfig();
	            
	            //获取socke连接池的实例对象
	            SockIOPool pool = SockIOPool.getInstance();
	    
	            //设置可用的MemCached服务器信息，实现分布式存储
	            pool.setServers(serverListArr);
	            
	            //设置各MemCached服务器的负载权重，根据可支配内存实现负载均衡
	            pool.setWeights(weightListArr);
	    
	            //设置初始连接数
	            pool.setInitConn(Integer.parseInt(serverConfig.get("initConn").toString()));
	            
	            //设置最小连接数
	            pool.setMinConn(Integer.parseInt(serverConfig.get("minConn").toString()));
	            
	            //设置最大连接数
	            pool.setMaxConn(Integer.parseInt(serverConfig.get("maxConn").toString()));
	            
	            //设置连接最大空闲时间
	            pool.setMaxIdle(Long.parseLong(serverConfig.get("maxIdle").toString()));
	    
	            //设置主线程的睡眠时间，每隔该时间维护一次各连接线程状态
	            pool.setMaintSleep(Long.parseLong(serverConfig.get("maintSleep").toString()));
	    
	            //关闭nagle算法
	            pool.setNagle(false);
	            
	            //读取操作的超时限制
	            pool.setSocketTO(Integer.parseInt(serverConfig.get("socketTO").toString()));
	            
	            //连接操作的超时限制，0为不限制
	            pool.setSocketConnectTO(Integer.parseInt(serverConfig.get("socketConnTO").toString()));
	    
	            //初始化连接池
	            pool.initialize();
	        }
	    }
		    
		   
		    
	    /**
	     * @category 初始化MemCached运行环境配置
	     * @category 注：该方法在整个服务器周期内仅运行一次
	     */
	    protected static void initConfig(){
	        
	        //初始化可用的MemCached服务器列表默认值（本机）
	        serverListArr[0] = "127.0.0.1:11211";
	        weightListArr[0] = 1;
	        
	        //初始化MemCached服务器运行环境表（默认值），当某参数未在配置文件中进行定义时，将使用该默认值
	        serverConfig = new HashMap<String, String>(){
	            private static final long serialVersionUID = 1L;
	            {
	                put("initConn", "5"); //设置初始连接数
	                put("minConn", "5"); //设置最小连接数
	                put("maxConn", "250"); //设置最大连接数
	                put("maxIdle", "21600000"); //设置连接最大空闲时间（6小时）
	                put("maintSleep", "30"); //设置主线程的睡眠时间（30秒）
	                put("socketTO", "10000"); //读取操作的超时限制（10秒）
	                put("socketConnTO", "0"); //连接操作的超时限制（不限制）
	                put("compressEnable", "true"); //是否启用自动压缩（启用）
	                put("compressThreshold", "65536"); //超过指定大小的数据都会被压缩（64K）
	            }
	        };

	        //初始化MemCached服务器运行状态表，对各状态进行中文解释
	        statsItems = new HashMap<String, String>(){
	            {
	                put("pid", "MemCached服务进程ID");
	                put("version", "MemCached服务版本");
	                put("pointer_size", "MemCached服务器架构");
	                put("time", "服务器当前时间");
	                put("uptime", "服务器本次启动以来，总共运行时间");
	                put("connection_structures", "服务器分配的连接结构数");
	                put("total_connections", "服务器本次启动以来，累计响应连接总次数");
	                put("curr_connections", "当前打开的连接数");
	                put("limit_maxbytes", "允许服务支配的最大内存容量");
	                put("bytes", "当前已使用的内存容量");
	                put("bytes_written", "服务器本次启动以来，写入的数据量");
	                put("bytes_read", "服务器本次启动以来，读取的数据量");
	                put("total_items", "服务器本次启动以来，曾存储的Item总个数");
	                put("curr_items", "当前存储的Item个数");
	                put("cmd_get", "服务器本次启动以来，执行Get命令总次数");
	                put("get_hits", "服务器本次启动以来，Get操作的命中次数");
	                put("get_misses", "服务器本次启动以来，Get操作的未命中次数");
	                put("cmd_set", "服务器本次启动以来，执行Set命令总次数");
	            }
	        };
	    }
}
