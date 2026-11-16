package br.com.bookstore.main;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("  LIVRARIA BOOKSTORE ENTERPRISE • TESTE CANÔNICO DAO");
        System.out.println("====================================================");

        LivroDAO dao = new LivroDAOSqlite();

        System.out.println("\n--- 1. Cadastrando Livros no SQLite ---");
        Livro l1 = new Livro("Clean Code", "Robert C. Martin", 89.90);
        Livro l2 = new Livro("Design Patterns", "Erich Gamma et al.", 125.50);
        Livro l3 = new Livro("Refactoring", "Martin Fowler", 98.00);

        dao.salvar(l1);
        dao.salvar(l2);
        dao.salvar(l3);

        System.out.println("Livro salvo com sucesso: " + l1);
        System.out.println("Livro salvo com sucesso: " + l2);
        System.out.println("Livro salvo com sucesso: " + l3);

        System.out.println("\n--- 2. Consulta por ID Existente (ID: 2) ---");
        dao.buscarPorId(2L).ifPresentOrElse(
            livro -> System.out.println("Encontrado: " + livro),
            () -> System.out.println("Livro não localizado!")
        );

        System.out.println("\n--- 3. Consulta por ID Inexistente (ID: 999) ---");
        dao.buscarPorId(999L).ifPresentOrElse(
            livro -> System.out.println("Encontrado: " + livro),
            () -> System.out.println("Livro com ID 999 não encontrado (Optional.empty tratado com segurança!).")
        );

        System.out.println("\n--- 4. Listagem Geral do Acervo ---");
        List<Livro> catalogo = dao.listarTodos();
        System.out.printf("%-6s | %-25s | %-22s | %-10s%n", "ID", "TÍTULO", "AUTOR", "PREÇO");
        System.out.println("----------------------------------------------------------------------");
        for (Livro l : catalogo) {
            System.out.printf("#%-5d | %-25s | %-22s | R$ %8.2f%n",
                l.getId(), l.getTitulo(), l.getAutor(), l.getPreco());
        }
        System.out.println("====================================================");
    }
}
