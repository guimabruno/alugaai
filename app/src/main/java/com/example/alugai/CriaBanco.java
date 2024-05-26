package com.example.alugai;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "alugaAI.db";
    private static final int VERSAO = 1;
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }
        @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "CREATE TABLE usuarios ("
                + "idUser integer primary key autoincrement,"
                + "nome text,"
                + "email text,"
                + "endereco text,"
                + "cpf text,"
                + "telefone text,"
                + "senha text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}

