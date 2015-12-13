package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Address extends Activity {
    TextView custAddress, custCity, custState, custStartTime;
    Spinner custEndTime;
    String endTime, startTime;
    String month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


        custEndTime   = (Spinner)  findViewById(R.id.spinner);
        custStartTime = (TextView) findViewById(R.id.startTime);
        custAddress   = (TextView) findViewById(R.id.customerAddress);
        custCity      = (TextView) findViewById(R.id.city);
        custState     = (TextView) findViewById(R.id.state);
        startTime     = getIntent().getStringExtra("startTime");
        month         = getIntent().getStringExtra("month");

        custStartTime.setText(startTime);
        calcTime();
    }

    public void onClickAddress(View view) {
        String time = custStartTime.getText().toString() + " - " + custEndTime.getSelectedItem().toString() + " PM";

        Intent intent = new Intent(this, Confirmation.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("date", getIntent().getStringExtra("date"));
        intent.putExtra("address", custAddress.getText().toString());
        //intent.putExtra("city",    custCity.getText().toString());
        //intent.putExtra("state",   custState.getText().toString());
        intent.putExtra("time", time);
        intent.putExtra("numberMonth", month);
        startActivity(intent);
        finish();
    }

    private void calcTime () {
        String startTime, endTime;
        startTime = getIntent().getStringExtra("startTime");

        String temp[] = startTime.split(":");
        int timeHour = (Integer.parseInt(temp[0]));

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        while (timeHour < 12){
            ++timeHour;
            if (timeHour == 12) {
                if (!temp[1].equals("30 PM")) {
                    categories.add((timeHour) + ":" + temp[1]);
                }
            } else {
                categories.add((timeHour) + ":" + temp[1]);
            }

            if (timeHour < 12) {
                if (temp[1].equals("30 PM"))
                    categories.add((timeHour + 1) + ":00 PM");
                else
                    categories.add((timeHour) + ":30 PM" );
            }
        }


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // attaching data adapter to spinner
        custEndTime.setAdapter(dataAdapter);

    }
}
