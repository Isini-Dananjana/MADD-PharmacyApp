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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicare_pharmacyapp.Model.Stock;
import com.example.medicare_pharmacyapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminStock extends AppCompatActivity {
    private String CatName , qunatity,price, pName,saveCurrentdate,saveCurrentTime;
    private Button addNewStocktBtn;
    FloatingActionButton check;
    private EditText inputProductNme,inputProductQuan,inputProductPri;


    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stock);


        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("product Images");
        ProductsRef= FirebaseDatabase.getInstance().getReference();

        addNewStocktBtn = (Button)findViewById(R.id.add_new_stock);
        check = findViewById(R.id.fab);

        inputProductNme = (EditText)findViewById(R.id.product_name);
        inputProductQuan = (EditText)findViewById(R.id.product_quantity);
        inputProductPri = (EditText)findViewById(R.id.product_price);
        loadingBar = new ProgressDialog(this);



        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminStock.this, Check_Stock.class);
                startActivity(intent1);

            }
        });

        addNewStocktBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ValidateProductData();
            }
        });
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


    }


    private void ValidateProductData()
    {
        qunatity = inputProductQuan.getText().toString();
        price = inputProductPri.getText().toString();
        pName= inputProductNme.getText().toString();


         if (TextUtils.isEmpty(qunatity))
        {
            Toast.makeText(this, "Please write product quantity...", Toast.LENGTH_SHORT).show();
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

         else if(!qunatity.matches("[0-9]+"))
         {
             isValidateQty(qunatity);
             inputProductQuan.setError("PLEASE ENTER A NUMERICAL VALUE");
         }

         else if(!price.matches("[0-9]+"))
         {
             isValidatePrice( price);
             inputProductPri.requestFocus();
             inputProductPri.setError("PLEASE ENTER A NUMERICAL VALUE ");
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

    public boolean isValidateQty(String qunatity) {
        if(price.matches("[0-9]+")){
            return true;
        }
        else
            return false;
    }


    private void StoreProductInformation()
    {
        loadingBar.setTitle("Add New Product to stock");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentdate + saveCurrentTime;




        final DatabaseReference productsRef;
        productsRef = FirebaseDatabase.getInstance().getReference();


        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentdate);
        productMap.put("time", saveCurrentTime);
        productMap.put("quantity", qunatity);


        productMap.put("category", CatName);
        productMap.put("price", price);
        productMap.put("pname", pName);

        ProductsRef.child("Stock").child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())

                        {


                            Intent intent1 = new Intent(AdminStock.this, AdminStock.class);
                            startActivity(intent1);


                            loadingBar.dismiss();
                            Toast.makeText(AdminStock.this, "Stock added successfully..", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AdminStock.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
