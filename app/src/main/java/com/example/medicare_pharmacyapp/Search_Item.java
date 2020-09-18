package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Search_Item extends AppCompatActivity {

    Button btn;
    ImageButton cbtn;
    Button uploadpresbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__item);


        btn = findViewById(R.id.bandbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Search_Item.this, com.example.medicare_pharmacyapp.Display_Item.class);
                startActivity(i);
            }
        });

        cbtn = findViewById(R.id.cartbtn);
        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Search_Item.this,My_Cart.class);
                startActivity(i);
            }
        });
/*
        uploadpresbtn = findViewById(R.id.uploadpresbtn);
        uploadpresbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Search_Item.this,Prescri_Delivery.class);
                startActivity(i);
            }
        });*/
    }
}