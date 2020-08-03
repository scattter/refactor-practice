package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public int getYearMonthDate(String dateAndTimeString, String target, int splitHeadIndex, int splitAfterIndex, int minTarget, int maxTarget){
        int result;
        try {
            String splitString = dateAndTimeString.substring(splitHeadIndex, splitAfterIndex);
            result = Integer.parseInt(splitString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(target + " string is less than " + (splitAfterIndex-splitHeadIndex) + " characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(target + " is not an integer");
        }
        if (result < minTarget || result > maxTarget)
            throw new IllegalArgumentException(target + " cannot be less than "+ minTarget + " or more than " + maxTarget);
        return result;
    }


    public int[] getTime(String dateAndTimeString){
        int hour, minute;

        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            hour = 0;
            minute = 0;
        } else {
            hour = getYearMonthDate(dateAndTimeString,"Hour", 11, 13, 0, 23);

            minute = getYearMonthDate(dateAndTimeString,"Minute", 14, 16, 0, 59);
        }
        return new int[]{hour, minute};
    }

    public Date parse() {

        int year = getYearMonthDate(dateAndTimeString,"Year", 0, 4, 2000, 2012);
        int month = getYearMonthDate(dateAndTimeString,"Month", 5, 7, 1, 12);
        int date = getYearMonthDate(dateAndTimeString,"Date", 8, 10, 1, 31);
        int hour = getTime(dateAndTimeString)[0];
        int minute = getTime(dateAndTimeString)[1];

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
