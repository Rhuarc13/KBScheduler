package com.cs246.clark.mysqltestapp;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
 *RegisterNewUser
 *
 *This class sets up fields that the user can enter to create a new
 * user account that is then registered in the database. The information
 * are then stored and can be used to log in
 **********************************************************************/
public class RegisterNewUser extends AppCompatActivity {

    //set some local vars and textViews
    EditText firstNameView;
    EditText lastNameView;
    EditText passwordView;
    EditText passwordConfirmView;
    EditText emailView;
    EditText phoneView;
    volatile Response response;
    //we'll use this for some system output to debug
    private static final String TAG = "REGISTER";
    private static final String SUCCESS = "SUCCESS: Customer creation complete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        response = new Response();
    }

    /*********************************************************************
    * This will run the background task to communicate with the MySQL DB
    **********************************************************************/
    public void registerButtonPress(View view) {

        //set the TextViews so we can pull the user input
        firstNameView       = (EditText) findViewById(R.id.firstName);
        lastNameView        = (EditText) findViewById(R.id.lastName);
        passwordView        = (EditText) findViewById(R.id.password);
        passwordConfirmView = (EditText) findViewById(R.id.passwordConfirm);
        emailView           = (EditText) findViewById(R.id.email);
        phoneView           = (EditText) findViewById(R.id.phone);

        //set the data in the user class from the TextViews
        User user = new User();

        user.setFirstName(firstNameView.getText().toString());
        user.setLastName (lastNameView.getText().toString());
        user.setPassword (passwordView.getText().toString());
        user.setEmail    (emailView.getText().toString());
        user.setPhone    (phoneView.getText().toString());


        //ensure they have matching passwords
        if(!user.getPassword().equals(passwordConfirmView.getText().toString())){
            Toast toast = Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG);
            Log.i(TAG, "User typed in mismatched passwords");
            toast.setGravity(Gravity.CENTER, 0, 25);
            toast.show();
            passwordView.setFocusable(true);
            passwordView.requestFocus();
        } else {
            //Create a server class and run it to send the info to the DB
            String method = "register";

            //send the data to backgroundTask to speak to the server
            BackgroundTask backgroundTask = new BackgroundTask(user, method, response);
            backgroundTask.execute();
            Log.i(TAG, "All your info are belong to us!");

            //locks the activity until we get a response from the server - prevents errors
            Lock lock = new ReentrantLock();
            int waitTime = 0;
            synchronized (lock) {
                while (response.getCode() == 0 && waitTime < 26) {
                    try {
                        lock.wait(100);
                        waitTime++;
                    } catch (InterruptedException e) {
                        Log.e(TAG, "Ran into an InterruptedException");
                    }
                }

                try {
                    lock.wait(1500);

                    //did we connect to the server? (200 is good)
                    if (response.getCode() == 200) {
                        if (response.getText().equals(SUCCESS)) {
                            Intent intent = new Intent(this, Menu.class);
                            String name = firstNameView.getText().toString() + ' ' + lastNameView.getText().toString();
                            intent.putExtra("name", name);
                            intent.putExtra("phone", phoneView.getText().toString());
                            intent.putExtra("email", emailView.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            //if not, why? the if/else will tell us what went wrong
                            Log.e(TAG, "Incorrect response string: " + response.getText());
                            emailView.setText("");
                            passwordView.setText("");
                            passwordConfirmView.setText("");
                            Toast.makeText(this, "Account exists with this email", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Error occurred connecting to the database", Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    Log.e(TAG, "Experienced Interruption: " + e.toString());
                }
            }
        }
     }
}



