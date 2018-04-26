package tzoawhweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.junit.Test;

import tzoawhweb.util.RandomUtil;

import com.sys.util.DBUtil;

public class TestInsertPayrollData {

	public static void main(String[] args){
	   String urlFrom = "localhost:3306/staff2";
	   String userFrom = "root";
	   String passwordFrom = "123456";
       String sql = "insert into payroll(empId,baseSalary,actualSalary,bonus,deductMoney,grantDate) values(?,?,?,?,?,?)";
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try{
		   
		 conn = DBUtil.getConnByMysql(urlFrom, userFrom, passwordFrom);
		 for(int i=0;i<500000;i++){
			 pstmt = conn.prepareStatement(sql);
			 int baseSalary = RandomUtil.getDoubleData(10);
			 int bonus = RandomUtil.getDoubleData(2);
			 int deductMoney = RandomUtil.getDoubleData(1);
			 int actualSalary = baseSalary+bonus-deductMoney;
			 pstmt.setInt(1, RandomUtil.getEmpId());
		     pstmt.setDouble(2,  baseSalary);
		     pstmt.setDouble(3, actualSalary);
		     pstmt.setDouble(4, bonus);
		     pstmt.setDouble(5, deductMoney);
		     pstmt.setString(6, RandomUtil.getGrantDate());
		     pstmt.executeUpdate();
		     System.out.println("============第"+(1+i)+"行==============");
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
