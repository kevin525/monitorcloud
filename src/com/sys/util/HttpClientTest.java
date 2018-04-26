package com.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import com.common.utils.HttpUtil;

public class HttpClientTest {

	public static void getTzdeskInfo(){
		String url = "http://10.171.251.76:8083/tzdesk/toDojson.jsp";
		Map<String,String> params = new HashMap<String,String>();
	    params.put("pageSize", "20");
	    params.put("curPage", "1");
	    params.put("status", "1");
	    params.put("loginName", "dongxiaoqi");
	    params.put("uname", "dbsy");
	    params.put("callback", "jQuery182011391746006140441_1504750940322");
	    params.put("_", String.valueOf(System.currentTimeMillis()));
	    String json = HttpUtil.post(url, params);
	    System.out.println(json);
	    
	}
	
	public static boolean getTzoaData() {
		try {
			String url="http://10.171.251.74/tzoa/login.do";
			Map<String,String> params = new HashMap<String,String>();
		    params.put("userAccount", "dongxiaoqi");
		    params.put("userPwd", "123456");
		    params.put("loginType", "pc");
		    params.put("security", "noFilter");
		    String json = HttpUtil.post(url, params);
		    if(null != json && json.contains("true") && json.contains("success")){
		    	JSONObject jsonObject=JSONObject.fromObject(json);
		    	String result = (jsonObject.get("success")).toString();
		    	if("true".equals(result)){
		    		return true;
		    	}
		    }
		    
		} catch (Exception e) {
			return false;
		}
		
	    return false;
	}
	
	public static boolean getFiletransferData() {
		try {
			String url="http://10.171.251.15:8080/tzoa/filetransfer/listRecvFile.do";
			Map<String,String> params = new HashMap<String,String>();
		    params.put("queryType", "draft");
		    params.put("isOpen", "false");
		    params.put("start", "0");
		    params.put("limit", "5");
		    params.put("leaderUserAccount", "zhangmo");
		    String json = HttpUtil.post(url, params);
		    if(null != json && json.contains("true") && json.contains("success")){
		    	JSONObject jsonObject=JSONObject.fromObject(json);
		    	String result = (jsonObject.get("success")).toString();
		    	if("true".equals(result)){
		    		return true;
		    	}
		    }
		} catch (Exception e) {
			return false;
		}
		
	    return false;
	}
	
	
	public static void main4(String[] args){
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		for(int i=0;i<2000;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("=========================="+Thread.currentThread().getName()+"线程开始："+df.format(new Date()));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					getTzoaData();
					System.out.println("=========================="+Thread.currentThread().getName()+"线程结束："+df.format(new Date()));
				}
			}).start();
		}
	}
	
	public static void main(String[] args){
		getFiletransferData();
		
		ExecutorService service = Executors.newCachedThreadPool(); //创建一个线程池
	       final CountDownLatch cdOrder = new CountDownLatch(1);//指挥官的命令，设置为1，指挥官一下达命令，则cutDown,变为0，战士们执行任务
	       final CountDownLatch cdAnswer = new CountDownLatch(10);//因为有三个战士，所以初始值为3，每一个战士执行任务完毕则cutDown一次，当三个都执行完毕，变为0，则指挥官停止等待。
	    	   for(int i=0;i<500;i++){
		           Runnable runnable = new Runnable(){
		               public void run(){
		                   try {
		                       System.out.println("线程" + Thread.currentThread().getName() +
		                               "正准备接受命令！");
		                       cdOrder.await(); //战士们都处于等待命令状态
		                       System.out.println("线程" + Thread.currentThread().getName() +"执行命令开始！");
		                       //HttpClientTest.getTzdeskInfo();
		                      // HttpClientTest.getTzoaData();
		                       TestSQL.testFileTranfer();
		                       System.out.println("线程" + Thread.currentThread().getName() +"执行命令结束！");
		                       Thread.sleep((long)(Math.random()*6000));
		                       System.out.println("线程" + Thread.currentThread().getName() +
		                               "回应命令处理结果！");

		                   } catch (Exception e) {
		                       e.printStackTrace();
		                   } finally {
		                       cdAnswer.countDown(); //任务执行完毕，返回给指挥官，cdAnswer减1。
		                   }
		               }
		           };
		           service.execute(runnable);//为线程池添加任务
		       }
	      try {
	           Thread.sleep((long)(Math.random()*6000));

	           System.out.println("线程" + Thread.currentThread().getName() +
	                   "即将发布命令！！");
	           cdOrder.countDown(); //发送命令，cdOrder减1，处于等待的战士们停止等待转去执行任务。
	           System.out.println("线程" + Thread.currentThread().getName() +
	                   "已发送命令，正在等待结果！！");
	           cdAnswer.await(); //命令发送后指挥官处于等待状态，一旦cdAnswer为0时停止等待继续往下执行
	           System.out.println("线程" + Thread.currentThread().getName() +
	                   "已收到所有响应结果！！");
	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally {

	       }
	       service.shutdown(); //任务结束，停止线程池的所有线程
	}
	
}
