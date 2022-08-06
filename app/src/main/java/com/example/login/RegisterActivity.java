package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button btnRegisterUser;
    private User user;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.inputName);
        lastName = findViewById(R.id.inputLastName);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.inputConfirmPassword);
        btnRegisterUser = findViewById(R.id.btnRegisterUser);
        mAuth = FirebaseAuth.getInstance();

        btnRegisterUser.setOnClickListener(v -> {
            recoverData();

            if (password.getText().toString().equals(confirmPassword.getText().toString())){
                createLogin();
            } else {
                Toast.makeText(RegisterActivity.this, "As senhas não conferem", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createLogin() {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    user.setId(firebaseUser.getUid());
                    startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(RegisterActivity.this, "Erro ao cadastrar " + error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void recoverData() {
        if(name.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Você deve peencher todos os dados", Toast.LENGTH_LONG).show();
        } else {
            user = new User();
            user.setName(name.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setConfirmPassword(confirmPassword.getText().toString());
            //Toast.makeText(RegisterActivity.this,  "Nome: " + user.getName() + " Sobrenome: " + user.getLastName() + " Email: " + user.getEmail() + " Senha: " + user.getPassword() + " Confirmar Senha: " + user.getConfirmPassword(), Toast.LENGTH_LONG).show();
        }

    }


}