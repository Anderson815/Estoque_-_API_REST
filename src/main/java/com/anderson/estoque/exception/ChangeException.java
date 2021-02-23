package com.anderson.estoque.exception;

public class ChangeException extends RuntimeException{
    public ChangeException(){
        super("Nenhuma mudança foi feita: não foi informado o valor nem a quantidade");
    }
}
