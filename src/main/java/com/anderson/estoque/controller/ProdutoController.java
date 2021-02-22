package com.anderson.estoque.controller;

import com.anderson.estoque.exception.RequestConstraintException;
import com.anderson.estoque.model.request.ProdutoModelRequest;
import com.anderson.estoque.model.response.ProdutoModelResponse;
import com.anderson.estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoModelResponse> criar(@Valid @RequestBody ProdutoModelRequest produtoRequest, BindingResult erroRequest){
        if(erroRequest.hasErrors()) throw new RequestConstraintException(erroRequest.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(produtoService.criar(produtoRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> obterProdutos(){
        return new ResponseEntity<>(produtoService.obterProdutos(), HttpStatus.OK);
    }

    @GetMapping(value="/{marca}")
    public ResponseEntity<List<ProdutoModelResponse>> obterProdutosPelaMarca(@PathVariable String marca){
        return new ResponseEntity<>(produtoService.obterProdutosPelaMarca(marca), HttpStatus.OK);
    }

    @GetMapping(value="/{id_produto}")
    public ResponseEntity<ProdutoModelResponse> obterProdutoPeloId(@PathVariable String id){
        return new ResponseEntity<>(produtoService.obterProdutoPeloId(id), HttpStatus.OK);
    }
}
