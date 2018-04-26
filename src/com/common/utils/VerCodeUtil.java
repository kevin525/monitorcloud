package com.common.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
/**
 * @ClassName: VerCodeUtil
 * @Description: 验证码识别，参考
 * http://blog.csdn.net/problc/article/details/5794460
 * http://blog.csdn.net/problc/article/details/5797507
 * http://blog.csdn.net/problc/article/details/5800093
 * http://blog.csdn.net/problc/article/details/5846614
 * 
 * @author LG
 * @date 2017年10月27日 下午2:29:48
 */
public class VerCodeUtil {

	private static Map<BufferedImage, String> trainMap = null;
	private static int index = 0;
	//验证码地址
	public static String getImageUrl = "http://10.171.251.64:8080/manageweb/RandomImage";
	public static String srcPath = "images/code/source/";
	public static String trainPath = "images/code/train/";
	public static String tempPath = "images/code/temp/";
	public static String cookie = null;
	public static int isBlue(int colorInt) {  
        Color color = new Color(colorInt);  
        int rgb = color.getRed() + color.getGreen() + color.getBlue();
        if (rgb == 153) {  
            return 1;  
        }  
        return 0;  
    }  
	
	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
			return 1;
		}
		return 0;
	}

	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 600) {
			return 1;
		}
		return 0;
	}
	
	public static int isYellow(int colorInt) {  
        Color color = new Color(colorInt);  
        int rgb = color.getRed() + color.getGreen() + color.getBlue();
        if (rgb >= 500 && rgb <= 720) {  
            return 1;  
        }  
        return 0;  
    }
	/**
	 * 去除背景，二值化
	 * @param picFile
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage removeBackgroud1(String picFile)
			throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile)); 
		System.out.println(img.getWidth()+","+img.getHeight());
        img = img.getSubimage(5, 1, img.getWidth()-11, img.getHeight()-2); 
        img = img.getSubimage(0, 0, 50, img.getHeight());
        int width = img.getWidth();  
        int height = img.getHeight();  
        for(int x=0; x<width; x++){
        	for(int y=0; y<height; y++){
        		img.setRGB(x, y, Color.BLACK.getRGB());
        		if(isBlue(img.getRGB(x, y)) == 1){
        			img.setRGB(x, y, Color.BLACK.getRGB());
        		}else{
        			img.setRGB(x, y, Color.WHITE.getRGB());
        		}
        	}
        }
        return img;  
	}
	
	public static BufferedImage removeBackgroud(String picFile)  
            throws Exception {  
        BufferedImage img = ImageIO.read(new File(picFile)); 
        img = img.getSubimage(13, 0, img.getWidth()-13, img.getHeight());
        int width = img.getWidth();
		int height = img.getHeight();
        for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (isYellow(img.getRGB(x, y)) == 1) {
					img.setRGB(x, y, Color.BLACK.getRGB());
				} else {
					img.setRGB(x, y, Color.WHITE.getRGB());
				}
			}
		}
        VerCodeUtil util = new VerCodeUtil();
		String rootUrl = util.getCodeUrl()+srcPath+"test.png";
        ImageIO.write(img, "PNG", new File(rootUrl));
        return img;  
   }

	/**
	 * 按自己的规则分割验证码
	 * @param img
	 * @return
	 * @throws Exception
	 */
	public static List<BufferedImage> splitImage(BufferedImage img)
			throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		int width = img.getWidth()/4;
		int height = img.getHeight();
		subImgs.add(img.getSubimage(0, 0, width, height));
		subImgs.add(img.getSubimage(width, 0, width, height));
		subImgs.add(img.getSubimage(width*2, 0, width, height));
		subImgs.add(img.getSubimage(width*3, 0, width, height));
		return subImgs;
	}
	/**
	 * 载入训练好的字摸
	 * @return
	 * @throws Exception
	 */
	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		if (trainMap == null) {
			VerCodeUtil util = new VerCodeUtil();
			String rootUrl = util.getCodeUrl();
			Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
			File dir = new File(rootUrl+trainPath);
			File[] files = dir.listFiles();
			for (File file : files) {
				map.put(ImageIO.read(file), file.getName().charAt(0) + "");
			}
			trainMap = map;
		}
		return trainMap;
	}
	/**
	 * 识别分割的单个字符
	 * @param img
	 * @param map
	 * @return
	 */
	public static String getSingleCharOcr(BufferedImage img,
			Map<BufferedImage, String> map) {
		String result = "#";
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		for (BufferedImage bi : map.keySet()) {
			int count = 0;
			if (Math.abs(bi.getWidth()-width) > 2)
				continue;
			int widthmin = width < bi.getWidth() ? width : bi.getWidth();
			int heightmin = height < bi.getHeight() ? height : bi.getHeight();
			Label1: for (int x = 0; x < widthmin; ++x) {
				for (int y = 0; y < heightmin; ++y) {
					//if (isYellow(img.getRGB(x, y)) != isYellow(bi.getRGB(x, y))) {
					if (img.getRGB(x, y) != bi.getRGB(x, y)) {
						count++;
						if (count >= min)
							break Label1;
					}
				}
			}
			if (count < min) {
				min = count;
				result = map.get(bi);
			}
		}
			return result;
	}
	/**
	 * 验证码识别
	 * @param file  要验证的验证码本地路径
	 * @return
	 * @throws Exception
	 */
	public static String getAllOcr(String file) throws Exception {
		BufferedImage img = removeBackgroud(file);
		List<BufferedImage> listImg = splitImage(img);
		Map<BufferedImage, String> map = loadTrainData();
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
		return result;
	}
	
	/**
	 * 下载验证码
	 * @param url
	 */
	public static void downloadImage(String url,int count) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		for(int i=0; i<count; i++){
			HttpGet getMethod = new HttpGet(url);
			HttpResponse response = null;
			try {
				response = httpClient.execute(getMethod);
				if("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())){
					HttpEntity entity = response.getEntity();
					VerCodeUtil util = new VerCodeUtil();
					String rootUrl = util.getCodeUrl()+srcPath+i+".png";
					File file = new File(rootUrl);
					if(file.exists()){
						file.delete();
					}
					InputStream is = entity.getContent();
					OutputStream os = new FileOutputStream(file);
					int length = -1;
					byte[] bytes = new byte[1024];
					while((length = is.read(bytes)) != -1){
						os.write(bytes, 0, length);
					}
					os.close();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Title: downloadImage
	 * @Description: 下载一张验证码图片
	 * @param url
	 * @param count
	 * @return void
	 * @throws
	 */
	public static String downloadSingleImage(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet getMethod = new HttpGet(url);
			HttpResponse response = null;
			VerCodeUtil util = new VerCodeUtil();
			String rootUrl = util.getCodeUrl()+srcPath+"yzm.png";
			try {
				response = httpClient.execute(getMethod);
				 cookie=response.getFirstHeader("Set-Cookie").getValue();
				if("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())){
					HttpEntity entity = response.getEntity();
					
					File file = new File(rootUrl);
					if(file.exists()){
						file.delete();
					}
					InputStream is = entity.getContent();
					OutputStream os = new FileOutputStream(file);
					int length = -1;
					byte[] bytes = new byte[1024];
					while((length = is.read(bytes)) != -1){
						os.write(bytes, 0, length);
					}
					os.close();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return rootUrl;
	}

	/**
	 * 机器训练
	 * @throws Exception
	 */
	public static void trainData() throws Exception {
		VerCodeUtil util = new VerCodeUtil();
		String rootUrl = util.getCodeUrl();
		
		File dir = new File(rootUrl+tempPath);
		File[] files = dir.listFiles();
		for (File file : files) {
			BufferedImage img = removeBackgroud(rootUrl+tempPath+ file.getName());
			List<BufferedImage> listImg = splitImage(img);
			if (listImg.size() == 4) {
				
				for (int j = 0; j < listImg.size(); ++j) {
					System.out.println(file.getName());
					System.out.println(file.getName().charAt(j));
					ImageIO.write(listImg.get(j), "PNG", new File(rootUrl+trainPath+
							+ file.getName().charAt(j) + "-" + (index++)
							+ ".png"));
				}
			}
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		downloadImage(getImageUrl,1);
		trainData();
		//VerCodeUtil util = new VerCodeUtil();
		//util.getCodeUrl();
		for (int i = 0; i < 100; ++i) {
			String text = getAllOcr("img\\" + i + ".png");
			System.out.println(i + ".png = " + text);
		}
	}
	
	private String getCodeUrl(){
		String classpath=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
		//String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
		String url = classpath.substring(0,classpath.indexOf("tzoawhweb")+10);
	    System.out.println(url);
		return url;
	}
	/*private String getCodeUrl(){
		return "f:/";
	}*/
}
