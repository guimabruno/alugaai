package com.example.alugai;

import android.graphics.Bitmap;

public class ModeloDados {
    int idCarro, idUser;
    String marca, modelo, cor, placa, renavam;
    Bitmap imagem;

    // Construtor padrão
    public ModeloDados() {
    }

    // Construtor com parâmetros
    public ModeloDados(int idCarro, int idUser, String marca, String modelo, String cor, String placa, String renavam, Bitmap imagem) {
        this.setIdCarro(idCarro);
        this.setIdUser(idUser);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setCor(cor);
        this.setPlaca(placa);
        this.setRenavam(renavam);
        this.setImagem(imagem);
    }

    public void setIdCarro(int idCarro) { this.idCarro = idCarro; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setCor(String cor) { this.cor = cor; }
    public void setPlaca(String placa) { this.placa = placa; }
    public void setRenavam(String renavam) { this.renavam = renavam; }
    public void setImagem(Bitmap imagem) { this.imagem = imagem; }

    public int getIdCarro() { return idCarro; }
    public int getIdUser() { return idUser; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getCor() { return cor; }
    public String getPlaca() { return placa; }
    public String getRenavam() { return renavam; }
    public Bitmap getImagem() { return imagem; }
}
