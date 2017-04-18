package com.testedesoftware;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CarrinhoDeCompraTest {

    @Test

    public void deve_adicionar_um_item_ao_carrinho()throws ValorInvalido {

        String descricao= "cama";
        Double valor= 1600d;
        Produto cama = new Produto(descricao,valor);

        CarrinhoDeCompra carrinhoDeCompra = new CarrinhoDeCompra();
        carrinhoDeCompra.adicionar(cama);

        assertThat(carrinhoDeCompra.getItensDoCarrinho(), contains(cama));


    }

    @Test
    public void deve_adicionar_um_produto_duas_vezes_e_incrementar_a_quantidade()throws ValorInvalido{
        String descricao = "cama";
        Double valor = 1600d;
        int quantidadeEsperada = 2;

        Produto cama = new Produto(descricao, valor);
        Produto celular = new Produto("Celular", 1800d);

        CarrinhoDeCompra carrinhoDeCompra = new CarrinhoDeCompra();
        carrinhoDeCompra.adicionar(cama);
        carrinhoDeCompra.adicionar(celular);
        carrinhoDeCompra.adicionar(celular);
        carrinhoDeCompra.adicionar(cama);

        int quantidadeRetornada = carrinhoDeCompra.getItensDoCarrinho().size();
        assertEquals(quantidadeEsperada, quantidadeRetornada);
    }

    @Test
    public void deve_remover_um_produto_do_carrinho() throws ValorInvalido{
        String descricao = "cama";
        Double valor = 1600d;

        Produto cama= new Produto(descricao,valor);
        CarrinhoDeCompra carrinhoDeCompra = new CarrinhoDeCompra();
        carrinhoDeCompra.adicionar(cama);
        carrinhoDeCompra.remover(cama);

        assertThat(carrinhoDeCompra.getItensDoCarrinho(),not(contains(cama)));

    }

    @Test(expected = ValorInvalido.class)
    public void nao_deve_aceitar_valor_negativo() throws ValorInvalido {
        String descricao="Notebook";
        Double valor = -3000d;

        new Produto(descricao,valor);
    }

    @Test
    public void deve_ser_possivel_adicionar_um_item_ao_carrinho() throws ValorInvalido {
        Produto umCelular = new Produto("Celular",100d);
        CarrinhoDeCompra carrinhoDeCompra = new CarrinhoDeCompra();

        CarrinhoRepository carrinhoRepository = Mockito.mock(CarrinhoDAO.class);
        carrinhoDeCompra.adicionar(umCelular);
        carrinhoRepository.salvar(carrinhoDeCompra);

        Mockito.verify(carrinhoRepository).salvar(carrinhoDeCompra);
    }

    @Test
    public void deve_verificar_se_o_metodo_salvar_foi_chamado_uma_vez() throws ValorInvalido {
        Produto umCelular = new Produto("Celular",100d);
        CarrinhoDeCompra carrinhoDeCompra = new CarrinhoDeCompra();

        CarrinhoRepository carrinhoRepository = Mockito.mock(CarrinhoDAO.class);
        carrinhoDeCompra.adicionar(umCelular);
        carrinhoRepository.salvar(carrinhoDeCompra);

        Mockito.verify(carrinhoRepository, Mockito.times(1)).salvar(carrinhoDeCompra);
    }
}
