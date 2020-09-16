package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddCategory extends AppCompatActivity {

    private ImageView medicine , mumNbaby;
    private  ImageView weightloss , vitamins;
    private ImageView personalCare, health;
    private String panel;
    private String CatNameAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

    

        medicine = (ImageView)findViewById(R.id.medicine);
        mumNbaby = (ImageView)findViewById(R.id.mombaby);
         weightloss= (ImageView)findViewById(R.id.weightloss);
        vitamins = (ImageView)findViewById(R.id.vitamin);
        personalCare = (ImageView)findViewById(R.id.personal);
        health = (ImageView)findViewById(R.id.health) ;



        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategory.this, AddItem.class);
                intent.putExtra("category","medicine");
                startActivity(intent);
            }
        });

        personalCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategory.this, AddItem.class);
                intent.putExtra("category","personalCare");
                startActivity(intent);
            }
        });

        mumNbaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategory.this, AddItem.class);
                intent.putExtra("category","mumNbaby");
                startActivity(intent);
            }
        });

       weightloss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategory.this, AddItem.class);
                intent.putExtra("category","weightloss");
                startActivity(intent);
            }
        });

        vitamins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategory.this, AddItem.class);
                intent.putExtra("category","vitamins");
                startActivity(intent);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategory.this, AddItem.class);
                intent.putExtra("category","health");
                startActivity(intent);
            }
        });

        weightloss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategory.this, AddItem.class);
                intent.putExtra("category","weightloss");
                startActivity(intent);
            }
        });




    }
}