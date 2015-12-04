package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.sql.Time;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import android.widget.Toast;


public class Calendar extends Activity {

    private static final String TAG = "CALENDAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        HashSet<Date> events = new HashSet<>();
        events.add(new Date());
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(2015, 11, 4);
        events.add(new Date(cal.getTimeInMillis()));

        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(Calendar.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });



    }

}
