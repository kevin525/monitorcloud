package com.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtil {
	
	/**
	 * 下载文件（默认为保存）
	 * @param response
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletResponse response,String filePath,String fileName) throws Exception{
		File file=new File(filePath);
		if(file.exists()&&file.isFile()){
			fileName=fileName.replace(" ", "");
			String sname=URLEncoder.encode(fileName, "UTF-8");
			
			response.setHeader("Content-disposition", "attachment; filename="+ sname);
			String CONTENT_TYPE = "APPLICATION/OCTET-STREAM";
			response.setContentType(CONTENT_TYPE);
			
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));//BufferedInputStream读取效率更快
 
			int num=0;
			byte buffer[] = new byte[1024 * 1024];//缓存1M
			while ((num = bis.read(buffer)) != -1) {
					out.write(buffer,0,num);//使用这种写入方法以免文件发生损坏
			}

			bis.close();
			out.flush();
			out.close();
		}
	}

}
