package com.example.gotaxi.Login_Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gotaxi.R;
import com.example.gotaxi.DatabaseUser.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    /*
        Variable Declaration
     */
    EditText regEmail, regPassword, regphoneNumber;
    Spinner spinner;
    Button registerEnd;
    boolean isPhoneExists;
    ImageView contact;
    FirebaseAuth mAuth;
    int chooseSt;
    boolean isDriver;
    FirebaseDatabase rootNode;
    int messageType; //messageType the user.
    int currentAdapterPosition; //user checks its messageType , and the variables show the position in spinner.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setStatusBarColor(ContextCompat.getColor(Register.this, R.color.goTaxi));
        //status bar color
        getSupportActionBar().hide();           //hide intent title.


        regEmail = (EditText) findViewById(R.id.regEmailAddress);
        regPassword = (EditText) findViewById(R.id.reg_user_password);
        regphoneNumber = (EditText) findViewById(R.id.reg_user_phone);
        registerEnd = (Button) findViewById(R.id.register);
//

//


/*            *******************************************
                SPINNER SPINNER SPINNER SPINNER SPINNER
              *******************************************
 */
        spinner = (Spinner) findViewById(R.id.spinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        registerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //auth

                rootNode = FirebaseDatabase.getInstance();
                FirebaseDatabase user_instance = FirebaseDatabase.getInstance();
                mAuth = FirebaseAuth.getInstance();




                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();
                String phoneNum = regphoneNumber.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    regEmail.setError("יש לציין אימייל");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    regPassword.setError("יש למלא סיסמא");
                    return;
                }
                if (password.length() < 6) {
                    regPassword.setError("הסיסמא חייבת להכיל לפחות 6 תווים");
                    return;
                }
                if (phoneNum.length() != 10) {
                    regphoneNumber.setError("מספר הטלפון צריך להיות בעל 10 ספרות");
                    return;
                }
                if (phoneNum.charAt(0) != '0' || phoneNum.charAt(1) != '5') {
                    regphoneNumber.setError("מספר הטלפון צריך להתחיל ב05 וספרה נוספת");
                    return;
                }
                for (int i = 0; i < 8; i++) {
                    if (phoneNum.charAt(i) < '0' || phoneNum.charAt(i) > '9') {
                        regphoneNumber.setError("ספרה לא תקינה במספר הטלפון");
                        return;
                    }
                }
                //בדיקת אימייל תקין
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    regEmail.setError("הכנס אימייל תקין");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this,"position:"+spinner.getSelectedItemPosition(), Toast.LENGTH_LONG).show();

                                    /*Email verification area */
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void aVoid) {
//                                            Toast.makeText(Register.this, "נשלח אימייל לאימות המשתמש", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(Register.this, "אירעה שגיאה נסה שנית", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });

//                                  if the person who register select "Driver" option , then we will receive 1 , and it will update in the database
//                                  in this way we can change the user menu later on.
                                    if (spinner.getSelectedItemPosition()==1) {
                                        isDriver = true;
                                    } else {
                                        isDriver = false;
                                    }
                                    UserHelperClass helperClass = new UserHelperClass(email, phoneNum, password, "", 0, isDriver);
                                    FirebaseDatabase.getInstance().getReference("users").child(phoneNum)
                                            .setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "המשתמש נוצר", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(Register.this, "אירעה שגיאה", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });


            }
        });





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        messageType = position;//getting the position the user selected.
        currentAdapterPosition = position; //another variable to store the currentPosition from the user.

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(Register.this, "Nothing selected", Toast.LENGTH_LONG).show();

    }


}

