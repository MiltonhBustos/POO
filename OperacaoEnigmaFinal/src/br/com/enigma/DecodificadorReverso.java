package br.com.enigma;

public class DecodificadorReverso implements Decodificador {

    @Override
    public String decodificar(String texto) {

        StringBuilder resultado = new StringBuilder(texto);

        return resultado.reverse().toString();
    }
}