package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OnlineUserList {
	
	static private HashMap<String,OnlineUserInfo> userList = new HashMap<String,OnlineUserInfo>();
	public static  List<String> list= new ArrayList<String>();
	//记录portal过来的登录信息
	static public void loginRegist(String uid, String token, String loginTime, String loginName, String logId){
		OnlineUserInfo ui = new OnlineUserInfo();
		ui.setUid(uid);
		ui.setToken(token);
		ui.setLoginName(loginName);
		ui.setLoginTime(loginTime);
		if(logId!=null){
			ui.setLogId(logId);
		}
		synchronized (userList) {
			System.out.println("token:"+token);
			userList.put(token, ui);
		}
	}

	//查询portal过来的登录信息
	static public OnlineUserInfo getLoginInfo(String token){
		OnlineUserInfo ui = userList.get(token);
		return ui;
	}

	//删除portal过来的登录信息
	static public void logoutRegist(String token){
		synchronized (userList) {
			userList.remove(token);
		}
	}
	
	//删除所有登录信息
	static public void logoutALLRegist(){
		synchronized (userList) {
			if(userList!=null && userList.size()>0){
				for(int i=0;i<userList.size();i++){
					userList.remove(i);
				}
			}
			
		}
	}
	
	//查询在线人员
	static public HashMap<String,OnlineUserInfo> onLinePersons(){
		return userList;
	}
	
	//查询在线token值
	@SuppressWarnings("rawtypes")
	static public String getOnLineUserToken(long appLogId){
		String token="";
		Iterator iter = userList.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			OnlineUserInfo val = (OnlineUserInfo)entry.getValue();
			if(val!=null &&  String.valueOf(appLogId).equals(val.getLogId())){
				token=val.getToken();
			}
		}
		return token;
	}
	
	
}
