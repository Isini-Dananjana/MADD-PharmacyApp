package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class admin_newOrders extends AppCompatActivity {

    private String order;
    Button showItem;
    TextView showAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_new_orders);

        showItem = (Button)findViewById(R.id.show_allproducts_btn) ;
        showAlert = (TextView)findViewById(R.id.order_del) ;
        order = getIntent().getExtras().get("newOrders").toString();

        showItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_newOrders.this, NewOrederitems.class);
                intent.putExtra("newOrderItems","showItems");
                startActivity(intent);

            }
        });


       showAlert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               AlertDialog alertDialog = new AlertDialog.Builder(admin_newOrders.this)
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