package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewPrescriptions extends AppCompatActivity {

    private String viewPresct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescriptions);

        viewPresct = getIntent().getExtras().get("viewPresc").toString();


    }
}