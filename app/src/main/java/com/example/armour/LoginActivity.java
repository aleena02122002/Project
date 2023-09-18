package com.example.armour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView forget, register,emailerror,passworderror;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        emailerror= findViewById(R.id.email_error);
        passworderror= findViewById(R.id.password_error);

        login = findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (userEmail.isEmpty() || !userEmail.matches(emailPattern)) {
                    emailerror.setText(R.string.EmailError);
                } else if (userPassword.isEmpty()) {
                    emailerror.setText("");
                    passworderror.setText(R.string.please_enter_your_password);
                } else {
                    emailerror.setText("");
                    passworderror.setText("");
                    Intent intLogin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intLogin);
                }

            }
        });

        forget = findViewById(R.id.forgetTxt);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intforget = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intforget);
            }
        });

        register = findViewById(R.id.registerTxt);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intRegisterTxt = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intRegisterTxt);
            }
        });
    }
}