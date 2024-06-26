package com.example.alugai;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class CadVeiculo extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    public String nome, email, endereco, cpf, telefone;
    public Bundle parametros;
    int idUser;
    EditText txtMarca, txtModelo, txtCor, txtPlaca, txtRenavam;
    Button btSelImage, btCadastrar;
    ImageView IMGFoto;
    Bitmap selectedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_veiculo);

        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtCor = findViewById(R.id.txtCor);
        txtPlaca = findViewById(R.id.txtPlaca);
        txtRenavam = findViewById(R.id.txtRenavam);

        btCadastrar = findViewById(R.id.btCadastrar);
        btSelImage = findViewById(R.id.btSelImage);
        IMGFoto = findViewById(R.id.IMGFoto);

        Intent intencao = getIntent();
        parametros = intencao.getExtras();
        assert parametros != null;
        idUser = parametros.getInt("idUser");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        endereco = parametros.getString("endereco");
        cpf = parametros.getString("cpf");
        telefone = parametros.getString("telefone");

        btSelImage.setOnClickListener(v -> {
            Intent tela = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(tela, PICK_IMAGE_REQUEST);
        });

        btCadastrar.setOnClickListener(v -> {
            Integer user = idUser;
            String marca = txtMarca.getText().toString();
            String modelo = txtModelo.getText().toString();
            String cor = txtCor.getText().toString();
            String placa = txtPlaca.getText().toString();
            String renavam = txtRenavam.getText().toString();

            if (validarDados(marca, modelo, cor, placa, renavam, selectedImageBitmap)) {
                salvar(user, marca, modelo, cor, placa, renavam, selectedImageBitmap);
            }
        });
    }

    private boolean validarDados(String marca, String modelo, String cor, String placa, String renavam, Bitmap imagem) {
        if (TextUtils.isEmpty(marca) || TextUtils.isEmpty(modelo) ||
                TextUtils.isEmpty(cor) || TextUtils.isEmpty(placa) || TextUtils.isEmpty(renavam) || imagem == null) {
            Toast.makeText(CadVeiculo.this, "Preencha todos os campos e selecione uma imagem", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void salvar(Integer user, String marca, String modelo, String cor, String placa, String renavam, Bitmap imagem) {
        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        resultado = bd.insereDadosCarro(user, marca, modelo, cor, placa, renavam, imagem);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        Intent tela = new Intent(this, Menu.class);
        parametros.putInt("idUser", idUser);
        parametros.putString("nome", nome);
        parametros.putString("email", email);
        parametros.putString("endereco", endereco);
        parametros.putString("cpf", cpf);
        parametros.putString("telefone", telefone);
        tela.putExtras(parametros);
        startActivity(tela);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                selectedImageBitmap = BitmapFactory.decodeStream(imageStream);
                IMGFoto.setImageBitmap(selectedImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
