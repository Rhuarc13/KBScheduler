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
    TextView custAddress,custCity, custState, custStartTime;
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
        endTime       = calcTime();
        startTime     = getIntent().getStringExtra("startTime");
        month         = getIntent().getStringExtra("month");

        custStartTime.setText(startTime);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("6:00");
        categories.add("6:30");
        categories.add("7:00");
        categories.add("7:30");
        categories.add("8:00");
        categories.add("8:30");
        categories.add("9:00");
        categories.add("9:30");
        categories.add("10:00");
        categories.add("10:30");
        categories.add("11:00");
        categories.add("11:30");
        categories.add("12:00");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // attaching data adapter to spinner
        custEndTime.setAdapter(dataAdapter);

    }

    public void onClickAddress(View view) {
        String time = custStartTime.getText().toString() + " - " + custEndTime.getSelectedItem().toString() + " PM";

        Intent intent = new Intent(this, Confirmation.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("date", getIntent().getStringExtra("date"));
        intent.putExtra("address", custAddress.getText().toString());
        intent.putExtra("city",    custCity.getText().toString());
        intent.putExtra("state",   custState.getText().toString());
        intent.putExtra("time", time);
        intent.putExtra("numberMonth", month);
        startActivity(intent);
        finish();
    }

    private String calcTime () {
        String startTime, endTime;
        startTime = getIntent().getStringExtra("startTime");

        String temp[] = startTime.split(":");
        int time = (Integer.parseInt(temp[0])) + 1;
        endTime = time + ":" + temp[1];

        return endTime;
    }
}
