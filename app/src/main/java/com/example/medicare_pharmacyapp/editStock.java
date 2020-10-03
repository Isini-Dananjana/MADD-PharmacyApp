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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class editStock extends AppCompatActivity {



    private Button applyChangesBtn, deleteBtn;
    private EditText name, price, quantity;
    private ImageView imageView;

    private String productID = "";
    private DatabaseReference productsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stock);


        productID = getIntent().getStringExtra("pid");
        productsRef = FirebaseDatabase.getInstance().getReference().child("Stock").child(productID);

        applyChangesBtn = findViewById(R.id.edit_stock);
        name = findViewById(R.id.product_name);
        price = findViewById(R.id.product_price);
        quantity = findViewById(R.id.product_quantity);
        imageView = findViewById(R.id.product_image_maintain);


        displaySpecificProductInfo();

        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });





    }

    private void deleteThisProduct() {

        productsRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

               Toast.makeText(editStock.this, "Successfully delete", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(editStock.this, Check_Stock.class);
                startActivity(intent);
                finish();


            }
        });
    }

    private void applyChanges() {
        String pName = name.getText().toString();
        String pPrice = price.getText().toString();
        String pQuantity= quantity.getText().toString();

        if(pName.equals(""))
        {
            Toast.makeText(this,"Write product name",Toast.LENGTH_SHORT).show();
        }
        else if(pPrice.equals(""))
        {
            Toast.makeText(this,"Write product price",Toast.LENGTH_SHORT).show();
        }
        else if(pQuantity.equals(""))
        {
            Toast.makeText(this,"Write product Descrition",Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productID);
            productMap.put("quantity", pQuantity);
            productMap.put("price", pPrice);
            productMap.put("pname", pName);

            productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(editStock.this,"Changes applied succesfully",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(editStock.this, Check_Stock.class);
                        startActivity(intent);
                        finish();
                    }

                }
            });

        }


    }

    private void displaySpecificProductInfo() {
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String pName = snapshot.child("pname").getValue().toString();
                    String pPrice = snapshot.child("price").getValue().toString();
                    String pQuantity = snapshot.child("quantity").getValue().toString();

                    name.setText(pName);
                    price.setText(pPrice);
                    quantity.setText(pQuantity);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}

