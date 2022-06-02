package com.anderson.estoque.controller;

import com.anderson.estoque.exception.RequestConstraintException;
import com.anderson.estoque.model.request.ProdutoModelRequest;
import com.anderson.estoque.model.response.ProdutoModelResponse;
import com.anderson.estoque.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value = "Cadastra um novo produto")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Produto Cadastrado"),
        @ApiResponse(code = 400, message = "Alguma informação inválida para o cadastro do produto")
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProdutoModelResponse> criar(@ApiParam(name = "produtoRequest", value = "Objeto produto que será cadastrado no estoque", required = true) @Valid @RequestBody ProdutoModelRequest produtoRequest, BindingResult erroRequest){
        if(erroRequest.hasErrors()) throw new RequestConstraintException(erroRequest.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(produtoService.criar(produtoRequest), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna uma lista de todos os produtos cadastrados")
    @ApiResponse(code = 200, message = "Lista de todos os produtos cadastrados")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProdutoModelResponse>> obterProdutos(){
        return new ResponseEntity<>(produtoService.obterProdutos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de todos os produtos cadastrados de uma marca")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de todos os produtos cadastrados da marca"),
        @ApiResponse(code = 404, message = "Marca não encontrada")
    })
    @GetMapping(value = "marca/{marca}", produces = "application/json")
    public ResponseEntity<List<ProdutoModelResponse>> obterProdutosPelaMarca(@ApiParam(required = true) @PathVariable String marca){
        return new ResponseEntity<>(produtoService.obterProdutosPelaMarca(marca), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Produto pelo ID"),
        @ApiResponse(code = 404, message = "Produto não encontrado, pois o ID não existe")
    })
    @GetMapping(value = "/{id_produto}", produces = "application/json")
    public ResponseEntity<ProdutoModelResponse> obterProdutoPeloId(@ApiParam(name = "id_produto", value = "ID do produto que será consultado", required = true) @PathVariable(value="id_produto") String id){
        return new ResponseEntity<>(produtoService.obterProdutoPeloId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Modifica um produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Produto modificado"),
        @ApiResponse(code = 400, message = "Alguma informação inválida para alterar o produto"),
        @ApiResponse(code = 404, message = "Produto não encontrado, pois o ID não existe")
    })
    @PutMapping(value = "/{id_produto}", produces = "application/json")
    public ResponseEntity<ProdutoModelResponse> alterarProduto(@ApiParam(name = "id_produto", value = "ID do produto que será alterado", required = true) @PathVariable(value="id_produto") String id, @RequestParam(value="valor", required=false, defaultValue="") BigDecimal valor, @RequestParam(value="quantidade", required=false, defaultValue="") Integer quantidade){
        return new ResponseEntity<>(produtoService.alterarProduto(id, valor, quantidade), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta um produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Produto deletado"),
        @ApiResponse(code = 400, message = "O produto tem quantidade superior a 0"),
        @ApiResponse(code = 404, message = "Produto não encontrado, pois o ID não existe")
    })
    @DeleteMapping(value = "/{id_produto}", produces = "application/json")
    public ResponseEntity deletarProduto(@ApiParam(name = "id_produto", value = "ID do produto que será deletado", required = true) @PathVariable(value="id_produto") String id){
        produtoService.deletarProduto(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
