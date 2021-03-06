package com.example.organizze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizze.activity.CadastroActivity;
import com.example.organizze.activity.HomeActivity;
import com.example.organizze.activity.LoginActivity;
import com.example.organizze.config.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build()
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btnEntrar(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btnCadastrar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void verificarUsuarioLogado() {
        auth = ConfigFirebase.getFirebaseAuth();
        if (auth.getCurrentUser() != null) {
            home();
        }
    }

    public void home() {
        startActivity(new Intent(this, HomeActivity.class));
    }

}