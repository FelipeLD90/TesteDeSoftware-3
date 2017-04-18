package com.testedepersistencia.Produto;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ProdutoRestTest {

    Produto produto;
    private int idProduto;

    @Before
    public void inicializar(){
        produto = new Produto("Notebook",5000d);
    }

    @Test
    public  void deve_salvar_um_produto(){
        salvarProduto();
    }

    @Test
    public void deve_buscar_todos_os_produtos(){
        int quantidadeEsperada = 1;

        Produto[] produtos =  given().
                 accept(MediaType.APPLICATION_JSON_VALUE).
                expect().
                    statusCode(200).
                when().
                    get("http://localhost:8080/produto").as(Produto[].class);

        assertEquals(quantidadeEsperada,produtos.length);
    }

    @Test
    public void deve_buscar_produto_pelo_id(){
        salvarProduto();
        Produto produtoRetornado =
                given().
                    accept(MediaType.APPLICATION_JSON_VALUE).
                expect().
                    statusCode(200).
                when().
                    get("http://localhost:8080/produto/"+idProduto).as(Produto.class);

        assertEquals(idProduto,produtoRetornado.getId());
    }
    @Test
    public void deve_remover_um_produto(){
        salvarProduto();
            given().
                accept(MediaType.APPLICATION_JSON_VALUE).
                expect().
                    statusCode(200).
                when().
                    delete("http://localhost:8080/produto/"+idProduto);
    }

    private void salvarProduto() {
        idProduto =
                Integer.parseInt(
                given().
                    contentType(MediaType.APPLICATION_JSON_VALUE).
                    body(produto).
                expect().
                    statusCode(200).
                when().
                    post("http://localhost:8080/produto").asString());
    }
}
