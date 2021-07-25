package com.example.weddemofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
Button registerBtn;
ImageButton loginBtn;
EditText email, password;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        registerBtn = findViewById(R.id.registerBtnOnLoginPage);
        email = findViewById(R.id.emailViewLogin);
        password = findViewById(R.id.passwordEditViewLogin);
        loginBtn = findViewById(R.id.loginBtn);

        registerBtn.setOnClickListener(View -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(View -> {
            String emailStr = email.getText().toString();
            String passwordStr = password.getText().toString();

            if(!passwordStr.equals("")) {
                auth.signInWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(task -> {

                            if(task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Check if email or password is correct", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else {
                Toast.makeText(this, "Fill the fields first", Toast.LENGTH_SHORT).show();
            }


        });

    }
}