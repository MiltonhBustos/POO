public class ContaBancaria {

    private String numeroConta;
    private double saldo;
    private Cliente titular;

    public ContaBancaria(String numeroConta, Cliente titular, double saldoInicial) {

        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldoInicial;

        Agencia.registrarNovaConta();
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
        }
    }

    public boolean sacar(double valor) {

        if (valor <= 0) {
            return false;
        }

        double valorTotal = valor + Agencia.TAXA_SAQUE;

        if (saldo >= valorTotal) {
            saldo = saldo - valorTotal;
            return true;
        }

        return false;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

}
