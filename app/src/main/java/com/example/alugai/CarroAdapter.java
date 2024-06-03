package com.example.alugai;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
    public View getView(int position, View view, ViewGroup parent) {
        ModeloDados carro = listaCarros.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item,null);
        }

        ImageView IMGFoto = (ImageView) view.findViewById(R.id.IMGFoto);
        TextView txtidCarro = (TextView) view.findViewById(R.id.txtidCarro);
        TextView txtidUser = (TextView) view.findViewById(R.id.txtidUser);
        TextView txtmarca = (TextView) view.findViewById(R.id.txtmarca);
        TextView txtmodelo = (TextView) view.findViewById(R.id.txtmodelo);
        TextView txtcor = (TextView) view.findViewById(R.id.txtcor);
        TextView txtplaca = (TextView) view.findViewById(R.id.txtplaca);
        TextView txtrenavam = (TextView) view.findViewById(R.id.txtrenavam);

        IMGFoto.setImageBitmap(carro.getImagem());
        txtidCarro.setText(String.valueOf(carro.getIdCarro()));
        txtidUser.setText(String.valueOf(carro.getIdUser()));
        txtmarca.setText(String.valueOf(carro.getMarca()));
        txtmodelo.setText(String.valueOf(carro.getModelo()));
        txtcor.setText(String.valueOf(carro.getCor()));
        txtplaca.setText(String.valueOf(carro.getPlaca()));
        txtrenavam.setText(String.valueOf(carro.getRenavam()));

        return view;
    }

}
