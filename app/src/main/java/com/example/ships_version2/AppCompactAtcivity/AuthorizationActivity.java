package com.example.ships_version2.AppCompactAtcivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ships_version2.Adapters.AuthManager;
import com.example.ships_version2.AppCompactAtcivity.MainActivity;
import com.example.ships_version2.R;

public class AuthorizationActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        authManager = new AuthManager(this);
        buttonLogin.setOnClickListener(v -> handleLogin());
        buttonRegister.setOnClickListener(v -> handleRegister());
    }

    private void handleLogin() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Введите имя пользователя и пароль", Toast.LENGTH_SHORT).show();
            return;
        }
        if (authManager.loginUser(username, password)) {
            Toast.makeText(this, "Вход выполнен успешно!", Toast.LENGTH_SHORT).show();
            navigateToMainActivity();
        } else {
            Toast.makeText(this, "Ошибка входа. Проверьте данные или зарегистрируйтесь.", Toast.LENGTH_LONG).show();
        }
    }
    private void handleRegister() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Введите имя пользователя и пароль для регистрации", Toast.LENGTH_SHORT).show();
            return;
        }

        if (authManager.registerUser(username, password)) {
            Toast.makeText(this, "Регистрация успешна! Теперь вы можете войти.", Toast.LENGTH_SHORT).show();
            editTextPassword.setText("");
        } else {
            Toast.makeText(this, "Ошибка регистрации. Возможно, имя пользователя занято.", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(AuthorizationActivity.this, count_player.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}