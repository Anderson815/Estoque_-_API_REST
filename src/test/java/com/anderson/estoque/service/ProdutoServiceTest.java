package com.anderson.estoque.service;

import com.anderson.estoque.exception.NotFoundException;
import com.anderson.estoque.model.request.ProdutoModelRequest;
import com.anderson.estoque.model.response.ProdutoModelResponse;
import com.anderson.estoque.repository.ProdutoRepository;
import com.anderson.estoque.resource.ProdutoResource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ProdutoResource produtoResource;

    @Before
    public void produtoResource(){
        produtoResource = new ProdutoResource();
        produtoResource.setId("1234");
        produtoResource.setNome("PS5");
        produtoResource.setMarca("Sony");
        produtoResource.setPreco(new BigDecimal("4700.50"));
        produtoResource.setQuantidade(25);
    }

    //Teste do método criar()
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

    //Teste do método obterProdutos()
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

    //Testes do método obterProdutosPelaMarca(...)
    @Test
    public void testObterProdutosPelaMarcaComSucesso(){

        //Parâmetro
        String marca = "Sony";

        //Preparo
        ProdutoResource produtoResource1 = new ProdutoResource();
        produtoResource1.setId("abcde");
        produtoResource1.setNome("Smart TV");
        produtoResource1.setMarca("Sony");
        produtoResource1.setPreco(new BigDecimal("3500.69"));
        produtoResource1.setQuantidade(89);

        ProdutoResource produtoResource3 = new ProdutoResource();
        produtoResource3.setId("klmno");
        produtoResource3.setNome("PlayStation 5");
        produtoResource3.setMarca("Sony");
        produtoResource3.setPreco(new BigDecimal("4685.27"));
        produtoResource3.setQuantidade(30);

        //Objeto para simulação
        List<ProdutoResource> listaProdutoResource = new ArrayList<>();
        listaProdutoResource.add(produtoResource1);
        listaProdutoResource.add(produtoResource3);

        //Simulação
        when(produtoRepository.existsByMarca(marca))
                .thenReturn(true);

        when(produtoRepository.findAllByMarca(marca))
                .thenReturn(listaProdutoResource);

        //Preparo do esperado
        ProdutoModelResponse produtoResponse1 = new ProdutoModelResponse();
        produtoResponse1.setId("abcde");
        produtoResponse1.setNome("Smart TV");
        produtoResponse1.setMarca("Sony");
        produtoResponse1.setPreco(new BigDecimal("1550.75"));
        produtoResponse1.setQuantidade(89);

        ProdutoModelResponse produtoResponse3 = new ProdutoModelResponse();
        produtoResponse3.setId("klmno");
        produtoResponse3.setNome("PlayStation 5");
        produtoResponse3.setMarca("Sony");
        produtoResponse3.setPreco(new BigDecimal("4685.27"));
        produtoResponse3.setQuantidade(30);

        //Esperado
        List<ProdutoModelResponse> listaProdutoResponseEsperado = new ArrayList<>();
        listaProdutoResponseEsperado.add(produtoResponse1);
        listaProdutoResponseEsperado.add(produtoResponse3);

        //Teste
        List<ProdutoModelResponse> listaProdutoResponseAtual = produtoService.obterProdutosPelaMarca(marca);
        assertArrayEquals(listaProdutoResponseEsperado.toArray(), listaProdutoResponseAtual.toArray());
    }

    @Test
    public void testObterProdutosPelaMarcaFalhaMarcaInexistente(){

        //Parâmetro
        String marca = "Sony";

        //Esperado
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Não foi possível encontrar a marca");

        //Simulação
        when(produtoRepository.existsByMarca(marca))
                .thenReturn(false);

        //Teste
        List<ProdutoModelResponse> listaProdutoResponseAtual = produtoService.obterProdutosPelaMarca(marca);
    }
    
    //Teste do método obterProdutoPeloId(...)
    @Test 
    public void testObterProdutoPeloIdComSucesso(){
        
        //Parâmetro
        String id = "1234";
        
        //Esperado
        ProdutoModelResponse produtoEsperado = new ProdutoModelResponse();
        produtoEsperado.setId("1234");
        produtoEsperado.setNome("PS5");
        produtoEsperado.setMarca("Sony");
        produtoEsperado.setPreco(new BigDecimal("4700.50"));
        produtoEsperado.setQuantidade(25);

        //Simulação
        when(produtoRepository.findById(id))
                .thenReturn(Optional.of(produtoResource));

        //Teste
        ProdutoModelResponse produtoAtual = produtoService.obterProdutoPeloId(id);
        assertEquals(produtoEsperado, produtoAtual);
    }

    @Test
    public void testObterProdutoPeloIdFalhaIdInexistente(){

        //Parâmetro
        String id = "1234";

        //Esperado
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Não foi possível encontrar o produto: " + id);

        //Simulação
        when(produtoRepository.findById(id))
                .thenReturn(Optional.ofNullable(null));

        //Teste
        ProdutoModelResponse produtoResponse = produtoService.obterProdutoPeloId(id);
    }

}
