package com.ivo.activiticlient.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class DateUtil {

    public static String DatePattern = "yyyy-MM-dd";

    public static String DateTimePattern = "yyyy-MM-dd HH:mm";

    public static Date parseDate(String source) {
        return parse(source, DatePattern);
    }

    public static Date parseDateTime(String source) {
        if(source.trim().length() == 10) source = source + " 00:00";
        return parse(source, DateTimePattern);
    }

    public static Date parse(String source, String patten) {
        if (StringUtil.isEmpty(source))
            return null;
        DateFormat format;
        try {
            format = new SimpleDateFormat(patten);
        } catch (Exception e) {
            format = new SimpleDateFormat(DateTimePattern);
        }
        Date date;
        try {
            date = format.parse(source);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public static String formatDate(Date date) {
        return format(date, DatePattern);
    }

    public static String formatDateTime(Date date) {
        return format(date, DateTimePattern);
    }

    public static String format(Date date, String patten) {
        if (date == null)
            return "";
        DateFormat format;
        try {
            format = new SimpleDateFormat(patten);
        } catch (Exception e) {
            format = new SimpleDateFormat(DateTimePattern);
        }
        return format.format(date);
    }

    public static String getFormatBeforeOrAfterDate(int offset) {
        return getFormatBeforeOrAfterDay(offset, DatePattern);
    }

    public static String getFormatBeforeOrAfterDateTime(int offset) {
        return getFormatBeforeOrAfterDay(offset, DateTimePattern);
    }

    public static String getFormatBeforeOrAfterDay(int offset, String patten) {
        return format(getBeforeOrAfterDay(offset), patten);
    }

    public static Date getBeforeOrAfterDay(int offset) {
        if (offset == 0)
            return new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, offset);
        return c.getTime();
    }

    public static int getYear() {
        return getYear(new Date());
    }

    public static int getMonth() {
        return getMonth(new Date());
    }

    public static int getDayOfWeek() {
        return getDayOfWeek(new Date());
    }

    public static int getDayOfMonth() {
        return getDayOfMonth(new Date());
    }

    public static int getDayOfYear() {
        return getDayOfYear(new Date());
    }

    public static int getYear(Date date) {
        return get(date, Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        return get(date, Calendar.MONTH) + 1;
    }

    public static int getDayOfWeek(Date date) {
        int day = get(date, Calendar.DAY_OF_WEEK);
        return day == 0 ? 7 : get(date, Calendar.DAY_OF_WEEK) - 1;
    }

    public static int getDayOfMonth(Date date) {
        return get(date, Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfYear(Date date) {
        return get(date, Calendar.DAY_OF_YEAR);
    }

    private static Calendar getCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null)
            c.setTime(date);
        return c;
    }

    private static int get(Date date, int field) {
        Calendar c = getCalendar(date);
        return c.get(field);
    }

    public static boolean compareDateTime(String s1, String s2) {
        return compare(parseDateTime(s1), parseDateTime(s2));
    }

    public static boolean compareDateTime(String s, Date date) {
        return compare(parseDateTime(s), date);
    }

    public static boolean compareDateTime(Date date, String s) {
        return compare(date, parseDateTime(s));
    }

    public static boolean compareDate(String s1, String s2) {
        return compare(parseDate(s1), parseDate(s2));
    }

    public static boolean compareDate(String s, Date date) {
        return compare(parseDate(s), date);
    }

    public static boolean compareDate(Date date, String s) {
        return compare(date, parseDate(s));
    }

    public static boolean compare(String s1, String patten1, String s2,
                                  String patten2) {
        return compare(parse(s1, patten1), parse(s2, patten2));
    }

    public static boolean compare(String s, String patten, Date date) {
        return compare(parse(s, patten), date);
    }

    public static boolean compare(Date date, String s, String patten) {
        return compare(date, parse(s, patten));
    }

    public static boolean compare(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            throw new IllegalArgumentException();
        return d1.after(d2);
    }

    //取得两个日期的差额天数
    public static long getDiffDays (String strdate1,String strdate2){
        long diff = 0;
        try{
            Date date1 = parseDate(strdate1);
            Date date2 = parseDate(strdate2);
            diff = date2.getTime() - date1.getTime();
            diff = diff/1000/60/60/24;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return diff;
    }

    //结算周期开始日期
    public static String getFromDate(String date){
        return getFromDate(parseDate(date));
    }

    //结算周期开始日期
    public static String getFromDate(Date date){
        String fromDate = "";
        int year = getYear(date);
        int month = getMonth(date);
        int day = getDayOfMonth(date);
        if (day <= 25) {
            if(month == 1){
                month =12;
                year = year-1;
            }
            else month = month-1;
        }
        if(month < 10)
            fromDate = year + "-0" + month + "-" + "26";
        else
            fromDate = year + "-" + month + "-" + "26";

        return fromDate;
    }

    //结算周期结算日期
    public static String getEndDate(Date date){
        String monthOfThis = getMonthOfThis(formatDate(date));
        String endDate = monthOfThis.substring(0, 8)+"25";
        return endDate;
    }

    //获取结算月
    public static String getMonthOfThis(String date){
        String monthOfThis = "";
        Date date_ = parseDate(date);
        int year = getYear(date_);
        int month = getMonth(date_);
        int day = getDayOfMonth(date_);
        if (day > 25) {
            if(month == 12){
                month =1;
                year = year + 1;
            }
            else month = month+1;
        }
        if(month < 10)
            monthOfThis = year + "-0" + month + "-" + "01";
        else
            monthOfThis = year + "-" + month + "-" + "01";

        return monthOfThis;
    }

    /**
     * 获得指定日期的后一天
     * @param specifiedDay
     * @return
     */
    public static String getNextDay(String date){
        Calendar c = Calendar.getInstance();
        Date date_ = parseDate(date);
        c.setTime(date_);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);
        return formatDate(c.getTime());
    }

    /**
     * 获得指定日期的前一天
     * @param specifiedDay
     * @return
     */
    public static String getlastDay(String date){
        Calendar c = Calendar.getInstance();
        Date date_ = parseDate(date);
        c.setTime(date_);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);
        return formatDate(c.getTime());
    }

    /**
     * 获得指定日期的前一个月
     * @param specifiedDay
     * @return
     */
    public static String getlastMonth(String date){
        Calendar c = Calendar.getInstance();
        Date date_ = parseDate(date);
        c.setTime(date_);
        c.add(Calendar.MONTH, -1);

        return formatDate(c.getTime());
    }

    /**
     * 获得某日期相差指定天数的日期
     * @param specifiedDay
     * @return
     */
    public static String getDateByN(String date,int n){
        Calendar c = Calendar.getInstance();
        Date date_ = parseDate(date);
        c.setTime(date_);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+n);
        return formatDate(c.getTime());
    }

    /**
     * 获得指定日期的前一周
     * @param specifiedDay
     * @return
     */
    public static String getlastWeek(String date){
        Calendar c = Calendar.getInstance();
        Date date_ = parseDate(date);
        c.setTime(date_);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-7);
        return formatDate(c.getTime());
    }
}

