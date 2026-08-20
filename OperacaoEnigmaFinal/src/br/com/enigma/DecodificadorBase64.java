package br.com.enigma;

import java.util.Base64;

public class DecodificadorBase64 implements Decodificador {

    @Override
    public String decodificar(String textoCodificado) {

        byte[] bytesDecodificados =
                Base64.getDecoder().decode(textoCodificado);

        String textoDecodificado =
                new String(bytesDecodificados);

        return textoDecodificado;
    }
}