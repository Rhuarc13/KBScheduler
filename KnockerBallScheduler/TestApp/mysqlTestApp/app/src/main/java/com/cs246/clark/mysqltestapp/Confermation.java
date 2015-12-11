package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Confermation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confermation);
        setText();
    }

    private void setText() {
        TextView custEmail, custDate, custName, custTime, custAddress;
        String email, date, name = "", address = "", time = "";

        email = getIntent().getStringExtra("email");
        date = getIntent().getStringExtra("date");

        custName = (TextView) findViewById(R.id.customerName);
        custEmail = (TextView) findViewById(R.id.customerEmail);
        custAddress = (TextView) findViewById(R.id.customerAddress);
        custDate = (TextView) findViewById(R.id.customerDate);
        custTime = (TextView) findViewById(R.id.customerTime);

        custName.setText(name);
        custEmail.setText(email);
        custAddress.setText(address);
        custDate.setText(date);
        custTime.setText(time);
    }
}
