package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.tyczj.extendedcalendarview.CalendarAdapter;
import com.tyczj.extendedcalendarview.Event;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;
import com.tyczj.extendedcalendarview.Day;

import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Calendar extends Activity {
    ExtendedCalendarView calendarView;
    private static final String TAG = "Calendar Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        calendarView = (ExtendedCalendarView) findViewById(R.id.calendar);

        java.util.Calendar cal = java.util.Calendar.getInstance();
        CalendarAdapter ca = new CalendarAdapter(getApplicationContext(), cal);
        ca.getView()

        calendarView.setOnDayClickListener(new ExtendedCalendarView.OnDayClickListener() {
            @Override
            public void onDayClicked(AdapterView<?> adapter, View view, int position, long id, Day day) {
                Log.v(TAG, "Date selected is " + day.getDay() + "/" + (day.getMonth() + 1) + "/" + day.getYear());
                addEvent(day);
                calendarView.refreshCalendar();
            }
        });


    }

    public void addEvent(Day day) {
        java.util.Calendar startDay = java.util.Calendar.getInstance();
        java.util.Calendar endDay   = java.util.Calendar.getInstance();
        startDay.set(day.getYear(), day.getMonth(), day.getDay(), 15, 0);
        endDay.set(day.getYear(), day.getMonth(), day.getDay() + 2, 16, 0);
        Event event = new Event(123, startDay.getTimeInMillis(), endDay.getTimeInMillis());
        Log.i(TAG, "Colors (before): " + day.getColors().toString());
        event.setColor(Event.COLOR_RED);
        day.addEvent(event);
        calendarView.refreshCalendar();
        Log.i(TAG, "Colors: " + day.getColors().toString());
        Log.i(TAG, "Event added");
    }
}
