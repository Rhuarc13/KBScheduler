package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class DayView extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_view);
        ListView listview = (ListView) findViewById(R.id.listView);

        String[] values = new String[] {"6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:00",
                "9:30", "10:00", "10:30", "11:00", "11:30", "12:00"};

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);




        String date = getIntent().getStringExtra("date");

        String dateList[] = date.split("\\s");

        String method = "pull_time";
        String year   = dateList[5];
        String month  = dateList[1];
        String day    = dateList[2];

        if(month.equals("Jan")){
            month = "01";
        } else if(month.equals("Feb")){
            month = "02";
        } else if(month.equals("Mar")){
            month = "03";
        } else if(month.equals("Apr")){
            month = "04";
        } else if(month.equals("May")){
            month = "05";
        } else if(month.equals("Jun")){
            month = "06";
        } else if(month.equals("Jul")){
            month = "07";
        } else if(month.equals("Aug")){
            month = "08";
        } else if(month.equals("Sep")){
            month = "09";
        } else if(month.equals("Oct")){
            month = "10";
        } else if(month.equals("Nov")){
            month = "11";
        } else if(month.equals("Dec")){
            month = "12";
        }

        date = year+"-"+month+"-"+day;

        BackgroundTask backgroundTask = new BackgroundTask(date, method);
        backgroundTask.execute();



    }

    public void onClickDay(View view) {
        String date = getIntent().getStringExtra("date");
        String dateList[] = date.split("\\s");

        String year   = dateList[5];
        String month  = dateList[1];
        String day    = dateList[2];
        date = month + ' ' + day + ", " + year;

        Intent intent = new Intent(this, Confermation.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("date", date);
        startActivity(intent);
        finish();
    }
}
