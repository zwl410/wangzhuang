package com.zhang.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理工具类
 * Created by 张帅领 on 2017/4/12
 *
 * @author dylan_xu
 * @date Mar 4, 2017
 * @modified by
 * @modified date
 * @since JDK1.7
 */
public class DateUtil extends cn.hutool.core.date.DateUtil {
    private static final Log logger = LogFactory.getLog(DateUtil.class);
    private static String defaultDatePattern = null;
    private static String timePattern = "HH:mm";
    private static Calendar cale = Calendar.getInstance();
    public static final String TS_FORMAT = DateUtil.getDatePattern() + " HH:mm:ss.S";
    /**
     * 日期格式yyyy-MM字符串常量
     */
    private static final String MONTH_FORMAT = "yyyy-MM";
    /**
     * 日期格式yyyy-MM-dd字符串常量
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期格式HH:mm:ss字符串常量
     */
    private static final String HOUR_FORMAT = "HH:mm:ss";
    /**
     * 日期格式yyyy-MM-dd HH:mm:ss字符串常量
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式yyyy-MM-dd HH:mm:ss:SSS字符串常量
     */
    private static final String DATETIMEMS_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * 日期格式yyyy-MM-dd HH:mm:ss:SSS字符串常量
     */
    private static final String DATETIMEMS_FORMAT2 = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 某天开始时分秒字符串常量 00:00:00
     */
    private static final String DAY_BEGIN_STRING_HHMMSS = " 00:00:00";
    /**
     * 某天结束时分秒字符串常量 23:59:59
     */
    public static final String DAY_END_STRING_HHMMSS = " 23:59:59";
    private static SimpleDateFormat sdf_date_format = new SimpleDateFormat(DATE_FORMAT);
    private static SimpleDateFormat sdf_hour_format = new SimpleDateFormat(HOUR_FORMAT);
    private static SimpleDateFormat sdf_datetime_format = new SimpleDateFormat(DATETIME_FORMAT);
    private static SimpleDateFormat sdf_datetimems_format = new SimpleDateFormat(DATETIMEMS_FORMAT);

    private static SimpleDateFormat sdf_datetimems_format2 = new SimpleDateFormat(DATETIMEMS_FORMAT2);


    // ~ Methods
    // ================================================================

    public DateUtil() {
    }

