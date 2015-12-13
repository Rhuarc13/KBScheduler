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

/***********************************************************************
 *
 *                  ~~ KnockerBall Schedule App ~~
 *
 * This class handles the click and other information from the
 * calendar interaction
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford
 * @version 1.0
 **********************************************************************/
public class Calendar extends Activity {

    /*TAG for logging information*/
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

        /*Our observer loop for making sure the server responded*/
        Lock lock = new ReentrantLock();
        int waitTime = 0;
        synchronized (lock) {
            while (r.getCode() == 0 && waitTime < 26) { //waitTime variable is to prevent total hanging of the GUI
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


                /*
                Passing of the information to the next activity
                 */
                if (flag) {

                    Intent intent = new Intent(getApplicationContext(), DayView.class);
                    intent.putExtra("name", getIntent().getStringExtra("name"));
                    intent.putExtra("phone", getIntent().getStringExtra("phone"));
                    intent.putExtra("email", getIntent().getStringExtra("email"));
                    intent.putExtra("date", date.toString());
                    startActivity(intent);
                }
            }
        });

    }

}
