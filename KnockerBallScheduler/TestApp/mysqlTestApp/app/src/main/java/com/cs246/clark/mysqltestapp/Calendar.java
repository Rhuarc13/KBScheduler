package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.tyczj.extendedcalendarview.ExtendedCalendarView;

import java.util.Date;

public class Calendar extends Activity {
    ExtendedCalendarView calendarView;
    private static final String TAG = "Calendar Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        calendarView = (ExtendedCalendarView) findViewById(R.id.calendar);
        /*calendarView.setOnDayClickListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Date selected = new Date(calendarView.getDay());
                Log.v(TAG, "Date selected is " + selected.toString());
            }
        });*/

    }
    public void onClick(View v) {
        /*Date selected = new Date(calendarView.getDate());
        Log.v(TAG, "Date selected is " + selected.toString());*/
    }
}
