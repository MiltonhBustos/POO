package br.com.nexustech.main;

import br.com.nexustech.exception.NivelInsuficienteException;
import br.com.nexustech.exception.BanidoException;
import br.com.nexustech.model.Masmorra;
import br.com.nexustech.model.Matchmaker;
import br.com.nexustech.model.ModoCasual;
import br.com.nexustech.model.ModoRanqueado;
import br.com.nexustech.model.ModoJogo;

public class Main {
    public static void main(String[] args) {

        int kills = 15;
        int deaths = 0;

        //exc2
         try {

            System.out.println(kills / deaths);
            //java.lang.ArithmeticException: / by zero
        } catch (ArithmeticException e) {

            System.out.println("Taxa K/D: Jogador Invicto!");

        }

        // exc3
        String[] inventario = new String[3];

        try {

            inventario[5] = "Espada";

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Inventário cheio!");

        }
        
        //exc4
        String jogador = null;

        if (jogador != null) {
            System.out.println(jogador);
            
        } else {

            System.out.println("Jogador desconectado");

        }

        //exc5 E exc6
        try {

            conectarServidor();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally { //exc7
            //Ele vai rodar funcionando ou n!
            System.out.println("Fechando portas de rede do jogo...");

        }
        //exc9
        Masmorra masmorra = new Masmorra();
        
        try {
            masmorra.entrar(20);

        } catch (NivelInsuficienteException e) {
            System.out.println(e.getMessage());

        }

        //exc14
        Matchmaker matchmaker = new Matchmaker();

        ModoJogo casual = new ModoCasual();
        ModoJogo ranqueado = new ModoRanqueado();

        try {
            matchmaker.encontrarSala(casual, true);

        } catch (BanidoException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            matchmaker.encontrarSala(ranqueado, false);

        } catch (BanidoException e) {
            System.out.println(e.getMessage());
        }
    }

    // ------------------ FORA DA MAIN ----------------------
    //exc5
    public static void conectarServidor() throws Exception {

        throw new Exception("Servidor caiu!");

    }
}
