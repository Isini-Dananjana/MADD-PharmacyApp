package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfirmPrescri_order extends AppCompatActivity {

    Button Btn_dialog ;
    private Button btn_ChangeDel;
    private Button btn_RemoveDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_prescri_order);


/*
        //go to delivery page,if want to change details
        btn_ChangeDel = (Button) findViewById(R.id.btn_chnge);
        btn_ChangeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDeliChangeDetails();
            }
        });
        //go to delivery page,if want to remove details
        btn_RemoveDel = (Button) findViewById(R.id.btn_PresConfirmDel);
        btn_RemoveDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDeliRemoveDetails();
            }
        });*/

        //Alert
        Btn_dialog = findViewById(R.id.btn_confirm_alert);
        Btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmPrescri_order.this);

                //set title
                builder.setTitle("Medicare");
                //set icon
                builder.setIcon(R.drawable.plus);

                //set Message
                builder.setMessage("Your final order has already been placed.Soon it will be verified.Thank You.");

                //set positive
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close the activity when this button is clicked
                       /* ConfirmPrescri_order.this.finish();*/


                    }
                });
                //create alert dialog
                AlertDialog alertDialog = builder.create();
                //show alert dialog
                alertDialog.show();
            }
        });

    }
/*    //go to delivery page,if want to change details
    public void openDeliChangeDetails(){

        Intent intent = new Intent(this,Prescri_Delivery.class);
        startActivity(intent);


    }
    //go to delivery page,if want to remove details
    public void openDeliRemoveDetails(){

        Intent intent = new Intent(this,Prescri_Delivery.class);
        startActivity(intent);


    }*/

}