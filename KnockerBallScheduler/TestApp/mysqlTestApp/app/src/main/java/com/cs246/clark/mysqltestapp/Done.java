package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/***********************************************************************
 *
 *                  ~~ KnockerBall Schedule App ~~
 *
 * This application is intended to serve as an interface to communicate
 * with a MySQL Database to create and store scheduling information for
 * the KnockerBall rental service. The app will provide users with a means
 * of scheduling a reserved time to rent the KnockerBalls and will express
 * those reservations on a master calendar for the renter to manage.
 *
 * 10/26/2015
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford
 * @version 1.0
 **********************************************************************/

/***********************************************************************
 *Done
 *
 * This is a simple confirmation page that tells the user that the
 * date reservation succeeded or failed
 **********************************************************************/
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
