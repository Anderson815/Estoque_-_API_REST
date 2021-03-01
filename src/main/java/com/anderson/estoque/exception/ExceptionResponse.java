package com.anderson.estoque.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionResponse {
    private String data;
    private int cod;
    private String nome;
    private String mensagem;

    public ExceptionResponse(int cod, String nome, String mensagem) {

        Date date = new Date();
        SimpleDateFormat formatacao_data = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        this.data = formatacao_data.format(date);
        this.cod = cod;
        this.nome = nome;
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
