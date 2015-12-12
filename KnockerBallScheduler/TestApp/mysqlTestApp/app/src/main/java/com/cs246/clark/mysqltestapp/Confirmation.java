package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Confirmation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confermation);
        setText();
    }

    private void setText() {
        TextView custEmail, custDate, custName, custTime, custAddress, custPhone;

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String email = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");

        custName = (TextView) findViewById(R.id.customerName);
        custPhone = (TextView) findViewById(R.id.customerPhone);
        custEmail = (TextView) findViewById(R.id.customerEmail);
        custAddress = (TextView) findViewById(R.id.customerAddress);
        custDate = (TextView) findViewById(R.id.customerDate);
        custTime = (TextView) findViewById(R.id.customerTime);

        custName.setText(name);
        custPhone.setText(phone);
        custEmail.setText(email);
        custAddress.setText(address);
        custDate.setText(date);
        custTime.setText(time);
    }
}
