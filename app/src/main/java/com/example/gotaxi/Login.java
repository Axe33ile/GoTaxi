package com.example.gotaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    //          ***  Declare variables ***          ***  Declare variables ***       ***  Declare variables ***

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextView  register;
    EditText userPass,userPhone;
    Button signInButton;
    String emailfromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(Login.this, R.color.goTaxi));
        //status bar color
        getSupportActionBar().hide();           //hide intent title.


    //attributes
        TextView register = (TextView) findViewById(R.id.register_forward);
        userPass = (EditText) findViewById(R.id.user_Password);
        userPhone = (EditText) findViewById(R.id.user_Phone);
        signInButton = (Button) findViewById(R.id.signInButton1);



//login option
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_phone = userPhone.getText().toString();
                String password = userPass.getText().toString();
                //showing the next message if user didn't enter right phone number.
                if (TextUtils.isEmpty(user_phone)) {
                    userPhone.setError("יש להזין מספר טלפון");
                    return;
                }

                if (user_phone.length() != 10) {//change later to 10 digits
                    userPhone.setError("יש להזין מספר טלפון בעל 10 ספרות");
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    userPass.setError("יש להזין סיסמא");
                    return;
                }


    //connection to database

                //connect into firebase and enter users DB
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                //create query in which the phones orders in in specific path.
                Query checkUser = reference.orderByChild("user_phone").equalTo(user_phone);
                //check if the user is exists.
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {//If phonenumber exists in DB
                            emailfromDB = snapshot.child(user_phone).child("user_email").getValue(String.class).trim();//pull email from realtime DB to use in Auth
                            String user_name_fromDB = snapshot.child(user_phone).child("user_name").getValue(String.class).trim();//get the user_name from the phone_number we get.
                            //if login successful go to welcome session
                            mAuth.signInWithEmailAndPassword(emailfromDB, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                //calling intro activity.
                                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                                intent.putExtra("user_phone", user_phone);
                                                intent.putExtra("user_name", user_name_fromDB);
                                                intent.putExtra("user_email", emailfromDB);
                                                startActivity(intent);//if it exists move to welcome Session intent
                                            } else {
                                                Toast.makeText(Login.this, "אחד מהנתונים שהכנסת שגוי", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });



//      Move to register intent
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