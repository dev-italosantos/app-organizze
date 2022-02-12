package com.example.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze.R;
import com.example.organizze.config.ConfigFirebase;
import com.example.organizze.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        Button buttonEntrar = findViewById(R.id.buttonEntar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            final String campoEmail = editEmail.getText().toString();
            final String campoPassword = editPassword.getText().toString();

            @Override
            public void onClick(View view) {
                if (!campoEmail.isEmpty()) {
                    if (!campoPassword.isEmpty()) {
                        user = new User();

                        user.setEmail(campoEmail);
                        user.setPassword(campoPassword);

                        SignIn();

                    } else {
                        Toast.makeText(LoginActivity.this, "Preencha o password!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Preencha o email!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void SignIn() {
        FirebaseAuth auth = ConfigFirebase.getFirebaseAuth();
    }
}