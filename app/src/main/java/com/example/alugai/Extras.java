package com.example.alugai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Extras extends AppCompatActivity {
    String nome, email, endereco, cpf, telefone;
    int idUser;
    Button btMeusCarros, btHistContratos, btMensagens, btCarteira;

    ImageView btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_extras);

        btMeusCarros = (Button) findViewById(R.id.btMeusCarros);
        btHistContratos = (Button) findViewById(R.id.btHistContratos);
        btMensagens = (Button) findViewById(R.id.btMensagens);
        btCarteira = (Button) findViewById(R.id.btCarteira);
        btVoltar = (ImageView) findViewById(R.id.btVoltar);

        Intent intencao = getIntent();
        Bundle parametros = intencao.getExtras();
        assert parametros != null;
        idUser = parametros.getInt("idUser");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        endereco = parametros.getString("endereco");
        cpf = parametros.getString("cpf");
        telefone = parametros.getString("telefone");

        btVoltar.setOnClickListener(v -> {
            Intent tela = new Intent(Extras.this, Menu.class);
            parametros.putInt("idUser", idUser);
            parametros.putString("nome", nome);
            parametros.putString("email", email);
            parametros.putString("endereco", endereco);
            parametros.putString("cpf", cpf);
            parametros.putString("telefone", telefone);
            tela.putExtras(parametros);
            startActivity(tela);
        });

        };
    }