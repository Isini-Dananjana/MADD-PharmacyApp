/*
package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicare_pharmacyapp.Model.AdminOrders;
import com.example.medicare_pharmacyapp.Model.Cart;
import com.example.medicare_pharmacyapp.Model.Products;
import com.example.medicare_pharmacyapp.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ViewPrescriptions extends AppCompatActivity {


    private RecyclerView productsList;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference orders;


    private  String userID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescriptions);

        userID = getIntent().getStringExtra("uid");

        productsList = findViewById(R.id.products_list);
        productsList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        productsList.setLayoutManager(layoutManager);


       // ordersRef = FirebaseDatabase.getInstance().getReference().child("Prescription Orders");


        orders = FirebaseDatabase.getInstance().getReference().child("Prescription Orders").child(userID);



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrders> options=
                new FirebaseRecyclerOptions.Builder<AdminOrders>()
                        .setQuery(ProductsRef, AdminOrders.class)
                        .build();

    }
}
}
*/
