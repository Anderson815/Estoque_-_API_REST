package com.anderson.estoque.controller;

import com.anderson.estoque.exception.RequestConstraintException;
import com.anderson.estoque.model.request.ProdutoModelRequest;
import com.anderson.estoque.model.response.ProdutoModelResponse;
import com.anderson.estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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

    @GetMapping(value="marca/{marca}")
    public ResponseEntity<List<ProdutoModelResponse>> obterProdutosPelaMarca(@PathVariable String marca){
        return new ResponseEntity<>(produtoService.obterProdutosPelaMarca(marca), HttpStatus.OK);
    }

    @GetMapping(value="/{id_produto}")
    public ResponseEntity<ProdutoModelResponse> obterProdutoPeloId(@PathVariable(value="id_produto") String id){
        return new ResponseEntity<>(produtoService.obterProdutoPeloId(id), HttpStatus.OK);
    }

    @PutMapping(value="/{id_produto}")
    public ResponseEntity<ProdutoModelResponse> alterarProduto(@PathVariable(value="id_produto") String id, @RequestParam(value="valor", required=false, defaultValue="0.00") BigDecimal valor, @RequestParam(value="quantidade", required=false, defaultValue="0") int quantidade){
        return new ResponseEntity<>(produtoService.alterarProduto(id, valor, quantidade), HttpStatus.OK);
    }
}
