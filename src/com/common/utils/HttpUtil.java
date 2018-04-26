package com.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.apps.msgRemind.sms.domain.AsopSms;
import com.common.constants.Constant;
import com.sys.util.Config;

/**
 * @ClassName: HttpUtil 
 * @Description: TODO 
 * @author 提供HttpClient调用方法，支持get和post提交 
 * @date 2017年3月3日 下午1:24:50
 */
public class HttpUtil {
	
	public static String post(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		
		HttpPost post = postForm(url, params);
		body = invoke(httpclient, post);
		
		httpclient.getConnectionManager().shutdown();
		
		return body;
	}
	
	public static String post(String url, Map<String, String> params,String cookie) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		HttpPost post = postForm(url, params);
		post.setHeader("cookie", cookie);
		body = invoke2(httpclient, post);
		
		httpclient.getConnectionManager().shutdown();
		
		return body;
	} 
	public static String post(String url, Map<String, String> params,String cookie,HttpContext localContext) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost post = postForm(url, params);
		post.setHeader("cookie", cookie);
		HttpResponse response = null;
		
			try {
				response = httpclient.execute(post,localContext);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String location = response.getFirstHeader("Location")
		          .getValue();
		
		httpclient.getConnectionManager().shutdown();
		
		return location;
	}
	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		
		//log.info("create httppost:" + url);
		HttpGet get = new HttpGet(url);
		body = invoke(httpclient, get);
		
		httpclient.getConnectionManager().shutdown();
		
		return body;
	}
	public static String httpGet(String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
            HttpGet httpget = new HttpGet(url);
            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, "utf-8") : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            return httpclient.execute(httpget, responseHandler);
           
        } catch (Exception e) {
			e.printStackTrace();
		}finally {
            try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return null;
	}
	
	private static String invoke(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {
		
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		
		return body;
	}
	private static String invoke2(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {
		
		HttpResponse response = sendRequest(httpclient, httpost);
		if(null==response.getFirstHeader("Location")){
			return response.getStatusLine().toString();
		}else{
		String location = response.getFirstHeader("Location")
		          .getValue();
		return location;
		}
	}
	private static String paseResponse(HttpResponse response) {
		//log.info("get response from http server..");
		HttpEntity entity = response.getEntity();
		
		String charset = EntityUtils.getContentCharSet(entity);
		//log.info(charset);
		charset = Utils.isEmpty(charset) ? "utf-8" : charset;
		String body = null;
		try {
			body = EntityUtils.toString(entity, charset);
			//log.info(body);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {
		//log.info("execute post...");
		HttpResponse response = null;
		
		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params){
		
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		Set<String> keySet = params.keySet();
		for(String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return httpost;
	}
	
	/**
	 * @Title: sendSmsByUnicom
	 * @Description: 通过联通发送短信，如果给多个手机号码发送短信，用英文分号隔开
	 * @param content
	 * @param url
	 * @param phoneNum
	 * @return void
	 * @throws
	 */
	public static void sendSmsByUnicom(String content,String url,String phoneNum){
		String flag = Config.getAttribute("isSendMsg");
		if("true".equals(flag)){
			if(phoneNum.endsWith(",")){
				phoneNum = phoneNum.substring(0,phoneNum.lastIndexOf(","));
			}
		    if(phoneNum.contains(",")){
		    	String[] phoneNums = phoneNum.split(",");
		    	 for(String num:phoneNums){
		 	    	Map<String,String> params = new HashMap<String,String>();
		 		    params.put("recvLoginName", num);
		 		    params.put("sendLoginName", Config.getAttribute("sendLoginName"));
		 		    params.put("content", content);
		 		    HttpUtil.post(url, params);
		 	    }
		    }else{
		    	Map<String,String> params = new HashMap<String,String>();
	 		    params.put("recvLoginName", phoneNum);
	 		    params.put("sendLoginName", Config.getAttribute("sendLoginName"));
	 		    params.put("content", content);
	 		    HttpUtil.post(url, params);
		    }
		}
	}
	
	
	
	/**
	 * @Title: testUrlConnect
	 * @Description: 测试访问地址的是否有效
	 * @param urlStr
	 * @return
	 * @return boolean
	 * @throws
	 */
	public static boolean testUrlConnect(String url) throws Exception{
		boolean result = false;
		if(url == null || url.length() <= 0){
			return false;
		}
		HttpURLConnection conn = null;
		for (int i = 0; i < 5; i++) {
			URL connUrl = new URL(url);
			conn = (HttpURLConnection) connUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			int state = conn.getResponseCode();
			if (state == 200 || state == 304) {
				result = true;
				break;
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result;	
	}
}

