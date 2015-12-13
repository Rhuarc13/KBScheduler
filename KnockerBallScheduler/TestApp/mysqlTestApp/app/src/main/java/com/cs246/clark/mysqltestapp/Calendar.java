package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.lang.Override;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        final CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        Response r = new Response();

        HashSet<Day> events = cv.getEvents();
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
        cv.refreshDrawableState();

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayPress(Date date) {

                Object[] events = cv.getEvents().toArray();

                boolean flag = true;
                Day suspect;
                for (int i = 0; i < events.length; i++) {
                    suspect = (Day) events[i];

                    if (suspect.getDate().getDay()       == date.getDay() &&
                            suspect.getDate().getMonth() == date.getMonth() &&
                            suspect.getDate().getYear()  == date.getYear()) {
                        flag = suspect.isAvailable();
                        break;
                    }
                }



                if (flag) {
                    Intent intent = new Intent(getApplicationContext(), DayView.class);
                    intent.putExtra("name", getIntent().getStringExtra("name"));
                    intent.putExtra("phone", getIntent().getStringExtra("phone"));
                    intent.putExtra("email", getIntent().getStringExtra("email"));
                    intent.putExtra("phone", getIntent().getStringExtra("phone"));
                    intent.putExtra("date", date.toString());
                    startActivity(intent);
                }
            }
        });

    }

}
