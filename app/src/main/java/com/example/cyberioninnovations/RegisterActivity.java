package com.example.cyberioninnovations;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btnRegister = findViewById(R.id.btnRegister);
        ImageButton btnShowPassword = findViewById(R.id.btnShowPassword);
        ImageButton btnShowConfirmPassword = findViewById(R.id.btnShowConfirmPassword);
        TextView tvGoToLogin = findViewById(R.id.tvGoToLogin);

        // Alternar visibilidade da senha
        btnShowPassword.setOnClickListener(view -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            etPassword.setSelection(etPassword.getText().length()); // Mantém o cursor no final
        });

        btnShowConfirmPassword.setOnClickListener(view -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            if (isConfirmPasswordVisible) {
                etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            etConfirmPassword.setSelection(etConfirmPassword.getText().length()); // Mantém o cursor no final
        });

        // Lógica de cadastro
        btnRegister.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Digite um e-mail válido!", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(this, "A senha deve ter pelo menos 6 caracteres!", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                // Aqui pode adicionar a lógica de salvar no Firebase ou no banco de dados

                // Redireciona para a tela de login e finaliza a activity de cadastro
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Direcionar para a tela de login
        tvGoToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Fecha a tela de cadastro para evitar voltar ao pressionar "Voltar"
        });
    }
}
