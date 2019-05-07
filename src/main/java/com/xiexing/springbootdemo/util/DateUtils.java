package com.xiexing.springbootdemo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {


    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);


    public static Date getCurrentDate() {
        return new Date();
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getCurrentYearMonthDate() {
        return getCurrentFormatDate("yyyy-MM");
    }

    public static int cutTwoDateToDay(Date a, Date b) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int theday = 0;
        try {
            Date beginDate = format.parse(format.format(a));
            Date endDate = format.parse(format.format(b));

            calendar.setTime(beginDate);
            long begin = calendar.getTimeInMillis();
            calendar.setTime(endDate);
            long end = calendar.getTimeInMillis();

            theday = (int) ((end - begin) / 86400000L);
        } catch (ParseException e) {
            logger.debug("日期转换出错!", e);
        }
        return theday;
    }

    public static String intToTimeString(int time) {
        String hour = String.valueOf(time / 60);
        String minute = String.valueOf(time - time / 60 * 60);

        if (hour.length() < 2) {
            hour = "0" + hour;
        }
        if (minute.length() < 2) {
            minute = "0" + minute;
        }
        return hour + ":" + minute;
    }

    public static Date maxDate(Date a, Date b) {
        if (a.before(b)) {
            return b;
        }
        return a;
    }

    public static Date minDate(Date a, Date b) {
        if (a.before(b)) {
            return a;
        }
        return b;
    }

    public static int getDayOfWeek() {
        int dayOfWeek = Calendar.getInstance().get(7);

        if (dayOfWeek == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek--;
        }
        return dayOfWeek;
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date createDate(String dateString, String pattern)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(dateString);
    }

    public static String getCurrentFormatDate(String datePattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date dateInstance = getCurrentDate();
        simpleDateFormat.applyPattern(datePattern);
        return simpleDateFormat.format(dateInstance);
    }


    public static int getYear() {
        return Calendar.getInstance().get(1);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static int getDay() {
        return Calendar.getInstance().get(5);
    }

    public static int getHour() {
        return Calendar.getInstance().get(11);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(12);
    }

    public static int getSecond() {
        return Calendar.getInstance().get(13);
    }


    public static String getlastMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        GregorianCalendar gc = new GregorianCalendar();
        gc.roll(2, false);
        return sdf.format(gc.getTime());
    }

    public static String getCurrentLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.add(2, -1);
        return format.format(cal.getTime());
    }

    public static String[] getCurrentLastWeek() {
        String[] weeks = new String[2];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(6, -cal.get(7));
        weeks[1] = format.format(cal.getTime());
        cal.add(6, -6);
        weeks[0] = format.format(cal.getTime());
        return weeks;
    }


    public static String getDateStr(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String getMonthStr(Date date) {
        String pattern = "yyyy-MM";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    /**
     * 将原始格式日期串转换为目标格式日期串
     *
     * @param destinationPattern
     * @param originalPattern
     * @param dateStr
     * @return
     */
    public static String getDateFormatToConvert(String destinationPattern, String originalPattern, String dateStr) {
        SimpleDateFormat destination = new SimpleDateFormat(destinationPattern);
        SimpleDateFormat original = new SimpleDateFormat(originalPattern);
        String formatDate = null;
        try {
            formatDate = destination.format(original.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("日期转换格式异常！");
        }
        return formatDate;
    }

    /**
     * 计算两个日期字符串相差天数(格式为"yyyy-MM-dd")
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDaysOfCompare(String startDate, String endDate) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        long betweenDays = 0;
        try {
            long beginTime = sf.parse(startDate).getTime();
            long endTime = sf.parse(endDate).getTime();
            betweenDays = ((endTime - beginTime) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(betweenDays);
    }


    /**
     * 获取所传日期n天后的日期(格式为"yyyy-MM-dd")
     *
     * @param dateStr
     * @param days
     * @return
     */
    public static String getDaysAfterDays(String dateStr, int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("获取" + dateStr + "日期" + days + "天后的日期异常！");
        }
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + (days - 1));
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }


    /**
     * 根据年月,获取对应的月份天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }




    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String getCurrentDateTime(String Dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(Dateformat);
        datestr = df.format(new Date());
        return datestr;
    }


    /**
     * 将字符串日期转换为日期格式
     * 自定義格式
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr, String dateformat) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }



    /**
     * 将日期格式日期转换为字符串格式 自定義格式
     *
     * @param date
     * @param dateformat
     * @return
     */
    public static String dateToString(Date date, String dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 获取日期的DAY值
     *
     * @param date 输入日期
     * @return
     */
    public static int getDayOfDate(Date date) {
        int d = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        d = cd.get(Calendar.DAY_OF_MONTH);
        return d;
    }

    /**
     * 获取日期的MONTH值
     *
     * @param date 输入日期
     * @return
     */
    public static int getMonthOfDate(Date date) {
        int m = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        m = cd.get(Calendar.MONTH) + 1;
        return m;
    }

    /**
     * 获取日期的YEAR值
     *
     * @param date 输入日期
     * @return
     */
    public static int getYearOfDate(Date date) {
        int y = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        y = cd.get(Calendar.YEAR);
        return y;
    }

    /**
     * 获取星期几
     *
     * @param date 输入日期
     * @return
     */
    public static int getWeekOfDate(Date date) {
        int wd = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        wd = cd.get(Calendar.DAY_OF_WEEK) - 1;
        return wd;
    }

    /**
     * 获取输入日期的当月第一天
     *
     * @param date 输入日期
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        return cd.getTime();
    }



    /**
     * 判断是否是闰年
     *
     * @param year 输入年份
     * @return 是true 否false
     */
    public static boolean isLeapYEAR(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据整型数表示的年月日，生成日期类型格式
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return
     */
    public static Date getDateByYMD(int year, int month, int day) {
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, day);
        return cd.getTime();
    }

    /**
     * 获取年周期对应日
     *
     * @param date  输入日期
     * @param iyear 年数  負數表示之前
     * @return
     */
    public static Date getYearCycleOfDate(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 获取月周期对应日
     *
     * @param date 输入日期
     * @param i
     * @return
     */
    public static Date getMonthCycleOfDate(Date date, int i) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.MONTH, i);

        return cd.getTime();
    }

    /**
     * 计算 fromDate 到 toDate 相差多少年
     *
     * @param fromDate
     * @param toDate
     * @return 年数
     */
    public static int getYearByMinusDate(Date fromDate, Date toDate) {
        Calendar df = Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt = Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR) - df.get(Calendar.YEAR);
    }

    /**
     * 计算 fromDate 到 toDate 相差多少个月
     *
     * @param fromDate
     * @param toDate
     * @return 月数
     */
    public static int getMonthByMinusDate(Date fromDate, Date toDate) {
        Calendar df = Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt = Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR) * 12 + dt.get(Calendar.MONTH) -
                (df.get(Calendar.YEAR) * 12 + df.get(Calendar.MONTH));
    }

    /**
     * 在输入日期上增加（+）或减去（-）天数
     *
     * @param date   输入日期
     */
    public static Date addDay(Date date, int iday) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.DAY_OF_MONTH, iday);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）月份
     *
     * @param date   输入日期
     * @param imonth 要增加或减少的月分数
     */
    public static Date addMonth(Date date, int imonth) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.MONTH, imonth);

        return cd.getTime();
    }



    public static long getAgeByBirthday(String date) {

        Date birthday = stringToDate(date, "yyyy-MM-dd");
        long sec = System.currentTimeMillis() - birthday.getTime();

        long age = sec / (1000 * 60 * 60 * 24) / 365;

        return age;
    }

}
