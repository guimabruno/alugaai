package com.example.alugai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    String nome, email, endereco, cpf, telefone, senha;
    int idUser;
    TextView txtMENUser;

    Button btAlugue, btDisponibilize, btMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btAlugue = (Button) findViewById(R.id.btAlugue);
        btDisponibilize = (Button) findViewById(R.id.btDisponibilize);
        btMenu = (Button) findViewById(R.id.btMenu);
        txtMENUser = (TextView) findViewById(R.id.txtMENUser);

        Intent intencao = getIntent();
        Bundle parametros = intencao.getExtras();
        assert parametros != null;
        idUser = parametros.getInt("idUser");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        endereco = parametros.getString("endereco");
        cpf = parametros.getString("cpf");
        telefone = parametros.getString("telefone");
        senha = parametros.getString("senha");

        txtMENUser.setText("Usuário: " + nome);

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
                Intent intent = new Intent(Menu.this, CadVeiculo.class);
                startActivity(intent);
            }
        });
    }
}