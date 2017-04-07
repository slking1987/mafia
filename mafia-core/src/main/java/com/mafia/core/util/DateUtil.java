package com.mafia.core.util;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shaolin on 2017/3/27.
 */
public abstract class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public static Integer date2Int(Date date) {
        return (int) (date.getTime() / 1000);
    }

    public static Date str2Date(String strDate) {
        if(StringUtils.isEmpty(strDate)) {
            return null;
        }

        Date date = null;
        try {
            date = DATE_FORMATTER.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Integer getEarliestTimeInt(Date date) {
        return date2Int(getEarliestTime(date));
    }

    public static Date getEarliestTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取日期、星期、月、年信息
     * @param day Date
     * @return int[]
     */
    public static int[] getFullDate(Date day)
    {
        String start = new SimpleDateFormat("yyyyMMdd").format(day);
        int date = Integer.parseInt(start);
        int week = getWeekInt(day);
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String yearStr = String.valueOf(year);
        String monthStr = String.valueOf(month);
        if(month < 10)
        {
            monthStr = "0" + month;
        }
        monthStr = yearStr + monthStr;
        return new int[]{date, week, Integer.parseInt(monthStr), year};
    }

    /**
     * 获取星期-每年的第几个星期, 201503
     * @param day
     * @return
     */
    public static int getWeekInt(Date day)
    {
        // 业务要求 从周五0点到下周五0点为一周
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        // 获取本周最后一天所在年
        int year = cal.get(Calendar.YEAR);
        // 如果是周日到周四 则算上一周
        if(dayOfWeek < 6)
        {
            week --;
        }
        if(week == 0)
        {
            week = 52;
            year --;
        }
        String weekStr = String.valueOf(week);
        if(week < 10)
        {
            weekStr = "0" + weekStr;
        }
        String str = String.valueOf(year) + weekStr;
        return Integer.parseInt(str);
    }
}
