package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.example.madd_pharmacyapp.Seller.SellerProductCategoryActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class editItems extends AppCompatActivity {

    private Button applyChangesBtn, deleteBtn;
    private EditText name, price, description;
    private ImageView imageView;

    private String productID = "";
    private DatabaseReference productsRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        productID = getIntent().getStringExtra("pid");
        productsRef = FirebaseDatabase.getInstance().getReference().child("products").child(productID);



        applyChangesBtn = findViewById(R.id.btn_apply_changes);
        name = findViewById(R.id.product_name_edit);
        price = findViewById(R.id.product_price_edit);
        description = findViewById(R.id.product_description_edit);
        imageView = findViewById(R.id.product_image_edit);
        deleteBtn = findViewById(R.id.btn_delete_product);



        displayScpecificProductInfo();


        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                applyChanges();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteThisProduct();

            }
        });


    }

    private void deleteThisProduct() {

        productsRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(editItems.this, "Successfully delete", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(editItems.this, AddCategory.class);
                startActivity(intent);
                finish();


            }
        });


    }

    private void applyChanges() {

        String pName = name.getText().toString();
        String pPrice = price.getText().toString();
        String pDescription = description.getText().toString();


        if(pName.equals("")){

            Toast.makeText(this, "Enter product name", Toast.LENGTH_SHORT).show();

        }else if(pPrice.equals("")){

            Toast.makeText(this, "Enter product price", Toast.LENGTH_SHORT).show();

        }else if(pDescription.equals("")){

            Toast.makeText(this, "Enter product description", Toast.LENGTH_SHORT).show();

        }else{

            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productID);
            productMap.put("description", pDescription);
            productMap.put("price", pPrice);
            productMap.put("name", pName);


            productsRef.updateChildren(productMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(editItems.this, "Changes applied successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(editItems.this, AddCategory.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });
        }


    }

    private void displayScpecificProductInfo() {

        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String pName = dataSnapshot.child("name").getValue().toString();
                    String pPrice = dataSnapshot.child("price").getValue().toString();
                    String pDescription = dataSnapshot.child("description").getValue().toString();
                    String pImage = dataSnapshot.child("image").getValue().toString();


                    name.setText(pName);
                    price.setText(pPrice);
                    description.setText(pDescription);
                    Picasso.get().load(pImage).into(imageView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
