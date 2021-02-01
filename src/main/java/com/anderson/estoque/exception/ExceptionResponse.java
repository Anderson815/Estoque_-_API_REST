package com.anderson.estoque.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date data;
    private int cod;
    private String nome;
    private String mensagem;

    public ExceptionResponse(Date data, int cod, String nome, String mensagem) {
        this.data = data;
        this.cod = cod;
        this.nome = nome;
        this.mensagem = mensagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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
