package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddItem extends AppCompatActivity {

    private String CatName , description,price, pName,saveCurrentdate,saveCurrentTime;
    private Button addNewProductBtn;
    private EditText inputProductNme,inputProductDes,inputProductPri;
    private ImageView inputProductImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("product Images");
        ProductsRef= FirebaseDatabase.getInstance().getReference();

        addNewProductBtn = (Button)findViewById(R.id.add_new_product);
        inputProductImage = (ImageView)findViewById(R.id.select_image);
        inputProductNme = (EditText)findViewById(R.id.item_name);
        inputProductDes = (EditText)findViewById(R.id.item_description);
        inputProductPri = (EditText)findViewById(R.id.product_price);
        loadingBar = new ProgressDialog(this);

        inputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });


        addNewProductBtn.setOnClickListener(new View.OnClickListener() {
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
            inputProductImage.setImageURI(ImageUri);
        }
    }


    private void ValidateProductData()
    {
        description = inputProductDes.getText().toString();
        price = inputProductPri.getText().toString();
        pName= inputProductNme.getText().toString();


        if (ImageUri == null)
        {
            Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(price))
        {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pName))
        {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        }

        else if ((pName.length()) > 30)
        {
            inputProductNme.setError("Cannot enter more than 30 words for product name");
             }

        else if ((description.length()) < 10)
        {
            inputProductDes.setError("Description should have more than 10 Characters");
        }

        else if(!price.matches("[0-9]+"))
        {
            isValidatePrice(price);
            inputProductPri.requestFocus();
            inputProductPri.setError("ENTER A NUMERICAL VALUE ");
        }
        else
        {
            StoreProductInformation();
        }
    }

        public boolean isValidatePrice(String price) {
            if(price.matches("[0-9]+")){
                return true;
            }
            else
                return false;
        }



    private void StoreProductInformation()
    {
        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentdate + saveCurrentTime;


        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        final DatabaseReference productsRef;
        productsRef = FirebaseDatabase.getInstance().getReference();

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AddItem.this, "Error: " + message, Toast.LENGTH_LONG).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AddItem.this, "Product Image uploaded Successfully...", Toast.LENGTH_LONG).show();

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

                            Toast.makeText(AddItem.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();


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
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentdate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", description);
        productMap.put("image", downloadImageUrl);
        productMap.put("category", CatName);
        productMap.put("price", price);
        productMap.put("pname", pName);

        ProductsRef.child("products").child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {

                           // Toast.makeText(AddItem.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                           // loadingBar.dismiss();

                            Intent intent1 = new Intent(AddItem.this, AddItem.class);
                            startActivity(intent1);

                           loadingBar.dismiss();
                           Toast.makeText(AddItem.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddItem.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
