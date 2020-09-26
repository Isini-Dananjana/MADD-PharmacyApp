package com.example.medicare_pharmacyapp.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare_pharmacyapp.Model.Feedback;
import com.example.medicare_pharmacyapp.R;

import java.util.List;

public class feedbackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public  TextView  txtphone,txtdate, txttime,txtrating,txtfeedback;
    public feedbackViewHolder(@NonNull View itemView) {
        super(itemView);

        txtphone =itemView.findViewById(R.id.Afeedback_phone);
        txtdate =itemView.findViewById(R.id.Afeedback_date);
        txttime =itemView.findViewById(R.id.Afeedback_time);
        txtrating =itemView.findViewById(R.id.Afeedback_rating);
        txtfeedback =itemView.findViewById(R.id.Afeedback_);
    }

    @Override
    public void onClick(View v) {

    }
/*private Context mCtx;
    private List<Feedback> feedbackList;

    public feedbackViewHolder(Context mCtx, List<Feedback> feedbackList) {
        this.mCtx = mCtx;
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.admin_feedback_layout, null);
        ProductHolder holder = new ProductHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
            Feedback feedback = feedbackList.get(position);
            holder.phone.setText(feedback.getPhone());
            holder.date.setText(feedback.getDate());
            holder.time.setText(feedback.getTime());
            holder.rating.setText(feedback.getSmileRating());
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{

        TextView phone,date, time,rating;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            phone =itemView.findViewById(R.id.Afeedback_phone);
            date =itemView.findViewById(R.id.Afeedback_date);
            time =itemView.findViewById(R.id.Afeedback_time);
            rating =itemView.findViewById(R.id.Afeedback_rating);
        }
    }*/
}
