package cn.xeonsoft.scheduler.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	public static Date get8hBeginDate(DateInterval dataInterval) {
		return get8hBeginDate(dataInterval, new Date());
	}

	public static Date get8hEndDate(DateInterval dataInterval) {
		return get8hEndDate(dataInterval, new Date());
	}

	public static Date get8hBeginDateTime(Date tm) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(tm);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date get8hEndDateTime(Date tm) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(tm);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static Date get8hBeginDate(DateInterval dataInterval, Date tm) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(tm);
		// type:1日2周3月4季5年
		switch (dataInterval) {
		case DAY:
			break;
		case MONTH:
			cal.set(Calendar.DAY_OF_MONTH, 1);
			break;
		case YEAR:
			cal.set(Calendar.DAY_OF_YEAR, 1);
			break;
		}
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date get8hEndDate(DateInterval dataInterval, Date tm) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(tm);
		// type:1日2周3月4季5年
		switch (dataInterval) {
		case DAY:
			cal.add(Calendar.DATE, 1);
			break;
		case MONTH:
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.MONTH, 1);
			break;
		case YEAR:
			cal.roll(Calendar.DAY_OF_YEAR, -1);
			cal.add(Calendar.DATE, 1);
			break;
		}
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getBeginDate(DateInterval dataInterval) {
		return getBeginDate(dataInterval, new Date());
	}

	public static Date getBeginDate(DateInterval dataInterval, Date tm) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(tm);
		// type:1日2周3月4季5年
		switch (dataInterval) {
		case DAY:
			break;
		case FIVEDAYS:
			int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
			if(day_of_month >= 1 && day_of_month <= 5){
				cal.set(Calendar.DAY_OF_MONTH, 1);
			}else if(day_of_month >= 6 && day_of_month <= 10){
				cal.set(Calendar.DAY_OF_MONTH, 6);
			}else if(day_of_month >= 11 && day_of_month <= 15){
				cal.set(Calendar.DAY_OF_MONTH, 11);
			}else if(day_of_month >= 16 && day_of_month <= 20){
				cal.set(Calendar.DAY_OF_MONTH, 16);
			}else if(day_of_month >= 21 && day_of_month <= 25){
				cal.set(Calendar.DAY_OF_MONTH, 21);
			}else if(day_of_month >= 26 && day_of_month <= 31){
				cal.set(Calendar.DAY_OF_MONTH, 26);
			}
			break;
		case TENDAYS:
			int day = cal.get(Calendar.DAY_OF_MONTH);
			if(day >= 1 && day <= 10){
				cal.set(Calendar.DAY_OF_MONTH, 1);
			}else if(day >= 11 && day <= 20){
				cal.set(Calendar.DAY_OF_MONTH, 11);
			}else if(day >= 21 && day <= 31){
				cal.set(Calendar.DAY_OF_MONTH, 21);
			}
			break;
		case WEEK:
			int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (day_of_week == 0)
				day_of_week = 7;
			cal.add(Calendar.DATE, -day_of_week + 1);
			/* cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); */
			break;
		case MONTH:
			cal.set(Calendar.DAY_OF_MONTH, 1);
			break;
		case QUARTER:
			int currentMonth = cal.get(Calendar.MONTH) + 1;
			try {
				if (currentMonth >= 1 && currentMonth <= 3)
					cal.set(Calendar.MONTH, 0);
				else if (currentMonth >= 4 && currentMonth <= 6)
					cal.set(Calendar.MONTH, 3);
				else if (currentMonth >= 7 && currentMonth <= 9)
					cal.set(Calendar.MONTH, 6);
				else if (currentMonth >= 10 && currentMonth <= 12)
					cal.set(Calendar.MONTH, 9);
				cal.set(Calendar.DATE, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case YEAR:
			cal.set(Calendar.DAY_OF_YEAR, 1);
			break;
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getEndDate(DateInterval dataInterval) {
		return getEndDate(dataInterval, new Date());
	}

	public static Date getEndDate(DateInterval dataInterval, Date tm) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(tm);
		// type:1日2周3月4季5年
		switch (dataInterval) {
		case DAY:
			break;
		case FIVEDAYS:
			int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
			if(day_of_month >= 1 && day_of_month <= 5){
				cal.set(Calendar.DAY_OF_MONTH, 5);
			}else if(day_of_month >= 6 && day_of_month <= 10){
				cal.set(Calendar.DAY_OF_MONTH, 10);
			}else if(day_of_month >= 11 && day_of_month <= 15){
				cal.set(Calendar.DAY_OF_MONTH, 15);
			}else if(day_of_month >= 16 && day_of_month <= 20){
				cal.set(Calendar.DAY_OF_MONTH, 20);
			}else if(day_of_month >= 21 && day_of_month <= 25){
				cal.set(Calendar.DAY_OF_MONTH, 25);
			}else if(day_of_month >= 26 && day_of_month <= 31){
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
			break;
		case TENDAYS:
			int day = cal.get(Calendar.DAY_OF_MONTH);
			if(day >= 1 && day <= 10){
				cal.set(Calendar.DAY_OF_MONTH, 10);
			}else if(day >= 11 && day <= 20){
				cal.set(Calendar.DAY_OF_MONTH, 20);
			}else if(day >= 21 && day <= 31){
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
			break;
		case WEEK:
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);
			break;
		case MONTH:
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			break;
		case QUARTER:
			int currentMonth = cal.get(Calendar.MONTH) + 1;
			System.out.println(currentMonth);
			try {
				if (currentMonth >= 1 && currentMonth <= 3)
					cal.set(Calendar.MONTH, 3);
				else if (currentMonth >= 4 && currentMonth <= 6)
					cal.set(Calendar.MONTH, 6);
				else if (currentMonth >= 7 && currentMonth <= 9)
					cal.set(Calendar.MONTH, 9);
				else if (currentMonth >= 10 && currentMonth <= 12)
					cal.set(Calendar.MONTH, 12);
				cal.set(Calendar.DATE, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case YEAR:
			//cal.add(Calendar.YEAR, 1);
			cal.roll(Calendar.DAY_OF_YEAR, -1);
			//cal.set(Calendar.DAY_OF_YEAR, -1);
			break;
		}
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		return cal.getTime();
	}

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM", "yyyy" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear(Date tm) {
		return formatDate(tm, "yyyy");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth(Date tm) {
		return formatDate(tm, "MM");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}
	/**
	 * 得到指定时间字符串 格式（dd）
	 */
	public static String getDay(Date tm) {
		return formatDate(tm, "dd");
	}


	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	public static int getDistanceMonthOfTwoDate(Date date1, Date date2) {
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
				- cal2.get(Calendar.MONTH);
		return c;
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// System.out.println(formatDate(parseDate("2010/3/6")));
		// System.out.println(getDate("yyyy年MM月dd日 E"));
		// long time = new Date().getTime()-parseDate("2012-11-19").getTime();
		// System.out.println(time/(24*60*60*1000));
//		System.out.println(DateUtils.formatDate(getBeginDate(DateInterval.DAY, new Date()), "yyyy-MM-dd HH:mm:ss"));
//
//		System.out.println(DateUtils.formatDate(get8hBeginDate(DateInterval.MONTH, new Date()), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.formatDate(get8hEndDate(DateInterval.MONTH, new Date()), "yyyy-MM-dd HH:mm:ss"));
//
//		System.out.println(DateUtils.formatDate(get8hBeginDate(DateInterval.YEAR, DateUtils.parseDate("2017-01-01")),
//				"yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.formatDate(get8hEndDate(DateInterval.YEAR, DateUtils.parseDate("2017-01-01")),
//				"yyyy-MM-dd HH:mm:ss"));
//
//		System.out.println(DateUtils.formatDate(get8hBeginDate(DateInterval.DAY, new Date()), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.formatDate(get8hEndDate(DateInterval.DAY, new Date()), "yyyy-MM-dd HH:mm:ss"));
//
//		System.out.println(get8hBeginDateTime(new Date()));
//		System.out.println(get8hEndDateTime(new Date()));
//
//		System.out.println(getDistanceMonthOfTwoDate(DateUtils.parseDate("2017-01-01"), new Date()));
		System.out.println(DateUtils.getBeginDate(DateInterval.MONTH,new Date()));
		System.out.println(DateUtils.get8hBeginDate(DateInterval.DAY,new Date()));
		System.out.println(DateUtils.get8hEndDate(DateInterval.DAY,new Date()));
	}
}
