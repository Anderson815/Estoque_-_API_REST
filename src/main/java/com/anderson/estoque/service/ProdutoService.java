package com.anderson.estoque.service;

import com.anderson.estoque.exception.NotFoundException;
import com.anderson.estoque.model.request.ProdutoModelRequest;
import com.anderson.estoque.model.response.ProdutoModelResponse;
import com.anderson.estoque.repository.ProdutoRepository;
import com.anderson.estoque.resource.ProdutoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoModelResponse criar(ProdutoModelRequest produtoRequest){

        ProdutoResource produtoResource = new ProdutoResource();
        produtoResource.setId(UUID.randomUUID().toString());
        produtoResource.setNome(produtoRequest.getNome());
        produtoResource.setMarca(produtoRequest.getMarca());
        produtoResource.setPreco(produtoRequest.getPreco().setScale(2, RoundingMode.DOWN));
        produtoResource.setQuantidade(produtoRequest.getQuantidade());

        produtoRepository.save(produtoResource);

        return produtoParaResposta(produtoResource);
    }

    public List<ProdutoModelResponse> obterProdutos(){

        List<ProdutoModelResponse> listaProdutoResponse = new ArrayList<>();
        List<ProdutoResource> listaProdutoResource = produtoRepository.findAll();

        for(ProdutoResource produtoResource: listaProdutoResource){
            ProdutoModelResponse produtoResponse = produtoParaResposta(produtoResource);
            listaProdutoResponse.add(produtoResponse);
        }

        return listaProdutoResponse;
    }

    public List<ProdutoModelResponse> obterProdutosPelaMarca(String marca){

        if(!produtoRepository.existsByMarca(marca)) throw new NotFoundException("a marca: " + marca);

        List<ProdutoModelResponse> listaProdutoResponse = new ArrayList<>();
        List<ProdutoResource> listaProdutoResource = produtoRepository.findAllByMarca(marca);

        for(ProdutoResource produtoResource: listaProdutoResource){
            ProdutoModelResponse produtoResponse = produtoParaResposta(produtoResource);
            listaProdutoResponse.add(produtoResponse);
        }

        return listaProdutoResponse;
    }

    public ProdutoModelResponse obterProdutoPeloId(String id){
        ProdutoResource produtoResource = obterProduto(id);
        return produtoParaResposta(produtoResource);
    }

    //Métodos auxiliares

    private ProdutoResource obterProduto(String id){
        return produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("o produto: " + id));
    }

    private ProdutoModelResponse produtoParaResposta(ProdutoResource produtoResource){

        ProdutoModelResponse produtoResponse = new ProdutoModelResponse();

        produtoResponse.setId(produtoResource.getId());
        produtoResponse.setNome(produtoResource.getNome());
        produtoResponse.setMarca(produtoResource.getMarca());
        produtoResponse.setPreco(produtoResource.getPreco());
        produtoResponse.setQuantidade(produtoResource.getQuantidade());

        return produtoResponse;
    }

}
