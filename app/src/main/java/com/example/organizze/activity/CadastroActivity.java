package com.example.organizze.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizze.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPassword;
    private Button buttonCadast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        buttonCadast = findViewById(R.id.buttonCadast);

        buttonCadast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String campoName = editName.getText().toString();
                String campoEmail = editEmail.getText().toString();
                String campoPassword = editPassword.getText().toString();

                if (!campoName.isEmpty()) {
                    if (!campoEmail.isEmpty()) {
                        if (!campoPassword.isEmpty()) {
                            signUp();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Preencha o password!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "Preencha o email!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signUp() {

    }
}