# Atividade 23 - Operação Enigma: Refatoração MVC

Projeto desenvolvido para a atividade da Aula 23 da disciplina de Programação Orientada a Objetos (POO).

## 📌 Descrição
Refatoração de código legado com aplicação da arquitetura em camadas e padrão MVC (Model-View-Controller), eliminando acoplamento direto entre interface gráfica (Java Swing) e regras de negócio/criptografia.

## 📁 Estrutura do Projeto
```text
Atividade 23 - Git/
├── src/
│   └── br/com/starlog/
│       ├── model/
│       │   └── CriptoMensagem.java        # Regras de criptografia (César), checksum e fail-fast
│       ├── controller/
│       │   └── TransmissaoController.java # Maestro que orquestra a View e o Model
│       └── amador/
│           └── TelaCaoticaLegada.java     # View Swing refatorada delegando para o Controller
├── test/
│   └── br/com/starlog/test/
│       └── CriptoMensagemTest.java        # Suíte oficial de 8 testes JUnit 5
├── lib/                                   # Dependências do JUnit
├── CRC.md                                 # Cartões de Classe-Responsabilidade-Colaboração
└── README.md
```

## 🧪 Testes Automatizados (TDD)
O projeto atende 100% dos testes unitários da suíte `CriptoMensagemTest`:
- **TDD 01**: Cifra de César com chave válida
- **TDD 02**: Preservação de espaços e pontuação
- **TDD 03**: Rotação circular do alfabeto (wrap-around)
- **TDD 04**: Cálculo de integridade via checksum ASCII
- **TDD 05**: Validação fail-fast no construtor para campos vazios ou nulos
- **TDD 06**: Validação fail-fast para chave fora do intervalo (1..25)
- **TDD 07**: Formatação correta do pacote final no Controller
- **TDD 08**: Validação e tratamento de chave não numérica no Controller
