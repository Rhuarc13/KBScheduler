package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.sql.Time;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.widget.Toast;


public class Calendar extends Activity {

    private static final String TAG = "CALENDAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        Response r = new Response();

        HashSet<Date> events = cv.getEvents();
        CalendarEvents ce = new CalendarEvents(events, r);

        ce.execute();
        Log.i(TAG, "Getting the events from the server");

        Lock lock = new ReentrantLock();
        int waitTime = 0;
        synchronized (lock) {
            while (r.getCode() == 0 && waitTime < 26) {
                try {
                    lock.wait(100);
                    waitTime++;
                } catch (InterruptedException e) {
                    Log.e(TAG, "Ran into an InterruptedException");
                }
            }
        }

        if (r.getCode() == 200) {

        } else {
            Toast.makeText(this, "Error occurred connecting to the database", Toast.LENGTH_LONG).show();
        }

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
