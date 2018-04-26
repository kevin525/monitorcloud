package tzoawhweb;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.sys.util.DBUtil;

public class TestClient {

	@Test
	public void test(){
	   String urlFrom = "10.171.251.13:3306/freeflow";
	   String userFrom = "freeflow";
	   String passwordFrom = "freeflow_25113";
       String sql = "select * from a_content_attr_part where id='1918069'";		 
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String id = "";
	   String fileName = "";
	   Blob inkImg = null;
	   try{
		 conn = DBUtil.getConnByMysql(urlFrom, userFrom, passwordFrom);
		 pstmt = conn.prepareStatement(sql);
		 rs = pstmt.executeQuery();
		 while(rs.next()){
		    id = rs.getString(1);
			fileName = rs.getString(2);
			inkImg = rs.getBlob(4);
			System.out.println(id+"---"+fileName+"---"+inkImg);
		 }
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   DBUtil.close(rs);
		   DBUtil.close(pstmt);
		   DBUtil.close(conn);
	   }
	   
	   Connection conn1 = null;
	   PreparedStatement pstmt1 = null;
	   try{
		   String urlTo = "10.171.251.69:3306/freeflow_ceshi3";
		   String userTo = "root";
		   String passwordTo = "password";
		   String sql1 = "update a_content_attr_part set content_type = ?,content=?  where id='586675'";		
		   conn1 = DBUtil.getConnByMysql(urlTo, userTo, passwordTo);
		   pstmt1 = conn1.prepareStatement(sql1);
		   
		   pstmt1.setString(1, "测试");
		   pstmt1.setBlob(2, inkImg);
		   pstmt1.executeUpdate(); 
		   System.out.println(conn1);
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   DBUtil.close(pstmt1);
		   DBUtil.close(conn1);
	   }
	}
}
