package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goToCal(View view) {
        Intent intent = new Intent(this, Calendar.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
        startActivity(intent);
        finish();
    }

    public void viewReservation(View view) {
        Intent intent = new Intent(this, viewReservation.class);
        intent.putExtra("hope", "Tesing again");
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
