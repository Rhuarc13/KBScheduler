package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignIn extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
    }

    public void signInButton(View view){

        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }



}
