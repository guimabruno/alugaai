package com.example.alugai;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtLOGEmail, txtLOGSenha;
    Button btLOGCadastro, btLOGAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLOGEmail = (EditText) findViewById(R.id.txtLogin);
        txtLOGSenha = (EditText) findViewById(R.id.txtSenha);
        btLOGCadastro = findViewById(R.id.btCadastro);
        btLOGAcessar = (Button) findViewById(R.id.btLogin);

        btLOGAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaDados()) {
                    Toast.makeText(getApplicationContext(), "Login com sucesso!", Toast.LENGTH_LONG).show();
                    Intent tela = new Intent(MainActivity.this, Menu.class);
                    Bundle parametro = new Bundle();
                    parametro.putString("email", txtLOGEmail.getText().toString());
                    tela.putExtras(parametro);
                    startActivity(tela);
                }
            }
        });

        btLOGCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validaDados() {
        String msg = "";
        String senha = String.valueOf(txtLOGSenha.getText());
        String login = String.valueOf(txtLOGEmail.getText());

        boolean retorno = true;

        if (TextUtils.isEmpty(senha) || TextUtils.isEmpty(login)) {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
            retorno = false;
        }

        BancoController bd = new BancoController(getBaseContext());

        Cursor dados = bd.carregaDadosParaLogin(login, senha);

        if (dados.moveToFirst()) {
            // dados encontrados
            retorno = true;
        } else {
            msg = "O usuário não foi encontrado!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            retorno = false;
        }
        return retorno;
    }
}