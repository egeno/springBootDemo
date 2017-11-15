package com.qjkj.qjcsp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

	public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat formatterhms = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat formatterShort = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat formatterShortStr = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat formatterTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final SimpleDateFormat formatterTimeStr = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	public static final SimpleDateFormat formatterShortTimeStr = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分");

	public static final SimpleDateFormat formatterYYMMDD = new SimpleDateFormat("yyMMdd");
	public static String getDateFormat(String format,Date date) {
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}
	/**
	 * 
	 * @Title:getYear
	 * @Description:
	 * 				<p>
	 *               获取当前系统年份
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:10:38
	 * @return
	 */
	public static int getYear() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(currentTime);
		int newYear = Integer.parseInt(dateString);
		return newYear;
	}

	/**
	 * 
	 * @Title:getMonth
	 * @Description:
	 * 				<p>
	 *               获取当前系统月份
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:10:51
	 * @return
	 */
	public static int getMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		String dateString = formatter.format(currentTime);
		int newMonth = Integer.parseInt(dateString);
		return newMonth;
	}

	/**
	 * 
	 * @Title:formatStringDateTime
	 * @Description:
	 * 				<p>
	 *               Date转String
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:11:00
	 * @param date
	 *            日期yyyy年MM月dd日 HH点mm分
	 * @return
	 */
	public static String formatStringDateTime(Date date) {
		if (date == null) {
			return "";
		} else {
			try {
				String formatString = formatterShortTimeStr.format(date);
				return formatString;
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 
	 * @Title:formatString
	 * @Description:
	 * 				<p>
	 *               Date转String
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:11:36
	 * @param date
	 *            日期 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatString(Date date) {
		if (date == null) {
			return "";
		} else {
			try {
				String formatString = formatter.format(date);
				return formatString;
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 
	 * @Title:formatStringTime
	 * @Description:
	 * 				<p>
	 *               Date转String
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:12:05
	 * @param date
	 *            日期 yyyy/MM/dd HH:mm:ss
	 * @return
	 */
	public static String formatStringTime(Date date) {
		if (date == null) {
			return "";
		} else {
			try {
				String formatString = formatterTime.format(date);
				return formatString;
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 
	 * @Title:formatStringTimeStr
	 * @Description:
	 * 				<p>
	 *               Date转String
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:12:32
	 * @param date
	 *            日期 yyyy.MM.dd HH:mm
	 * @return
	 */
	public static String formatStringTimeStr(Date date) {
		if (date == null) {
			return "";
		} else {
			try {
				String formatString = formatterTimeStr.format(date);
				return formatString;
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 
	 * @Title:formatShortStr
	 * @Description:
	 * 				<p>
	 *               Date转String
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:13:15
	 * @param date
	 *            日期 yyyy-MM-dd
	 * @return
	 */
	public static String formatShortStr(Date date) {
		if (date == null) {
			return "";
		} else {
			try {
				String formatString = formatterShort.format(date);
				return formatString;
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 
	 * @Title:formatDate
	 * @Description:
	 * 				<p>
	 *               String转Date
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:13:37
	 * @param str
	 *            支持yyyy-MM-dd HH:mm:ss 和 yyyy-MM-dd 两种格式
	 * @return
	 */
	public static Date formatDate(String str) {
		if (str == null) {
			return null;
		} else {
			int length = str.trim().length();
			try {
				Date formatDate = null;
				if (length <= 10) {
					formatDate = formatterShort.parse(str);
				} else {
					formatDate = formatter.parse(str);
				}
				return formatDate;
			} catch (Exception e) {
				return null;
			}
		}
	}
	/**
	 * @param str
	 *            得到 hms 的 Date
	 * @return
	 */
	public static Date formatDatehms(String str) {
		Date formatDate = null;
		SimpleDateFormat formatterhms = new SimpleDateFormat("HH:mm:ss");
		try {
			formatDate = formatterhms.parse(str);
		} catch (Exception e) {
			return null;
		}
		return formatDate;
	}

	/**
	 * 
	 * @Title:formatStr
	 * @Description:
	 * 				<p>
	 *               Date转String
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:14:03
	 * @param date
	 *            日期 yyyy年MM月dd日
	 * @return
	 */
	public static String formatStr(Date date) {
		if (date == null) {
			return "";
		} else {
			try {
				String formatString = formatterShortStr.format(date);
				return formatString;
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 
	 * @Title:formatYYMMDD
	 * @Description:
	 * 				<p>
	 *               Date转String
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:14:03
	 * @param date
	 *            日期 yyMMdd
	 * @return
	 */
	public static String formatYYMMDD(Date date) {
		if (date == null) {
			return "";
		} else {
			try {
				String formatString = formatterYYMMDD.format(date);
				return formatString;
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 
	 * @Title:getCleverDate
	 * @Description:
	 * 				<p>
	 *               得到当前日期和参数传递日期的差值
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:15:19
	 * @param date
	 * @return
	 */
	public static String getCleverDate(Date date) {

		long current_time = new Date().getTime();
		long compare_time = date.getTime();
		long cha = current_time - compare_time;

		if (cha < 1000) {
			return "1秒钟前";
		}
		// second
		if (cha < (1000 * 60)) {
			return (int) (cha / 1000) + "秒钟前";
		}
		// mintue
		if (cha < (1000 * 60 * 60)) {
			return (int) (cha / (1000 * 60)) + "分钟前";
		}
		// hour
		if (cha < (1000 * 60 * 60 * 24)) {
			return (int) (cha / (1000 * 60 * 60)) + "小时前";
		}
		// day
		if (cha < (1000 * 60 * 60 * 24 * 7)) {
			return (int) (cha / (1000 * 60 * 60 * 24)) + "天前";
		}
		// week
		if (cha < (new Long(1000).longValue() * 60 * 60 * 24 * 30)) {
			return (int) (cha / (1000 * 60 * 60 * 24 * 7)) + "星期前";
		}
		// month
		if (cha < (new Long(1000).longValue() * 60 * 60 * 24 * 30 * 12)) {
			return (int) (cha / (new Long(1000 * 60 * 60 * 24).longValue() * 30)) + "月前";
		}
		// long time ...
		return "一年前...";
	}

	/**
	 * 
	 * @Title:getDayDate
	 * @Description:
	 * 				<p>
	 *               得到一个日期与今天相差的时间
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:15:45
	 * @param date2
	 * @return 1:今天 2:昨天 3:更早
	 */
	public static int getDayDate(Date date2) {
		if (date2 != null) {
			Date date = new Date();
			String formatString = formatterShort.format(date);
			String formatString2 = formatterShort.format(date2);
			// 返回今天
			if (formatString.equals(formatString2)) {
				return 1;
			}
			String[] datestr = formatString.split("-");
			String[] datestr2 = formatString2.split("-");
			int y = Integer.parseInt(datestr[0]);
			int m = Integer.parseInt(datestr[1]);
			int d = Integer.parseInt(datestr[2]);

			int y2 = Integer.parseInt(datestr2[0]);
			int m2 = Integer.parseInt(datestr2[1]);
			int d2 = Integer.parseInt(datestr2[2]);
			// 返回昨天
			if (y == y2 && m == m2 && (d - 1) == d2) {
				return 2;
			}
			if (y == y2 && (m - 1) == m2) {
				if (m2 == 1 || m2 == 3 || m2 == 5 || m2 == 7 || m2 == 8 || m2 == 10 || m2 == 12) {
					if (d == 31 && d2 == 1) {
						return 2;
					}
				}
				if (m2 == 4 || m2 == 6 || m2 == 9 || m2 == 11) {
					if (d == 30 && d2 == 1) {
						return 2;
					}
				}
				if (isLeapYear(y)) {
					if (m2 == 2) {
						if (d == 28 && d2 == 1) {
							return 2;
						}
					}
				} else {
					if (m2 == 2) {
						if (d == 29 && d2 == 1) {
							return 2;
						}
					}
				}
			}
			if ((y - 1) == y2 && m == 12 && d == 1) {
				return 2;
			}
			// 返回更早
			return 3;
		}
		return 0;
	}

	/**
	 * 
	 * @Title:isLeapYear
	 * @Description:
	 * 				<p>
	 *               判断闰年
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:16:40
	 * @param year
	 *            年数
	 * @return true:是 false:不是
	 */
	public static boolean isLeapYear(int year) {
		boolean falg = false;
		if (year % 4 != 0) {
			falg = false;
		} else if (year % 100 != 0) {
			falg = true;
		} else if (year % 400 != 0) {
			falg = false;
		} else {
			falg = true;
		}
		return falg;
	}

	/**
	 * 
	 * @Title:formatStrToHms
	 * @Description:
	 * 				<p>
	 *               Date转String 支持 HH:mm:ss 格式
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:17:22
	 * @param date
	 * @return HH:mm:ss 格式
	 */
	public static String formatStrToHms(Date date) {
		if (date == null) {
			return "";
		} else {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				String formatString = formatter.format(date);
				return formatString;
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 
	 * @Title:getDateDifference
	 * @Description:
	 * 				<p>
	 *               得到当前日期和参数传递日期的差值
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:18:36
	 * @param date
	 *            日期
	 * @param sort
	 *            0 second 1 mintue 2 hour 3 day 4 week 5 month 6 year 7 century
	 * @return
	 */
	public static int getDateDifference(Date date, int sort) {

		long current_time = new Date().getTime();
		long compare_time = date.getTime();
		long cha = current_time - compare_time;
		switch (sort) {
		case 1:// second
			if (cha < 1000) {
				return 0;
			} else {
				return (int) (cha / 1000);
			}
		case 2:// mintue
			return (int) (cha / (1000 * 60));
		case 3:// hour
			return (int) (cha / (1000 * 60 * 60));
		case 4:// day
			return (int) (cha / (1000 * 60 * 60 * 24));
		case 5:// week
			return (int) (cha / (1000 * 60 * 60 * 24 * 7));
		case 6:// month
			return (int) (cha / (new Long(1000 * 60 * 60 * 24).longValue() * 30));
		case 7:// year
			return (int) (cha / (new Long(1000 * 60 * 60 * 24).longValue() * 30 * 365));
		case 8:// century
			return ((int) (cha / (new Long(1000 * 60 * 60 * 24).longValue() * 30 * 365))) / 100;
		default:
			return (int) cha;
		}
	}

	/**
	 * 
	 * @Title:getCurrentSystemDate
	 * @Description:
	 * 				<p>
	 *               获得系统当前时间
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:18:16
	 * @return date
	 */
	public static Date getCurrentSystemDate() {
		return new Date();
	}

	/**
	 * 
	 * @Title:getCurrentSystemDate
	 * @Description:
	 * 				<p>
	 *               获得系统当前时间
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-4 下午10:18:16
	 * @return date
	 */
	public static long getCurrentSystemTime() {
		return new Date().getTime();
	}

	/**
	 * 判断日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static boolean checkDate(String date) {
		if (date == null || "".equals(date)) {
			return false;
		}
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 
	 * @Title:compareDate
	 * @Description:
	 * 				<p>
	 *               日期比较大小
	 *               </p>
	 * 
	 * @author gaoboyu
	 * @date 2013-3-19 下午3:38:19
	 * @param date
	 * @return 1：早于今天
	 * @return 2：晚于今天
	 * @return 0：比较错误
	 */
	public int compareDate(Date date) {
		if (date == null) {
			return 0;
		}
		if (date.before(new Date())) {
			return 1;// 早于今天
		} else {
			return 2;// 晚于今天
		}

	}

	/**
	 * 获取当前时间与传进参数时间时间差 格式： dd天hh时mm分ss秒
	 * 
	 * @return
	 */
	public static String getDateDifferences(Date date) {
		long inputTime = date.getTime();
		long nowTime = new Date().getTime();
		long diff = nowTime - inputTime;
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		String str = diffDays + "天" + diffHours + "时" + diffMinutes + "分" + diffSeconds + "秒";
		return str;
	}

	/**
	 * 获取今天所在的星期的所有日期 周一到周日
	 */
	public static List<String> getThisWeekDate() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		// calendar.setTime(new Date());
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		// 计算一周开始的日期
		calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

		for (int i = 1; i <= 7; i++) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			System.out.println(formatterShort.format(calendar.getTime()));
			list.add(formatterShort.format(calendar.getTime()));
		}
		return list;
	}
	public static Date getNowDate(){
		Date nowDate = new Date();
		return nowDate;
	}
	public static String getMonday(String psdate){
		if (psdate.equals("null")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			psdate = sdf.format(new Date());
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
        Calendar cal = Calendar.getInstance();  
        Date time;
		try {
			time = sdf.parse(psdate);
			  cal.setTime(time);  
		        //System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
		        
		        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
		        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		        if(1 == dayWeek) {  
		           cal.add(Calendar.DAY_OF_MONTH, -1);  
		        }  
		        
		       cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
		       
		       int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		       cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return sdf.format(cal.getTime());
	}
	public static String getSunday(String psdate){
		if (psdate.equals("null")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			psdate = sdf.format(new Date());
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
        Calendar cal = Calendar.getInstance();  
        Date time;
		try {
			time = sdf.parse(psdate);
			  cal.setTime(time);  
		        //System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
		        
		        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
		        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		        if(1 == dayWeek) {  
		           cal.add(Calendar.DAY_OF_MONTH, -1);  
		        }  
		        
		       cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
		       int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		       cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
		       cal.add(Calendar.DATE, 6);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return sdf.format(cal.getTime());
	}
	
	
}
