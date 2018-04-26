package com.apps.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.Var;

public class PingUtil {

	 /**
	  * 第一种方法：Jdk1.5的InetAddresss,代码简单。
	  * 使用时应注意，如果远程服务器设置了防火墙或相关的配制，可能会影响到结果。另外，由于发送ICMP请求需要程序对系统有一定的权限，当这个权限无法满足时， isReachable方法将试着连接远程主机的TCP端口 7(Echo)。
	  * @param ipAddress
	  * @return
	  * @throws Exception
	  */
	 public static boolean ping(String ipAddress) throws Exception {
	        int  timeOut =  3000 ;  //超时应该在3钞以上        
	        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
	        return status;
	    }
	    
	 
	    /**
	     * 第二种方法：使用java调用cmd命令,这种方式最简单，可以把ping的过程显示在本地。
	     * @param ipAddress
	     * @throws Exception
	     */
	    public static void ping02(String ipAddress) throws Exception {
	        String line = null;
	        try {
	            Process pro = Runtime.getRuntime().exec("ping " + ipAddress);
	            BufferedReader buf = new BufferedReader(new InputStreamReader(
	                    pro.getInputStream()));
	            while ((line = buf.readLine()) != null)
	                System.out.println(line);
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
	    }
	    
	    public static boolean ping(String ipAddress, int pingTimes, int timeOut) throws Exception{ 
	        BufferedReader in = null;  
	        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令  
//	        String pingCommand = "ping " + ipAddress + " -c " + pingTimes +" -w "+timeOut;  //linux情况下的ping
	        String pingCommand = "ping " + ipAddress + " -n " + pingTimes +" -w "+timeOut;  //windows情况下的ping
	           //System.out.println(pingCommand);   
            Process p = r.exec(pingCommand);
            if (p == null) {  
                return false;   
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
            int connectedCount = 0;   
            String line = null;   
            while ((line = in.readLine()) != null) {    
                connectedCount += getCheckResult(line);   
            }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真 
            System.out.println(pingCommand +"  "+ (connectedCount == pingTimes));
            boolean result = false;
            if(((double)connectedCount/pingTimes) >=0.6){
            	result = true;
            }
            in.close();
            return result;  
	    }
	    //若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.适用于window环境下
	    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);  
	        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);  
	        Matcher matcher = pattern.matcher(line);  
	        while (matcher.find()) {
	            return 1;
	        }
	        return 0; 
	    }
	   
	  /*  //适用于linux
	    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);  
	        //System.out.println("line:"+line);
	    	Pattern pattern = Pattern.compile("(ttl=\\d+)(.*)(ms$)", Pattern.CASE_INSENSITIVE);  
	        Matcher matcher = pattern.matcher(line);  
	        while (matcher.find()) {
	            return 1;
	        }
	        return 0; 
	    }*/
	   
}
