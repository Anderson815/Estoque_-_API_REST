package com.anderson.estoque.service;

import com.anderson.estoque.model.request.ProdutoModelRequest;
import com.anderson.estoque.model.response.ProdutoModelResponse;
import com.anderson.estoque.repository.ProdutoRepository;
import com.anderson.estoque.resource.ProdutoResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testObterProdutosComSucesso(){

        //Preparo
        ProdutoResource produtoResource1 = new ProdutoResource();
        produtoResource1.setId("abcde");
        produtoResource1.setNome("Notebook");
        produtoResource1.setMarca("Positivo");
        produtoResource1.setPreco(new BigDecimal("1550.75"));
        produtoResource1.setQuantidade(89);

        ProdutoResource produtoResource2 = new ProdutoResource();
        produtoResource2.setId("fghij");
        produtoResource2.setNome("iPhone 12");
        produtoResource2.setMarca("Apple");
        produtoResource2.setPreco(new BigDecimal("7899.99"));
        produtoResource2.setQuantidade(150);

        ProdutoResource produtoResource3 = new ProdutoResource();
        produtoResource3.setId("klmno");
        produtoResource3.setNome("PlayStation 5");
        produtoResource3.setMarca("Sony");
        produtoResource3.setPreco(new BigDecimal("4685.27"));
        produtoResource3.setQuantidade(30);

        //Objeto para simulação
        List<ProdutoResource> listaProdutoResource = new ArrayList<>();
        listaProdutoResource.add(produtoResource1);
        listaProdutoResource.add(produtoResource2);
        listaProdutoResource.add(produtoResource3);
        
        //Simulação
        when(produtoRepository.findAll())
                .thenReturn(listaProdutoResource);
        
        //Preparo do esperado
        ProdutoModelResponse produtoResponse1 = new ProdutoModelResponse();
        produtoResponse1.setId("abcde");
        produtoResponse1.setNome("Notebook");
        produtoResponse1.setMarca("Positivo");
        produtoResponse1.setPreco(new BigDecimal("1550.75"));
        produtoResponse1.setQuantidade(89);

        ProdutoModelResponse produtoResponse2 = new ProdutoModelResponse();
        produtoResponse2.setId("fghij");
        produtoResponse2.setNome("iPhone 12");
        produtoResponse2.setMarca("Apple");
        produtoResponse2.setPreco(new BigDecimal("7899.99"));
        produtoResponse2.setQuantidade(150);

        ProdutoModelResponse produtoResponse3 = new ProdutoModelResponse();
        produtoResponse3.setId("klmno");
        produtoResponse3.setNome("PlayStation 5");
        produtoResponse3.setMarca("Sony");
        produtoResponse3.setPreco(new BigDecimal("4685.27"));
        produtoResponse3.setQuantidade(30);
        
        //Esperado
        List<ProdutoModelResponse> listaProdutoResponseEsperado = new ArrayList<>();
        listaProdutoResponseEsperado.add(produtoResponse1);
        listaProdutoResponseEsperado.add(produtoResponse2);
        listaProdutoResponseEsperado.add(produtoResponse3);

        //Teste
        List<ProdutoModelResponse> listaProdutoResponseAtual = produtoService.obterProdutos();
        assertArrayEquals(listaProdutoResponseEsperado.toArray(), listaProdutoResponseAtual.toArray());
    }
}