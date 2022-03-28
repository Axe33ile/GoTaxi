package com.example.gotaxi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setStatusBarColor(ContextCompat.getColor(Register.this, R.color.goTaxi));
        //status bar color
        getSupportActionBar().hide();           //hide intent title.

/*            *******************************************
                        VARIABLE DECLARATION
              *******************************************
 */







/*            *******************************************
                SPINNER SPINNER SPINNER SPINNER SPINNER
              *******************************************
 */
//        Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}