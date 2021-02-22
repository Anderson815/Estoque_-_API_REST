package com.anderson.estoque.controller;

import com.anderson.estoque.exception.NotFoundException;
import com.anderson.estoque.model.request.ProdutoModelRequest;
import com.anderson.estoque.model.response.ProdutoModelResponse;
import com.anderson.estoque.service.ProdutoService;
import com.sun.xml.internal.fastinfoset.Encoder;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    //Testes do método criar()
    @Test
    public void testCriarComSucesso() throws Exception{

        String body = "{\"nome\": \"Notebook\", \"marca\": \"Positivo\", \"preco\": 1550.78, \"quantidade\": 5}";

        this.mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    public void testCriarComSucessoSemQuantidade() throws Exception{

        String body = "{\"nome\": \"Notebook\", \"marca\": \"Positivo\", \"preco\": 1550.78}";

        this.mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCriarFalhaFaltaNome() throws Exception{

        String body = "{\"marca\": \"Positivo\", \"preco\": 1550.78, \"quantidade\": 5}";

        this.mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem", Matchers.is("não foi informado o nome do produto")));
    }

    @Test
    public void testCriarFalhaNomeVazio() throws Exception{

        String body = "{\"nome\": \"\", \"marca\":\"Positivo\", \"preco\": 1550.78, \"quantidade\": 5}";

        this.mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem", Matchers.is("não foi informado o nome do produto")));
    }

    @Test
    public void testCriarFalhaFaltaMarca() throws Exception{

        String body = "{\"nome\": \"Notebook\", \"preco\": 1550.75, \"quantidade\": 5}";

        mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem", Matchers.is("não foi informado a marca do produto")));
    }

    @Test
    public void testCriarFalhaMarcaVazio() throws Exception{

        String body = "{\"nome\": \"Notebook\", \"marca\": \"\", \"preco\": 1550.75, \"quantidade\": 5}";

        mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem", Matchers.is("não foi informado a marca do produto")));
    }

    @Test
    public void testCriarFalhaFaltaPreco() throws Exception{

        String body = "{\"nome\": \"Notebook\", \"marca\": \"Positivo\", \"quantidade\": 5}";

        mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem", Matchers.is("não foi informado o valor do produto")));
    }

    @Test
    public void testCriarFalhaPrecoNegativo() throws Exception{

        String body = "{\"nome\": \"Notebook\", \"marca\": \"Positivo\", \"preco\": -0.01, \"quantidade\": 5}";

        mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem", Matchers.is("o preço não pode ser negativo")));
    }

    @Test
    public void testCriarFalharQuantidadeNegativo() throws Exception{

        String body = "{\"nome\": \"Notebook\", \"marca\": \"Positivo\", \"preco\": 0.00, \"quantidade\": -1}";

        mockMvc.perform(post("/produto").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem", Matchers.is("A quantidade não pode ser menor que 0")));
    }

    //Teste do método obterProdutos()
    @Test
    public void testObterProdutosComSucesso() throws Exception{

        mockMvc.perform(get("/produto"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Testes do método obterProdutosPelaMarca(...)
    @Test
    public void testObterProdutosPelaMarcaComSucesso() throws Exception{

        //Parâmetro
        String marca = "Sony";
        
        //Preparo
        ProdutoModelResponse produtoResponse1 = new ProdutoModelResponse();
        produtoResponse1.setId("abcde");
        produtoResponse1.setNome("Smart TV");
        produtoResponse1.setMarca("Sony");
        produtoResponse1.setPreco(new BigDecimal("1550.75"));
        produtoResponse1.setQuantidade(89);

        ProdutoModelResponse produtoResponse2 = new ProdutoModelResponse();
        produtoResponse2.setId("klmno");
        produtoResponse2.setNome("PlayStation 5");
        produtoResponse2.setMarca("Sony");
        produtoResponse2.setPreco(new BigDecimal("4685.27"));
        produtoResponse2.setQuantidade(20);

        //Objeto para simulação
        List<ProdutoModelResponse> listaProdutoResponseEsperado = new ArrayList<>();
        listaProdutoResponseEsperado.add(produtoResponse1);
        listaProdutoResponseEsperado.add(produtoResponse2);

        //Simulação
        when(produtoService.obterProdutosPelaMarca(marca))
                .thenReturn(listaProdutoResponseEsperado);
        
        //Teste
        mockMvc.perform(get("/produto/{marca}", marca).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void testObterProdutosPelaMarcaFalhaMarcaNaoEncontrada() throws Exception{

        //parâmetro
        String marca = "Positivo";

        //simulação
        when(produtoService.obterProdutosPelaMarca(marca))
                .thenThrow(new NotFoundException("a marca:" + marca));

        //Teste
        mockMvc.perform(get("/produto/{marca}", marca).contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.status().isNotFound())
              .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem", Matchers.is("Não foi possível encontrar a marca:" + marca)));
    }

}
