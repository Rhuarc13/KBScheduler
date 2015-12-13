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
 *Menu
 *
 * This class is set up to give the user a base interface for the app
 * where they can navigate to the calendar or check their reservations
 **********************************************************************/
public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /**
     * takes you to the calendar
     */
    public void goToCal(View view) {
        Intent intent = new Intent(this, Calendar.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
        startActivity(intent);
        finish();
    }

    /**
     * takes you to your reservations
     */
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
