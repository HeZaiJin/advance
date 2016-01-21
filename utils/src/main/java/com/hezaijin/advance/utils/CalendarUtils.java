package com.hezaijin.advance.utils;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Calendar工具类
 */
public class CalendarUtils {

    private static Calendar sCalendar = Calendar.getInstance();
    private static String FORMAT_DATE = "%Y-%m-%d";
    private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Time sTime = new Time();

    public static int getMaxDayInMonth(){
        return getCurCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getYear(){
        return getCurCalendar().get(Calendar.YEAR);
    }

    public static int getCurMonth(){
        return getCurCalendar().get(Calendar.MONTH) + 1;
    }

    public static int getCurDay(){
        return getCurCalendar().get(Calendar.DAY_OF_MONTH) + 1;
    }

    public static String getYearStr(){
        return String.valueOf(getCurCalendar().get(Calendar.YEAR));
    }

    public static String getMonthStr(){
        return String.valueOf(getCurCalendar().get(Calendar.MONTH) + 1);
    }

    public static long getTimeInSeconds(){
        return getCurCalendar().getTimeInMillis() / 1000;
    }

    public static long getTimeInMillis(int day){
        sTime.set(day, getCurCalendar().get(Calendar.MONTH), getCurCalendar().get(Calendar.YEAR));
        return sTime.toMillis(true);
    }

    public static int getMonthInSeconds(long timestamp){
        sTime.set(timestamp * 1000);
        return sTime.month + 1;
    }

    public static int getDay(long timestamp){
        sTime.set(timestamp);
        return sTime.monthDay;
    }

    public static String getFormatTime(int day){
        Calendar c = getCurCalendar();
        sTime.set(day, c.get(Calendar.MONTH), c.get(Calendar.YEAR));
        return sTime.format(FORMAT_DATE);
    }

    public static String getCurFormatTime(){
        return sFormat.format(getCurCalendar().getTime());
    }

    private static Calendar getCurCalendar(){
        sCalendar.setTimeInMillis(System.currentTimeMillis());
        return sCalendar;
    }
}
