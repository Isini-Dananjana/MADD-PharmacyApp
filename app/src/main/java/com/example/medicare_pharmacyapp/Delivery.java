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
    private String Cname , PhoneNo,Address, City,saveCurrentdate,saveCurrentTime;
    /*  private static final int GalleryPick = 1;*/
    /* private Uri ImageUri;*/
    private String DeliveryRandomKey, downloadImageUrl;
    /*    private StorageReference PrescriptionsImagesRef;*/
    private DatabaseReference DeliveryRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        DeliveryRef = FirebaseDatabase.getInstance().getReference().child("Delivery");

        btn_DelConfirm = (Button) findViewById(R.id.btn_confirmDel);
        name2 = (EditText) findViewById(R.id.name2);
        phone2 = (EditText) findViewById(R.id.phone2);
        addr2 = (EditText) findViewById(R.id.addr2);
        city2 = (EditText) findViewById(R.id.city2);
        loadingBar = new ProgressDialog(this);

        btn_DelConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Delivery.this, Confirm_Order.class);
                startActivity(i);
            }
        });

        btn_DelConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateProductData();
            }
        });
    }

    private void ValidateProductData()
    {
        Cname = name2.getText().toString();
        PhoneNo = phone2.getText().toString();
        Address= addr2.getText().toString();
        City=city2.getText().toString();


        if (TextUtils.isEmpty(Cname))
        {
            Toast.makeText(this, "Please enter your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(PhoneNo))
        {
            Toast.makeText(this, "Please enter your phone no...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Address))
        {
            Toast.makeText(this, "Please enter your address...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(City))
        {
            Toast.makeText(this, "Please enter your city...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }
    }
    private void StoreProductInformation() {

        loadingBar.setIcon(R.drawable.plus);
        loadingBar.setTitle("Medicare");
        loadingBar.setMessage("Please Wait......");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        DeliveryRandomKey = saveCurrentdate + saveCurrentTime;

        SaveProductInfoToDatabase();

    }
    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("DeliveryId", DeliveryRandomKey);
        productMap.put("date", saveCurrentdate);
        productMap.put("time", saveCurrentTime);
        productMap.put("CustomerName", Cname);

        productMap.put("PhoneNo", PhoneNo);
        productMap.put("Address", Address);
        productMap.put("City", City);

        DeliveryRef.child(DeliveryRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent1 = new Intent(Delivery.this, Confirm_Order.class);
                            startActivity(intent1);

                            loadingBar.dismiss();
                            Toast.makeText(Delivery.this, " Successfull..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(Delivery.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}