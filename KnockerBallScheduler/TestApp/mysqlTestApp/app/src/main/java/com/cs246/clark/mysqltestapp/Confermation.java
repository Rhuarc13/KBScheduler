package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Confermation extends Activity {
    TextView custEmail;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confermation);
        custEmail = (TextView) findViewById(R.id.customerEmail);
        email = getPreferences(0).getString("email", "");
        custEmail.setText(email);
        System.out.println("The email is :" + email);

    }
}
