package com.example.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze.R;
import com.example.organizze.config.ConfigFirebase;
import com.example.organizze.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmailLogin, editPasswordLogin;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmailLogin = findViewById(R.id.editEmailLogin);
        editPasswordLogin = findViewById(R.id.editPasswordLogin);

        Button buttonEntrar = findViewById(R.id.buttonEntar);

        buttonEntrar.setOnClickListener(view -> {
            String campoEmail = editEmailLogin.getText().toString();
            String campoPassword = editPasswordLogin.getText().toString();

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
        });
    }

    public void SignIn() {
        FirebaseAuth auth = ConfigFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()).addOnCompleteListener( this, task -> {
            if (task.isSuccessful()) {
                nextHome();
            } else {
                String exception;
                try {
                    throw Objects.requireNonNull(task.getException());
                } catch (FirebaseAuthInvalidUserException erro) {
                    exception = "Usuário não está cadastrado!";
                } catch (FirebaseAuthInvalidCredentialsException erro) {
                    exception = "Emial e password não correspondem a um usuário cadastrado!";
                } catch (Exception erro) {
                    exception = "Erro ao cadastra usuário!" + erro.getMessage();
                    erro.printStackTrace();
                }
                Toast.makeText(LoginActivity.this, exception,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void nextHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}