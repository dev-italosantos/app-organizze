package com.example.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button buttonEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonEntrar = findViewById(R.id.buttonEntar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            String campoEmail = editEmail.getText().toString();
            String campoPassword = editPassword.getText().toString();

            @Override
            public void onClick(View view) {
                if (!campoEmail.isEmpty()) {
                    if (!campoPassword.isEmpty()) {

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
}