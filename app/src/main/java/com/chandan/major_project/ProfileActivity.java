package com.chandan.major_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity {

    TextInputEditText full_name_edit, email_edit, contact_edit, address_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        full_name_edit = findViewById(R.id.full_name);
        email_edit = findViewById(R.id.email_address);
        contact_edit = findViewById(R.id.contact);
        address_edit = findViewById(R.id.address);

        Intent in = getIntent();
        String full = in.getStringExtra("full_name");
        String email = in.getStringExtra("email");
        String contact = in.getStringExtra("contact");
        String address = in.getStringExtra("address");

        full_name_edit.setText(full);
        email_edit.setText(email);
        contact_edit.setText(contact);
        address_edit.setText(address);
    }
}