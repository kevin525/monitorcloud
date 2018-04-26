package com.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;

import sun.misc.BASE64Encoder;

public class TomcatHTMLUtil {

	private static String managerUrl = "http://10.171.251.74:80/manager";
	
	public static String message(String operateURL,String userName,String password) {
        StringBuffer dataResult = new StringBuffer();
        InputStream is = null;
        try {
        	URL url = new URL(operateURL);

            URLConnection conn = (URLConnection) url.openConnection();
            String configuration = userName+":"+password; // manager角色的用户
            String encodedPassword = new BASE64Encoder().encode(configuration.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encodedPassword);
            conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
            is = conn.getInputStream();
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = bufreader.readLine()) != null) {
                dataResult.append(line);
            }
            if(is != null){
               is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataResult.toString();
    }
	
	public static ArrayList<WebApp> getTomcatWebAppData(String userName,String password,int version){

        ArrayList<WebApp> webAppArrayList = new ArrayList<WebApp>();
        String data = null;
        if(version <= 6){
        	data = TomcatHTMLUtil.message(managerUrl+"/list",userName,password);
        }else {
        	data = TomcatHTMLUtil.message(managerUrl+"/text/list",userName,password);
        }

        String[] oldDataStrs = data.split("/");

        if(oldDataStrs[0].startsWith("OK")){
            for (int i = 0; i < oldDataStrs.length; i++) {
                String name = oldDataStrs[i].split(":")[0];
                if(name.startsWith("tzoa")){
                    WebApp webApp = new WebApp();
                    webApp.setName(name);
                    if(oldDataStrs[i].split(":")[1].equals("running")){
                        if(oldDataStrs[i].split(":")[2].equals("0")){
                            webApp.setStatus("运行");
                        }
                        else{
                            webApp.setStatus("异常");
                        }
                    }
                    else if(oldDataStrs[i].split(":")[1].equals("stopped")){
                        if(oldDataStrs[i].split(":")[2].equals("0")){
                            webApp.setStatus("停止");
                        }
                        else{
                            webApp.setStatus("异常");
                        }
                    }
                    else{
                        webApp.setStatus("异常");
                    }
                    webAppArrayList.add(webApp);
                }
            }
        }
        return webAppArrayList;
    }

/**
     * 重新部署一个项目
     * @param webAppName
     * @return
     */
    public static boolean reloadWebApp(String webAppName,String userName,String password,int version){
    	String data = null;
        if(version <=6){
        	 data = TomcatHTMLUtil.message(managerUrl+"/reload?path=/"+webAppName,userName,password);
        }else{
        	 data = TomcatHTMLUtil.message(managerUrl+"/text/reload?path=/"+webAppName,userName,password);
        }
        if(data.startsWith("OK")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 停止一个项目
     * @param webAppName
     * @return
     */
    public static boolean stopWebApp(String webAppName,String userName,String password,int version){
    	String data = null;
        if(version <= 6){
        	data = TomcatHTMLUtil.message(managerUrl+"/stop?path=/"+webAppName,userName,password);
        }else{
        	data = TomcatHTMLUtil.message(managerUrl+"/text/stop?path=/"+webAppName,userName,password);
        }
        if(data.startsWith("OK")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 开始一个项目
     * @param webAppName
     * @return
     */
    public static boolean startWebApp(String webAppName,String userName,String password,int version){
    	String data = null;
        if(version <= 6){
        	data = TomcatHTMLUtil.message(managerUrl+"/start?path=/"+webAppName,userName,password);
        }else {
        	data = TomcatHTMLUtil.message(managerUrl+"/text/start?path=/"+webAppName,userName,password);
        }
        if(data.startsWith("OK")){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean startWebApp(String url,String webAppName,String userName,String password,int version) throws Exception{
    	String data = null;
        if(version <= 6){
        	data = TomcatHTMLUtil.message(url+"/start?path=/"+webAppName,userName,password);
        }else {
        	data = TomcatHTMLUtil.message(url+"/text/start?path=/"+webAppName,userName,password);
        }
        if(data.startsWith("OK")){
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args){
    	System.out.println(	startWebApp("tzoa","admin","123456",7));
    	//stopWebApp("tzoa","admin","123456", 7);
    	//getTomcatWebAppData("admin","123456",6);
    }

}

class WebApp {

	//项目名称
    private String name;

    //运行状态 "成功" or "停止"
    private String status;

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
