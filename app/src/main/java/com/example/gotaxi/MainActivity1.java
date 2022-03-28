package com.example.gotaxi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity1.this, R.color.goTaxi));
        //status bar color
        getSupportActionBar().hide();           //hide intent title.
    }
}