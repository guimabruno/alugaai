package com.example.alugai;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

public class Consulta_lista extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consulta_lista);

        List<ModeloDados> listaCarros = null;
        listaCarros = consultaTodosCarros();

        CarroAdapter adaptador = new CarroAdapter(this, listaCarros);

        lista = (ListView) findViewById(R.id.lista);

        lista.setAdapter(adaptador);
    }

    private List<ModeloDados> consultaTodosCarros() {
        List<ModeloDados> lista = new LinkedList<ModeloDados>();
        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.consultaCarros();

        if (dados.moveToFirst()){
            do {
                ModeloDados item = new ModeloDados();
                item.setIdCarro(dados.getInt(0));
                item.setIdUser(dados.getInt(1));
                item.setMarca(dados.getString(2));
                item.setModelo(dados.getString(3));
                item.setCor(dados.getString(4));
                item.setPlaca(dados.getString(5));
                item.setRenavam(dados.getString(6));
                item.setImagem(dados.getBlob(7));

                lista.add(item);
            } while (dados.moveToNext());
        } else {
            //String msg = "Não há carros cadastrados!";
            //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
        return lista;
    }
}