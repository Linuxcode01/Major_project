package com.chandan.major_project;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {

    TextInputEditText username_edit, password_edit;
    MaterialButton login_btn;
    MaterialTextView registerPage_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username_edit = findViewById(R.id.user_id);
        password_edit = findViewById(R.id.password);
        registerPage_btn = findViewById(R.id.registerPage);
        login_btn = findViewById(R.id.login);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = username_edit.getText().toString().trim();
                String password = password_edit.getText().toString().trim();

                if(userId.isBlank() || password.isBlank()){
                    Toast.makeText(LoginActivity.this,
                            "Fill all the credentials",LENGTH_LONG).show();
                }

                if(userId == null || password == null){
                    Toast.makeText(LoginActivity.this,
                            "Fill all the credentials",LENGTH_LONG).show();

                }

                MyDbHelper db = new MyDbHelper(LoginActivity.this);
                Cursor cursor;

               cursor =  db.findUser(userId,password);

                if (cursor != null && cursor.moveToFirst()) {

                    String user_Id = cursor.getString(cursor.getColumnIndexOrThrow("user_name"));
//                    Toast.makeText(LoginActivity.this, "User found: " + user_Id, LENGTH_LONG).show();
//                    String user_Id = cursor.getString(cursor.getColumnIndexOrThrow("user_name"));
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userId",user_Id);
                    startActivity(intent);
                    cursor.close();
                    finish();

                } else {

                    Toast.makeText(LoginActivity.this, "User not found", LENGTH_LONG).show();
                }

                db.close();

            }
        });

        registerPage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, ResisterActivity.class);
                startActivity(in);
            }
        });

    }
}