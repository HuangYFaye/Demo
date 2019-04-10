/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.gupaoedu.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyy"};

    /**
     * 获得某天最大时间 2018-12-25 23:59:59
     *
     * @param date Date对象
     * @return java.util.Date
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2018-12-25 00:00:00
     *
     * @param date Date对象
     * @return java.util.Date
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyyMMdd）
     */
    public static String getDateYYYYMMDD() {
        return getDate("yyyyMMdd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        if (date == null) { return ""; }
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期字符串格式（yyyyMMdd）,如果date为null，返回当前日期
     */
    public static String formatDate(Date date) {
        if (date == null) { return ""; }
        return DateFormatUtils.format(date, "yyyyMMdd");
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

    public static String getDateTimeStr() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss").replaceAll("-|:| ", "");
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
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
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

    public static Date fromStrToDate(String str, String dateType) throws ParseException {
        SimpleDateFormat time = new SimpleDateFormat(dateType);
        return time.parse(str);
    }

    /**
     * 获取年月日时分秒毫秒的字符串
     * add by zhengwei359  20170509
     *
     * @return String
     */
    public static String getDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * 根据传入的参数i获取i年前的年份
     * add by zhengwei359  20170510
     *
     * @return String
     */
    public static String getYear(Integer i) {
        String year = formatDate(new Date(), "yyyy");
        int j = Integer.valueOf(year) - i;
        return String.valueOf(j);
    }

    /**
     * 获取过去的天数
     *
     * @param date Date对象
     * @return long
     */
    public static long pastDays(Date date) {
        // 因为new Date().getTime()底层也是调用System.currentTimeMillis()，
        // 所以直接使用System.currentTimeMillis()效率会高点
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date Date对象
     * @return long
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date Date对象
     * @return long
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis 毫秒
     * @return String
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
     * @param before 从哪天
     * @param after  到哪天
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     *
     *
     * @param currDate
     * @param term
     * @param termUnit
     * @return
     */
    public static Date getExpirationDate(Date currDate, Integer term, String termUnit) {
        int unit = Integer.parseInt(termUnit);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTime(currDate);
        switch (unit) {
            //周
            case 1:
                expirationDate.add(Calendar.WEEK_OF_YEAR, term);
                break;
            //双周
            case 2:
                expirationDate.add(Calendar.WEEK_OF_YEAR, 2 * term);
                break;
            //月
            case 3:
                expirationDate.add(Calendar.MONTH, term);
                break;
            //双月
            case 4:
                expirationDate.add(Calendar.MONTH, 2 * term);
                break;
            //季
            case 5:
                expirationDate.add(Calendar.MONTH, 3 * term);
                break;
            //天
            default:
                expirationDate.add(Calendar.DAY_OF_YEAR, term);
                break;
        }
        return expirationDate.getTime();
    }

    /**
     * 两个日期相减，返回年（去尾）
     * 比如10.4，10.9，只返回10
     *
     * @param before 从哪天
     * @param after  到哪天
     * @return
     */
    public static int getYearOfTwoDate(Date before, Date after) {
        double d = getDistanceOfTwoDate(before, after);
        Double dd = new Double(d / 365);
        return dd.intValue();
    }

    /***
     * 获取前days天的时间
     * @param date
     * @param days
     * @return
     */
    public static Date getlastDayBefore(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, -days);
        return cal.getTime();
    }

    /**
     * 获取当年的第一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取当前月的第一天
     *
     * @return date
     */
    public static Date getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date getFirstDayTimeOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getFirstDayOfOneYearAgo() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -11);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /***
     * 获取前months月的时间
     * @param date
     * @param months
     * @return
     */
    public static Date getlastMonthBefore(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -months);
        return cal.getTime();
    }

    /***
     * 获取日期
     * @param dateStr 字符串类型的日期
     * @return
     */
    public static Date getFromatDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        try {
            return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            return null;
        }
    }

    public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));

//		Date a = new Date();
//		Date b1 = new Date(2006-1900, 11, 30);
//		Date b2 = new Date(2006-1900, 5, 25);
//		double d1 = getDistanceOfTwoDate(b1, a);
//		double d2 = getDistanceOfTwoDate(b2, a);
//		System.out.println(d1);
//		System.out.println(d2);
//		
//		System.out.println(d1/365);
//		System.out.println(d2/365);
//		
//		int d3 = getYearOfTwoDate(b1, a);
//		int d4 = getYearOfTwoDate(b2, a);
//		System.out.println(d3);
//		System.out.println(d4);
    }
}
