package br.com.NexusCloud.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CloudManager {

    private Map<Cliente, Set<Servidor>> bancoDeDados;

    public CloudManager(){
        this.bancoDeDados = new HashMap<>();
    }

    public void registrarCliente(Cliente c){
        this.bancoDeDados.putIfAbsent(c, new java.util.HashSet<>());
    }

    public void provisionarServidor(Cliente c, Servidor s){
        if(bancoDeDados.containsKey(c)){
            bancoDeDados.get(c).add(s);
        }
    }

    public double calcularFaturaAtiva(Cliente c){

        if(c == null || !bancoDeDados.containsKey(c)){
            System.out.println("Cliente não existe ou não existem servidores cadastrados");
            return 0;
        }

        return bancoDeDados.get(c).stream()
                                  .filter(s -> s.isStatusLigado())
                                  .mapToDouble(s -> s.getPrecoPorHora())
                                  .sum();
    }
}
