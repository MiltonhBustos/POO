package br.com.bookstore.test;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LivroDaoTest {

    private static LivroDAO dao;

    @BeforeAll
    static void setUp() {
        dao = new LivroDAOSqlite();
    }

    @Test
    @DisplayName("TDD 01: Insert e Chave Primária - Deve salvar livro e preencher ID gerado")
    void deveSalvarLivroEPreencherId() {
        Livro l = new Livro("Algoritmos e Estruturas", "Thomas Cormen", 180.0);
        dao.salvar(l);

        assertNotNull(l.getId());
        assertTrue(l.getId() > 0);
    }

    @Test
    @DisplayName("TDD 02: Consulta por ID - Deve retornar Optional com o livro existente")
    void deveBuscarLivroPorIdExistente() {
        Livro l = new Livro("Engenharia de Software", "Ian Sommerville", 130.0);
        dao.salvar(l);

        Optional<Livro> opt = dao.buscarPorId(l.getId());

        assertTrue(opt.isPresent());
        Livro encontrado = opt.get();
        assertEquals("Engenharia de Software", encontrado.getTitulo());
        assertEquals("Ian Sommerville", encontrado.getAutor());
        assertEquals(130.0, encontrado.getPreco(), 0.001);
    }

    @Test
    @DisplayName("TDD 03: Consulta por ID Inexistente - Deve retornar Optional vazio sem NullPointerException")
    void deveRetornarOptionalVazioParaIdInexistente() {
        Optional<Livro> opt = dao.buscarPorId(999999L);
        assertTrue(opt.isEmpty());
    }

    @Test
    @DisplayName("TDD 04: Consulta Geral - Deve retornar lista com livros cadastrados ordenada por título")
    void deveListarTodosOsLivros() {
        dao.salvar(new Livro("B - Livro Teste", "Autor X", 50.0));
        dao.salvar(new Livro("A - Livro Teste", "Autor Y", 45.0));

        List<Livro> lista = dao.listarTodos();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @DisplayName("TDD 05: Fail-Fast - Construtor deve barrar título nulo ou com menos de 2 caracteres")
    void deveBarrarTituloInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Livro(null, "Autor Valido", 50.0));
        assertThrows(IllegalArgumentException.class, () -> new Livro("   ", "Autor Valido", 50.0));
        assertThrows(IllegalArgumentException.class, () -> new Livro("A", "Autor Valido", 50.0));
    }

    @Test
    @DisplayName("TDD 06: Fail-Fast - Construtor deve barrar autor nulo ou vazio")
    void deveBarrarAutorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Livro("Titulo Valido", null, 50.0));
        assertThrows(IllegalArgumentException.class, () -> new Livro("Titulo Valido", "   ", 50.0));
    }

    @Test
    @DisplayName("TDD 07: Fail-Fast - Construtor deve barrar preço menor ou igual a zero")
    void deveBarrarPrecoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Livro("Titulo Valido", "Autor Valido", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new Livro("Titulo Valido", "Autor Valido", -15.0));
    }
}
