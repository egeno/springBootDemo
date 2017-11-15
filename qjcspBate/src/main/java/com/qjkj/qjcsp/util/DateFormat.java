package com.qjkj.qjcsp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名:DateFormat
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-29
 * 日期格式的格式转换类
 */
public class DateFormat {
	/**
	 * 将date型的带时间数据转换成去时间的日期格式数据
	 * @param date 待转换的日期数据
	 * @return
	 */
	public static Date dateFormatYYYYMMDD(Date date) {
		Date newDate = date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);
		try {
			newDate = sdf.parse(strDate);
		} catch (Exception e) {

		}
		return newDate;
	}

	/**
	 * @function 将date型的转换成带时间的日期格式数据
	 * @param date
	 * @return
	 */
	public static Date dateFormatYYYYMMDDHHMMSS(Date date) {
		Date newDate = date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(date);
		try {
			newDate = sdf.parse(strDate);
		} catch (Exception e) {

		}
		return newDate;
	}

	/**
	 * @function 将字符串型的日期数据转换成“yyyy-MM-dd”的日期数据
	 * @param strDate
	 * @return
	 */
	public static Date dateFormatYYYYMMDD(String strDate) {
		Date newDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			newDate = sdf.parse(strDate);
		} catch (Exception e) {

		}
		return newDate;
	}
	
	/**
	 * @function 返回“yyyy-MM-dd”格式的日期字符串数据
	 * @return
	 */
	public static String dateFormatYYYYMMDD() {
		Date newDate = new Date();
		String strDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			strDate = sdf.format(newDate);
		} catch (Exception e) {

		}
		return strDate;
	}

	/**
	 * @function 将日期的字符串数据转换成"yyyy-MM-dd HH:mm:ss"的日期型数据
	 * @param strDate 字符串的日期数据
	 * @return
	 */
	public static Date dateFormatYYYYMMDDHHMMSS(String strDate) {
		Date newDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			newDate = sdf.parse(strDate);
		} catch (Exception e) {

		}
		return newDate;
	}

	/**
	 * @function 将日期型数据转换成"yyyy-MM-dd HH:mm:ss"的字符串日期数据
	 * @param date 日期型数据
	 * @return
	 */
	public static String dateFormatYMDHMS(Date date) {
		String strDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			strDate = sdf.format(date);
		} catch (Exception e) {

		}
		return strDate;
	}
	/**
	 * @function 将日期型数据转换成"yyyy-MM-dd HH:mm"的字符串日期数据
	 * @param date 日期型数据
	 * @return
	 */
	public static String dateFormatYMDHM(Date date) {
		String strDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			strDate = sdf.format(date);
		} catch (Exception e) {

		}
		return strDate;
	}
}
