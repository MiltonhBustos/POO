public class Main {

    public static void main(String[] args) {

        // 1. Cria dois clientes
        Cliente cliente = new Cliente(
                "123",
                "Milton",
                "milton@email.com"
        );

        Cliente cliente2 = new Cliente(
                "123",
                "Apollo",
                "apollo@email.com"
        );


        // 2. Testa se eles são iguais usando equals()
        if (cliente.equals(cliente2)) {

            System.out.println("Clientes são iguais (mesmo CPF).");

        } else {

            System.out.println("Clientes diferentes");
        }


        // 3. Instancia uma ContaBancaria com saldo de 50.0
        ContaBancaria conta = new ContaBancaria(
                "001",
                cliente,
                50.0
        );


        // 4. Tenta realizar um saque de 50.0
        boolean sucesso = conta.sacar(50.0);

        System.out.println(
                "Resultado do saque de 50.0: " + sucesso
        );


        // 5. Imprime o total de contas abertas
        System.out.println(
                "Total de contas abertas: " +
                Agencia.getTotalContasAbertas()
        );
    }
}