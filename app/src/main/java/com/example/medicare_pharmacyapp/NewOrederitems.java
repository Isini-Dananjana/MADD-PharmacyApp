package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NewOrederitems extends AppCompatActivity {

    private String showItems;
    private String DelteOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orederitems);

        showItems = getIntent().getExtras().get("newOrderItems").toString();


        AlertDialog.Builder builder = new AlertDialog.Builder(NewOrederitems.this);
        builder.setTitle("Have you already delivered this products ?");



    }
}