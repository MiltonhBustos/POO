package br.com.ecommerce.exception;

public class TipoFreteInvalidoException extends RuntimeException{

    public TipoFreteInvalidoException() {
        super("Erro de frete Inválido!");
        
    }
}
