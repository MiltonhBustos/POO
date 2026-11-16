package br.com.autotech.test;

import br.com.autotech.dao.PecaDao;
import br.com.autotech.model.Peca;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PecaDaoTest {

    private static PecaDao dao;

    @BeforeAll
    static void setUp() {
        dao = new PecaDao();
        dao.criarTabelaSeNaoExistir();
    }

    @Test
    @DisplayName("TDD 01: DDL Automático - Deve criar a tabela pecas sem erros")
    void deveCriarTabela() {
        assertDoesNotThrow(() -> dao.criarTabelaSeNaoExistir());
    }

    @Test
    @DisplayName("TDD 02: Insert e Chave Primária - Deve salvar peca e resgatar o ID gerado")
    void deveSalvarPecaEResgatarId() {
        String cod = "TEST-" + System.currentTimeMillis();
        Peca peca = new Peca(cod, "Peça de Teste Unitário", 55.0, 20);

        Peca salva = dao.salvar(peca);

        assertNotNull(salva.getId());
        assertTrue(salva.getId() > 0);
    }

    @Test
    @DisplayName("TDD 03: Consulta Geral - Deve listar pecas cadastradas")
    void deveListarTodasAsPecas() {
        String cod = "LIS-" + System.currentTimeMillis();
        dao.salvar(new Peca(cod, "Peça Para Listagem", 10.0, 5));

        List<Peca> lista = dao.listarTodas();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @DisplayName("TDD 04: Busca Filtrada - Deve localizar peca pelo codigo unico")
    void deveBuscarPecaPorCodigo() {
        String cod = "BUS-" + System.currentTimeMillis();
        dao.salvar(new Peca(cod, "Peça Para Busca", 89.90, 15));

        Peca encontrada = dao.buscarPorCodigo(cod);

        assertNotNull(encontrada);
        assertEquals(cod, encontrada.getCodigo());
        assertEquals("Peça Para Busca", encontrada.getNome());
    }

    @Test
    @DisplayName("TDD 05: Busca Filtrada - Deve retornar null ao buscar codigo inexistente")
    void deveRetornarNullParaCodigoInexistente() {
        Peca encontrada = dao.buscarPorCodigo("CODIGO_QUE_NAO_EXISTE_9999");
        assertNull(encontrada);
    }

    @Test
    @DisplayName("TDD 06: Baixa de Estoque - Deve atualizar estoque com sucesso quando houver saldo")
    void deveDarBaixaEstoqueComSucesso() {
        String cod = "BAX-" + System.currentTimeMillis();
        Peca salva = dao.salvar(new Peca(cod, "Peça Para Baixa", 30.0, 10));

        boolean sucesso = dao.darBaixaEstoque(salva.getId(), 4);
        assertTrue(sucesso);

        Peca atualizada = dao.buscarPorCodigo(cod);
        assertNotNull(atualizada);
        assertEquals(6, atualizada.getEstoque());
    }

    @Test
    @DisplayName("TDD 07: Baixa de Estoque - Deve falhar quando quantidade solicitada for maior que saldo")
    void deveRejeitarBaixaComEstoqueInsuficiente() {
        String cod = "INS-" + System.currentTimeMillis();
        Peca salva = dao.salvar(new Peca(cod, "Peça Saldo Baixo", 20.0, 2));

        boolean sucesso = dao.darBaixaEstoque(salva.getId(), 5);
        assertFalse(sucesso);

        Peca atualizada = dao.buscarPorCodigo(cod);
        assertNotNull(atualizada);
        assertEquals(2, atualizada.getEstoque());
    }
}
