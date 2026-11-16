# Cartões CRC - AutoTech Parts (Aula 26)

## Cartão CRC 01: Peca
* **Classe:** `br.com.autotech.model.Peca`
* **Camada:** Model (Modelo de Domínio / POJO)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Representar os dados essenciais de uma peça de reposição (id, código único, nome, preço e estoque). | *Nenhum* (Camada Model pura e isolada de SQL e de interface). |
| - Fornecer métodos de acesso (getters) e modificação (setters). | |
| - Formatar a representação textual alinhada e amigável da peça via `toString()`. | |

---

## Cartão CRC 02: ConexaoFactory
* **Classe:** `br.com.autotech.factory.ConexaoFactory`
* **Camada:** Factory / Infraestrutura de Dados

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Centralizar a URL de conexão JDBC (`jdbc:sqlite:autotech.db`) em um único ponto da aplicação. | - `java.sql.DriverManager` |
| - Estabelecer e fornecer conexões ativas com o banco de dados SQLite local via `obterConexao()`. | - `java.sql.Connection` |
| - Impedir instanciação indevida por ser classe utilitária (construtor privado). | |

---

## Cartão CRC 03: PecaDao
* **Classe:** `br.com.autotech.dao.PecaDao`
* **Camada:** DAO (Data Access Object / Persistência Relacional)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Executar DDL autônomo com `CREATE TABLE IF NOT EXISTS` para garantir que o banco crie a tabela `pecas` automaticamente sem falhas. | - `ConexaoFactory` |
| - Gravar novas peças usando `PreparedStatement` parametrizado (proteção contra SQL Injection CWE-89) e recuperar a chave primária autoincrementada com `Statement.RETURN_GENERATED_KEYS`. | - `Peca` (Model) |
| - Consultar todas as peças cadastradas ordenadas por nome, varrendo o `ResultSet` e convertendo tuplas relacionais em instâncias de `Peca`. | - `java.sql.Connection` |
| - Realizar busca parametrizada por código único (`WHERE codigo = ?`). | - `java.sql.PreparedStatement` |
| - Efetuar baixa atômica de estoque no balcão garantindo que o saldo não fique negativo (`UPDATE ... WHERE id = ? AND estoque >= ?`). | - `java.sql.ResultSet` |
| - Garantir fechamento determinístico de 100% das conexões, statements e cursores via `try-with-resources` (evitando Connection Leaks e erro `database is locked`). | |

---

## Cartão CRC 04: MainApp
* **Classe:** `br.com.autotech.main.MainApp`
* **Camada:** View / Console Interface & Ponto de Entrada

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Ponto de inicialização da aplicação (`main`). | - `PecaDao` |
| - Acionar a verificação da tabela no SQLite na abertura do sistema. | - `Peca` |
| - Apresentar o menu interativo com as opções do painel do mecânico. | |
| - Coletar entradas do usuário, sanitizar strings com `.trim().toUpperCase()` e tratar erros de conversão (`NumberFormatException`). | |
| - Delegar as operações de negócio e persistência para o `PecaDao`. | |
| - Apresentar feedback visual claro no terminal (listagem formatada, confirmações e avisos de erro). | |
