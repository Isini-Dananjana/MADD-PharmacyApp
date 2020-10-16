package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.medicare_pharmacyapp.Model.Feedback;
import com.example.medicare_pharmacyapp.Prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.SmileRating;
import com.hsalf.smilerating.BaseRating;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/*
import dev.yuganshtyagi.smileyrating.SmileyRatingView;
*/


public class FeedbackActivity extends AppCompatActivity {

private static final String TAG = "MainActivity";

private SmileRating smileRating;
private EditText review;
private Button submit;
private String UserReview,smile;
private int level;
private DatabaseReference Ref;
Feedback feedback;


    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final String saveCurrentdate,saveCurrentTime;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        smileRating = findViewById(R.id.ratingView);


        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dinamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                int level = smileRating.getRating();

                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        break;
                }

            }
        });
        //smileRating=(SmileRating)findViewById(R.id.ratingView);
         review =(EditText)findViewById(R.id.review_box);
        submit =(Button)findViewById(R.id.button);
        feedback =new Feedback();
        Ref=FirebaseDatabase.getInstance().getReference().child("Feedback");


        submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Check();
            if(TextUtils.isEmpty(smile))
            {
                Toast.makeText(FeedbackActivity.this,"Please select a smile value",Toast.LENGTH_SHORT);
            }
            else if(TextUtils.isEmpty(UserReview))
            {
                Toast.makeText(FeedbackActivity.this,"Please provide your review.. ",Toast.LENGTH_SHORT);
            }
            else
            {
                smile = String.valueOf(smileRating.getRating());
                UserReview =review.getText().toString();
                String date = saveCurrentdate;
                String time = saveCurrentTime;
                String phone = Prevalent.currentonlineUser.getPhone();

                feedback.setDate(date);
                feedback.setTime(time);
                feedback.setPhone(phone);
                feedback.setFeedback(UserReview);
                feedback.setSmileRating(smile);

                Ref.child(phone).setValue(feedback);//under feedback table insert the feedback details
                Toast.makeText(FeedbackActivity.this, "Submitted your feedback successfully", Toast.LENGTH_SHORT).show();
            }
        }
    });



        /*smileyRating.setOnSmileySelectionListener(new SmileyRating.OnSmileySelectedListener() {

            @Override
            public void onSmileySelected(SmileyRating.Type type) {
                if (SmileyRating.Type.GREAT == type) {
                    Log.i(TAG, "Wow, You gave high rating Thank you");
                }
                else if (SmileyRating.Type.GOOD == type) {
                    Log.i(TAG, "You gave Good rating");
                }
                else if (SmileyRating.Type.OKAY == type) {
                    Log.i(TAG, "You gave okay rating");
                }
                else if (SmileyRating.Type.BAD == type) {
                    Log.i(TAG, "You gave bad rating.Tell us why");
                }
                else if (SmileyRating.Type.TERRIBLE == type) {
                    Log.i(TAG, "You gave Terrible rating.Tell us why");
                }
                int rating = type.getRating();
            }*/





        }

    private void Check() {

        smile = String.valueOf(smileRating.getRating());
        UserReview =review.getText().toString();

        if(TextUtils.isEmpty(smile))
        {
            Toast.makeText(this,"Please select a smile value",Toast.LENGTH_SHORT);
        }
        else if(TextUtils.isEmpty(UserReview))
        {
            Toast.makeText(this,"Please provide your review.. ",Toast.LENGTH_SHORT);
        }
         else
        {
            ConfirmFeedback();
        }
    }

    private void ConfirmFeedback() {
        final String saveCurrentdate,saveCurrentTime;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());


        smile = String.valueOf(smileRating.getRating());
                UserReview =review.getText().toString();
                String date = saveCurrentdate;
                String time = saveCurrentTime;
                String phone = Prevalent.currentonlineUser.getPhone();

                feedback.setDate(date);
                feedback.setTime(time);
                feedback.setPhone(phone);
                feedback.setFeedback(UserReview);
                feedback.setSmileRating(smile);

                Ref.child(phone).setValue(feedback);
                Toast.makeText(FeedbackActivity.this,
                        "Submitted your feedback successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FeedbackActivity.this,HomeActivity.class);
                startActivity(intent);
    }


 /*   @Override
    public void onRatingSelected(int level, boolean reselected) {
        Log.i(TAG, "Rated as: " + level + " - " + reselected);
    }

    @Override
    public void onSmileySelected(int smiley, boolean reselected) {
        switch (smiley) {
            case SmileRating.BAD:
                Log.i(TAG, "Bad");
                break;
            case SmileRating.GOOD:
                Log.i(TAG, "You gave Good rating");
                break;
            case SmileRating.GREAT:
                Log.i(TAG, "Wow, You gave high rating Thank you");
                break;
            case SmileRating.OKAY:
                Log.i(TAG, "You gave okay rating");
                break;
            case SmileRating.TERRIBLE:
                Log.i(TAG, "You gave Terrible rating.Tell us why");
                break;

        }
    }*/
}