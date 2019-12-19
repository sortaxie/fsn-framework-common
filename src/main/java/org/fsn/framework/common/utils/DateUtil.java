package org.fsn.framework.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Classname: DateUtil
 * @Description: 时间格式化类
 * @Author: 熊俊堡
 * @Date: 2019/09/12 10:10
 * @Version: 1.0
 */
public class DateUtil {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter PATTERN_YMD_HMS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter Y_M_D_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter YM = DateTimeFormatter.ofPattern("yyyyMM");

    /**
     * 格式化时间 yyyy-MM-dd HH:mm:ss 转换 Date
     * @param var
     * @return
     */
    public static Date transformYmdHms(String var) {
        LocalDateTime localDateTime = LocalDateTime.parse(var, PATTERN);
        return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
    }

    /**
     * 生成年月
     * @return
     */
    public static String ym() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(YM);
    }

    /**
     * 格式化时间 yyyyMMddHHmmss 转换 Date
     * @param var
     * @return
     */
    public static Date pattern_ymd_hms(String var) {
        LocalDateTime localDateTime = LocalDateTime.parse(var, PATTERN_YMD_HMS);
        return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
    }

    /**
     * 生成年月日
     * @return
     */
    public static String ymd() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * 生成年月日时分秒
     * @return
     */
    public static String ymdHms() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(PATTERN_YMD_HMS);
    }

    /**
     * date转字符串
     * @param date
     * @param type true 代表转换 yyyy-MM-dd
     *             false 代表转换 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String time(Date date, boolean type) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE_ID);
        if (type) {
            return Y_M_D_FORMAT.format(localDateTime);
        }
        return PATTERN.format(localDateTime);
    }

    /**
     * Y_M_d
     * @return
     */
    public static String time() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(Y_M_D_FORMAT);
    }

    /**
     * 获取下月时间 年月
     * @month 增加(减少)月份
     * @return
     */
    public static String getNextMonth(int month) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.plusMonths(month);
        String format = yesterday.format(YM);
        return format;
    }

    public static Date plusDays(long days) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime minusDays = today.plusDays(days);
        return Date.from(minusDays.atZone(ZONE_ID).toInstant());
    }

    private static final DateTimeFormatter HOUR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String hour() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(HOUR);
    }

    public static String plusMinutes(long minute) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime minusDays = today.plusMinutes(minute);
        return minusDays.format(HOUR);
    }



    public static String plusMinutesHms(long minute) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime minusDays = today.plusMinutes(minute);
        return minusDays.format(PATTERN);
    }
}