    /**
     * 获得服务器当前日期及时间及毫秒数，以格式为：yyyy-MM-dd HH:mm:ss:SSS的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getDateTimeMs() {
        try {
            return sdf_datetimems_format.format(new Date());
        } catch (Exception e) {
            logger.debug("DateUtil.getDateTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期及时间及毫秒数，以格式为：yyyy-MM-dd HH:mm:ss.SSS的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getDateTimeMs2() {
        try {
            return sdf_datetimems_format2.format(new Date());
        } catch (Exception e) {
            logger.debug("DateUtil.getDateTime():" + e.getMessage());
            return "";
        }
    }


    /**
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getDateTime() {
        try {
            return sdf_datetime_format.format(new Date());
        } catch (Exception e) {
            logger.debug("DateUtil.getDateTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getDate() {
        try {
            return sdf_date_format.format(new Date());
        } catch (Exception e) {
            logger.debug("DateUtil.getDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getTime() {
        String temp = " ";
        try {
            temp += sdf_hour_format.format(new Date());
            return temp;
        } catch (Exception e) {
            logger.debug("DateUtil.getTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 统计时开始日期的默认值
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getStartDate() {
        try {
            return getYear() + "-01-01";
        } catch (Exception e) {
            logger.debug("DateUtil.getStartDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 统计时结束日期的默认值
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getEndDate() {
        try {
            return getDate();
        } catch (Exception e) {
            logger.debug("DateUtil.getEndDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期的年份
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getYear() {
        try {
            return String.valueOf(cale.get(Calendar.YEAR));
        } catch (Exception e) {
            logger.debug("DateUtil.getYear():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期的月份
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getMonth() {
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.applyPattern("00;00");
            return df.format((cale.get(Calendar.MONTH) + 1));
        } catch (Exception e) {
            logger.debug("DateUtil.getMonth():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器在当前月中天数
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getDay() {
        try {
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            logger.debug("DateUtil.getDay():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器在当前小时
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static int getHour() {
        try {
            return Integer.valueOf(cale.get(Calendar.HOUR_OF_DAY));
        } catch (Exception e) {
            logger.debug("DateUtil.getDay():" + e.getMessage());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static int getMargin(String date1, String date2) {
        int margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf_date_format.parse(date1, pos);
            Date dt2 = sdf_date_format.parse(date2, pos1);
            long l = dt2.getTime() - dt1.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的小时数
     *
     * @param date1
     * @param date2
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static int getHoursMargin(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;
        try {
            cal.setTime(sdf.parse(date1));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date2));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long between_days = (time2 - time1) / (1000 * 3600);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 比较两个日期相差的分钟数
     *
     * @param date1
     * @param date2
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static double getMinuteMargin(String date1, String date2) {
        double margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf_datetime_format.parse(date1, pos);
            Date dt2 = sdf_datetime_format.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (l / (60 * 1000.00));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static double getDoubleMargin(String date1, String date2) {
        double margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf_datetime_format.parse(date1, pos);
            Date dt2 = sdf_datetime_format.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (l / (24 * 60 * 60 * 1000.00));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的月数
     *
     * @param date1
     * @param date2
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static int getMonthMargin(String date1, String date2) {
        try {
            Calendar bef = Calendar.getInstance();
            Calendar aft = Calendar.getInstance();
            bef.setTime(sdf_date_format.parse(date1));
            aft.setTime(sdf_date_format.parse(date2));
            int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
            int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
            return Math.abs(month + result);
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 返回日期加X天后的日期
     *
     * @param date
     * @param i
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String addDay(String date, int i) {
        try {
            Date addDate = sdf_date_format.parse(date);
            Calendar cl = Calendar.getInstance();
            cl.setTime(addDate);
            cl.add(Calendar.DATE, i);
            return sdf_date_format.format(cl.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addDay():" + e.toString());
            return getDate();
        }
    }

    /**
     * 返回日期加X月后的日期
     *
     * @param date
     * @param i
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String addMonth(String date, int i) {
        try {
            Date addDate = sdf_date_format.parse(date);
            Calendar cl = Calendar.getInstance();
            cl.setTime(addDate);
            cl.add(Calendar.MONTH, i);
            return sdf_date_format.format(cl.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addMonth():" + e.toString());
            return getDate();
        }
    }

    /**
     * 返回日期加X年后的日期
     *
     * @param date
     * @param i
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String addYear(String date, int i) {
        try {
            Date addDate = sdf_date_format.parse(date);
            Calendar cl = Calendar.getInstance();
            cl.setTime(addDate);
            cl.add(Calendar.YEAR, i);
            return sdf_date_format.format(cl.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addYear():" + e.toString());
            return "";
        }
    }

    /**
     * 返回某年某月中的最大天
     *
     * @param iyear
     * @param imonth
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static int getMaxDay(int iyear, int imonth) {
        int day = 0;
        try {
            if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7
                    || imonth == 8 || imonth == 10 || imonth == 12) {
                day = 31;
            } else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11) {
                day = 30;
            } else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))) {
                day = 29;
            } else {
                day = 28;
            }
            return day;
        } catch (Exception e) {
            logger.debug("DateUtil.getMonthDay():" + e.toString());
            return 1;
        }
    }

    /**
     * 格式化日期
     *
     * @param orgDate
     * @param Type
     * @param Span
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    @SuppressWarnings("static-access")
    public static String rollDate(String orgDate, int Type, int Span) {
        try {
            String temp = "";
            int iyear, imonth, iday;
            int iPos = 0;
            char seperater = '-';
            if (orgDate == null || orgDate.length() < 6) {
                return "";
            }

            iPos = orgDate.indexOf(seperater);
            if (iPos > 0) {
                iyear = Integer.parseInt(orgDate.substring(0, iPos));
                temp = orgDate.substring(iPos + 1);
            } else {
                iyear = Integer.parseInt(orgDate.substring(0, 4));
                temp = orgDate.substring(4);
            }

            iPos = temp.indexOf(seperater);
            if (iPos > 0) {
                imonth = Integer.parseInt(temp.substring(0, iPos));
                temp = temp.substring(iPos + 1);
            } else {
                imonth = Integer.parseInt(temp.substring(0, 2));
                temp = temp.substring(2);
            }

            imonth--;
            if (imonth < 0 || imonth > 11) {
                imonth = 0;
            }

            iday = Integer.parseInt(temp);
            if (iday < 1 || iday > 31)
                iday = 1;

            Calendar orgcale = Calendar.getInstance();
            orgcale.set(iyear, imonth, iday);
            temp = rollDate(orgcale, Type, Span);
            return temp;
        } catch (Exception e) {
            return "";
        }
    }

    public static String rollDate(Calendar cal, int Type, int Span) {
        try {
            String temp = "";
            Calendar rolcale;
            rolcale = cal;
            rolcale.add(Type, Span);
            temp = sdf_date_format.format(rolcale.getTime());
            return temp;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回默认的日期格式
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static synchronized String getDatePattern() {
        defaultDatePattern = "yyyy-MM-dd";
        return defaultDatePattern;
    }

    /**
     * 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd
     *
     * @param aDate
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 取得给定日期的时间字符串，格式为当前默认时间格式
     *
     * @param theTime
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * 取得当前时间的Calendar日历对象
     *
     * @return
     * @throws ParseException
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }

    /**
     * 获得服务器当前日期，以格式为：MM-dd的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getMonthDay() {
        try {
            SimpleDateFormat sdf_date_format = new SimpleDateFormat("MM-dd");
            return sdf_date_format.format(new Date());
        } catch (Exception e) {
            logger.debug("DateUtil.getDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 将日期类转换成指定格式的字符串形式
     *
     * @param aMask
     * @param aDate
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            logger.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 将指定的日期转换成默认格式的字符串形式
     *
     * @param aDate
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * 将日期字符串按指定格式转换成日期类型
     *
     * @param aMask   指定的日期格式，如:yyyy-MM-dd
     * @param strDate 待转换的日期字符串
     * @return
     * @throws ParseException
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static final Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (logger.isDebugEnabled()) {
            logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            logger.error("ParseException: " + pe);
            throw pe;
        }
        return (date);
    }

    /**
     * 将日期字符串按默认格式转换成日期类型
     *
     * @param strDate
     * @return
     * @throws ParseException
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static Date convertStringToDate(String strDate)
            throws ParseException {
        Date aDate = null;

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("converting date with pattern: " + getDatePattern());
            }
            aDate = convertStringToDate(getDatePattern(), strDate);
        } catch (ParseException pe) {
            logger.error("Could not convert '" + strDate + "' to a date, throwing exception");
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return aDate;
    }

    /**
     * 返回一个JAVA简单类型的日期字符串
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getSimpleDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat();
        String NDateTime = formatter.format(new Date());
        return NDateTime;
    }

    /**
     * 将指定字符串格式的日期与当前时间比较
     *
     * @param strDate 需要比较时间
     * @return <p>
     * int code
     * <ul>
     * <li>-1 当前时间 < 比较时间 </li>
     * <li> 0 当前时间 = 比较时间 </li>
     * <li>>=1当前时间 > 比较时间 </li>
     * </ul>
     * </p>
     * @author DYLAN
     * @date Feb 17, 2012
     */
    public static int compareToCurTime(String strDate) {
        if (strDate.length() == 0) {
            return -1;
        }
        Date curTime = cale.getTime();
        String strCurTime = null;
        try {
            strCurTime = sdf_datetime_format.format(curTime);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("[Could not format '" + strDate + "' to a date, throwing exception:" + e.getLocalizedMessage() + "]");
            }
        }
        if (strDate.length() != 0) {
            return strCurTime.compareTo(strDate);
        }
        return -1;
    }

    /**
     * 为查询日期添加最小时间
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addStartTime(Date param) {
        Date date = param;
        try {
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            return date;
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 为查询日期添加最大时间
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addEndTime(Date param) {
        Date date = param;
        try {
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(0);
            return date;
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 返回系统现在年份中指定月份的天数
     *
     * @param month
     * @return 指定月的总天数
     */
    @SuppressWarnings("deprecation")
    public static String getMonthLastDay(int month) {
        Date date = new Date();
        int[][] day = {{0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        int year = date.getYear() + 1900;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return day[1][month] + "";
        } else {
            return day[0][month] + "";
        }
    }

    /**
     * 返回指定年份中指定月份的天数
     *
     * @param year
     * @param month
     * @return 指定月的总天数
     */
    public static String getMonthLastDay(int year, int month) {
        int[][] day = {{0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return day[1][month] + "";
        } else {
            return day[0][month] + "";
        }
    }

    /**
     * 判断是平年还是闰年
     *
     * @param year
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static boolean isLeapyear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 取得当前时间的日戳
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    @SuppressWarnings("deprecation")
    public static String getTimestamp() {
        Date date = cale.getTime();
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth()
                + date.getDate() + date.getMinutes() + date.getSeconds()
                + date.getTime();
        return timestamp;
    }

    /**
     * 取得指定时间的日戳
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getTimestamp(Date date) {
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth()
                + date.getDate() + date.getMinutes() + date.getSeconds()
                + date.getTime();
        return timestamp;
    }


    /**
     * 判断分段时间是否在指定的某个时段区间内
     *
     * @param date         待比较的日期
     * @param strDateBegin 开始时间段 05:00:00
     * @param strDateEnd   开始时间段 10:00:00
     * @return
     */
    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(date);
        int dateBegin = Integer.parseInt(strDateBegin.replaceAll(":", ""));
        int dateEnd = Integer.parseInt(strDateEnd.replaceAll(":", ""));
        int compareDate = Integer.parseInt(strDate.replaceAll(":", ""));
        if (compareDate >= dateBegin && compareDate <= dateEnd) {
            return true;
        }
        return false;
    }

    /**
     * 得到本月的第一天
     *
     * @return
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));

        return sdf.format(calendar.getTime());
    }

    /**
     * 得到本月的最后一天
     *
     * @return
     */
    public static String getMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    /**
     * 检验日期是否属于当天时间内
     *
     * @param str 2017-8-22
     * @return
     * @throws Exception
     */
    public static boolean isToday(String str) throws Exception {
        Date date = null;
        try {
            date = sdf_date_format.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期错误", e);
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        }
        return false;
    }

    /**
     * 由出生日期获得年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 判断一个日期是否大于今天
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean isAfterToday(String date) throws ParseException {
        Date date1 = convertStringToDate(date);
        Date now = new Date();
        if (date1.after(now)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    public static boolean belongCalendar(Date nowTime, String startTime, String endTime) throws ParseException {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(convertStringToDate(startTime));
        Calendar end = Calendar.getInstance();
        end.setTime(convertStringToDate(endTime));
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Integer differentDays(String date1, String date2) throws ParseException {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(convertStringToDate(date1));

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(convertStringToDate(date2));
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * 功能描述: 时间按月份推后
     *
     * @param time  需要操作的时间
     * @param month 需要退回的月份
     * @return:
     */
    public static String advanceMonthTime(String time, Integer month) throws ParseException {
        Calendar curr = Calendar.getInstance();
        curr.setTime(convertStringToDate(time));
        curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + month);
        Date date = curr.getTime();
        return sdf_datetime_format.format(date);
    }

    public static void main(String[] args) throws Exception {

    }
}