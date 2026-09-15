package br.com.technexus.model;
//exc1
public class Produto {
    private String nome;
    private String categoria;
    private double preco;

    
    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", categoria=" + categoria + ", preco=" + preco + "]";
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public Produto(String nome, String categoria, double preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }
}
