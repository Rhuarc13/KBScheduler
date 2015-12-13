package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Done extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        Intent old = getIntent();
        

        BackgroundTask backgroundTask = new BackgroundTask("email", old.getStringExtra("first_name"), old.getStringExtra("last_name"), old.getStringExtra("email"), old.getStringExtra("date"), old.getStringExtra("start"), old.getStringExtra("end"), old.getStringExtra("street"), old.getStringExtra("city"), old.getStringExtra("state"), old.getStringExtra("duration"));
        backgroundTask.execute();
    }

    public void onClickDone (View view) {
        Intent intent =  new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }
}
