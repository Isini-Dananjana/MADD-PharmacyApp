package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Prescri_Delivery extends AppCompatActivity {

    Button btn_PresConfirmDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescri__delivery);


        btn_PresConfirmDel = findViewById(R.id.btn_PresConfirmDel);
        btn_PresConfirmDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Prescri_Delivery.this,Confirm_Order.class);
                startActivity(i);
            }
        });

      /*  //Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button
*/
    }
 /*   //back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            //end the activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }*/
    //back button
}