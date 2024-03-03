/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hoang
 */
public class DateTimeHelper {

    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        } else {
            long milliseconds = utilDate.getTime();
            return new java.sql.Date(milliseconds);
        }
    }

    public static java.util.Date sqlDateToUtilDate(Date sqlDate) {
        return sqlDate == null ? null : new java.util.Date(sqlDate.getTime());
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int offset = calendar.getFirstDayOfWeek() - dayOfWeek;
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    public static Date addDaysToDate(Date date, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
        return calendar.getTime();
    }

}
