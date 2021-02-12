package com.anderson.estoque.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensagem){
        super("Não foi possível encontrar " + mensagem);
    }
}
