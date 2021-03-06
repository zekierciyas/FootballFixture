package com.zeki.football_fixture.ui.view_model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeekCalculator {

    private Calendar calendar;
    public String[] getCurrentWeek() {
        this.calendar = Calendar.getInstance();
        this.calendar.setFirstDayOfWeek(Calendar.MONDAY);
        this.calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getNextWeek();
    }
    public String[] getNextWeek() {
         DateFormat format = new SimpleDateFormat("M-dd");
        String[] days = new String[7];
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(this.calendar.getTime());
            this.calendar.add(Calendar.DATE, 1);
        }
        return days;
    }
    public String[] getPreviousWeek() {
         this.calendar.add(Calendar.DATE, -21);
        return getNextWeek();
    }
}

