package com.anderson.estoque.exception;

public class InvalidValueException extends RuntimeException{
    public InvalidValueException(String mensagem){
        super("Informação inválida: " + mensagem);
    }
}
