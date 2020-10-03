package com.example.medicare_pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.medicare_pharmacyapp.Model.Cart;
import com.example.medicare_pharmacyapp.Prevalent.Prevalent;
import com.example.medicare_pharmacyapp.R;
import com.example.medicare_pharmacyapp.ViewHolder.CartViewHolder;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
/*import com.tomer.fadingtextview.FadingTextView;

import static com.tomer.fadingtextview.FadingTextView.SECONDS;*/

public class My_Cart extends AppCompatActivity {

   private RecyclerView recyclerView;
   private RecyclerView.LayoutManager layoutManager;
   private Button Next;
   private TextView totAmount, txtMsg1;





   private int totPrice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__cart);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Next = (Button) findViewById(R.id.next);
        totAmount = (TextView) findViewById(R.id.total_price);
        txtMsg1 = (TextView) findViewById(R.id.msg1);



        Next = findViewById(R.id.next);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                totAmount.setText("Total Price = "+String.valueOf(totPrice)+"LKR");

                Intent intent = new Intent(My_Cart.this,Delivery.class);
                intent.putExtra("Total Price",String.valueOf(totPrice));
                startActivity(intent);
                finish();

                Toast.makeText(My_Cart.this,"Total price for the items you purchased is "+totPrice+" LKR",Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();


        CheckOrderState();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
               .setQuery(cartListRef.child("User View")
               .child(Prevalent.currentonlineUser.getPhone()).child("products"), Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull final Cart cart) {
                cartViewHolder.txtProductQuantity.setText("Quantity = "+cart.getQuantity());
                cartViewHolder.txtProductPrice.setText("Price = "+cart.getPrice()+" LKR");
                cartViewHolder.txtProductName.setText(cart.getPName());

                int oneTypeProductTPrice  = oneProductPrice(Integer.parseInt(cart.getQuantity()) , Integer.parseInt(cart.getPrice()));
                 // int oneTypeProductTPrice = ((Integer.parseInt(cart.getPrice()))) * Integer.parseInt(cart.getQuantity());
                   totPrice = totPrice + oneTypeProductTPrice;








                cartViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Remove"

                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(My_Cart.this);
                        builder.setTitle("Cart Options:");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(i == 0){

                                    Intent intent = new Intent(My_Cart.this,Display_Item.class);
                                    intent.putExtra("pid",cart.getPid());
                                    startActivity(intent);
                                }
                                if(i == 1){

                                    cartListRef.child("User View")
                                            .child(Prevalent.currentonlineUser.getPhone())
                                            .child("products")
                                            .child(cart.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if(task.isSuccessful()){

                                                        Toast.makeText(My_Cart.this,"item removed from cart",Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(My_Cart.this,HomeActivity.class);
                                                        startActivity(intent);

                                                    }

                                                }
                                            });

                                }
                            }
                        });

                        builder.show();



                    }
                });

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }



    private void CheckOrderState(){

        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentonlineUser.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    String shippingState = snapshot.child("state").getValue().toString();
                    String userName = snapshot.child("name").getValue().toString();

                    if(shippingState.equals("shipped")){

                        totAmount.setText("Dear "+ userName + "\n order is successfully shipped.");
                        recyclerView.setVisibility(View.GONE);

                        txtMsg1.setVisibility(View.VISIBLE);
                        txtMsg1.setText("Congratulations, your final order has been placed successfully. Soon you will receive your order at your doorstep.");
                        Next.setVisibility(View.GONE);

                        Toast.makeText(My_Cart.this,"you can purchase more products, once you received your first final order",Toast.LENGTH_SHORT);


                    }
                    else if(shippingState.equals("not shipped")){

                        totAmount.setText("Dear "+ userName + "\n your order is not yet shipped.");
                        recyclerView.setVisibility(View.GONE);

                        txtMsg1.setVisibility(View.VISIBLE);
                        Next.setVisibility(View.GONE);

                        Toast.makeText(My_Cart.this,"you can purchase more products, once you received your first final order",Toast.LENGTH_SHORT);



                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /*public int  calculate_oneProductPrice(Cart cart, int q, int  p){
        int oneProductPrice = oneProductPrice(Integer.parseInt(cart.getQuantity()) , Integer.parseInt(cart.getPrice()));
        return oneProductPrice;

    }*/

    protected int oneProductPrice(int price, int quantity) {

        return price*quantity;
    }


}