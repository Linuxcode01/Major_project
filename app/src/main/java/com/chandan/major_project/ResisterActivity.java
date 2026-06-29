package com.chandan.major_project;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.zip.Inflater;

public class ResisterActivity extends AppCompatActivity {

    TextInputEditText full_name, email, user_name, password, address, contact;
    MaterialTextView loginPage;
    MaterialButton register_btn;
    private boolean isInvalidInput(String value) {
        return value == null || value.isEmpty() || value.equalsIgnoreCase("null");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resister);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        full_name = findViewById(R.id.res_fullName);
        email = findViewById(R.id.res_email);
        user_name = findViewById(R.id.res_userName);
        password = findViewById(R.id.res_password);
        address = findViewById(R.id.res_address);
        contact = findViewById(R.id.res_contact);
        loginPage = findViewById(R.id.loginPage);
        register_btn = findViewById(R.id.register);

        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ResisterActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = full_name.getText().toString().trim();
                String emailId = email.getText().toString().trim();
                String userName = user_name.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Contact = contact.getText().toString().trim();

                if (isInvalidInput(name) || isInvalidInput(emailId) || isInvalidInput(userName)
                        || isInvalidInput(Password) || isInvalidInput(Address) || isInvalidInput(Contact)) {
                    Toast.makeText(ResisterActivity.this, "Fill is Null", LENGTH_LONG).show();
                    return;
                }

                if(Password.length() < 8){
                    Toast.makeText(ResisterActivity.this,
                            "Password length should be greater than 8",LENGTH_LONG).show();
                    return;
                }

                if(name.isEmpty() || emailId.isEmpty() || userName.isEmpty() || Password.isEmpty()
                        || Address.isEmpty() || Contact.isEmpty()){
                    Toast.makeText(ResisterActivity.this,"Fill the all fields",LENGTH_LONG).show();
                    return;
                }

                try {
                    MyDbHelper myDbHelper = new MyDbHelper(ResisterActivity.this);
                    myDbHelper.add_user(name,emailId,userName,Contact,Address,Password);
                    myDbHelper.close();
                    Toast.makeText(ResisterActivity.this,"Registration Successfully",LENGTH_LONG).show();

                    // sending to login screen
                    Intent intent = new Intent(ResisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                    Toast.makeText(ResisterActivity.this,"Registration UnSuccessfully",LENGTH_LONG).show();
                    System.out.println("Exception : " + e);

                }



            }
        });


    }
}