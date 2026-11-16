# Atividade 24 - StarLog Logistics: Arquitetura MVC e Despacho de Cargas

Projeto desenvolvido para a atividade prática da Aula 24 da disciplina de Programação Orientada a Objetos (POO).

## 📌 Descrição
Implementação de um sistema de despacho operacional aplicando a arquitetura em camadas e o padrão MVC (Model-View-Controller). A aplicação integra uma interface gráfica em Java Swing com regras de domínio protegidas por barreiras fail-fast e cálculo dinâmico de seguro de cargas, com desacoplamento total entre visão, controle e modelo.

## 📁 Estrutura do Projeto
```text
Atividade 24 - Git/
├── src/
│   └── br/com/starlog/
│       ├── model/
│       │   └── Carga.java              # Modelo de Domínio (Fail-fast, cálculo de seguro e dados imutáveis)
│       ├── controller/
│       │   └── CargaController.java    # Maestro do fluxo (Parse, tratamento de exceções e buffer de memória)
│       ├── view/
│       │   └── TelaCadastroCarga.java  # Interface gráfica Swing (Delegação pura para o Controller)
│       └── main/
│           └── MainApp.java            # Inicialização segura na Event Dispatch Thread (EDT)
├── test/
│   └── br/com/starlog/test/
│       └── CargaMvcTest.java           # Suíte de testes automatizados JUnit 5
├── lib/                                # Dependências do JUnit Platform
├── CRC.md                              # Documentação dos Cartões CRC (Classes, Responsabilidades e Colaboradores)
└── README.md
```

## 🧪 Cobertura de Testes Unitários (TDD)
O projeto atende com 100% de sucesso (Barra Verde) a suíte de testes unitários:
- **TDD 01**: Cálculo da taxa base de 2% do valor declarado para cargas comuns.
- **TDD 02**: Aplicação de sobretaxa fixa de R$ 150 para cargas da categoria `"PERIGOSA"`.
- **TDD 03**: Aplicação de sobretaxa fixa de R$ 150 para cargas com peso superior a 100 kg.
- **TDD 04**: Barreira fail-fast barrando peso zero ou negativo com `IllegalArgumentException`.
- **TDD 05**: Barreira fail-fast barrando código de rastreio vazio ou em branco.
- **TDD 06**: Orquestração do Controller mantendo repositório em memória e totalizando corretamente.
- **TDD 07**: Barreira fail-fast barrando valor declarado zero ou negativo.
- **TDD 08**: Barreira fail-fast barrando categoria vazia ou nula.

## 🚀 Como Executar

### Executando a Aplicação Gráfica
```bash
javac -cp "lib/*" -d bin src/br/com/starlog/**/*.java
java -cp bin br.com.starlog.main.MainApp
```

### Executando os Testes Automatizados (JUnit 5)
```bash
javac -cp "lib/*" -d bin src/br/com/starlog/**/*.java test/br/com/starlog/test/*.java
java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
```
