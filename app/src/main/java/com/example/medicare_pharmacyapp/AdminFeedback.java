package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicare_pharmacyapp.Model.Feedback;
import com.example.medicare_pharmacyapp.ViewHolder.feedbackViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AdminFeedback extends AppCompatActivity {

   /* RecyclerView recyclerView;
    feedbackViewHolder  adapter;

    List<Feedback> feedbacks;*/
private Query feedbackRef;
private RecyclerView recyclerView;
RecyclerView.LayoutManager layoutManager;
private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedback);

        /*feedbackRef = FirebaseDatabase.getInstance().getReference().child("Feedback")
                .orderByChild("feedback")
                .startAt("app")
                .endAt("app\uf8ff");*/
      feedbackRef = FirebaseDatabase.getInstance().getReference().child("Feedback");

        recyclerView = findViewById(R.id.recycle_feed);
        recyclerView.setHasFixedSize(true);//
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminFeedback.this, AdminPanel.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Feedback> options =
                new FirebaseRecyclerOptions.Builder<Feedback>()
                .setQuery(feedbackRef ,Feedback.class)
                .build();
        FirebaseRecyclerAdapter<Feedback ,feedbackViewHolder> adapter =
                new FirebaseRecyclerAdapter<Feedback, feedbackViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull feedbackViewHolder holder, int position, @NonNull Feedback feedback) {

               holder.txtphone.setText(feedback.getPhone());
                holder.txtdate.setText(feedback.getDate());
                holder.txttime.setText(feedback.getTime());
                holder.txtrating.setText(feedback.getSmileRating());
                holder.txtfeedback.setText(feedback.getFeedback());


            }

            @NonNull
            @Override
            public feedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_feedback_layout,parent,false);
                feedbackViewHolder holder = new feedbackViewHolder(view);

                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}