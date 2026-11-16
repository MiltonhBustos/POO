package br.com.starlog.test;

import br.com.starlog.dao.ProdutoDAO;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.model.Produto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoDaoTest {

    private static ProdutoDAO dao;

    @BeforeAll
    static void setUp() {
        dao = new ProdutoDAOSqlite();
    }

    @Test
    @DisplayName("TDD 01: [CREATE] Deve salvar produto e preencher ID gerado")
    void deveSalvarProdutoEPreencherId() {
        Produto p = new Produto("Monitor Gamer 24 pol", 899.90, 8);
        dao.salvar(p);

        assertNotNull(p.getId());
        assertTrue(p.getId() > 0);
    }

    @Test
    @DisplayName("TDD 02: [READ] Deve buscar produto por ID existente retornando Optional presente")
    void deveBuscarPorIdExistente() {
        Produto p = new Produto("Headset Surround 7.1", 349.90, 12);
        dao.salvar(p);

        Optional<Produto> opt = dao.buscarPorId(p.getId());

        assertTrue(opt.isPresent());
        Produto encontrado = opt.get();
        assertEquals("Headset Surround 7.1", encontrado.getNome());
        assertEquals(349.90, encontrado.getPreco(), 0.001);
        assertEquals(12, encontrado.getEstoque());
    }

    @Test
    @DisplayName("TDD 03: [READ] Deve retornar Optional vazio para ID inexistente sem lançar exceção")
    void deveRetornarOptionalVazioParaIdInexistente() {
        Optional<Produto> opt = dao.buscarPorId(999999L);
        assertTrue(opt.isEmpty());
    }

    @Test
    @DisplayName("TDD 04: [READ] Deve listar todos os produtos ordenados alfabeticamente por nome")
    void deveListarTodosOrdenadosPorNome() {
        dao.salvar(new Produto("Z - Suporte Articulado", 120.0, 5));
        dao.salvar(new Produto("A - Cabo HDMI 2.1", 45.0, 30));

        List<Produto> lista = dao.listarTodos();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        for (int i = 0; i < lista.size() - 1; i++) {
            String nomeAtual = lista.get(i).getNome();
            String proximoNome = lista.get(i + 1).getNome();
            assertTrue(nomeAtual.compareToIgnoreCase(proximoNome) <= 0);
        }
    }

    @Test
    @DisplayName("TDD 05: [UPDATE] Deve atualizar produto existente retornando true")
    void deveAtualizarProdutoComSucesso() {
        Produto p = new Produto("Webcam Full HD 1080p", 199.90, 15);
        dao.salvar(p);

        p.setPreco(179.90);
        p.setEstoque(20);
        boolean atualizado = dao.atualizar(p);

        assertTrue(atualizado);

        Optional<Produto> recarregado = dao.buscarPorId(p.getId());
        assertTrue(recarregado.isPresent());
        assertEquals(179.90, recarregado.get().getPreco(), 0.001);
        assertEquals(20, recarregado.get().getEstoque());
    }

    @Test
    @DisplayName("TDD 06: [UPDATE] Deve retornar false ao tentar atualizar ID inexistente")
    void deveRetornarFalseAoAtualizarIdInexistente() {
        Produto p = new Produto(999999L, "Produto Fantasma", 100.0, 5);
        boolean atualizado = dao.atualizar(p);
        assertFalse(atualizado);
    }

    @Test
    @DisplayName("TDD 07: [DELETE] Deve excluir produto existente retornando true")
    void deveExcluirProdutoComSucesso() {
        Produto p = new Produto("Item Para Ser Deletado", 50.0, 1);
        dao.salvar(p);

        boolean excluido = dao.excluir(p.getId());
        assertTrue(excluido);

        Optional<Produto> busca = dao.buscarPorId(p.getId());
        assertTrue(busca.isEmpty());
    }

    @Test
    @DisplayName("TDD 08: [DELETE] Deve retornar false ao tentar excluir ID inexistente")
    void deveRetornarFalseAoExcluirIdInexistente() {
        boolean excluido = dao.excluir(888888L);
        assertFalse(excluido);
    }

    @Test
    @DisplayName("TDD 09: [FAIL-FAST] Construtor deve barrar nome nulo ou menor que 3 caracteres")
    void deveBarrarNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Produto(null, 100.0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Produto("   ", 100.0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Produto("AB", 100.0, 5));
    }

    @Test
    @DisplayName("TDD 10: [FAIL-FAST] Construtor deve barrar preço menor ou igual a zero")
    void deveBarrarPrecoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Nome Valido", 0.0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Produto("Nome Valido", -20.0, 5));
    }

    @Test
    @DisplayName("TDD 11: [FAIL-FAST] Construtor deve barrar estoque negativo")
    void deveBarrarEstoqueNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Nome Valido", 50.0, -1));
    }
}
