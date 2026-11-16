# Atividade 29 - Catálogo BookStore Enterprise: Padrão DAO & Mapeamento Objeto-Relacional

> **Engenharia de Software & Programação Orientada a Objetos**  
> Disciplina: Programação Orientada a Objetos • UniCesumar  
> Prof. Mestre Júlio B. Herculani • Sistema Operacional: Windows 10 / 11

---

## 📌 1. Descrição do Projeto
Implementação do catálogo corporativo da livraria **BookStore** aplicando o padrão **Data Access Object (DAO)**, Inversão de Dependência (DIP - SOLID) e desacoplamento arquitetural entre regras de negócio e infraestrutura de persistência em **SQLite (`bookstore.db`)**.

O projeto resolve o problema clássico do **Impedance Mismatch** (descompasso objeto-relacional), mapeando tuplas relacionais de tabelas para entidades de domínio ricas e imutáveis, eliminando o risco de `NullPointerException` através do uso de `java.util.Optional`.

---

## 📁 2. Estrutura do Pacote
```text
Atividade 29 - Git/
├── .vscode/
│   └── settings.json                    <- Configurações do VS Code
├── .classpath & .project                <- Metadados Eclipse e extensões Java
├── lib/
│   ├── sqlite-jdbc-3.45.1.0.jar         <- Driver JDBC do SQLite
│   ├── slf4j-api-1.7.36.jar             <- Biblioteca de logging
│   ├── slf4j-simple-1.7.36.jar          <- Logger simples embutido
│   └── junit-platform-console-standalone-1.10.2.jar <- Test runner do JUnit 5
├── src/br/com/bookstore/
│   ├── model/
│   │   └── Livro.java                   <- Modelo de Domínio (POJO rico com validação Fail-Fast)
│   ├── factory/
│   │   └── ConexaoFactory.java          <- Fábrica centralizada de conexão com bookstore.db
│   ├── dao/
│   │   ├── LivroDAO.java                <- Contrato da Interface (DIP / Clean Architecture)
│   │   └── LivroDAOSqlite.java          <- Implementação concreta com SQLite e try-with-resources
│   └── main/
│       └── Main.java                    <- Roteiro oficial de testes e homologação
├── test/br/com/bookstore/test/
│   └── LivroDaoTest.java                <- Suíte de testes automatizados JUnit 5 (TDD 01 a 07)
├── CRC.md                               <- Documentação detalhada dos Cartões CRC
├── schema.sql                           <- Script DDL e consultas de auditoria
└── README.md
```

---

## 🧪 3. Cobertura de Testes Automatizados (TDD)
O projeto conta com suíte de testes unitários automatizados cobrindo o modelo e o DAO:
- **TDD 01**: Inserção com `PreparedStatement` e sincronização do ID gerado (`livro.getId() > 0`).
- **TDD 02**: Busca pontual por ID existente retornando `Optional` presente com atributos corretos.
- **TDD 03**: Busca por ID inexistente retornando `Optional.empty()` sem `NullPointerException`.
- **TDD 04**: Listagem completa do acervo ordenada por título via cursor `ResultSet`.
- **TDD 05**: Barreira Fail-Fast barrando título nulo, vazio ou com menos de 2 caracteres.
- **TDD 06**: Barreira Fail-Fast barrando autor nulo ou vazio.
- **TDD 07**: Barreira Fail-Fast barrando preço menor ou igual a zero.

---

## 🚀 4. Como Executar

### Executando a Aplicação Principal (Main):
```bash
javac -encoding UTF-8 -cp "lib/*" -d bin src/br/com/bookstore/**/*.java
java -cp "bin;lib/*" br.com.bookstore.main.Main
```

### Executando a Suíte de Testes JUnit 5:
```bash
javac -encoding UTF-8 -cp "lib/*" -d bin src/br/com/bookstore/**/*.java test/br/com/bookstore/test/*.java
java -jar lib/junit-platform-console-standalone-1.10.2.jar -cp "bin;lib/sqlite-jdbc-3.45.1.0.jar;lib/slf4j-api-1.7.36.jar;lib/slf4j-simple-1.7.36.jar" --scan-class-path
```
