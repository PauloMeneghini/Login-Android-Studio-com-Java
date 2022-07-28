package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //chama a tela de cadastro ao clicar no botão
    public void RegisterScreen(View view){
        Intent registerScreen = new Intent(this, RegisterActivity.class);
        startActivity(registerScreen);
    }

    //chama a tela de login ao clicar no botão
    public void LoginScreen(View view){
        Intent loginScreen = new Intent(this, LoginActivity.class);
        startActivity((loginScreen));
    }

}