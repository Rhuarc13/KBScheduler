package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Address extends Activity {
    TextView custEndTime, custAddress, custStartTime;
    String endTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        custEndTime = (TextView) findViewById(R.id.endTime);
        custStartTime = (TextView) findViewById(R.id.startTime);
        endTime = getIntent().getStringExtra("");
        custEndTime.setText(endTime);
    }

    public void onClickAddress(View view) {
        String time = custStartTime.toString() + '-' + custEndTime.toString();

        Intent intent = new Intent(this, Confermation.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("date", getIntent().getStringExtra("date"));
        //intent.putExtra("address", custAddress.getText().toString());
        intent.putExtra("time", time);
        startActivity(intent);
        finish();
    }

    public void returnToCal(View view) {
        Intent intent = new Intent(this, DayView.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        startActivity(intent);
        finish();

    }
}
