package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.os.Bundle;

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
 *View Reservation
 *
 *This class allows you to see all your current reservations that are
 * linked to your account on the server
 **********************************************************************/
public class viewReservation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);
    }
}
