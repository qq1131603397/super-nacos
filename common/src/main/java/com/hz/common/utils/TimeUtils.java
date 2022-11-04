package com.hz.common.utils;

import org.joda.time.DateTime;

/**
 * @author： pt
 * @date： 2022/10/13 16:40
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

    public static String format(long timeStamp, String pattern) {
        DateTime dateTime = new DateTime(timeStamp);
        return dateTime.toString(pattern);
    }

}
