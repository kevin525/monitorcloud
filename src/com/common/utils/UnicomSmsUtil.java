package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.sys.util.Config;

public class UnicomSmsUtil {
	

	/**
	 * 通过post方式发送短信
	 * @param message
	 * @param mobile
	 * @param ext
	 * @return
	 */
	public static String sendSmsByPost(String message, String mobile, String ext){
		System.out.println("mobile="+mobile+"---message="+message);
		if(Utils.isEmpty(mobile) || Utils.isEmpty(message)) return "-4";
		//多个手机号用，分隔
		//mobile = mobile.replaceAll(";", ",");
		mobile=getSendMobiles(mobile);
		message = Utils.convertStrCode("utf-8", "gbk", message);
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", Config.getAttribute("unicomUserId"));
		params.put("username", Config.getAttribute("unicomUserName"));
		params.put("passwordMd5", getMd5(Config.getAttribute("unicomPassword"),"0"));
		params.put("mobile", mobile);
		params.put("message", message);
		System.out.println("params="+params);
		String url = Config.getAttribute("unicomURL") + ":" + Config.getAttribute("unicomPort") + "/sendsms.php";
		System.out.println("url="+url);
		return HttpUtil.post(url , params);
	}
	
	
	/**
	 * 通过get方式发送短信
	 * @param message
	 * @param mobile
	 * @param ext
	 * @return
	 */
	public static String sendSmsByGet(String message, String mobile, String ext){
		if(Utils.isEmpty(mobile) || Utils.isEmpty(message)) return "-4";
		//多个手机号用，分隔
		mobile=getSendMobiles(mobile);
		try {
			message = URLEncoder.encode(message, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String passwordMd5 = getMd5(Config.getAttribute("unicomPassword"),"0");
		String url = Config.getAttribute("unicomURL") + ":" + Config.getAttribute("unicomPort") + "/sendsms.php?userid="+Config.getAttribute("unicomUserId")+"&username=" + Config.getAttribute("unicomUserName") + "&passwordMd5=" + passwordMd5 + "&mobile=" + mobile + "&message=" + message;
		return HttpUtil.get(url);
	}
	
	public static String sendSmsByGetTest(String message, String mobile, String ext){
		if(Utils.isEmpty(mobile) || Utils.isEmpty(message)) return "-4";
		//多个手机号用，分隔
		//mobile=getSendMobiles(mobile);
		try {
			message = URLEncoder.encode(message, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String passwordMd5 = getMd5("0C4G%g;h","0");
		String url = "http://42.96.248.183" + ":" + "8080" + "/sendsms.php?userid="+100542+"&username=" +"TZXXZX" + "&passwordMd5=" + passwordMd5 + "&mobile=" + mobile + "&message=" + message;
		return HttpUtil.get(url);
	}
	
	/**
	 * 获取状态报告
	 * @return
	 */
	public static String getStateXML(){
		String url = Config.getAttribute("unicomURL") + ":" + Config.getAttribute("unicomPort") + "/getStateXml.asp";	
		url += "?username=" + Config.getAttribute("unicomUserName") + "&password=" + Config.getAttribute("unicomPassword");
		return HttpUtil.get(url);
	}
	
	/**
	 * 获取回复信息
	 * @return
	 */
	public static String getRecSmsXml(){
		String url = Config.getAttribute("unicomURL") + ":" + Config.getAttribute("unicomReturnPort") + "/getRecSmsXml.asp";	
		url += "?username=" + Config.getAttribute("unicomUserName") + "&password=" + Config.getAttribute("unicomPassword");
		return HttpUtil.get(url);
	}
	/*
	 * 处理短信手机号
	 */
	private static String getSendMobiles(String mobile){
		//mobile原格式:资源部-樊家耀(13693671217);资源部-陈国忠(13683127666)
		if(mobile==null){
			return "";
		}
		StringBuffer sb=new StringBuffer();
		String[] mm=mobile.split("[;]");
		for(int i=0;i<mm.length;i++){
			if(mm[i].contains("(")){
				sb.append(mm[i].substring(mm[i].indexOf("(")+1,mm[i].lastIndexOf(")"))+",");
			}else{
				sb.append(mm[i]+",");
			}
		}
		return sb.substring(0,sb.length()-1);
	}
	
	/**
	 * md5加密，type等于0输出16，否则输出32位，都想小写
	 * @param plainText
	 * @return
	 */
	public static String getMd5(String plainText,String type) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes());  
            byte b[] = md.digest();  
            int i;  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            if("0".equals(type)){
            	//16位的加密
            	return buf.toString().substring(8, 24); 
            }else{
            	//32位加密 
            	return buf.toString(); 
            }
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
}
