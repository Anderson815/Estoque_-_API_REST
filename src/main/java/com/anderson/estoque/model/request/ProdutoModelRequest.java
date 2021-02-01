package com.anderson.estoque.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoModelRequest {

    @NotBlank(message = "não foi informado o nome do produto")
    private String nome;
    @NotBlank(message = "não foi informado a marca do produto")
    private String marca;
    @NotNull(message = "não foi informado o valor do produto")
    @Min(value = 0, message = "o preço não pode ser negativo")
    private BigDecimal preco;
    @Min(value = 0, message = "A quantidade não pode ser menor que 0")
    private int quantidade;


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

    public void setPreco(BigDecimal valor) {
        this.preco = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
