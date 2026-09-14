package br.com.nexustech.model;

import br.com.nexustech.exception.NivelInsuficienteException;
//exc9
public class Masmorra {
    public void entrar(int nivelJogador) throws NivelInsuficienteException {

        if(nivelJogador < 50){ 
            throw new NivelInsuficienteException();
        }
        System.out.println("Você entrou na masmorra!");
    }
}
