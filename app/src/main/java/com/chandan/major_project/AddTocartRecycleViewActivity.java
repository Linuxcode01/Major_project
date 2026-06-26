package com.chandan.major_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddTocartRecycleViewActivity extends AppCompatActivity {

    TextView total_price_view;
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_tocart_recycle_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ArrayList<ItemPOJO> cartArr = new ArrayList<>();

        cartArr.add(new ItemPOJO(R.drawable.profile,"Person" ,"120"));
        cartArr.add(new ItemPOJO(R.drawable.delete_icon,"Person-delete" ,"320"));

        total_price_view = findViewById(R.id.total_price);

        int totalPrice = 0;

        for (ItemPOJO item : cartArr) {
            totalPrice += Integer.parseInt(item.getProductPrice());
        }

        total_price_view.setText(String.valueOf(totalPrice));

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddToCartAdapter addToCartAdapter = new AddToCartAdapter(cartArr);
        recyclerView.setAdapter(addToCartAdapter);

    }
}