package com.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;

/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2015年3月13日上午10:09:03
 *类说明：对token值得处理
 */
public class EncryptionToken {
	
	/**
	 * 构造token值并进行MD5加密
	 * @param uid
	 * @return
	 */
	static public String generateToken(String uid){
		try {
			Random rand = new Random();
			int num = rand.nextInt(100000);
			String src = uid + "-" + num + "-" + System.currentTimeMillis();
			String token=getMD5(src);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return "tokenError";
		}
	}
	
	/**
	 * 构造token值并进行Base64加密
	 * @param uid
	 * @return
	 */
	static public String generateBase64Token(String uid){
		try {
			Random rand = new Random();
			int num = rand.nextInt(10000);
			String src = uid + "-" + num + "-" + System.currentTimeMillis();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(src.getBytes());
			byte[] theDigest = md.digest();
			BASE64Encoder encoder = new BASE64Encoder();
			String token = encoder.encode(theDigest);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getMD5(String str){
		if(str==null) return null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(str.getBytes());  //使用指定的 byte 数组更新摘要
		byte[] b = md.digest(); 	//通过执行诸如填充之类的最终操作完成哈希计算

		str = byte2hex(b);     		//把字节数组转变为16进制的字符串
		return str;
	}
	
	
	//把字节数组转变为16进制的字符串
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0Xff));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs;
	}
}
