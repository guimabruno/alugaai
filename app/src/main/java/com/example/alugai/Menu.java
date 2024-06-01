package com.example.alugai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    String email_login = "";
    TextView txtMENUser;

    Button btAlugue, btDisponibilize, btMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btAlugue = (Button) findViewById(R.id.btAlugue);
        btDisponibilize = (Button) findViewById(R.id.btDisponibilize);
        btMenu = (Button) findViewById(R.id.btMenu);

        Intent intencao = getIntent();
        Bundle paramertros = intencao.getExtras();
        assert paramertros != null;
        email_login = paramertros.getString("email");

        txtMENUser = (TextView) findViewById(R.id.txtMENUser);
        txtMENUser.setText("Usuário: " + email_login);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Extras.class);
                startActivity(intent);
            }
        });

        btAlugue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
//                Intent intent = new Intent(Menu.this, Extras.class);
//                startActivity(intent);
            }
        });

        btDisponibilize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
//                Intent intent = new Intent(Menu.this, Extras.class);
//                startActivity(intent);
            }
        });
    }
}