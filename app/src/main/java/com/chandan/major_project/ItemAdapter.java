package com.chandan.major_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {

    ArrayList<ItemPOJO> itemPOJOArrayList;
    OnAddToCartListener listener;

    public interface OnAddToCartListener {
        void onAddToCart(ItemPOJO item);
    }

    public ItemAdapter(ArrayList<ItemPOJO> itemPOJOArrayList) {
        this.itemPOJOArrayList = itemPOJOArrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.product_name.setText(itemPOJOArrayList.get(position).getProductName());
        holder.imageView.setImageResource(itemPOJOArrayList.get(position).getProductImage());
        holder.product_price.setText(itemPOJOArrayList.get(position).getProductPrice());

        holder.addToCart.setOnClickListener(v -> {
            listener.onAddToCart(itemPOJOArrayList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return itemPOJOArrayList.size();
    }

    static class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView product_name, product_price;
        Button addToCart;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            addToCart = itemView.findViewById(R.id.add_to_cart);
        }
    }
}

