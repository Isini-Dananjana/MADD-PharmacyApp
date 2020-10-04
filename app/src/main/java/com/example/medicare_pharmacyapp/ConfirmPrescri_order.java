package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare_pharmacyapp.Model.Pres_Delivery;
import com.example.medicare_pharmacyapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmPrescri_order extends AppCompatActivity {

    ImageView imgPresc11 ;
    Pres_Delivery deliver;
    Button Btn_dialog ;
    private DatabaseReference ordersRef;
    private DataSnapshot dataSnapshot;
    private Button btn_Change;
    private EditText name3, phone3, address3, city3;
    private String Cname , PhoneNo,Address, City;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private StorageReference PrescriptionsImagesRef;
    private TextView tvDelpres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_prescri_order);

        //edit text
        name3 = findViewById(R.id.name3);
        phone3 = findViewById(R.id.phone3);
        address3 = findViewById(R.id.address3);
        city3 = findViewById(R.id.city3);

        btn_Change = findViewById(R.id.btn_Change);
        deliver = new Pres_Delivery();
        tvDelpres = findViewById(R.id.tvDelpres);



        DatabaseReference ordersRef =  FirebaseDatabase.getInstance().getReference().child("Prescription Orders").
                child(Prevalent.currentonlineUser.getPhone());


        ordersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){
                    name3.setText(dataSnapshot.child("name").getValue().toString());
                    phone3.setText(dataSnapshot.child("phone").getValue().toString());
                    address3.setText(dataSnapshot.child("address").getValue().toString());
                    city3.setText(dataSnapshot.child("city").getValue().toString());



                    if(dataSnapshot.child("city").getValue().toString().equals("Kandy" ) || dataSnapshot.child("city").getValue().toString().equals("kandy") ){

                        int del = 100;


                        tvDelpres.setText("LKR"+Integer.toString(del));


                    }else if (dataSnapshot.child("city").getValue().toString().equals("Matale" ) || dataSnapshot.child("city").getValue().toString().equals("matale") ) {

                        int del = 200;


                        tvDelpres.setText("LKR"+Integer.toString(del));



                    }else if (dataSnapshot.child("city").getValue().toString().equals("Kurunegala" ) || dataSnapshot.child("city").getValue().toString().equals("kurunegala") ) {

                        int del = 300;


                        tvDelpres.setText("LKR"+Integer.toString(del));



                    }else if (dataSnapshot.child("city").getValue().toString().equals("Kegalle" ) || dataSnapshot.child("city").getValue().toString().equals("kegalle") ) {

                        int del = 270;




                        tvDelpres.setText("LKR" + Integer.toString(del));



                    }else {

                        int del = 350;

                        tvDelpres.setText("LKR" + Integer.toString(del));



                    }




                }else
                    Toast.makeText(getApplicationContext(), "No source to Display", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Prescription Orders").
                        child(Prevalent.currentonlineUser.getPhone());
                final String saveCurrentdate,saveCurrentTime;
                Calendar calendar = Calendar.getInstance();
                final SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentdate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());

                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        if(datasnapshot.hasChildren()){

                            try {
                                deliver.setName(name3.getText().toString().trim());
                                deliver.setPhone(phone3.getText().toString().trim());
                                deliver.setAddress(address3.getText().toString().trim());
                                deliver.setCity(city3.getText().toString().trim());
                                deliver.setDate(saveCurrentdate);
                                deliver.setTime(saveCurrentTime);
                                deliver.setState("not shipped");


                                DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("Prescription Orders").
                                        child(Prevalent.currentonlineUser.getPhone());


                                delref.setValue(deliver);

                                //Feedback to the user via a Toast
                                Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();

                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();



                            }




                        }else

                            Toast.makeText(getApplicationContext(),"No Source to Update", Toast.LENGTH_SHORT).show();


                    }







                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



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
                Toast.makeText(getApplicationContext(), "You can pay your charges,once the order is received at your doorstep.", Toast.LENGTH_SHORT).show();
                //set positive
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close the activity when this button is clicked
                        ConfirmPrescri_order.this.finish();


                    }
                });
                //create alert dialog
                AlertDialog alertDialog = builder.create();
                //show alert dialog
                alertDialog.show();
            }
        });

    }

    private void Check()
    {
        Cname = name3.getText().toString();
        PhoneNo = phone3.getText().toString();
        Address= address3.getText().toString();
        City=city3.getText().toString();


        if (Cname.length()==0)
        {
            name3.requestFocus();
            name3.setError("Name cannot be empty!");
        }

        else if(!Cname.matches("[a-zA-Z ]+")  )
        {
            isValidName(Cname);
            /*Toast.makeText(this,"Please provide your full name.. ",Toast.LENGTH_SHORT);*/
            name3.requestFocus();
            name3.setError("Enter only alphabetical characters!");
        }
        else if (PhoneNo.length()==0)
        {
            phone3.requestFocus();
            phone3.setError("Phone number cannot be empty!");
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
            phone3.requestFocus();
            phone3.setError("Invalid phone number!");


        }
        else if(Address.length()==0)
        {
            address3.requestFocus();
            address3.setError("Address cannot be empty!");
        }
        else if(City.length()==0)
        {
            city3.requestFocus();
            city3.setError("City cannot be empty!");
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



}