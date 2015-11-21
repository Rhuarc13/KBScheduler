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

public class RegisterNewUser extends AppCompatActivity {

    EditText firstNameView;
    EditText lastNameView;
    EditText passwordView;
    EditText passwordConfirmView;
    EditText emailView;
    EditText phoneView;
    private static final String TAG = "REGISTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
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
            boolean validated = false;

            BackgroundTask backgroundTask = new BackgroundTask(user, method);
            backgroundTask.execute();
            Log.i(TAG, "All your info are belong to us!");
            Lock lock = new ReentrantLock();
            while (backgroundTask.isAlive()) {
                try {
                    lock.lock();
                    wait(100);
                    lock.unlock();
                } catch (InterruptedException e) {
                    Log.e(TAG, "Wait was interrupted: " + e.toString());
                }
            }
            stopLockTask();
            Log.i(TAG, "Background Task is dead");
            String response = backgroundTask.getStatusString();
            if (response.equals("Success")) {
                Intent intent = new Intent(this, Calendar.class);
                Log.i(TAG, "Starting Calendar Activity");
                startActivity(intent);
                finish();
            } else {
                System.out.println(response);
                Toast.makeText(this, "Invalid username", Toast.LENGTH_LONG).show();
                Log.i(TAG, "Error creating the user");
                emailView.setText("");
                passwordView.setText("");
                passwordConfirmView.setText("");
            }
        }
     }
}



