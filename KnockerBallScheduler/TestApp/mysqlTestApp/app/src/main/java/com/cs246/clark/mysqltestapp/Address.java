package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Address extends Activity {
    TextView custEndTime, custAddress, custStartTime;
    String endTime, startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        custEndTime = (TextView) findViewById(R.id.endTime);
        custStartTime = (TextView) findViewById(R.id.startTime);
        custAddress = (TextView) findViewById(R.id.customerAddress);
        endTime = calcTime();
        startTime = getIntent().getStringExtra("startTime");
        custEndTime.setHint(endTime);
        custStartTime.setText(startTime);
    }

    public void onClickAddress(View view) {
        String time = custStartTime.getText().toString() + " - " + custEndTime.getText().toString() + " PM";

        Intent intent = new Intent(this, Confirmation.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("date", getIntent().getStringExtra("date"));
        intent.putExtra("address", custAddress.getText().toString());
        intent.putExtra("time", time);
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
