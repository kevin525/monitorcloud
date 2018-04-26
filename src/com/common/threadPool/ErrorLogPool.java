package com.common.threadPool;

import java.util.LinkedList;
import java.util.List;

import com.apps.warn.domain.ErrorLog;
import com.common.utils.TimeUtil;

/**
 * @ClassName: WarningErrorPool
 * @Description: 告警错误日志
 * @author LG
 * @date 2017年11月29日 下午2:12:59
 */
public class ErrorLogPool {

	private List<ErrorLog> messagePool = new LinkedList<ErrorLog>();
	
	/**
	 * 查询消息
	 * @return
	 */
	public synchronized ErrorLog loadMessage(){
		try{
			while(messagePool.size() <= 0){
				String time = TimeUtil.GetCurDateTime();
				System.out.println(time+":当前时刻消息池中没有消息");
				this.wait();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		String time = TimeUtil.GetCurDateTime();
		System.out.println(time+":当前时刻消息池中有消息了");
		ErrorLog tool = messagePool.remove(0);
		System.out.println("获取消息后 消息池大小为：："+messagePool.size());
		return tool;
	}
	
	/**
	 * 保存单个消息
	 * @param message
	 */
	public synchronized void saveMessage(ErrorLog message){
		System.out.println("消息入消息池");
		messagePool.add(message);
		System.out.println("消息池大小为："+messagePool.size());
		this.notifyAll();
	}
	
}
