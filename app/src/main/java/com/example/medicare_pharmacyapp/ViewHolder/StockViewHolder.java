package com.example.medicare_pharmacyapp.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare_pharmacyapp.Interface.ItemClickListener;
import com.example.medicare_pharmacyapp.R;

    public class StockViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView txtProductName, txtProductQuantity, txtProductPrice , date , time , stockTot;
        public ItemClickListener listener;
        public Button edit;

        public StockViewHolder(@NonNull View itemView) {
            super(itemView);

           // imageView = (ImageView) itemView.findViewById(R.id.product_image);
            txtProductName = (TextView) itemView.findViewById(R.id.product_name);
            txtProductQuantity = (TextView) itemView.findViewById(R.id.product_quantity);
            txtProductPrice = (TextView) itemView.findViewById(R.id.product_price);
            date = (TextView) itemView.findViewById(R.id.Stock_date_time);
            edit = itemView.findViewById(R.id.edit_stock_btn);
            stockTot = itemView.findViewById(R.id.stock_tot);


        }

        public void setItemClickListener(ItemClickListener listener){

            this.listener = listener;

        }



        @Override
        public void onClick(View view) {

            listener.onClick(view, getAdapterPosition(), false);

        }
    }
