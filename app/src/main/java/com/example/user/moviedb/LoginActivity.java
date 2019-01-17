package com.example.user.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout TextInputLayoutUsername, TextInputLayoutPassword;
    private EditText editUsername, editPassword;
    private Button buttonLogin;
    private String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        TextInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        editUsername = findViewById(R.id.editTextUsername);
        editPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editUsername.getText().toString();
                password = editPassword.getText().toString();
                if (username.contains("user")&&password.contains("user")){
                    Toast.makeText(getApplicationContext(), "Anda berhasil masuk", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (username.matches("")){
                    TextInputLayoutUsername.setError("Isi Username");
                } else if (password.matches("")){
                    TextInputLayoutPassword.setError("Isi Password");
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau Password salah",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
