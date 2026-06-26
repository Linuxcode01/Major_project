package com.chandan.major_project;

import static android.widget.Toast.LENGTH_LONG;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.Holder> {

    ArrayList<ItemPOJO> cartArr;

    public AddToCartAdapter(ArrayList<ItemPOJO> cartArr) {
        this.cartArr = cartArr;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_to_cart_layout,parent,false);
        return new  Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.product_name.setText(cartArr.get(position).getProductName());
        holder.product_price.setText(cartArr.get(position).getProductPrice());
        holder.imageView.setImageResource(cartArr.get(position).getProductImage());

        holder.quantity.setText("1");
        holder.imageButton.setOnClickListener(v -> {
            cartArr.remove(cartArr.get(position));
            Toast.makeText(v.getContext(),"clicked item", LENGTH_LONG).show();
        });

    }

    @Override
    public int getItemCount() {
        return cartArr.size();
    }

    static class Holder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView product_name, product_price, quantity;
        ImageButton imageButton;
        public Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image_cart);
            product_name = itemView.findViewById(R.id.product_name_cart);
            product_price = itemView.findViewById(R.id.product_price_cart);
            quantity = itemView.findViewById(R.id.quantity_cart);
            imageButton = itemView.findViewById(R.id.delete_btn);
        }
    }
}
