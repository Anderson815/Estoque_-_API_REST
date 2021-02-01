package com.anderson.estoque.exception;

public class RequestConstraintException extends RuntimeException{
    public RequestConstraintException(String mensagem){
        super(mensagem);
    }
}
