package com.example.alugai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    String nome, email, endereco, cpf, telefone;
    int idUser;

    ImageView btSair;

    Button btAlugue, btDisponibilize, btMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btAlugue = (Button) findViewById(R.id.btAlugue);
        btDisponibilize = (Button) findViewById(R.id.btDisponibilize);
        btMenu = (Button) findViewById(R.id.btMenu);
        btSair = (ImageView) findViewById(R.id.btSair);

        Intent intencao = getIntent();
        Bundle parametros = intencao.getExtras();
        if (parametros != null && parametros.containsKey("idUser")) {
            idUser = parametros.getInt("idUser");
            nome = parametros.getString("nome");
            email = parametros.getString("email");
            endereco = parametros.getString("endereco");
            cpf = parametros.getString("cpf");
            telefone = parametros.getString("telefone");
        } else {
            idUser = 0; // Valor indicando que o usuário não foi passado.
            nome = "";
            email = "";
            endereco = "";
            cpf = "";
            telefone = "";
        }

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(Menu.this, Extras.class);
                parametros.putInt("idUser", idUser);
                parametros.putString("nome", nome);
                parametros.putString("email", email);
                parametros.putString("endereco", endereco);
                parametros.putString("cpf", cpf);
                parametros.putString("telefone", telefone);
                tela.putExtras(parametros);
                startActivity(tela);
            }
        });

        btAlugue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(Menu.this, Consulta_lista.class);
                parametros.putInt("idUser", idUser);
                parametros.putString("nome", nome);
                parametros.putString("email", email);
                parametros.putString("endereco", endereco);
                parametros.putString("cpf", cpf);
                parametros.putString("telefone", telefone);
                tela.putExtras(parametros);
                startActivity(tela);
            }
        });

        btDisponibilize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(Menu.this, CadVeiculo.class);
                parametros.putInt("idUser", idUser);
                parametros.putString("nome", nome);
                parametros.putString("email", email);
                parametros.putString("endereco", endereco);
                parametros.putString("cpf", cpf);
                parametros.putString("telefone", telefone);
                tela.putExtras(parametros);
                startActivity(tela);
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}