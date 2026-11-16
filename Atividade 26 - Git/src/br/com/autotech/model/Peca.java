package br.com.autotech.model;

public class Peca {

    private Long id;
    private String codigo;
    private String nome;
    private double preco;
    private int estoque;

    public Peca() {
    }

    public Peca(String codigo, String nome, double preco, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Peca(Long id, String codigo, String nome, double preco, int estoque) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        long idVal = 0;
        if (id != null) {
            idVal = id;
        }
        return String.format("ID: %-3d | Cód: %-8s | %-30s | R$ %7.2f | Estoque: %3d un",
                idVal, codigo, nome, preco, estoque);
    }
}
