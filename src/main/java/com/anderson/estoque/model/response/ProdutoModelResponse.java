package com.anderson.estoque.model.response;

import java.math.BigDecimal;

public class ProdutoModelResponse {

    private String id;
    private String nome;
    private String marca;
    private BigDecimal preco;
    private int quantidade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object objetoComparado){
        ProdutoModelResponse produtoComparado = (ProdutoModelResponse) objetoComparado;

        if(produtoComparado.getId().equals(this.getId()) && produtoComparado.getNome().equals(this.getNome()) && produtoComparado.getMarca().equals(this.getMarca())) return true;
        else return false;
    }
}
