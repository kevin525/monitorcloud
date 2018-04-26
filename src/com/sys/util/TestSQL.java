package com.sys.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestSQL {

	public static void testFileTranfer(){
		   StringBuffer sb = new StringBuffer();
		   sb.append("select * from filetransfer_recv_relation r inner join filetransfer f");
		   sb.append(" on r.filetransfer_id = f.oid where (r.recevieunitcode in ('3995') or r.privateaccount='3995') ");
		   sb.append(" and f.isvalid='0' and r.isdelete=0 and r.isvalid=0 and f.issend=1 ");
		   sb.append(" and (r.rejectflage !='1' or r.rejectflage is null )  order by f.senddate desc");
			 
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   
		   try{
			 String url = "jdbc:oracle:thin:@10.171.251.15:1521:tzoa";
			 String user = "swcc";
			 String password = "swcc";
			 conn = DBUtil.getConnByOracle(url, user, password);
			 pstmt = conn.prepareStatement(sb.toString());
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 System.out.println(rs.getString("oid"));
			 }
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally{
			   DBUtil.close(rs);
			   DBUtil.close(pstmt);
			   DBUtil.close(conn);
		   }
	}
	
	public static void main(String[] args){
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		final Calendar calendar = Calendar.getInstance(); 
		for(int i=0;i<50000;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("=========================="+Thread.currentThread().getName()+"线程开始："+df.format(new Date()));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(calendar.getTimeInMillis());
					testFileTranfer();
					System.out.println(calendar.getTimeInMillis());
					System.out.println("=========================="+Thread.currentThread().getName()+"线程结束："+df.format(new Date()));
				}
			}).start();
		}
		
	/*	for(int i=0;i<1000;i++){
			System.out.println(calendar.getTimeInMillis());
			testFileTranfer();
			System.out.println(calendar.getTimeInMillis());
		}*/
		
	}
	
}
