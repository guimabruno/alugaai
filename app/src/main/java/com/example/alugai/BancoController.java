package com.example.alugai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }


    //inclusão de dados na tabela de Usuarios
    public String insereDadosUsuario(String _nome, String _email, String _endereco, String _cpf, String _telefone, String _senha) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        // Verifica se o email já existe
        Cursor cursor = db.query("usuarios", new String[]{"email"}, "email = ?", new String[]{_email}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return "Erro: O email já está cadastrado!";
        }
        cursor.close();

        valores = new ContentValues();
        valores.put("nome", _nome);
        valores.put("email", _email);
        valores.put("endereco", _endereco);
        valores.put("cpf", _cpf);
        valores.put("telefone", _telefone);
        valores.put("senha", _senha);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir os dados do usuário!";
        else
            return "Dados cadastrados com sucesso!";
    }


    public String insereDadosCarro(int idUser, String marca, String modelo, String cor, String placa, String renavam, Bitmap imagem) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("idUser", idUser);
        valores.put("marca", marca);
        valores.put("modelo", modelo);
        valores.put("cor", cor);
        valores.put("placa", placa);
        valores.put("renavam", renavam);
        valores.put("imagem", getBitmapAsByteArray(resizeAndCropToSquare(imagem, 500))); // Redimensionando e recortando a imagem antes de convertê-la para byte[]

        resultado = db.insert("veiculos", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir os dados do veículo!";
        else
            return "Dados do veículo cadastrados com sucesso!";
    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    // Método para redimensionar e recortar a imagem para um quadrado
    private Bitmap resizeAndCropToSquare(Bitmap bitmap, int size) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int newWidth = size;
        int newHeight = size;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);

        int cropStartX = (scaledBitmap.getWidth() - size) / 2;
        int cropStartY = (scaledBitmap.getHeight() - size) / 2;

        return Bitmap.createBitmap(scaledBitmap, cropStartX, cropStartY, size, size);
    }


    // Consultar para ver se usuario existe
    public Cursor carregaDadosParaLogin(String _login, String _senha) {
        Cursor cursor;
        // SELECT idUser, nome, email, endereco, cpf, telefone, senha FROM usuarios WHERE email = 'digitado' and senha = 'digitado'
        String[] campos = { "idUser", "nome", "email", "endereco", "cpf", "telefone", "senha" };
        String where = "email= '" + _login + "' and senha = '" + _senha + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor consultaCarros() {
        Cursor cursor;
        // SELECT idCarro, idUser, marca, modelo, cor, placa, renavam, imagem FROM carros
        String[] campos = { "idCarro", "idUser", "marca", "modelo", "cor", "placa", "renavam", "imagem" };
        db = banco.getReadableDatabase();
        cursor = db.query("veiculos", campos, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }}
