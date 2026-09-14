package br.com.nexustech.model;

import br.com.nexustech.exception.BanidoException;

public class Matchmaker {
    //exc13
    public void encontrarSala(ModoJogo modo, boolean jogadorBanido) throws BanidoException{

        if(jogadorBanido == true){
            throw new BanidoException();

        } 
        
        modo.buscarPartida();
        
    }
}
