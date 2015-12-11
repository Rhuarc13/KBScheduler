package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

    public void onClick(View view) {
        Intent intent = new Intent(this, Confermation.class);
        startActivity(intent);
        finish();
    }
}
