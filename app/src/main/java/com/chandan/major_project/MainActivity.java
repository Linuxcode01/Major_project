package com.chandan.major_project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton menu_btn;
    RecyclerView recyclerView;

    TextView name_text, email_text;

    private String full_name = "";
    private String email = "";
    private String user_name = "";
    private String address = "";
    private String contact = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent in = getIntent();
        String userId = in.getStringExtra("userId");

//      fetching data of user
        getData(userId);

        drawerLayout = findViewById(R.id.drawer_layout);
        menu_btn = findViewById(R.id.menu_btn);
        navigationView = findViewById(R.id.nav_view);

        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(item ->{
            int id = item.getItemId();

            if(id == R.id.home){

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

            } else if (id == R.id.profile) {

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("full_name",full_name);
                intent.putExtra("email",email);
                intent.putExtra("contact",contact);
                intent.putExtra("address",address);
                startActivity(intent);

            } else if (id == R.id.card) {

                Intent intent = new Intent(MainActivity.this, AddTocartRecycleViewActivity.class);
                startActivity(intent);

            } else if (id == R.id.logout) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });


        ArrayList<ItemPOJO> itemPOJOS = ProductManager.getInstance().getProductItem();
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Sumsang S24","120000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Nokia 512 ","35000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Sumsang S24","120500"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Motorola rar","85000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Iphone 16","120000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Sumsang S24","120000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Nokia 512 ","35000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Sumsang S24","120500"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Motorola rar","85000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Iphone 16","120000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Sumsang S24","120000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Nokia 512 ","35000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Sumsang S24","120500"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Motorola rar","85000"));
        itemPOJOS.add(new ItemPOJO(R.drawable.profile,"Iphone 16","120000"));

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemAdapter itemAdapter = new ItemAdapter(itemPOJOS);

        itemAdapter.listener = item -> {
          CartManager.getInstance().addItem(item);
            Toast.makeText(this, item.getProductName() + " added to cart", Toast.LENGTH_SHORT).show();
        };

        recyclerView.setAdapter(itemAdapter);

    }

    void getData(String userId) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            MyDbHelper db = new MyDbHelper(this);
            Cursor cursor = db.fetchUser(userId);

            if (cursor != null && cursor.moveToFirst()) {
                full_name = cursor.getString(cursor.getColumnIndexOrThrow("full_name"));
                email    = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                user_name = cursor.getString(cursor.getColumnIndexOrThrow("user_name"));
                address  = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                contact  = cursor.getString(cursor.getColumnIndexOrThrow("contact"));
                cursor.close();
            }

            handler.post(() -> {
                setNavigationData();
            });
        });
    }
    private void setNavigationData() {
        View headerView = navigationView.getHeaderView(0);

        name_text = headerView.findViewById(R.id.header_name);
        email_text = headerView.findViewById(R.id.header_email);

        name_text.setText(full_name);
        email_text.setText(email);
    }
}