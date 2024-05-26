package com.example.alugai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
}
