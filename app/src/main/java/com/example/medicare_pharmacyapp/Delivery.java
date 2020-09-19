package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicare_pharmacyapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Delivery extends AppCompatActivity {

    private Button btn_DelConfirm;
    private EditText name2,phone2,addr2,city2;
    private String Cname , PhoneNo,Address, City;
    private String DeliveryRandomKey;
    private DatabaseReference DeliveryRef;
    private ProgressDialog loadingBar;
    private String totalAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

  /*      totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this,"Total Price = "+totalAmount+"LKR",Toast.LENGTH_SHORT);*/


        btn_DelConfirm = (Button) findViewById(R.id.btn_confirmDel);
        name2 = (EditText) findViewById(R.id.name2);
        phone2 = (EditText) findViewById(R.id.phone2);
        addr2 = (EditText) findViewById(R.id.addr2);
        city2 = (EditText) findViewById(R.id.city2);
        /*loadingBar = new ProgressDialog(this);*/

        btn_DelConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Check();
            }
        });

   /*     btn_DelConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Delivery.this, Confirm_Order.class);
                startActivity(i);
            }
        });
*/

    }


    private void Check()
    {
        Cname = name2.getText().toString();
        PhoneNo = phone2.getText().toString();
        Address= addr2.getText().toString();
        City=city2.getText().toString();



        if(TextUtils.isEmpty(Cname))
        {
            Toast.makeText(this,"Please provide your full name.. ",Toast.LENGTH_SHORT);
        }
        else if(TextUtils.isEmpty(PhoneNo))
        {
            Toast.makeText(this,"Please provide your phone number.. ",Toast.LENGTH_SHORT);
        }
        else if(TextUtils.isEmpty(Address))
        {
            Toast.makeText(this,"Please provide your address.. ",Toast.LENGTH_SHORT);
        }
        else if(TextUtils.isEmpty(City))
        {
            Toast.makeText(this,"Please provide your city name.. ",Toast.LENGTH_SHORT);
        }
        else
        {
            ConfirmOrder();
        }
    }

    private void ConfirmOrder()
    {
       final String saveCurrentdate,saveCurrentTime;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        final DatabaseReference ordersRef =  FirebaseDatabase.getInstance().getReference().child("Orders").
                child(Prevalent.currentonlineUser.getPhone());
        HashMap<String,Object> ordersMap = new HashMap<>();

        ordersMap.put("totalAmount", totalAmount);
        ordersMap.put("name", name2.getText().toString());
        ordersMap.put("phone", phone2.getText().toString());
        ordersMap.put("address", addr2.getText().toString());
        ordersMap.put("city", city2.getText().toString());
        ordersMap.put("date", saveCurrentdate);
        ordersMap.put("time", saveCurrentTime);
        ordersMap.put("state","not shipped");


        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("Cart List").
                            child("User View").child(Prevalent.currentonlineUser.getPhone()).
                            removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Delivery.this,"Your final order has placed successful..",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Delivery.this,Confirm_Order.class);
                               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }

            }
        });
    }


}