package br.com.starlog.main;

import br.com.starlog.controller.ProdutoController;
import br.com.starlog.dao.ProdutoDAO;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.model.Produto;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("====================================================================");
        System.out.println("🔧 ALMOXARIFADO STARLOG • SISTEMA DE GESTÃO DE ESTOQUE (AULA 30)");
        System.out.println("   Disciplina: Programação Orientada a Objetos • UniCesumar");
        System.out.println("====================================================================");

        ProdutoDAO dao = new ProdutoDAOSqlite();
        ProdutoController controller = new ProdutoController(dao);

        System.out.println("\n--- 1. [CREATE] Cadastrando Produtos no SQLite ---");
        Produto p1 = new Produto("Teclado Mecânico RGB", 249.90, 10);
        Produto p2 = new Produto("Mousepad Speed XL", 79.90, 25);
        dao.salvar(p1);
        dao.salvar(p2);
        System.out.println("Produto 1 cadastrado: " + p1);
        System.out.println("Produto 2 cadastrado: " + p2);

        System.out.println("\n--- 2. [READ] Listagem Inicial em Colunas ---");
        imprimirTabela(controller.obterTodos());

        System.out.println("\n--- 3. [UPDATE] Atualizando Preço e Estoque do Teclado ---");
        boolean atualizado = controller.alterar(p1.getId(), "Teclado Mecânico RGB", 289.90, 15);
        if (atualizado) {
            System.out.println("Atualização confirmada!");
            controller.buscar(p1.getId()).ifPresent(p -> System.out.println("   -> " + p));
        } else {
            System.out.println("Falha na atualização!");
        }

        System.out.println("\n--- 4. [DELETE] Exclusão de Itens ---");
        boolean removido = controller.remover(p2.getId());
        if (removido) {
            System.out.println("Produto com ID " + p2.getId() + " removido com sucesso!");
        }

        boolean tentativaFalha = controller.remover(888L);
        if (!tentativaFalha) {
            System.out.println("Exclusão do ID 888 retornou false com segurança (registro inexistente).");
        }

        System.out.println("\n--- 5. [FINAL STATE] Catálogo Final no Banco ---");
        List<Produto> finalCat = controller.obterTodos();
        imprimirTabela(finalCat);
        System.out.println("Total de itens restantes no almoxarifado: " + finalCat.size());
        System.out.println("====================================================================");
    }

    private static void imprimirTabela(List<Produto> produtos) {
        System.out.printf("%-6s | %-28s | %-12s | %-10s%n", "ID", "NOME", "PREÇO", "ESTOQUE");
        System.out.println("--------------------------------------------------------------------");
        for (Produto p : produtos) {
            System.out.printf("#%-5d | %-28s | R$ %9.2f | %4d un%n",
                    p.getId(), p.getNome(), p.getPreco(), p.getEstoque());
        }
    }
}
