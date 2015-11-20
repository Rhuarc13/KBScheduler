package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Calendar extends Activity {

    private static final String TAG = "CALENDAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);




    }

    Log.e(TAG, "You made a booboo");
    Log.i(TAG, "You have selected a date");
    Log.i(TAG, "You have selected a time");
}
