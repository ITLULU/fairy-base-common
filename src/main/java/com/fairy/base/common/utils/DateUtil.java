package com.fairy.base.common.utils;

import com.fairy.base.common.exception.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 日期时间工具类
 *
 * @author huanglulu
 * @version 1.0
 * @date 2022/4/11 10:31
 */
@Slf4j
public class DateUtil {
    /**
     * 日期格式转换
     */
    public static final String format = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss.SSS)
     */
    public final static String TIME_PATTERN_ALL = "yyyy-MM-dd HH:mm:ss.SSS ";
    /**
     * 转换数字格式
     */
    public final static String NUMBER_PATTERN_ALL = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式转换为字符串
     *
     * @param date 日期
     * @param form 格式
     * @return java.lang.String
     **/
    public static String parseDate(Date date, String form) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formater = new SimpleDateFormat(form);
        return formater.format(date);
    }


    /**
     * 格式日期转换日期
     *
     * @param dateStr 字符串
     * @param form    转换格式
     * @return java.util.Date
     **/
    public static Date parseDate(String dateStr, String form) {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(form);//可以方便地修改日期格式
            Date date = dateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw ResultException.create(e.getMessage());
        }
    }


    /**
     * 当前日期date 减去几分钟得到的日期
     *
     * @param date   日期
     * @param minute minute
     * @return java.util.Date
     **/
    public static Date subMinute(Date date, Integer minute) {
        Date before = new Date(date.getTime() - minute * 60000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -minute * 1);
        Date b = cal.getTime();
        return before;
    }


    /**
     * 当前日期date 加上几天得到的日期
     *
     * @param date   date
     * @param minute minute
     * @return java.util.Date
     **/
    public static Date plusMinute(Date date, Integer minute) {
        Date after = new Date(date.getTime() + minute * 60000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute * 1);
        Date b = cal.getTime();
        return after;
    }

    /**
     * 当前日期date 减去几天得到的日期
     *
     * @param date  date
     * @param value value
     * @return java.util.Date
     **/
    public static Date subDate(Date date, Integer value) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, -1 * value);
        return ca.getTime();
    }


    /**
     * 计算时间之间差
     *
     * @param end   end
     * @param start start
     * @return long
     **/
    public static long getDateBetweenTime(Date end, Date start) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        long startTime = calendar.getTimeInMillis();
        calendar.setTime(end);
        long endtime = calendar.getTimeInMillis();
        return endtime - startTime;
    }

    /**
     * 日期时间 前几小时
     *
     * @param date date
     * @param hour hour
     * @return java.util.Date
     **/
    public static Date subHour(Date date, Integer hour) {
        Date before = new Date(date.getTime() - hour * 60000 * 60);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -hour * 60);
        Date b = cal.getTime();
        return before;
    }

    /**
     * 日期比较
     *
     * @param date  date
     * @param date1 date1
     * @return int
     **/
    public static int compareDate(Date date, Date date1) {
        return date.compareTo(date1);
    }

    /**
     * 检查日期类型
     *
     * @param date    date
     * @param pattern pattern
     * @return boolean
     **/
    public static boolean checkDateType(String date, String pattern) {
        try {
            if (StringUtils.isEmpty(date)) {
                return true;
            }
            //可以方便地修改日期格式
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime.parse(date, dateFormat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将java.util.Date 转换为java8 的java.time.LocalDateTime,默认时区为东8区
     *
     * @param date date
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime dateConvertToLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }

    /**
     * 将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
     *
     * @param localDateTime localDateTime
     * @return java.util.Date
     **/
    public static Date localDateTimeConvertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

    /**
     * 获取data的时间戳
     *
     * @param date date
     * @return java.lang.Long
     **/
    public static Long getLocalDateTimeMillSeconds(LocalDateTime date) {
        return date.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * localDateTime 转换
     *
     * @param localDateTime localDateTime
     * @param pattern       pattern
     * @return java.lang.String
     **/
    public static String localDateForm(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String dateTime = localDateTime.format(formatter);
        return dateTime;
    }

    /**
     * 当前时间秒数
     *
     * @param date date
     * @return java.lang.Long
     **/
    public static Long getLocalDateTimeSeconds(LocalDateTime date) {
        return date.toEpochSecond(ZoneOffset.of("+8"));
    }


    /**
     * 当前时间的后几分钟
     *
     * @param date   date
     * @param minute minute
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime plusMinute(LocalDateTime date, Integer minute) {
        return date.plusMinutes(minute);
    }

    /**
     * 当前时间的前几分钟
     *
     * @param date   date
     * @param minute minute
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime minusMinute(LocalDateTime date, Integer minute) {
        return date.minusSeconds(minute);
    }


    /**
     * 得到年份
     *
     * @param date int
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 得到月份
     *
     * @param date 日期
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 得到当天是这一年的那一天
     *
     * @param date 日期
     * @return int
     */
    public static int getDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int date2 = calendar.get(Calendar.DAY_OF_YEAR);
        return date2;
    }


    public static void main(String[] args) {

        Map<String,Object>map = new HashMap<>();
        System.out.println((String)map.get("ri"));
    }
}
