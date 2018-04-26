package com.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import jxl.Workbook;
import jxl.write.Blank;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Decoder;

public class Utils {
	public static final String PATTERN_FULL_NUMBER = "^\\d+$";
	private static Properties properties = new Properties();
	private static Map<String, ArrayList<String>> localDefineMap = new HashMap<String, ArrayList<String>>(); 
	public static final String DEFAULT_PROPERTY_FILE = "config.properties";
	public static Map<String, Map<String, String>> statisticCacheMap = new HashMap<String, Map<String,String>>();
	public static final Map<String, String> snapshotAuthSessionMap = new HashMap<String, String>();
	public static String getStrValue(Object obj){
		if(obj==null){
			return null;
		}
		return obj.toString();
	}
	
	public static boolean isEmpty(String str){
		return str == null || str.trim().length() <= 0;
	}
	/**
	 * 将String类型转换成int型

	 * @param String sourceString
	 * @return int 转换后的int型

	 */
	public static int toNumber(String sourceString) {
		return toNumber(sourceString, 0);
	}

	/**
	 * @reload
	 * 将String类型转换成int型数字

	 * @param String sourceString
	 * @param int defaultValue
	 * @return int 转换后的int型

	 */
	public static int toNumber(String sourceString, int defaultValue) {
		return isDigitalNumber(sourceString)
				&& Long.parseLong(sourceString) <= Integer.MAX_VALUE ? Integer
				.parseInt(sourceString) : defaultValue;
	}

	/**
	 * 将String类型转换成long型

	 * @param String sourceString
	 * @return long 转换后的long型

	 */
	public static long toLongNumber(String sourceString) {
		return toLongNumber(sourceString, 0);
	}

	/**
	 * @reload
	 * 将String类型转换成long型数字

	 * @param String sourceString
	 * @param long defaultValue
	 * @return long 转换后的long型

	 */
	public static long toLongNumber(String sourceString, long defaultValue) {
		return isDigitalNumber(sourceString) ? Long.parseLong(sourceString)
				: defaultValue;
	}
	/**
	 * 判断是否为数字

	 * @param String sourceString
	 * @return boolean
	 */
	public static boolean isDigitalNumber(String sourceString) {
		return sourceString != null
				&& sourceString.matches(PATTERN_FULL_NUMBER);
	}
	public static boolean isURL(String url) {
		return url.startsWith("http") && !url.startsWith("http://127.0.0.1") && !url.startsWith("http://localhost");
	}
	
	public static double formatDouble(double d,String pattern){
		  DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
		  
		  // format.applyPattern("0.00");
		   format.applyPattern(pattern);
		   String s = format.format(d);
		   d = Double.parseDouble(s);
		   return d;

	}
	public static boolean loadProperties1(String fileName){
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            return false;
        }
        return true;
    }
	public static boolean loadProperties(InputStream is){
        try {
            properties.load(is);
        } catch (IOException e) {
            return false;
        } finally {
        	try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return true;
    }
	public static String getProperty(String key){
        return properties.getProperty(key);
    }

	public static String getProperty(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }
	public static Properties getProperties(){
        return properties;
    }
	
	
	public static Map<String, ArrayList<String>> getLocalDefineMap() {
		return localDefineMap;
	}
	public static void setLocalDefineMap(Map<String, ArrayList<String>> defineMap) {
		localDefineMap = defineMap;
	}
	/**
	 * 替换字体类型中的空格
	 * @param str
	 * @return
	 */
	public static String replaceCharacterSpace(String contentHtml) {
		List<String[]> toReplaces=new ArrayList<String[]>();
    	
    	toReplaces.add(new String[]{"Comic&nbsp;Sans&nbsp;MS","Comic Sans MS"});
    	toReplaces.add(new String[]{"Courier&nbsp;New","Courier New"});
    	toReplaces.add(new String[]{"Times&nbsp;New&nbsp;Roman","Times New Roman"});
    	toReplaces.add(new String[]{"Arial&nbsp;Black","Arial Black"});
    	toReplaces.add(new String[]{"Lucida&nbsp;Sans&nbsp;Typewriter", "Lucida Sans Typewriter"});
    	toReplaces.add(new String[]{"Microsoft&nbsp;Sans&nbsp;Serif", "Microsoft Sans Serif"});
    	toReplaces.add(new String[]{"MS&nbsp;UI&nbsp;Gothic", "MS UI Gothic"});
    	toReplaces.add(new String[]{"Arial&nbsp;Unicode&nbsp;MS", "Arial Unicode MS"});
    	for(String[] cons:toReplaces){
    		String src=cons[0];
    		String tgt=cons[1];
    		contentHtml = contentHtml.replaceAll(src, tgt);
    	}
		return contentHtml;
	}
	

     public static boolean contain(Object[] partNames, Object dataType) {
 		if(partNames!=null){
 			for(Object ob:partNames){
 				if(ob.equals(dataType)){
 					return true;
 				}
 			}
 		}
 		return false;
 	}
     
     public static String splitAndFilterString(String input) {
 		if (input == null || input.trim().equals("")) {
 			return "";
 		}
 		// 去掉所有html元素,
 		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
 				"<[^>]*>", "");
 		str = str.replaceAll("[(/>)<]", "");
 		str = str.replaceAll("\\[pagebreak\\].*\\[pagebreak\\]", "");

 		return str;
 	}
     
     
     public static byte[] writeObj(Object obj) throws IOException
 	{
 	ByteArrayOutputStream bos = new ByteArrayOutputStream();
 	ObjectOutputStream oos = new ObjectOutputStream(bos);
 	oos.writeObject(obj);
 	bos.close();
 	oos.close();
 	return bos.toByteArray();
 	}

 	public static Object readObj(byte[] bos) throws IOException, ClassNotFoundException
 	{
 	ByteArrayInputStream bis = new ByteArrayInputStream(bos);
 	ObjectInputStream ois = new ObjectInputStream(bis);
 	bis.close();
 	ois.close();
 	return ois.readObject();
 	}
 	
 	
 	
 	
 	public static void main(String[] args) {
		/*String str1 = "aaa" ;
		String str2 = "中国" ;
		String str3 = "中a.gif" ;
		isContainsChinese(str1);
		isContainsChinese(str2);
		isContainsChinese(str3);
		String str = "“一站式”简报";
		str = HtmlUtils.htmlEscape(str);
		System.out.println(str);*/
 		String mobile = "13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;13546756656;13296153114;";
	}	
 	

	public static boolean isContainsChinese(String str){
		if(str.length() < str.getBytes().length){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getBASE64(byte[] bytes) { 
		if (bytes == null) return null; 
		return (new sun.misc.BASE64Encoder()).encode(bytes); 
	} 

	// 将 BASE64 编码的字符串 s 进行解码 
	public static String getFromBASE64(String s) { 
		if (s == null) return null; 
		BASE64Decoder decoder = new BASE64Decoder(); 
		try { 
			byte[] b = decoder.decodeBuffer(s); 
			return new String(b); 
		} catch (Exception e) { 
			return null; 
		} 
	}
	public static String convertStrCode(String sourceCode, String destCode, String str){
		try {
        	byte[] temp = str.getBytes(sourceCode);//这里写原编码方式
            byte[] newtemp = new String(temp,sourceCode).getBytes(destCode);//这里写转换后的编码方式
            return new String(newtemp,destCode);//这里写转换后的编码方式
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		return str;
	}
}
