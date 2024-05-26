package com.example.alugai;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;


public class CadastroActivity extends AppCompatActivity {

    private EditText nomeEditText, emailEditText, enderecoEditText, cpfEditText, telefoneEditText, senhaEditText, confirmSenhaEditText;
    private Button cadastrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        nomeEditText = findViewById(R.id.nome);
        emailEditText = findViewById(R.id.email);
        enderecoEditText = findViewById(R.id.endereco);
        cpfEditText = findViewById(R.id.cpf);
        telefoneEditText = findViewById(R.id.telefone);
        senhaEditText = findViewById(R.id.senha);
        confirmSenhaEditText = findViewById(R.id.confirmSenha);
        cadastrarButton = findViewById(R.id.cadastrar);


        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String endereco = enderecoEditText.getText().toString();
                String cpf = cpfEditText.getText().toString();
                String telefone = telefoneEditText.getText().toString();
                String senha = senhaEditText.getText().toString();
                String confirmSenha = confirmSenhaEditText.getText().toString();


                if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(email) || TextUtils.isEmpty(endereco) ||
                        TextUtils.isEmpty(cpf) || TextUtils.isEmpty(telefone) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(confirmSenha)) {
                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!senha.equals(confirmSenha)) {
                    Toast.makeText(CadastroActivity.this, "As Senhas devem ser iguais", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
    }
}

