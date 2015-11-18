package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class Calendar extends Activity {
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
            }
        });
    }

}
