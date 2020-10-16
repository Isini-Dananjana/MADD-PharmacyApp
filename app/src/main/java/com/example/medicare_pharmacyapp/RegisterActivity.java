package com.example.medicare_pharmacyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    protected EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        InputName = (EditText) findViewById(R.id.register_user_name_Input);
        InputPassword = (EditText) findViewById(R.id.register_password_Input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_num_Input);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateAccount();


            }
        });



    }

    private void CreateAccount() {

        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        //String MobilePattern = "[0-9]{10}";

        if(TextUtils.isEmpty(name)){

            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){

            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(password)){

            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
        }
       /* else if(phone.length()<10|| phone.length()>10){
            Toast.makeText(this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
        }*/

        else{

            isValidatePhone(phone);
            isValidatePassword(password);
            if(isValidatePhone(phone)==true && isValidatePassword(password)==true) {
                loadingBar.setTitle("Create Account");
                loadingBar.setMessage("Please wait..");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                ValidatephoneNumber(name, phone, password);
            }else {
                Toast.makeText(this, "Please enter valid phone number or enter more than 4 character password", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public boolean isValidatePassword(String password) {

        if(password.length()<4){
             return false;
        }
        else {
            return true;
        }
    }

    public boolean isValidatePhone(String phone) {
        if(phone.matches("[0-9]{10}")){
            return true;
        }
        else
            return false;
    }

    private void ValidatephoneNumber(final String name, final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("Users").child(phone).exists())){

                    HashMap<String, Object> userdataMap = new HashMap<>();

                    userdataMap.put("name", name);
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){

                                        Toast.makeText(RegisterActivity.this,
                                                "Your account has been created..",
                                                Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new
                                                Intent(RegisterActivity.this,
                                                LoginActivity.class);
                                        startActivity(intent);

                                    }else{

                                        loadingBar.dismiss();

                                        Toast.makeText(RegisterActivity.this,
                                                "Registration failed",
                                                Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });


                }else{

                    Toast.makeText(RegisterActivity.this, "This phone number already have an account", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, Login_signupActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
