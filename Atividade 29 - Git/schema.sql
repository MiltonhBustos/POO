-- ============================================================================
-- SCRIPT DE INFRAESTRUTURA DDL & HOMOLOGAÇÃO • AULA 29 (BOOKSTORE)
-- ============================================================================

-- 1. Criação determinística da tabela do catálogo de livros
CREATE TABLE IF NOT EXISTS livros (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    autor TEXT NOT NULL,
    preco REAL NOT NULL
);

-- 2. Carga inicial de dados para homologação e testes de bancada
INSERT INTO livros (titulo, autor, preco) VALUES 
('Clean Code', 'Robert C. Martin', 89.90),
('Design Patterns', 'Erich Gamma et al.', 125.50),
('Refactoring', 'Martin Fowler', 98.00);

-- 3. Consultas canônicas de verificação e auditoria
SELECT * FROM livros ORDER BY titulo;
SELECT id, titulo, autor, preco FROM livros WHERE id = 1;
