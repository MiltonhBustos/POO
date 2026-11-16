-- ============================================================================
-- SCRIPT DE INFRAESTRUTURA DDL & AUDITORIA • AULA 30 (STARLOG ALMOXARIFADO)
-- ============================================================================

-- 1. Criação determinística da tabela de produtos com chave autoincrementável
CREATE TABLE IF NOT EXISTS produtos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    preco REAL NOT NULL,
    estoque INTEGER NOT NULL
);

-- 2. Carga inicial de dados para homologação de estoque
INSERT INTO produtos (nome, preco, estoque) VALUES 
('Teclado Mecânico RGB', 249.90, 10),
('Mousepad Speed XL', 79.90, 25),
('Mouse Gamer Óptico', 149.90, 18);

-- 3. Consultas canônicas de verificação e auditoria
SELECT * FROM produtos ORDER BY nome ASC;
SELECT id, nome, preco, estoque FROM produtos WHERE id = 1;
