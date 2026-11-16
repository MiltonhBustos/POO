# Cartões CRC - BookStore Enterprise (Aula 29)

## Cartão CRC 01: Livro
* **Classe:** `br.com.bookstore.model.Livro`
* **Camada:** Model (Modelo de Domínio / Entidade Pura)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Armazenar id, título, autor e preço de venda de um livro. | *Nenhum* (Camada Model desacoplada de persistência e SQL). |
| - Validar invariantes no construtor via barreira Fail-Fast (título com min. 2 caracteres, autor preenchido, preço > 0). | |
| - Sanitizar strings recebidas (.trim()). | |
| - Fornecer getters para leitura e setter exclusivo para o ID (sincronização de PK gerada). | |
| - Fornecer representação textual amigável via `toString()`. | |

---

## Cartão CRC 02: ConexaoFactory
* **Classe:** `br.com.bookstore.factory.ConexaoFactory`
* **Camada:** Factory / Infraestrutura de Conexão

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Centralizar a URL JDBC de conexão com o SQLite local (`jdbc:sqlite:bookstore.db`). | - `java.sql.DriverManager` |
| - Fornecer instâncias de `Connection` prontas para transações e operações via `obterConexao()`. | - `java.sql.Connection` |
| - Impedir instanciação indevida por ser utilitária (construtor privado). | |

---

## Cartão CRC 03: LivroDAO
* **Interface:** `br.com.bookstore.dao.LivroDAO`
* **Camada:** DAO Interface (Contrato Formal / Abstração DIP)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Declarar o contrato de persistência para as operações do catálogo (`salvar`, `buscarPorId`, `listarTodos`). | - `Livro` (Model) |
| - Isolar a camada de negócio de qualquer dependência ou import de `java.sql.*`. | - `java.util.Optional` |

---

## Cartão CRC 04: LivroDAOSqlite
* **Classe:** `br.com.bookstore.dao.LivroDAOSqlite`
* **Camada:** DAO Implementation (Persistência Concreta SQLite)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Implementar o contrato `LivroDAO` com banco relacional SQLite. | - `ConexaoFactory` |
| - Inicializar deterministicamente a tabela `livros` via DDL `CREATE TABLE IF NOT EXISTS` no construtor. | - `Livro` (Model) |
| - Inserir registros com `PreparedStatement` parametrizado (proteção contra SQL Injection) e capturar chave gerada (`getGeneratedKeys()`). | - `java.sql.Connection` |
| - Buscar livro por ID retornando `Optional<Livro>` (prevenindo `NullPointerException`). | - `java.sql.PreparedStatement` |
| - Listar todos os livros ordenados por título, delegando a conversão de tuplas para o método centralizador `mapearLinha(rs)`. | - `java.sql.ResultSet` |
| - Garantir fechamento de 100% dos recursos com `try-with-resources`. | |

---

## Cartão CRC 05: Main
* **Classe:** `br.com.bookstore.main.Main`
* **Camada:** Main / Ponto de Entrada e Homologação

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Instanciar o DAO através da interface `LivroDAO` (Inversão de Dependência). | - `LivroDAO` (Interface) |
| - Executar o roteiro canônico de testes: persistir 3 livros, consultar por ID existente e inexistente (tratando `Optional`), e listar acervo formatado. | - `LivroDAOSqlite` (Implementação) |
| - Apresentar os resultados de bancada no console. | - `Livro` (Model) |
