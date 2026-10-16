package br.com.NexusCloud.main;

import br.com.NexusCloud.exception.CapacidadeExcedidaException;
import br.com.NexusCloud.model.Cliente;
import br.com.NexusCloud.model.CloudManager;
import br.com.NexusCloud.model.Container;
import br.com.NexusCloud.model.Servidor;

public class Main {
    public static void main(String[] args) {
        

        CloudManager manager = new CloudManager();

        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");
        Servidor servidor = new Servidor("192.168.0.1", 5.50, 16.0);

        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        Container c1 = new Container("C-01", "App1", 10.0);
        Container c2 = new Container("C-02", "App2", 8.0);

        //servidor.alocarContainer(c1);

        servidor.Ligar();

        try {
            servidor.alocarContainer(c1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            servidor.alocarContainer(c2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println("Erro capturado com sucesso: " + e.getMessage());
        }

        double fatura = manager.calcularFaturaAtiva(cliente);
        System.out.println("Fatura Ativa: R$ " + fatura);
    }
}