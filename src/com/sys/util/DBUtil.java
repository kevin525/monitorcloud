package com.sys.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.common.StringHelpers;
import com.common.constants.Constant;

/***
 * 建立与数据库的连接111111111
 * 
 * @author xiaoliang
 * 
 */
public class DBUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//String url = "jdbc:mysql://localhost/visitingcardmanagesystem";
			//String user = "root";
			//String password = "123456";
			//conn = (Connection) DriverManager.getConnection(url, user, password);
			 conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/visitingcardmanagesystem?user=root&password=&useUnicode=true&characterEncoding=GBK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * @Title: getConnByMysql 
	 * @Description: 创建mysql数据库连接
	 * @param      
	 * @return Connection 
	 * @throws
	 */
	public static Connection getConnByMysql(String url,String user,String password){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://"+url+"?characterEncoding=utf-8";
			conn = (Connection) DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * @Title: getConnByOracle 
	 * @Description: 创建oracle数据库连接 
	 * @param      
	 * @return Connection 
	 * @throws
	 */
	public static Connection getConnByOracle(String url,String user,String password){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * @Title: testDataBaseConn
	 * @Description: 测试数据库的连通性
	 * @param url
	 * @param user
	 * @param password
	 * @param dataBaseType
	 * @return
	 * @return boolean
	 * @throws
	 */
	public static boolean testDataBaseConn(String url,String user,String password,String dataBaseType,String dataBaseIns,String dataBaseName) throws Exception{
		Connection conn = null;
		if(StringHelpers.isNull(url) || StringHelpers.isNull(user)){
			return false;
		}
		if(Constant.DATA_BASE_MYSQL.equals(dataBaseType)){
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://"+url+"/"+dataBaseName+"?characterEncoding=utf-8";
		}else if(Constant.DATA_BASE_ORACLE.equals(dataBaseType)){
			if(dataBaseIns==null || "".equals(dataBaseIns)){
				return false;
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@"+url+":1521:"+dataBaseIns;
		}else{
			return true;
		}
		conn = (Connection) DriverManager.getConnection(url, user, password);
		if(conn == null){
			return false;
		}else{
			close(conn);
		}
			
		return true;
	}
	
	// 若想被其他类调用此方法，必须把该方法写成静态的、公共的方法；
	public static void close(java.sql.Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Statement属于PreparedStatement的基类方法，调用它关闭就可以关掉PreparedStatement
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Title: getObjectByRef 
	 * @Description: 通过java反射机制获取对象，支持String/Integer/Double/Date/Boolean类型的字段
	 * @param      
	 * @return Object 
	 * @throws
	 */
	public static Object getObjectByRef(Class clazz,ResultSet rs){  
         Object obj = null;
         try{
        	 Field[] fields =clazz.getDeclaredFields();
        	 obj = clazz.newInstance();
             for(Field field : fields){  
                 String setname = "set"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
                 Method method = clazz.getMethod(setname, field.getType());
                 Class rsclazz = ResultSet.class;
                 String rsmname = "get"+field.getType().getSimpleName();
                 Method rsme = null;
                 if(field.getGenericType().toString().equals("class java.lang.String")){
                	 rsme = rsclazz.getMethod(rsmname, java.lang.String.class);
                 }else if(field.getGenericType().toString().equals("class java.lang.Integer")){
                	 rsme = rsclazz.getMethod(rsmname, java.lang.Integer.class);
                 }else if(field.getGenericType().toString().equals("class java.lang.Double")){
                	 rsme = rsclazz.getMethod(rsmname, java.lang.Double.class);
                 }else if(field.getGenericType().toString().equals("class java.lang.Boolean")){
                	 rsme = rsclazz.getMethod(rsmname, java.lang.Boolean.class);
                 }else if(field.getGenericType().toString().equals("class java.util.Date")){
                	 rsme = rsclazz.getMethod(rsmname, java.util.Date.class);
                 }
                 
                 method.invoke(obj, rsme.invoke(rs, field.getName()));
             } 
         }catch(Exception e){
        	 e.printStackTrace();
         }
         return obj;  
     } 
	
}
