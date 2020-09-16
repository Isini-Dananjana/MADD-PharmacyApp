package com.example.medicare_pharmacyapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.MenuItem;
import android.view.View;

public class Checkout extends AppCompatActivity {

    private Button btn_CheckDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
     /*   //Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button

*/
        //go to delivery page
        btn_CheckDel = (Button) findViewById(R.id.btn_continue);
        btn_CheckDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDelivery();
            }
        });
    }
    public void openDelivery(){

        Intent intent = new Intent(this,Delivery.class);
        startActivity(intent);


    }

    /*//back button
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
