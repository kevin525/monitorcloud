package tzoawhweb;

import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.rowset.serial.SerialClob;

import tzoawhweb.util.RandomUtil;

import com.sys.util.DBUtil;

public class TestInsertAskLeaveData {

	public static void main(String[] args){
	   String urlFrom = "localhost:3306/staff";
	   String userFrom = "root";
	   String passwordFrom = "123456";
       String sql = "insert into ask_leave(empId,leaveReason,beginDate,endDate,submitDate,auditId,status) values(?,?,?,?,?,?,?)";
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try{
		 
		 conn = DBUtil.getConnByMysql(urlFrom, userFrom, passwordFrom);
		 for(int i=0;i<200000;i++){
			 Format f = new SimpleDateFormat("yyyy-MM-dd");
			 pstmt = conn.prepareStatement(sql);
			 Date beginDate = RandomUtil.getRandomDate("2000-01-01", "2018-01-01");
			 System.out.println(beginDate);
			 Calendar c = Calendar.getInstance();
		     c.setTime(beginDate);
		     c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
		     System.out.println("明天是:" + f.format(c.getTime()));
		     Calendar c1 = Calendar.getInstance();
		     c1.setTime(beginDate);
		     c1.add(Calendar.DAY_OF_MONTH, 2);// 今天+1天
		     System.out.println("明天是:" + f.format(c1.getTime()));
		     String s="由于家中有事原因，需要请假处理，望批准！";
		     SerialClob clob=new javax.sql.rowset.serial.SerialClob(s.toCharArray());
			 pstmt.setInt(1, RandomUtil.getEmpId());
		     pstmt.setClob(2,  clob);
		     pstmt.setDate(3, new java.sql.Date(beginDate.getTime()));
		     pstmt.setDate(4, new java.sql.Date(c.getTime().getTime()));
		     pstmt.setDate(5, new java.sql.Date(c1.getTime().getTime()));
		     pstmt.setInt(6, RandomUtil.getDeptId());
		     pstmt.setInt(7, RandomUtil.getLevel());
		     pstmt.executeUpdate();
		     System.out.println("============第"+(2+i)+"行==============");
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

