package br.com.ecommerce.main;

import br.com.ecommerce.exception.TipoFreteInvalidoException;
import br.com.ecommerce.model.CalculadoraFrete;
import br.com.ecommerce.model.FreteMotoboy;
import br.com.ecommerce.model.FretePac;
import br.com.ecommerce.model.FreteSedex;

public class Main {
    public static void main(String[] args) throws Exception {
        CalculadoraFrete calculadora = new CalculadoraFrete();

        try {
            System.out.println(calculadora.processarFrete(100, new FreteSedex()));
            System.out.println(calculadora.processarFrete(100, new FretePac()));
            System.out.println(calculadora.processarFrete(100, new FreteMotoboy()));
            
            calculadora.processarFrete(100, null);  

        } catch (TipoFreteInvalidoException e) {
            System.out.println(e.getMessage());
        }

    }
}
