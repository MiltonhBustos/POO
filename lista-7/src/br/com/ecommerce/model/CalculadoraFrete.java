package br.com.ecommerce.model;
import br.com.ecommerce.exception.TipoFreteInvalidoException;

public class CalculadoraFrete {
    public double processarFrete(double valorPedido, EstrategiaFrete estrategiaFrete) throws TipoFreteInvalidoException{
        if(estrategiaFrete == null){
            throw new TipoFreteInvalidoException();
        }
        return estrategiaFrete.calcular(valorPedido);
    }
}
