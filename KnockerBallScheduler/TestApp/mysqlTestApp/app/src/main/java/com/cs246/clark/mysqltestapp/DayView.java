package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class DayView extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_view);
        ListView list = (ListView) findViewById(R.id.listView);
        String date = getIntent().getStringExtra("date");
        System.out.println(date);

    }
}
