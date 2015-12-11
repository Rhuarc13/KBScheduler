package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DayView extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_view);



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

        String timeView = "sevenT";

        Resources res = getResources();
        int id = res.getIdentifier(timeView, "id", this.getPackageName());


        TextView tempTextView = (TextView) findViewById(id);
        tempTextView.setText("LALALALA SUCCESS!");




        //here is where we need to set the color/click-ability of the time slots


    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Confermation.class);
        startActivity(intent);
        finish();
    }
}
