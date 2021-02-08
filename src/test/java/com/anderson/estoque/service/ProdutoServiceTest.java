package com.anderson.estoque.service;

import com.anderson.estoque.model.request.ProdutoModelRequest;
import com.anderson.estoque.model.response.ProdutoModelResponse;
import com.anderson.estoque.repository.ProdutoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void testCriarComSucesso(){

        ProdutoModelRequest produtoRequest = new ProdutoModelRequest();
        produtoRequest.setNome("PS5");
        produtoRequest.setMarca("Sony");
        produtoRequest.setPreco(new BigDecimal("4700.50"));
        produtoRequest.setQuantidade(25);

        ProdutoModelResponse produtoResponse = produtoService.criar(produtoRequest);
        assertNotNull(produtoResponse);
        assertNotEquals("", produtoResponse.getId());
    }

}
