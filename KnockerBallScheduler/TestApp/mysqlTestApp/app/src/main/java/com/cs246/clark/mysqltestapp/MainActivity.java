package com.cs246.clark.mysqltestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
public class MainActivity extends AppCompatActivity {

    /*********************************************************************
    * On Create - sets the xml main layout
    **********************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*********************************************************************
    * Register 'button' takes you to the register activity
    **********************************************************************/
    public void registerButton(View view){

        Intent intent = new Intent(this, RegisterNewUser.class);
        startActivity(intent);
    }

    /*********************************************************************
     * Register 'button' takes you to the register activity
     **********************************************************************/
    public void signInButton(View view){

        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}
