package com.anderson.estoque.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoModelRequest {

    @NotBlank(message = "não foi informado o nome do produto")
    private String nome;
    @NotBlank(message = "não foi informado a marca do produto")
    private String marca;
    @NotNull(message = "não foi informado o valor do produto")
    private BigDecimal valor;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
