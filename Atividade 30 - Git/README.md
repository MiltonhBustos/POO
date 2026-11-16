# Atividade 30 - Almoxarifado StarLog: Prática DAO Completa & CRUD de Negócio

> **Engenharia de Persistência & Arquitetura em Camadas (CRUD Completo)**  
> Disciplina: Programação Orientada a Objetos • UniCesumar  
> Prof. Mestre Júlio B. Herculani • Sistema Operacional: Windows 10 / 11

---

## 📌 1. Descrição do Projeto
Implementação do sistema de almoxarifado corporativo **StarLog Parts** contemplando o ciclo completo de persistência relacional **CRUD** (Create, Read, Update, Delete) em banco de dados **SQLite (`starlog.db`)**.

O projeto consolida o padrão **DAO (Data Access Object)**, Inversão de Dependência (DIP - SOLID), tratamento determinístico de recursos via `try-with-resources` e blindagem contra injeção de SQL (CWE-89) com `PreparedStatement`.

---

## 📁 2. Estrutura do Pacote
```text
Atividade 30 - Git/
├── .vscode/
│   └── settings.json                    <- Configuração do VS Code
├── .classpath & .project                <- Metadados para Eclipse e VS Code
├── lib/
│   ├── sqlite-jdbc-3.45.1.0.jar         <- Driver JDBC do SQLite
│   ├── slf4j-api-1.7.36.jar             <- Biblioteca de logging
│   ├── slf4j-simple-1.7.36.jar          <- Logger simples embutido
│   └── junit-platform-console-standalone-1.10.2.jar <- Test runner do JUnit 5
├── src/br/com/starlog/
│   ├── model/
│   │   └── Produto.java                 <- Entidade de Domínio com barreira Fail-Fast
│   ├── factory/
│   │   └── ConexaoFactory.java          <- Fábrica centralizada de conexão (starlog.db)
│   ├── dao/
│   │   ├── ProdutoDAO.java              <- Contrato da Interface CRUD
│   │   └── ProdutoDAOSqlite.java        <- Implementação concreta SQLite com DDL automático
│   ├── controller/
│   │   └── ProdutoController.java       <- Maestro da aplicação que orquestra as regras
│   └── main/
│       └── Main.java                    <- Simulação oficial do ciclo completo CRUD
├── test/br/com/starlog/test/
│   └── ProdutoDaoTest.java              <- Suíte de testes automatizados JUnit 5 (TDD 01 a 11)
├── CRC.md                               <- Documentação detalhada dos Cartões CRC
├── schema.sql                           <- Script DDL determinístico e auditoria
└── README.md
```

---

## 🛠️ 3. As 5 Operações do Contrato CRUD (`ProdutoDAO`)

1. **`void salvar(Produto produto)` [Create]:**  
   Inserção com `PreparedStatement` parametrizado e captura da chave primária autoincrementada via `Statement.RETURN_GENERATED_KEYS` e `stmt.getGeneratedKeys()`.
2. **`Optional<Produto> buscarPorId(Long id)` [Read]:**  
   Busca pontual protegida por `Optional`, prevenindo `NullPointerException` caso o ID não exista.
3. **`List<Produto> listarTodos()` [Read]:**  
   Listagem do catálogo completo com ordenação obrigatória `ORDER BY nome ASC` e mapeamento das colunas via método auxiliar `mapearLinha(ResultSet rs)`.
4. **`boolean atualizar(Produto produto)` [Update]:**  
   Atualização de nome, preço e estoque validando as linhas afetadas no banco (`linhas > 0`).
5. **`boolean excluir(Long id)` [Delete]:**  
   Exclusão por chave primária com retorno booleano confirmando se a remoção foi realizada com sucesso.

---

## 🧪 4. Cobertura de Testes Automatizados (TDD)
O projeto conta com suíte de 11 testes unitários automatizados cobrindo o modelo, o DAO e as invariantes:
- **TDD 01**: [CREATE] Salvar produto e sincronizar o ID gerado pelo banco.
- **TDD 02**: [READ] Buscar produto por ID existente retornando `Optional` presente.
- **TDD 03**: [READ] Buscar produto por ID inexistente retornando `Optional.empty()` sem NPE.
- **TDD 04**: [READ] Listar todos os produtos ordenados alfabeticamente por nome.
- **TDD 05**: [UPDATE] Atualizar produto com sucesso quando ID existe.
- **TDD 06**: [UPDATE] Retornar false ao tentar atualizar ID inexistente.
- **TDD 07**: [DELETE] Excluir produto existente com sucesso retornando true.
- **TDD 08**: [DELETE] Retornar false ao tentar excluir ID inexistente.
- **TDD 09**: [FAIL-FAST] Barrar nome nulo ou com menos de 3 caracteres.
- **TDD 10**: [FAIL-FAST] Barrar preço menor ou igual a zero.
- **TDD 11**: [FAIL-FAST] Barrar estoque negativo.

---

## 🚀 5. Como Executar

### Executando o Sistema (Main):
```bash
javac -encoding UTF-8 -cp "lib/*" -d bin src/br/com/starlog/**/*.java
java -cp "bin;lib/*" br.com.starlog.main.Main
```

### Executando os Testes Automatizados (JUnit 5):
```bash
javac -encoding UTF-8 -cp "lib/*" -d bin src/br/com/starlog/**/*.java test/br/com/starlog/test/*.java
java -jar lib/junit-platform-console-standalone-1.10.2.jar -cp "bin;lib/sqlite-jdbc-3.45.1.0.jar;lib/slf4j-api-1.7.36.jar;lib/slf4j-simple-1.7.36.jar" --scan-class-path
```
