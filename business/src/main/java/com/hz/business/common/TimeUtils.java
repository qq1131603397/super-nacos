package com.hz.business.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author： pt
 * @date： 2023/6/28 11:10
 * @discription
 */
public class TimeUtils {
    public static final long SECOND_MILLISECOND = 1000L;
    public static final long MINUTE_MILLISECOND = 60000L;
    public static final long HOUR_MILLISECOND = 3600000L;
    public static final long DAY_MILLISECOND = 86400000L;
    public static final String DEFAULT_TPL = "yyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";

    public TimeUtils() {
    }

    public static String format(long timeStamp, String pattern) {
        DateTime dateTime = new DateTime(timeStamp);
        return dateTime.toString(pattern);
    }

    public static String format(DateTime dateTime, String pattern) {
        return dateTime.toString(pattern);
    }

    public static String getNowDefault() {
        return format(System.currentTimeMillis(), "yyy-MM-dd HH:mm:ss");
    }

    public static long getDayOfZero() {
        DateTime currentDateTime = new DateTime();
        DateTime zero = currentDateTime.withMillisOfDay(0);
        return zero.getMillis();
    }

    public static long getDayOfZero(long time) {
        DateTime currentDateTime = new DateTime(time);
        DateTime zero = currentDateTime.withMillisOfDay(0);
        return zero.getMillis();
    }

    public static long getDayOfEnd() {
        return getDayOfZero() + 86400000L - 1L;
    }

    public static long getDayOfNoon() {
        DateTime nowTime = new DateTime();
        DateTime startOfDay = nowTime.withTimeAtStartOfDay();
        return startOfDay.getMillis() + 43200000L;
    }

    public static long converTime(String time, String pattern) {
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = DateTime.parse(time, format);
        return dateTime.getMillis();
    }

    public static long converDefaultTime(String time) {
        return converTime(time, "yyy-MM-dd HH:mm:ss");
    }

    public static void setDefaultTimeZone(String zoneId) {
        DateTimeZone.setDefault(DateTimeZone.forID(zoneId));
    }

    public static String getDeltaTime(long startTime, long endTime) {
        Interval interval = new Interval(startTime, endTime);
        Period period = interval.toPeriod();
        StringBuilder builder = new StringBuilder();
        builder.append(period.getYears());
        builder.append("年");
        builder.append(period.getMonths());
        builder.append("月");
        builder.append(period.getDays());
        builder.append("天");
        builder.append(period.getHours());
        builder.append("小时");
        builder.append(period.getMinutes());
        builder.append("分");
        builder.append(period.getSeconds());
        builder.append("秒");
        return builder.toString();
    }

    public static String getDeltaTimeByHour(long startTime, long endTime) {
        Interval interval = new Interval(startTime, endTime);
        Period period = interval.toPeriod();
        StringBuilder builder = new StringBuilder();
        long hour = (endTime - startTime) / 3600000L;
        builder.append(hour);
        builder.append("小时");
        builder.append(period.getMinutes());
        builder.append("分");
        builder.append(period.getSeconds());
        builder.append("秒");
        return builder.toString();
    }

    public static String getDeltaTimeByHour(long delta) {
        Period period = new Period(delta);
        StringBuilder builder = new StringBuilder();
        long hour = delta / 3600000L;
        builder.append(hour);
        builder.append("小时");
        builder.append(period.getMinutes());
        builder.append("分");
        builder.append(period.getSeconds());
        builder.append("秒");
        return builder.toString();
    }

    public static String getDeltaFormatTime(long delta) {
        Period period = new Period(delta);
        StringBuilder builder = new StringBuilder();
        long day = delta / 86400000L;
        if (day > 0L) {
            builder.append(day);
            builder.append("天");
            period = new Period(delta - day * 86400000L);
        }

        if (period.getHours() > 0 || day > 0L) {
            builder.append(period.getHours());
            builder.append("小时");
        }

        if (period.getMinutes() > 0 || day > 0L || period.getHours() > 0) {
            builder.append(period.getMinutes());
            builder.append("分");
        }

        builder.append(period.getSeconds());
        builder.append("秒");
        return builder.toString();
    }

    public static Long getAlertTime(String content) {
        try {
            String temp = content.substring(0, 19);
            return converTime(temp, "MM/dd/yyy-HH:mm:ss");
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static long getNowSecond() {
        return System.currentTimeMillis() / 1000L;
    }

    public static long getPrevDayZero(int days) {
        DateTime today = new DateTime(getDayOfZero());
        DateTime beforeDay = today.minusDays(days);
        return beforeDay.getMillis();
    }

    public static long getPrevDayZeroMonth(int months) {
        DateTime today = new DateTime(getDayOfZero());
        DateTime beforeDay = today.minusMonths(months);
        return beforeDay.getMillis();
    }

    public static long getPrevMonthZero(int month) {
        DateTime dateTime = new DateTime();
        DateTime theFirstDateOfMonth = dateTime.minusMonths(month).dayOfMonth().withMinimumValue().withMillisOfDay(0);
        return theFirstDateOfMonth.getMillis();
    }

    public static long getPrevMonthEnd(int month) {
        DateTime dateTime = new DateTime();
        DateTime theFirstDateOfMonth = dateTime.minusMonths(month).dayOfMonth().withMaximumValue().withMillisOfDay(0);
        return theFirstDateOfMonth.getMillis() + 86400000L - 1L;
    }

}
