package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class newPrescriptions extends AppCompatActivity {


    private String prescription;
    private ImageView imgPres;
    TextView showAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_prescriptions);
        showAlert = (TextView)findViewById(R.id.order_delete) ;

        prescription = getIntent().getExtras().get("newPres").toString();

        imgPres = (ImageView)findViewById(R.id.img_presc);

        imgPres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(newPrescriptions.this, ViewPrescriptions.class);
                intent.putExtra("viewPresc","imgPres");
                startActivity(intent);
            }
        });

        showAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(newPrescriptions.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Have you delivered this Order Items")
                        .setMessage("Delete this oder:")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        //set negative button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"nothing changed",Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();


            }
        });



    }
}