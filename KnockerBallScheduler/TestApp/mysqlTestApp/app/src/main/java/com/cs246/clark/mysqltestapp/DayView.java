package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
<<<<<<< HEAD
import android.content.SharedPreferences;
=======
>>>>>>> 2f90c66e6c1f241d452b44b3fcb50dd54a9b12ec
import android.net.Uri;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
<<<<<<< HEAD
import android.widget.ArrayAdapter;
import android.widget.ListView;
=======
>>>>>>> 2f90c66e6c1f241d452b44b3fcb50dd54a9b12ec
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.TextView;
<<<<<<< HEAD

import java.util.ArrayList;
=======
>>>>>>> 2f90c66e6c1f241d452b44b3fcb50dd54a9b12ec
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DayView extends Activity {

    private final static String TAG = "DAY_VIEW";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_view);


        String date = getIntent().getStringExtra("date");

        String dateList[] = date.split("\\s");

        String method = "pull_time";
        String year = dateList[5];
        String month = dateList[1];
        String day = dateList[2];

        if (month.equals("Jan")) {
            month = "01";
        } else if (month.equals("Feb")) {
            month = "02";
        } else if (month.equals("Mar")) {
            month = "03";
        } else if (month.equals("Apr")) {
            month = "04";
        } else if (month.equals("May")) {
            month = "05";
        } else if (month.equals("Jun")) {
            month = "06";
        } else if (month.equals("Jul")) {
            month = "07";
        } else if (month.equals("Aug")) {
            month = "08";
        } else if (month.equals("Sep")) {
            month = "09";
        } else if (month.equals("Oct")) {
            month = "10";
        } else if (month.equals("Nov")) {
            month = "11";
        } else if (month.equals("Dec")) {
            month = "12";
        }

        date = year + "-" + month + "-" + day;

        Response r = new Response();
        BackgroundTask backgroundTask = new BackgroundTask(date, method, r);
        backgroundTask.execute();

<<<<<<< HEAD

=======
>>>>>>> 2f90c66e6c1f241d452b44b3fcb50dd54a9b12ec
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

            /*try {
                lock.wait(500);
            } catch (InterruptedException e) {
                Log.e(TAG, "Ran into an InterruptedException");
            }*/
        }

<<<<<<< HEAD
        String timeView = "sevenT";

        Resources res = getResources();
        int id = res.getIdentifier(timeView, "id", this.getPackageName());
        TextView tempTextView = (TextView) findViewById(id);

=======

        String startTime;
>>>>>>> 2f90c66e6c1f241d452b44b3fcb50dd54a9b12ec
        if (r.getCode() == 200) {
            Log.i(TAG, "Response is: " + r.getText());
            try {
                JSONObject jsonObject = new JSONObject(r.getText());
                JSONArray array = jsonObject.getJSONArray("times");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject times = array.getJSONObject(i);
                    Log.i(TAG, times.toString());
                    startTime = times.getString("start_time");
                    int start_hr = Integer.parseInt(startTime.split(":")[0]);
                    int start_min = Integer.parseInt(startTime.split(":")[1]);
                    startTime = start_hr + ":" + start_min;
                    String endTime   = times.getString("end_time");
                }
            } catch (JSONException je) {
                Log.e(TAG, "JSONException: " + je.toString());
            }
        } else {
            Toast.makeText(this, "Error occurred connecting to the database", Toast.LENGTH_LONG).show();
        }

        String timeView = "sevenT";

        Resources res = getResources();
        int id = res.getIdentifier(timeView, "id", this.getPackageName());


        TextView tempTextView = (TextView) findViewById(id);
        tempTextView.setText("LALALALA SUCCESS!");
        //here is where we need to set the color/click-ability of the time slots

<<<<<<< HEAD
}
=======

    }
>>>>>>> 2f90c66e6c1f241d452b44b3fcb50dd54a9b12ec

    public void onClick(View view) {
        Intent intent = new Intent(this, Confermation.class);
        startActivity(intent);
        finish();
    }

}
