 package com.example.armour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

 public class ForgetPasswordActivity extends AppCompatActivity {

     private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        TextView textView5 = findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email= findViewById(R.id.EmailAddress);
                String userEmail = email.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (!userEmail.isEmpty() && userEmail.matches(emailPattern)) {
                    Intent intent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Please Enter a valid Email and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
 }
