package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare_pharmacyapp.Model.Users;
import com.example.medicare_pharmacyapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.invoke.ConstantCallSite;

import io.paperdb.Paper;


public class Login_signupActivity extends AppCompatActivity {

    private Button joinNowButton , LoginButton;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);


        joinNowButton=(Button)findViewById(R.id.main_Join_now_btn);
        LoginButton=(Button)findViewById(R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent intent= new Intent(Login_signupActivity.this,LoginActivity.class);
                                               startActivity(intent);
                                           }
                                       }
        );

        joinNowButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 Intent intent1= new Intent(Login_signupActivity.this,RegisterActivity.class);
                                                 startActivity(intent1);
                                             }
                                         }
        );
        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if (UserPhoneKey != "" && UserPasswordKey != "")
        {
            if (!TextUtils.isEmpty(UserPhoneKey)  &&  !TextUtils.isEmpty(UserPasswordKey))
            {
                AllowAccess(UserPhoneKey, UserPasswordKey);

                loadingBar.setTitle("You are already Logged in");
                loadingBar.setMessage("Please wait.....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }


    }

    private void AllowAccess(final String phone,final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child(phone).exists()){

                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone)){

                        if(usersData.getPassword().equals(password) ){

                            /*if(password =="admin"){

                                Toast.makeText(Login_signupActivity.this, "Admin Successfully logged-in..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(Login_signupActivity.this, AdminPanel.class);
                                startActivity(intent);

                            }else {*/

                                Toast.makeText(Login_signupActivity.this, "Successfully logged-in...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(Login_signupActivity.this, HomeActivity.class);
                                Prevalent.currentonlineUser = usersData;
                                startActivity(intent);

                           // }

                        }

                        else {

                            loadingBar.dismiss();
                            Toast.makeText(Login_signupActivity.this, "Password is incorrect..", Toast.LENGTH_SHORT).show();

                        }

                    }

                }else if(dataSnapshot.child("Admins").child(phone).exists()){

                    Users usersData = dataSnapshot.child("Admins").child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone)){

                        if(usersData.getPassword().equals(password) ){

                            //if(password =="admin"){

                                Toast.makeText(Login_signupActivity.this, "Admin Successfully logged-in..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(Login_signupActivity.this, AdminPanel.class);
                                startActivity(intent);

                           /* }else {

                                Toast.makeText(Login_signupActivity.this, "Successfully logged-in...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(Login_signupActivity.this, HomeActivity.class);
                                Prevalent.currentonlineUser = usersData;
                                startActivity(intent);

                            }*/

                        }

                        else {

                            loadingBar.dismiss();
                            Toast.makeText(Login_signupActivity.this, "Password is incorrect..", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

                else{

                    Toast.makeText(Login_signupActivity.this, "Account with this number do not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}