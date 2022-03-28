package com.example.gotaxi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(Login.this, R.color.goTaxi));
        //status bar color
        getSupportActionBar().hide();           //hide intent title.



//      ***  Declare variables ***
        TextView register = (TextView) findViewById(R.id.register_forward);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewMember();
            }
        });
    }

    //move to register intent.
    public void registerNewMember() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}