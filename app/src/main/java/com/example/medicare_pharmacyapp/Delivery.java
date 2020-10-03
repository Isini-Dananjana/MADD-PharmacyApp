package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicare_pharmacyapp.Model.DeliveryModel;
import com.example.medicare_pharmacyapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Delivery extends AppCompatActivity {

    DeliveryModel deliver;
    private Button btn_DelConfirm;
    private EditText name2,phone2,addr2,city2;
    private String Cname , PhoneNo,Address, City;
    private String DeliveryRandomKey;
    private DatabaseReference DeliveryRef;
    private ProgressDialog loadingBar;
    private String totalAmount = " ";
    private String delTot = " ";
    private String finalTot = "";
    private String phoneNo;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this,"Total Price = "+totalAmount+"LKR",Toast.LENGTH_SHORT);



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



    }




    private void Check()
    {
        Cname = name2.getText().toString();
        PhoneNo = phone2.getText().toString();
        Address= addr2.getText().toString();
        City=city2.getText().toString();


        if (Cname.length()==0)
        {
            name2.requestFocus();
            name2.setError("Name cannot be empty!");
        }

        else if(!Cname.matches("[a-zA-Z ]+")  )
        {
            isValidName(Cname);
            /*Toast.makeText(this,"Please provide your full name.. ",Toast.LENGTH_SHORT);*/
            name2.requestFocus();
            name2.setError("Enter only alphabetical characters!");
        }
        else if (PhoneNo.length()==0)
        {
            phone2.requestFocus();
            phone2.setError("Phone number cannot be empty!");
        }
  /*      else if(PhoneNo.length()<10 || PhoneNo.length()>10)
        {
            isValidPhoneNum(PhoneNo);
            phone2.requestFocus();
            phone2.setError("Enter valid Phone number!");
        }*/
        else if(!(PhoneNo.matches("[0-9]{10}")))
        {
            isValidPhoneNum(PhoneNo);
            phone2.requestFocus();
            phone2.setError("Invalid phone number!");


        }
        else if(Address.length()==0)
        {
            addr2.requestFocus();
            addr2.setError("Address cannot be empty!");
        }
        else if(City.length()==0)
        {
            city2.requestFocus();
            city2.setError("City cannot be empty!");
        }
        else
        {
            ConfirmOrder();
        }
    }

    public boolean isValidName(String Cname) {
        if (Cname.matches("[a-zA-Z ]+"))
        {
            return true;
        }
        else
            return false;

    }

    public boolean isValidPhoneNum(String PhoneNo) {


        if(PhoneNo.matches("[0-9]{10}")){
            return true;
        }
        else
            return false;
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