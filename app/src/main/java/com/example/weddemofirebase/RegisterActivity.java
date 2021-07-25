package com.example.weddemofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
private FirebaseAuth auth;
ImageButton registerBtn;
EditText email, password, confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        registerBtn = findViewById(R.id.registerBtn);
        email = findViewById(R.id.emailView);
        password = findViewById(R.id.passwordEditView);
        confirmPassword = findViewById(R.id.confirmPasswordEditView);


        registerBtn.setOnClickListener(v -> {
            if (password.getText().toString().equals(confirmPassword.getText().toString()) && !password.getText().toString().equals("")) {

                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {

                                Log.d("TAG", "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
//                          updateUI(null);
                            }
                        });
            }
            else {
                Toast.makeText(this, "fill carefully", Toast.LENGTH_SHORT).show();
            }
        });
    }

}