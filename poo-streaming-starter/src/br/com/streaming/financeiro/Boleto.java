package br.com.streaming.financeiro;

public class Boleto implements IPagamento {
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public void efetuarPagamento(double valor) {
        System.out.println("Gerando boleto de pagamento de R$ " + valor + ".");
    }

}
