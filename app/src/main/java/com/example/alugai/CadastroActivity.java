package com.example.alugai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText txtNome, txtEmail, txtEndereco, txtCPF, txtTelefone, txtSenha, txtConfSenha;
    private Button btCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        txtNome = findViewById(R.id.nome);
        txtEmail = findViewById(R.id.email);
        txtEndereco = findViewById(R.id.endereco);
        txtCPF = findViewById(R.id.cpf);
        txtCPF.addTextChangedListener(new MaskTextWatcher(txtCPF, "###.###.###-##"));
        txtTelefone = findViewById(R.id.telefone);
        txtTelefone.addTextChangedListener(new MaskTextWatcher(txtTelefone, "(##) #####-####"));
        txtSenha = findViewById(R.id.senha);
        txtConfSenha = findViewById(R.id.confirmSenha);
        btCadastro = findViewById(R.id.cadastrar);

        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();
                String endereco = txtEndereco.getText().toString();
                String cpf = txtCPF.getText().toString();
                String telefone = txtTelefone.getText().toString();
                String senha = txtSenha.getText().toString();
                String confirmSenha = txtConfSenha.getText().toString();

                if (validarDados(nome, email, endereco, cpf, telefone, senha, confirmSenha)) {
                    salvar(nome, email, endereco, cpf, telefone, senha);
                }
            }
        });
    }

    private boolean validarDados(String _nome, String _email, String _endereco, String _cpf, String _telefone, String _senha, String _confirmSenha) {
        boolean retorno = true;

        if (TextUtils.isEmpty(_nome) || TextUtils.isEmpty(_email) || TextUtils.isEmpty(_endereco) ||
                TextUtils.isEmpty(_cpf) || TextUtils.isEmpty(_telefone) || TextUtils.isEmpty(_senha) || TextUtils.isEmpty(_confirmSenha)) {
            Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            retorno = false;
        }

        if (!_senha.equals(_confirmSenha)) {
            Toast.makeText(CadastroActivity.this, "As Senhas devem ser iguais", Toast.LENGTH_SHORT).show();
            retorno = false;
        }

        return retorno;
    }

    private void salvar(String _nome, String _email, String _endereco, String _cpf, String _telefone, String _senha) {

        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        resultado = bd.insereDadosUsuario(_nome, _email, _endereco, _cpf, _telefone, _senha);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
