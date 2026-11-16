# AutoTech Parts • Persistência JDBC com SQLite (Aula 26)

> **Engenharia de Persistência: Prática de JDBC com SQLite (Banco em Arquivo Local)**  
> Disciplina de Programação Orientada a Objetos • UniCesumar  
> Prof. Mestre Júlio B. Herculani • Sistema Operacional: Windows 10 / 11

---

## 🏢 1. O Cenário de Negócio
A oficina mecânica **AutoTech** atende dezenas de veículos por dia em seu galpão de serviços rápidos. Os mecânicos utilizam computadores nos boxes de serviço para consultar e dar baixa em peças de reposição (pastilhas de freio, filtros de óleo, velas de ignição).

**O Grande Problema:** O sinal de Wi-Fi do galpão cai com frequência devido à estrutura metálica do teto e interferência das ferramentas elétricas. Se o sistema dependesse de um banco de dados na nuvem ou de um servidor pesado de rede (como PostgreSQL ou Oracle), **a oficina inteira pararia a cada oscilação de internet!**

**A Solução de Engenharia:** Desenvolver um software em Java com **persistência relacional local autônoma em SQLite (`autotech.db`)**, que roda 100% embutido na aplicação, sem precisar de servidores, sem logins complexos e sem dependência de internet.

---

## 📂 2. Arquitetura do Pacote
```text
Atividade 26 - Git/
├── .vscode/
│   └── settings.json                    <- Configuração do VS Code
├── .classpath & .project                <- Metadados para Eclipse e VS Code
├── lib/
│   ├── sqlite-jdbc-3.45.1.0.jar         <- Driver JDBC do SQLite (Xerial)
│   ├── slf4j-api-1.7.36.jar             <- Biblioteca de logging
│   ├── slf4j-simple-1.7.36.jar          <- Logger simples embutido
│   └── junit-platform-console-standalone-1.10.2.jar <- Executor de testes JUnit 5
├── src/br/com/autotech/
│   ├── model/
│   │   └── Peca.java                    <- Modelo de Domínio (POJO puro)
│   ├── factory/
│   │   └── ConexaoFactory.java          <- Fábrica centralizada de conexão com autotech.db
│   ├── dao/
│   │   └── PecaDao.java                 <- Implementação DAO com PreparedStatement e try-with-resources
│   └── main/
│       └── MainApp.java                 <- Interface interativa de console para o mecânico
├── test/br/com/autotech/test/
│   └── PecaDaoTest.java                 <- Suíte de testes automatizados JUnit 5 (TDD 01 a 07)
├── CRC.md                               <- Documentação dos Cartões CRC
├── executar.bat                         <- Script para compilação e execução rápida
└── README.md
```

---

## 🛠️ 3. As 5 Missões Implementadas no `PecaDao`

1. **Missão 1: DDL Automático (`criarTabelaSeNaoExistir`)**  
   Criação da tabela `pecas` automaticamente com `CREATE TABLE IF NOT EXISTS` garantindo autossuficiência do software.
2. **Missão 2: Insert com Resgate de Chave Primária (`salvar`)**  
   Inserção parametrizada com `PreparedStatement` e captura da chave gerada via `Statement.RETURN_GENERATED_KEYS` e `stmt.getGeneratedKeys()`.
3. **Missão 3: Consulta Geral (`listarTodas`)**  
   Varredura com `while (rs.next())`, lendo colunas pelo nome e populando lista de objetos `Peca`.
4. **Missão 4: Busca Filtrada por Código (`buscarPorCodigo`)**  
   Busca indexada com parâmetro posicional `WHERE codigo = ?`.
5. **Missão 5: Baixa de Estoque Atômica (`darBaixaEstoque`)**  
   Atualização protegida com validação de saldo no próprio SQL (`UPDATE pecas SET estoque = estoque - ? WHERE id = ? AND estoque >= ?;`).

---

## 🧪 4. Testes Automatizados (TDD)
O projeto conta com testes unitários automatizados cobrindo todas as operações do DAO:
- **TDD 01**: DDL Automático - Criação da tabela sem erros.
- **TDD 02**: Insert e Chave Primária - Resgate de ID gerado autoincrementado.
- **TDD 03**: Consulta Geral - Listagem ordenada de todas as peças.
- **TDD 04**: Busca Filtrada - Localização por código único.
- **TDD 05**: Busca Filtrada - Retorno nulo para código inexistente.
- **TDD 06**: Baixa de Estoque - Atualização com sucesso para quantidade válida.
- **TDD 07**: Baixa de Estoque - Rejeição automática para estoque insuficiente.

---

## 🚀 5. Como Executar

### Executando pelo Script Batch (Windows):
Dê um duplo clique no arquivo `executar.bat` ou execute no prompt:
```cmd
executar.bat
```

### Executando os Testes JUnit 5 via Terminal:
```bash
javac -encoding UTF-8 -cp "lib/*" -d bin src/br/com/autotech/**/*.java test/br/com/autotech/test/*.java
java -jar lib/junit-platform-console-standalone-1.10.2.jar -cp "bin;lib/sqlite-jdbc-3.45.1.0.jar;lib/slf4j-api-1.7.36.jar;lib/slf4j-simple-1.7.36.jar" --scan-class-path
```
