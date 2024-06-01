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
        btLOGCadastro = (Button) findViewById(R.id.btCadastro);
        btLOGAcessar = (Button) findViewById(R.id.btLogin);

        btLOGAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor dados = validaDados();
                if (dados != null) {
                    Toast.makeText(getApplicationContext(), "Login com sucesso!", Toast.LENGTH_LONG).show();
                    Intent tela = new Intent(MainActivity.this, Menu.class);
                    Bundle parametros = new Bundle();
                    parametros.putInt("idUser", dados.getInt(dados.getColumnIndex("idUser")));
                    parametros.putString("nome", dados.getString(dados.getColumnIndex("nome")));
                    parametros.putString("email", dados.getString(dados.getColumnIndex("email")));
                    parametros.putString("endereco", dados.getString(dados.getColumnIndex("endereco")));
                    parametros.putString("cpf", dados.getString(dados.getColumnIndex("cpf")));
                    parametros.putString("telefone", dados.getString(dados.getColumnIndex("telefone")));
                    tela.putExtras(parametros);
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

    private Cursor validaDados() {
        String senha = txtLOGSenha.getText().toString();
        String login = txtLOGEmail.getText().toString();

        if (TextUtils.isEmpty(senha) || TextUtils.isEmpty(login)) {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
            return null;
        }

        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.carregaDadosParaLogin(login, senha);

        if (dados != null && dados.moveToFirst()) {
            return dados; // dados encontrados
        } else {
            Toast.makeText(getApplicationContext(), "O usuário não foi encontrado!", Toast.LENGTH_LONG).show();
            return null; // dados não encontrados
        }
    }
}