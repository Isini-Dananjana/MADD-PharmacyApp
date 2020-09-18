package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Prescri_Delivery extends AppCompatActivity {

    private ImageView imgPresc11;
    private Button btn_PresConfirmDel;
    private EditText name1,phone1,addr1,city1;
    private String Cname , PhoneNo,Address, City,saveCurrentdate,saveCurrentTime;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String PrescriptionsRandomKey, downloadImageUrl;
    private StorageReference PrescriptionsImagesRef;
    private DatabaseReference PrescriptionsRef;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescri__delivery);

        PrescriptionsImagesRef = FirebaseStorage.getInstance().getReference().child("Prescriptions Images");
        PrescriptionsRef= FirebaseDatabase.getInstance().getReference().child("Prescriptions");


        btn_PresConfirmDel = (Button) findViewById(R.id.btn_PresConfirmDel);

        imgPresc11 = (ImageView)findViewById(R.id.imgPresc11);
        name1 = (EditText)findViewById(R.id.name1);
        phone1 = (EditText)findViewById(R.id.phone1);
        addr1 = (EditText)findViewById(R.id.addr1);
        city1 = (EditText)findViewById(R.id.city1);
        loadingBar = new ProgressDialog(this);

        btn_PresConfirmDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Prescri_Delivery.this,ConfirmPrescri_order.class);
                startActivity(i);
            }
        });


        imgPresc11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });
        btn_PresConfirmDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ValidateProductData();
            }
        });


    }

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            imgPresc11.setImageURI(ImageUri);
        }
    }

    private void ValidateProductData()
    {
        Cname = name1.getText().toString();
        PhoneNo = phone1.getText().toString();
        Address= addr1.getText().toString();
        City=city1.getText().toString();

        if (ImageUri == null)
        {
            Toast.makeText(this, "Please upload your prescription...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Cname))
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

        PrescriptionsRandomKey = saveCurrentdate + saveCurrentTime;


        final StorageReference filePath = PrescriptionsImagesRef.child(ImageUri.getLastPathSegment() + PrescriptionsRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(Prescri_Delivery.this, "Error: " + message, Toast.LENGTH_LONG).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(Prescri_Delivery.this, "Prescription uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(Prescri_Delivery.this, "got the Prescription image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });

    }

    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("PrescId", PrescriptionsRandomKey);
        productMap.put("date", saveCurrentdate);
        productMap.put("time", saveCurrentTime);
        productMap.put("CustomerName", Cname);
        productMap.put("image", downloadImageUrl);
        productMap.put("PhoneNo", PhoneNo);
        productMap.put("Address", Address);
        productMap.put("City", City);

        PrescriptionsRef.child(PrescriptionsRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent1 = new Intent(Prescri_Delivery.this, ConfirmPrescri_order.class);
                            startActivity(intent1);

                            loadingBar.dismiss();
                            Toast.makeText(Prescri_Delivery.this, " Successfull..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(Prescri_Delivery.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}