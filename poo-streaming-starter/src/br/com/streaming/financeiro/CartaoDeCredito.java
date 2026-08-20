package br.com.streaming.financeiro;

public class CartaoDeCredito implements IPagamento {
    private String numero;
    private double limite;
    // TODO: Gerar Getters, Setters e toString()

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "CartaoDeCredito [numero=" + numero + ", limite=" + limite + "]";
    }

    @Override
    public void efetuarPagamento(double valor) {
        System.out.println("Conectando com a adquirente");
        System.out.println("Aprovando transação de R$ " + valor + " no cartão ");
    }

}
