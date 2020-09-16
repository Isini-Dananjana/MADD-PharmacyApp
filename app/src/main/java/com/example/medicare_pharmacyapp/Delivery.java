package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Delivery extends AppCompatActivity {

    private Button btn_DelConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
      /*  //Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button*/


        btn_DelConfirm = (Button) findViewById(R.id.btn_confirmDel);
        btn_DelConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openConfirm();
            }
        });
    }
    public void openConfirm(){

        Intent intent = new Intent(this,Confirm_Order.class);
        startActivity(intent);


    }
   /* //back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            //end the activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    //back button*/

}