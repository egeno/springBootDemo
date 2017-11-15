package com.qjkj.qjcsp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.entity.WeekPo;

/**
 * 类名:DateTimeUtil 
 * 创建者:yjg 
 * 版本号：V1.0 
 * 日期：2015-12-29 
 * 日期工具类
 */
public class DateTimeUtil {
	private static String ymdhms = "yyyy-MM-dd HH:mm:ss";
	private static String ymd = "yyyy-MM-dd";
	public static SimpleDateFormat ymdSDF = new SimpleDateFormat(ymd);
	private static String year = "yyyy";
	private static String month = "MM";
	private static String day = "dd";
	public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(ymdhms);
	public static SimpleDateFormat yearSDF = new SimpleDateFormat(year);
	public static SimpleDateFormat monthSDF = new SimpleDateFormat(month);
	public static SimpleDateFormat daySDF = new SimpleDateFormat(day);

	public static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat yyyyMMddHH_NOT_ = new SimpleDateFormat("yyyyMMdd");
	
	public static SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");

	public static long DATEMM = 86400L;

	/**
	 * 获得当前时间 格式：2014-12-02 10:38:53
	 * 
	 * @return String
	 */
	public static String getCurrentTime() {
		return yyyyMMddHHmmss.format(new Date());
	}

	/**
	 * 可以获取昨天的日期 格式：2014-12-01
	 * 
	 * @return String
	 */
	public static String getYesterdayYYYYMMDD() {
		Date date = new Date(System.currentTimeMillis() - DATEMM * 1000L);
		String str = yyyyMMdd.format(date);
		try {
			date = yyyyMMddHHmmss.parse(str + " 00:00:00");
			return yyyyMMdd.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 可以获取明天的日期 格式：2014-12-03
	 * 
	 * @return String
	 */
	public static String getTomorrowYYYYMMDD() {
		Date date = new Date(System.currentTimeMillis() + DATEMM * 1000L);
		String str = yyyyMMdd.format(date);
		try {
			date = yyyyMMddHHmmss.parse(str + " 00:00:00");
			return yyyyMMdd.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 可以获取明天的日期 格式：2014-12-03
	 * 
	 * @return String
	 */
	public static Date getTomorrow() {
		Date date = new Date(System.currentTimeMillis() + DATEMM * 1000L);

		return DateFormat.dateFormatYYYYMMDD(date);
	}
	
	/**
	 * 可以获取后退N天的日期 格式：传入2 得到2014-11-30
	 * 
	 * @param backDay
	 * @return String
	 */
	public String getStrDate(String backDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt("-" + backDay));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String back = sdf.format(calendar.getTime());
		return back;
	}

	/**
	 * 获取当前的年、月、日
	 * 
	 * @return String
	 */
	public static String getCurrentYear() {
		return yearSDF.format(new Date());
	}

	public static String getCurrentMonth() {
		return monthSDF.format(new Date());
	}

	public static String getCurrentDay() {
		return daySDF.format(new Date());
	}

	/**
	 * 获取年月日 也就是当前时间 格式：2014-12-02
	 * 
	 * @return String
	 */
	public static String getCurrentymd() {
		return ymdSDF.format(new Date());
	}

	/**
	 * 获取今天0点开始的秒数
	 * 
	 * @return long
	 */
	public static long getTimeNumberToday() {
		Date date = new Date();
		String str = yyyyMMdd.format(date);
		try {
			date = yyyyMMdd.parse(str);
			return date.getTime() / 1000L;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	/**
	 * 获取今天的日期 格式：20141202
	 * 
	 * @return String
	 */
	public static String getTodateString() {
		String str = yyyyMMddHH_NOT_.format(new Date());
		return str;
	}

	/**
	 * 获取昨天的日期 格式：20141201
	 * 
	 * @return String
	 */
	public static String getYesterdayString() {
		Date date = new Date(System.currentTimeMillis() - DATEMM * 1000L);
		String str = yyyyMMddHH_NOT_.format(date);
		return str;
	}

	/**
	 * 获得昨天零点
	 * 
	 * @return Date
	 */
	public static Date getYesterDayZeroHour() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		return cal.getTime();
	}

	/**
	 * 把long型日期转String ；---OK
	 * 
	 * @param date long型日期；
	 * @param format 日期格式；
	 * @return
	 */
	public static String longToString(long date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		java.util.Date dt2 = new Date(date * 1000L);
		String sDateTime = sdf.format(dt2); // 得到精确到秒的表示：08/31/2006 21:08:00
		return sDateTime;
	}

	/**
	 * 获得今天零点
	 * 
	 * @return Date
	 */
	public static Date getTodayZeroHour() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		return cal.getTime();
	}

	/**
	 * 获得昨天23时59分59秒
	 * 
	 * @return
	 */
	public static Date getYesterDay24Hour() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.HOUR, 23);
		return cal.getTime();
	}

	/**
	 * String To Date ---OK
	 * 
	 * @param date 待转换的字符串型日期；
	 * @param format 转化的日期格式
	 * @return 返回该字符串的日期型数据；
	 */
	public static Date stringToDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获得指定日期所在的自然周的第一天，即周日
	 * 
	 * @param date 日期
	 * @return 自然周的第一天
	 */
	public static Date getStartDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * 获得指定日期所在的自然周的最后一天，即周六
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, 7);
		date = c.getTime();
		return date;
	}

	/**
	 * 获得指定日期所在当月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * 获得指定日期所在当月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * 获得指定日期的下一个月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDayOfNextMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * 获得指定日期的下一个月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfNextMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 2);
		c.add(Calendar.DATE, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * 
	 * 求某一个时间向前多少秒的时间(currentTimeToBefer)---OK
	 * 
	 * @param givedTime 给定的时间
	 * @param interval 间隔时间的毫秒数；计算方式 ：n(天)*24(小时)*60(分钟)*60(秒)(类型)
	 * @param format_Date_Sign 输出日期的格式；如yyyy-MM-dd、yyyyMMdd等；
	 */
	public static String givedTimeToBefer(String givedTime, long interval, String format_Date_Sign) {
		String tomorrow = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format_Date_Sign);
			Date gDate = sdf.parse(givedTime);
			long current = gDate.getTime(); // 将Calendar表示的时间转换成毫秒
			long beforeOrAfter = current - interval * 1000L; // 将Calendar表示的时间转换成毫秒
			Date date = new Date(beforeOrAfter); // 用timeTwo作参数构造date2
			tomorrow = new SimpleDateFormat(format_Date_Sign).format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tomorrow;
	}

	/**
	 * 把String 日期转换成long型日期；---OK
	 * 
	 * @param date String 型日期；
	 * @param format 日期格式；
	 * @return
	 */
	public static long stringToLong(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dt2 = null;
		long lTime = 0;
		try {
			dt2 = sdf.parse(date);
			// 继续转换得到秒数的long型
			lTime = dt2.getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lTime;
	}

	/**
	 * 得到二个日期间的间隔日期；
	 * 
	 * @param endTime 结束时间
	 * @param beginTime 开始时间
	 * @param isEndTime 是否包含结束日期；
	 * @return
	 */
	public static Map<String, String> getTwoDay(String endTime, String beginTime, boolean isEndTime) {
		Map<String, String> result = new HashMap<String, String>();
		if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime.equals(""))))
			return null;
		try {
			java.util.Date date = ymdSDF.parse(endTime);
			endTime = ymdSDF.format(date);
			java.util.Date mydate = ymdSDF.parse(beginTime);
			long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
			result = getDate(endTime, Integer.parseInt(day + ""), isEndTime);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 得到二个日期间的间隔日期；
	 * 
	 * @param endTime 结束时间
	 * @param beginTime 开始时间
	 * @param isEndTime 是否包含结束日期；
	 * @return
	 */
	public static Integer getTwoDayInterval(String endTime, String beginTime, boolean isEndTime) {
		if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime.equals(""))))
			return 0;
		long day = 0l;
		try {
			java.util.Date date = ymdSDF.parse(endTime);
			endTime = ymdSDF.format(date);
			java.util.Date mydate = ymdSDF.parse(beginTime);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return 0;
		}
		return Integer.parseInt(day + "");
	}

	/**
	 * 根据结束时间以及间隔差值，求符合要求的日期集合；
	 * 
	 * @param endTime
	 * @param interval
	 * @param isEndTime
	 * @return
	 */
	public static Map<String, String> getDate(String endTime, Integer interval, boolean isEndTime) {
		Map<String, String> result = new HashMap<String, String>();
		if (interval == 0 || isEndTime) {
			if (isEndTime)
				result.put(endTime, endTime);
		}
		if (interval > 0) {
			int begin = 0;
			for (int i = begin; i < interval; i++) {
				endTime = givedTimeToBefer(endTime, DATEMM, ymd);
				result.put(endTime, endTime);
			}
		}
		return result;
	}

	/**
	 * 给指定日期加上相应的天数
	 * 
	 * @param srcDate 原日期
	 * @param days 天数
	 * @return
	 */
	public static Date getPlusDaysDate(Date srcDate, int days) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(srcDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);// 让日期加1

		return calendar.getTime();
	}

	/**
	 * 给指定日期加上相应的小时数，小时数为double型，如2.5
	 * 
	 * @param srcDate
	 * @param hours
	 * @return
	 */
	public static Date getPlusHoursDate(Date srcDate, double hours) {
		Calendar calendar = Calendar.getInstance();
		int iHours = (int) hours;
		int iMinutes = (int) ((hours - iHours) * 60);

		calendar.setTime(srcDate);
		calendar.add(Calendar.HOUR_OF_DAY, iHours);
		calendar.add(Calendar.MINUTE, iMinutes);

		return calendar.getTime();
	}
	
	public static Date getPlusMinuteDate(Date srcDate, int minute) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(srcDate);
		calendar.add(Calendar.MINUTE, minute);

		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期的星期几
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {      
	    String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(date);      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
	
	/**
	 * 获得指定日期所在的自然周的周日
	 * 
	 * @param date 日期
	 * @return 自然周的周日
	 */
	public static Date getSunDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, 1);
		c.add(Calendar.DAY_OF_WEEK, 7);
		date = c.getTime();
		return date;
	}
	
	/**
	 * 获取指定日期后一天属于一周的第几天
	 * @param date
	 * @return 7:周日 1：周一 .....
	 */
	public static Integer getWeekNumOfDate(Date date) { 
	    Integer[] weekOfDays = {7, 1, 2, 3, 4, 5, 6};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	    	calendar.setTime(date);  
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
	
	/**
	 * 获取当前日期的未来七天时间
	 * */
	public static List<String> getSevenDateList(int addDay) {
		List<String> list=new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, addDay);
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            list.add(yyyyMMdd.format(calendar.getTime()));
        }
        return list;
    }
	
	/**
	 * 获取当前日期的未来七天日期对应的是星期几
	 * */
	public static List<String> getSevenWeekList(int addDay) {
		List<String> list=new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, addDay);
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            list.add(getWeekOfDate(calendar.getTime()));
        }
        return list;
    }
	
	/**
	 * 获取当前日期的未来七天日期对应的是星期几
	 * @return 返回的是对应的数字
	 * */
	public static List<Integer> getSevenWeekNumList(int addDay) {
		List<Integer> list=new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, addDay);
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            list.add(getWeekNumOfDate(calendar.getTime()));
        }
        return list;
    }

	/**
	 * 根据时分秒获得完整的日期
	 * @param date HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date getCompleteTimeByHHmmss(Date date){
		Date currentTime = new Date();
		String ymd = yyyyMMdd.format(currentTime);
		String hms = HHmmss.format(date);
		Date completeTime = null;
		try {
			completeTime = yyyyMMddHHmmss.parse(ymd+" "+hms);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return completeTime;
	}
	
	/**
	 * 根据日期返回周几，星期一为第一天
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int i = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (i==0){
			i=7;
		}
		
		return i;
	}
	
	/**
	 * 获取今日时分秒
	 * @param str HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date getTodayTimeByHHmmss(String str){
		Date currentTime = new Date();
		String ymd = yyyyMMdd.format(currentTime);
		Date completeTime = null;
		try {
			completeTime = yyyyMMddHHmmss.parse(ymd+" "+str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return completeTime;
	}
	
	/**
	 * 获取今日时分
	 * @param str
	 * @return
	 */
	public static Date getTodayTimeByHHmm(String str){
		Date currentTime = new Date();
		String ymd = yyyyMMdd.format(currentTime);
		Date completeTime = null;
		try {
			completeTime = yyyyMMddHHmm.parse(ymd+" "+str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return completeTime;
	}
	/**
	 * 获取今日时分秒
	 * @param Date HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date getTodayTimeByHHmmss(Date str){
		Date currentTime = new Date();
		String ymd = yyyyMMdd.format(currentTime);
		String hms = HHmmss.format(str);
		Date completeTime = null;
		try {
			completeTime = yyyyMMddHHmmss.parse(ymd+" "+hms);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return completeTime;
	}
	
	/**
	 * 获取今日时分秒的前一小时时间
	 * @param str HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date getTodayTimeAnHourBeforeByHHmmss(String str){
		Date currentTime = new Date();
		String ymd = yyyyMMdd.format(currentTime);
		Date completeTime = null;
		Calendar cal = Calendar.getInstance();
		try {
			completeTime = yyyyMMddHHmmss.parse(ymd+" "+str);
			cal.setTime(completeTime);
			cal.add(Calendar.HOUR, -1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return cal.getTime();
	}
	
	/**
	 * 获取明日时分秒
	 * @param str HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date getTorrowTimeByHHmmss(String str){
		Date currentTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentTime);
		cal.add(Calendar.DATE, 1);
		String ymd = yyyyMMdd.format(cal.getTime());
		Date completeTime = null;
		try {
			completeTime = yyyyMMddHHmmss.parse(ymd+" "+str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return completeTime;
	}
	
	/**
	 * 获取传入时间的前15分钟
	 * @param Date HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date getFrontFifteenMinutesDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -15);
		return cal.getTime();
	}
	
}