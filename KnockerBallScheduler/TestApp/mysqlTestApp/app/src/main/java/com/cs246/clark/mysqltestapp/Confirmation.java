package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Confirmation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confermation);
        setText();
    }

    private void setText() {
        TextView custEmail, custDate, custName, custTime, custAddress, custCity, custState, custPhone;


        String name    = getIntent().getStringExtra("name");
        String phone   = getIntent().getStringExtra("phone");
        String email   = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");
        String city    = getIntent().getStringExtra("city");
        String state   = getIntent().getStringExtra("state");
        String date    = getIntent().getStringExtra("date");
        String time    = getIntent().getStringExtra("time");
        String month = getIntent().getStringExtra("numberMonth");

        custName    = (TextView) findViewById(R.id.customerName);
        custPhone   = (TextView) findViewById(R.id.customerPhone);
        custEmail   = (TextView) findViewById(R.id.customerEmail);
        custAddress = (TextView) findViewById(R.id.customerAddress);
        custCity    = (TextView) findViewById(R.id.cityAddress);
        custState   = (TextView) findViewById(R.id.stateAddress);
        custDate    = (TextView) findViewById(R.id.customerDate);
        custTime    = (TextView) findViewById(R.id.customerTime);

        custName.setText(name);
        custPhone.setText(phone);
        custEmail.setText(email);
        custAddress.setText(address);
        custCity.setText(city);
        custState.setText(state);
        custDate.setText(date);
        custTime.setText(time);


        //split the time into start and end time to send to the server
        String[] combTime = time.split("\\s");
        String sTime = combTime[0];
        String eTime = combTime[3];

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

        sTime = "" + sTimeHourInt + ":" + sTimeMin + ":00";
        eTime = "" + eTimeHourInt + ":" + eTimeMin + ":00";
        System.out.println(sTime + " and " + eTime);

        String[] splitName = name.split("\\s");
        String firstName = splitName[0];
        String lastName  = splitName[1];

        String[] splitDate = date.split("\\s");
        String year = splitDate[2];
        String day  = splitDate[1];
        String startDateAndTime = year+"-"+month+"-"+day+ " " + sTime;
        String endDateAndTime   = year+"-"+month+"-"+day+ " " + eTime;



        String method = "confirm";


        //BackgroundTask backgroundTask = new BackgroundTask(method, );
        //backgroundTask.execute();






    }

    public void confirmation(){

    }
}
