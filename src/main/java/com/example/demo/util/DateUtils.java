package com.example.demo.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	private static final String defaultFormat = "yyyyMMddHHmmss";
	private static final String defaultFormatWithMilis = "yyyyMMddHHmmssSSS";
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static String getCurrentTimestampWithMilis() {
		return formatDate(new Date(), "yyyyMMddHHmmssSSS");
	}

	public static String getCurrentTimestamp() {
		return formatDate(new Date());
	}

	public static String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = dateFormat.format(date);
		return time;
	}

	public static String formatDate(Date date, String type) {
		DateFormat dateFormat = new SimpleDateFormat(type);
		String time = dateFormat.format(date);
		return time;
	}

	public static Date parseDateTime(String date) {
		if ((date == null) || (date.length() == 0))
			return null;
		if (date.length() == 14)
			return parse(date, "yyyyMMddHHmmss");
		if (date.length() == 17) {
			return parse(date, "yyyyMMddHHmmssSSS");
		}
		return null;
	}

	public static Date getDayBefore(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, -1);
		date = calendar.getTime();
		return date;
	}

	public static Date parse(String date, String type) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String addSeconds(String yyyyMMddHHmmssDate, int second) {
		Date d = parseDateTime(yyyyMMddHHmmssDate);
		Date d2 = org.apache.commons.lang.time.DateUtils.addSeconds(d, second);
		String d3 = formatDate(d2);
		return d3;
	}

	public static Timestamp stringToTimestamp(String timestampStr, String format) {
		if ((timestampStr == null) || (timestampStr.trim().equals(""))) {
			return null;
		}
		if ((null == format) || ("".equals(format))) {
			format = "yyyyMMddHHmmss";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(timestampStr);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String timestamptoString(Timestamp timestamp, String format) {
		if (timestamp == null) {
			return null;
		}
		if (null == format) {
			format = "yyyyMMddHHmmss";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(timestamp);
	}

	public static Date getSysdate() {
		return new Date();
	}

	public static Timestamp getSysTime() {
		String mDateTime = MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", new Object[] { getSysdate() });
		return Timestamp.valueOf(mDateTime);
	}

	public static Timestamp getAddSeconds(String lifeTime) {
		long addSeconds = Long.parseLong(lifeTime);
		return new Timestamp(new Date().getTime() + addSeconds * 1000L);
	}

	public static Timestamp getCurrentTimestamp2() {
		return new Timestamp(new Date().getTime());
	}

	public static Date getdateAdded(Date date, int type, int i) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(type, i);
		return c.getTime();
	}

	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String format(Date date, String type) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return sdf.format(date);
	}

	public static String getFirstDayOfMonth() {
		return getFirstDayOfMonth(getSysdate());
	}

	public static String getFirstDayOfMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String theday = sdf.format(Long.valueOf(date.getTime()));
		String[] thedays = theday.split("-");
		return new StringBuilder().append(thedays[0]).append("-").append(thedays[1]).append("-01").toString();
	}

	public static String getLastDayOfMonth() {
		return getLastDayOfMonth(getSysdate());
	}

	public static String getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int max = c.getActualMaximum(5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String theday = sdf.format(Long.valueOf(date.getTime()));
		String[] thedays = theday.split("-");
		return new StringBuilder().append(thedays[0]).append("-").append(thedays[1])
				.append(max >= 10 ? new StringBuilder().append("-").append(max).toString()
						: new StringBuilder().append("-0").append(max).toString())
				.toString();
	}

	public static String getFirstDayOfWeek() {
		return getFirstDayOfWeek(getSysdate());
	}

	public static String getFirstDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(2);
		c.set(7, c.getFirstDayOfWeek());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	public static Date parse(String date) {
		return parse(date, "yyyy-MM-dd");
	}

	public static Date parseDateTime2(String date) {
		return parse(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static Timestamp parseTime(String date) {
		String mDateTime = MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", new Object[] { parseDateTime2(date) });
		return Timestamp.valueOf(mDateTime);
	}

	public static String formatterTime(Timestamp date) {
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null)
			str = df.format(date);
		return str;
	}


	public static String getWeekFromDate(Date date) {
		String[] weekDaysCode = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(7) - 1;
		return weekDaysCode[index];
	}

	/**
	 * 根据日期字符串判断当月第几周
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static int getWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//第几周
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
//		//第几天，从周日开始
//		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return week;
	}


	public static String getYearFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String index = new StringBuilder().append("").append(calendar.get(1)).toString();
		return index;
	}

	public static String getMonthFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String index = new StringBuilder().append("").append(calendar.get(2) + 1).toString();
		return index;
	}

	public static long getLeftSecondsOfDay() {
		long currTime = System.currentTimeMillis();
		Date midNight = parseDateTime2(formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
		return (midNight.getTime() - currTime) / 1000L;
	}

	public static String getSpecifiedDayBefore(String specifiedDay){
		Calendar c = Calendar.getInstance();
		Date date=null;
		try {
			date = dateFormat.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day-1);

		String dayBefore = dateFormat.format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获取当前天，格式 "yyyyMMdd"
	 *
	 * @return 时间字符串
	 */
	public static String getCurrentDay() {
		Date date = new Date();
		return format.format(date);
	}

	/**
	 * 获取当前天，格式 "yyyyMMdd"
	 *
	 * @return 时间字符串
	 */
	public static String getyyyyMMddCurrentDay() {
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * 计算两天的天数时间差
	 *
	 * */
	public static int between2day(Date fromDay, Date toDay){
		int result = 0;
		try{
			String fromDate = dateFormat.format(fromDay);
			String toDate = dateFormat.format(toDay);
			long from = dateFormat.parse(fromDate).getTime();
			long to = dateFormat.parse(toDate).getTime();
			result = (int) ((to - from)/(1000 * 60 * 60 * 24));
		}catch (Exception e){
			System.out.print("error");
		}
		return result;
	}

	/**
	 * 计算两天的天数时间差
	 *
	 * */
	public static int between2day(long fromTime, long toTime){
		String d = format.format(fromTime);
		String t = format.format(toTime);
		int result = 0;
		try{
			Date fromDay = format.parse(d);
			Date toDay = format.parse(t);
			String fromDate = dateFormat.format(fromDay);
			String toDate = dateFormat.format(toDay);
			long from = dateFormat.parse(fromDate).getTime();
			long to = dateFormat.parse(toDate).getTime();
			result = (int) ((to - from)/(1000 * 60 * 60 * 24));
		}catch (Exception e){
			System.out.print("error");
		}
		return result;
	}

	/**
	 * 计算两天的天数时间差
	 *
	 * */
	public static int between2dayWithMinute(long fromTime, long toTime){
		String d = format.format(fromTime);
		String t = format.format(toTime);
		int result = 0;
		try{
			Date fromDay = format.parse(d);
			Date toDay = format.parse(t);
			String fromDate = dateFormat.format(fromDay);
			String toDate = dateFormat.format(toDay);
			long from = dateFormat.parse(fromDate).getTime();
			long to = dateFormat.parse(toDate).getTime();
			result = (int) ((to - from)/(1000 * 60));
		}catch (Exception e){
			System.out.print("error");
		}
		return result;
	}

	/**
	 * 判断时间是不是今天
	 * @param date
	 * @return    是返回true，不是返回false
	 */
	public static boolean isToday(Date date) {
		//当前时间
		Date now = new Date();
		//获取今天的日期
		String nowDay = dateFormat.format(now);
		//对比的时间
		String day = dateFormat.format(date);
		return day.equals(nowDay);
	}

	/**
	 * 判断是否是周末
	 *
	 * */
	public static boolean isWeekend(String date){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date bdate = format1.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(bdate);
			if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
				return true;
			}else return false;
		}catch (Exception e){
			System.out.print(e.getMessage());
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		boolean isWeekend = DateUtils.isWeekend(DateUtils.format(new Date()));
		System.out.print(isWeekend);
	}

	public static int  compareTime(String dateStr1,String dateStr2){
		String[] str1Arr = dateStr1.split(":");
		String[] str2Arr = dateStr2.split(":");

		if(Integer.parseInt(str1Arr[0]) > Integer.parseInt(str2Arr[0])){
			return 1;
		}if(Integer.parseInt(str1Arr[0]) == Integer.parseInt(str2Arr[0])){

			if(Integer.parseInt(str1Arr[1]) > Integer.parseInt(str2Arr[1])){
				return 1;
			}else if(Integer.parseInt(str1Arr[1]) == Integer.parseInt(str2Arr[1])){
				return 0;
			}else{
				return -1;
			}
		}else{

			return -1;
		}
	}

	public static String getBeforDate(int days){
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		date = calendar.getTime();
		return format.format(date);
	}

	public String paresDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return String.valueOf(sdf.parse(dateStr));
	}


	public static String getTimeInterval(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String imptimeBegin = format.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = format.format(cal.getTime());
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeBegin + "," + imptimeEnd;
	}

	public static List<Date> findDates(String start_time, String end_time){
		try {
			Date dBegin = format.parse(start_time);
			Date dEnd = format.parse(end_time);
			List lDate = new ArrayList();
			lDate.add(dBegin);
			Calendar calBegin = Calendar.getInstance();
			// 使用给定的 Date 设置此 Calendar 的时间
			calBegin.setTime(dBegin);
			Calendar calEnd = Calendar.getInstance();
			// 使用给定的 Date 设置此 Calendar 的时间
			calEnd.setTime(dEnd);
			// 测试此日期是否在指定日期之后
			while (dEnd.after(calBegin.getTime()))
			{
				// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
				calBegin.add(Calendar.DAY_OF_MONTH, 1);
				lDate.add(calBegin.getTime());
			}
			return lDate;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


}
