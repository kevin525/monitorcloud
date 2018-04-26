package tzoawhweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.junit.Test;

import tzoawhweb.util.RandomUtil;

import com.sys.util.DBUtil;

public class TestInsertEmpData {

	public static void main(String[] args){
	   String urlFrom = "localhost:3306/staff";
	   String userFrom = "root";
	   String passwordFrom = "123456";
       String sql = "insert into employee(userName,birthDate,idCard,loginName,password,mobile,email,deptId,level) values(?,?,?,?,?,?,?,?,?)";
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try{
		 conn = DBUtil.getConnByMysql(urlFrom, userFrom, passwordFrom);
		 
		 for(int i=0;i<1000000;i++){
			 pstmt = conn.prepareStatement(sql);
			 String[] names = RandomUtil.getUserName();
			 pstmt.setString(1, names[0]);
		     pstmt.setDate(2,  new java.sql.Date(RandomUtil.getRandomDate("1970-01-01", "2018-01-01").getTime()));
		     pstmt.setString(3, RandomUtil.getIdCard());
		     pstmt.setString(4, names[1]);
		     pstmt.setString(5, "123456");
		     pstmt.setString(6, RandomUtil.getTel());
		     pstmt.setString(7, RandomUtil.getEmail());
		     pstmt.setInt(8, RandomUtil.getDeptId());
		     pstmt.setInt(9, RandomUtil.getLevel());
		     pstmt.executeUpdate();
		     System.out.println("============第"+(945039+i)+"行==============");
		 }
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   DBUtil.close(rs);
		   DBUtil.close(pstmt);
		   DBUtil.close(conn);
	   }
	   
	   
	}
}
