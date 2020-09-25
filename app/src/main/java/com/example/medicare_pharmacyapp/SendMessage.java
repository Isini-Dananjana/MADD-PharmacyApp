package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare_pharmacyapp.Model.AdminOrders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SendMessage extends AppCompatActivity {

    private EditText msgNumber,msgText;
    private Button send;
    private RecyclerView ordersList;
    private DatabaseReference ordersRef;
    Button stockItems;
    private String userID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        msgNumber = findViewById(R.id.msg_phone);
        msgText = findViewById(R.id.msg_text);
        send = findViewById(R.id.btnSend);





       userID = getIntent().getStringExtra("uid");
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(userID);

        displaySpecificProductInfo();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
                    {
                        sendSMS();
                    }
                    else
                    {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }


            }
        });



    }



    private void sendSMS()
    {
        String phoneNo = msgNumber.getText().toString().trim();
        String SMS = msgText.getText().toString().trim();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, SMS, null, null);
            Toast.makeText(this, "Message is sent", Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    private void displaySpecificProductInfo() {
        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String pPhone = snapshot.child("phone").getValue().toString();

                    msgNumber.setText(pPhone);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
