package com.apps.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * Telnet连通性测试
 * @author Administrator
 *
 */
public class TelnetUtil {
	
	public static boolean testTelnet(String ip,int port){
		 Socket server = new Socket();
	        try {
	        	InetSocketAddress address = new InetSocketAddress(ip,port);
	        	server.connect(address, 5000);
	        }  catch (UnknownHostException e) {
	            System.out.println("telnet失败");
	            return false;
	        } catch (IOException e) {
	            System.out.println("telnet失败");
	            return false;
	        }finally{
	            if(server!=null)
					try {
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        }
	        return true;
	}
	    

}
