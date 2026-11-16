# Cartões CRC - Almoxarifado StarLog CRUD (Aula 30)

## Cartão CRC 01: Produto
* **Classe:** `br.com.starlog.model.Produto`
* **Camada:** Model (Modelo de Domínio / Entidade Pura)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Armazenar id, nome descritivo, preço de venda e quantidade em estoque. | *Nenhum* (Camada Model desacoplada de persistência e SQL). |
| - Proteger invariantes no construtor e setters via Fail-Fast (nome min. 3 caracteres, preço > 0, estoque >= 0). | |
| - Sanitizar strings recebidas (.trim()). | |
| - Fornecer getters e setters para manipulação dos dados. | |
| - Fornecer representação textual formatada via `toString()`. | |

---

## Cartão CRC 02: ConexaoFactory
* **Classe:** `br.com.starlog.factory.ConexaoFactory`
* **Camada:** Factory / Infraestrutura de Dados

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Centralizar a URL JDBC de conexão com o SQLite (`jdbc:sqlite:starlog.db`). | - `java.sql.DriverManager` |
| - Fornecer instâncias ativas de `Connection` via `obterConexao()`. | - `java.sql.Connection` |
| - Construtor privado para impedir instanciação. | |

---

## Cartão CRC 03: ProdutoDAO
* **Interface:** `br.com.starlog.dao.ProdutoDAO`
* **Camada:** DAO Interface (Contrato Formal CRUD / Abstração DIP)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Declarar o contrato com as 5 operações fundamentais de persistência (`salvar`, `buscarPorId`, `listarTodos`, `atualizar`, `excluir`). | - `Produto` (Model) |
| - Desacoplar a regra de negócio da infraestrutura relacional. | - `java.util.Optional` |

---

## Cartão CRC 04: ProdutoDAOSqlite
* **Classe:** `br.com.starlog.dao.ProdutoDAOSqlite`
* **Camada:** DAO Implementation (Persistência Concreta SQLite)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Implementar o contrato `ProdutoDAO` operando sobre o SQLite. | - `ConexaoFactory` |
| - Inicializar a tabela `produtos` deterministicamente com DDL `CREATE TABLE IF NOT EXISTS` no construtor. | - `Produto` (Model) |
| - Inserir registros com `PreparedStatement` parametrizado e recuperar PK autoincrementada gerada (`getGeneratedKeys()`). | - `java.sql.Connection` |
| - Buscar produto por ID retornando `Optional<Produto>` (prevenção contra `NullPointerException`). | - `java.sql.PreparedStatement` |
| - Listar todos os produtos com ordenação canônica obrigatória `ORDER BY nome ASC`. | - `java.sql.ResultSet` |
| - Atualizar e excluir registros verificando linhas afetadas (`linhas > 0`) e devolvendo booleano de confirmação. | |
| - Garantir fechamento determinístico de 100% dos recursos com `try-with-resources`. | |

---

## Cartão CRC 05: ProdutoController
* **Classe:** `br.com.starlog.controller.ProdutoController`
* **Camada:** Controller (Orquestrador do Fluxo)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Receber dados da interface e orquestrar as operações de negócio com o `ProdutoDAO`. | - `ProdutoDAO` (Interface) |
| - Instanciar entidades `Produto` e repassar para inserção ou atualização no DAO. | - `Produto` (Model) |
| - Fornecer métodos de consulta e remoção desacoplados. | |

---

## Cartão CRC 06: Main
* **Classe:** `br.com.starlog.main.Main`
* **Camada:** Main / Ponto de Entrada e Homologação de Negócio

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Ponto de inicialização do sistema no console. | - `ProdutoController` |
| - Injetar o `ProdutoDAO` no `ProdutoController` (Inversão de Dependência). | - `ProdutoDAO` / `ProdutoDAOSqlite` |
| - Executar a simulação de ciclo de vida completo: [Create], [Read], [Update], [Delete] e [Final State]. | - `Produto` |
| - Exibir relatórios tabulares limpos e formatados no terminal. | |
