package br.com.cybercorp.main;

import br.com.cybercorp.model.Credencial;
import br.com.cybercorp.model.Departamento;
import br.com.cybercorp.model.Funcionario;
import br.com.cybercorp.model.SistemaSeguranca;
import br.com.cybercorp.model.Veiculo;

public class MainTeste {

    public static void main(String[] args) {

        // 1. Departamento
        Departamento departamento = new Departamento(
            "TI",
            "Tecnologia da Informação",
            1
        );

        // 2. Funcionário
        Funcionario funcionario = new Funcionario(
            "001",
            "Milton",
            departamento
        );

        // 3. Veículo
        Veiculo veiculo = new Veiculo(
            "ABC-1234",
            "Honda Civic",
            funcionario
        );

        // 4. Sistema com apenas 2 vagas
        SistemaSeguranca sistema = new SistemaSeguranca(2);

        // 5. Credencial original
        Credencial c1 = new Credencial(
            "FFF-999",
            true,
            funcionario
        );

        // 6. Clone
        Credencial clone = new Credencial(
            "FFF-999",
            true,
            funcionario
        );

        // 7. Catraca - primeira passagem
        sistema.registrarCatraca(funcionario);

        // 8. Catraca - segunda passagem
        sistema.registrarCatraca(funcionario);

        // 9. Cofre - credencial original
        sistema.acessarCofre(c1);

        // 10. Cofre - clone
        sistema.acessarCofre(clone);

        // 11. Garagem - vaga válida
        sistema.estacionarVeiculo(veiculo, 0);

        // 12. Garagem - vaga inválida
        sistema.estacionarVeiculo(veiculo, 5);
    }
}
