package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***********************************************************************
 *
 *                  ~~ KnockerBall Schedule App ~~
 *
 * This class provides an opportunity for the user to review their
 * full reservation and submit or cancel.
 *
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford
 * @version 1.0
 **********************************************************************/

public class Confirmation extends Activity {
    /**/
    private final static String TAG = "CONFIRMATION";
    private final static String SUCCESS = "SUCCESS: Reservation inserted";

    double duration = 0.0;
    String method, name, firstName, lastName, address, city, state, finalDate, sTime, eTime, response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confermation);
        setText();
    }

    private void setText() {
        TextView custEmail, custDate, custName, custTime, custAddress, custCity, custState, custPhone;


        name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String email = getIntent().getStringExtra("email");
        address = getIntent().getStringExtra("address");
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String month = getIntent().getStringExtra("numberMonth");

        custName = (TextView) findViewById(R.id.customerName);
        custPhone = (TextView) findViewById(R.id.customerPhone);
        custEmail = (TextView) findViewById(R.id.customerEmail);
        custAddress = (TextView) findViewById(R.id.customerAddress);
        custCity = (TextView) findViewById(R.id.cityAddress);
        custState = (TextView) findViewById(R.id.stateAddress);
        custDate = (TextView) findViewById(R.id.customerDate);
        custTime = (TextView) findViewById(R.id.customerTime);

        custName.setText(name);
        custPhone.setText(phone);
        custEmail.setText(email);
        custAddress.setText(address);
        custCity.setText(city);
        custState.setText(state);
        custDate.setText(date);
        custTime.setText(time);

        //split the time into start and end time to send to the server
        String[] combTime = time.split(" ");
        sTime = combTime[0];
        eTime = combTime[3];

        //now convert to military time for the server
        String[] sTimeSplit = sTime.split(":");
        String sTimeHour = sTimeSplit[0];
        String sTimeMin = sTime.split(":")[1];

        String[] eTimeSplit = eTime.split(":");
        String eTimeHour = eTimeSplit[0];
        String eTimeMin = eTime.split(":")[1];

        int sTimeHourInt;
        sTimeHourInt = Integer.parseInt(sTimeHour);
        sTimeHourInt += 12;

        int eTimeHourInt;
        eTimeHourInt = Integer.parseInt(eTimeHour);
        eTimeHourInt += 12;

        duration = eTimeHourInt - sTimeHourInt;
        if (Integer.parseInt(sTimeMin) < 30 && Integer.parseInt(eTimeMin) >= 30) {
            duration += 0.5;
        } else if (Integer.parseInt(sTimeMin) >= 30 && Integer.parseInt(eTimeMin) < 30) {
            duration -= 0.5;
        }

        sTime = "" + sTimeHourInt + ":" + sTimeMin + ":00";
        eTime = "" + eTimeHourInt + ":" + eTimeMin + ":00";
        System.out.println(sTime + " and " + eTime);

        firstName = name.split(" ")[0];
        lastName = name.split(" ")[1];

        String[] splitDate = date.split(" ");
        String year = splitDate[2];
        String day = splitDate[1].split(",")[0];
        finalDate = year + "-" + month + "-" + day;
        
    }


    public void confirmation(View view){

        method = "confirm";

        Response response = new Response();
        Log.i(TAG, "Start time: " + sTime);
        Log.i(TAG, "End time: " + eTime);
        Log.i(TAG, "Date: " + finalDate);
        BackgroundTask backgroundTask = new BackgroundTask(method, firstName, lastName, address, city, state, finalDate, sTime, eTime, response);

        backgroundTask.execute();

        Lock lock = new ReentrantLock();
        int waitTime = 0;
        synchronized (lock) {
            while (response.getCode() == 0 && waitTime < 26) {
                try {
                    lock.wait(100);
                    waitTime++;
                } catch (InterruptedException e) {
                    Log.e(TAG, "Ran into an InterruptedException");
                }
            }

            if (response.getCode() == 200) {
                try {
                    lock.wait(500);
                    if (response.getText().equals(SUCCESS)) {
                        //TODO Find out where this is going
                        Intent old = getIntent();
                        Intent intent = new Intent(this, Done.class);
                        intent.putExtra("email", old.getStringExtra("email"));
                        intent.putExtra("first_name", firstName);
                        intent.putExtra("last_name", lastName);
                        intent.putExtra("date", old.getStringExtra("date"));
                        intent.putExtra("street", address);
                        intent.putExtra("city", city);
                        intent.putExtra("state", state);
                        intent.putExtra("start", sTime);
                        intent.putExtra("end", eTime);
                        intent.putExtra("duration", "" + duration);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.e(TAG, "Error on the PHP side: " + response.getText());
                    }
                } catch (InterruptedException ie) {
                    Log.e(TAG, "Process interrupted");
                }
            } else {
                Toast.makeText(this, "Error occurred connecting to the database", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void cancleReservation(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }
}

