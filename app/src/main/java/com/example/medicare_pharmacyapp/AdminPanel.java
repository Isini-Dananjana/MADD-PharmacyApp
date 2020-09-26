package com.example.medicare_pharmacyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;

public class AdminPanel extends AppCompatActivity {

    private Button addItem;
    private Button manageItem;
    private Button newOrder;
    private Button newprescrption;
    private Button logout;
    private Button stock;
    private Button feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Paper.init(this);

        addItem = (Button)findViewById(R.id.addItems_btn);
       addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, AddCategory.class);
                intent.putExtra("panel","addItem");
                startActivity(intent);
            }
        });

        manageItem = (Button)findViewById(R.id.MangeItems_btn);
        manageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this,HomeActivity.class);
                intent.putExtra("Admin","Admin");
                startActivity(intent);
            }
        });

        newOrder = (Button)findViewById(R.id.newOrders_btn);
        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, admin_newOrders.class);
                intent.putExtra("newOrders","newOrder");
                startActivity(intent);
            }
        });

        newprescrption = (Button)findViewById(R.id.btn_prescription);
        newprescrption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, newPrescriptions.class);
                intent.putExtra("newPres","newprescription");
                startActivity(intent);
            }
        });

        stock = (Button)findViewById(R.id.btn_stock);
        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, AdminStock.class);

                startActivity(intent);
            }
        });

        logout = (Button)findViewById(R.id.admin_logout_btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();

                Intent intent = new Intent(AdminPanel.this,Login_signupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        feedback = (Button)findViewById(R.id.btn_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();

                Intent intent = new Intent(AdminPanel.this,AdminFeedback.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}