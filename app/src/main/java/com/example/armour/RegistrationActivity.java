package com.example.armour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.resources.TextAppearance;

public class RegistrationActivity extends AppCompatActivity {
    private TextView to_login;
    private Button done_register;
    private EditText name,reg_email,reg_contact,reg_password;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        backBtn = findViewById(R.id.back_btn);
        name = findViewById(R.id.edt_name);
        reg_contact = findViewById(R.id.edt_phone);
        reg_email = findViewById(R.id.edt_email);
        reg_password = findViewById(R.id.edt_password);

        backBtn.setOnClickListener(view -> {
            Intent intRegister = new Intent(RegistrationActivity.this,LoginActivity.class);
            startActivity(intRegister);
        });

        done_register = findViewById(R.id.startBtn);
        done_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = reg_email.getText().toString();
                String userPassword = reg_password.getText().toString();
                String userContact = reg_contact.getText().toString();
                String userName = name.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (userEmail.isEmpty() || userPassword.isEmpty() || userContact.isEmpty() || userName.isEmpty()) {

                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!userEmail.matches(emailPattern)) {
                    Toast.makeText(RegistrationActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intRegister = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intRegister);
                }
            }
        });

        to_login = findViewById(R.id.loginTxt);
        to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intRegisterTxt = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intRegisterTxt);
            }
        });
    }
}