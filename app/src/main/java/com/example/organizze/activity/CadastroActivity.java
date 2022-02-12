package com.example.organizze.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizze.R;
import com.example.organizze.config.ConfigFirebase;
import com.example.organizze.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Objects;

public class CadastroActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPassword;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        Button buttonCadast = findViewById(R.id.buttonEntar);

        buttonCadast.setOnClickListener(view -> {
            String campoName = editName.getText().toString();
            String campoEmail = editEmail.getText().toString();
            String campoPassword = editPassword.getText().toString();

            if (!campoName.isEmpty()) {
                if (!campoEmail.isEmpty()) {
                    if (!campoPassword.isEmpty()) {
                        user = new User();

                        user.setName(campoName);
                        user.setEmail(campoEmail);
                        user.setPassword(campoEmail);

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
        });
    }

    public void signUp() {
        FirebaseAuth auth = ConfigFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(CadastroActivity.this, "Sucesso ao cadastra usuário!",
                        Toast.LENGTH_SHORT).show();
            } else {
                String exception;
                try {
                    throw Objects.requireNonNull(task.getException());
                } catch (FirebaseAuthWeakPasswordException erro) {
                    exception = "Por favor digite um password mais forte!";
                } catch (FirebaseAuthInvalidCredentialsException erro) {
                    exception = "Por favor digite um email válido!";
                } catch (FirebaseAuthUserCollisionException erro) {
                    exception = "Essa conta já foi cadastrada!";
                } catch (Exception erro) {
                    exception = "Erro ao cadastra usuário!" + erro.getMessage();
                    erro.printStackTrace();
                }

                Toast.makeText(CadastroActivity.this, exception,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}