package br.com.starlog.model;

public class Produto {

    private Long id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(Long id, String nome, double preco, int estoque) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome do produto é obrigatório e deve ter no mínimo 3 caracteres!");
        }
        if (preco <= 0.0) {
            throw new IllegalArgumentException("O preço do produto deve ser estritamente positivo!");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("O estoque do produto não pode ser negativo!");
        }

        this.id = id;
        this.nome = nome.trim();
        this.preco = preco;
        this.estoque = estoque;
    }

    public Produto(String nome, double preco, int estoque) {
        this(null, nome, preco, estoque);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome do produto é obrigatório e deve ter no mínimo 3 caracteres!");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0.0) {
            throw new IllegalArgumentException("O preço do produto deve ser estritamente positivo!");
        }
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("O estoque do produto não pode ser negativo!");
        }
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        long idVal = 0;
        if (id != null) {
            idVal = id;
        }
        return String.format("[#%d] %-25s | R$ %8.2f | Estoque: %3d un", idVal, nome, preco, estoque);
    }
}
