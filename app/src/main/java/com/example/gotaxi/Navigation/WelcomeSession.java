package com.example.gotaxi.Navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.gotaxi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WelcomeSession extends AppCompatActivity {

    boolean isDriver;
    String user_phone;
    String user_email;
    ImageView driver;
    ImageView passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_session);
        getWindow().setStatusBarColor(ContextCompat.getColor(WelcomeSession.this, R.color.goTaxi));
        //status bar color
        getSupportActionBar().hide();           //hide intent title.

//      Declaration of Variables
        Intent intent = getIntent();
        user_phone = intent.getStringExtra("user_phone");

        driver = (ImageView) findViewById(R.id.driver);
        passenger = (ImageView) findViewById(R.id.passenger);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        //create query in which the phones orders in in specific path.
        Query checkUser = reference.orderByChild("user_phone").equalTo(user_phone);
        //check if the user is exists.
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {//if the DB credentials exists please enter the data base and do the series commands.
                    isDriver = (Boolean) snapshot.child(user_phone).child("isDriver").getValue();
                    user_email = (String) snapshot.child(user_phone).child("user_email").getValue();
                    //buffer wait to retrieve data from data base.
                    if (isDriver) {
                        //if you are manager you cant go to admin session.
                        passenger.setVisibility(ImageView.GONE);
                    }else{
                        driver.setVisibility(ImageView.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        menus


//        DRIVER










//        Costumer

    }
}