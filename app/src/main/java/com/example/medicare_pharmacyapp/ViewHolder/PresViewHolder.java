package com.example.medicare_pharmacyapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare_pharmacyapp.Interface.ItemClickListener;
import com.example.medicare_pharmacyapp.R;

public class PresViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListener listener;



    public PresViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.pres_image);

    }

    public void setItemClickListener(ItemClickListener listener){

        this.listener = listener;

    }



    @Override
    public void onClick(View view) {

        listener.onClick(view, getAdapterPosition(), false);

    }
}
