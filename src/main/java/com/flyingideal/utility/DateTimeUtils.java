package com.flyingideal.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * @author yanchao
 * @dateTime 2018-2-28 23:43:43
 */
public class DateTimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    /**
     * 获取给定格式的DateTimeFormatter对象，且日期验证为非宽松模式
     * 注意：java8中的年应该使用uuuu，而不是yyyy，否则会报错
     * @param pattern
     * @return
     */
    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        if (pattern == null || pattern.trim().length() == 0) {
            logger.error("日期格式不允许为空");
            throw new IllegalArgumentException("日期格式不允许为空");
        }
        return DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
    }

    /**
     * 判断给定的日期格式字符串是否合法
     * @param dateString 日期字符串
     * @param pattern 日期格式
     * @return true：合法的日期格式字符串；false：非法的日期格式字符串
     */
    public static boolean isValidDate(String dateString, String pattern) {
        if (dateString == null || dateString.trim().length() == 0) {
            return false;
        }
        boolean b = true;
        try {
            DateTimeFormatter formatter = getDateTimeFormatter(pattern);
            LocalDate.parse(dateString, formatter);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            b = false;
        }
        return b;
    }

    /**
     * 判断给定的日期时间格式字符串是否合法
     * @param dateTimeString 日期时间字符串
     * @param pattern 日期时间格式
     * @return true：合法的日期时间格式字符串；false：非法的日期时间格式字符串
     */
    public static boolean isValidDateTime(String dateTimeString, String pattern) {
        if (dateTimeString == null || dateTimeString.trim().length() == 0) {
            return false;
        }
        boolean b = true;
        try {
            DateTimeFormatter formatter = getDateTimeFormatter(pattern);
            LocalDateTime.parse(dateTimeString, formatter);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            b = false;
        }
        return b;
    }

    /**
     * java7及以下版本的日期或日期时间格式字符串验证
     * @param dateTimeString 日期时间字符串
     * @param pattern 日期时间格式
     * @return true：合法的日期时间格式字符串；false：非法的日期时间格式字符串
     */
    public static boolean isValidDateTimeForLowerJavaVersion(String dateTimeString, String pattern) {
        if (dateTimeString == null || dateTimeString.trim().length() == 0) {
            return false;
        }
        boolean b = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            b = false;
        }
        return b;
    }
}
