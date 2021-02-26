package com.anderson.estoque.exception;

public class DeleteException extends RuntimeException {
    public DeleteException(){
        super("Esse produto ainda tem unidades no estoque. Para deletar o produto é necessário que a quantidade dele seja 0 em estoque");
    }
}
