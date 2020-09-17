package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.medicare_pharmacyapp.R;

public class My_Cart extends AppCompatActivity {

    public Button editbtn1, editbtn2,editbtn3, order;

    Button orderbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__cart);

        editbtn1 = (Button) findViewById(R.id.changebtn1);
        editbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(My_Cart.this, com.example.madd_pharmacyapp.Display_Item.class);
                startActivity(i);
            }
        });

        orderbtn = (Button) findViewById(R.id.orderbtn);
        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(My_Cart.this, Checkout.class);
                startActivity(i);
            }
        });

    }
    public void showAlertDialog(View v){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("MediCare");
        alert.setMessage("Do you want to remove this item?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(My_Cart.this,"your item is removed",Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(My_Cart.this,"your item is still in the cart",Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();

    }
}