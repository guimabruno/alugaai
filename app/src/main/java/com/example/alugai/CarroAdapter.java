package com.example.alugai;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class CarroAdapter extends ArrayAdapter<ModeloDados> {
    private Context context;
    private List<ModeloDados> listaCarros = null;
    public CarroAdapter(Context context, List<ModeloDados> listaCarros) {
        super(context, 0, listaCarros);
        this.listaCarros = listaCarros;
        this.context = context;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ModeloDados carro = listaCarros.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item,null);
        }

        ImageView IMGFoto = (ImageView) view.findViewById(R.id.IMGFoto);
//        TextView txtidCarro = (TextView) view.findViewById(R.id.txtidCarro);
//        TextView txtidUser = (TextView) view.findViewById(R.id.txtidUser);
        TextView txtmarca = (TextView) view.findViewById(R.id.txtmarca);
        TextView txtmodelo = (TextView) view.findViewById(R.id.txtmodelo);
        TextView txtcor = (TextView) view.findViewById(R.id.txtcor);
        TextView txtplaca = (TextView) view.findViewById(R.id.txtplaca);
        TextView txtrenavam = (TextView) view.findViewById(R.id.txtrenavam);

        Button btEuQuero = (Button) view.findViewById(R.id.btEuQuero);

        btEuQuero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(context, Menu.class);
                Toast.makeText(context.getApplicationContext(), "Carro alugado com sucesso!", Toast.LENGTH_LONG).show();
                context.startActivity(tela);
            }
        });



        //IMGFoto.setImageBitmap(carro.getImagem());

        byte[] imagemBytes = carro.getImagem();
        Bitmap IMGConvertida = BitmapFactory.decodeByteArray(imagemBytes, 0, imagemBytes.length);
        IMGFoto.setImageBitmap(IMGConvertida);

//        txtidCarro.setText(String.valueOf(carro.getIdCarro()));
//        txtidUser.setText(String.valueOf(carro.getIdUser()));
        txtmarca.setText(String.valueOf(carro.getMarca()));
        txtmodelo.setText(String.valueOf(carro.getModelo()));
        txtcor.setText(String.valueOf(carro.getCor()));
        txtplaca.setText(String.valueOf(carro.getPlaca()));
        txtrenavam.setText(String.valueOf(carro.getRenavam()));

        return view;
    }

}

