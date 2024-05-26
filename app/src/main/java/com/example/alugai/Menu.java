package com.example.alugai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    String email_login = "";
    TextView txtMENUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intencao = getIntent();
        Bundle paramertros = intencao.getExtras();
        email_login = paramertros.getString("email");

        txtMENUser = (TextView) findViewById(R.id.txtMENUser);
        txtMENUser.setText("Usuário: " + email_login);
    }
}