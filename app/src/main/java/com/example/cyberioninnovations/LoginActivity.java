package com.example.cyberioninnovations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvGoToRegister = findViewById(R.id.tvGoToRegister);

        // Lógica de login
        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                // Aqui você pode adicionar a lógica de autenticação (ex: Firebase Authentication)
                if (email.equals("user@example.com") && password.equals("password123")) {
                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Redireciona para a tela principal ou outra atividade
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "E-mail ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Direcionar para a tela de registro
        tvGoToRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish(); // Fecha a tela de login para evitar voltar ao pressionar "Voltar"
        });
    }
}
