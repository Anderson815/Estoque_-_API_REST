package com.anderson.estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RequestConstraintException.class)
    public ResponseEntity<ExceptionResponse> responseRequestConstraintException(RequestConstraintException erro){
        ExceptionResponse respostaErro = new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), erro.getMessage());
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> responseNotFoundException(NotFoundException erro){
        ExceptionResponse respostaErro = new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), erro.getMessage());
        return new ResponseEntity<>(respostaErro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChangeException.class)
    public ResponseEntity<ExceptionResponse> responseChangeException(ChangeException erro){
        ExceptionResponse respostaErro = new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), erro.getMessage());
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ExceptionResponse> responseInvalidValueException(InvalidValueException erro){
        ExceptionResponse respostaErro = new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), erro.getMessage());
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

}
