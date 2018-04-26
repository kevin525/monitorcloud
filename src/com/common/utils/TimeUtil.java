package com.common.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class TimeUtil {
	private static java.util.Random r=new java.util.Random(); 
	
	static public String makeTempId(){
		long ll = r.nextLong();
		if (ll<0) ll=-ll;
		String tid = "t"+Calendar.getInstance().getTimeInMillis()+"r"+ll;
		return tid;
	}
	public static String DATE_YEAR = "yyyy";
	public static String DATE_YEAR_MONTH = "yyyy-MM";
	public static String DATE_FORMAT = "yyyyMMdd";
	public static String DATE_FORMAT_EX = "yyyy-MM-dd";
	public static String TIME_FORMAT = "HHmmss";
	public static String BH_TIME_FORMAT = "HH:mm:ss";
	public static String DATETIME_FORMAT = DATE_FORMAT + TIME_FORMAT;
	public static String BH_DATETIME_FORMAT = DATE_FORMAT_EX +" "+ BH_TIME_FORMAT;
	
	public static String GetCurDate() {
		String ddate = new SimpleDateFormat(DATE_FORMAT).format(Calendar
				.getInstance().getTime());
		return ddate;
	}
	public static String GetCurYear() {
		String ddate = new SimpleDateFormat(DATE_YEAR).format(Calendar
				.getInstance().getTime());
		return ddate;
	}
	public static String GetCurYearMonth() {
		String ddate = new SimpleDateFormat(DATE_YEAR_MONTH).format(Calendar
				.getInstance().getTime());
		return ddate;
	}
	public static String GetCurDateEx() {
		String ddate = new SimpleDateFormat(DATE_FORMAT_EX).format(Calendar
				.getInstance().getTime());
		return ddate;
	}

	public static String GetCurTime() {
		String time = new SimpleDateFormat(TIME_FORMAT).format(Calendar
				.getInstance().getTime());
		return time;
	}

	public static String GetCurDateTime() {
		String ddate = new SimpleDateFormat(DATETIME_FORMAT).format(Calendar
				.getInstance().getTime());
		return ddate;
	}

	public static String ParseDate(Calendar c) {
		if(c == null)
			return null;
		String ddate = new SimpleDateFormat(DATE_FORMAT).format(c.getTime());
		return ddate;
	}

	public static String ParseTime(Calendar c) {
		if(c == null)
			return null;
		String time = new SimpleDateFormat(TIME_FORMAT).format(c.getTime());
		return time;
	}

	public static String ParseDateTime(Calendar c) {
		if(c == null)
			return null;
		String ddate = new SimpleDateFormat(DATETIME_FORMAT).format(c.getTime());
		return ddate;
	}

	public static Date String2Date(String str){
		if(isNullString(str)){
			str = GetCurDate();
		}
		try {
			Format fm = new SimpleDateFormat(DATE_FORMAT);
			Date sDate = ((DateFormat) fm).parse(str);
			return sDate;
		} catch (ParseException e) {
			// TODO 自动生成 catch 块

			e.printStackTrace();

			//System.out.println("错误的时间格式。");
		}
		return String2Date(null);
	}
	
	public static Date String2DateTime(String str){
		if(isNullString(str)){
			str = GetCurDateTime();
		}
		try {
			Format fm = new SimpleDateFormat(DATETIME_FORMAT);
			Date sDate = ((DateFormat) fm).parse(str);
			return sDate;
		} catch (ParseException e) {
			// TODO 自动生成 catch 块

			e.printStackTrace();
	
			//System.out.println("错误的时间格式。");
		}
		return String2Date(null);
	}
	 SimpleDateFormat DATE_FORMAT_FILENAME = new SimpleDateFormat(
		"yyyyMMddHHmmssSSS");
	//BH_DATETIME_FORMAT
	
	 public static String DateTime2String(Date dateTime){
			 
		 	if(dateTime == null){
				return null;
		 	}
				Format fm = new SimpleDateFormat(BH_DATETIME_FORMAT);
				String datetime=((DateFormat) fm).format(dateTime);
			
			return datetime;
		 
		}
	public static Calendar String2Calendar(String str){
		if(isNullString(str)){
			str = GetCurDateTime();
		}
		Date d;
		if(str.length()>10){
			d =String2DateTime(str);
		} else{
			d = String2Date(str);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}
	
	public static Calendar Date2Calendar(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static String Date2String(Date tdate) {
		if(tdate == null)
			return null;
		String date = new SimpleDateFormat(DATE_FORMAT).format(tdate);
		if(date.length()==8){
			date = date + "000000";
		}
		return date;
	}
	
	public static String String2SDate(String tt) {
		if (tt==null || tt.length()<8) return "";
		return tt.substring(0,4)+"-"+tt.substring(4,6)+"-"+tt.substring(6,8);
	}
	
	public static String SDate2String(String tt) {
		if (tt==null || tt.length()<10) return "";
		return tt.substring(0,4)+tt.substring(5,7)+tt.substring(8,10)+"000000";
	}
	//截取到分钟
	public static String SDate2String2(String tt) {
		if (tt==null || tt.length()<10) return "";
		return tt.substring(0,4)+tt.substring(5,7)+tt.substring(8,10)+tt.substring(11,13)+tt.substring(14,16);
	}
	public static String Null2String(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
	public static boolean isNullString(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return false;
	}
	/**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     * @param nowdate 源日期
     * @param delay 前移或后移的天数
     * @param dateFormat 日期格式
     * @return 返回延后或前几天的日期
     */
    public static String getNextDay(String nowDate, int delay, String dateFormat)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            java.util.Date d = (nowDate == null) ? new Date() : strToFullDate(nowDate);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            return format.format(d);
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 告警模块使用，获得指定时间 xx分钟之后的时间
     * @param nowDate
     * @param minute
     * @param dateFormat
     * @return
     */
    public static String getAfterTime(String nowTime, int minute, String dateFormat) {
	  	SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		Date sDate;
		try {
			sDate = sdf.parse(nowTime);
			Date afterDate = new Date((sDate.getTime() + minute*60000));
			String afterTime =sdf.format(afterDate);
			return SDate2String2(afterTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return SDate2String2(nowTime);
    }
    private static Date strToFullDate(String nowDate) {
      	 SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT);
           ParsePosition pos = new ParsePosition(0);
           return formatter.parse(nowDate, pos);
   	}
	public static void main(String[] args){
		/*UUID uuid = UUID.randomUUID();
		 String str=uuid.toString();
		 System.out.println(str);*/
		// String curDate = GetCurDate();
		// System.out.println(curDate.substring(0, 4) + "/" + curDate.substring(4, 6));
		/* String string = getNextDay(GetCurDateTime(), -14, DATETIME_FORMAT);
		 System.out.println(strToFullDate("20171111091111"));
		 System.out.println(getNextDay("20160301000000", -1, BH_DATETIME_FORMAT));
		 System.out.println("===" + string);*/
		System.out.println(GetCurDateTime() );
		
	}
	
	/**
     * 通过月来获取该月有几天
     * @param month 该月
     * @return 返回该月的日期
     */
	public static String getDayByMonth(String month){
		String day = "";
		int monthInt =Integer.parseInt(month);
		switch (monthInt) {
			case 0:
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = "31";
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				day = "30";
				break;
			default:
				//2月
				int year =Integer.parseInt(GetCurDateEx().split("-")[0]);
				if((year%4==0&&year%100!=0)||year%400==0){
					day = "29";
				}else{
					day = "28";
				}
				break;
		}
		return day;
	}
}
